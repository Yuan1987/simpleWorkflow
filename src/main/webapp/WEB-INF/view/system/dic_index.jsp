<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">系统管理</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">数据字典</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">字段类型</h3>
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
							<i class="fa fa-plus"></i> 新增
						</button>
						<button id="import" class="btn btn-sm">
							<i class="fa fa-upload"></i> 导入
						</button>
						<button id="btn-delete" class="btn btn-sm btn-danger">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
					</div>
					<table id="dicTable" class="table"
						data-side-pagination="server" data-pagination="true"
						data-row-style="rowStyle" data-side-pagination="server"
		  				data-page-list="[10,15, 20, 50, 100, 200]"
						data-search="true" data-show-refresh="true">
						<thead>
							<tr>
								<th data-checkbox="true"></th>
								<th data-field="kindname" data-halign="center"
									data-align="center">类型名称</th>
								<th data-field="kind" data-halign="center"
									data-align="center">类型</th>
								<th data-field="code" data-halign="center"
									data-align="center">代码</th>
								<th data-field="detail" data-halign="center"
									data-align="center">值</th>
								<th data-formatter="dic.operation" data-align="center">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
<div class="modal fade" id="dicModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>


<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/ztree/js/jquery.ztree.exedit.js"></script>

<script src="${ctx}/static/system/dic.js?v1.0"></script>
<script>
	var rootPath = '${ctx}';
</script>
