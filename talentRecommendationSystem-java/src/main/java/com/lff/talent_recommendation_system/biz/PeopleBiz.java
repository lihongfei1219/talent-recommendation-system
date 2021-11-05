package com.lff.talent_recommendation_system.biz;


import com.lff.talent_recommendation_system.entity.People;
import com.lff.talent_recommendation_system.mapper.PeopleMapper;
import com.lff.talent_recommendation_system.until.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 业务层
 *
 * @author 李飞飞
 * @date 2021-10-20 20:25:57
 */
@Service
public class PeopleBiz{
    /**
     *
     */
    @Autowired
    PeopleMapper peopleMapper;
    /**
     * 查询所有人才
     * */
    public List<People> getAllPeople(){
        return peopleMapper.getAllPeople();
    }
    /**
     * 添加人才
     * */
    public int insertPeople(People people){
        //TODO 前端传过来的图片名进行一个储存就可以了，存在mysql里面
        //TODO 然后实际上把图片数据存储在磁盘 （有oss肯定最好存储在oss啊）
        people.setPhoto("http://localhost:8080/".concat(people.getPhoto()));
        String skills = people.getSkills();
        //根据提交的技能来给综合能力打分
        String rewards = people.getRewards();
        //根据提交的获奖情况来计算科研成果的分数 TODO
        people.setSuccessNum(rewards.split(",").length*10);
        people.setTalent(skills.split(",").length*10);
        return peopleMapper.insertPeople(people);
    }

    /**
     * 根据获取的id来查询人才
     * */

    public People getPeopleByID(String id){
        return peopleMapper.getPeopleByID(id);
    }
    /**
     * 根据用户搜索的内容进行查询人才返回
     * */
    public List<People> getPeopleBySearch(String search)  {
        //对用户的输入进行一个提取内容然后返还人才
        JSONObject jsobj1 = new JSONObject();
        try {
            jsobj1.put("data",search);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result= HttpUtil.post(jsobj1,"http://localhost:8088/data");
        System.out.println(result);

        //TODO 正则表达式去提取数据

        String data = result.substring(1,result.length()-2);
        System.out.println(data);
        return peopleMapper.getPeopleBySearch(data);

    }
    /**
     * 接受前端传过来的图片并保存
     * */
    public void savePhoto(MultipartFile photo) throws IOException {
        String name =photo.getOriginalFilename();
        //获取图片的名称
        FileOutputStream picOutput = null;
        //设置存储路径
        try {
            picOutput = new FileOutputStream("E:\\images\\"+name);
            picOutput.write(photo.getBytes());
            //获取字节流直接写入到磁盘内
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            picOutput.close();//关闭字节流
        }

    }

}