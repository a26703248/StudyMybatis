package study.mybatis;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.DynamicSQLMapper;
import study.mybatis.pojo.Emp;
import study.mybatis.utils.SqlSessionUtils;

public class DynamicSQLMapperTest {

  /*
   * 動態拼接 SQL 語法 tag
   * 1. if: 可透過 text 屬性內判斷式是否要拼接上 SQL
   * 2. where: where 內容無任何條件將不會生成,可自動判斷條件前方是否要刪除 or 和 and(只能將放置前方做刪除,放置後方則無效)
   * 3. trim: 自訂增加或刪除內容規則prefix|suffix|prefixOverrides|suffixOverrides
   * 4. choose|when|otherwise:等價於 if..else, if..else-if..else,when 只少要一個,otherwise 只能有一個
   * 5. foreach: 可迭代集合內容
   * 6. sql|include: 可宣告 SQL 片段語法
   */
  @Test
  public void testGetEmpByCondition() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DynamicSQLMapper mapper = ss.getMapper(DynamicSQLMapper.class);
    List<Emp> empByCondition = mapper.getEmpByCondition(new Emp(null, "", null, "", null));
    empByCondition.forEach(System.out::println);
  }

  @Test
  public void testGetEmpByChoose() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DynamicSQLMapper mapper = ss.getMapper(DynamicSQLMapper.class);
    List<Emp> empByCondition = mapper.getEmpByChoose(new Emp(null, "老李", 42, "", null));
    empByCondition.forEach(System.out::println);
  }

  @Test
  public void testDeleteMoreByArray() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DynamicSQLMapper mapper = ss.getMapper(DynamicSQLMapper.class);
    int result = mapper.deleteMoreByArray(new Integer[] {6,7,8});
    System.out.println(result);
  }

  @Test
  public void testInsertMoreByArray() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DynamicSQLMapper mapper = ss.getMapper(DynamicSQLMapper.class);
    Emp emp1 =  new Emp(null, "a1", 23, "男", "XXX@gmail.com");
    Emp emp2 =  new Emp(null, "a2", 23, "男", "XXX@gmail.com");
    Emp emp3 =  new Emp(null, "a3", 23, "男", "XXX@gmail.com");
    List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
    int result = mapper.insertMoreByList(emps);
    System.out.println(result);
  }

}
