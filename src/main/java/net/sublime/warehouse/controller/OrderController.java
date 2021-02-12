package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Order;
import net.sublime.warehouse.model.ProductBundle;
import net.sublime.warehouse.service.order.OrderService;
import net.sublime.warehouse.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
        Arrays.stream(productBundle).forEach(
                x -> x
                        .setProductName(productService
                                .getProduct(x.getProductId()).getName()));
        Arrays.stream(productBundle).forEach(
                x -> x.setPrice(productService
                        .getProduct(x.getProductId()).getPrice()));


        System.out.println("All products are exist");

        order.getProductBundle().addAll(Arrays.stream(productBundle).collect(Collectors.toSet()));
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
