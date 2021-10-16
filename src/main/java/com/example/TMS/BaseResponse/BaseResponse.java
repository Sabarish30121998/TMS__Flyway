package com.example.TMS.BaseResponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BaseResponse<T> {
    private String statuscode;
    private String statusmessage;
    private T Data;
}
