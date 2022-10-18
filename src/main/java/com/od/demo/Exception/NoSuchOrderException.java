package com.od.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class NoSuchOrderException extends HttpClientErrorException {
    public NoSuchOrderException(){
        super (HttpStatus.NOT_FOUND,"THe order doesn't exist");
    }
}
