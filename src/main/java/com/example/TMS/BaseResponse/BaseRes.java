package com.example.TMS.BaseResponse;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class BaseRes<T> {

    @Builder.Default
    private String statuscode = "200";

    @Builder.Default
    private String stausmessage = "Success";

    private T Data;

}
