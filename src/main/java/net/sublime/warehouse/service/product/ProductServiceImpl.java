package net.sublime.warehouse.service.product;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Product;
import net.sublime.warehouse.reposirtory.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(int number, int size) {
        Pageable pageable = PageRequest.of(number , size);
        Page<Product> productsResult = productRepository.findAll(pageable);

        return productsResult.toList();
    }

    @Override
    public List<Product> getAllAvailableProducts() {
        return productRepository.getAllAvailable();
    }

    @Override
    public List<Long> getAllId() {
        return productRepository.findAll().stream().map(Product::getId).collect(Collectors.toList());
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public void archiveProduct(long id) {
        productRepository.getOne(id).setArchived(true);
    }

    @Override
    public void archiveProduct(Product product) {
        product.setArchived(true);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
