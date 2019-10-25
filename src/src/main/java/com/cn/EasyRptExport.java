package com.cn;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class EasyRptExport {

    /**
     * id : 1
     * companyId : 1
     * rptNo : 1
     * rptName : 导出库存数据
     * rptUrl : /petrel-easyrpt-lmp-api/easyRpt/page?sort.create_time=desc
     * selectSql : SELECT * FROM bl_purchase where 1=1
     * sqlGroupFields :
     * remarks : 1
     * creator : liu.qiang
     * createTime : 2019-06-20 07:04:47
     * modifier : admin:自动
     * modifyTime : 2019-08-05 10:40:04
     * delTag : 0
     * updateTime : 2019-08-05 10:00:11
     */
    /**
     * 主键
     */
    private Integer id;
    /**
     * companyId
     */
    private String companyId;
    /**
     * companyId
     */
    private String rptNo;
    /**
     * companyId
     */
    private String rptName;
    /**
     * companyId
     */
    private String rptUrl;
    /**
     * companyId
     */
    private String selectSql;
    /**
     * companyId
     */
    private String sqlGroupFields;
    /**
     * companyId
     */
    private String remarks;
    /**
     * companyId
     */
    private String creator;
    /**
     * companyId
     */
    private Date createTime;
    /**
     * companyId
     */
    private String modifier;
    /**
     * companyId
     */
    private Date modifyTime;
    /**
     * companyId
     */
    private String delTag;
    /**
     * companyId
     */
    private Date updateTime;

    private List<String> ORe;
}
