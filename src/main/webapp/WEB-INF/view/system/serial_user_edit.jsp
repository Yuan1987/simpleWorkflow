<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script>
	var orgIds = "${orgId}".split(",");
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">序列小组人员修改</h3>
		</div>
		<form id="userSerialForm" class="form-horizontal" method="POST">
			<div class="form-group margin-top-10">
				<label class="col-sm-2 control-label">*人员</label>
				<div class="col-sm-8">
					<input name="id" type="hidden" value="${id}"> <input
						class="form-control" value="${uname}"
						readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">*类型</label>
				<div class="col-sm-8">
					<select class="form-control" required="required" name="type">
						<option value="0" <c:if test="${type=='0'}">selected</c:if> >负责人</option>
						<option value="1" <c:if test="${type=='1'}">selected</c:if>>成员</option>
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
<script>
$(function(){
	$('#custom-headers').multiSelect({
	});
	
	bootstrapValidator = $('#userSerialForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/serial/serialUserEdit",
			type : "post",
			data : $("#userSerialForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#usTable").bootstrapTable('refresh');
					$("#theModal").modal("hide");
				}
				layer.alert(data.message, {
					title : '提示',
					icon : 0,
					skin : 'layer-ext-moon'
				});
			},
			error : function() {
				layer.alert(data.message, {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
});
</script>

