package study.mybatis.mybatisplus;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import study.mybatis.mybatisplus.mapper.UserMapper;
import study.mybatis.mybatisplus.pojo.User;

@SpringBootTest
public class MyBatisPlusTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testSelectList() {
    List<User> list = userMapper.selectList(null);
    list.forEach(System.out::println);
  }

  @Test
  public void testInsert() {
    User user = new User();
    user.setName("老李");
    user.setAge(23);
    user.setEmail("XXX@gmail.com");
    int result = userMapper.insert(user);
    System.out.println("result: " + result);
    System.out.println("id: " + user.getId());
  }

  @Test
  public void testDelete() {
    // 1. delete by id
    // int result = userMapper.deleteById(1612621881834381314L);
    // System.out.println(result);

    // 2. delete by map
    // Map<String, Object> map = new HashMap<>();
    // map.put("name", "老李");
    // map.put("age", 23);
    // int result = userMapper.deleteByMap(map);
    // System.out.println(result);

    // 3. batch delete by id
    List<Long> list = Arrays.asList(1L, 2L, 3L);
    userMapper.deleteBatchIds(list);
  }

  @Test
  public void testUpdate() {
    User user = new User();
    user.setId(4L);
    user.setName("老李");
    user.setEmail("XXX@gmail.com");
    int result = userMapper.updateById(user);
    System.out.println("result: " + result);
  }

  @Test
  public void testSelect() {
    // 1. select by id
    // User user = userMapper.selectById(1L);
    // System.out.println(user);

    // 2. batch select by id
    // List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
    // users.forEach(System.out::println);

    // 3. select by map
    // Map<String, Object> map = new HashMap<>();
    // map.put("name", "Jack");
    // map.put("age", 20);
    // List<User> users = userMapper.selectByMap(map);
    // users.forEach(System.out::println);

    // 4. select by queryWrapper
    // List<User> users = userMapper.selectList(null);
    // users.forEach(System.out::println);

    // 5. custom SQL
    // Map<String, Object> map = userMapper.selectMapById(1L);
    // System.out.println(map);
  }

}
