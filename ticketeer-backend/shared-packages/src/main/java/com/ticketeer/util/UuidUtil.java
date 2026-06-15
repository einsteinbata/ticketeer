package com.ticketeer.util;

import com.ticketeer.constants.Operation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UuidUtil {

    public static String generateUUID(Operation operation){
        UUID uuid = UUID.randomUUID();

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateTimeStr = df.format(new Date());

        return operation.getPrefix() + uuid + "_" + dateTimeStr;
    }

}
