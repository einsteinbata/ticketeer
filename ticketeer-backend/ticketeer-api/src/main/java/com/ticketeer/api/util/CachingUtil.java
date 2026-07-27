package com.ticketeer.api.util;

import com.ticketeer.api.constraints.EventSearchConstraints;
import com.ticketeer.constants.CachingKey;
import com.ticketeer.constants.EventStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CachingUtil {

    public static List<CachingKey> getEventSearchCachingKeys(EventSearchConstraints constraints){

        List<CachingKey> cachingKeys = new ArrayList<>();

        if(constraints.isFeatured())
            cachingKeys.add(CachingKey.FEATURED_EVENT);

        if(Objects.nonNull(constraints.getCity()) && !constraints.getCity().isEmpty())
            cachingKeys.add(CachingKey.CITY);

        if(Objects.nonNull(constraints.getVenueId()))
            cachingKeys.add(CachingKey.VENUE);

        if(Objects.nonNull(constraints.getOrganizerId()))
            cachingKeys.add(CachingKey.ORGANIZER);

        if(constraints.getEventStatus() == EventStatus.PRE_SALE)
            cachingKeys.add(CachingKey.PRE_SALE);

        if(constraints.getEventStatus() == EventStatus.SOLD_OUT)
            cachingKeys.add(CachingKey.SOLD_OUT);

        if(constraints.getEventStatus() == EventStatus.ENDED)
            cachingKeys.add(CachingKey.ENDED);

        if(constraints.getEventStatus() == EventStatus.CANCELLED)
            cachingKeys.add(CachingKey.CANCELLED);

        if(fieldsAreNull(constraints))
            cachingKeys.add(CachingKey.ALL_EVENTS);

        return cachingKeys;
    }

    public static String generateQueryKey(CachingKey cachingKey, EventSearchConstraints constraints){
        StringBuilder sb = new StringBuilder();

        sb.append(cachingKey.getKey()).append(":");

        if(cachingKey == CachingKey.CITY)
            sb.append(constraints.getCity());

        if(cachingKey == CachingKey.VENUE)
            sb.append(constraints.getVenueId());

        if(cachingKey == CachingKey.ORGANIZER)
            sb.append(constraints.getOrganizerId());

        System.out.println("Generated cache query key: " + sb);

        return sb.toString();
    }
    private static boolean fieldsAreNull(EventSearchConstraints constraints){

        if(
                (Objects.isNull(constraints.getCity()) || constraints.getCity().isEmpty()) &&
                (Objects.isNull(constraints.getVenueId())) &&
                (Objects.isNull(constraints.getOrganizerId()))
        ){
            return true;
        }

        return false;
    }

}
