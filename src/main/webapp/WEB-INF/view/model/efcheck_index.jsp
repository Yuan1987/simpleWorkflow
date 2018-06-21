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
<style>
	.form-section {
		margin: 10px 0 !important;
		padding-bottom: 5px;
		border-bottom: 1px solid #e5e5e5;
	}
</style>

<div class="page-content-wrapper">
	<!-- BEGIN CONTENT BODY -->
	<div class="page-content">
		<!-- BEGIN PAGE HEAD-->
		<ul class="page-breadcrumb breadcrumb">
			<li><a href="#">评测复核</a> <i class="fa fa-circle"></i></li>
			<li><span class="active">待我审核</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
			<div class="col-md-12">
				<div class="m-top-up-15">
					<div class="panel panel-default">
			          <div class="panel-heading">查询条件</div>
			            <div class="panel-body">
			                <form id="formSearch" class="form-horizontal">
			                    <div class="form-group" style="margin-top:15px">
			                        <label class="control-label col-sm-1" for="txt_search_departmentname">状态</label>
			                        <div class="col-sm-3">
			                            <select class="form-control" name="status" id="status">
											<option selected="selected" value="0">待处理</option>
											<option value="1">已处理</option>
										</select>
			                        </div>
			                        <label class="control-label col-sm-1" for="txt_search_statu">环节</label>
			                        <div class="col-sm-3">
			                            <select class="form-control" name="node" id="node">
											<option value="">全部</option>
											<option value="bbmld">本部门审核</option>
											<option value="syb">事业部审核</option>
											<option value="xlxz">指定评测人</option>
											<option value="xlxz">评测</option>
											<option value="xlxz">汇总</option>
											<option value="gb">公布</option>
										</select>
			                        </div>
			                        <div class="col-sm-4">
			                            <button type="button" id="btn_query" class="btn">查询</button>
			                        </div>
			                    </div>
			                </form>
			            </div>
			        </div>   
					<div id="toolbar">
						<form class="form-inline" method="post">
							<button id="batchCheckApply" class="btn" type="button">
								<i class="fa fa-check-square-o"></i> 批量审核
							</button>
							<c:if test="${isxlxzld}">
								<button id="batchChose" class="btn" type="button">
									<i class="fa fa-user-plus"></i> 批量指定
								</button>
							</c:if>
							<c:if test="${isHr}">
								<button id="batchPublish" class="btn" type="button">
									<i class="fa fa-bullhorn"></i> 公布
								</button>
							</c:if>
						</form>
					</div>
					<table id="efCheckTable" class="table"
						data-side-pagination="server" data-pagination="true"
						data-row-style="rowStyle" data-side-pagination="server"
						data-page-list="[10,15, 20, 50, 100, 200]"
						data-show-refresh="true" data-show-export="true">
						<thead>
							<tr>
								<th data-checkbox="true" data-formatter="efCheck.stateFormatter"></th>
								<th data-field="taskName" data-align="center">流程环节</th>
								<th data-field="username" data-align="center">申报人</th>
								<th data-field="typeV" data-align="center">申报类型</th>
								<th data-field="seqV" data-align="center">申报序列</th>
								<th data-field="gradeV" data-align="center">申报级别</th>
								<!-- <th data-field="submittime" data-align="center">评测表提交时间</th> -->
								<th data-field="taskEndTime" data-align="center">处理时间</th>
								<th data-formatter="efCheck.operation" data-align="center">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="theModal" tabindex="-1"></div>
<div class="modal fade" id="doCheck" tabindex="-1"></div>
<div class="modal fade bs-modal-lg" id="paFileModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/fileinput/js/fileinput.js?11"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/fileinput/themes/explorer/theme.js?1"></script>
<script src="${ctx}/static/model/efcheck.js?121"></script>
<script>
	var rootPath = '${ctx}';
</script>
