package com.example.delivery.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ResourceNotFoundException("resource didn'f dinf");
        } else if (response.status() == 401) {
            return new ResourceNotFoundException("This user doesn't have aturization");
        } else if (response.status() == 500) {
            return new ResourceNotFoundException("Internal error in the server");
        } else {
            return new Exception("Unknow Error");
        }
    }
}
