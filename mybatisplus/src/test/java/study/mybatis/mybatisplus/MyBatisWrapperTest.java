package study.mybatis.mybatisplus;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import study.mybatis.mybatisplus.mapper.UserMapper;
import study.mybatis.mybatisplus.pojo.User;

@SpringBootTest
public class MyBatisWrapperTest {

  @Autowired
  private UserMapper userMapper;

  // 1. 普通查詢
  @Test
  public void test01() {
    // 查詢名稱包含a,年齡在20~30之間,email不為 null
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("user_name", "a")
        .between("age", 20, 30)
        .isNotNull("email");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  // 2. 排序
  @Test
  public void test02() {
    // 查詢按照年齡降序,若年齡相同則依照id升序
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("age")
        .orderByAsc("uid");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  // 3. 查詢刪除
  @Test
  public void test03() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.isNull("email");
    int result = userMapper.delete(queryWrapper);
    System.out.println(result);
  }

  /* QueryWrapper */
  // 4. 條件查詢修改
  @Test
  public void test04() {
    // 將 年齡大於20 和 名稱包含a 或 email為null做修改
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.gt("age", 20)
        .like("user_name", "a")
        .or()
        .isNull("email");
    User user = new User();
    user.setName("小明");
    user.setEmail("OOO@gmail.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println(result);
  }

  // 5. 條件查詢優先問題
  @Test
  public void test05() {
    // 將 名稱包含a 和 年齡大於20 或 email為null做修改
    // lambda 中的條件優先執行
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("user_name", "a")
        .and(i -> i.gt("age", 20).or().isNull("email"));

    User user = new User();
    user.setName("小洪");
    user.setEmail("OOO@gmail.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println(result);
  }

  // 6. 自訂 SQL 欄位
  @Test
  public void test06() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("user_name", "age", "email");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
  }

  // 7. 子查詢
  @Test
  public void test07() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.inSql("uid", "select uid from t_user where uid <= 100");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  /* UpdateWrapper */
  // 8. 條件查詢修改
  @Test
  public void test08() {
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.like("user_name", "a")
        .and(i -> i.gt("age", 20).or().isNull("email"));
    updateWrapper.set("user_name", "小黑").set("email", "ABC@gmail.com");
    int result = userMapper.update(null, updateWrapper);
    System.out.println(result);
  }

  // 9. 條件判斷
  @Test
  public void test09() {
    String username = "a";
    Integer ageBegin = 20;
    Integer ageEnd = 30;
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if(StringUtils.isNotBlank(username)){
      // 判斷字串是否不為空字串和null
      queryWrapper.like("user_name", username);
    }

    if(ageBegin != null){
      queryWrapper.gt("age", ageBegin);
    }

    if(ageEnd != null){
      queryWrapper.le("age", ageEnd);
    }

    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  // 10. 判斷條件 condition 參數
  @Test
  public void test10() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    String username = "a";
    Integer ageBegin = null;
    Integer ageEnd = 30;

    queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
      .gt(ageBegin != null, "age", ageBegin)
      .le(ageEnd != null, "age", ageEnd);

    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  // 11. LambdaQueryWrapper 查詢
  @Test
  public void test11() {
    String username = "";
    Integer ageBegin = 20;
    Integer ageEnd = 30;
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
      .ge(ageBegin != null, User::getAge, ageBegin)
      .le(ageEnd != null, User::getAge, ageEnd);

    List<User> users = userMapper.selectList(lambdaQueryWrapper);
    users.forEach(System.out::println);
  }

  // 12. LambdaUpdateWrapper 條件查詢修改
  @Test
  public void test12() {
    LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
    lambdaUpdateWrapper.like(User::getName, "a")
        .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
    lambdaUpdateWrapper.set(User::getName, "小黑").set(User::getEmail, "ABC@gmail.com");
    int result = userMapper.update(null, lambdaUpdateWrapper);
    System.out.println(result);
  }



}
