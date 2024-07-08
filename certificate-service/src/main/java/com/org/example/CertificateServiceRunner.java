package com.org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CertificateServiceRunner {
    public static void main(String[] args) {
        try {
            SpringApplication.run(CertificateServiceRunner.class, args);
        } catch (Exception e){
            System.out.printf("Не работает" + e.getMessage());
        }

    }
}
