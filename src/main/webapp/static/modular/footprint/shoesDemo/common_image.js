var uploader = WebUploader.create({
    // 选完文件后，是否自动上传。
    auto: true,
    // swf文件路径
    swf: '/static/js/plugins/webuploader/Uploader.swf',
    // 文件接收服务端。
    server: '/mgr/upload',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',
    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});
uploader.on( 'uploadSuccess', function( file ,response) {
   console.log(file.name);
   setImage(response);
});
let myImage;
let ctx;
let img;
let degree = 0;
var scaleInput = 1;
function openImage(image) {
	var canvas = '<canvas id="myCanvas" width="800" height="600" style="background-color: white"></canvas>';
	var content = '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff;">'+canvas+'</div>'; 
	layer.open({
			type : 1,
			title : false,
			closeBtn : false,
			area: ['100%', '100%'],
			shade : 0.8,
			id : 'LAY_layuipro' ,
			resize : false,
			btn : [ '放大', '缩小', '旋转', '二值化','关闭' ],
			btnAlign : 'c',
			moveType : 1 ,
			content : content,
			btn1: function(index, layero){//放大
				if(scaleInput >=3){
			 		return false;
			 	}
		    	scaleInput = scaleInput + 0.5 ;
		        ctx.clearRect(0, 0, myImage.width, myImage.height);
		        ctx.save();
		        ctx.translate(myImage.width / 2 - img.width / 2 * scaleInput, myImage.height / 2 - img.height / 2 * scaleInput);
		        ctx.scale(scaleInput, scaleInput);
		        ctx.drawImage(img, 0, 0);
		        ctx.restore();
			    return false
			},
			btn2: function(index, layero){//缩小
				if(scaleInput <= 0.5){
		   	 		return false;
		   	 	}
		   	 	scaleInput = scaleInput - 0.5 ;
		        ctx.clearRect(0, 0, myImage.width, myImage.height);
		        ctx.save();
		        ctx.translate(myImage.width / 2 - img.width / 2 * scaleInput, myImage.height / 2 - img.height / 2 * scaleInput);
		        ctx.scale(scaleInput, scaleInput);
		        ctx.drawImage(img, 0, 0);
		        ctx.restore();
			    return false
			},
			btn3: function(index, layero){//旋转
				 let rotateInput = 90;
			     degree += parseInt(rotateInput);
			     degree %= 360;
			     ctx.save();
			     ctx.clearRect(0, 0, myImage.width, myImage.height);
			     ctx.translate(myImage.width / 2, myImage.height / 2);
			     ctx.rotate(degree / 180 * Math.PI);
			     ctx.translate(-myImage.width / 2, -myImage.height / 2);
			     ctx.drawImage(img, myImage.width / 2 - img.width / 2, myImage.height / 2 - img.height / 2);
			     ctx.restore();
			     return false;
			},
			btn4: function(index, layero){//二值化
				 var data = $(image).val();
				 ajaxUtil("/test/binaryImage?url="+data,null,function(data){
					 $(image).val(data);
					 img.src = '/kaptcha/' + data;
				 }).start();
			     return false;
			},
			end:function(){
				setImage(image,$(image).val());
			},
			success : function(layero) {
				 myImage = document.getElementById("myCanvas");
				 ctx = myImage.getContext("2d");
				 img = new Image();
				 img.src = '/kaptcha/'+$(image).val();
				 img.onload = function () {
				      ctx.drawImage(img, myImage.width / 2 - img.width / 2, myImage.height / 2 - img.height / 2);
				 };
			}
	});
	
}
function setImage(image,response){
	$("#images").empty();
	$(image).val(response);
	$("#images").append("<img onclick='openImage(\""+image+"\")' src='/kaptcha/"+response+"' alt='通用的占位符缩略图' width='200px' height='200px' class='img-thumbnail' width='200' height='200'/>")
}