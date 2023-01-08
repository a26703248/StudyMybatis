package study.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.SelectMapper;
import study.mybatis.pojo.User;
import utils.SqlSessionUtils;

public class SelectMapperTest {

  @Test
  public void testGetUserById() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = ss.getMapper(SelectMapper.class);
    User user = mapper.getUserById(2);
    System.out.println(user);

    List<User> users = mapper.getAllUser();
    users.forEach(System.out::println);
  }

  @Test
  public void testGetCount() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = ss.getMapper(SelectMapper.class);
    Integer result = mapper.getCount();
    System.out.println(result);

  }

  @Test
  public void testGetUserByIdToMap() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = ss.getMapper(SelectMapper.class);
    Map<String, Object> userToMap = mapper.getUserByIdToMap(2);
    System.out.println(userToMap);
  }

  @Test
  public void testGetAllUserByIdToMap() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = ss.getMapper(SelectMapper.class);
    // List<Map<String, Object>> userToMap = mapper.getAllUserByIdToMap();
    // userToMap.forEach(System.out::println);

    Map<String, Object> userToMap = mapper.getAllUserByIdToMap();
    System.out.println(userToMap);
  }

}
