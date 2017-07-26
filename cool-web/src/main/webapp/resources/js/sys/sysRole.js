$(document).ready(function() {
	
	$("#data-table").bootstrapTable({
		url : "list",
		method : 'post', // 请求方式（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		dataType : 'json',
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）	
		sortable : true, // 是否启用排序
		sortName : "id", // 排序字段名称
		sortOrder : "desc", // 排序方式
		//queryParams : oTableInit.queryParams,// 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, // 是否启用点击选中行
		uniqueId : "recordId", // 每一行的唯一标识，一般为主键列
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		//设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
		//设置为limit可以获取limit, offset, search, sort, order 
		queryParamsType : "undefined",
		contentType : "application/x-www-form-urlencoded",
		columns : [ {
			field : 'id',
			title : '序号',
			width : 60,
			sorttype : "int",
			search : true
		}, {
			field : 'account',
			title : 'account',
			width : 90,
		}, {
			field : 'password',
			title : 'password',
			width : 100
		}, {
			field : 'userType',
			title : 'userType',
			width : 80,
		}, {
			field : 'userName',
			title : 'userName',
			width : 80,
		}, {
			field : 'total',
			title : 'total',
			width : 80,
		}, {
			field : 'note',
			title : 'note',
			width : 100,
			sortable : false
		} ],
		detailFormatter : function(index, row) {
			
		},
		onExpandRow : function(index, row, detail) {}
	});
});