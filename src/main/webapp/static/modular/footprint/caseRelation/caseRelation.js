/**
 * 串并案件管理初始化
 */
var CaseRelation = {
    id: "CaseRelationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CaseRelation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '串并案件编号', field: 'relationNo', visible: true, align: 'center', valign: 'middle'},
            {title: '串案名称', field: 'relationName', visible: true, align: 'center', valign: 'middle'},
            {title: '串案依据', field: 'relationReason', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CaseRelation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CaseRelation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加串并案件
 */
CaseRelation.openAddCaseRelation = function () {
    var index = layer.open({
        type: 2,
        title: '添加串并案件',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/caseRelation/caseRelation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看串并案件详情
 */
CaseRelation.openCaseRelationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '串并案件详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/caseRelation/caseRelation_update/' + CaseRelation.seItem.relationNo
        });
        this.layerIndex = index;
    }
};

/**
 * 删除串并案件
 */
CaseRelation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/caseRelation/delete", function (data) {
            Feng.success("删除成功!");
            CaseRelation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("caseRelationId",this.seItem.relationNo);
        ajax.start();
    }
};

/**
 * 查询串并案件列表
 */
CaseRelation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CaseRelation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CaseRelation.initColumn();
    var table = new BSTable(CaseRelation.id, "/caseRelation/list", defaultColunms);
    table.setPaginationType("client");
    CaseRelation.table = table.init();
});
