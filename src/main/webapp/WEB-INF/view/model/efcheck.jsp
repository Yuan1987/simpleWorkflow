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
				<input type="hidden"  name="result" value="${pass}">
				<c:if test="${taskKey!='cpr'&&!fn:endsWith(taskKey, '-add')}">
					<div class="form-group">
						<label class="col-sm-2 control-label">审核结果</label>
						<div class="col-sm-10">
								<select class="form-control" disabled="disabled">
									<option <c:if test="${pass}">selected</c:if>>通过</option>
									<option <c:if test="${!pass}">selected</c:if>>不通过</option>
								</select>
						</div>
					</div>
				</c:if>
				<c:choose>
					<c:when test="${taskKey=='cpr'}">
						<div class="form-group">
							<label class="col-sm-2 control-label">审核意见*</label>
							<div class="col-sm-10">
								<div class="fixed-table-container">
									<table class="table" style="margin-bottom: 0px;">
										<c:forEach var="pae" items="${paeList}">
											<tr>
												<td>${pae.abilityname}</td><td>${pae.note}</td>
											</tr>
										</c:forEach>
									</table>
									<input name="opinion" value="${notes}" type="hidden"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">问题1*</label>
							<div class="col-sm-10">
								<textarea rows="4" name="question1"  id="question1" class="form-control" required="required">${pae.question1}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">问题2</label>
							<div class="col-sm-10">
								<textarea rows="4" name="question2"  class="form-control" >${pae.question2}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">问题3</label>
							<div class="col-sm-10">
								<textarea rows="4" name="question3" id="note" class="form-control">${pae.question3}</textarea>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="form-group">
							<label class="col-sm-2 control-label">审核意见*</label>
							<div class="col-sm-10">
								<textarea rows="4" name="opinion" placeholder="审核意见" class="form-control" required="required">${notes}</textarea>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<c:if test="${isDEPT &&pass}">
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-md-6" style="text-align: left">
							<input type="checkbox"  value="true" id="istrue" name="istrue" required="required"><span id="tmSpan">我已确认材料真实有效</span>
						</div>
					</div>
				</c:if>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<a class="btn btn-default" id="modalclose" data-dismiss="modal">返回</a>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	var taskKey="${taskKey}";
	var url=rootPath + "/model/efcheck/check2";

	$(function(){
		$("#tmSpan").click(function(){
			var obj=$("#istrue");
			if(obj.is(':checked')){
				obj.prop({checked:false}).change();
			}else{
				obj.prop({checked:true}).change();
			}
		});
		bootstrapValidator = $('#checkForm').bootstrapValidator({
			excluded : [ ':disabled' ],
			fields : {
				istrue: {
					trigger:"change",
					 validators: {
			             notEmpty: {
				             message: '请确认材料真实有效'
				         }
			         } 
			    }
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
	});
</script>