package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 串并案件关联案件
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-31
 */
@TableName("fp_case_relation_detail")
public class CaseRelationDetail extends Model<CaseRelationDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 串并案件编号
     */
    @TableField("relation_no")
    private String relationNo;
    /**
     * 案件编号
     */
    @TableField("case_no")
    private String caseNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelationNo() {
        return relationNo;
    }

    public void setRelationNo(String relationNo) {
        this.relationNo = relationNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CaseRelationDetail{" +
        ", id=" + id +
        ", relationNo=" + relationNo +
        ", caseNo=" + caseNo +
        "}";
    }
}
