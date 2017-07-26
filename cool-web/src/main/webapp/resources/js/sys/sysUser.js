$(function() {
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();
	//初始化table按钮功能
	var oButton = new ButtonInit();
	oButton.Init();
	//初始化其他页面功能
	initOtherFunction();
});

/**
 * 初始化table数据
 */
var TableInit = function() {
	$('#data-list-table').bootstrapTable('showLoading');	
	var operator = '<div class="btn-group">'+
	                   '<button data-toggle="dropdown" class="btn btn-warning btn-xs dropdown-toggle">操作 <span class="caret"></span>'+
	                   '</button>'+
	                   '<ul class="dropdown-menu">'+
		                   '<li><a href="buttons.html#">置顶</a>'+
		                   '</li>'+
		                   '<li><a href="buttons.html#" class="font-bold">修改</a>'+
		                   '</li>'+
		                   '<li><a href="buttons.html#">禁用</a>'+
		                   '</li>'+
		                   '<li class="divider"></li>'+
		                   '<li><a href="buttons.html#">删除</a>'+
		                   '</li>'+
	                   '</ul>'+
                   '</div>';

	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#data-list-table').bootstrapTable({
			url : 'list', // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			exportDataType : 'selected', //导出类型
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showHeader : true,//是否显示列头
			showFooter : true,//是否显示列脚
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			showToggle : true,//是否显示 切换试图（table/card）按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			singleSelect : false,
			height: 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			idField:'id',
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
			// 设置为limit可以获取limit, offset, search, sort, order
			queryParamsType : "undefined",
			contentType : "application/x-www-form-urlencoded",
			columns : [
			{
				checkbox:true
			},
			{
				field : 'id',
				title : '序号',
				align: 'center',
				valign: 'middle',
			}, {
				field : 'account',
				title : '账号',
				align: 'center',
				valign: 'middle',
			}, {
				field : 'userName',
				title : '姓名',
				align: 'center',
				valign: 'middle',
			},{
				field : 'sex',
				title : '性别',
				align: 'center',
				valign: 'middle',
				formatter : function(value, row, index) {
					if(value == 0){
						return "未知";
					}else if(value == 1){
						return "男";
					}else if(value == 2){
						return "女";
					}
				}
			}, {
				field : 'phone',
				title : '电话',
				align: 'center',
			},{
				field : 'birthDay',
				title : '生日',
				align: 'center',
//				formatter : function(value, row, index) {
//
//				}
			},{
				field : 'enable',
				title : '状态',
				align: 'center',
				valign: 'middle',
				formatter : function(value, row, index) {
					if(value == 0){
						return "锁定";
					}else if(value == 1){
						return "有效";
					}
				}
			},{
				field : 'do',
				title : '操作',
				align: 'center',
				formatter : function(value, row, index) {
					return operator;
				}
			} ],
			detailFormatter : function(index, row) {
			},
			onExpandRow : function(index, row, detail) {
			}
		});
	};
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			pageSize : params.pageSize,
			pageNum : params.pageNumber, // 页码
			sort : params.sortName,
			order : params.sortOrder,
		};
		return temp;
	}
	$('#data-list-table').bootstrapTable('hideLoading');
	return oTableInit;
};

/**
 * 初始化按钮
 */
var ButtonInit = function() {
	var oButtonInit = new Object();
	//初始化Table
	oButtonInit.Init = function() {
		//新增功能
		$("#add").click(function(){
			$('#signupForm')[0].reset();
			$("#myModal").modal('show');
		});
		
		$("#edit").click(function(){
			var rows = $('#data-list-table').bootstrapTable(
			'getAllSelections');
			if (rows == null || rows.length <= 0 || rows.length > 1) {
				swal("请选择一条记录", "", "warning");
				return;
			}
			$("#id").val(rows[0].id);
			$("#account").val(rows[0].account);
			$("#myModal").modal('show');
		});
		
		$("#delete").click(function(){
			var rows = $('#data-list-table').bootstrapTable('getAllSelections');
			if (rows == null || rows.length <= 0) {
				swal("请选择一条记录", "", "warning");
				return;
			}
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			var length = ids.length;
			swal({
		        title: "您确定要删除这"+length+"条信息吗",
		        text: "删除后将无法恢复，请谨慎操作！",
		        type: "warning",
		        showCancelButton: true,
		        showLoaderOnConfirm: true,
		        closeOnConfirm: false
		    }, function () {
		        swal("删除成功！", "您已经永久删除了这"+length+"条信息。", "success");
		    });
		});
	}
	return oButtonInit;
}

function initOtherFunction(){
	$("#save").click(function() {
		var options = {
			dataType:'json',
			type : 'post', // get和post两种方式
			clearForm : true, // 表示成功提交后清除所有表单字段值
			resetForm : true,// 表示成功提交后重置所有字段
			beforeSubmit:function(){
				var account = $("#account").val();
				if(account == ""){
					swal("账号不能为空", "", "warning");
					return false;
				}	
				var password = $("#password").val();
				var confirm_password = $("#confirm_password").val();
				if(password == "" || confirm_password == ""){
					swal("请输入密码", "", "warning");
					return false;
				}
				if(password != confirm_password){
					swal("两次输入的密码不一致", "", "warning");
					return false;
				}
			},
			success : function(data) {
				if (data.success) {
					$("#myModal").modal('hide');
					$('#data-list-table').bootstrapTable('refresh');
					swal(data.msg, "", "success");
					
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