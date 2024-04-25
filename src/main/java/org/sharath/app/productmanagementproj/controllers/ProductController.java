package org.sharath.app.productmanagementproj.controllers;

import org.sharath.app.productmanagementproj.dtos.CreateProductReqDto;
import org.sharath.app.productmanagementproj.dtos.FakeStoreCategoryDto;
import org.sharath.app.productmanagementproj.models.Product;
import org.sharath.app.productmanagementproj.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

//    This is field injection
    @Autowired
    ProductService productService;



//    This is constructor injection, preferred way, but I won't be using it
//    Just because it is a tough way to code
//    public ProductController(ProductService productService){
//        this.productService = productService;
//    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductReqDto productReqDto){
        return productService.createProduct(
                productReqDto.getTitle(),
                productReqDto.getDescription(),
                productReqDto.getImage(),
                productReqDto.getCategory(),
                productReqDto.getPrice()
        );
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

//    While using @PathVariable, I'm not passing any param to annotation
//    Because name in url and variable both are same
//    If both we different we need to use @PathVariable("nameUsedInURL")
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getSingleProductById(id);
    }


    @GetMapping("/products/categories")
    public List<FakeStoreCategoryDto> getAllCategories(){
        return productService.getAllCategories();
    }


    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductReqDto productRequestDto){
        return productService.updateProduct(id,
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory());
    }

    @GetMapping("/products/category/{title}")
    public List<Product> getAllProductByCategory(@PathVariable("title") String title){
        return productService.getAllProductByCategory(title);
    }
}
