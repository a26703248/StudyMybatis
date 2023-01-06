package study.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.ParameterMapper;
import study.mybatis.pojo.User;
import utils.SqlSessionUtils;

public class ParameterMapperTest {

  @Test
  public void testGetAllUser() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    List<User> users = mapper.getAllUser();
    users.forEach(System.out::println);
  }

}
