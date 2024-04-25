package org.sharath.app.productmanagementproj.services;


import org.sharath.app.productmanagementproj.dtos.FakeStoreCategoryDto;
import org.sharath.app.productmanagementproj.dtos.FakeStoreProductDto;
import org.sharath.app.productmanagementproj.models.Category;
import org.sharath.app.productmanagementproj.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Product getSingleProductById(Long id) {

//        This restTemplate will take what we've in given url
//        and convert it to a Java object(i.e., DTO here)
//        FakeStoreProductDto fakeStoreProductDto = restTemplate
//                .getForObject(
//                        "https://fakestoreapi.com/products/" + id,
//                        FakeStoreProductDto.class);



//        We're using ResponseEntity instead of Just DTO because it makes way for even Response Data such as header etc
        ResponseEntity<FakeStoreProductDto> response = restTemplate
                .getForEntity(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return  fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);
        return response.toProduct();
    }

    @Override
    public List<Product> getAllProduct() {
//        We're using a simple array here as compared to list because to use in restTemplate
//        and to convert it into .class we're using array
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> listOfProducts = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: response){
            listOfProducts.add(fakeStoreProductDto.toProduct());
        }
        return listOfProducts;
    }


    @Override
    public List<FakeStoreCategoryDto> getAllCategories() {
        String[] stringResponse = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );

        List<FakeStoreCategoryDto> response = new ArrayList<>();
        for (String category : stringResponse) {
            FakeStoreCategoryDto categoryResponseDto = new FakeStoreCategoryDto();
            categoryResponseDto.setTitle(category);
            response.add(categoryResponseDto);
        }
        return response;
    }

    @Override
    public Product deleteProduct(Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,requestEntity,
                FakeStoreProductDto.class,id);

        return response.getBody().toProduct();
    }

    @Override
    public Product updateProduct(Long id, String title, double price, String description, String image, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto,headers);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );

        return response.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProductByCategory(String title) {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/"+ title,
                FakeStoreProductDto[].class);

        List<Product> product = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response){
            product.add(fakeStoreProductDto.toProduct());
        }
        return product;
    }
}
