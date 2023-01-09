package study.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.CacheMapper;
import study.mybatis.pojo.Emp;
import study.mybatis.utils.SqlSessionUtils;

public class CacheMapperTest {
  /*
   * 一級快取(預設開啟): 在同一個 SqlSession 相同查詢條件,會將查詢結果存儲至快取中
   * 一級快取失效四種情況:
   * 1. 不同 sqlSession 相同查詢條件
   * 2. 相同 SqlSession 不同查詢條件
   * 3. 相同 SqlSession 期間有 "增刪改" 動作(中途有增刪改動作將會清空所有快取)
   * 4. 相同 SqlSession 期間手動清空快取
   */
  @Test
  public void testGetEmpByEid() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    CacheMapper mapper1 = ss.getMapper(CacheMapper.class);
    Emp empByEid = mapper1.getEmpByEid(2);
    System.out.println(empByEid);
    // 1. 不同 sqlSession
    // ss = SqlSessionUtils.getSqlSession();
    // CacheMapper mapper2 = ss.getMapper(CacheMapper.class);
    // Emp empByEid2 = mapper2.getEmpByEid(2);
    // System.out.println(empByEid2);

    // 2. 不同條件
    // Emp empByEid2 = mapper1.getEmpByEid(4);
    // System.out.println(empByEid2);


    // 3. 中途有"增刪修"改動作
    // mapper1.insertEmp(new Emp(null, "abc", 23, "男", "XX@gmail.com"));
    // Emp empByEid2 = mapper1.getEmpByEid(2);
    // System.out.println(empByEid2);

    // 4. 手動清除
    ss.clearCache();
    Emp empByEid2 = mapper1.getEmpByEid(2);
    System.out.println(empByEid2);
  }

}
