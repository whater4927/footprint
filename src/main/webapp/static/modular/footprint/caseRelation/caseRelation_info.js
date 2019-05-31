/**
 * 初始化串并案件详情对话框
 */
var CaseRelationInfoDlg = {
    caseRelationInfoData : {}
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

$(function() {

});
