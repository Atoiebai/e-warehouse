package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Order;
import net.sublime.warehouse.model.Product;
import net.sublime.warehouse.model.ProductBundle;
import net.sublime.warehouse.reposirtory.ProductRepository;
import net.sublime.warehouse.service.order.OrderService;
import net.sublime.warehouse.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/catalog/sales")
@AllArgsConstructor
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> newOrder(@RequestBody ProductBundle... productBundle) {
        Order order = new Order();
        // TODO: 2/9/2021 Check if all products has correct id
        System.out.println("All products are exist");


        order.getProductBundle().addAll(Arrays.stream(productBundle).collect(Collectors.toSet()));
         orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
