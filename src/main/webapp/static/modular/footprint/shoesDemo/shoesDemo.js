/**
 * 鞋厂鞋样管理初始化
 */
var ShoesDemo = {
    id: "ShoesDemoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ShoesDemo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '鞋样编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '品牌', field: 'brand', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建机构', field: 'createOrgName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'url', visible: true, align: 'center', valign: 'middle',formatter:formatterImage}
    ];
};
function formatterImage(value,row,index){ 
	var html = "<img src='/kaptcha/"+value+"?t="+new Date().getTime()+"' width='200px' height='200px' class='img-thumbnail' onclick='openImage(\""+value+"\")'>";
	return html;
}
/**
 * 检查是否选中
 */
ShoesDemo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ShoesDemo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加鞋厂鞋样
 */
ShoesDemo.openAddShoesDemo = function () {
    var index = layer.open({
        type: 2,
        title: '添加鞋厂鞋样',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/shoesDemo/shoesDemo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看鞋厂鞋样详情
 */
ShoesDemo.openShoesDemoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '鞋厂鞋样详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shoesDemo/shoesDemo_update/' + ShoesDemo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除鞋厂鞋样
 */
ShoesDemo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/shoesDemo/delete", function (data) {
            Feng.success("删除成功!");
            ShoesDemo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shoesDemoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询鞋厂鞋样列表
 */
ShoesDemo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ShoesDemo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ShoesDemo.initColumn();
    var table = new BSTable(ShoesDemo.id, "/shoesDemo/list", defaultColunms);
    table.setPaginationType("client");
    ShoesDemo.table = table.init();
});
