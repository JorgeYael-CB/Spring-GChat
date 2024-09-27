package com.yael.springboot.api.gchat.gchat.application.services;




public class ResponseServicePagination<T> extends ResponseService<T> {

    private int currentPage;
    private int maxPage;


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
