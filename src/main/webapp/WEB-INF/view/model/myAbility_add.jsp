<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css" type="text/css">

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">新增</h3>
		</div>

		<form id="myAbilityForm" class="form-horizontal form" method="POST" enctype="multipart/form-data">
			<div class="form-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">*附件</label>
					<div class="col-sm-8">
						<input type="file" name="file" id="input-ke-3" multiple="multiple" required="required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*附件描述</label>
                     <div class="col-sm-8">
                          <textarea  class="form-control" name="description"  rows="3"  maxlength="500"  required="required"></textarea>
                     </div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label">能力项</label>
                     <div class="col-sm-8">
                         <select class="form-control"  name="saId"  multiple="multiple">
                         	<option></option>
                            <c:forEach items="${ability}" var="ab">
                            	<option value="${ab.id}">${ab.name}(${ab.cnSerial}-${ab.cnTheLevel})</option>
                            </c:forEach>
                         </select>
                    </div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label"></label>
                     <div class="col-sm-8">
                         <input type="checkbox"  value="true" name="istrue" required="required">我已确认文件已脱密
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
<script type="text/javascript"
	src="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-form.js"></script> --%>
<script type="text/javascript" src="${ctx}/static/model/myAbility_add.js"></script>

