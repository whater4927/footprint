package cn.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 单位信息
 * </p>
 *
 * @author whater
 * @since 2019-04-17
 */
@TableName("gs_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * 单位参保编号ID
     */
    private String id;
    /**
     * 单位名称
     */
    private String name;
    /**
     * 组织机构编码
     */
    @TableField("org_code")
    private String orgCode;
    /**
     * 单位地址
     */
    private String address;
    /**
     * 单位注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("reg_date")
    private Date regDate;
    /**
     * 单位注册资金
     */
    @TableField("reg_amt")
    private BigDecimal regAmt;
    /**
     * 单位法人
     */
    @TableField("legal_person")
    private String legalPerson;
    /**
     * 法人电话号码
     */
    @TableField("legal_phone")
    private String legalPhone;
    /**
     * 单位参保状态，单位是否被注销作用
     */
    @TableField("join_status")
    private String joinStatus;
    /**
     * 参保时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("join_tm")
    private Date joinTm;
    /**
     * 个人缴纳比例
     */
    @TableField("emp_ration")
    private BigDecimal empRation;
    /**
     * 单位缴纳比例
     */
    @TableField("com_ration")
    private BigDecimal comRation;
    /**
     * 账户类型
     */
    @TableField("acc_type")
    private String accType;
    /**
     * 缴费账户编号
     */
    @TableField("acc_no")
    private String accNo;
    /**
     * 用途
     */
    @TableField("acc_use_type")
    private String accUseType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public BigDecimal getRegAmt() {
        return regAmt;
    }

    public void setRegAmt(BigDecimal regAmt) {
        this.regAmt = regAmt;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Date getJoinTm() {
        return joinTm;
    }

    public void setJoinTm(Date joinTm) {
        this.joinTm = joinTm;
    }

    public BigDecimal getEmpRation() {
        return empRation;
    }

    public void setEmpRation(BigDecimal empRation) {
        this.empRation = empRation;
    }

    public BigDecimal getComRation() {
        return comRation;
    }

    public void setComRation(BigDecimal comRation) {
        this.comRation = comRation;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccUseType() {
        return accUseType;
    }

    public void setAccUseType(String accUseType) {
        this.accUseType = accUseType;
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
        return "Company{" +
        ", id=" + id +
        ", name=" + name +
        ", orgCode=" + orgCode +
        ", address=" + address +
        ", regDate=" + regDate +
        ", regAmt=" + regAmt +
        ", legalPerson=" + legalPerson +
        ", legalPhone=" + legalPhone +
        ", joinStatus=" + joinStatus +
        ", joinTm=" + joinTm +
        ", empRation=" + empRation +
        ", comRation=" + comRation +
        ", accType=" + accType +
        ", accNo=" + accNo +
        ", accUseType=" + accUseType +
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
