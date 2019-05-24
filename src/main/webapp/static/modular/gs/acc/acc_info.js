/**
 * 初始化账目查询详情对话框
 */
var AccInfoDlg = {
    accInfoData : {}
};

/**
 * 清除数据
 */
AccInfoDlg.clearData = function() {
    this.accInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccInfoDlg.set = function(key, val) {
    this.accInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccInfoDlg.close = function() {
    parent.layer.close(window.parent.Acc.layerIndex);
}

/**
 * 收集数据
 */
AccInfoDlg.collectData = function() {
    this
    .set('id')
    .set('objectId')
    .set('type')
    .set('payAccType')
    .set('apyAccNum')
    .set('payDt')
    .set('accType')
    .set('accNum')
    .set('operId')
    .set('operOrgId')
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
AccInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/acc/add", function(data){
        Feng.success("添加成功!");
        window.parent.Acc.table.refresh();
        AccInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AccInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/acc/update", function(data){
        Feng.success("修改成功!");
        window.parent.Acc.table.refresh();
        AccInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accInfoData);
    ajax.start();
}

$(function() {

});
