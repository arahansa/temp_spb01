import com.example.ServiceRequest;
import com.example.service.ServiceRequestService;
import com.example.test.TestService;
import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by arahansa on 2015-11-04.
 */
public class ProtoTypeClass {

    @Test
    public void singleToneScope(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, SingletonClientBean.class);

        Set<SingletonBean> beans = new HashSet<SingletonBean>();

        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingletonBean.class));
        assertThat(beans.size(), is(1));

        beans.add(ac.getBean(SingletonClientBean.class).bean1);
        beans.add(ac.getBean(SingletonClientBean.class).bean2);
        assertThat(beans.size(), is(1));
    }

    static class SingletonBean{}
    static class SingletonClientBean{
        @Autowired SingletonBean bean1;
        @Autowired SingletonBean bean2;
    }

    @Test
    public void protoTypeScope(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            ProtoTypeBean.class, ProtoTypeClientBean.class);
        Set<ProtoTypeBean> bean = new HashSet<ProtoTypeBean>();

        bean.add(ac.getBean(ProtoTypeBean.class));
        assertThat(bean.size(), is(1));

        bean.add(ac.getBean(ProtoTypeBean.class));
        assertThat(bean.size(), is(2));

        bean.add(ac.getBean(ProtoTypeClientBean.class).bean1);
        assertThat(bean.size(), is(3));
        bean.add(ac.getBean(ProtoTypeClientBean.class).bean2);
        assertThat(bean.size(), is(4));
    }

    @Scope("prototype")
    static class ProtoTypeBean{}
    static class ProtoTypeClientBean{
        @Autowired ProtoTypeBean bean1;
        @Autowired ProtoTypeBean bean2;
    }

    @Test
    public void objectFactoryBeanTest(){
        ApplicationContext ac = new GenericXmlApplicationContext(new ClassPathResource("objectFactoryBeanTest.xml"));
        ServiceRequestService serviceRequestService = ac.getBean(ServiceRequestService.class);
        ServiceRequest request = serviceRequestService.getRequest();
        assertNotNull(request);
        assertThat(request.getCustomerNo(), is("1"));

        assertThat(request.requestCount, is(1));
        request = serviceRequestService.getRequest();
        assertThat(request.requestCount, is(2));
    }

    @Test
    public void showHelloWorld(){
        ApplicationContext ac = new GenericXmlApplicationContext(new ClassPathResource("objectFactoryBeanTest.xml"));
        ServiceRequestService serviceRequestService = ac.getBean(ServiceRequestService.class);
        serviceRequestService.show();
    }

    @Test
    public void showHelloWorld2(){
        ApplicationContext ac = new GenericXmlApplicationContext(new ClassPathResource("applicationContext.xml"));
        TestService testService = ac.getBean(TestService.class);
        testService.show();
    }






}
