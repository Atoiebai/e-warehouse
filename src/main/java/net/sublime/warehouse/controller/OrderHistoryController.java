package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Order;
import net.sublime.warehouse.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reports/order-history")
@AllArgsConstructor
public class OrderHistoryController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
                                                 @RequestParam(required = false) Integer limit,
                                                 @RequestParam(required = false) Integer offset,
                                                 @RequestParam(required = false) String dateFrom,
                                                 @RequestParam(required = false) String dateTo) {

        int  limitN, offsetN;
        Date from, to;

        limitN = limit == null ? 0 : limit;
        offsetN = offset == null ? 0 : offset;
        try {
            from = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateFrom);
            to = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateTo);
            System.out.println(from);
            System.out.println(to);
        } catch (ParseException e) {
            from = null;
            to = null;
        }

        Date finalFrom = from;
        Date finalTo = to;
        return new ResponseEntity<>(
                orderService.getAll(limitN,offsetN - limitN)
                        .stream()
                        .skip(limitN)
                        .filter(x -> x.getCreatedAt().after(finalFrom))
                        .filter(x -> x.getCreatedAt().before(finalTo))
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );

    }

}
