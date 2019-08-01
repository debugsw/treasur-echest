package com.liushuai.treasurechest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ls
 */
@SpringBootApplication
@EnableScheduling
public class TreasureChestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreasureChestApplication.class, args);
    }

}
