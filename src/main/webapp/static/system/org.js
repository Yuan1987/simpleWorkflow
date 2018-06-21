var org = window.NameSpace || {};
var treeDemoObJ,
	isEventInit = false;
$(function() {
	
	$('#orgModal').on('hidden.bs.modal', function(e) {
		document.getElementById("orgForm").reset();
		$('#orgForm').data('bootstrapValidator').resetForm();
	});
	
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentid",
				rootPId : 0
			},
			key : {
				name : "displayname"
			}
		},
		view : {
			dblClickExpand : false
		},
		callback : {
			onClick : org.proOnClick,
			onAsyncSuccess : function initTree(){
				treeDemoObJ.expandAll(true);
				if (!isEventInit) {
					org.pagerInit();
				}
			}
		},
		async : {
			enable : true,
			url : rootPath + "/system/orgManager/getOrgTree"
		}
	};
	treeDemoObJ = $.fn.zTree.init($("#treeDemo"), setting);
	//搜索
	$('#btn-search').click(function() {
		$("#userTable").bootstrapTable('refresh');
	});

	//组织新增
	$('#itemFileMenuAdd').click(function() {
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == "") {
			layer.alert('请选择上级组织！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		var nodeName = selectedNode.name;
		$("#parentid").val(nodeid);
		$("#parentname").val(nodeName);
		$("#path").val(selectedNode.path);
		$("#depth").val(selectedNode.level+1);
		$("#typeid").val(selectedNode.typeid);
		//获取上级节点信息
		
		var pNode = selectedNode.getParentNode();
		var pobj=[{id:selectedNode.id,depth:selectedNode.level,type:selectedNode.typeid}];
		while(pNode!=undefined) {
			pobj.push({id:pNode.id,depth:pNode.level,type:pNode.typeid});
		    pNode = pNode.getParentNode();
		}
		$("#pobj").val(JSON.stringify(pobj));
		$("#orgId").val("");
		$("#orgModal").modal("show");
		$('#modelTitle').html('新增');

	});

	//组织修改
	$('#itemFileMenuEdit').click(function() {
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == "") {
			layer.alert('请选择上级组织！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		var pNode=selectedNode.getParentNode();
		
		$("#parentname").val(pNode.name);
		$("#orgId").val(selectedNode.id);
		$("#name").val(selectedNode.name);
		$("#displayname").val(selectedNode.displayname);
		$("#parentid").val(pNode.id);
		$("#parentname").val(pNode.name);
		$("#path").val(selectedNode.path);
		$("#depth").val(selectedNode.level);
		$("#typeid").val(selectedNode.typeid);
		
		$("#orgModal").modal("show");
		$('#modelTitle').html('修改');
		
	});

	//组织删除
	$('#itemFileMenuDelb').click(function() {
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == "") {
			layer.alert('请选择组织！',{
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		//获取上级节点信息
		var pNode = selectedNode.getParentNode();
		var pobj=[{id:selectedNode.id,depth:selectedNode.level,type:selectedNode.typeid}];
		while(pNode!=undefined) {
			pobj.push({id:pNode.id,depth:pNode.level,type:pNode.typeid});
		    pNode = pNode.getParentNode();
		}
		
		org.deleteOne(nodeid,pobj);
	});

	//验证
	bootstrapValidator = $('#orgForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			/*name : {
				validators : {
					stringLength : {
						max : 5,
						message : '名称超过5位!'
					}
				}
			}*/
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var id = $("#orgId").val();
		if (id == "") { //新增
			org.add();
		} else {
			org.update();
		}
	});

})


//人员操作
org.operation = function(value, row,index) {
	return "<a href='javascript:user.editClick(\"" + row.id + "\","+index+")' title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
		+ "<a href='javascript:user.deleteOne(\""
		+ row.id + "\","+index+")' title='删除'><span class='glyphicon glyphicon-trash'></span></a>&nbsp;&nbsp;&nbsp;" 
		/*+"<a href='javascript:user.profile(\"" + row.id + "\")' title='详细信息'><span class='fa fa-share-alt'></span></a>";*/
};

org.role = function(value){
	var val="";
	if(value){
		$.each(value,function(i,n){
			val+=n.displayname+",";
		});
	}
	return val.substring(0,val.length-1);
};

org.add = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/orgManager/add",				
		type : "post",
		data :$("#orgForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				treeDemoObJ.reAsyncChildNodes(null, "refresh");
				$("#orgModal").modal("hide");
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

org.update = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/orgManager/update",				
		type : "post",
		data :$("#orgForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				treeDemoObJ.reAsyncChildNodes(null, "refresh");
				$("#orgModal").modal("hide");
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

org.deleteOne=function(nodeid,pobj) {
	var index = layer.confirm('确认删除该组织吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/orgManager/delete",
			data : {
				id : nodeid,
				pobj:JSON.stringify(pobj)
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				treeDemoObJ.reAsyncChildNodes(null, "refresh");
				$("#usereTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}

//获取当前选中节点的所有上级节点 包含自己
org.getParents = function(selectedNode) {
	var pNode = selectedNode.getParentNode();
	var pobj=[{id:selectedNode.id,depth:selectedNode.level,type:selectedNode.typeid}];
	while(pNode!=undefined) {
		pobj.push({id:pNode.id,depth:pNode.level,type:pNode.typeid});
	    pNode = pNode.getParentNode();
	}
};
//双击刷新
org.proOnClick=function(event, treeId, treeNode) {
	$("#userTable").bootstrapTable('refresh');
}

/*人员数据初始化*/
org.pagerInit=function() {
	isEventInit = true;
	$('#userTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			var selectedNode = treeDemoObJ.getSelectedNodes()[0];
			return {
				page : params.pageNumber,
				size : params.pageSize,
				searchText : params.searchText,
				nodeid : selectedNode == undefined ? "" : selectedNode.id == 0 ? "" : selectedNode.id
			};
		},
		url : rootPath + "/system/userManager/userList",
		cache : false,
		pagination : true,
		sidePagination : 'server',
		toolbar:"#toolbar",
		pageSize : 15,
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '名称';
		}
	});
}
