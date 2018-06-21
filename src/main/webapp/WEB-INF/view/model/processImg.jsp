<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/static/common/flow.css?2" type="text/css">
<div class="modal-dialog modal-full">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">流程图</h3>
		</div>
		<div class="portlet light">
			<div class="mt-element-step" >
				<div class="row step-line flow" id="flow">
               </div>
           </div>
		</div>
	</div>
</div>

<script>
$(function() {
	
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

</script>