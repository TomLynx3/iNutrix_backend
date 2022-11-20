package com.rtu.iNutrix.models.DTO.Meals;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecNutrientAmount {

    public RecNutrientAmount(char gender){

        this.A = gender == 'M' ? 0.9: 0.7;
        this.B1 = gender == 'M' ? 1.2 : 1.1;
        this.B2 = gender == 'M' ? 1.3 : 1.1;
        this.PP = gender == 'M' ? 16 : 14;
        this.C = gender == 'M' ? 90 : 75;
        this.Ca  = gender == 'M' ? 1000 : 1000;
        this.P = gender == 'M' ? 700 : 700;
        this.Fe = gender == 'M' ? 8 : 18;
    }

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
