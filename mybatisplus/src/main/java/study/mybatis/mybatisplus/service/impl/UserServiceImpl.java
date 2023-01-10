package study.mybatis.mybatisplus.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import study.mybatis.mybatisplus.mapper.UserMapper;
import study.mybatis.mybatisplus.pojo.User;
import study.mybatis.mybatisplus.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {

}
