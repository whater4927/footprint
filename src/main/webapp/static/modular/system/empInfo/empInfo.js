/**
 * 员工信息管理初始化
 */
var EmpInfo = {
    id: "EmpInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EmpInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'name', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: 'birthday', field: 'birthday', visible: true, align: 'center', valign: 'middle'},
            {title: 'sex', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: 'salary', field: 'salary', visible: true, align: 'center', valign: 'middle'},
            {title: 'address', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'remark', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
EmpInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EmpInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加员工信息
 */
EmpInfo.openAddEmpInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加员工信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/empInfo/empInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看员工信息详情
 */
EmpInfo.openEmpInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '员工信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/empInfo/empInfo_update/' + EmpInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除员工信息
 */
EmpInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/empInfo/delete", function (data) {
            Feng.success("删除成功!");
            EmpInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("empInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询员工信息列表
 */
EmpInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    EmpInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = EmpInfo.initColumn();
    var table = new BSTable(EmpInfo.id, "/empInfo/list", defaultColunms);
    table.setPaginationType("client");
    EmpInfo.table = table.init();
});
