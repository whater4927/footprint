/**
 * 初始化串并案件详情对话框
 */
var CaseRelationInfoDlg = {
    caseRelationInfoData : {},
    validateFields:{
    	relationName:{
    		validators: {
                notEmpty: {
                    message: '串案名称不能为空'
                }
            }
    	},
    	relationReason:{
    		validators: {
                notEmpty: {
                    message: '串案依据不能为空'
                }
            }
    	}
    }
};

/**
 * 清除数据
 */
CaseRelationInfoDlg.clearData = function() {
    this.caseRelationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseRelationInfoDlg.set = function(key, val) {
    this.caseRelationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}
CaseRelationInfoDlg.validate = function () {
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
CaseRelationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaseRelationInfoDlg.close = function() {
    parent.layer.close(window.parent.CaseRelation.layerIndex);
}
/**
 * 关闭此对话框
 */
CaseRelationInfoDlg.closePre = function() {
    parent.layer.close(window.parent.CaseRelationInfoDlg.layerIndex);
}

/**
 * 收集数据
 */
CaseRelationInfoDlg.collectData = function() {
    this
    .set('relationNo')
    .set('relationName')
    .set('relationReason')
    .set('remark')
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
CaseRelationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseRelation/add", function(data){
        Feng.success("添加成功!");
        window.parent.CaseRelation.table.refresh();
        CaseRelationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseRelationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CaseRelationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseRelation/update", function(data){
        Feng.success("修改成功!");
        window.parent.CaseRelation.table.refresh();
        CaseRelationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseRelationInfoData);
    ajax.start();
}
CaseRelationInfoDlg.imageCompareImages = function(){
	if(arr.length != 2){
		 Feng.error("请选择两个足迹比对!");
		 return;
	}
	var index = layer.open({
         type: 2,
         title: '足迹比对',
         area: ['100%', '100%'], //宽高
         fix: false, //不固定
         maxmin: true,
         content: Feng.ctxPath + '/caseRelation/imageCompareImages/' + arr[0]+","+arr[1]
    });
   this.layerIndex = index;
}
$(function() {
	 Feng.initValidator("form", CaseRelationInfoDlg.validateFields);
});
