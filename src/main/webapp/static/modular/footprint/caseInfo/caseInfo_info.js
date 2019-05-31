/**
 * 初始化案件基本信息详情对话框
 */
var CaseInfoInfoDlg = {
    caseInfoInfoData : {}
};

/**
 * 清除数据
 */
CaseInfoInfoDlg.clearData = function() {
    this.caseInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseInfoInfoDlg.set = function(key, val) {
    this.caseInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaseInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.CaseInfo.layerIndex);
}

/**
 * 收集数据
 */
CaseInfoInfoDlg.collectData = function() {
    this
    .set('caseNo')
    .set('caseState')
    .set('caseTm')
    .set('caseAddress')
    .set('unit')
    .set('caseDesc')
    .set('caseType')
    .set('intrusionMode')
    .set('stolenGoods')
    .set('crimesPersonNum')
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
CaseInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.CaseInfo.table.refresh();
        CaseInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CaseInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.CaseInfo.table.refresh();
        CaseInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseInfoInfoData);
    ajax.start();
}

$(function() {

});
