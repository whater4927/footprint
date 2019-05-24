/**
 * 收缴记录明细管理初始化
 */
var AccEmp = {
    id: "AccEmpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AccEmp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '人员', field: 'empName', visible: true, align: 'center', valign: 'middle'},
            {title: '单位', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '日期', field: 'payDt', visible: true, align: 'center', valign: 'middle'},
            {title: '个人缴纳金额', field: 'empAmt', visible: true,formatter:amtFormatter, align: 'center', valign: 'middle'},
            {title: '单位缴纳金额', field: 'comAmt', visible: true,formatter:amtFormatter, align: 'center', valign: 'middle'},
            {title: '缴纳状态', field: 'payStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '缴纳时间', field: 'dealTm', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构id', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AccEmp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AccEmp.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收缴记录明细
 */
AccEmp.openAddAccEmp = function () {
    var index = layer.open({
        type: 2,
        title: '添加收缴记录明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/accEmp/accEmp_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收缴记录明细详情
 */
AccEmp.openAccEmpDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收缴记录明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/accEmp/accEmp_update/' + AccEmp.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除收缴记录明细
 */
AccEmp.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/accEmp/delete", function (data) {
            Feng.success("删除成功!");
            AccEmp.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accEmpId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收缴记录明细列表
 */
AccEmp.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AccEmp.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AccEmp.initColumn();
    var table = new BSTable(AccEmp.id, "/accEmp/list", defaultColunms);
    table.setPaginationType("client");
    AccEmp.table = table.init();
});
