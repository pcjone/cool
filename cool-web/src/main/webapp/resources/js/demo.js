$(function() {
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();
	
	
	$("#demo1").click(function (){
		swal({
	        title: "欢迎使用SweetAlert",
	        text: "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
	    });
	});
	
	$('#demo2').click(function () {
        swal({
            title: "太帅了",
            text: "小手一抖就打开了一个框",
            type: "success"
        });
    });

    $('#demo3').click(function () {
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            swal("删除成功！", "您已经永久删除了这条信息。", "success");
        });
    });

    $('#demo4').click(function () {
		        swal({
			title : "您确定要删除这条信息吗",
			text : "删除后将无法恢复，请谨慎操作！",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "是的，我要删除！",
			cancelButtonText : "让我再考虑一下…",
			closeOnConfirm : false,
			closeOnCancel : false
		}, function(isConfirm) {
			if (isConfirm) {
				swal("删除成功！", "您已经永久删除了这条信息。", "success");
			} else {
				swal("已取消", "您取消了删除操作！", "error");
			}
		});
	});
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
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 2, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : false,
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height: 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
			// 设置为limit可以获取limit, offset, search, sort, order
			queryParamsType : "undefined",
			contentType : "application/x-www-form-urlencoded",
			columns : [ {
				checkbox : true
			}, {
				field : 'id',
				title : '序号',
			}, {
				field : 'account',
				title : 'account',
			}, {
				field : 'password',
				title : 'password',
			},{
				field : 'Time',
				title : '时间',
				formatter : function(value, row, index) {

				}
			}, {
				field : 'do',
				title : '操作',
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
			test : 1
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
			
		});
		
		$("#edit").click(function(){
			var rows = $('#data_list_table').bootstrapTable(
			'getAllSelections');
			if (rows == null || rows.length <= 0 || rows.length > 1) {
				swal("请选择一条记录");
				return;
			}
		});
		
		$("#delete").click(function(){
			var rows = $('#data_list_table').bootstrapTable('getAllSelections');
			if (rows == null || rows.length <= 0) {
				swal("提示", "请选择一条记录");
				return;
			}
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
		});
	}
	return oButtonInit;
}

