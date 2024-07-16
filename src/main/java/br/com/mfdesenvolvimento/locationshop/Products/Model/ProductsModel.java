package br.com.mfdesenvolvimento.locationshop.Products.Model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;


@Data
//Create the table Products
@Entity
@Table(name = "products")
public class ProductsModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")

  //Table's field products

  private UUID id;
  private String nameProduct;
  private String description;
  private String price;

  //Come of the mobile
  private String idUser;
  private String Username;


}
