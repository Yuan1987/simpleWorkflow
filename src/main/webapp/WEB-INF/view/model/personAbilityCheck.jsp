<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/assets/global/plugins/typeahead/bootstrap3-typeahead.js" type="text/javascript"></script>
<style>
.dropdown-menu {
    margin: 0px 0 0 !important;
    width: 90%;
}
</style>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">测评</h3>
		</div>
		<form id="checkForm" class="form-horizontal form" method="get"
			action="#">
			<div class="form-body">
				<input type="hidden" name="paeId" value="${pae.id}">

				<div class="form-group">
					<label class="col-sm-2 control-label">能力项</label>
					<div class="col-sm-8">
						<input class="form-control" name="abilityname" readonly="readonly"
							value="${pa.name}" />
					</div>
				</div>
				<c:if test="${needMark}">
					<div class="form-group">
						<label class="col-sm-2 control-label">评分*</label>
						<div class="col-sm-8">
							<input name="scoring" class="form-control" required="required"
								value="${pae.scoring}" placeholder="分值：${pa.score}" 
								<c:if test="${complete}">
									readonly
								</c:if>
								/>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-2 control-label">意见*</label>
					<div class="col-sm-8">
						<textarea rows="4" name="note" placeholder="意见" id="note"
							class="form-control" required="required" 
							<c:if test="${complete}">
								readonly
							</c:if>
							>${pae.note}</textarea>
					</div>
				</div>
				<div class="modal-footer">
					<c:if test="${!complete}">
						<button type="submit" class="btn btn-primary">确定</button>
					</c:if>
					<a href="javascript:modalHide();" class="btn btn-default"
						data-dismiss="modal">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>

<script>
	var score="${pa.score}";
	var index="${index}";
	
	$(function(){
		bootstrapValidator = $('#checkForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			scoring: {
	            validators: {
	            	between: {
                        min: 0,
                        max: score,
                        message: '分数应在0-'+score+'之间'
                    }
	            }
		    },
		    note:{
		    	trigger:"change"
		    }
		}
	}).on('success.form.bv', function(e) {
			e.preventDefault();
			$.ajax({
				dataType : "json",
				url : "${ctx}/model/efcheck/markScore",
				type : "post",
				data : $("#checkForm").serialize(),
				success : function(data) {
					if (data.message == "操作成功！") {
						$("#evaluFormTable").bootstrapTable('refresh');
						$('#evaluFormTable').bootstrapTable('expandRow',index);
						$("#paFileModal").modal("hide");
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
		$('#note').typeahead({
			 source: function (query, process){
	            return $.ajax({
	                url: '${ctx}/model/efcheck/getTypeaheadSource',
	                type: 'post',
	                data: {key: query},
	                success: function (result) {
	                    return process(result);
	                },
	            });
	        },
			minLength:0,
          	showHintOnFocus:true
		})
	});
</script>