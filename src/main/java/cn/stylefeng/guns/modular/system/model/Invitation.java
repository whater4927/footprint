package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 发帖信息
 * </p>
 *
 * @author whater
 * @since 2019-05-14
 */
@TableName("gs_invitation")
public class Invitation extends Model<Invitation> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 人员ID
     */
    @TableField("user_id")
    private String userId;
    /**
     * 单位ID
     */
    private String title;
    /**
     * 日期
     */
    private String content;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Invitation{" +
        ", id=" + id +
        ", userId=" + userId +
        ", title=" + title +
        ", content=" + content +
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
