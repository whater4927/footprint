/**
 * 账目查询管理初始化
 */
var Acc = {
    id: "AccTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Acc.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费主题ID', field: 'objectId', visible: true, align: 'center', valign: 'middle'},
            {title: '类型:个人、单位', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费账户类型', field: 'payAccType', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费账户编号', field: 'apyAccNum', visible: true, align: 'center', valign: 'middle'},
            {title: '缴费日期', field: 'payDt', visible: true, align: 'center', valign: 'middle'},
            {title: '收费账户类型', field: 'accType', visible: true, align: 'center', valign: 'middle'},
            {title: '收费账户编号', field: 'accNum', visible: true, align: 'center', valign: 'middle'},
            {title: '业务办理人', field: 'operId', visible: true, align: 'center', valign: 'middle'},
            {title: '业务办理机构', field: 'operOrgId', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'delStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改人', field: 'updUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改机构', field: 'updOrgId', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改时间', field: 'updTm', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'crtUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构id', field: 'crtOrgId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Acc.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Acc.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账目查询
 */
Acc.openAddAcc = function () {
    var index = layer.open({
        type: 2,
        title: '添加账目查询',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/acc/acc_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账目查询详情
 */
Acc.openAccDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账目查询详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/acc/acc_update/' + Acc.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账目查询
 */
Acc.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/acc/delete", function (data) {
            Feng.success("删除成功!");
            Acc.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账目查询列表
 */
Acc.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Acc.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Acc.initColumn();
    var table = new BSTable(Acc.id, "/acc/list", defaultColunms);
    table.setPaginationType("client");
    Acc.table = table.init();
});
