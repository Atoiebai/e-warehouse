package net.sublime.warehouse.service.productbundle;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.ProductBundle;
import net.sublime.warehouse.reposirtory.ProductBundleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductBundleServiceImpl implements ProductBundleService {

    private final ProductBundleRepository productBundleRepository;

    @Override
    public ProductBundle getBundle(long id) {
        return productBundleRepository.getOne(id);
    }

    @Override
    public List<ProductBundle> getAll() {
        return productBundleRepository.findAll();
    }

    @Override
    public void deleteBundle(long id) {
        productBundleRepository.deleteById(id);
    }

    @Override
    public void saveBundle(ProductBundle bundle) {
        productBundleRepository.save(bundle);
    }
}
