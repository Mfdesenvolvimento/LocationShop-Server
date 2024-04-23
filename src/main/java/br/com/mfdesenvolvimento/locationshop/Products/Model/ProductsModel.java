package br.com.mfdesenvolvimento.locationshop.Products.Model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
//Create the table Products
@Entity(name = "products")
public class ProductsModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")

  //Table's field products

  private UUID id;
  private String nameProduct;
  private String description;
  private String price;
  private String idUser;

	public void setnameProduct(@org.jetbrains.annotations.NotNull String nameProduct) throws Exception {
    if (nameProduct.length() > 50) {
      throw new Exception("The field nameProduct must be at least 3 characters");
    }
    this.nameProduct = nameProduct;
  }
}
