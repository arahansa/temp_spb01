package com.example;

import lombok.Data;
import org.springframework.context.annotation.Scope;

/**
 * Created by arahansa on 2015-11-05.
 */
@Scope("prototype")
@Data
public class ServiceRequest {
    String customerNo="1";
    String productNo;
    String description;

    public static int requestCount;
    public ServiceRequest(){
        requestCount++;
    }


}
