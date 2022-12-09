package com.rtu.iNutrix.models.DTO.Meals;


import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductGroupDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DailyProduct {


    private UUID productId;

    private String name;

    private double amount;

    private ProductGroupDTO productGroup;

    private boolean isCustomProduct;

    private ProductDTO productDTO;


    public DailyProduct(ProductDTO product, double amount){
        this.productDTO = product;
        this.productId = product.getId();
        this.name = product.getName();
        this.amount = amount;
        this.productGroup = product.getProductGroup();
    }
    public DailyProduct(DailyProduct dailyProduct) { // makes a shallow copy
        this.productId = dailyProduct.getProductId();
        this.name = dailyProduct.getName();
        this.amount = dailyProduct.getAmount();
        this.productGroup = dailyProduct.getProductGroup();
        this.isCustomProduct = dailyProduct.isCustomProduct();
        this.productDTO = dailyProduct.getProductDTO();
    }
}
