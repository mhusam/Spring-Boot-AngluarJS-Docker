package me.progresssoft.interview.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
public class ApiException extends Exception {

    private final Response response;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.response = new Response(status, message);
    }

    public ApiException(HttpStatus status, String message, Throwable ex) {
        super(message, ex);
        this.response = new Response(status, message);
    }

    public Response getResponse() {return response;}
    public HttpStatus getStatus() {return response.status;}
    public @Override String getMessage() {return response.message;}
    
    @Data
    @AllArgsConstructor
    public static class Response {
        private HttpStatus status;
        private String message;
    }
}
