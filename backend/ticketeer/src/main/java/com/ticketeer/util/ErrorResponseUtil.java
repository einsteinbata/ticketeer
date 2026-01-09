package com.ticketeer.util;

import com.ticketeer.constants.ServiceStatus;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;

public class ErrorResponseUtil {

    public static GetVenuesOutput generateForGetVenues(Throwable throwable){
        GetVenuesOutput getVenuesOutput = new GetVenuesOutput();
        getVenuesOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        getVenuesOutput.setErrorMessage(throwable.getMessage());

        return getVenuesOutput;
    }

    public static GetOrganizersOutput generateForGetOrganizers(Throwable throwable){
        GetOrganizersOutput getOrganizersOutput = new GetOrganizersOutput();

        getOrganizersOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        getOrganizersOutput.setErrorMessage(throwable.getMessage());

        return getOrganizersOutput;
    }

    public static AddEventOutput generateForAddEvent(Throwable throwable){
        AddEventOutput addEventOutput = new AddEventOutput();

        addEventOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        addEventOutput.setErrorMessage(throwable.getMessage());

        return addEventOutput;
    }

    public static GetEventsOutput generateForGetEvents(Throwable throwable){
        GetEventsOutput getEventsOutput = new GetEventsOutput();

        getEventsOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        getEventsOutput.setErrorMessage(throwable.getMessage());

        return getEventsOutput;
    }

}
