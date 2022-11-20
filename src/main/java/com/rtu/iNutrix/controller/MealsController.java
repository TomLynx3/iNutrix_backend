package com.rtu.iNutrix.controller;


import com.rtu.iNutrix.models.BaseResponse;
import com.rtu.iNutrix.service.interfaces.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meals")
public class MealsController {

    @Autowired
    private MealsService _mealsService;


    @GetMapping("")
    public BaseResponse getProducts(){
        BaseResponse res = new BaseResponse();

        res.setResult(_mealsService.getMealPlan());

        res.setSuccess(true);
        return res;
    }
}
