package com.lff.talent_recommendation_system.biz;


import com.lff.talent_recommendation_system.entity.Search;
import com.lff.talent_recommendation_system.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 业务层
 *
 * @author 李飞飞
 * @date 2021-10-20 20:25:57
 */
@Service
public class SearchBiz {
    /**
     *
     */
    @Autowired
    SearchMapper searchMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加搜索内容
     * */
    public int insertSearch(Search search){

        return searchMapper.insertSearch(search);
    }
    /**
     * 设置缓存失效时间，统一为凌晨零点
     * @param hotWord
     * @throws Exception
     */
    public void saveHotData(String hotWord) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        //晚上十二点与当前时间的毫秒差
        Long timeOut = (calendar.getTimeInMillis()-System.currentTimeMillis()) / 1000;
        redisTemplate.expire("hotWord",timeOut, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().incrementScore("hotWord", hotWord, 1);
        // 加入排序set
    }



    /**
     * 获取热词前五位
     * @return
     */
    public List<Search> getHotWord() {
        List<Search> searchList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("hotWord",1,100);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        int flag = 0;
        while (iterator.hasNext()){
            flag++;
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            String value = (String)typedTuple.getValue();
            int score = (int) Math.ceil(typedTuple.getScore());
            Search search = new Search();
            search.setNumber(score);
            search.setData(value);
            searchList.add(search);
            if( flag >= 5 ) {
                break;
            }
        }
        return searchList;
    }


}