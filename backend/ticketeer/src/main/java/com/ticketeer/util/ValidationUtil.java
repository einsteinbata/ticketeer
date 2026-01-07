package com.ticketeer.util;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddVenueInput;

import java.util.Objects;

public class ValidationUtil {

    //The venue coordinates and some other fields are currently not mandatory
    public static boolean validateAddVenueInput(AddVenueInput input) throws FieldValidationError {

        if(Objects.isNull(input))
            throw new FieldValidationError("Invalid venue object");

        if(Objects.isNull(input.getVenueName()) || input.getVenueName().isEmpty())
            throw new FieldValidationError("Invalid venue name");

        if(Objects.isNull(input.getVenueCity()) || input.getVenueCity().isEmpty())
            throw new FieldValidationError("Invalid venue city");

        if(input.getCapacity() < 1)
            throw new FieldValidationError("Invalid venue capacity");

        return true;
    }

    public static boolean validateAddOrganizerInput(AddOrganizerInput input) throws FieldValidationError {
        if(Objects.isNull(input))
            throw new FieldValidationError("Invalid organizer object");

        if(Objects.isNull(input.getOrganizerName()) || input.getOrganizerName().isEmpty())
            throw new FieldValidationError("Invalid organizer name");

        if(Objects.isNull(input.getOrganizerEmail()) || input.getOrganizerEmail().isEmpty())
            throw new FieldValidationError("Invalid organizer email");

        if(Objects.isNull(input.getOrganizerPhoneNumber()) || input.getOrganizerPhoneNumber().isEmpty())
            throw new FieldValidationError("Invalid organizer phone number");

        if(Objects.isNull(input.getOrganizerAddress()) || input.getOrganizerAddress().isEmpty())
            throw new FieldValidationError("Invalid organizer address");

        return true;
    }

    public static boolean validateAddEventInput(AddEventInput input) throws FieldValidationError{

        if(Objects.isNull(input))
            throw new FieldValidationError("Invalid event object");

        if(Objects.isNull(input.getSeatArrangement()) || input.getSeatArrangement().getSeatCategoryList().isEmpty())
            throw new FieldValidationError("Invalid seat arrangement");

        if(Objects.isNull(input.getEventDate()) || input.getEventDate().isEmpty())
            throw new FieldValidationError("Invalid event date");

        //TODO check if event date is not past

        if(Objects.isNull(input.getVenueId()))
            throw new FieldValidationError("Invalid venue ID");

        if(Objects.isNull(input.getOrganizerId()))
            throw new FieldValidationError("Invalid organizer ID");

        return true;

    }

}
