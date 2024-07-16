package br.com.mfdesenvolvimento.locationshop.Products.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductsRecordDto(@NotBlank String nameProduct, @NotBlank String description, @NotBlank String price) {
}
