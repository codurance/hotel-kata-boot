package com.codurance.corporatehotel.common.exception;

public class HotelNotExistsException extends RuntimeException{

    public HotelNotExistsException(){
        super("A hotel with given id does not exist in the system.");
    }

}
