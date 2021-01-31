package com.mituh.vueblog.service.impl;

import com.mituh.vueblog.entity.Blog;
import com.mituh.vueblog.mapper.BlogMapper;
import com.mituh.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
