<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />

<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">系统管理</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">系统日志</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
			<div class="col-md-12">
				<div class="m-top-up-15">
					<!-- <div class="fixed-table-toolbar">
						<div class="bs-bars pull-left">
							<div id="toolbar">
								<button id="dicAdd" class="btn btn-sm">
									<i class="fa fa-plus"></i> 新增
								</button>
								<button id="import" class="btn btn-sm">
									<i class="fa fa-upload"></i> 导入
								</button>
								<button id="btn-delete" class="btn btn-sm btn-danger">
									<i class="glyphicon glyphicon-remove"></i> 删除
								</button>
							</div>
						</div>
					</div> -->
					<table id="syslogTable" class="table"
						data-side-pagination="server" data-pagination="true"
						data-row-style="rowStyle" data-side-pagination="server"
		  				data-page-list="[10,15, 20, 50, 100, 200]"
						data-search="true" data-show-refresh="true">
						<thead>
							<tr>
								<th data-checkbox="true"></th>
								<th data-field="type" data-halign="center"
									data-align="center">操作类型</th>
								<th data-field="username" data-halign="center"
									data-align="center">操作人</th>
								<th data-field="msg" data-halign="center"
									data-align="center">内容</th>
								<th data-field="createdtime" data-halign="center"
									data-align="center">操作时间</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/model/syslog.js?${staticVersion}"></script>
<script>
	var rootPath = '${ctx}';
</script>
