/**
 * 社区问答管理初始化
 */
var Invitation = {
    id: "InvitationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Invitation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标题', field: 'title', visible: true, align: 'left', valign: 'middle',width:200},
            {title: '内容', field: 'content', visible: true, align: 'left', valign: 'middle',width:300},
            {title: '楼主', field: 'createUserName', visible: true, align: 'center', valign: 'middle',width:80},
            {title: '创建机构', field: 'createOrgName', visible: true, align: 'center', valign: 'middle',width:80},
            {title: '创建时间', field: 'crtTm', visible: true, align: 'center', valign: 'middle',width:80}
    ];
};

/**
 * 检查是否选中
 */
Invitation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Invitation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加社区问答
 */
Invitation.openAddInvitation = function () {
    var index = layer.open({
        type: 2,
        title: '添加社区问答',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/invitation/invitation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看社区问答详情
 */
Invitation.openInvitationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '社区问答详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/invitation/invitation_update/' + Invitation.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除社区问答
 */
Invitation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/invitation/delete", function (data) {
            Feng.success("删除成功!");
            Invitation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("invitationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询社区问答列表
 */
Invitation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Invitation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Invitation.initColumn();
    var table = new BSTable(Invitation.id, "/invitation/list", defaultColunms);
    table.setPaginationType("client");
    Invitation.table = table.init();
});
