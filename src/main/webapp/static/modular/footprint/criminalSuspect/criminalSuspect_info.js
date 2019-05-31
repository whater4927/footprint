/**
 * 初始化嫌疑人信息详情对话框
 */
var CriminalSuspectInfoDlg = {
    criminalSuspectInfoData : {}
};

/**
 * 清除数据
 */
CriminalSuspectInfoDlg.clearData = function() {
    this.criminalSuspectInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CriminalSuspectInfoDlg.set = function(key, val) {
    this.criminalSuspectInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CriminalSuspectInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CriminalSuspectInfoDlg.close = function() {
    parent.layer.close(window.parent.CriminalSuspect.layerIndex);
}

/**
 * 收集数据
 */
CriminalSuspectInfoDlg.collectData = function() {
    this
    .set('csNo')
    .set('name')
    .set('sex')
    .set('heigh')
    .set('nation')
    .set('idNo')
    .set('birthday')
    .set('address')
    .set('csType')
    .set('graspDate')
    .set('graspUnit')
    .set('inputUser')
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
CriminalSuspectInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/criminalSuspect/add", function(data){
        Feng.success("添加成功!");
        window.parent.CriminalSuspect.table.refresh();
        CriminalSuspectInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.criminalSuspectInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CriminalSuspectInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/criminalSuspect/update", function(data){
        Feng.success("修改成功!");
        window.parent.CriminalSuspect.table.refresh();
        CriminalSuspectInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.criminalSuspectInfoData);
    ajax.start();
}

$(function() {

});
