var user = window.NameSpace || {};

$(function(){
	
	$("#userAdd").click(function(){
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		var nodeName=selectedNode == undefined ? "" : selectedNode.name;
		if (nodeid == "") {
			layer.alert('请选择组织！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		$modal=$('#userModal');
	    $modal.load(rootPath+"/system/userManager/add.html",{orgId:nodeid,orgName:nodeName}, function(){
			$modal.modal();
	    });
	});
	
	$("#userUpload").click(function(){
		$modal=$('#userModal');
	    $modal.load(rootPath+"/system/userManager/userImport.html",'', function(){
			$modal.modal();
	    });
	});
});

user.deleteOne=function(id,index) {
	
	var selectedNode = treeDemoObJ.getSelectedNodes()[0];
	var nodeid = selectedNode == undefined ? "" : selectedNode.id;
	
	$('#userTable').bootstrapTable('uncheckAll');
	$('#userTable').bootstrapTable('check', index);
	var index = layer.confirm('确认删除？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/userManager/delete",
			data : {
				id : id,
				orgId:nodeid
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#userTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}


user.editClick = function(vid,index) {
	
	var selectedNode = treeDemoObJ.getSelectedNodes()[0];
	var nodeid = selectedNode == undefined ? "" : selectedNode.id;
	var nodeName=selectedNode == undefined ? "" : selectedNode.name;
	if (nodeid == "") {
		layer.alert('请选择组织！', {
			title : '提示',
			icon : 0,
			skin : 'layer-ext-moon'
		});
		return;
	}
	$('#userTable').bootstrapTable('uncheckAll');
	$('#userTable').bootstrapTable('check', index);
	$modal=$('#userModal');
    $modal.load(rootPath+"/system/userManager/edit.html",{userId:vid,orgId:nodeid,orgName:nodeName}, function(){
		$modal.modal();
    });
};

user.profile = function(vid) {
	$modal=$('#userModal');
    $modal.load(rootPath+"/system/userManager/userProfile.html?userId="+vid,'', function(){
		$modal.modal();
    });
};
