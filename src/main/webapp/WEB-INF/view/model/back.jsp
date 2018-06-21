<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">审核</h3>
			<form id="checkForm" class="form-horizontal" method="get" action="#">
				<input type="hidden" name="taskid" value="${taskId}">
				<input type="hidden" name="applyid" value="${applyId}">
				<input type="hidden" name="result" value="false">
				<input type="hidden" name="isback" value="true">
				<div class="form-group">
					<label class="col-sm-2 control-label">审核结果</label>
					<div class="col-sm-10">
						<input value="退回" class="form-control" readonly="readonly"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">审核意见*</label>
					<div class="col-sm-10">
						<textarea rows="4" name="opinion" placeholder="退回原因" class="form-control" required="required"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<a class="btn btn-default" id="modalclose"
						data-dismiss="modal">返回</a>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	var taskKey="${taskKey}";
	var url=rootPath + "/model/efcheck/check2";
	var type="${type}";
	
	if(type=="apply"){
		url=rootPath + "/model/check/check2";
	}

	bootstrapValidator = $('#checkForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : url,
			type : "post",
			data : $("#checkForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#efCheckTable").bootstrapTable('refresh');
					$("#applyCheckTable").bootstrapTable('refresh');
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
				layer.alert("操作失败！", {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
</script>