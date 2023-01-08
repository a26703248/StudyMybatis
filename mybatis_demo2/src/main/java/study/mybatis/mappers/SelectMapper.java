package study.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.User;

public interface SelectMapper {

  // 單筆查詢 by id
  User getUserById(@Param("id")Integer id);

  // 多筆查詢
  List<User> getAllUser();

  // 取得使用者數量
  Integer getCount();

  // 查詢使用者 by id to map
  Map<String, Object> getUserByIdToMap(@Param("id")Integer id);

  // 查詢所有使用者 by id to map
  // 1. 多值用 List 對應
  // List<Map<String, Object>> getAllUserByIdToMap();
  // 2. 設定 map key 值
  @MapKey("id")// 設定轉換成 map 物件的 key 值
  Map<String, Object> getAllUserByIdToMap();
}
