package com.example.service;

import com.example.ServiceRequest;
import com.example.test.TestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


/**
 * Created by arahansa on 2015-11-05.
 */
public class ServiceRequestService {

    @Autowired
    TestService testService;
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Resource
    private ObjectFactory<ServiceRequest> serviceRequestObjectFactory;
    public void setServiceRequestObjectFactory(ObjectFactory<ServiceRequest> serviceRequestObjectFactory) {
        this.serviceRequestObjectFactory = serviceRequestObjectFactory;
    }

    public void show(){
        testService.show();
    }

    public ServiceRequest getRequest(){
        ServiceRequest request = this.serviceRequestObjectFactory.getObject();
        return request;
    }


}
