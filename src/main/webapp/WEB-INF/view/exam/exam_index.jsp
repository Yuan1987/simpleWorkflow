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
			<li><a href="#">培训、考试</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">成绩上报</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
					<div class="col-md-12">
						<div class="m-top-up-15">
							<div class="fixed-table-toolbar">
								<div class="bs-bars pull-left">
									<div id="toolbar">
										<button id="exAdd" class="btn btn-sm">
											<i class="fa fa-plus"></i> 新增
										</button>
										<!-- <button id="import" class="btn btn-sm">
											<i class="fa fa-upload"></i> 导入
										</button> -->
										<button id="btn-delete" class="btn btn-sm btn-danger">
											<i class="glyphicon glyphicon-remove"></i> 删除
										</button>
									</div>
								</div>
							</div>
							<table id="exTable" class="table"
								data-side-pagination="server" data-pagination="true"
								data-row-style="rowStyle" data-side-pagination="server"
				  				data-page-list="[10,15, 20, 50, 100, 200]"
								data-search="true" data-show-refresh="true"
								data-show-export="true">
								<thead>
									<tr>
										<th data-checkbox="true"></th>
										<th data-field="kind" data-halign="center"
											data-align="center">类别</th>
										<th data-field="seqV" data-halign="center"
                                			data-align="center">序列</th>
                            			<th data-field="gradeV" data-halign="center"
                                			data-align="center">级别</th>
										<th data-field="date" data-halign="center"
											data-align="center">时间</th>
										<th data-field="score" data-halign="center"
											data-align="center">成绩</th>
										<th data-formatter="ex.operation" data-align="center">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
<div class="modal fade" id="exModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>

<script src="${ctx}/static/exam/ex.js"></script>
<script>
	var rootPath = '${ctx}';
</script>
