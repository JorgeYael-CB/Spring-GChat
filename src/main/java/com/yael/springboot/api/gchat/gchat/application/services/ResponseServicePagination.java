package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;




public class ResponseServicePagination<T> extends ResponseService<T> {

    private int currentPage;
    private int maxPage;


    public ResponseServicePagination( T data, int currentPage, int maxPage, int status ){
        super(new Date(), data, status);
        this.maxPage = maxPage;
        this.currentPage = currentPage;
    }

    public ResponseServicePagination(){}


    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getMaxPage() {
        return maxPage;
    }
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

}
