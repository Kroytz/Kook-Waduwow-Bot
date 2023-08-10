package com.ybybcsgo.backend;

import com.ybybcsgo.backend.utils.MaximHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        MaximHelper.ConvertFullChatRecordToMaxim();

        SpringApplication.run(BackendApplication.class, args);
    }

}
