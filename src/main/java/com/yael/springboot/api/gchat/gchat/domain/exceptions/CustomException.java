package com.yael.springboot.api.gchat.gchat.domain.exceptions;



public class CustomException extends RuntimeException {

    private int status;
    private String error;

    public CustomException(int status, String error){
        super(error);

        this.status = status;
        this.error = error;
    }


    public static CustomException badRequestException( String error ){
        return new CustomException(400, error);
    }

    public static CustomException notFoundException( String error ){
        return new CustomException(404, error);
    }

    public static CustomException unaothorizedException( String error ){
        return new CustomException(401, error);
    }

    public static CustomException internalServerException( String error ){
        // TODO: logger
        System.out.println(error);

        return new CustomException(500, "Internal server error, please try again later.");
    }

    public static CustomException unexpectedException( String error ){
        return new CustomException(415, error);
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
