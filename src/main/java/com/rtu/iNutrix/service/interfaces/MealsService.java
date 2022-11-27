package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Meals.DailyProduct;
import com.rtu.iNutrix.models.DTO.Meals.DietDay;
import com.rtu.iNutrix.models.DTO.Meals.DietDayMetaData;
import com.rtu.iNutrix.models.DTO.Meals.MealDTO;

import java.util.List;

public interface MealsService {

    // DietDayMetaData getMealPlan() throws IllegalAccessException, InstantiationException;
     DietDayMetaData getDietDayMetadata() throws IllegalAccessException;

     List<DietDay> getDiet(int days) throws IllegalAccessException;

     List<MealDTO> getMealsForDay(List<DailyProduct> products);
}
