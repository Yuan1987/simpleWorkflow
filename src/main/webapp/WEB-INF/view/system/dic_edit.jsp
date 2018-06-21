<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
	var orgIds="${orgId}".split(",");
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">数据字典修改</h3>
		</div>
		<form id="dicForm" class="form-horizontal form" method="POST">
			<div class="form-body">
				<input type="hidden" name="id" value="${dic.id}">
				<div class="form-group">
					<label class="col-sm-2 control-label">*类型名称</label>
					<div class="col-sm-8">
						<input type="text" id="kindname" name="kindname"
							class="form-control" placeholder="" required="required" value="${dic.kindname}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*类型</label>
					<div class="col-sm-8">
						<input type="text" id="kind" name="kind" class="form-control"
							 required="required" value="${dic.kind}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">上级</label>
					<div class="col-sm-8">
						<select name="parentcode" class="form-control">
							<option></option>
							<c:forEach items="${dics}" var="item">
								<option value="${time.code}" <c:if test="${dic.parentcode==item.code }"></c:if>>${item.code}|${item.detail}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*代码</label>
					<div class="col-sm-8">
						<input type="text" id="code" name="code" class="form-control"
							 required="required" value="${dic.code}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*值</label>
					<div class="col-sm-8">
						<input type="text" id="detail" name="detail"
							class="form-control" required="required" value="${dic.detail}">
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
<script type="text/javascript" src="${ctx}/static/system/dic_edit.js"></script>

