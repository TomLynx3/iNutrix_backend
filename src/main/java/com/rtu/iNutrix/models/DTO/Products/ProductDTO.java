package com.rtu.iNutrix.models.DTO.Products;


import com.rtu.iNutrix.models.entities.Product;
import com.rtu.iNutrix.models.entities.ProductCustom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    
    public ProductDTO(Product entity){
        this.productGroup = new ProductGroupDTO(entity.getProductGroup());
        this.name = entity.getName();
        this.protein = entity.getProtein();
        this.fat = entity.getFat();
        this.carbohydrates = entity.getCarbohydrates();
        this.kJ = entity.getKJ();
        this.kcal = entity.getKcal();
        this.A = entity.getA();
        this.B1 = entity.getB1();
        this.B2 = entity.getB2();
        this.PP = entity.getPP();
        this.C = entity.getC();
        this.Ca = entity.getCa();
        this.P = entity.getP();
        this.Fe = entity.getFe();

    }

    public ProductDTO(ProductCustom entity){
        this.productGroup = new ProductGroupDTO(entity.getProductGroup());
        this.name = entity.getName();
        this.protein = entity.getProtein();
        this.fat = entity.getFat();
        this.carbohydrates = entity.getCarbohydrates();
        this.kJ = entity.getKJ();
        this.kcal = entity.getKcal();
        this.A = entity.getA();
        this.B1 = entity.getB1();
        this.B2 = entity.getB2();
        this.PP = entity.getPP();
        this.C = entity.getC();
        this.Ca = entity.getCa();
        this.P = entity.getP();
        this.Fe = entity.getFe();

    }


    private ProductGroupDTO productGroup;

    private String name;

    private double protein;

    private double fat;

    private double carbohydrates;

    private double kJ;

    private double kcal;

    private double A;

    private double B1;

    private double B2;

    private double PP;

    private double C;

    private double Ca;

    private double P;

    private double Fe;
}
