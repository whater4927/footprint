/**
 * 生成财务数据管理初始化
 */
var GenerAcc = {
    id: "GenerAccTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GenerAcc.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '单位', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '日期', field: 'genDt', visible: true, align: 'center', valign: 'middle'},
            {title: '总金额', field: 'totalAmt', visible: true, formatter:amtFormatter, align: 'right', valign: 'middle'},
            {title: '员工人数', field: 'empCount', visible: true, align: 'center', valign: 'middle'},
            {title: '创建部门', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'}
    ];
};

GenerAcc.generation = function() {
	var genDt = $("#genDt").val() ;
	if(genDt){
		var ajax = new $ax(Feng.ctxPath + "/generAcc/generation/"+genDt, function (data) {
			Feng.success("成功!");
			GenerAcc.table.refresh();
	    }, function (data) {
	    	Feng.error("失败!" + data.responseJSON.message + "!");
	    });
	    ajax.start();
	} else {
		Feng.error("请选择生成日期!");
	}
}

/**
 * 检查是否选中
 */
GenerAcc.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GenerAcc.seItem = selected[0];
        return true;
    }
};
amtFormatter = function(value, row, index){
	return parseFloat (value).toFixed(2);
}
/**
 * 点击添加生成财务数据
 */
GenerAcc.openAddGenerAcc = function () {
    var index = layer.open({
        type: 2,
        title: '添加生成财务数据',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/generAcc/generAcc_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看生成财务数据详情
 */
GenerAcc.openGenerAccDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '生成财务数据详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/generAcc/generAcc_update/' + GenerAcc.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除生成财务数据
 */
GenerAcc.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/generAcc/delete", function (data) {
            Feng.success("删除成功!");
            GenerAcc.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("generAccId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询生成财务数据列表
 */
GenerAcc.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    GenerAcc.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GenerAcc.initColumn();
    var table = new BSTable(GenerAcc.id, "/generAcc/list", defaultColunms);
    table.setPaginationType("client");
    GenerAcc.table = table.init();
});
