package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 足迹信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-31
 */
@TableName("fp_footprint")
public class Footprint extends Model<Footprint> {

    private static final long serialVersionUID = 1L;

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


    public String getFpNo() {
        return fpNo;
    }

    public void setFpNo(String fpNo) {
        this.fpNo = fpNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCsNo() {
        return csNo;
    }

    public void setCsNo(String csNo) {
        this.csNo = csNo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLegacyMode() {
        return legacyMode;
    }

    public void setLegacyMode(String legacyMode) {
        this.legacyMode = legacyMode;
    }

    public String getExtractionMethod() {
        return extractionMethod;
    }

    public void setExtractionMethod(String extractionMethod) {
        this.extractionMethod = extractionMethod;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    public String getNewImg() {
        return newImg;
    }

    public void setNewImg(String newImg) {
        this.newImg = newImg;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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
        return this.fpNo;
    }

    @Override
    public String toString() {
        return "Footprint{" +
        ", fpNo=" + fpNo +
        ", caseNo=" + caseNo +
        ", csNo=" + csNo +
        ", position=" + position +
        ", legacyMode=" + legacyMode +
        ", extractionMethod=" + extractionMethod +
        ", originalImg=" + originalImg +
        ", newImg=" + newImg +
        ", length=" + length +
        ", width=" + width +
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
