package net.sublime.warehouse.reposirtory;

import net.sublime.warehouse.model.ProductBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBundleRepository extends JpaRepository<ProductBundle, Long> {
}
