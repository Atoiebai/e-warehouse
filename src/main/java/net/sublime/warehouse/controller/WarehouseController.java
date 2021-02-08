package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Product;
import net.sublime.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) boolean all) {
        List<Product> products;
        if (all) products = warehouseService.getAllProducts();
        else products = warehouseService.getAllAvailableProducts();
        if (products.isEmpty()) throw new IllegalStateException("Products list is empty");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = warehouseService.getProduct(id);
        if (product == null) throw new NullPointerException("No product found with such id");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        warehouseService.archiveProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        warehouseService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/archive/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> archiveProduct(@PathVariable long id) {
        Product product = warehouseService.getProduct(id);
        product.setArchived(true);
        warehouseService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/archive/", method = RequestMethod.PUT)
    public ResponseEntity<Product> archiveProduct(@RequestBody Product product) {
        product.setArchived(true);
        warehouseService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);


    }



}
