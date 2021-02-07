package net.sublime.warehouse.service;

import net.sublime.warehouse.model.Product;

import java.util.List;

public interface WarehouseService {

    List<Product> getAllProducts();

    List<Product> getAllAvailableProducts();

    Product getProduct(long id);

    void archiveProduct(long id);

    void archiveProduct(Product product);

    void addProduct(Product product);

}
