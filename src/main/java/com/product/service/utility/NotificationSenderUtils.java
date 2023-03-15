package com.product.service.utility;


import com.product.service.dto.ProductDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NotificationSenderUtils {

    public static String generateNotificationBody(ProductDTO productDTO){

        String moneyString = properMoneyFormat(productDTO.getPrice());

        return "NEW PRODUCT AVAILABLE! \n" +
                " "+productDTO.getName() + "(" +productDTO.getCategory()+")\n" +
                "PRICE: Php" + moneyString;
    }

    public static String properMoneyFormat(BigDecimal price){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(price);
    }
}
