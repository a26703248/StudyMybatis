package study.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.Emp;

public interface DynamicSQLMapper {
  // 多條件查詢
  public List<Emp> getEmpByCondition(Emp emp);

  // choose,when,otherwise tag
  public List<Emp> getEmpByChoose(Emp emp);

  // foreach 批量刪除
  public int deleteMoreByArray(@Param("eids")Integer[] eids);

  // foreach 批量增加
  public int insertMoreByList(@Param("emps") List<Emp> emps);

}
