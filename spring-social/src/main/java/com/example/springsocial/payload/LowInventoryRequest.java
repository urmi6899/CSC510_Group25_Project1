package com.example.springsocial.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LowInventoryRequest {

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setMax_qty(int max_qty) {
        this.max_qty = max_qty;
    }

    @NotBlank
    public String restaurant_id;

    @NotNull
    @Min(1)
    public int max_qty;


}
