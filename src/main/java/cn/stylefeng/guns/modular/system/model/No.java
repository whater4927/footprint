package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 业务编号
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-28
 */
@TableName("sys_no")
public class No extends Model<No> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("org_id")
    private Integer orgId;
    private Integer year;
    private Integer month;
    @TableField("seq_no")
    private Integer seqNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "No{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", year=" + year +
        ", month=" + month +
        ", seqNo=" + seqNo +
        "}";
    }
}
