/**
 * 初始化生成财务数据详情对话框
 */
var GenerAccInfoDlg = {
    generAccInfoData : {}
};

/**
 * 清除数据
 */
GenerAccInfoDlg.clearData = function() {
    this.generAccInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GenerAccInfoDlg.set = function(key, val) {
    this.generAccInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GenerAccInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GenerAccInfoDlg.close = function() {
    parent.layer.close(window.parent.GenerAcc.layerIndex);
}

/**
 * 收集数据
 */
GenerAccInfoDlg.collectData = function() {
    this
    .set('id')
    .set('comId')
    .set('genDt')
    .set('totalAmt')
    .set('empCount')
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
GenerAccInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/generAcc/add", function(data){
        Feng.success("添加成功!");
        window.parent.GenerAcc.table.refresh();
        GenerAccInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.generAccInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GenerAccInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/generAcc/update", function(data){
        Feng.success("修改成功!");
        window.parent.GenerAcc.table.refresh();
        GenerAccInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.generAccInfoData);
    ajax.start();
}

$(function() {

});
