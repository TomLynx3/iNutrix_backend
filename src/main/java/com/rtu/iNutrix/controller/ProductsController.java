package com.rtu.iNutrix.controller;


import com.rtu.iNutrix.models.BaseResponse;
import com.rtu.iNutrix.service.interfaces.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsService _productsService;

    @GetMapping("")
    public BaseResponse getProducts(){
        BaseResponse res = new BaseResponse();

        res.setResult(_productsService.getAllProducts());
        res.setSuccess(true);
        return res;
    }
}
