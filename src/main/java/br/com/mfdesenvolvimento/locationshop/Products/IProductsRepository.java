package br.com.mfdesenvolvimento.locationshop.Products;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<ProductsModel, UUID>{
  ProductsModel findByIdProductsModel(UUID id);
}
