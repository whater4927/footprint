/**
 * 初始化单位管理详情对话框
 */
var CompanyInfoDlg = {
    companyInfoData : {},
	validateFields:{
		name:{
    		validators: {
                notEmpty: {
                    message: '单位名称不能为空'
                }
            }
    	},
    	orgCode:{
    		validators: {
                notEmpty: {
                    message: '组织机构编码不能为空'
                }
            }
    	},
    	address:{
    		validators: {
                notEmpty: {
                    message: '单位地址不能为空'
                }
            }
    	},
    	regDate:{
    		validators: {
                notEmpty: {
                    message: '单位注册时间不能为空'
                }
            }
    	},
    	regAmt:{
    		validators: {
                notEmpty: {
                    message: '单位注册资金(万元)不能为空'
                },
                numeric:{
                	message: '请输入数字'
                }
            }
    	},
    	legalPerson:{
    		validators: {
                notEmpty: {
                    message: '单位法人不能为空'
                }
            }
    	},
    	legalPhone:{
    		validators: {
                notEmpty: {
                    message: '法人电话号码不能为空'
                },
                regexp: {
                	regexp: /^\d{11}$/,
                    message: '法人电话号码格式有误'
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
    	accType:{
    		validators: {
                notEmpty: {
                    message: '账户类型不能为空'
                }
            }
    	},
    	accNo:{
    		validators: {
                notEmpty: {
                    message: '缴费账户编号不能为空'
                }
            }
    	},
    	accUseType:{
    		validators: {
                notEmpty: {
                    message: '用途不能为空'
                }
            }
    	},
    	joinStatus:{
    		validators: {
                notEmpty: {
                    message: '单位参保状态不能为空'
                }
            }
    	},
    	joinTm:{
    		validators: {
                notEmpty: {
                    message: '参保时间不能为空'
                }
            }
    	},
    	empRation:{
    		validators: {
                notEmpty: {
                    message: '个人缴纳比例(%)不能为空'
                },
                between:{
                	min:0,
                	max:100,
                	message: '请输入0-100数字'
                }
            }
    	},
    	comRation:{
    		validators: {
                notEmpty: {
                    message: '单位缴纳比例(%)不能为空'
                },
                between:{
                	min:0,
                	max:100,
                	message: '请输入0-100数字'
                }
            }
    	}
	}
};

/**
 * 清除数据
 */
CompanyInfoDlg.clearData = function() {
    this.companyInfoData = {};
}
/**
 * 验证数据是否为空
 */
CompanyInfoDlg.validate = function () {
    $('#form').data("bootstrapValidator").resetForm();
    $('#form').bootstrapValidator('validate');
    return $("#form").data('bootstrapValidator').isValid();
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.set = function(key, val) {
    this.companyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.Company.layerIndex);
}

/**
 * 收集数据
 */
CompanyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('orgCode')
    .set('address')
    .set('regDate')
    .set('regAmt')
    .set('legalPerson')
    .set('legalPhone')
    .set('joinStatus')
    .set('joinTm')
    .set('empRation')
    .set('comRation')
    .set('accType')
    .set('accNo')
    .set('accUseType')
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
 * 提交添加
 */
CompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/company/add", function(data){
        Feng.success("添加成功!");
        window.parent.Company.table.refresh();
        CompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/company/update", function(data){
        Feng.success("修改成功!");
        window.parent.Company.table.refresh();
        CompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyInfoData);
    ajax.start();
}

$(function() {
	SelectOption.render("#accType","acc_type");
	SelectOption.render("#accUseType","acc_use_type");
	SelectOption.render("#status","company_status");
	SelectOption.render("#joinStatus","status_yn");
	Feng.initValidator("form", CompanyInfoDlg.validateFields);
});
