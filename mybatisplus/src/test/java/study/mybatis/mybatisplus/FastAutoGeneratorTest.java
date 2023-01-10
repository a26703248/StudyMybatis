package study.mybatis.mybatisplus;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

@SpringBootTest
public class FastAutoGeneratorTest {

  @Test
  public void test() {
    FastAutoGenerator.create("jdbc:mysql://192.168.0.200:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false", "developer", "a0909007892")
        .globalConfig(builder -> {
          builder.author("howard") // 设置作者
              // .enableSwagger() // 开启 swagger 模式
              .fileOverride() // 覆盖已生成文件
              .outputDir("/home/a0909007892/StudyMybatis/mybatisplus/"); // 指定输出目录
        })
        .packageConfig(builder -> {
          builder.parent("study.mybatis") // 设置父包名
              .moduleName("mybatisplus") // 设置父包模块名
              .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/home/a0909007892/StudyMybatis/mybatisplus/")); // 设置mapperXml生成路径
        })
        .strategyConfig(builder -> {
          builder.addInclude("t_user") // 设置需要生成的表名
              .addTablePrefix("t_", "c_"); // 设置过滤表前缀
        })
        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        .execute();
  }

}
