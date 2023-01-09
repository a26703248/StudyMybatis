package study.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import study.mybatis.mappers.EmpMapper;
import study.mybatis.pojo.Emp;

public class PageHelperTest {

  private SqlSession ss;
  /*
   * limit, index, pageSize
   * index: 當前分頁的起始索引
   * pageSize: 每頁顯示多少筆
   * pageNum: 當前頁碼
   * index = (pageNum-1)*pageSize
   * 使用 mybatis 的分頁插件功能
   * 1. 需要在查詢功能前開啟分頁
   * PageHelper.startPage(int pageNum, int pageSize)
   * 2. 在查詢功能之後獲取相關訊息
   * PageInfo<Emp> pageInfo = new PageInfo<>(emps, 3);
   * emps: 分頁資料
   * 3:當前要顯示頁碼數量
   */

  @Test
  public void testPageHelper() {
    try {
      InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
      SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
      ss = ssf.openSession(true);
      EmpMapper mapper = ss.getMapper(EmpMapper.class);
      Page<Object> page = PageHelper.startPage(1, 4);
      List<Emp> emps = mapper.selectByExample(null);
      // 計算導航行分頁
      PageInfo<Emp> pageInfo = new PageInfo<>(emps, 3);
      // emps.forEach(System.out::println);
      System.out.println(pageInfo);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
