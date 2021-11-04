package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;

public class NotificationExpiredRequest {

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @NotBlank
    public String restaurant_id;


}
