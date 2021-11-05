package com.lff.talent_recommendation_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lff.talent_recommendation_sys                                                                                                                                                                                                                                                                                                                              tem.mapper")
public class TalentRecommendationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TalentRecommendationSystemApplication.class, args);
    }

}
