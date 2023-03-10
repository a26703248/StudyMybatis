package study.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import study.mybatis.mappers.DeptMapper;
import study.mybatis.mappers.EmpMapper;
import study.mybatis.pojo.Dept;
import study.mybatis.pojo.Emp;
import study.mybatis.utils.SqlSessionUtils;

public class ResultMapTest {

  /*
   * 解決資料表與物件名稱不相同問題
   * 1. 資料表欄位別名 table_column as target_name
   * 2. 設定 mybatis-config.xml(mapUnderscoreToCamelCase:true)
   * 3. mapper.xml 中 resultMap
   */
  @Test
  public void testGetAllEmp() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    EmpMapper mapper = ss.getMapper(EmpMapper.class);
    List<Emp> emps = mapper.getAllEmp();
    emps.forEach(System.out::println);
  }

  /*
   * 關聯對應
   * 多對一:
   * 1. resultMap tag 和資料表 join 對應
   * 2. association 和資料表 join 來處理多對一關聯
   * 3. association 和分開查詢來處理多對一關聯
   * 一對多:
   * 1. collection 和資料表 join 來處理一對多關聯
   * 2. collection 和分開查詢來處理一對多關聯
   */
  @Test
  public void testGetEmpAndDept() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    EmpMapper mapper = ss.getMapper(EmpMapper.class);
    Emp emp = mapper.getEmpAndDept(2);
    System.out.println(emp);
  }

  @Test
  public void testGetEmpAndDeptByStepOne() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    EmpMapper mapper = ss.getMapper(EmpMapper.class);
    Emp emp = mapper.getEmpAndDeptByStepOne(2);
    System.out.println(emp.getEmpName());
    System.out.println("+++++++++++++++++++++++++++++");
    System.out.println(emp.getDept());
  }

  @Test
  public void testGetDeptAndEmp() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DeptMapper mapper = ss.getMapper(DeptMapper.class);
    Dept deptAndEmps = mapper.getDeptAndEmps(2);
    System.out.println(deptAndEmps);
  }

  @Test
  public void testGetDeptAndEmpByStep() {
    SqlSession ss = SqlSessionUtils.getSqlSession();
    DeptMapper mapper = ss.getMapper(DeptMapper.class);
    Dept deptAndEmpByStepOne = mapper.getDeptAndEmpByStepOne(2);
    System.out.println(deptAndEmpByStepOne.getDeptName());
  }

}
