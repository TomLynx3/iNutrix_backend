package com.rtu.iNutrix.models.DTO.Meals;


import com.rtu.iNutrix.models.DTO.Products.ProductBase;
import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductGroupDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class DailyProduct extends ProductBase {


    private ProductDTO productDTO;


    public DailyProduct(ProductDTO product, double amount){
        super(product.getId(),product.getName(),amount,product.getProductGroup(),product.isCustom());
        this.productDTO = product;
    }
}
