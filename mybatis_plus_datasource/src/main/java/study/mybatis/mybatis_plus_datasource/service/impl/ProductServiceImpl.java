package study.mybatis.mybatis_plus_datasource.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import study.mybatis.mybatis_plus_datasource.mapper.ProductMapper;
import study.mybatis.mybatis_plus_datasource.pojo.Product;
import study.mybatis.mybatis_plus_datasource.service.ProductService;

@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
