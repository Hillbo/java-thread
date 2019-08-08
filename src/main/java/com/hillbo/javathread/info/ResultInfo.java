package com.hillbo.javathread.info;

import lombok.Data;

@Data
public class ResultInfo {

    private String result;

    public ResultInfo(String result) {
        this.result = result;
    }
}
