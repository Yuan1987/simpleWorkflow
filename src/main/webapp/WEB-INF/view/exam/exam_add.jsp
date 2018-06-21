<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">
<script>
	var orgIds="${orgId}".split(",");
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">新增</h3>
		</div>

		<form id="exForm" class="form-horizontal form" method="POST">
			<div class="form-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">*考试人</label>
					<div class="col-sm-8">
						<input type="text" id="name" name="username"
							class="form-control" placeholder="" value="${userName}" required="required" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*申请序列</label>
                    <div class="col-sm-8">
                        <select class="form-control sp"   id="seq" name="seq" required="required">
                            <option></option>
                            <c:forEach items="${xlList}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*申请级别</label>
                    <div class="col-sm-8">
                        <select class="form-control sp"  name="level"   id="level" required="required">
                            <option></option>
                            <c:forEach items="${jbList}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*类别</label>
					<div class="col-sm-8">
						<input type="text" id="kind" name="kind" class="form-control"
							 required="required" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*时间</label>
					<div class="col-sm-8">
						<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" name="date" value="" >
							<span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*成绩</label>
					<div class="col-sm-8">
						<input type="text" id="score" name="score"
							class="form-control" required="required">
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
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/static/exam/ex_add.js"></script>

