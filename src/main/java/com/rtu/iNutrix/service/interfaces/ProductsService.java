package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Products.BannedProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.utilities.errors.ProductErrorCodes;

import java.util.List;
import java.util.UUID;

public interface ProductsService {

    List<ProductDTO> getAllProducts();
    void banProducts(List<BannedProductDTO> products);
    List<BannedProductDTO> getBannedProducts() throws ProductErrorCodes.SystemProductNotFoundException;
    void removeFromBanList(List<UUID> ids);
}
