package study.mybatis.mybatisplus.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import study.mybatis.mybatisplus.pojo.User;

@Repository
public interface UserMapper extends BaseMapper<User> {

  /**
   * 依據 Id 查詢返回Maps<String, Object>物件
   * @param id 查詢 id
   * @return
   */
  public Map<String, Object> selectMapById(Long id);

  /**
   * 通過年齡查詢並分頁
   * @param page Mybatis-Plus 提供的分頁物件,必須為第一個參數
   * @param age
   * @return
   */
  Page<User> selectPageVo(@Param("page")Page<User> page, @Param("age")Integer age);

}
