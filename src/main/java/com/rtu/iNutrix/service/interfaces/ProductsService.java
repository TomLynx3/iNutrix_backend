package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.entities.Product;

import java.util.List;

public interface ProductsService {


    List<ProductDTO> getAllProducts();
}
