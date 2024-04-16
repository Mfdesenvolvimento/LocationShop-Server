package br.com.mfdesenvolvimento.locationshop.Products;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductsRepository extends JpaRepository<ProductsModel, UUID> {
  Optional<ProductsModel> findById(UUID id);

}
