package study.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.Dept;

public interface DeptMapper {


  // 分開查詢第二步
  public Dept getEmpAndDept(@Param("did")Integer did);

}
