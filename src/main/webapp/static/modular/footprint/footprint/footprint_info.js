/**
 * 初始化足迹信息详情对话框
 */
var FootprintInfoDlg = {
    footprintInfoData : {}
};

/**
 * 清除数据
 */
FootprintInfoDlg.clearData = function() {
    this.footprintInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FootprintInfoDlg.set = function(key, val) {
    this.footprintInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FootprintInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FootprintInfoDlg.close = function() {
    parent.layer.close(window.parent.Footprint.layerIndex);
}

/**
 * 收集数据
 */
FootprintInfoDlg.collectData = function() {
    this
    .set('fpNo')
    .set('caseNo')
    .set('csNo')
    .set('position')
    .set('legacyMode')
    .set('extractionMethod')
    .set('originalImg')
    .set('newImg')
    .set('length')
    .set('width')
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
FootprintInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/footprint/add", function(data){
        Feng.success("添加成功!");
        window.parent.Footprint.table.refresh();
        FootprintInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.footprintInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FootprintInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/footprint/update", function(data){
        Feng.success("修改成功!");
        window.parent.Footprint.table.refresh();
        FootprintInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.footprintInfoData);
    ajax.start();
}

$(function() {

});
