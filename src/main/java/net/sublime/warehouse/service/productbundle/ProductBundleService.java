package net.sublime.warehouse.service.productbundle;

import net.sublime.warehouse.model.ProductBundle;

import java.util.List;

public interface ProductBundleService {

    ProductBundle getBundle(long id);

    List<ProductBundle> getAll();

    void deleteBundle(long id);

    void saveBundle(ProductBundle bundle);

}
