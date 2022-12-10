package com.rtu.iNutrix.service.interfaces;

import com.rtu.iNutrix.models.DTO.Products.BannedProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductBase;
import com.rtu.iNutrix.models.DTO.Products.ProductDTO;
import com.rtu.iNutrix.models.DTO.Products.ProductGroupDTO;
import com.rtu.iNutrix.models.entities.DietProduct;
import com.rtu.iNutrix.utilities.errors.ProductErrorCodes;

import java.util.List;
import java.util.UUID;

public interface ProductsService {

    List<ProductDTO> getAllProducts();
    void banProducts(List<BannedProductDTO> products);
    List<BannedProductDTO> getBannedProducts() throws ProductErrorCodes.SystemProductNotFoundException;
    void removeFromBanList(List<UUID> ids);

    UUID addCustomProduct(ProductDTO productDTO);
    void deleteCustomProducts(List<UUID> productIds);
    void editCustomProduct(ProductDTO productDTO);
    List<ProductGroupDTO> getProductGroups();
    List<ProductBase> getProductBases(List<DietProduct> dietProducts);
}
