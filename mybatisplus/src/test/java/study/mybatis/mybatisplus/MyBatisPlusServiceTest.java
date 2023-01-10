package study.mybatis.mybatisplus;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import study.mybatis.mybatisplus.pojo.User;
import study.mybatis.mybatisplus.service.UserService;

@SpringBootTest
public class MyBatisPlusServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testGetCount() {
    // 查詢資料總筆數
    long count = userService.count();
    System.out.println("count: " + count);
  }

  @Test
  public void testInsertMore() {
    // 批量新增
    ArrayList<User> arrayList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      User user = new User();
      user.setName("abc"+1);
      user.setAge(20+i);
      arrayList.add(user);
    }
    boolean result = userService.saveBatch(arrayList);
    System.out.println(result);
  }

}
