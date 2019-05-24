package cn.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 生成财务信息
 * </p>
 *
 * @author whater
 * @since 2019-04-26
 */
@TableName("gs_gener_acc")
public class GenerAcc extends Model<GenerAcc> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 单位ID
     */
    @TableField("com_id")
    private String comId;
    /**
     * 日期：年月
     */
    @TableField("gen_dt")
    private String genDt;
    /**
     * 总金额
     */
    @TableField("total_amt")
    private BigDecimal totalAmt;
    /**
     * 员工人数
     */
    @TableField("emp_count")
    private Integer empCount;
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

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getGenDt() {
        return genDt;
    }

    public void setGenDt(String genDt) {
        this.genDt = genDt;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
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
        return "GenerAcc{" +
        ", id=" + id +
        ", comId=" + comId +
        ", genDt=" + genDt +
        ", totalAmt=" + totalAmt +
        ", empCount=" + empCount +
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
