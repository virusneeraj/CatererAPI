package com.hunza.catererapi.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class APIResponse implements Serializable {
    private String status;
    private String message;
    private Object data;
}
