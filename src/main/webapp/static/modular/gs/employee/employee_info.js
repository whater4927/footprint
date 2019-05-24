/**
 * 初始化人员管理详情对话框
 */
var EmployeeInfoDlg = {
    employeeInfoData : {},
    validateFields:{
    	name:{
    		validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
    	},
    	idNum:{
    		validators: {
                notEmpty: {
                    message: '身份证号不能为空'
                },
                regexp: {
                	 regexp: /(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                	 message: '身份证号格式有误'
                }
            }
    	},
    	email:{
    		validators: {
                notEmpty: {
                    message: '邮箱不能为空'
                },
                emailAddress:{
                	message: '邮箱格式有误！'
                }
                
            }
    	},
    	education:{
    		validators: {
                notEmpty: {
                    message: '学历不能为空'
                }
            }
    	},
    	comId:{
    		validators: {
                notEmpty: {
                    message: '工作单位不能为空'
                }
            }
    	},
    	salary:{
    		validators: {
                notEmpty: {
                    message: '工资不能为空'
                },
                numeric:{
                	message: '请输入数字'
                }
            }
    	},
    	status:{
    		validators: {
                notEmpty: {
                    message: '状态不能为空'
                }
            }
    	},
    	nativePlace:{
    		validators: {
                notEmpty: {
                    message: '民族不能为空'
                }
            }
    	},
    	entryDt:{
    		validators: {
                notEmpty: {
                    message: '入职日期不能为空'
                }
            }
    	},
    	phone:{
    		validators: {
                notEmpty: {
                    message: '电话号码不能为空'
                },
                regexp: {
                	regexp: /^\d{11}$/,
                    message: '电话号码格式有误'
                }
            }
    	}
    }
};

/**
 * 清除数据
 */
EmployeeInfoDlg.clearData = function() {
    this.employeeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmployeeInfoDlg.set = function(key, val) {
    this.employeeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmployeeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EmployeeInfoDlg.close = function() {
    parent.layer.close(window.parent.Employee.layerIndex);
}

/**
 * 收集数据
 */
EmployeeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('empNum')
    .set('name')
    .set('idNum')
    .set('email')
    .set('education')
    .set('nativePlace')
    .set('entryDt')
    .set('phone')
    .set('comId')
    .set('salary')
    .set('status')
    .set('delStatus')
    .set('updUserId')
    .set('updOrgId')
    .set('updTm')
    .set('crtUserId')
    .set('crtOrgId')
    .set('crtTm');
}
/**
 * 验证数据是否为空
 */
EmployeeInfoDlg.validate = function () {
    $('#form').data("bootstrapValidator").resetForm();
    $('#form').bootstrapValidator('validate');
    return $("#form").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
EmployeeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employee/add", function(data){
        Feng.success("添加成功!");
        window.parent.Employee.table.refresh();
        EmployeeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EmployeeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employee/update", function(data){
        Feng.success("修改成功!");
        window.parent.Employee.table.refresh();
        EmployeeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeeInfoData);
    ajax.start();
}
EmployeeInfoDlg.init = function() {
//	debugger;
	SelectOption.renderURL("#comId","/company/list");
	SelectOption.render("#nativePlace","nativePlace");
	SelectOption.render("#education","education");
	SelectOption.render("#status","emp_status");
}
$(function() {
	EmployeeInfoDlg.init();
	Feng.initValidator("form", EmployeeInfoDlg.validateFields);
});
