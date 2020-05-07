package com.sherbenko.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Room Not Found")
public class RoomNotFoundException extends Exception{

    private static final long serialVersionUID = -3332292346834265371L;

    public RoomNotFoundException(long id){
        super("RoomNotFoundException with id="+id);
    }
}
