package com.rtu.iNutrix.models.DTO.Products;


import com.rtu.iNutrix.models.entities.BannedProduct;
import com.rtu.iNutrix.models.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BannedProductDTO {



    public BannedProductDTO(Product systemProduct){
        this.id = systemProduct.getId();
        this.name = systemProduct.getName();
        this.productGroup = new ProductGroupDTO(systemProduct.getProductGroup());
        this.isCustom = false;
    }


    @NotNull
    private UUID id;


    @NotNull
    private String name;


    @NotNull
    private ProductGroupDTO productGroup;

    @NotNull
    private boolean isCustom;

}
