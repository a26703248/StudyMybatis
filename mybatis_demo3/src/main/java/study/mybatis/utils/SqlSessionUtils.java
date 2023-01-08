package study.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {

  public static SqlSession getSqlSession() {
    SqlSession session = null;
    try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
      SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
      session = ssf.openSession(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return session;
  }

}
