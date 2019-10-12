package com.cn.dynamic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.cn.dynamic.entity.DmExpressB30;
import com.cn.dynamic.mapper.DmExpressB30Mapper;
import com.cn.dynamic.service.DmExpressB30Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-10-09
 */
@Service
@DS("oracle")
public class DmExpressB30ServiceImpl extends ServiceImpl<DmExpressB30Mapper, DmExpressB30> implements DmExpressB30Service {

}
