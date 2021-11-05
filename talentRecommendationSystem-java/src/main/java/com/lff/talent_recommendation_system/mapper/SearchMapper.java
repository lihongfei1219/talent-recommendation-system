package com.lff.talent_recommendation_system.mapper;



import com.lff.talent_recommendation_system.entity.Search;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author 李飞飞
 * @date 2021-10-20 20:25:57
 */
@Mapper
public interface SearchMapper {
    /**
     * 添加搜索信息
     * */
    int insertSearch(Search search);

}
