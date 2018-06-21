<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">批量指定评测人</h3>
		</div>
		<form id="checkForm" class="form-horizontal" method="get" action="#">
			<input type="hidden" name="data" id="data" value="">
			<div class="form-group margin-top-10">
				<label class="col-sm-2 control-label">评测人</label>
				<div class="col-sm-8">
					<select name="users" id="users" class="form-control" multiple="multiple">
						<c:forEach items="${users}" var="user">
							<option value="${user.uid}">${user.displayname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确定</button>
				<a href="javascript:modalHide();" class="btn btn-default"
					data-dismiss="modal">返回</a>
			</div>
		</form>
		</div>
	</div>
<script>

$(function(){
	
	$("#users").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});

	var data="${data}";
	$("#data").val(data);
	bootstrapValidator = $('#checkForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/model/efcheck/batchChose",
			type : "post",
			data : $("#checkForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#applyCheckTable").bootstrapTable('refresh');
					$("#efCheckTable").bootstrapTable('refresh');
					$("#theModal").modal("hide");
					$("#doCheck").modal("hide");
				}
				layer.alert(data.message, {
					title : '提示',
					icon : 0,
					skin : 'layer-ext-moon'
				});
			},
			error : function() {
				layer.alert("操作失败", {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
});
</script>