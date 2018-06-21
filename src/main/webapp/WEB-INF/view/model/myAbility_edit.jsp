<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">
<script>
var fileId="${af.guid}";
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">修改</h3>
		</div>

		<form id="myAbilityForm" class="form-horizontal form" method="POST">
		<input id="id" name="fileId" value="${af.guid }" type="hidden">
			<div class="form-body">
				 <div class="form-group">
					<label class="col-sm-2 control-label">文件名称</label>
					<div class="col-sm-8">
						<input value="${af.friendlyfilename}" class="form-control" readonly="readonly"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">*附件描述</label>
                     <div class="col-sm-8">
                          <textarea  class="form-control" name="description"  rows="3"  maxlength="500"  required="required">${af.description}</textarea>
                     </div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">能力项</label>
                     <div class="col-sm-8">
                         <select class="form-control"  name="paIds" multiple="multiple" id="paid">
                            <c:forEach items="${sa}" var="ab">
                            	<option value="${ab.id}">${ab.name}(${ab.cnSerial}-${ab.cnTheLevel})</option>
                            </c:forEach>
                         </select>
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
<script type="text/javascript" src="${ctx}/static/model/myAbility_edit.js"></script>

