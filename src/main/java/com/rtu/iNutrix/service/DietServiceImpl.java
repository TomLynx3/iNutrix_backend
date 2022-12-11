package com.rtu.iNutrix.service;

import com.rtu.iNutrix.models.DTO.Diet.DietProgressDTO;
import com.rtu.iNutrix.models.DTO.Diet.DietProgressDay;
import com.rtu.iNutrix.models.DTO.Diet.DietProgressProductDTO;
import com.rtu.iNutrix.models.DTO.Meals.MealType;
import com.rtu.iNutrix.models.DTO.Products.ProductBase;
import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductInfoDTO;
import com.rtu.iNutrix.models.entities.DietHistory;
import com.rtu.iNutrix.models.entities.DietProduct;
import com.rtu.iNutrix.models.entities.DietProgress;
import com.rtu.iNutrix.repositories.DietHistoryRepository;
import com.rtu.iNutrix.repositories.DietProductRepository;
import com.rtu.iNutrix.repositories.DietProgressRepository;
import com.rtu.iNutrix.service.interfaces.DietService;
import com.rtu.iNutrix.service.interfaces.ProductsService;
import com.rtu.iNutrix.service.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DietServiceImpl implements DietService {

    private final UserDataService _userDataService;

    private final DietHistoryRepository _dietHistoryRepo;

    private  final DietProgressRepository _dietProgressRepo;

    private final DietProductRepository _dietProductRepo;

    private  final ProductsService _productService;


    @Autowired
    public DietServiceImpl(UserDataService userDataService, DietHistoryRepository dietHistoryRepository,
                           DietProgressRepository dietProgressRepository, DietProductRepository dietProductRepository,
                           ProductsService productsService){
        this._userDataService = userDataService;
        this._dietHistoryRepo = dietHistoryRepository;
        this._dietProgressRepo = dietProgressRepository;
        this._dietProductRepo = dietProductRepository;
        this._productService = productsService;
    }

    @Override
    public DietProgressDTO getCurrentDietProgress() {

        Optional<DietHistory> currentDietOp = _dietHistoryRepo.getUserCurrentDiet(_userDataService.getUserID());

        if(currentDietOp.isEmpty()){
            return null;
        }

        DietHistory currentDiet = currentDietOp.get();

        DietProgressDTO progress = new DietProgressDTO();

        progress.setId(currentDiet.getDiet().getId());
        progress.setDietGoal(currentDiet.getDiet().getDietGoal());
        progress.setKcal(currentDiet.getDiet().getKcal());

        List<DietProgress> currentDietProgress = _dietProgressRepo.getDietProgress(progress.getId());

        List<DietProduct> currentDietProducts = _dietProductRepo.getDietProducts(progress.getId());

        Map<ZonedDateTime,List<DietProduct>> groupedProducts = currentDietProducts.stream().collect(Collectors.groupingBy(x->x.getDate()));

        List<DietProgressDay> progressDays = new ArrayList<>();


        for(Map.Entry<ZonedDateTime,List<DietProduct>> entry : groupedProducts.entrySet()){
            DietProgressDay progressDay = new DietProgressDay();

            progressDay.setDate(entry.getKey());

            List<DietProgressProductDTO> progressProductDTOList = new ArrayList<>();

            List<ProductBase> productBases = _productService.getProductBases(entry.getValue());


            for(ProductBase base : productBases){

                Optional<DietProgress> dateProgress = currentDietProgress.stream().filter(x->x.getDate().truncatedTo(ChronoUnit.DAYS).equals(entry.getKey().truncatedTo(ChronoUnit.DAYS))).findFirst();

                boolean consumed = false;

                if(dateProgress.isPresent()){
                    DietProgress value = dateProgress.get();

                    consumed = value.getProducts().stream().anyMatch(x-> UUID.fromString(x).equals(base.getProductId()));

                }
                DietProgressProductDTO dto = new DietProgressProductDTO(base,consumed);
                _setDietProductAmountAndMealType(currentDietProducts,base.getProductId(),dto);

                progressProductDTOList.add(dto);

            }


           progressDay.setProducts(progressProductDTOList);
           progressDays.add(progressDay);

        }

        
        Collections.sort(progressDays, Comparator.comparing(DietProgressDay::getDate));
        progress.setDays(progressDays);

        return progress;
    }


    private void _setDietProductAmountAndMealType(List<DietProduct> products, UUID productId,DietProgressProductDTO dto){
        Optional<DietProduct> dietProduct = products.stream().filter(x->x.getProductId().equals(productId)).findFirst();

        if(dietProduct.isPresent()){
            DietProduct value = dietProduct.get();

            dto.setMealType(value.getMealType());
            dto.setAmount(value.getAmount());
        }

    }


}
