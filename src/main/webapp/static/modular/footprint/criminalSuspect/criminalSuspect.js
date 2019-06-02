/**
 * 嫌疑人信息管理初始化
 */
var CriminalSuspect = {
    id: "CriminalSuspectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CriminalSuspect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '嫌疑人编号', field: 'csNo', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sexName', visible: true, align: 'center', valign: 'middle'},
            {title: '籍贯', field: 'nation', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号', field: 'idNo', visible: true, align: 'center', valign: 'middle'},
            {title: '出生日期', field: 'birthday', visible: true, align: 'center', valign: 'middle'},
            {title: '涉案类别', field: 'csTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '抓获日期', field: 'graspDate', visible: true, align: 'center', valign: 'middle'},
            {title: '抓获单位', field: 'graspUnitName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CriminalSuspect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CriminalSuspect.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加嫌疑人信息
 */
CriminalSuspect.openAddCriminalSuspect = function () {
    var index = layer.open({
        type: 2,
        title: '添加嫌疑人信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/criminalSuspect/criminalSuspect_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看嫌疑人信息详情
 */
CriminalSuspect.openCriminalSuspectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '嫌疑人信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/criminalSuspect/criminalSuspect_update/' + CriminalSuspect.seItem.csNo
        });
        this.layerIndex = index;
    }
};

/**
 * 删除嫌疑人信息
 */
CriminalSuspect.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/criminalSuspect/delete", function (data) {
            Feng.success("删除成功!");
            CriminalSuspect.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("criminalSuspectId",this.seItem.csNo);
        ajax.start();
    }
};

/**
 * 查询嫌疑人信息列表
 */
CriminalSuspect.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CriminalSuspect.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CriminalSuspect.initColumn();
    var table = new BSTable(CriminalSuspect.id, "/criminalSuspect/list", defaultColunms);
    table.setPaginationType("client");
    CriminalSuspect.table = table.init();
});
