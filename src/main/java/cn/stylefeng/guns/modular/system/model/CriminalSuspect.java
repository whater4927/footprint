package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 嫌疑人信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-31
 */
@TableName("fp_criminal_suspect")
public class CriminalSuspect extends Model<CriminalSuspect> {

    private static final long serialVersionUID = 1L;

    /**
     * 嫌疑人编号
     */
    @TableId("cs_no")
    private String csNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身高
     */
    private Integer heigh;
    /**
     * 籍贯
     */
    private String nation;
    /**
     * 身份证号
     */
    @TableField("id_no")
    private String idNo;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 居住地
     */
    private String address;
    /**
     * 涉案类别
     */
    @TableField("cs_type")
    private String csType;
    /**
     * 抓获日期
     */
    @TableField("grasp_date")
    private Date graspDate;
    /**
     * 抓获单位
     */
    @TableField("grasp_unit")
    private String graspUnit;
    /**
     * 录入人
     */
    @TableField("input_user")
    private Integer inputUser;
    /**
     * 备注
     */
    private String remark;
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


    public String getCsNo() {
        return csNo;
    }

    public void setCsNo(String csNo) {
        this.csNo = csNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getHeigh() {
        return heigh;
    }

    public void setHeigh(Integer heigh) {
        this.heigh = heigh;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCsType() {
        return csType;
    }

    public void setCsType(String csType) {
        this.csType = csType;
    }

    public Date getGraspDate() {
        return graspDate;
    }

    public void setGraspDate(Date graspDate) {
        this.graspDate = graspDate;
    }

    public String getGraspUnit() {
        return graspUnit;
    }

    public void setGraspUnit(String graspUnit) {
        this.graspUnit = graspUnit;
    }

    public Integer getInputUser() {
        return inputUser;
    }

    public void setInputUser(Integer inputUser) {
        this.inputUser = inputUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return this.csNo;
    }

    @Override
    public String toString() {
        return "CriminalSuspect{" +
        ", csNo=" + csNo +
        ", name=" + name +
        ", sex=" + sex +
        ", heigh=" + heigh +
        ", nation=" + nation +
        ", idNo=" + idNo +
        ", birthday=" + birthday +
        ", address=" + address +
        ", csType=" + csType +
        ", graspDate=" + graspDate +
        ", graspUnit=" + graspUnit +
        ", inputUser=" + inputUser +
        ", remark=" + remark +
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
