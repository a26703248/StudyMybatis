package study.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.Emp;

public interface EmpMapper {

  /*
   * 查詢所有員工訊息
   */
  List<Emp> getAllEmp();

  /*
   * 查詢員工及員工部分
   */
  Emp getEmpAndDept(@Param("eid")Integer eid);

  // 分開查詢
  Emp getEmpAndDeptByStepOne(@Param("eid")Integer eid);
}
