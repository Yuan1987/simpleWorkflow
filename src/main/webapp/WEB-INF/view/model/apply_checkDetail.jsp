<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/static/common/flow.css?22" type="text/css">

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">审核状态</h3>
		</div>
		<div class="portlet light">
			<div class="mt-element-step" >
				<div class="row step-line flow" id="flow">
               </div>
           </div>
			<table id="applyCheckDetailTable" class="table" >
				<thead>
					<tr>
						<th data-checkbox="true"></th>
						<th data-field="nodename" data-align="center">环节名称</th>
						<th data-field="createdtime" data-align="center">审核时间</th>
						<th data-field="username" data-align="center">审核人</th>
						<th data-field="result" data-align="center" data-formatter="resultDecode">审核结果</th>
						<th data-field="opinion">审核意见</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<script>
$(function() {

	var id="${id}";
	var processId="${processId}";
	
	$('#applyCheckDetailTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
			};
		},
		url : rootPath + "/model/check/applyCheckDetail?applyId="+id,
		cache : false,
		searchOnEnterKey:true,
		sidePagination : 'server',
		onLoadSuccess : function() {}
	});
	
	//加载流程图
	$.ajax({
		dataType : "json",
		url : rootPath + "/model/check/lookprocessdefine?processId=${processId}",
		type : "post",
		success : function(data) {
			var flowJson=JSON.parse(data.flowJson);
			var currentNode=data.currentNode;
			var status="done";
			$("#flow").append("<div class=\"mt-step-col first done\"><div class=\"mt-step-number bg-white\">开始</div><div class=\"mt-step-title uppercase font-grey-cascade\"></div></div>");
			$.each(flowJson,function(i,n){
			
				if(n.key==currentNode){
					status="active";
				}
				var str="<div class=\"mt-step-col "+status+"\">"
                       +"<div class=\"mt-step-word bg-white\">"+n.name+"</div>"
                       +"<div class=\"mt-step-title uppercase font-grey-cascade\"></div>"
                   	+"</div>";
                if(n.key==currentNode){
					status="";
				}
				$("#flow").append(str);
			});
			$("#flow").append("<div class=\"mt-step-col last\" id=\"last\"><div class=\"mt-step-number bg-white\">结束</div><div class=\"mt-step-title uppercase font-grey-cascade\"></div></div>");
			if(currentNode==undefined){
				$("#last").addClass("done");
			}
		},
		error : function() {
		}
	});
	
})

function resultDecode(val,row){
	var value="";
	if(val==true){
		value="通过";
	}else{
		value="未通过";
		if(row.isback){
			value="被退回";
		}
	}
	return value;
}
</script>