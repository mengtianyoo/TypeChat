package cn.henu.typechatbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan(basePackages = "cn.henu.typechatbackend")
@EnableAsync
public class TypeChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeChatBackendApplication.class, args);
    }

}
