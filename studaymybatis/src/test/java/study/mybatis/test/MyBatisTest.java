package study.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.UserMapper;
import study.mybatis.pojo.User;

public class MyBatisTest {

  @Test
  public void testInsertUser() throws IOException {
    // 讀取 config 檔
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

    // 透過 SqlSessionFactory 取得 MySql session 連線
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    // 取得 Mapper(底層使用代理完成)
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    int result = mapper.insertUser();

    // 提交交易(因使用原生 JDBC 所以需手動commit)
    // 也可openSession(autoCommit=true)自動提交
    // sqlSession.commit();
    System.out.println("result: " + result);
  }

  @Test
  public void testUpdateUser() throws IOException {
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    mapper.updateUser();

  }

  @Test
  public void testDeleteUser() throws IOException {
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    mapper.deleteUser();
  }

  @Test
  public void testGetUserById() throws IOException {
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    User user = mapper.getUserById();
    System.out.println("select by id: " + user);
  }

  @Test
  public void testGetUsers() throws IOException {
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    List<User> users = mapper.getUsers();
    users.forEach(System.out::println);
  }

}
