var reource = window.NameSpace || {};

var resourceTree;

$(function() {
	
	$('#resourceModal').on('hidden.bs.modal', function(e) {
		document.getElementById("resourceForm").reset();
		$('#resourceForm').data('bootstrapValidator').resetForm();
	});
	
	var setting = {
		isSimpleData:true,
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : 0
			},
			key : {
				name : "name",
				url:"xxxx"//避免跳转
			}
		},
		view : {
			dblClickExpand : false
		},
		callback : {
			onClick:null,
			onAsyncSuccess : function initTree(){
				resourceTree.expandAll(true);
			}
		},
		async : {
			enable : true,
			url : rootPath + "/system/resourceManager/listForTree?isSelect=false"
		}
	};
	resourceTree = $.fn.zTree.init($("#resourceTree"), setting);
	
	
	
	//新增
	$('#itemFileMenuAdd').click(function() {
		var selectedNode = resourceTree.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == "") {
			layer.alert('请选择上级资源！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		var nodeName = selectedNode.name;
		$("#parentId").val(nodeid);
		$("#parentname").val(nodeName);
		$("#path").val(selectedNode.path);
		$("#depth").val(selectedNode.level+1);
		$("#typeid").val(selectedNode.typeid);
		//获取上级节点信息
		var pNode = selectedNode.getParentNode();
		var pid=[];
		while(pNode!=undefined) {
			pid.push(pNode.id);
		    pNode = pNode.getParentNode();
		}
		pid.reverse();
		var pids="/";
		for(var i in pid){
			pids=pids+pid[i]+"/"
		}
		pids=pids+nodeid+"/";
		$("#parentIds").val(pids);
		$("#resoureId").val("");
		$("#resourceModal").modal("show");
		$('#modelTitle').html('新增');

	});

	//修改
	$('#itemFileMenuEdit').click(function(){
		
		var selectedNode = resourceTree.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		var pNode=selectedNode.getParentNode();
		
		$("#resoureId").val(selectedNode.id);
		$("#name").val(selectedNode.name);
		if(pNode!=null){
			$("#parentId").val(pNode.id);
			$("#parentname").val(pNode.name);
		}
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/resourceManager/getById",
			type : "post",
			data : {
				id : nodeid
			},
			success : function(data) {
				$("#permission").val(data.permission);
				$("#url").val(data.url);
				$("#type").val(data.type);
				$('#classname').val(data.classname);
				$('#viplevelname').val(data.vipLevelName);
				$("#orderno").val(data.orderno);
				$("#parentIds").val(data.parentIds);
				
				$("#resourceModal").modal("show");
				$('#modelTitle').html('修改');
			}
		});
	});

	//删除
	$('#itemFileMenuDelb').click(function() {
		var selectedNode = resourceTree.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == "") {
			layer.alert('请选择！',{
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		reource.deleteOne(nodeid);
	});
	
	//验证
	bootstrapValidator = $('#resourceForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var id = $("#resoureId").val();
		if (id == "") { //新增
			reource.add();
		} else {
			reource.update();
		}
	});
})

reource.add = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/resourceManager/add",				
		type : "post",
		data :$("#resourceForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				$("#resourceModal").modal("hide");
				resourceTree.reAsyncChildNodes(null, "refresh");
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

reource.update = function(){
	$.ajax({
		dataType : "json",
		url : rootPath+ "/system/resourceManager/update",				
		type : "post",
		data :$("#resourceForm").serialize(),
		success : function(data){
			if(data.message=="操作成功！"){
				resourceTree.reAsyncChildNodes(null, "refresh");
				$("#resourceModal").modal("hide");
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


reource.deleteOne=function(nodeid,pobj) {
	var index = layer.confirm('确认删除该资源吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/resourceManager/delete",
			data : {
				id : nodeid,
				pobj:JSON.stringify(pobj)
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				resourceTree.reAsyncChildNodes(null, "refresh");
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}