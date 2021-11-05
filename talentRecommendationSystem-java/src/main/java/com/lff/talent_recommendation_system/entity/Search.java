package com.lff.talent_recommendation_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 李飞飞
 * @Description:搜索内容实体类
 * @Date: Created in 9:07 2021/10/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search implements Serializable {
    /**
     * 搜索次数
     * */
    private int number;
    /**
     * 搜索内容
     * */
    private String data;

}
