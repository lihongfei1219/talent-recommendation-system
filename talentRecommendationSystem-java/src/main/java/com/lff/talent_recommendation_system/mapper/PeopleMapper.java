package com.lff.talent_recommendation_system.mapper;


import com.lff.talent_recommendation_system.entity.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author 李飞飞
 * @date 2021-10-20 20:25:57
 */
@Mapper
public interface PeopleMapper{
    /**
     * 查询所有人才
     * */
    List<People> getAllPeople();
    /**
     * 添加人才
     * */
    int insertPeople(People people);
    /**
     * 根据id查询用户
     * */
    People getPeopleByID(String id);
    /**
     * 根据用户搜索的内容进行查询人才返回
     * */
    List<People> getPeopleBySearch(String search);
}
