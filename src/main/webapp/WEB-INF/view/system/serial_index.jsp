<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link href="${ctx}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">系统管理</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">序列管理</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
			<div class="col-md-4">
				<div class="fixed-table-toolbar" id="itemFileEditDivMenu">
					<div class="bs-bars pull-left">
						<button id="itemFileMenuAdd" class="btn btn-sm">
							<i class="fa fa-plus"></i> 新增
						</button>
						<button id="itemFileMenuEdit" class="btn btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>
						<shiro:hasPermission name="org:delete">
							<button id="itemFileMenuDelb" class="btn btn-sm btn-danger">
								<i class="glyphicon glyphicon-remove"></i> 删除
							</button>
						</shiro:hasPermission>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">序列</h3>
					</div>
					<div class="panel-body" id="itemFileTypeTree">
						<ul id="treeDemo" class="ztree">
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-8">
					<div id="toolbar">
						<button id="dicAdd" class="btn btn-sm">
							<i class="fa fa-plus"></i> 增加
						</button>
					</div>
					<table id="usTable" class="table"
						data-side-pagination="server" data-pagination="true"
						data-row-style="rowStyle" data-side-pagination="server"
		  				data-page-list="[10,15, 20, 50, 100, 200]"
						data-show-refresh="true">
						<thead>
							<tr>
								<th data-field="sname" data-align="center">序列</th>
								<th data-field="type" data-align="center" data-formatter="serial.type" >类型</th>
								<th data-field="displayname" data-align="center">姓名</th>
								<!-- <th data-field="idnumber" data-align="center">身份证号</th> -->
								<th data-field="accountname" data-align="center">账号</th>
								<th data-formatter="serial.operation" data-align="center">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
<div class="modal fade" id="serialModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header padding-sty">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="modelTitle"></h3>
			</div>
			<form id="serialForm" class="form-horizontal" method="POST">
				<input type="hidden" id="parentid" name="parentid" value="0"/>
				<input type="hidden" id="id" name="id" value=""/>
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">*名称</label>
					<div class="col-sm-8">
						<input type="text" id="name" name="name"
							class="form-control" placeholder="名称"  required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*编号</label>
					<div class="col-sm-8">
						<input type="text" id="code" name="code" required="required"
							class="form-control" placeholder="编号">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确认</button>
					<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="modal fade" id="theModal" tabindex="-1"></div>

<script src="${ctx}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.exedit.js"></script>

<script src="${ctx}/static/system/serial.js"></script>
<script>
	var rootPath = '${ctx}';
</script>
