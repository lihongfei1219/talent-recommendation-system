package com.lff.talent_recommendation_system.controller;

import com.lff.talent_recommendation_system.biz.PeopleBiz;
import com.lff.talent_recommendation_system.biz.SearchBiz;
import com.lff.talent_recommendation_system.entity.Search;
import com.lff.talent_recommendation_system.exception.HandleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lff.talent_recommendation_system.entity.People;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 控制层
 * @author 李飞飞
 * @date 2021-10-20 20:25:57
 */
@RestController
@Slf4j
@RequestMapping("people")
public class Controller {
    @Autowired
    PeopleBiz peopleBiz;
    @Autowired
    SearchBiz searchBiz;

    /**
     * 添加人才操作
     * */
    @HandleException("注册信息不合法！")
    @PostMapping(value = "insertPeople",produces = "application/json;charset=UTF-8")
    public int insertPeople(@RequestBody People people){
        return peopleBiz.insertPeople(people);
    }
    /**
     * 根据用户搜索的内容进行查询人才返回
     * */
    @GetMapping(value = "getPeopleBySearch")
    public List<People> getPeopleBySearch(Search search){
        String data = search.getData();

        try {
            searchBiz.saveHotData(data);
            searchBiz.insertSearch(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleBiz.getPeopleBySearch(data);
    }
    /**
     * 查询所有人才
     * */
    @GetMapping(value = "getAllPeople")
    public List<People> getAllPeople(){
        return peopleBiz.getAllPeople();
    }
    /**
     * 根据id查询用户信息
     * */
    @GetMapping(value = "getPeopleByID/{id}")
    public People getPeopleByID(@PathVariable String id){
        return peopleBiz.getPeopleByID(id);
    }
    /**
     * 接受前端传过来的图片
     * */
    @RequestMapping(value = "savePhoto",method = RequestMethod.POST)
    public void savePhoto(@RequestParam MultipartFile photo){
        try {
            peopleBiz.savePhoto(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 今日热搜数据
     * */
    @RequestMapping("getHostData")
    public List<Search> getHostData(){
        return searchBiz.getHotWord();
    }
}