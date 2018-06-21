u<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">加签</h3>
		</div>
		<form id="form" class="form-horizontal" method="get" action="#">
			<div class="portlet light bordered">
				<div class="form-group">
					<label class="col-sm-2 control-label">*审核人</label>
					<div class="col-sm-8">
						<select name="userIds" id="userIds" class="form-control" multiple="multiple">
							<c:forEach items="${users}" var="user">
								<option value="${user.id}">${user.displayname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<input type="hidden" name="taskId" value="${taskId}">
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<a class="btn btn-default" id="modalclose" data-dismiss="modal">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">

$(function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$("#userIds").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});

	bootstrapValidator = $('#form').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/model/efcheck/addNode",
			type : "post",
			data : $("#form").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#efCheckTable").bootstrapTable('refresh');
					$("#theModal").modal("hide");
					try{
						$("#doCheck").modal("hide");
					}catch(e){
						$("#modalclose").click();
					}
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

