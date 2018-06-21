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

<input type="hidden" name="orgIds" id="orgIds" /> 
<div class="input-icon right" id="orgSelectButton">
	<i class="fa fa-caret-down"></i> <input type="text" placeholder="请选组织"
		id="selectOrgs" name="selectOrgs" class="form-control" readonly>
</div>

<div id="orgZtreeContent"
	style="display: none; position: absolute; z-index: 10000;">
	<ul id="orgZtreeId" class="ztree diy"></ul>
</div>
<script>
	var zNodesOrg = new Array();
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		dataType : "json",
		url : '${ctx}' + "/system/orgManager/getOrgTree",  
		error : function() {
			  layer.msg('加载失败!', {icon: 2});
		},
		success : function(data) {
			if (data) {
				for ( var key in data) {
					var orgs = data[key];
						zNodesOrg.push({
							id : orgs.id,
							pId : orgs.parentid,
							name : orgs.name
						});
				}
			}
		}
	});
</script>