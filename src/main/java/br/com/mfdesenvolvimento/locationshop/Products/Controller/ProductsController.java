package br.com.mfdesenvolvimento.locationshop.Products.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;
import br.com.mfdesenvolvimento.locationshop.Products.Repository.IProductsRepository;
import br.com.mfdesenvolvimento.locationshop.Products.dto.ProductsRecordDto;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*")
public class ProductsController {
  
  @Autowired
  private IProductsRepository productsRepository;

  @GetMapping("/products")
  public List<ProductsModel> getAllProducts() {
      return this.productsRepository.findAll();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<ProductsModel> getProductById(@PathVariable UUID id) {
      Optional<ProductsModel> product = this.productsRepository.findById(id);
      if (product.isPresent()) {
          return ResponseEntity.ok(product.get());
      } else {
          return ResponseEntity.notFound().build();
      }
  }
  
  

  @PostMapping("/products")
  public ResponseEntity<Object> createProduct(@RequestBody ProductsModel productsModel) {
    var product = this.productsRepository.save(productsModel);
    System.out.println(productsModel);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,@RequestBody @Valid ProductsRecordDto productRecordDto) {
      Optional<ProductsModel> productO = productsRepository.findById(id);
      if(productO.isEmpty()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
      }
      var productModel = productO.get();
      BeanUtils.copyProperties(productRecordDto, productModel);
      return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(productModel));
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
