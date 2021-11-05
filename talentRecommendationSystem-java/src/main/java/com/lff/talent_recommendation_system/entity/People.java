package com.lff.talent_recommendation_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author 李飞飞
 * @Description:人才实体类
 * @date 2021-10-20 20:25:57
 */
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People implements Serializable {



    /**
     * 主键
     */

    private String id;

    /**
     * 姓名
     */

    private String name;

    /**
     * 年龄
     */

    private Integer age;

    /**
     * 电话号码
     */

    private String phone;

    /**
     *  照片
     */

    private String photo;

    /**
     * 工作年限
     * */

    private int workTime;

    /**
     * 科技成果分数
     * */

    private int successNum;
    /**
     * 综合能力分数
     * */

    private int talent;
    /**
     * 技能
     * */

    private String skills;
    /**
     * 获奖情况
     * */

    private String rewards;
}
