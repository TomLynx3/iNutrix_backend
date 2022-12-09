package com.rtu.iNutrix.controller;


import com.rtu.iNutrix.models.BaseResponse;
import com.rtu.iNutrix.models.DTO.Meals.DietRequest;
import com.rtu.iNutrix.service.interfaces.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diets")
public class DietController {


    private final DietService _dietService;

    @Autowired
    public DietController(DietService dietService){
        this._dietService = dietService;
    }

    @GetMapping("/active")
    public BaseResponse getActiveDiet()  {

        BaseResponse res = new BaseResponse();

        res.setResult(_dietService.getCurrentDietProgress());
        res.setSuccess(true);

        return res;
    }
}
