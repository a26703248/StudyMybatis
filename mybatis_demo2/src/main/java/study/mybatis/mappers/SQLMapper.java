package study.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.User;

public interface SQLMapper {
  // 模糊查詢
  List<User> getUserByLike(@Param("username") String username);

  // 多筆刪除
  int deleteMore(@Param("ids") String ids);

  // 指定資料表查詢
  List<User> getUserByTable(@Param("tableName")String tableName);

  // 返回 Id
  void insertUser(User user);
}
