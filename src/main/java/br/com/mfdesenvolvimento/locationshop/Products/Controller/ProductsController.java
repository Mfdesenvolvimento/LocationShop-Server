package br.com.mfdesenvolvimento.locationshop.Products.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;
import br.com.mfdesenvolvimento.locationshop.Products.Repository.IProductsRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ProductsController {
  
  @Autowired
  private IProductsRepository productsRepository;

  @GetMapping("/products")
  public List<ProductsModel> getAllProducts(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var product = this.productsRepository.findByIdUser((String) idUser);
    return product;
  }

  @PostMapping("/products")
  public ResponseEntity<Object> createProduct(@RequestBody ProductsModel productsModel) {
    String idUser = productsModel.getIdUser();
    var product = this.productsRepository.save(productsModel);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }
}
