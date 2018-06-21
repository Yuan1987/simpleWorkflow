<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/crop/cropper.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/static/common/tableFile.css?22" type="text/css">	
<script>
	var evaluationFormId="${evaluationFormId}";
	var applyId="${applyId}";
	var serial="${serial}";
	var level="${level}";
	var efstatus="${ef.status}";
	var needMark="${needMark}";
</script>
<div class="modal-dialog modal-full">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">测评表</h3>
			<div class="bootstrap-table">
				<div class="fixed-table-toolbar">
					<div class="bs-bars pull-left">
						<div id="toolbar">
							<c:if test="${ef.status=='草稿'||ef.status=='被退回'}">
								<button id="cAdd" class="btn btn-sm"
									onclick="eval.commit('${evaluationFormId}');">
									<i class="fa fa-plus"></i> 提交
								</button>
							</c:if>
<!-- 							<button id="remove" class="btn btn-sm"
								onclick="modalShow('print2');">
								<i class="fa fa-print"></i> 打印
							</button> -->
						</div>
					</div>
				</div>
				<table id="evaluFormTable" class="table">
					<thead>
						<tr>
							<th data-field="typeV" data-valign="middle" data-width="200px">任职资格模型评测项目</th>
							<th data-field="name" data-align="center" data-valign="middle">能力项</th>
							<th data-field="evidence" data-align="center" data-valign="middle">可以作为证据的输出件</th>
							<th data-field="files" data-formatter="eval.file" data-align="center" data-width="400px" class="file" data-valign="middle">附件信息</th>
							<!-- <th data-field="files" data-formatter="eval.description" data-align="center">附件描述</th> -->
							<th data-field="score" data-align="center" data-valign="middle">分值</th>
							<c:if test="${needMark&&ef.status=='已完成'}">
								<th data-formatter="eval.count" data-field="paes" data-align="center" data-footer-formatter="eval.total2" data-valign="middle">得分</th>
							</c:if>
							<!-- <th data-formatter="evalu.operation" data-align="center">操作</th> -->
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-form.js"></script>
<script type="text/javascript" src="${ctx}/static/model/person_evaluation_edit.js?2"></script>
