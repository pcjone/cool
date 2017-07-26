function login() {
	var account = $("#account").val();
	var password = $("#password").val();
	if (account.trim() == "") {
		alert("用户名不能为空！");
		return;
	}
	if (password.trim() == "") {
		alert("密码不能为空！");
		return;
	}
	var options = {
		success : function(data) {
			if (data.success) {
				window.location.href="main";
			} else {
				alert(data.msg);
			}
		}
	};
	$("#formId").ajaxForm(options);
}
