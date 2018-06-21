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
			<li><span class="active">流程管理</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
					<div class="col-md-12">
						<div class="m-top-up-15">
							<div class="fixed-table-toolbar">
								<div class="bs-bars pull-left">
									<div id="toolbar">
										<button id="add" class="btn btn-sm">
											<i class="fa fa-plus"></i> 新增
										</button>
									</div>
								</div>
							</div>
							<table id="processTable" class="table"
								data-side-pagination="server" data-pagination="true"
								data-row-style="rowStyle" data-side-pagination="server"
				  				data-page-list="[10,15, 20, 50, 100, 200]"
								data-search="true" data-show-refresh="true" data-show-toggle="true"
								data-show-columns="true"
								data-show-export="true">
								<thead>
									<tr>
										<th data-checkbox="true"></th>
										<th class="center" data-field="id">流程ID</th>
										<th class="center" data-field="deploymentId">部署ID</th>
										<th class="center" data-field="thekey">KEY</th>
										<th class="center" data-field="name">流程名称</th>
										<th class="center" data-field="resourceName">资源名称</th>
										<th class="center" data-field="revision">版本号</th>
										<th class="center" data-field="diagramResourceName" data-formatter="process.ck">流程图片</th>
										<th data-formatter="process.operation" data-align="center">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
<div class="modal fade" id="processModal" tabindex="-1"></div>

<script src="${ctx}/static/system/process.js"></script>
<script>
	var rootPath = '${ctx}';
</script>
