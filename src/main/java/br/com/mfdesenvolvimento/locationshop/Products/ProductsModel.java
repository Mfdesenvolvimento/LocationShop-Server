package br.com.mfdesenvolvimento.locationshop.Products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
//Create the table Products
@Entity(name = "Products")
public class ProductsModel {
  

  @Id
  @GeneratedValue(generator = "UUID")
  @Column(length = 50)

  //Table's field profucts

  private UUID id;
  private String nameProduct;
  private String description;
  private String price;

  public void setnameProduct(String nameProduct) throws Exception {
    if (nameProduct.length() > 3) {
      throw new Exception("The field nameProduct must be at least 3 characters");
    }
  }
}
