package br.com.mfdesenvolvimento.locationshop.Products.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;

public interface IProductsRepository extends JpaRepository<ProductsModel, UUID> {
  List<ProductsModel> findByIdUser(UUID idUser);

  ProductsModel findByIdAndIdUser(UUID id, UUID idUser);
}
