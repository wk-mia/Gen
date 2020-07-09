package com.priv.mia;

import com.priv.mia.generate.GenByTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : wuyz
 * @date : 2020/07/09
 */
@SpringBootApplication
public class GenApplication {


    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
        try {
            GenByTemplate.generate();
        }catch (Exception e){
            System.out.println("some error occurred...");
            System.out.println("the detailed exception is as follows...");
            e.printStackTrace();
        }

    }

}
