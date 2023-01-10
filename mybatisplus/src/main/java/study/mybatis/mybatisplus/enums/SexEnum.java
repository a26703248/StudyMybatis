package study.mybatis.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

@Getter
public enum SexEnum {
  MALE(1, "男"),
  FEMALE(2, "女");

  @EnumValue // 將註解標示的值存到資料表中
  private Integer sex;
  private String sexName;
  private SexEnum(Integer sex, String sexName) {
    this.sex = sex;
    this.sexName = sexName;
  }

}
