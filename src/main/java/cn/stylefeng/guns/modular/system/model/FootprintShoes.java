package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 足迹鞋样关联
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-03
 */
@TableName("fp_footprint_shoes")
public class FootprintShoes extends Model<FootprintShoes> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 鞋样id
     */
    @TableField("shoes_id")
    private String shoesId;
    /**
     * 足迹编号
     */
    @TableField("fp_no")
    private String fpNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShoesId() {
        return shoesId;
    }

    public void setShoesId(String shoesId) {
        this.shoesId = shoesId;
    }

    public String getFpNo() {
        return fpNo;
    }

    public void setFpNo(String fpNo) {
        this.fpNo = fpNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FootprintShoes{" +
        ", id=" + id +
        ", shoesId=" + shoesId +
        ", fpNo=" + fpNo +
        "}";
    }
}
