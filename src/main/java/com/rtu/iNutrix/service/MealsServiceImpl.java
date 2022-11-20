package com.rtu.iNutrix.service;

import com.rtu.iNutrix.models.DTO.Meals.RecNutrientAmount;
import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.DTO.UserDataDTO;
import com.rtu.iNutrix.service.interfaces.MealsService;
import com.rtu.iNutrix.service.interfaces.ProductsService;
import com.rtu.iNutrix.service.interfaces.UserDataService;
import com.rtu.iNutrix.utilities.constants.LookUpConstants;
import org.aspectj.weaver.ast.Var;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class MealsServiceImpl implements MealsService {

    @Autowired
    private UserDataService _userDataService;

    @Autowired
    private ProductsService _productService;

    public Optimisation.Result getMealPlan(){
        ExpressionsBasedModel model = new ExpressionsBasedModel();
        RecNutrientAmount nutrients  = _getNutrientAmount();

        List<ProductDTO> products = _productService.getAllProducts();

        HashMap<ProductDTO, Variable> t = new HashMap<>();

       // List<Variable> variables = new ArrayList<>();

        int min = 1;
        int max = 10;

        for(ProductDTO product : products){
          // variables.add(model.addVariable(product.getName()).weight(1));
           t.put(product,model.addVariable(product.getName()).weight(1).lower(0));

        }
//(int)Math.floor(Math.random()*(max-min+1)+min)).lower(0)

        Expression carbs =  model.addExpression("Carbs").lower(nutrients.getCarbohydrates());
        Expression proteins =  model.addExpression("Proteins").lower(nutrients.getProtein());
        Expression fat = model.addExpression("Fat").lower(nutrients.getFat());
        Expression kCal = model.addExpression("kCal").lower(nutrients.getKcal());
        Expression A = model.addExpression("A").lower(nutrients.getA());
        Expression B1 = model.addExpression("B1").lower(nutrients.getB1());
        Expression B2 = model.addExpression("B2").lower(nutrients.getB2());
        Expression PP = model.addExpression("PP").lower(nutrients.getPP());
        Expression C = model.addExpression("C").lower(nutrients.getC());
        Expression Ca = model.addExpression("Ca").lower(nutrients.getCa());
        Expression P = model.addExpression("P").lower(nutrients.getP());
        Expression Fe= model.addExpression("Fe").lower(nutrients.getFe());

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            carbs.set(entry.getValue(),entry.getKey().getCarbohydrates());
        }
        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            proteins.set(entry.getValue(),entry.getKey().getProtein());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            fat.set(entry.getValue(),entry.getKey().getFat());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            kCal.set(entry.getValue(),entry.getKey().getKcal());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            A.set(entry.getValue(),entry.getKey().getA());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            B2.set(entry.getValue(),entry.getKey().getB2());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            B1.set(entry.getValue(),entry.getKey().getB1());
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            PP.set(entry.getValue(),entry.getKey().getPP());;
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            C.set(entry.getValue(),entry.getKey().getC());;
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            Ca.set(entry.getValue(),entry.getKey().getCa());;
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            P.set(entry.getValue(),entry.getKey().getP());;
        }

        for (Map.Entry<ProductDTO, Variable> entry : t.entrySet()) {
            Fe.set(entry.getValue(),entry.getKey().getFe());;
        }

        return model.minimise();

    }


    @Override
    public RecNutrientAmount getNutrientsNeeded() {
        return _getNutrientAmount();
    }


    private RecNutrientAmount _getNutrientAmount(){

        RecNutrientAmount nutrients = new RecNutrientAmount(_userDataService.getUserData().getGender());

        double caloriesNeededPerDay = _getAMRrate() * _getMBR();

        nutrients.setKcal(caloriesNeededPerDay);
        _setMacroNutrientNeeded(nutrients,caloriesNeededPerDay);

        return nutrients;
    }

    private void _setMacroNutrientNeeded(RecNutrientAmount nutrients,double calories){
        //Carbs 4 calories per gram
        //Protein 4 calories per gram
        // Fat 9 calories per gram

        nutrients.setCarbohydrates((calories * 0.5)/4);
        nutrients.setProtein((calories * 0.35)/4);
        nutrients.setFat((calories * 0.15)/9);
    }


    private double _getAMRrate(){

        UUID userActivityLevel = _userDataService.getUserData().getActivityLevel();

        if(LookUpConstants.LookUp_ActivityLevel_Sedentary.equals(userActivityLevel)){
            return 1.2;
        }else if(LookUpConstants.LookUp_ActivityLevel_LightlyActive.equals(userActivityLevel)){
            return 1.375;
        }else if(LookUpConstants.LookUp_ActivityLevel_ModeratelyActive.equals(userActivityLevel)){
            return 1.55;
        }else if(LookUpConstants.LookUp_ActivityLevel_Active.equals(userActivityLevel)){
            return 1.725;
        }else{
            return 1.9;
        }
    }

    private double _getMBR(){
        UserDataDTO userData = _userDataService.getUserData();

        if(userData.getGender() == 'M'){
            return 66.47+ 13.75 * userData.getBodyWeight()+5.003 * userData.getHeight() - 6.755 * userData.getAge();
        }else{
            return 655.1+ 9.563 * userData.getBodyWeight()+1.85 * userData.getHeight() - 4.676 * userData.getAge();
        }
    }


}
