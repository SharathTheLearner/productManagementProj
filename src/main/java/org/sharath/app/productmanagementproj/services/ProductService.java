package org.sharath.app.productmanagementproj.services;


//We're using Interface and letting class handle impl
//Think of PhonePay, They changed from YesBank to other bank because they use interface

//We're letting controller use this interface and not class directly

import org.sharath.app.productmanagementproj.dtos.FakeStoreCategoryDto;
import org.sharath.app.productmanagementproj.models.Category;
import org.sharath.app.productmanagementproj.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface ProductService {
    public Product getSingleProductById(Long id);

//    We're making createProduct independent of any DTOs/Models and making service generic
//    by passing in every data as just values as there are not a lot of values
//    If we had multiple params(say 10-15 fields) then we'd create a new DTO and pass it in
    public Product createProduct(String title, String description,
                                 String image, String category, double price);


    public List<Product> getAllProduct();

//    public ResponseEntity<Product> deleteProductById(Long id);

    public List<FakeStoreCategoryDto> getAllCategories();

    public Product deleteProduct(Long id);



    public Product updateProduct(Long id, String title, double price, String description,
                                 String image, String category);

    public List<Product> getAllProductByCategory(String title);
}
