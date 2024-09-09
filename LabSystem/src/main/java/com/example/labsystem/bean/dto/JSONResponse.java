package com.example.labsystem.bean.dto;

import java.util.List;

public class JSONResponse<T> {

    //  private String draw; // 第几页
    private long iTotalRecords;
    private long iTotalDisplayRecords;
    private List<T> aaData;

    public JSONResponse(long totalRecords, long totalDisplayRecords, String draw, List<T> d) {
        this.iTotalRecords = totalRecords;
        this.iTotalDisplayRecords = totalDisplayRecords;
        // this.draw = draw;
        this.aaData = d;
    }

//
//    public String getDraw() {
//        return draw;
//    }
//
//    public void setDraw(String draw) {
//        this.draw = draw;
//    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }
}
