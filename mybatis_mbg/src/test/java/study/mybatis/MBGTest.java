package study.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.EmpMapper;

public class MBGTest {

  private SqlSession ss;

  @Test
  public void testMBG() {
    try {
      InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
      SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
      ss = ssf.openSession(true);
      EmpMapper mapper = ss.getMapper(EmpMapper.class);
      // 1. 查詢所有 Emp
      // List<Emp> selectByExample = mapper.selectByExample(null);
      // selectByExample.forEach(System.out::println);

      // 2. 依據條件查詢(QBC)
      // EmpExample empExample = new EmpExample();
      // empExample.createCriteria().andEmpNameEqualTo("老李");
      // List<Emp> emps = mapper.selectByExample(empExample);
      // emps.forEach(System.out::println);

      // 3. 略過 null 不修改(insert 相同)
      // mapper.updateByPrimaryKey(new Emp(3, "admin", 22, "男", "OOO@gmail.com", 3));
      // mapper.updateByPrimaryKeySelective(new Emp(3, "admin", 22, null, "OOO@gmail.com", 3));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
