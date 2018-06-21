<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">组织机构</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">资源管理</span></li>
		</ul>
		
		<div class="row">
			<div class="col-md-12">
				<div class="fixed-table-toolbar" id="itemFileEditDivMenu">
					<div class="bs-bars pull-left">
						<button id="itemFileMenuAdd" class="btn btn-sm">
							<i class="fa fa-plus"></i> 新增
						</button>
						<button id="itemFileMenuEdit" class="btn btn-sm">
							<i class="fa fa-edit"></i> 修改
						</button>
						<shiro:hasPermission name="resource:delete">
							<button id="itemFileMenuDelb" class="btn btn-sm btn-danger">
								<i class="glyphicon glyphicon-remove"></i> 删除
							</button>
						</shiro:hasPermission>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">系统资源</h3>
					</div>
					<div class="panel-body" id="itemFileTypeTree">
						<ul id="resourceTree" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade" id="resourceModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header padding-sty">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="modelTitle"></h3>
			</div>

			<form id="resourceForm" class="form-horizontal" method="POST">
				<input type="hidden" id="resoureId" name="id" />
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">上级资源</label>
					<div class="col-sm-8">
						<input type="hidden" id="parentId" name="parentId">
						<input type="hidden" id="parentIds" name="parentIds">
						
						<input type="text" id="parentname" name="parentname" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">*名称</label>
					<div class="col-sm-8">
						<input type="text" id="name"
							name="name" class="form-control">
					</div>
				</div>
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">*类型</label>
					<div class="col-sm-8">
						<select class="form-control" name="type" id="type">
							<option value="menu">菜单</option>
							<option value="button">按钮</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*权限</label>
					<div class="col-sm-8">
						<input type="text" id="permission" name="permission" class="form-control"
							placeholder="如：resource:add" required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">url</label>
					<div class="col-sm-8">
						<input type="text" id="url" name="url"
							class="form-control" placeholder="路径">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图标样式</label>
					<div class="col-sm-8">
						<input type="text" id="classname" name="classname"
							class="form-control" placeholder="如：fa fa-home">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号</label>
					<div class="col-sm-8">
						<input type="number" id="orderno" name="orderno"
							class="form-control" placeholder="" >
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
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${ctx}/static/system/resource.js?version=${staticVersion}"></script>
<script>
	var rootPath = '${ctx}';
</script>
