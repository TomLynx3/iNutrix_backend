package com.rtu.iNutrix.controller;


import com.rtu.iNutrix.models.BaseResponse;
import com.rtu.iNutrix.models.DTO.Meals.DietDay;
import com.rtu.iNutrix.models.DTO.Meals.DietRequest;
import com.rtu.iNutrix.service.interfaces.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealsController {

    @Autowired
    private MealsService _mealsService;


    @GetMapping("")
    public BaseResponse getProducts() throws IllegalAccessException, InstantiationException {
        BaseResponse res = new BaseResponse();


        res.setResult(_mealsService.getDiet(7));

        res.setSuccess(true);
        return res;
    }

    @PostMapping("/get-diet")
    public  BaseResponse getDiet(@RequestBody DietRequest req) throws IllegalAccessException {

        BaseResponse res = new BaseResponse();

        res.setResult(_mealsService.getDiet(req.getDays()));
        res.setSuccess(true);

        return res;
    }
}
