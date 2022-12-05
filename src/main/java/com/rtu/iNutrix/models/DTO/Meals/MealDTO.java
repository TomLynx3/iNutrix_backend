package com.rtu.iNutrix.models.DTO.Meals;

import java.util.List;

public class MealDTO {
    public MealDTO(MealType mealType, List<DailyProduct> dailyProductList) {
        this.mealType = mealType;
        this.products = dailyProductList;
    }
    private MealType mealType;

    private List<DailyProduct> products;
}
