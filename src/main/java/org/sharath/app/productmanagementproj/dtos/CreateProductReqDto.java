package org.sharath.app.productmanagementproj.dtos;

import lombok.Getter;
import lombok.Setter;
import org.sharath.app.productmanagementproj.models.Category;

//We've this DTO so that our front end can talk with our backend
//What is 3rd party API make changes tomorrow and then our front end can break
//So we've created our own version to be used in Controller
@Getter
@Setter
public class CreateProductReqDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

}
