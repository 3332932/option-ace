package com.cn.easyRpt.entity.dto;

import com.cn.easyRpt.entity.Cols;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ColsDto {
    List<Cols> deleteRows = new ArrayList<>();
    List<Cols> insertRows = new ArrayList<>();
    List<Cols> updateRows = new ArrayList<>();
}
