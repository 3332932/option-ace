package com.cn.service.impl;

import com.cn.entity.Permission;
import com.cn.mapper.PermissionMapper;
import com.cn.service.PermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
