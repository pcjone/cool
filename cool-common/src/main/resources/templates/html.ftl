<#assign bathPath=springMacroRequestContext.getContextPath() />
<!-- 引入自定义标签 -->
<#include "/tags/tag.html"/>
<!-- 引入本地标签 -->
<#include "/tags/local_tags.html"/>
<!DOCTYPE html>
<html>
<!-- 引入css文件 -->
<#include "/public/header.html"/>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>${title}</h5>
				<div class="ibox-tools">
					<a class="collapse-link" data-toggle="collapse" href="#collapseOne"> <i
						class="fa fa-chevron-up"></i>
					</a>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div id="collapseOne" class="ibox-content collapse in">
				<div class="row">
					<form method="post" class="form-horizontal" id="searchForm">
						<div class="col-sm-3 col-md-3">
						</div>
						<div class="col-sm-3 col-md-3">
						</div>
						<div class="col-sm-3 col-md-3">
						</div>

						<div class="col-sm-3 col-md-3">
							<div style="float: right;">
								<button type="button" class="btn btn-sm btn-success" id="searchBtn">
									&nbsp;查询 <i class="fa fa-arrow-right icon-on-right"></i>
								</button>
								<button type="button" class="btn btn-sm btn-reset" id="reset">
									&nbsp;重置 <i class="fa fa-undo"></i>
								</button>
							</div>
						</div>
					</form>
					
				</div>
				<hr>
				<div class="btn-group hidden-xs" id="toolbar" role="group">
					<@shiro.hasPermission name="${sys_name}.${table_name_small}.add"> 
					<button id="add" type="button" class="btn btn-outline btn-default">
						<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>&nbsp;新增
					</button>
					</@shiro.hasPermission>
					<@shiro.hasPermission name="${sys_name}.${table_name_small}.update"> 
					<button id="edit" type="button" class="btn btn-outline btn-default">
						<i class="glyphicon glyphicon-edit" aria-hidden="true"></i>&nbsp;编辑
					</button>
					</@shiro.hasPermission>
					<@shiro.hasPermission name="${sys_name}.${table_name_small}.cancel"> 
					<button id="cancel" type="button" class="btn btn-outline btn-default">
						<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>&nbsp;锁定
					</button>
					</@shiro.hasPermission>
					<@shiro.hasPermission name="${sys_name}.${table_name_small}.delete"> 
					<button id="delete" type="button" class="btn btn-outline btn-default">
						<i class="glyphicon glyphicon-remove-circle" aria-hidden="true"></i>&nbsp;删除
					</button>
					</@shiro.hasPermission>
				</div>
				<table id="data-list-table">

				</table>
			</div>
		</div>
	</div>
	
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated fadeIn">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h1 class="modal-title"></h1>
				</div>
				<div class="modal-body">
					 <form class="form-horizontal m-t" action="save" method="post" id="signupForm">
					 		<input id="id" name="id" type="text" style="display:none"/>
					 	</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="save">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<#include "/public/base_js.html"/>
	<script src="${bathPath}/resources/js/${sys_name}/${table_name_small}.js"></script>
</body>
</html>
