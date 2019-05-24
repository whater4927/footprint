/**
 * 初始化社区问答详情对话框
 */
var InvitationInfoDlg = {
    invitationInfoData : {}
};

/**
 * 清除数据
 */
InvitationInfoDlg.clearData = function() {
    this.invitationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvitationInfoDlg.set = function(key, val) {
    this.invitationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvitationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InvitationInfoDlg.close = function() {
    parent.layer.close(window.parent.Invitation.layerIndex);
}

/**
 * 收集数据
 */
InvitationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pid')
    .set('userId')
    .set('title')
    .set('content')
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
InvitationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invitation/add", function(data){
        Feng.success("添加成功!");
        window.parent.Invitation.table.refresh();
        InvitationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invitationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InvitationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    this.set("pid",$("#pid").val());
    this.set("content",$("#rep_content").val());
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invitation/addDetail", function(data){
        Feng.success("回复成功!");
        InvitationInfoDlg.init();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invitationInfoData);
    ajax.start();
}
InvitationInfoDlg.init = function() {
	if($("#pid").val()){
		 var pid = $("#pid").val();
		 var ajax = new $ax(Feng.ctxPath + "/invitation/detail/" + pid, function(data){
		     //debugger;
			 $("#title").empty();
			 $("#title").append(data.title);
			 $("#info").empty();
			 $("#info").append("楼主:"+data.createUserName+" 时间："+data.crtTm+" 回复:"+data.list.length);
			 $("#content").empty();
			 $("#content").append(data.content);
			 $("#detail").empty();
			 for(var i=0;i<data.list.length;i++){
				 var detal = data.list[i];
				 var html = "<figure class='highlight'>"+
							"   <div class='row container'>"+
							"       <div class='col-sm-12 b-r'>"+
							"			<p>"+detal.content+"</p>"+
						    "		   <h6>作者:"+data.createUserName+" 时间："+data.crtTm+"</h6>"+
							"	    </div>"+
							"   </div>"+
							"</figure>";
				 $("#detail").append(html);
			 }
			
		 },function(data){
		        Feng.error("修改失败!" + data.responseJSON.message + "!");
		 });
		 ajax.start();
	 }
}
$(function() {
	InvitationInfoDlg.init();
});
