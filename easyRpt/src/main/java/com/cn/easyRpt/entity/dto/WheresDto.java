package com.cn.easyRpt.entity.dto;

import com.cn.easyRpt.entity.WhereCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WheresDto {
    List<WhereCondition> deleteRows = new ArrayList<>();
    List<WhereCondition> insertRows = new ArrayList<>();
    List<WhereCondition> updateRows = new ArrayList<>();
}
