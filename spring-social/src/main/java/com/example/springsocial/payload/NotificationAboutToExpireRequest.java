package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class NotificationAboutToExpireRequest {
    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setExpires_in_days(int expires_in_days) {
        this.expires_in_days = expires_in_days;
    }

    @NotBlank
    public String restaurant_id;

    @NotNull
    public int expires_in_days;



}
