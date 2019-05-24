var SelectOption = {};
SelectOption.render = function(id,code) {
	$(id).empty();
	var ajax = new $ax(Feng.ctxPath + "/dict/render?code="+code, function(data) {
		 for (var i = 0; i < data.length; i++) {
			var e = data[i];
			$(id).append("<option value='"+e.code+"'>"+e.name+"</option>");
		}
		$(id).val($(id+"_value").val());
	}, function(data) {
		alert(data);
	});
	ajax.start();
}
SelectOption.renderURL = function(id,url) {
	$(id).empty();
	var ajax = new $ax(Feng.ctxPath + url, function(data) {
		 for (var i = 0; i < data.length; i++) {
			var e = data[i];
			$(id).append("<option value='"+e.id+"'>"+e.name+"</option>");
		}
		$(id).val($(id+"_value").val());
	}, function(data) {
		alert(data);
	});
	ajax.start();
}