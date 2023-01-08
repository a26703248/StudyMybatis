package study.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.SQLMapper;
import study.mybatis.pojo.User;
import utils.SqlSessionUtils;

public class SQLMapperTest {

  // 模糊查詢
  @Test
  public void testGetUserByLike() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SQLMapper mapper = ss.getMapper(SQLMapper.class);
    List<User> userByLike = mapper.getUserByLike("ad");
    System.out.println(userByLike);
  }

  @Test
  public void testDeleteMore() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SQLMapper mapper = ss.getMapper(SQLMapper.class);
    int result = mapper.deleteMore("1,2,3");
    System.out.println(result);
  }

  @Test
  public void testGetUserByTableName() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SQLMapper mapper = ss.getMapper(SQLMapper.class);
    List<User> users = mapper.getUserByTable("t_user");
    users.forEach(System.out::println);
  }

  // 返回自動遞增 Id
  @Test
  public void testInsertUser() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    SQLMapper mapper = ss.getMapper(SQLMapper.class);
    User user = new User(null, "老李", "123", 23, "男", "XOX@gmail.com");
    mapper.insertUser(user);
    System.out.println(user);
  }

  // 原生 JDBC 返回自動遞增 Id
  @Test
  public void testJDBC() throws Exception {
    Class.forName("");
    Connection conn = DriverManager.getConnection("", "", "");
    PreparedStatement ps = conn.prepareStatement("insert", Statement.RETURN_GENERATED_KEYS);
    ps.executeUpdate();
    ResultSet resultSet = ps.getResultSet();
  }

}
