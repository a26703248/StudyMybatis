package study.mybatis.mappers;

import java.util.List;

import study.mybatis.pojo.User;

public interface UserMapper {

  /*
   * Mybatis 須保持兩項一致
   * 1. Mapper XML 中的 namespace 需與介面路徑名稱一致
   * 2. XML 中對應的 SQL 標籤 id 需與方法名稱一致
   * 最後將 Mapper XML 註冊進 config 檔
   */
  int insertUser();

  // 可以不用返回值
  void updateUser();

  void deleteUser();

  // by id
  User getUserById();

  List<User> getUsers();

}
