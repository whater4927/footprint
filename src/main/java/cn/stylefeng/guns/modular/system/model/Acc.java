package cn.stylefeng.guns.modular.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 账目信息
 * </p>
 *
 * @author whater
 * @since 2019-04-17
 */
@TableName("gs_acc")
public class Acc extends Model<Acc> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 缴费主题ID
     */
    @TableField("object_id")
    private String objectId;
    /**
     * 类型:个人、单位
     */
    private String type;
    /**
     * 缴费账户类型
     */
    @TableField("pay_acc_type")
    private String payAccType;
    /**
     * 缴费账户编号
     */
    @TableField("apy_acc_num")
    private String apyAccNum;
    /**
     * 缴费日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("pay_dt")
    private Date payDt;
    /**
     * 收费账户类型
     */
    @TableField("acc_type")
    private String accType;
    /**
     * 收费账户编号
     */
    @TableField("acc_num")
    private String accNum;
    /**
     * 业务办理人
     */
    @TableField("oper_id")
    private String operId;
    /**
     * 业务办理机构
     */
    @TableField("oper_org_id")
    private String operOrgId;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("crt_tm")
    private Date crtTm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayAccType() {
        return payAccType;
    }

    public void setPayAccType(String payAccType) {
        this.payAccType = payAccType;
    }

    public String getApyAccNum() {
        return apyAccNum;
    }

    public void setApyAccNum(String apyAccNum) {
        this.apyAccNum = apyAccNum;
    }

    public Date getPayDt() {
        return payDt;
    }

    public void setPayDt(Date payDt) {
        this.payDt = payDt;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getOperOrgId() {
        return operOrgId;
    }

    public void setOperOrgId(String operOrgId) {
        this.operOrgId = operOrgId;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "Acc{" +
        ", id=" + id +
        ", objectId=" + objectId +
        ", type=" + type +
        ", payAccType=" + payAccType +
        ", apyAccNum=" + apyAccNum +
        ", payDt=" + payDt +
        ", accType=" + accType +
        ", accNum=" + accNum +
        ", operId=" + operId +
        ", operOrgId=" + operOrgId +
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
