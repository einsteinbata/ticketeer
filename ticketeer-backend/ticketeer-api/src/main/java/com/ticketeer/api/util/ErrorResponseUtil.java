package com.ticketeer.api.util;


import com.ticketeer.constants.ServiceStatus;
import com.ticketeer.pojo.io.*;

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

    public static DeleteOrganizerOutput generateForDeleteOrganizer(Throwable throwable){
        DeleteOrganizerOutput deleteOrganizerOutput = new DeleteOrganizerOutput();

        deleteOrganizerOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        deleteOrganizerOutput.setErrorMessage(throwable.getMessage());

        return deleteOrganizerOutput;
    }

    public static DeleteVenueOutput generateForDeleteVenue(Throwable throwable){
        DeleteVenueOutput deleteVenueOutput = new DeleteVenueOutput();

        deleteVenueOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        deleteVenueOutput.setErrorMessage(throwable.getMessage());

        return deleteVenueOutput;
    }

    public static DeleteEventOutput generateForDeleteEvent(Throwable throwable){
        DeleteEventOutput deleteEventOutput = new DeleteEventOutput();

        deleteEventOutput.setOperationStatus(ServiceStatus.ERROR.getCod());
        deleteEventOutput.setErrorMessage(throwable.getMessage());

        return deleteEventOutput;
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
