package com.lff.talent_recommendation_system;

import com.lff.talent_recommendation_system.until.ReUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TalentRecommendationSystemApplicationTests {

    @Test
    void contextLoads() {
        String result = ReUtil.dataChange("[\"食用\", \"肉\", \"鸭\", \"培育\", \"我\", \"想\", \"找\", \"一个\", \"人才\"]");
        System.out.println(result);
    }

}
