package com.cn;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Where {

    /**
     * id : 1
     * companyId : 1
     * rptNo : 1
     * columnOrder : 0
     * whereKey : divide_id
     * whereRequire : 0
     * whereOrder : 0
     * whereOperator : eq
     * whereReplaceType : 1
     * whereValue : 编号
     * whereType : text
     * whereTypeMap :
     * whereTypeData :
     * whereTypeDataType : null
     * status : 1
     * remarks : null
     * creator : liu.qiang
     * createTime : 2019-06-17 07:11:46
     * modifier : admin:自动
     * modifyTime : 2019-10-25 11:29:16
     */

    private Integer id;
    private String companyId;
    private String rptNo;
    private int columnOrder;
    private String whereKey;
    private int whereRequire;
    private int whereOrder;
    private String whereOperator;
    private String whereReplaceType;
    private String whereValue;
    private String whereType;
    private String whereTypeMap;
    private String whereTypeData;
    private Object whereTypeDataType;
    private int status;
    private Object remarks;
    private String creator;
    private Date createTime;
    private String modifier;
    private Date modifyTime;
}
