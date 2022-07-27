package com.company.service;

import com.company.anotat.Cache;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    public void runFirstMethod(){
        System.out.println("Runnnnnnnnn first method");
    }

    public void runSecMethod(int param){
        System.out.println("Runnn //// + param" + param);
    }

    @Cache
    public String getStr(int param){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hi, Aspect";
    }
}
