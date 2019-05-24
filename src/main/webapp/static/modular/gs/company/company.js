/**
 * 单位管理管理初始化
 */
var Company = {
    id: "CompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Company.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '单位名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '组织机构编码', field: 'orgCode', visible: true, align: 'center', valign: 'middle'},
            {title: '单位地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '单位注册时间', field: 'regDate', visible: true,formatter:dateFormatter, align: 'center', valign: 'middle'},
            {title: '单位注册资金(万元)', field: 'regAmt', visible: true,formatter:amtFormatter, align: 'center', valign: 'middle'},
            {title: '单位法人', field: 'legalPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '法人电话号码', field: 'legalPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '单位参保状态', field: 'joinStatusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Company.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Company.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加单位管理
 */
Company.openAddCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加单位管理',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/company_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看单位管理详情
 */
Company.openCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '单位管理详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/company/company_update/' + Company.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除单位管理
 */
Company.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/company/delete", function (data) {
            Feng.success("删除成功!");
            Company.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("companyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询单位管理列表
 */
Company.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Company.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Company.initColumn();
    var table = new BSTable(Company.id, "/company/list", defaultColunms);
    table.setPaginationType("client");
    Company.table = table.init();
});
