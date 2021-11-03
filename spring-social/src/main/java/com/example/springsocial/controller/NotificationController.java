package com.example.springsocial.controller;

import com.example.springsocial.model.Inventory;
import com.example.springsocial.payload.LowInventoryRequest;
import com.example.springsocial.payload.NotificationAboutToExpireRequest;
import com.example.springsocial.payload.NotificationExpiredRequest;
import com.example.springsocial.repository.InventoryRepository;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Validated
public class NotificationController {

    private Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    private InventoryRepository inventoryRepository;


    /**
     * API used to fetch inventory items that have expired for a particular restaurant
     * @param notificationExpiredRequest
     * @return JSON object with list of inventory items that have expired
     */
    @PostMapping("/findExpiredInventory")
    public String fetchExpiredInventoryItems(@Valid @RequestBody NotificationExpiredRequest notificationExpiredRequest) {
        List<Inventory> expiredInventoryItems = inventoryRepository.findInventoryByRestaurantIDAndDateExpiredLessThan(notificationExpiredRequest.restaurant_id, new Date());

        String inventoryItemsExpired = new Gson().toJson(expiredInventoryItems);

        return inventoryItemsExpired;

    }

    /**
     * API used to fetch inventory items that are about to expire in the next N days
     * @param notificationAboutToExpireRequest
     * @return JSON object with list of inventory items that are about to expire
     */
    @PostMapping("/findAboutToExpireInventory")
    public String fetchExpiredInventoryItems(@Valid @RequestBody NotificationAboutToExpireRequest notificationAboutToExpireRequest) {

        DateTime dt = new DateTime();
        dt = dt.plusDays(notificationAboutToExpireRequest.expires_in_days);
        Date dateAfterNDays = dt.toDate();

        logger.info("After date: " + dateAfterNDays.toString());

        List<Inventory> aboutToExpireInventoryItems = inventoryRepository.findInventoryByRestaurantIDAndDateExpiredBetween(notificationAboutToExpireRequest.restaurant_id, new Date(), dateAfterNDays);

        String aboutToExpireInventoryItemsJSON = new Gson().toJson(aboutToExpireInventoryItems);

        return aboutToExpireInventoryItemsJSON;

    }

    /**
     * API used to fetch inventory items that have quantity less than N left
     * @param lowInventoryRequest
     * @return JSON object with list of inventory items that have low quantity
     */
    @PostMapping("/findLowQuantityInventoryItems")
    public String fetchLowQuantityInventoryItems(@Valid @RequestBody LowInventoryRequest lowInventoryRequest) {

        List<Inventory> lowQuantityItems = inventoryRepository.findInventoryByRestaurantIDAndBatchQtyLessThanEqual(lowInventoryRequest.restaurant_id, lowInventoryRequest.max_qty);

        String lowQuantityItemsJson = new Gson().toJson(lowQuantityItems);

        return lowQuantityItemsJson;

    }

}
