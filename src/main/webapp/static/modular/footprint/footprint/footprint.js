/**
 * 足迹信息管理初始化
 */
var Footprint = {
    id: "FootprintTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Footprint.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '足迹编号', field: 'fpNo', visible: true, align: 'center', valign: 'middle'},
            {title: '案件编号', field: 'caseNo', visible: true, align: 'center', valign: 'middle'},
            {title: '嫌疑人编号', field: 'csNo', visible: true, align: 'center', valign: 'middle'},
            {title: '足迹遗留部位', field: 'position', visible: true, align: 'center', valign: 'middle'},
            {title: '足迹遗留方式', field: 'legacyMode', visible: true, align: 'center', valign: 'middle'},
            {title: '足迹提取方式', field: 'extractionMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '原始照片', field: 'originalImg', visible: true, align: 'center', valign: 'middle'},
            {title: '处理后的照片', field: 'newImg', visible: true, align: 'center', valign: 'middle'},
            {title: '长', field: 'length', visible: true, align: 'center', valign: 'middle'},
            {title: '宽', field: 'width', visible: true, align: 'center', valign: 'middle'},
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
Footprint.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Footprint.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加足迹信息
 */
Footprint.openAddFootprint = function () {
    var index = layer.open({
        type: 2,
        title: '添加足迹信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/footprint/footprint_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看足迹信息详情
 */
Footprint.openFootprintDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '足迹信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/footprint/footprint_update/' + Footprint.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除足迹信息
 */
Footprint.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/footprint/delete", function (data) {
            Feng.success("删除成功!");
            Footprint.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("footprintId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询足迹信息列表
 */
Footprint.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Footprint.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Footprint.initColumn();
    var table = new BSTable(Footprint.id, "/footprint/list", defaultColunms);
    table.setPaginationType("client");
    Footprint.table = table.init();
});
