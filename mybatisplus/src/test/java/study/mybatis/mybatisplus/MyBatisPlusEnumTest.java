package study.mybatis.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import study.mybatis.mybatisplus.enums.SexEnum;
import study.mybatis.mybatisplus.mapper.UserMapper;
import study.mybatis.mybatisplus.pojo.User;

@SpringBootTest
public class MyBatisPlusEnumTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void test() {
    User user = new User();
    user.setName("admin");
    user.setAge(33);
    user.setSex(SexEnum.MALE);
    int result = userMapper.insert(user);
    System.out.println(result);
  }

}
