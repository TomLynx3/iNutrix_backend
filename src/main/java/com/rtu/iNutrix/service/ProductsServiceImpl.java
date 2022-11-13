package com.rtu.iNutrix.service;

import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.entities.Product;
import com.rtu.iNutrix.repositories.ProductRepository;
import com.rtu.iNutrix.service.interfaces.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository _productsRepo;

    @Override
    public List<ProductDTO> getAllProducts() {
        return _productsRepo.findAll().stream().map(x-> new ProductDTO(x)).collect(Collectors.toList());
    }
}
