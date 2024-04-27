package br.com.mfdesenvolvimento.locationshop.Products.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;

public interface IProductsRepository extends JpaRepository<ProductsModel, String> {
//  List<ProductsModel> findAll(String idUser);
}
