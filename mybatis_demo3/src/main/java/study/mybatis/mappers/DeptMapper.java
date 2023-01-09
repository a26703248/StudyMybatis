package study.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.Dept;

public interface DeptMapper {


  // 分開查詢第二步
  public Dept getEmpAndDeptStepTwo(@Param("did")Integer did);

  // 查詢部門及該部門員工資訊(join)
  public Dept getDeptAndEmps(@Param("did")Integer did);

  // 查詢部門及該部門員工資訊(分開查詢)
  public Dept getDeptAndEmpByStepOne(@Param("did")Integer did);

}
