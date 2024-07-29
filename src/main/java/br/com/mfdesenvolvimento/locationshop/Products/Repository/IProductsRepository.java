package br.com.mfdesenvolvimento.locationshop.Products.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductsRepository extends JpaRepository<ProductsModel, String> {
    List<ProductsModel> findAll();

    Optional<Object> findById(UUID id);

}
