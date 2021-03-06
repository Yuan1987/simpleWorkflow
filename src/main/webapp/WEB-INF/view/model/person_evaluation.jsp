<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/fileinput/css/fileinput.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/fileinput/themes/explorer/theme.css" type="text/css">


<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">评测、复审</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">我的测评</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
					<div class="col-md-12">
						<div class="m-top-up-15">
							<%-- <div class="fixed-table-toolbar">
								<div class="bs-bars pull-left">
									<div id="toolbar">
										<a href="${ctx}/model/apply/ExportExcel?applyId=b9e69990-2fed-462b-9896-ab194445467e">导出</a>
									</div>
								</div>
							</div> --%>
							<div id="toolbar">
								<!-- <div class="form-inline">
									<select class="form-control" id="thestatus">
										<option value="">全部</option>
										<option value="未通过">未通过</option>
										<option value="已通过">已通过</option>
										<option value="审核中">审核中</option>
										<option value="草稿">草稿</option>
									</select>
								</div> -->
							</div>
							<table id="evaluTable" class="table"
								data-side-pagination="server" data-pagination="true"
								data-row-style="rowStyle" data-side-pagination="server"
				  				data-page-list="[10,15, 20, 50, 100, 200]">
								<thead>
									<tr>
										<th data-checkbox="true"></th>
										<th data-field="typeV" data-align="center">申报类型</th>
										<th data-field="seqV" data-align="center">申报序列</th>
										<th data-field="gradeV" data-align="center">申报级别</th>
										<th data-field="evalsubmittime" data-align="center">提交时间</th>
										<th data-field="evalStatus" data-align="center">状态</th>
										<th data-formatter="evalu.operation" data-align="center">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
<div class="modal fade bs-modal-lg" id="evaluModal" tabindex="-1"></div>
<div class="modal fade bs-modal-lg" id="paFileModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/assets/global/plugins/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/fileinput/themes/explorer/theme.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/fileinput/js/locales/zh.js"></script> --%>
<script src="${ctx}/static/model/person_evaluation.js"></script>

<script>
	var rootPath = '${ctx}';
</script>
