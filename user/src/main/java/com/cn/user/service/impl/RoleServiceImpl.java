package com.cn.user.service.impl;

import com.cn.user.entity.Role;
import com.cn.user.mapper.RoleMapper;
import com.cn.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
