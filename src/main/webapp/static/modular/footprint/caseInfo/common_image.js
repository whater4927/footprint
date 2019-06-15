let myImage;
let ctx;
let img;
let degree = 0;
var scaleInput = 1;


let orignWidth;
let orignHeight;

let ID = function (id) {
    return document.getElementById(id);
};

//拖拽与拉伸方法
//拖拽拉伸所需参数
let params = {
    left: 0,
    top: 0,
    width: 0,
    height: 0,
    currentX: 0,
    currentY: 0,
    flag: false,
    kind: "drag"
};
//获取相关CSS属性方法
let getCss = function (o, key) {
    return o.currentStyle ? o.currentStyle[key] : document.defaultView.getComputedStyle(o, false)[key];
};

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
			btn : [ '放大', '缩小', '旋转', '二值化','裁剪','裁剪完成','对比度','提交','关闭' ],
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
				 /*var data = $(image).val();
				 ajaxUtil("/test/binaryImage?url="+data,null,function(data){
					 $(image).val(data);
					 img.src = '/kaptcha/' + data;
				 }).start();*/
				var w = img.width, h = img.height;
				var vidData = ctx.getImageData(0, 0, w, h);
				var data = vidData.data;
                for(var i =0;i<data.length;i+=4){
                    var myRed = data[i];
                    var myGreen = data[i + 1];
                    var myBlue = data[i + 2];
                    myGray = parseInt((myRed + myGreen + myBlue) / 3);
                    //取平均值，替代原来的颜色值.
                    data[i] = myGray;
                    data[i + 1] = myGray;
                    data[i + 2] = myGray;
                }
                ctx.putImageData(vidData,0,0);
			     return false;
			},
			btn5: function(index, layero){//裁剪
				cropFun();
			     return false;
			},
			btn6: function(index, layero){//裁剪完成
				 finishCropFun();
			     return false;
			},
			btn7: function(index, layero){//对比度
				/*var w = img.width, h = img.height;
				var vidData = ctx.getImageData(0, 0, w, h);
				var data = vidData.data;
                for(var i =0;i<data.length;i+=4){
                    var myRed = data[i];
                    var myGreen = data[i + 1];
                    var myBlue = data[i + 2];
                    myGray = parseInt((myRed + myGreen + myBlue) / 3);
                    //取平均值，替代原来的颜色值.
                    data[i] = myGray;
                    data[i + 1] = myGray;
                    data[i + 2] = myGray;
                }
                ctx.putImageData(vidData,0,0);*/
				/*var light = prompt("请输入对比度");
				if(!light)return false;*/
				layer.prompt({
					  formType: 0,
					  value: 1.5,
					  title: '请输入值'
					}, function(value, index, elem){
						var light = value;
						 var x = 0 , y = 0 ;
						// 绘制图片
				        // ctx.drawImage(image , x , y);
				        // 获取从x、y开始，宽为image.width、高为image.height的图片数据
				        // 也就是获取绘制的图片数据
				        var imgData = ctx.getImageData(x , y , img.width , img.height);  
				        for (var i = 0 , len = imgData.data.length ; i < len ; i += 4 )  
				        {  
				            // 改变每个像素的R、G、B值
				            imgData.data[i + 0] = imgData.data[i + 0] * light;  
				            imgData.data[i + 1] = imgData.data[i + 1] * light;  
				            imgData.data[i + 2] = imgData.data[i + 2] * light;  
				        }  
				        // 将获取的图片数据放回去。
				        ctx.putImageData(imgData , x , y);
						layer.close(index);
				});
			    return false;
			},
			btn8:function(index, layero){//提交
				//debugger;
				myCanvas.toBlob(function (result) {
					//debugger;
		            var form=new FormData();
		            form.append("xxx",result);
		            form.append("url",$(image).val());
		            ajax(form);
		        })
		        return false;
			},
			end:function(){
				// setImage(image,$(image).val());
			},
			success : function(layero) {
				 myImage = document.getElementById("myCanvas");
				 ctx = myImage.getContext("2d");
				 img = new Image();
				 img.src = '/kaptcha/'+image+"?t="+new Date().getTime();
				 img.onload = function () {
					 //ctx.drawImage(img, myImage.width / 2 - img.width / 2, myImage.height / 2 - img.height / 2);
					  $("#myCanvas").attr({ width: img.width, height: img.height });
				      ctx.drawImage(img, 0, 0);
				      orignWidth = img.naturalWidth;
				      orignHeight = img.naturalHeight;
				 };
			}
	});
	
}


function ajax(formData) {
    $.ajax({
        url:"/test/imageshangchuan",
        type:"post",
        Accept:"html/text;chatset=utf-8",
        contentType:false,
        data:formData,
        processData:false, 
        success: function (data) {
        	layer.alert('上传成功!!!!');    
            console.log("上传成功!!!!");
        }, error: function () {
        	layer.alert("上传失败！");
            console.log("上传失败！");
        }
    })
}

function cropImage(img, cropPosX, cropPosY, width, height) {
    var cropContainer = ID("cropContainer");
    cropContainer.parentNode.removeChild(cropContainer);
    ctx.clearRect(0, 0, myImage.width, myImage.height);
    //sx,sy 是相对于图片的坐标。巨坑
    ctx.drawImage(img, cropPosX, cropPosY, width, height, myImage.width / 2 - width / 2, myImage.height / 2 - height / 2, width, height);
    img.src = myImage.toDataURL("image/png");
}

function finishCropFun() {
    console.log("clipend......");
    var tx = myImage.offsetLeft + (myImage.width - img.width) / 2;
    var ty = myImage.offsetTop + (myImage.height - img.height) / 2;

    var x = parseInt(ID("zxxCropBox").style.left) - tx,
        y = ID("zxxCropBox").offsetTop + ID("zxxCropBox").parentNode.offsetTop - ty,
        w = document.getElementById("cropImageWidth").value,
        h = document.getElementById("cropImageHeight").value;


    cropImage(img, x, y, parseInt(w), parseInt(h));

}

function cropFun() {
    var clickFlag = false;

    var iCurWidth = img.width;
    var iCurHeight = img.height;

    var oRelDiv = document.createElement("div");
    oRelDiv.style.position = "absolute";
    oRelDiv.style.width = iCurWidth + "px";
    oRelDiv.style.height = iCurHeight + 30 + "px";
    oRelDiv.style.top = "30px";
    oRelDiv.id = "cropContainer";

    var iOrigWidth = orignWidth, iOrigHeight = orignHeight;
    var scaleX = iCurWidth / iOrigWidth;
    var scaleY = iCurHeight / iOrigHeight;

    myImage.parentNode.insertBefore(oRelDiv, myImage);

    //初始化坐标与剪裁高宽
    var cropW = 80, cropH = 80;
    var posX = (myImage.offsetLeft + myImage.width / 2 - cropW / 2),
        posY = myImage.offsetTop + myImage.height / 2 - cropH / 2;
    var sInnerHtml =
        '<div id="zxxCropBox" style="height:' + cropH + 'px; width:' + cropW + 'px; position:absolute; left:' + posX + 'px; top:' + posY + 'px; border:1px solid black;">' +
        '<div id="zxxDragBg" style="height:100%; background:white; opacity:0.3; filter:alpha(opacity=30); cursor:move"></div>' +
        '<div id="dragLeftTop" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; left:-3px; top:-3px; cursor:nw-resize;"></div>' +
        '<div id="dragLeftBot" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; left:-3px; bottom:-3px; cursor:sw-resize;"></div>' +
        '<div id="dragRightTop" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; right:-3px; top:-3px; cursor:ne-resize;"></div>' +
        '<div id="dragRightBot" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; right:-3px; bottom:-3px; cursor:se-resize;"></div>' +
        '<div id="dragTopCenter" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; top:-3px; left:50%; margin-left:-3px; cursor:n-resize;"></div>' +
        '<div id="dragBotCenter" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; bottom:-3px; left:50%; margin-left:-3px; cursor:s-resize;"></div>' +
        '<div id="dragRightCenter" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; right:-3px; top:50%; margin-top:-3px; cursor:e-resize;"></div> ' +
        '<div id="dragLeftCenter" style="position:absolute; width:4px; height:4px; border:1px solid #000; background:white; overflow:hidden; left:-3px; top:50%; margin-top:-3px; cursor:w-resize;"></div>' +
        '</div>' +
        '<input type="hidden" id="cropPosX" value="' + posX / scaleX + '" />' +
        '<input type="hidden" id="cropPosY" value="' + posY / scaleY + '" />' +
        '<input type="hidden" id="cropImageWidth" value="' + cropW / scaleX + '" />' +
        '<input type="hidden" id="cropImageHeight" value="' + cropH / scaleY + '" />';

    oRelDiv.innerHTML = sInnerHtml;

    var startDrag = function (point, target, kind) {
        //point是拉伸点，target是被拉伸的目标，其高度及位置会发生改变
        //此处的target与上面拖拽的target是同一目标，故其params.left,params.top可以共用，也必须共用
        //初始化宽高
        params.width = getCss(target, "width");
        params.height = getCss(target, "height");
        //初始化坐标
        if (getCss(target, "left") !== "auto") {
            params.left = getCss(target, "left");
        }
        if (getCss(target, "top") !== "auto") {
            params.top = getCss(target, "top");
        }
        //target是移动对象
        point.onmousedown = function (event) {
            params.kind = kind;
            params.flag = true;
            clickFlag = true;
            if (!event) {
                event = window.event;
            }
            var e = event;
            params.currentX = e.clientX;
            params.currentY = e.clientY;
            //防止IE文字选中，有助于拖拽平滑
            point.onselectstart = function () {
                return false;
            };

            document.onmousemove = function (event) {
                let e = event ? event : window.event;
                clickFlag = false;
                if (params.flag) {
                    var nowX = e.clientX, nowY = e.clientY;
                    var disX = nowX - params.currentX, disY = nowY - params.currentY;
                    if (params.kind === "n") {
                        //上拉伸
                        //高度增加或减小，位置上下移动
                        target.style.top = parseInt(params.top) + disY + "px";
                        target.style.height = parseInt(params.height) - disY + "px";
                    } else if (params.kind === "w") {//左拉伸
                        target.style.left = parseInt(params.left) + disX + "px";
                        target.style.width = parseInt(params.width) - disX + "px";
                    } else if (params.kind === "e") {//右拉伸
                        target.style.width = parseInt(params.width) + disX + "px";
                    } else if (params.kind === "s") {//下拉伸
                        target.style.height = parseInt(params.height) + disY + "px";
                    } else if (params.kind === "nw") {//左上拉伸
                        target.style.left = parseInt(params.left) + disX + "px";
                        target.style.width = parseInt(params.width) - disX + "px";
                        target.style.top = parseInt(params.top) + disY + "px";
                        target.style.height = parseInt(params.height) - disY + "px";
                    } else if (params.kind === "ne") {//右上拉伸
                        target.style.top = parseInt(params.top) + disY + "px";
                        target.style.height = parseInt(params.height) - disY + "px";
                        target.style.width = parseInt(params.width) + disX + "px";
                    } else if (params.kind === "sw") {//左下拉伸
                        target.style.left = parseInt(params.left) + disX + "px";
                        target.style.width = parseInt(params.width) - disX + "px";
                        target.style.height = parseInt(params.height) + disY + "px";
                    } else if (params.kind === "se") {//右下拉伸
                        target.style.width = parseInt(params.width) + disX + "px";
                        target.style.height = parseInt(params.height) + disY + "px";
                    } else {//移动
                        target.style.left = parseInt(params.left) + disX + "px";
                        target.style.top = parseInt(params.top) + disY + "px";
                    }
                }

                document.onmouseup = function () {

                    params.flag = false;
                    if (getCss(target, "left") !== "auto") {
                        params.left = getCss(target, "left");
                    }
                    if (getCss(target, "top") !== "auto") {
                        params.top = getCss(target, "top");
                    }
                    params.width = getCss(target, "width");
                    params.height = getCss(target, "height");

                    //给隐藏文本框赋值
                    posX = parseInt(target.style.left);
                    posY = parseInt(target.style.top);
                    cropW = parseInt(target.style.width);
                    cropH = parseInt(target.style.height);
                    if (posX < 0) {
                        posX = 0;
                    }
                    if (posY < 0) {
                        posY = 0;
                    }
                    if ((posX + cropW) > iCurWidth) {
                        cropW = iCurWidth - posX;
                    }
                    if ((posY + cropH) > iCurHeight) {
                        cropH = iCurHeight - posY;
                    }
                    //赋值
                    ID("cropPosX").value = posX;
                    ID("cropPosY").value = posY;
                    ID("cropImageWidth").value = parseInt(ID("zxxCropBox").style.width);
                    ID("cropImageHeight").value = parseInt(ID("zxxCropBox").style.height);

                };
            }
        };
    };


    //绑定拖拽
    startDrag(ID("zxxDragBg"), ID("zxxCropBox"), "drag");
    //绑定拉伸
    startDrag(ID("dragLeftTop"), ID("zxxCropBox"), "nw");
    startDrag(ID("dragLeftBot"), ID("zxxCropBox"), "sw");
    startDrag(ID("dragRightTop"), ID("zxxCropBox"), "ne");
    startDrag(ID("dragRightBot"), ID("zxxCropBox"), "se");
    startDrag(ID("dragTopCenter"), ID("zxxCropBox"), "n");
    startDrag(ID("dragBotCenter"), ID("zxxCropBox"), "s");
    startDrag(ID("dragRightCenter"), ID("zxxCropBox"), "e");
    startDrag(ID("dragLeftCenter"), ID("zxxCropBox"), "w");


    //图片不能被选中，目的在于使拖拽顺滑
    ID("myCanvas").onselectstart = function () {
        return false;
    };
    img.onselectstart = function () {
        return false;
    };
};
function setImage(image,response){
	$("#images").empty();
	$(image).val(response);
	$("#images").append("<img onclick='openImage(\""+image+"\")' src='/kaptcha/"+response+"?t="+new Date().getTime()+"' alt='通用的占位符缩略图' width='200px' height='200px' class='img-thumbnail' width='200' height='200'/>")
}
function remove(response){
	$("#selectImages").val($("#selectImages").val().replace(response,""));
	showImages();
}
function showImages(){
	var imgs = $("#selectImages").val().split(",");
	$("#images").empty();
	for(var i = 0 ; i < imgs.length ; i++ ){
		if(imgs[i]){
			setAddImage(imgs[i]);
		}
	}
}
function setAddImage(response){
	var removeBtn = '<button type="button" onclick="remove(\''+response+'\')" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>';
	var img = "<img onclick='openImage(\""+response+"\")' src='/kaptcha/"+response+"?t="+new Date().getTime()+"' alt='通用的占位符缩略图' width='200px' height='200px' class='img-thumbnail' width='200' height='200'/>" ;
	$("#images").append("<li><div class='card highlight'>"+img+removeBtn+"</div></li>");
}