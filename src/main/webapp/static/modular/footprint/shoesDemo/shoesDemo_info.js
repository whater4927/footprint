/**
 * 初始化鞋厂鞋样详情对话框
 */
var ShoesDemoInfoDlg = {
    shoesDemoInfoData : {},
    validateFields:{
    	brand:{
    		validators: {
                notEmpty: {
                    message: '品牌不能为空'
                }
            }
    	}
    }
};

/**
 * 清除数据
 */
ShoesDemoInfoDlg.clearData = function() {
    this.shoesDemoInfoData = {};
}
ShoesDemoInfoDlg.validate = function () {
    $('#form').data("bootstrapValidator").resetForm();
    $('#form').bootstrapValidator('validate');
    return $("#form").data('bootstrapValidator').isValid();
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShoesDemoInfoDlg.set = function(key, val) {
    this.shoesDemoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShoesDemoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShoesDemoInfoDlg.close = function() {
    parent.layer.close(window.parent.ShoesDemo.layerIndex);
}
function formatterImage(value,row,index){ 
	var html = "<img src='/kaptcha/"+value+"' width='200px' height='200px' class='img-thumbnail' onclick='openImage(\""+value+"\")'>";
	return html;
}
/**
 * 收集数据
 */
ShoesDemoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('brand')
    .set('url')
    .set('length')
    .set('width')
    .set('remark')
    .set('inputUser')
    .set('status')
    .set('delStatus')
    .set('updUserId')
    .set('updOrgId')
    .set('updTm')
    .set('crtUserId')
    .set('crtOrgId')
    .set('crtTm');
}

/**
 * 提交添加
 */
ShoesDemoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shoesDemo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ShoesDemo.table.refresh();
        ShoesDemoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shoesDemoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShoesDemoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shoesDemo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ShoesDemo.table.refresh();
        ShoesDemoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shoesDemoInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("form", ShoesDemoInfoDlg.validateFields);
});
