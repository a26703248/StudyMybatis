package study.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
   *
   * 二級快取(預設關閉): 對有設定 cache tag 的 mapper xml 中所有查詢結果做快取
   * 開啟設定:
   * 1. 在主要設定檔 setting 中設定(預設開啟) <setting name="cacheEnabled" value="true" />
   * 2. 在要快取的 mapper xml 檔中加入 <cache></cache>
   * 3. 在 session 關閉(close)或提交(commit)才會存入快取(在此之前都將保存至一級快取中)
   * 4. 查詢結果所對應物件需實作 Serializable 介面
   * 失效一種情況
   * 1. 兩次查詢期間有 "增刪改" 動作(中途有增刪改動作將會清空所有快取)
   *
   * 快取查詢順序
   * 會先查找二級快取是否有相同查詢條件的查詢紀錄,
   * 若未有則會在各 SqlSession 中尋找是否有,
   * 最後若都無查詢到則會在資料庫做查詢並存入快取
   *
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

  @Test
  public void testTwoCache() {
    try {
      InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
      SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
      SqlSession ss1 = ssf.openSession(true);
      CacheMapper mapper1 = ss1.getMapper(CacheMapper.class);
      System.out.println(mapper1.getEmpByEid(1));
      ss1.close();
      SqlSession ss2 = ssf.openSession(true);
      CacheMapper mapper2 = ss2.getMapper(CacheMapper.class);
      System.out.println(mapper2.getEmpByEid(1));
      ss2.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
