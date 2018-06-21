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
                <table id="planTable" class="table"
                       data-side-pagination="server" data-pagination="true"
                       data-row-style="rowStyle" data-side-pagination="server"
                       data-page-list="[10,15, 20, 50, 100, 200]"
                       data-search="true" data-show-refresh="true" data-show-toggle="false"
                       data-show-columns="false"
                       data-show-export="true">
                    <thead>
                    <tr>
                        <th data-checkbox="true"></th>
                        <th data-field="id" data-halign="center"
                            data-align="center" data-formatter="flow.typeFormatter">类型</th>
                        <th data-field="startUserName" data-halign="center"
                            data-align="center">申请人/被审核人</th>
                        <th data-field="startTime" data-halign="center"
                            data-align="center">流程开始日期</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="theModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header padding-sty">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3 id="modelTitle">转办</h3>
			</div>

			<form id="taskTransferForm" class="form-horizontal" method="POST">
				<input type="hidden" id="taskId" name="taskId" />
				<input type="hidden" id="oldUserId" name="oldUserId" />
				<div class="form-group margin-top-10">
					<label class="col-sm-2 control-label">处理人</label>
					<div class="col-sm-8">
						<select id='newUserId' name="newUserId" required="required">
							<option></option>
							<c:forEach var="u" items="${users}">
								<option value='${u.id}'>${u.displayname}</option>
							</c:forEach>
						</select>
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

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>

<script src="${ctx}/static/model/flowManager.js?12"></script>
<script>
    var rootPath = '${ctx}';
</script>
