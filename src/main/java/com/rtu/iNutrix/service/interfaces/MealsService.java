package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Meals.RecNutrientAmount;
import org.ojalgo.optimisation.Optimisation;

public interface MealsService {

     RecNutrientAmount getNutrientsNeeded();
     Optimisation.Result getMealPlan();
}
