package com.cn.user.service.impl;

import com.cn.user.entity.Permission;
import com.cn.user.mapper.PermissionMapper;
import com.cn.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
