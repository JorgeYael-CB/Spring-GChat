package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;




public class ResponseServicePagination<T> extends ResponseService<T> {

    private Long allElements;
    private int allPages;


    public ResponseServicePagination( T data, Long allElements, int allPages, int status ){
        super(new Date(), data, status);
        this.allPages = allPages;
        this.allElements = allElements;
    }

    public ResponseServicePagination(){}


    public Long getallElements() {
        return allElements;
    }
    public void setallElements(Long allElements) {
        this.allElements = allElements;
    }
    public int getAllPages() {
        return allPages;
    }
    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

}
