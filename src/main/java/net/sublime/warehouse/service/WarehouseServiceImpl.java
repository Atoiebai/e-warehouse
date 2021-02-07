package net.sublime.warehouse.service;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Product;
import net.sublime.warehouse.reposirtory.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public List<Product> getAllProducts() {
        return warehouseRepository.findAll();
    }

    @Override
    public List<Product> getAllAvailableProducts() {
        return warehouseRepository.getAllAvailable();
    }

    @Override
    public Product getProduct(long id) {
        return warehouseRepository.getOne(id);
    }

    @Override
    public void archiveProduct(long id) {
        warehouseRepository.getOne(id).setArchived(true);
    }

    @Override
    public void archiveProduct(Product product) {
        product.setArchived(true);
    }

    @Override
    public void addProduct(Product product) {
        warehouseRepository.save(product);
    }
}
