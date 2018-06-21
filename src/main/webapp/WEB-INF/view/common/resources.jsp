<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	ul.ztree.diy {
		 margin-top: 10px;
		 border: 1px solid #617775;
		 background: #f0f6e4;
		 width:auto;
		 height:auto;
		 height:300px;
		 width:240px;
		 overflow-y:scroll;
	}
</style>

<input type="hidden" name="resourceIds" id="orgIdsRes" /> 
<div class="input-icon right" id="orgSelectButtonRes">
	<i class="fa fa-caret-down"></i> <input type="text" placeholder="请选资源"
		id="selectOrgRes" name="selectOrgRes" class="form-control" readonly>
</div>

<div id="orgZtreeContentRes"
	style="display: none; position: absolute; z-index: 10000;">
	<ul id="orgZtreeRes" class="ztree diy"></ul>
</div>
<script>
	var zNodesRes = new Array();
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		dataType : "json",
		url : '${ctx}' + "/system/resourceManager/listForTree",  
		error : function() {
			  layer.msg('资源树加载失败!', {icon: 2});
		},
		success : function(data) {
			if (data) {
				for ( var key in data) {
					var resources = data[key];
						zNodesRes.push({
							id : resources.id,
							pId : resources.parentId,
							name : resources.name
						});
				}
			}
		}
	});
</script>