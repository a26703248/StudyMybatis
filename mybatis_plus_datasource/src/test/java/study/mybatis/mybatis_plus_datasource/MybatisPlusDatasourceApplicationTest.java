package study.mybatis.mybatis_plus_datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import study.mybatis.mybatis_plus_datasource.service.ProductService;
import study.mybatis.mybatis_plus_datasource.service.UserService;

@SpringBootTest
public class MybatisPlusDatasourceApplicationTest {

  @Autowired
  private UserService userService;

  @Autowired
  private ProductService productService;

  @Test
  public void test() {
    System.out.println(userService.getById(1));
    System.out.println(productService.getById(1));
  }

}


