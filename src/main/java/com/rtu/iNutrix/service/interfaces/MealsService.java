package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Meals.*;

import java.util.List;
import java.util.UUID;

public interface MealsService {

    // DietDayMetaData getMealPlan() throws IllegalAccessException, InstantiationException;
     DietDayMetaData getDietDayMetadata() throws IllegalAccessException;

     DietDTO createDiet(int days) throws IllegalAccessException;

     List<MealDTO> getMealsForDay(List<DailyProduct> products);

     UUID saveDiet(DietDTO diet);

     DietDTO getCurrentDiet();
}
