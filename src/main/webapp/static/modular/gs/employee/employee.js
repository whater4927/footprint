/**
 * 人员管理管理初始化
 */
var Employee = {
    id: "EmployeeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Employee.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号', field: 'idNum', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '文化程度', field: 'educationName', visible: true, align: 'center', valign: 'middle'},
            {title: '民族', field: 'nativePlaceName', visible: true, align: 'center', valign: 'middle'},
            {title: '入职日期', field: 'entryDt', visible: true,formatter:dateFormatter, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '工作单位', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '工资', field: 'salary', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Employee.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Employee.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加人员管理
 */
Employee.openAddEmployee = function () {
    var index = layer.open({
        type: 2,
        title: '添加人员管理',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/employee/employee_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看人员管理详情
 */
Employee.openEmployeeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '人员管理详情',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/employee/employee_update/' + Employee.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除人员管理
 */
Employee.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/employee/delete", function (data) {
            Feng.success("删除成功!");
            Employee.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("employeeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询人员管理列表
 */
Employee.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Employee.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Employee.initColumn();
    var table = new BSTable(Employee.id, "/employee/list", defaultColunms);
    table.setPaginationType("client");
    Employee.table = table.init();
});
