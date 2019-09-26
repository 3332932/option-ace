package com.cn.shrio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.jwt.entity.Role;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yshh44
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     *
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(@Param("userId") Long userId);
}
