package net.sublime.warehouse.service.product;

import net.sublime.warehouse.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllAvailableProducts();

    List<Long> getAllId();

    Product getProduct(long id);

    void archiveProduct(long id);

    void archiveProduct(Product product);

    void addProduct(Product product);

}
