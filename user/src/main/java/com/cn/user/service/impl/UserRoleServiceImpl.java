package com.cn.user.service.impl;

import com.cn.user.entity.UserRole;
import com.cn.user.mapper.UserRoleMapper;
import com.cn.user.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与权限关系表 服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
