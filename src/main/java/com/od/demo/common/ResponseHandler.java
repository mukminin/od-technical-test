package com.od.demo.common;

import com.od.demo.service.Customer.model.common.Meta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj){
        Map<String, Object> map = new HashMap<String, Object>();
        Meta meta = new Meta();
        meta.setCode(String.valueOf(status.value()));

        map.put("meta", meta);
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity generateResponseMeta(HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        Meta meta = new Meta();
        meta.setCode(String.valueOf(status.value()));

        map.put("meta", meta);


        return new ResponseEntity(map,status);
    }

    public static ResponseEntity generateErrorMessage(String message,HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        Meta meta = new Meta();
        meta.setCode(String.valueOf(status.value()));

        map.put("meta", meta);
        map.put("message", message);


        return new ResponseEntity(map,status);
    }
}
