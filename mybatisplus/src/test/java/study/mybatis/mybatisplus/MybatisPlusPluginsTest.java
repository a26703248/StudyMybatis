package study.mybatis.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import study.mybatis.mybatisplus.mapper.ProductMapper;
import study.mybatis.mybatisplus.mapper.UserMapper;
import study.mybatis.mybatisplus.pojo.Product;
import study.mybatis.mybatisplus.pojo.User;

@SpringBootTest
public class MybatisPlusPluginsTest {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ProductMapper productMapper;

  // page
  @Test
  public void testPage() {
    Page<User> page = new Page<>(2, 3);
    userMapper.selectPage(page, null);
    System.out.println(page.getRecords());
    System.out.println(page.getPages());
    System.out.println(page.getTotal());
    System.out.println(page.getSize());
    System.out.println(page.hasNext());
    System.out.println(page.hasPrevious());
  }

  // custom page
  @Test
  public void testPageVo() {
    Page<User> page = new Page<>();
    userMapper.selectPageVo(page, 20);
    System.out.println(page.getRecords());
    System.out.println(page.getPages());
    System.out.println(page.getTotal());
    System.out.println(page.getSize());
    System.out.println(page.hasNext());
    System.out.println(page.hasPrevious());
  }

  /*
   * 樂觀鎖情境:
   * 一件商品，成本价是80元，售价是100元。老板先是通知小李，说你去把商品价格增加50元。小
   * 李正在玩游戏，耽搁了一个小时。正好一个小时后，老板觉得商品价格增加到150元，价格太
   * 高，可能会影响销量。又通知小王，你把商品价格降低30元。
   * 此时，小李和小王同时操作商品后台系统。小李操作的时候，系统先取出商品价格100元；小王
   * 也在操作，取出的商品价格也是100元。小李将价格加了50元，并将100+50=150元存入了数据
   * 库；小王将商品减了30元，并将100-30=70元存入了数据库。是的，如果没有锁，小李的操作就
   * 完全被小王的覆盖了。
   * 现在商品价格是70元，比成本价低10元。几分钟后，这个商品很快出售了1千多件商品，老板亏1
   * 万多。
   *
   * 開啟步驟:
   * 1. 於 config 檔加上攔截器
   * interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
   * 2. 於物件中 version 屬性加上 @Version
   */
  @Test
  public void testProduct() {
    // 情境結果
    // 小李查詢商品價格
    Product li = productMapper.selectById(1L);
    System.out.println(li);
    // 小王查詢商品價格
    Product wang = productMapper.selectById(1L);
    System.out.println(wang);
    // 小李將商品價格 + 50
    li.setPrice(li.getPrice()+50);
    productMapper.updateById(li);
    // 小王將商品價格 - 30
    wang.setPrice(wang.getPrice()-30);
    int result = productMapper.updateById(wang);
    // 因更新時版本號搜尋不到,所以加上樂觀鎖小王不會更新成功,須在一次查詢後在更新
    if(result == 0){
      Product newProduct = productMapper.selectById(1);
      newProduct.setPrice(newProduct.getPrice() - 30);
      productMapper.updateById(newProduct);
    }
    // 老闆查詢產品價格
    Product boss = productMapper.selectById(1L);
    System.out.println(boss.getPrice());
  }

}
