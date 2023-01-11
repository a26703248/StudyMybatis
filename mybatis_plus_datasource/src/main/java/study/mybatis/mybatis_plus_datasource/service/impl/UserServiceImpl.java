package study.mybatis.mybatis_plus_datasource.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import study.mybatis.mybatis_plus_datasource.mapper.UserMapper;
import study.mybatis.mybatis_plus_datasource.pojo.User;
import study.mybatis.mybatis_plus_datasource.service.UserService;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
