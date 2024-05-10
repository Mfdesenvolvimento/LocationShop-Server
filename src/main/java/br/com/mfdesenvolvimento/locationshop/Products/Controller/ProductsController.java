package br.com.mfdesenvolvimento.locationshop.Products.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;
import br.com.mfdesenvolvimento.locationshop.Products.Repository.IProductsRepository;

import java.util.List;
import java.util.UUID;


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

  @PutMapping("/products/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody ProductsModel updatedProduct) {
    ProductsModel products = (ProductsModel) this.productsRepository.findById(id).orElse(null);

    if (products == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Product not found");
    }

    this.productsRepository.save(products);

    return ResponseEntity.ok().body("Product updated successfully");
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    ProductsModel product = (ProductsModel) this.productsRepository.findById(id).orElse(null);

    if (product == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Product not found");
    }

    this.productsRepository.delete(product);

    return ResponseEntity.ok().body("Product deleted successfully");
  }
}
