package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 案件基本信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-31
 */
@TableName("fp_case_info")
public class CaseInfo extends Model<CaseInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件编号
     */
    @TableId("case_no")
    private String caseNo;
    /**
     * 案件状态
     */
    @TableField("case_state")
    private String caseState;
    /**
     * 案发时间
     */
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


    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    public Date getCaseTm() {
        return caseTm;
    }

    public void setCaseTm(Date caseTm) {
        this.caseTm = caseTm;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getIntrusionMode() {
        return intrusionMode;
    }

    public void setIntrusionMode(String intrusionMode) {
        this.intrusionMode = intrusionMode;
    }

    public String getStolenGoods() {
        return stolenGoods;
    }

    public void setStolenGoods(String stolenGoods) {
        this.stolenGoods = stolenGoods;
    }

    public String getCrimesPersonNum() {
        return crimesPersonNum;
    }

    public void setCrimesPersonNum(String crimesPersonNum) {
        this.crimesPersonNum = crimesPersonNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public String getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(String updUserId) {
        this.updUserId = updUserId;
    }

    public String getUpdOrgId() {
        return updOrgId;
    }

    public void setUpdOrgId(String updOrgId) {
        this.updOrgId = updOrgId;
    }

    public Date getUpdTm() {
        return updTm;
    }

    public void setUpdTm(Date updTm) {
        this.updTm = updTm;
    }

    public String getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(String crtUserId) {
        this.crtUserId = crtUserId;
    }

    public String getCrtOrgId() {
        return crtOrgId;
    }

    public void setCrtOrgId(String crtOrgId) {
        this.crtOrgId = crtOrgId;
    }

    public Date getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(Date crtTm) {
        this.crtTm = crtTm;
    }

    @Override
    protected Serializable pkVal() {
        return this.caseNo;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
        ", caseNo=" + caseNo +
        ", caseState=" + caseState +
        ", caseTm=" + caseTm +
        ", caseAddress=" + caseAddress +
        ", unit=" + unit +
        ", caseDesc=" + caseDesc +
        ", caseType=" + caseType +
        ", intrusionMode=" + intrusionMode +
        ", stolenGoods=" + stolenGoods +
        ", crimesPersonNum=" + crimesPersonNum +
        ", status=" + status +
        ", delStatus=" + delStatus +
        ", updUserId=" + updUserId +
        ", updOrgId=" + updOrgId +
        ", updTm=" + updTm +
        ", crtUserId=" + crtUserId +
        ", crtOrgId=" + crtOrgId +
        ", crtTm=" + crtTm +
        "}";
    }
}
