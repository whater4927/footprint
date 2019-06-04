/**
 * 案件基本信息管理初始化
 */
var CaseInfo = {
    id: "CaseInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CaseInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '案件编号', field: 'caseNo', visible: true, align: 'center', valign: 'middle'},
            {title: '案件状态', field: 'caseStateName', visible: true, align: 'center', valign: 'middle'},
            {title: '案发时间', field: 'caseTm', visible: true, align: 'center', valign: 'middle'},
            {title: '案发地点', field: 'caseAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '所属单位', field: 'unitName', visible: true, align: 'center', valign: 'middle'},
            {title: '简要案情', field: 'caseDesc', visible: true, align: 'center', valign: 'middle'},
            {title: '案件类别', field: 'caseTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '侵入方式', field: 'intrusionModeName', visible: true, align: 'center', valign: 'middle'},
            {title: '被盗物品', field: 'stolenGoods', visible: true, align: 'center', valign: 'middle'},
            {title: '作案人数', field: 'crimesPersonNum', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CaseInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CaseInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加案件基本信息
 */
CaseInfo.openAddCaseInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加案件基本信息',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/caseInfo/caseInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看案件基本信息详情
 */
CaseInfo.openCaseInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '案件基本信息详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/caseInfo/caseInfo_update/' + CaseInfo.seItem.caseNo
        });
        this.layerIndex = index;
    }
};

/**
 * 删除案件基本信息
 */
CaseInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/caseInfo/delete", function (data) {
            Feng.success("删除成功!");
            CaseInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("caseInfoId",this.seItem.caseNo);
        ajax.start();
    }
};

/**
 * 查询案件基本信息列表
 */
CaseInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CaseInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CaseInfo.initColumn();
    var table = new BSTable(CaseInfo.id, "/caseInfo/list", defaultColunms);
    table.setPaginationType("client");
    CaseInfo.table = table.init();
});
