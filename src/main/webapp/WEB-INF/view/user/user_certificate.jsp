<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">持证情况</h3>
		</div>

		<form id="ucForm" class="form-horizontal form" method="POST">
		
			<input type="hidden" name="userid" value="${userid}"/>
			<input type="hidden" name="id" value="${uc.id}"/>
		
			<div class="form-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">*证书类型</label>
					<div class="col-sm-8">
						<input type="text"  name="type"
							class="form-control" value="${uc.type}" required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*发证机关</label>
					<div class="col-sm-8">
						<input type="text" name="dept" value="${uc.dept}" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">发证时间</label>
					<div class="col-sm-8">
						<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" name="thedate" 
							value="${uc.thedate}" readonly>
							<span class="input-group-addon"> <i
								class="fa fa-calendar"></i></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*证书编号</label>
					<div class="col-sm-8">
						<input type="text" name="theno" class="form-control" value="${uc.theno}" required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*专业类别</label>
					<div class="col-sm-8">
						<input type="text" name="category" class="form-control" value="${uc.category}" required="required">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确认</button>
				<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/static/user/userCertificate.js?2"></script>

