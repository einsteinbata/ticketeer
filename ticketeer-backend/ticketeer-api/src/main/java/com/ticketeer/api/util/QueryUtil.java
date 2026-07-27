package com.ticketeer.api.util;

import com.ticketeer.api.constraints.EventSearchConstraints;
import com.ticketeer.constants.CachingKey;

import java.util.List;

public class QueryUtil {

    public static EventSearchConstraints generateForEventSearch(List<CachingKey> cachingKeys, EventSearchConstraints constraints){
        EventSearchConstraints limitedConstraints = new EventSearchConstraints();

        for(CachingKey cachingKey : cachingKeys){

            if(cachingKey == CachingKey.FEATURED_EVENT){
                limitedConstraints.setFeatured(constraints.isFeatured());
            }

            if(cachingKey == CachingKey.VENUE){
                limitedConstraints.setVenueId(constraints.getVenueId());
            }

            if(cachingKey == CachingKey.ORGANIZER){
                limitedConstraints.setOrganizerId(constraints.getOrganizerId());
            }

            if(
                    cachingKey == CachingKey.PRE_SALE ||
                    cachingKey == CachingKey.SOLD_OUT ||
                    cachingKey == CachingKey.ENDED ||
                    cachingKey == CachingKey.CANCELLED
            ) {
                limitedConstraints.setEventStatus(constraints.getEventStatus().name());
            }

            //TODO add for others

        }


        return limitedConstraints;
    }

}
