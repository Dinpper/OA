package com.example.labSystem.la;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ParkingIncome {
    private double income;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    private Integer hour;

    // Getter 和 Setter 方法

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }


    // toString 方法
    @Override
    public String toString() {
        return "ParkingIncome{" +
                "income=" + income +
                ", reportDate=" + reportDate +
                ", hour=" + hour +
                '}';
    }
}
