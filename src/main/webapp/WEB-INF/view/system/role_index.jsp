<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">组织机构</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">角色管理</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
			<div class="form-horizontal" role="form">
				<div class="row">
					<div class="col-md-12" id="itemFileRightDiv">
						<div class="m-top-up-15">
							<div class="fixed-table-toolbar">
								<div class="bs-bars pull-left">
									<div id="toolbar">
										<shiro:hasPermission name="role:add">
											<button id="add" class="btn btn-sm">
												<i class="fa fa-plus"></i> 新增
											</button>
										</shiro:hasPermission>
									</div>
								</div>
							</div>
							<table id="roleTable" class="table"
								data-side-pagination="server" data-pagination="true"
								data-row-style="rowStyle" data-side-pagination="server"
				  				data-page-list="[10,15, 20, 50, 100, 200]"
								data-search="true" data-show-refresh="true"
								data-search-on-enter-key="true">
								<thead>
									<tr>
										<th data-checkbox="true"></th>
										<th data-field="displayname" data-halign="center"
											data-align="center">角色名称</th>
										<th data-field="description" data-halign="center"
											data-align="center">描述</th>
										<th data-formatter="role.operation" data-align="center">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="roleModal" tabindex="-1" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header padding-sty">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="roleModelTitle"></h3>
			</div>
			
			<form id="roleForm" class="form-horizontal" method="POST">
				<input type="hidden" id="roleId" name="id" />
				<!-- <div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">*所属组织</label>
					<div class="col-sm-8">
						<input type="hidden" id="organizationid" name="organizationid" readonly="readonly" >
						<input type="text" id="orgName" name="orgName" class="form-control" readonly="readonly">
					</div>
				</div> -->
				<div class="form-group margin-top-10" style="display: none;">
					<label class="col-sm-2 control-label">*名称</label>
					<div class="col-sm-8">
						<input type="text" id="rolename" name="name"
							class="form-control" placeholder="名称" >
					</div>
				</div>
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">*名称</label>
					<div class="col-sm-8">
						<input type="text" id="roledisplayname" name="displayname"
							class="form-control" placeholder="显示名称"  required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*角色类型</label>
					<div class="col-sm-8">
						<select name="typeid" class="form-control" id="roletypeid">
							<c:forEach items="${roleType}" var="t">
								<option value="${t.id}">${t.displayname }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*拥有权限</label>
					<div class="col-sm-8">
						 <jsp:include page="../common/resources.jsp"></jsp:include>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">描述</label>
					<div class="col-sm-8">
						<input type="text" id="description" name="description"
							class="form-control" placeholder="角色描述">
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

<script src="${ctx}/static/common/baseTree.js"></script>
<script src="${ctx}/static/system/role.js"></script>
<script>
	var rootPath = '${ctx}';
</script>
