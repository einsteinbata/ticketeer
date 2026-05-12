package com.ticketeer.ticketeer_purchases_producer.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MessageUtil {

    private static final String PURCHASE_PREFIX = "PRCHS_";

    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateTimeStr = df.format(new Date());

        return PURCHASE_PREFIX + uuid + "_" + dateTimeStr;
    }

}
