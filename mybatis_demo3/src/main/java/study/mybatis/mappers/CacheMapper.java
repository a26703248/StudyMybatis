package study.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import study.mybatis.pojo.Emp;

public interface CacheMapper {

  public Emp getEmpByEid(@Param("eid")Integer eid);

  public void insertEmp(Emp emp);

}
