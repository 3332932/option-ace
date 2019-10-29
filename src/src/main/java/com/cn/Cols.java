package com.cn;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Cols {

    /**
     * id : 1141557777739489447
     * companyId : 1
     * rptNo : 1
     * columnGroupName :
     * columnOrder : 0
     * columnName : 1
     * columnValue : password
     * columnType : text
     * columnTypeMap : null
     * columnTypeData : null
     * columnTypeDataType : null
     * columnWidth : 120
     * columnIsSubtotal : 0
     * columnIsTotal : 0
     * status : 1
     * remarks : null
     * creator : admin:自动
     * createTime : 2019-10-25 11:29:16
     * modifier : null
     * modifyTime : null
     * delTag : 0
     * updateTime : 2019-10-25 11:29:17
     */

    private Integer id;
    private String companyId;
    private String rptNo;
    private String columnGroupName;
    private int columnOrder;
    private String columnName;
    private String columnValue;
    private String columnType;
    private Object columnTypeMap;
    private Object columnTypeData;
    private Object columnTypeDataType;
    private int columnWidth;
    private int columnIsSubtotal;
    private int columnIsTotal;
    private int status;
    private Object remarks;
    private String creator;
    private Date createTime;
    private Object modifier;
    private Date modifyTime;
    private String delTag;
    private Date updateTime;
}
