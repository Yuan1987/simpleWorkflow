<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">审核</h3>
			<form id="checkForm" class="form-horizontal" method="get" action="#">
				<input type="hidden" name="data" id="data" value="">
				<div class="form-group">
					<label class="col-sm-2 control-label">审核结果</label>
					<div class="col-sm-10">
						<select class="form-control" name="result">
							<option selected value="true">通过</option>
							<option value="false">不通过</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">审核意见</label>
					<div class="col-sm-10">
						<textarea rows="4" name="opinion" placeholder="审核意见" class="form-control"></textarea>
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
</div>

<script>

$(function(){
	var type="${type}";
	var url=rootPath + "/model/efcheck/batchCheck";
	if(type=="apply"){
		url=rootPath + "/model/check/batchCheck";
	}
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
			url : url,
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