package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class NotificationAboutToExpireRequest {
    @NotBlank
    public String restaurant_id;

    @NotNull
    public int expires_in_days;

}
