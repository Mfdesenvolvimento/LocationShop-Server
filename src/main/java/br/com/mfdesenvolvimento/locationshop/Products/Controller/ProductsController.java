package br.com.mfdesenvolvimento.locationshop.Products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;
import br.com.mfdesenvolvimento.locationshop.Products.Repository.IProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
public class ProductsController {
  
  @Autowired
  private IProductsRepository productsRepository;

  @GetMapping("/products")
  public List<ProductsModel> getAllProducts() {
      return this.productsRepository.findAll();
  }

  @PostMapping("/products")
  public ResponseEntity<Object> createProduct(@RequestBody ProductsModel productsModel) {
    var product = this.productsRepository.save(productsModel);
    System.out.println(productsModel);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }
}
