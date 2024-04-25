package org.sharath.app.productmanagementproj.dtos;

import lombok.Getter;
import lombok.Setter;
import org.sharath.app.productmanagementproj.models.Category;
import org.sharath.app.productmanagementproj.models.Product;

//This DTO is so that backend can talk with 3rd party API
@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;


//    We're using this toProduct method instead of creating Product and converting everywhere
//    Because it reduces the no. of lines of code we need to make changes to

//    Instead of this we could've done is Create Product class and set everything in everyplaces but lines of code increases
    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(getPrice());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());

//        We can't set it like that because of type mismatch
//        product.setCategory(fakeStoreProductDto.getCategory());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }
}
