/**
 * 初始化案件基本信息详情对话框
 */
var CaseInfoInfoDlg = {
    caseInfoInfoData : {},
    validateFields:{
    	crimesPersonNum:{
    		validators: {
                notEmpty: {
                    message: '作案人数不能为空'
                }
            }
    	},
    	caseAddress:{
    		validators: {
                notEmpty: {
                    message: '案发地点不能为空'
                }
            }
    	},
    	citySel:{
    		validators: {
                notEmpty: {
                    message: '所属单位不能为空'
                }
            }
    	},
    	stolenGoods:{
    		validators: {
                notEmpty: {
                    message: '被盗物品不能为空'
                }
            }
    	},
    	caseDesc:{
    		validators: {
                notEmpty: {
                    message: '简要案情不能为空'
                }
            }
    	},
    	caseTm:{
    		validators: {
                notEmpty: {
                    message: '案发时间不能为空'
                }
            }
    	},
    	intrusionMode:{
    		validators: {
                notEmpty: {
                    message: '侵入方式不能为空'
                }
            }
    	},
    	caseState:{
    		validators: {
                notEmpty: {
                    message: '案件状态不能为空'
                }
            }
    	}
    }
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
    	CaseInfoInfoDlg.hideDeptSelectTree();
    }
}
/**
 * 清除数据
 */
CaseInfoInfoDlg.clearData = function() {
    this.caseInfoInfoData = {};
}
CaseInfoInfoDlg.validate = function () {
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
    if (!this.validate()) {
        return;
    }
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
    if (!this.validate()) {
        return;
    }
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


/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
CaseInfoInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#unit").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
CaseInfoInfoDlg.showDeptSelectTree = function () {
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
CaseInfoInfoDlg.showInfoDeptSelectTree = function () {
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
CaseInfoInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

$(function() {
	var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(CaseInfoInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;
    Feng.initValidator("form", CaseInfoInfoDlg.validateFields);
});
