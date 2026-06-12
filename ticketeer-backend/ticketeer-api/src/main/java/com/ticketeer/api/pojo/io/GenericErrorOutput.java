package com.ticketeer.api.pojo.io;

import com.ticketeer.api.constants.ServiceStatus;

public class GenericErrorOutput extends ServiceOutput{

    public GenericErrorOutput(){
        setOperationStatus(ServiceStatus.ERROR.getCod());
        setErrorMessage(ServiceStatus.SUCCESS.getMsg());
    }

    public GenericErrorOutput(Exception err){
        setOperationStatus(ServiceStatus.ERROR.getCod());
        setErrorMessage(err.getLocalizedMessage());
    }

}
