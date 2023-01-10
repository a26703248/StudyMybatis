package study.mybatis.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;
import study.mybatis.mybatisplus.enums.SexEnum;

@Data
// @TableName("t_user") // 設定對應資料表名稱(預設為物件名稱)
public class User {

  // 因 mybatis plus 透過雪花算法生成 id 所以必須使用 Long 型態
  /*
   *  TableId(
   *     value="mybatis plus 主鍵對應到資料表的欄位名稱", type
   *     type="mybatis plus 主鍵生成規則"
   *          AUTO: 依照資料庫自動增加(若未設定自動遞增則會拋出 Exception)
   *          ASSIGN_ID: 依照 mybatis plus 雪花演算法生成寫入
   *  )
   */
  // @TableId(value="uid", type=IdType.AUTO)
  // @TableId(value="uid", type=IdType.ASSIGN_ID)
  @TableId("uid")
  private Long id;

  // 可自行選擇對應欄位
  @TableField("user_name")
  private String name;

  private Integer age;

  private String email;

  private SexEnum sex;

  // 可定義是否為註銷資料
  @TableLogic
  private Integer isDeleted;
}
