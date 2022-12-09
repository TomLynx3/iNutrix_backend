package com.rtu.iNutrix.controller;


import com.rtu.iNutrix.models.BaseResponse;
import com.rtu.iNutrix.models.DTO.Meals.DietDTO;
import com.rtu.iNutrix.models.DTO.Meals.DietRequest;
import com.rtu.iNutrix.service.interfaces.MealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealsController {

    @Autowired
    private MealsService _mealsService;


    @PostMapping("/create-diet")
    public  BaseResponse createDiet(@RequestBody DietRequest req) throws IllegalAccessException {

        BaseResponse res = new BaseResponse();

        res.setResult(_mealsService.createDiet(req.getDays()));
        res.setSuccess(true);

        return res;
    }

    @PostMapping("/save-diet")
    public  BaseResponse saveDiet(@RequestBody DietDTO req)  {

        BaseResponse res = new BaseResponse();

        res.setResult(_mealsService.saveDiet(req));
        res.setSuccess(true);

        return res;
    }

    @GetMapping
    public BaseResponse getCurrentDiet(){
        BaseResponse res = new BaseResponse();


        return  res;
    }
}
