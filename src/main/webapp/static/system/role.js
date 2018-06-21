var role = window.NameSpace || {};
$(function() {
	
	$('#roleTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				searchText : params.searchText
			};
		},
		url : rootPath + "/system/roleManager/roleList",
		cache : false,
		pagination : true,
		sidePagination : 'server',
		toolbar:"#add",
		pageSize : 15,
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '角色名称';
		}
	});
	
	//搜索
	$('#btn-search').click(function() {
		$("#roleTable").bootstrapTable('refresh');
	});
	
	$('#roleModal').on('hidden.bs.modal', function(e) {
		document.getElementById("roleForm").reset();
		$('#roleForm').data('bootstrapValidator').resetForm();
	});
	
	//新增role
	$('#add').click(function() {
		$("#roleId").val("");
		showTree(zNodesRes,"", "orgSelectButtonRes", "orgZtreeRes","selectOrgRes", "orgIdsRes","orgZtreeContentRes",true);
		$("#roleModal").modal("show");
		$('#roleModelTitle').html('新增');
	});
	
	
	//验证
	bootstrapValidator = $('#roleForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			/*selectOrgRes: {
				trigger:"change",
				 validators: {
		             notEmpty: {
			             message: '请选权限'
			         }
		         } 
		    }*/
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var id = $("#roleId").val();
		if (id == "") { //新增
			role.add();
		} else {
			role.update();
		}
	});
	
})

role.operation = function(value, row) {
	return "<a href='javascript:role.editClick(\"" + row.id + "\")' title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
		+ "<a href=javascript:role.deleteOne('"
		+ row.id + "') title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};

role.add = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/roleManager/add",				
		type : "post",
		data :$("#roleForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				$("#roleModal").modal("hide");
				$("#roleTable").bootstrapTable('refresh');
			}
			layer.alert(data.message, {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
		},
		error:function(){
			layer.alert(data.message, {
				title : '提示',
				icon : 2,
				skin : 'layer-ext-moon'
			});
		}
	});
};

role.editClick = function(vid) {
	$('#roleModelTitle').html('修改');
	$.ajax({
		dataType : "json",
		url : rootPath + "/system/roleManager/getById",
		type : "post",
		data : {
			id : vid
		},
		success : function(data) {
			
			var role=data.role;
			var rids=data.resourceIds;
			
			var checkIds="";
			
			for(var i in rids){
				checkIds=checkIds+rids[i].resourceid+","
			}
			$("#roleId").val(vid);
			
			console.info(role.typeid);
			
			showTree(zNodesRes,checkIds, "orgSelectButtonRes", "orgZtreeRes","selectOrgRes", "orgIdsRes","orgZtreeContentRes",true);

			$("#organizationid").val(role.organizationid);
			$("#orgName").val(role.organizationName);
			$("#rolename").val(role.name);
			$("#roledisplayname").val(role.displayname);
			$("#roletypeid").val(role.typeid);
			$('#description').val(role.description);
			$('#roleModal').modal('show');
		}
	});
};

role.update = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/roleManager/update",				
		type : "post",
		data :$("#roleForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				$("#roleTable").bootstrapTable('refresh');
				$("#roleModal").modal("hide");
			}
			layer.alert(data.message, {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
		},
		error:function(){
			layer.alert(data.message, {
				title : '提示',
				icon : 2,
				skin : 'layer-ext-moon'
			});
		}
	});
};

role.deleteOne=function(id) {
	var index = layer.confirm('确认删除？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/roleManager/delete",
			data : {
				id : id
			},
			success : function(data) {
				
				layer.msg(data.message, {
					icon : 1
				});
				$("#roleTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}
