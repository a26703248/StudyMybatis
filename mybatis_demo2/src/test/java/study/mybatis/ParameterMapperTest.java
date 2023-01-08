package study.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.ParameterMapper;
import study.mybatis.pojo.User;
import utils.SqlSessionUtils;

public class ParameterMapperTest {

  /*
   * Mybatis 取得參數 ${} and #{}
   * ${} 本質為文字拼接
   * #{} 本質為佔位符注入
   * Mybatis 傳入參數狀況
   * 1. mapper 介面方法的參數為單個類型的參數
   * 可通過${} 與 #{} 取得以任意名稱得到的值
   * 因 ${} 為字串拼接所以須注意文字是否有加上單引號,#{}則不須
   *
   * 2. mapper 介面傳入多個值
   * 若有多個參數 mybatis 預設會以 Map 類別填充,所以無法用其他名稱作為變數名稱,
   * arg0, arg1, param1, param2 (可混用)
   * 因 ${} 為字串拼接所以須注意文字是否有加上單引號,#{}則不須
   *
   * 3. 若 mapper 介面傳入多個值時，自行手動對應 Mybatis 參數 Map 值
   * 只需通過 ${} 和 #{} 已鍵值方式取值,但須注意${}是否有加上單引號
   *
   * 4. mapper 參數是一個物件
   * 只需通過 ${} 和 #{} 已屬性方式取得,但須注意${}是否有加上單引號
   *
   * 5. 使用 @Param() 註解方式宣告參數名稱
   * 可透過  @Param() 定義 Mapper xml 取用變數名稱
   */
  @Test
  public void testGetUserByUsername() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    List<User> users = mapper.getUserByUsername("admin");
    users.forEach(System.out::println);
  }

  @Test
  public void testCheckLoginByMap() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    Map<String, Object> map =new HashMap<>();
    map.put("username", "admin");
    map.put("password", "123456");
    List<User> users = mapper.checkLoginByMap(map);
    users.forEach(System.out::println);
  }

  @Test
  public void testCheckLogin() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    List<User> users = mapper.checkLogin("admin", "123456");
    users.forEach(System.out::println);
  }

  @Test
  public void testGetAllUser() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    List<User> users = mapper.getAllUser();
    users.forEach(System.out::println);
  }

  @Test
  public void testInsertUser() {
    SqlSession session = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = session.getMapper(ParameterMapper.class);
    int result = mapper.insertUser(new User(null, "Hellen", "1234", 21, "男", "OOO@gmail.com"));
    System.out.println(result);
  }

  // 原生 JDBC 寫法
  @Test
  public void testJDBC() throws Exception{
    String username = "admin";
    Class.forName("");
    Connection conn = DriverManager.getConnection("", "", "");
    conn.prepareStatement("select * from t_user where username = '" + username + "'");

    PreparedStatement ps = conn.prepareStatement("select * from t_user where username = ?");
    ps.setString(1, username);

  }
}
