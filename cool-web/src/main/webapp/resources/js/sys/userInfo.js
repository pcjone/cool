$().ready(function() {
	// 外部js调用
	laydate({
		elem : '#birthDay', // 目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式
							// '#id .class'
		event : 'focus' // 响应事件。如果没有传入event，则按照默认的click
	});

	$("#passwordSave").click(function(){
		var account = $("#account").val();
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		if(oldPassword == "" ){
			swal("密码不能为空", "", "warning");
			return;
		}
		if(newPassword == "" && newPassword.length <6){
			swal("密码不能为空或密码长度不能低于5位", "", "warning");
			return;
		}
		$.ajax({
			url:'user/editPassword',
			data:{"account":account,"oldPassword":oldPassword,"newPassword":newPassword},
			async:false,
			type : 'post',
			success:function(data){
				if(data.success){
					$('#passwordModal').modal('hide');
					swal(data.msg, "", "success");
				}else{
					swal(data.msg, "", "error");
				}
			},
			error:function(data){
				swal('异常提交', "", "error");
			}
		});
	});
	
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		submitHandler : function(form) {
			var options = {
				url : "user/user/update",
				type : "post",
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$("#userInfoModal").modal("hide");
						swal("保存成功", '', "success");
					} else {
						swal(data.msg, '', "success");
					}
				},
				error : function(data) {
					swal("请求错误", '', "error");
				},
			};
			$("#signupForm").ajaxSubmit(options);
		},
		rules : {
			userName : {
				required : true,
				minlength : 2
			},
			phone : {
				required : true,
				minlength : 11,
				maxlength : 11,
			},
			idCard : {
				required : true,
				minlength : 18,
				maxlength : 18,
			},
			email : {
				required : true,
				email : true
			},
			birthDay : {
				required : true
			},
			agree : "required"
		},
		messages : {
			userName : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上"
			},
			phone : {
				required : icon + "请输入您的电话",
				minlength : icon + "电话必须11个字符",
				maxlength : icon + "电话必须11个字符",
			},
			idCard : {
				required : icon + "请输入您的身份证",
				minlength : icon + "身份证必须18个字符",
				maxlength : icon + "身份证必须18个字符"
			},
			email : icon + "请输入您的E-mail",
			birthDay : {
				required : icon + "请选择您的生日",
			}
		}
	});
});

function modelShow(){
	$('#passwordModal').modal('show');
}

function userInfo(id){
	 $.ajax({
		url:"user/user/"+id,
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var user = data.user;
			$("#userName").val(user.userName);
			$("#phone").val(user.phone);
			$("#idCard").val(user.idCard);
			$("#email").val(user.email);
			$("#birthDay").val(user.birthDay);
			$("#userInfoModal").modal("show");	
		},
		error:function(data){
			swal("请求错误",'', "error");
		}
	}); 
	//$("#J_iframe").attr("src","user/user/"+id);
	//$("#J_iframe").reload();
}
