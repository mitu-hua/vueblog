package com.mituh.vueblog.service.impl;

import com.mituh.vueblog.entity.User;
import com.mituh.vueblog.mapper.UserMapper;
import com.mituh.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hzz
 * @since 2020-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
