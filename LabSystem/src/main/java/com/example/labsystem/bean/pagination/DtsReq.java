package com.example.labsystem.bean.pagination;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;


public class DtsReq {

    @Min(value = 0, message = "开始序号必须大于等于0")
    @ApiModelProperty(required = true, value = "开始序号[0-N]")
    private int start;
    @Range(min = 10, max = 55, message = "请求容量必须大于等于10,小于等于55")
    @ApiModelProperty(required = true, value = "每次请求的容量")
    private int length;
    @ApiModelProperty(value = "排序Json")
    private String sortJson;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSortJson() {
        return sortJson;
    }

    public void setSortJson(String sortJson) {
        this.sortJson = sortJson;
    }
}
