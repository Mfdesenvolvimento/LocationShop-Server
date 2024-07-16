package br.com.mfdesenvolvimento.locationshop.Products.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;

@Repository
public interface IProductsRepository extends JpaRepository<ProductsModel, UUID> {
    @SuppressWarnings("null")
    List<ProductsModel> findAll();


    @SuppressWarnings("null")
    Optional<ProductsModel> findById(UUID id);

}
