amtFormatter = function(value, row, index){
	return parseFloat (value).toFixed(2);
};

rationFormatter = function(value, row, index){
	return (parseFloat (value).toFixed(2)) + "%" ;
};

dateFormatter = function(value, row, index){
	return value.substr(0,10)  ;
};



(function () {
	var $ax1 = function (url, success, error) {
		this.url = url;
		this.type = "post";
		this.data = {};
		this.dataType = "json";
		this.async = false;
		this.success = success;
		this.error = error;
	};
	
	$ax1.prototype = {
		start : function () {	
			var me = this;
			
			if (this.url.indexOf("?") == -1) {
				this.url = this.url + "?jstime=" + new Date().getTime();
			} else {
				this.url = this.url + "&jstime=" + new Date().getTime();
			}
			
			$.ajax({
		        type: this.type,
		        url: this.url,
		        dataType: this.dataType,
		        async: this.async,
		        data: this.data,
		        contentType: "application/json; charset=utf-8",
				beforeSend: function(data) {
					
				},
		        success: function(data) {
		        	me.success(data);
		        },
		        error: function(data) {
		        	me.error(data);
		        }
		    });
		}, 
		
		set : function (key, value) {
			/*if (typeof key == "object") {
				for (var i in key) {
					if (typeof i == "function")
						continue;
					this.data[i] = key[i];
				}
			} else {
				this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
			}*/
			if (typeof key == "object"){
				this.data = JSON.stringify(key);
			} else {
				this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
			}
			return this;
		},
		
		setData : function(data){
			this.data = data;
			return this;
		},
		
		clear : function () {
			this.data = {};
			return this;
		}
	};
	
	window.$ax1 = $ax1;
	
} ());

function ajaxUtil(url,data,func){
	//提交信息
	var ajax = new $ax1(Feng.ctxPath + url , function(data){
		func(data);
	},function(data){
	    Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	if(data)
		ajax.setData(data);
	return ajax ;
}



