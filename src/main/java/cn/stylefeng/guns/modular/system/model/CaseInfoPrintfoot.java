package cn.stylefeng.guns.modular.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import lombok.Data;

/**
 * <p>
 * 案件基本信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-31
 */
@Data
public class CaseInfoPrintfoot {
	 /**
     * 足迹编号
     */
    @TableId("fp_no")
    private String fpNo;
    /**
     * 案件编号
     */
    @TableField("case_no")
    private String caseNo;
    /**
     * 嫌疑人编号
     */
    @TableField("cs_no")
    private String csNo;
    /**
     * 足迹遗留部位
     */
    private String position;
    /**
     * 足迹遗留方式
     */
    @TableField("legacy_mode")
    private String legacyMode;
    /**
     * 足迹提取方式
     */
    @TableField("extraction_method")
    private String extractionMethod;
    /**
     * 原始照片
     */
    @TableField("original_img")
    private String originalImg;
    /**
     * 处理后的照片
     */
    @TableField("new_img")
    private String newImg;
    /**
     * 长
     */
    private Integer length;
    /**
     * 宽
     */
    private Integer width;

    /**
     * 案件状态
     */
    @TableField("case_state")
    private String caseState;
    /**
     * 案发时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("case_tm")
    private Date caseTm;
    /**
     * 案发地点
     */
    @TableField("case_address")
    private String caseAddress;
    /**
     * 所属单位
     */
    private String unit;
    /**
     * 简要案情
     */
    @TableField("case_desc")
    private String caseDesc;
    /**
     * 案件类别
     */
    @TableField("case_type")
    private String caseType;
    /**
     * 侵入方式
     */
    @TableField("intrusion_mode")
    private String intrusionMode;
    /**
     * 被盗物品
     */
    @TableField("stolen_goods")
    private String stolenGoods;
    /**
     * 作案人数
     */
    @TableField("crimes_person_num")
    private String crimesPersonNum;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否删除
     */
    @TableField("del_status")
    private String delStatus;
    /**
     * 最后修改人
     */
    @TableField("upd_user_id")
    private String updUserId;
    /**
     * 最后修改机构
     */
    @TableField("upd_org_id")
    private String updOrgId;
    /**
     * 最后修改时间
     */
    @TableField("upd_tm")
    private Date updTm;
    /**
     * 创建人
     */
    @TableField("crt_user_id")
    private String crtUserId;
    /**
     * 创建机构id
     */
    @TableField("crt_org_id")
    private String crtOrgId;
    /**
     * 创建时间
     */
    @TableField("crt_tm")
    private Date crtTm;


}
