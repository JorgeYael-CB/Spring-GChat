package com.yael.springboot.api.gchat.gchat.application.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;




public class PaginationDto {

    @Min(value=1)
    @Max(value=200)
    private int limit = 10;

    @Min(value = 0)
    private int page = 0;


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
