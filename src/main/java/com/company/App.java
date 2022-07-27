package com.company;

import com.company.conf.AppConfig;
import com.company.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SomeService service = context.getBean(SomeService.class);
        service.runFirstMethod();
        service.runSecMethod(19);
        System.out.println(service.getStr(1));
        System.out.println(service.getStr(2));
        System.out.println(service.getStr(1));
    }
}
