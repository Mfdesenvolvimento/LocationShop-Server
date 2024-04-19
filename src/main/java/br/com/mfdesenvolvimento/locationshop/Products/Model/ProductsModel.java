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

  //Table's field profucts

  private UUID id;
  private String nameProduct;
  private String description;
  private String price;
  private UUID idUser;

	public void setnameProduct(String nameProduct) throws Exception {
    if (nameProduct.length() > 50) {
      throw new Exception("The field nameProduct must be at least 3 characters");
    }
    this.nameProduct = nameProduct;
  }
}
