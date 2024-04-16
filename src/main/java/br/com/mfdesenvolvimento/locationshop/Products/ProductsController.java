package br.com.mfdesenvolvimento.locationshop.Products;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/products")
public class ProductsController {
  
  @Autowired
  private IProductsRepository productsRepository;
  private UUID id;

  @GetMapping("/")
  public Optional<ProductsModel> list(HttpServletRequest request) {
    var product = this.productsRepository.findById((UUID) id);
    return product;
  }
  
}
