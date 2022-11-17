package com.rtu.iNutrix.service;

import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.entities.Product;
import com.rtu.iNutrix.repositories.ProductCustomRepository;
import com.rtu.iNutrix.repositories.ProductRepository;
import com.rtu.iNutrix.service.interfaces.ProductsService;
import com.rtu.iNutrix.service.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private UserDataService _userDataService;
    @Autowired
    private ProductRepository _productsRepo;
    @Autowired
    private ProductCustomRepository _productsCustomRepo;

    @Override
    public List<ProductDTO> getAllProducts() {
        String userID = _userDataService.getUserID();
        List<ProductDTO> commonProducts = _productsRepo.findAll().stream().map(x-> new ProductDTO(x)).collect(Collectors.toList());
        List<ProductDTO> personalizedProducts = _productsCustomRepo.findByUser_id(userID).stream().map(x-> new ProductDTO(x)).collect(Collectors.toList());
        return Stream.concat(commonProducts.stream(), personalizedProducts.stream()).collect(Collectors.toList());
    }
}
