var serial = window.NameSpace || {};

var treeDemoObJ,
    isEventInit = false;
$(function() {

    var setting = {
        data : {
            simpleData : {
                enable : true,
                idKey : "id",
                pIdKey : "parentid",
                rootPId : 0
            },
            key : {
                name : "name"
            }
        },
        view : {
            dblClickExpand : false
        },
        callback : {
            onClick : serial.proOnClick,
            onAsyncSuccess : function initTree(){
                treeDemoObJ.expandAll(true);
                if (!isEventInit) {
                    //serial.proOnClick();
                }
            }
        },
        async : {
            enable : true,
            url : rootPath + "/system/serial/list"
        }
    };
    treeDemoObJ = $.fn.zTree.init($("#treeDemo"), setting);

	$('#usTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
            var selectedNode = treeDemoObJ.getSelectedNodes()[0];
            var nodeValue= selectedNode == undefined ? "" : selectedNode.id == 0 ? "" : selectedNode.id;
            nodeValue=nodeValue=="root"?"":nodeValue;
			return {
				page : params.pageNumber,
				size : params.pageSize,
				searchText : params.searchText,
                sid :nodeValue
			};
		},
		url : rootPath + "/system/serial/userList",
		cache : false,
		pagination : true,
		searchOnEnterKey:true,
		sidePagination : 'server',
		toolbar:"#toolbar",
		pageSize : 15,
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '名称';
		}
	});
	
	$("#dicAdd").click(function(){
		
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		var nodeName=selectedNode == undefined ? "" : selectedNode.name;
		if (nodeid == ""||nodeid=="0") {
			layer.alert('请选择所属序列！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		$modal=$('#theModal');
	    $modal.load(rootPath+"/system/serial/gl.html",{sid:nodeid,sname:nodeName}, function(){
			$modal.modal();
	    });
	});
	
	//序列新增
	$('#itemFileMenuAdd').click(function() {
		$("#serialModal").modal("show");
		$('#modelTitle').html('新增');
	});
	
	//组织修改
	$('#itemFileMenuEdit').click(function() {
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == ""||nodeid=="0") {
			layer.alert('请选择具体序列！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		$("#id").val(selectedNode.id);
		$("#name").val(selectedNode.name);
		$("#code").val(selectedNode.code);
		$("#serialModal").modal("show");
		$('#modelTitle').html('修改');
		
	});

	//序列删除
	$('#itemFileMenuDelb').click(function() {
		var selectedNode = treeDemoObJ.getSelectedNodes()[0];
		var nodeid = selectedNode == undefined ? "" : selectedNode.id;
		if (nodeid == ""||nodeid=="0") {
			layer.alert('请选择具体序列！', {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
			return;
		}
		var index = layer.confirm('确认删除该序列吗？', {
			btn : [ '确认', '取消' ] //按钮
		}, function() {
			$.ajax({
				type : "POST",
				url : rootPath + "/system/serial/delete",
				data : {
					id : nodeid
				},
				success : function(data) {
					layer.msg(data.message, {
						icon : 1
					});
					treeDemoObJ.reAsyncChildNodes(null, "refresh");
					$("#usTable").bootstrapTable('refresh');
					layer.close(index);
				}
			});
		}, function() {
			layer.close(index);
		});
	});

	
	//序列新增修改验证
	bootstrapValidator = $('#serialForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var id = $("#id").val();
		var url=rootPath+ "/system/serial/add";
		if (id == "") { //新增
			url=rootPath+ "/system/serial/add";
		} else {
			url=rootPath+ "/system/serial/update";
		}
		
		$.ajax({
			dataType : "json",
			url : url,				
			type : "post",
			data :$("#serialForm").serialize(),
			success : function(data){
				if(data.message=="操作成功！"){
					treeDemoObJ.reAsyncChildNodes(null, "refresh");
					$("#serialModal").modal("hide");
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
		
	});
	

})
//双击刷新
serial.proOnClick=function(event, treeId, treeNode) {

     $("#usTable").bootstrapTable('refresh');
}
serial.deleteUser=function(id,index) {
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/serial/serialUserDelete",
			data : {
				id : id
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#usTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}

serial.editUserClick = function(id,uname,type,index) {
	$('#usTable').bootstrapTable('uncheckAll');
	$('#usTable').bootstrapTable('check', index);
	$modal=$('#theModal');
    $modal.load(rootPath+"/system/serial/serial_user_edit.html",{id:id,uname:uname,type:type}, function(){
		$modal.modal();
    });
};

serial.operation = function(value,row,index) {
	return "<a href=javascript:serial.editUserClick('" + row.id + "','"+row.displayname+"','"+row.type+"',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
	+ "<a href=javascript:serial.deleteUser('"
	+ row.id + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};

serial.type=function(value){
	var str="";
	if(value=="0"){
		str="负责人";
	}else if(value=="1"){
		str="成员";
	}
	return str;
}