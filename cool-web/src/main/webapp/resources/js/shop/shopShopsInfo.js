$(function() {
	initOtherFunction();
});


function refush(){
	$("#refush").click();
}

function createShop(){
	$(".modal-title").html("商铺创建");
	$('#signupForm')[0].reset();
	$("#myModal").modal("show");
}

function initOtherFunction(){
	$("#refush").click(function(){
		window.location.href="list";
	});
	$("#update").click(function(){
		$(".modal-title").html("商铺编辑");
		var id = $("#id").val();
		$.ajax({
			url:"queryById",
			data:{id:id},
			type:"post",
			dataType:"json",
			success:function(data){
				$("#shopName").val(data.shopName);
				$("#shopIntroduce").val(data.shopIntroduce);
				$("#shopType").val(data.shopType);
				$("#id").val(data.id);
				$("#preview").attr("src","../image/view?path="+data.shopImage);
				$("#myModal").modal("show");
			},
			error:function(data){
				swal(data.msg, "", "error");
			}
		});
	});
	$("#save").click(function() {
		var options = {
			url:"save",
			//dataType:'json',
			type : 'post', // get和post两种方式
			clearForm : true, // 表示成功提交后清除所有表单字段值
			resetForm : true,// 表示成功提交后重置所有字段
			beforeSubmit:function(){
				var shopName = $("#shopName").val();
				if(shopName.trim() == ""){
					swal("请输入商铺名称", "", "warning");
					return false;
				}
				var shopType = $("#shopType").val();
				if(shopType.trim() == ""){
					swal("请输入商铺类型", "", "warning");
					return false;
				}
				var image = $("#image").val();
				if(image == ""){
					swal("请选择图片", "", "warning");
					return false;
				}
			},
			success : function(data) {
				if (data.success) {
					$("#myModal").modal('hide');
					swal(data.msg, "", "success");
					refush();
				} else {
					swal(data.msg, "", "error");
				}
			},
			error : function(){
				swal('异常提交', "", "error");
			}
		};
		$("#signupForm").ajaxSubmit(options);
	});
}

//预览照片
function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("preview");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}