package study.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.User;

public interface ParameterMapper {

  int insertUser(User user);

  List<User> checkLoginByAnnotation(@Param("username") String username, @Param("password") String password);

  List<User> checkLoginByMap(Map<String, Object> map);

  List<User> checkLogin(String username, String password);

  List<User> getUserByUsername(String username);

  List<User> getAllUser();

}
