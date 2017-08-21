$(function() {
	//初始化整个权限树
	jsTreeInit();
	
	//加载字典
	var categorys=['ENABLE','ROLE_TYPE'];
	loadDic(categorys);
	
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();
	//初始化table按钮功能
	var oButton = new ButtonInit();
	oButton.Init();
	//初始化其他页面功能
	initOtherFunction();
	
	$('#collapseOne').on('hide.bs.collapse', function () {
		$(".fa.fa-chevron-up").attr("class","fa fa-chevron-down");
	});
	$('#collapseOne').on('show.bs.collapse', function () {
		$(".fa.fa-chevron-down").attr("class","fa fa-chevron-up");
	})
	$(".fa.fa-times").click(function(){
		$(".gray-bg").hide();
	});
	
});

function jsTreeInit() {
	$.ajax({
        type: "post",
        url: "roleMenuTree",
        data:{},
        dataType: "json",
        success: function (result) {
	        	$("#roleTree").jstree({
	        		'plugins' : [ "wholerow", "checkbox", "types" ],
	        		'core' : {
	        			'data' : result,
	        		},
	        		"types" : {
	        			"default" : {
	        				"icon" : "fa fa-folder icon-state-warning icon-lg"
	        			},
	        			"file" : {
	        				"icon" : "fa fa-file icon-state-warning icon-lg"
	        			}
	        		}
	        	});
        },
        error: function (result) {
    			swal("角色权限树加载失败", "", "error");
        }
	});
}

/**
 * 初始化table数据
 */
var TableInit = function() {
	$('#data-list-table').bootstrapTable('showLoading');	
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#data-list-table').bootstrapTable({
			url : 'dataList', // 请求后台的URL（*）
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
			showFooter : false,//是否显示列脚
			showColumns : true, // 是否显示所有的列
			showRefresh : false, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			singleSelect : false,
			height: 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			idField:'id',
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
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
				valign: 'middle',
				sortable:true,
			}, {
				field : 'roleName',
				title : '角色名称',
				valign: 'middle',
				sortable:true,
			},{
				field : 'roleType',
				title : '角色类型',
				valign: 'middle',
				sortable:true,
				formatter : dic_value_text("ROLE_TYPE"),
			}, {
				field : 'enable',
				title : '字典状态',
				valign: 'middle',
				sortable:true,
				formatter : function(value, row, index) {
					if(value == 1){
						return "<a class='btn btn-danger btn-rounded btn-xs'>锁定<a>";
					}else if(value == 0){
						return "<a class='btn btn-success btn-rounded btn-xs'>有效<a>";
					}
				}
			}],
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
			enable : $("#enable_search").val(),
			roleName : $("#roleName_search").val(),
			roleType : $("#roleType_search").val(),
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
		$("#searchBtn").click(function(){
			$("#data-list-table").bootstrapTable('refresh',{query:{page:1}});
		});
		
		$("#reset").click(function(){
			$("#roleName_search").val("");
			$("#roleType_search").val("");
			$("#enable_search").val("");
		});
		
		
		//新增功能
		$("#add").click(function(){
			$(".modal-title").html("新增角色");
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
			$("#roleName").val(rows[0].roleName);
			$("#roleType").val(rows[0].roleType);
			$(".modal-title").html("编辑角色");
			$("#myModal").modal('show');
		});
		
		$("#cancel").click(function(){
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
		        title: "您确定要锁定这"+length+"条信息吗",
		        type: "warning",
		        showCancelButton: true,
		        showLoaderOnConfirm: true,
		        closeOnConfirm: false
		    }, function (isConfirm) {
		    		if(isConfirm){
			    		$.ajax({
			    			url:"cancel",
			    			type : 'post',
						async : false,
						data : {
							"ids" : ids
						},
						traditional : true,
			    			success:function(data){
			    				if (data.success) {
			    					$('#data-list-table').bootstrapTable('refresh');
			    					swal("锁定成功！", "您已经锁定了这"+length+"条信息。", "success");					
			    				} else {
			    					swal(data.msg, "", "error");
			    				}
			    			},
			    			error : function(){
			    				swal('异常提交', "", "error");
			    			}
			    		});
		    		}
		    });
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
		    }, function (isConfirm) {
			    	if(isConfirm){
			    		$.ajax({
			    			url:"delete",
			    			type : 'post',
						async : false,
						data : {
							"ids" : ids
						},
						traditional : true,
			    			success:function(data){
			    				if (data.success) {
			    					$('#data-list-table').bootstrapTable('refresh');
			    					 swal("删除成功！", "您已经永久删除了这"+length+"条信息。", "success");
			    				} else {
			    					swal(data.msg, "", "error");
			    				}
			    			},
			    			error : function(){
			    				swal('异常提交', "", "error");
			    			}
			    		});
		    		}
		       
		    });
		});
		

		$("#roleManager").click(function(){
			var rows = $('#data-list-table').bootstrapTable(
			'getAllSelections');
			if (rows == null || rows.length <= 0 || rows.length > 1) {
				swal("请选择一条记录", "", "warning");
				return;
			}
			var roleId = rows[0].id;
			$.ajax({
				url : 'queryRoleInfo',
				type : 'post',
				async : false,
				data : {
					"roleId" : roleId
				},
				dataType:'json',
				traditional : true,
				success : function(result) {
					if(result!=null){
						$("#curRoleId").val(roleId);
						$('#roleTree').jstree("close_all");
						$('#roleTree').jstree("deselect_all");
						//遍历该角色已有权限
						var menuIds = result.menuIds;
						$.each(menuIds, function(i, value) {
							var selectedNode = $('#roleTree').jstree('get_node', value);
							if(selectedNode.children == ''){
								$('#roleTree').jstree("select_node", selectedNode,true);
							}
						});
						$("#treeModal").modal("show");
					}else{
						swal("请求错误",'', "error");
					}
				},
				error : function(data) {
					swal("请求异常", "", "error");
				}
			});
		});
	}
	return oButtonInit;
}

function initOtherFunction(){
	$("#save").click(function() {
		var options = {
			url:"save",
			dataType:'json',
			type : 'post', // get和post两种方式
			clearForm : true, // 表示成功提交后清除所有表单字段值
			resetForm : true,// 表示成功提交后重置所有字段
			beforeSubmit:function(){
				var roleName = $("#roleName").val();
				if(roleName == ""){
					swal("请输入角色名称", "", "warning");
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
	
	$("#treeSave").click(function(){
		var checked = $("#roleTree").jstree().get_selected();
		var roleId = $("#curRoleId").val();
		var menuIds = [] ;
		for(var i = 0; i<checked.length; i++){
			var item = checked[i];
			if(item != '-1'){
				if(menuIds.indexOf(item)==-1){
					menuIds.push(item);
				}
				findParent(menuIds,item);
			}
		}
		$.ajax({
			type: "post",
	        url: "updateRoleMenu",
	        data:{
	        		"menuIds":menuIds,
	        		"roleId":roleId
	        	},
	        traditional : true,
	        success : function(data) {
				if (data.success) {
					$("#treeModal").modal("hide");
					swal(data.msg, "", "success");					
				} else {
					swal(data.msg, "", "error");
				}
			},
	        error : function(){
	        		swal('异常提交', "", "error");
	        }
		});
	});
	
}

function findParent(menuIds,item){
	//查看是否有父级节点
	if($('#roleTree').jstree().get_parent(item)){
		//数组中元素是否重复
		if(menuIds.indexOf(item)==-1){
			//系统最上级元素不添加到数组中
			if(item != "-1"){
				menuIds.push(item);
			}
		}
		findParent(menuIds,$('#roleTree').jstree().get_parent(item))
	}
}