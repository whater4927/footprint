/**
 * 初始化嫌疑人信息详情对话框
 */
var CriminalSuspectInfoDlg = {
    criminalSuspectInfoData : {}
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	CriminalSuspectInfoDlg.hideDeptSelectTree();
    }
}
CriminalSuspectInfoDlg.validate = function () {
    $('#form').data("bootstrapValidator").resetForm();
    $('#form').bootstrapValidator('validate');
    return $("#form").data('bootstrapValidator').isValid();
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
    if (!this.validate()) {
        return;
    }
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
    if (!this.validate()) {
        return;
    }
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

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
CriminalSuspectInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#graspUnit").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
CriminalSuspectInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
CriminalSuspectInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
CriminalSuspectInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
$(function() {
	var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(CriminalSuspectInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;
    Feng.initValidator("form", CriminalSuspectInfoDlg.validateFields);
});
