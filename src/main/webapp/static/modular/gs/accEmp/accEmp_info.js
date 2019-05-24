/**
 * 初始化收缴记录明细详情对话框
 */
var AccEmpInfoDlg = {
    accEmpInfoData : {}
};

/**
 * 清除数据
 */
AccEmpInfoDlg.clearData = function() {
    this.accEmpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccEmpInfoDlg.set = function(key, val) {
    this.accEmpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccEmpInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccEmpInfoDlg.close = function() {
    parent.layer.close(window.parent.AccEmp.layerIndex);
}

/**
 * 收集数据
 */
AccEmpInfoDlg.collectData = function() {
    this
    .set('id')
    .set('empId')
    .set('comId')
    .set('empAmt')
    .set('comAmt')
    .set('payStatus')
    .set('dealTm')
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
AccEmpInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/accEmp/add", function(data){
        Feng.success("添加成功!");
        window.parent.AccEmp.table.refresh();
        AccEmpInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accEmpInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AccEmpInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/accEmp/update", function(data){
        Feng.success("修改成功!");
        window.parent.AccEmp.table.refresh();
        AccEmpInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accEmpInfoData);
    ajax.start();
}

$(function() {

});
