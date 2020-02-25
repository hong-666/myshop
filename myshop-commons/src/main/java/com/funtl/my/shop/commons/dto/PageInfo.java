package com.funtl.my.shop.commons.dto;

import com.funtl.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T extends BaseEntity> implements Serializable {
    private int darw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public int getDarw() {
        return darw;
    }

    public void setDarw(int darw) {
        this.darw = darw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
