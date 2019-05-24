package cn.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 收缴记录明细
 * </p>
 *
 * @author whater
 * @since 2019-04-26
 */
@TableName("gs_acc_emp")
public class AccEmp extends Model<AccEmp> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 人员ID
     */
    @TableField("emp_id")
    private String empId;
    /**
     * 单位ID
     */
    @TableField("com_id")
    private String comId;
    /**
     * 日期
     */
    @TableField("pay_dt")
    private String payDt;
    /**
     * 个人缴纳金额
     */
    @TableField("emp_amt")
    private BigDecimal empAmt;
    /**
     * 单位缴纳金额
     */
    @TableField("com_amt")
    private BigDecimal comAmt;
    /**
     * 缴纳状态
     */
    @TableField("pay_status")
    private String payStatus;
    /**
     * 业务办理时间
     */
    @TableField("deal_tm")
    private Date dealTm;
    /**
     * 办理业务的用户ID
     */
    @TableField("oper_id")
    private String operId;
    /**
     * 用户的机构ID
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public BigDecimal getEmpAmt() {
        return empAmt;
    }

    public void setEmpAmt(BigDecimal empAmt) {
        this.empAmt = empAmt;
    }

    public BigDecimal getComAmt() {
        return comAmt;
    }

    public void setComAmt(BigDecimal comAmt) {
        this.comAmt = comAmt;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getDealTm() {
        return dealTm;
    }

    public void setDealTm(Date dealTm) {
        this.dealTm = dealTm;
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

    

	public String getPayDt() {
		return payDt;
	}

	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}

	@Override
	public String toString() {
		return "AccEmp [id=" + id + ", empId=" + empId + ", comId=" + comId + ", payDt=" + payDt + ", empAmt=" + empAmt
				+ ", comAmt=" + comAmt + ", payStatus=" + payStatus + ", dealTm=" + dealTm + ", operId=" + operId
				+ ", operOrgId=" + operOrgId + ", status=" + status + ", delStatus=" + delStatus + ", updUserId="
				+ updUserId + ", updOrgId=" + updOrgId + ", updTm=" + updTm + ", crtUserId=" + crtUserId + ", crtOrgId="
				+ crtOrgId + ", crtTm=" + crtTm + "]";
	}
    
    
}
