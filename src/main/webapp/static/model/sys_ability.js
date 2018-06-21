var ability = window.NameSpace || {};
$(function() {
	
	$('#abilityTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				searchText : params.searchText,
                abilitykind : $("#abilitykind").val(),
			};
		},
		url : rootPath + "/system/ability/list",
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
    //搜索
    $('#abilitykind').change(function() {
        $("#abilityTable").bootstrapTable('refresh');
    });
	$("#abilityAdd").click(function(){
		$modal=$('#abilityModal');
	    $modal.load(rootPath+"/system/ability/add.html",'', function(){
			$modal.modal();
	    });
	});
	
	$("#btn-delete").click(function() {
		var checked=$('#abilityTable').bootstrapTable('getSelections');
		var chk_value =[];
	 	for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push(checked[i].id);
	 	}
	 	if(chk_value.length==0){
	 		layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	   		return;
	 	}
		index = layer.confirm('确认删除选中的数据吗？', {
			btn : [ '确认', '取消' ] //按钮
		}, function() {
			$.ajax({
				type : "POST",
				url : rootPath + "/system/ability/batchDelete",
				data : {
					ids : chk_value.join()
				},
				success : function(data) {
					layer.msg(data.message, {
						icon : 1
					});
					$("#abilityTable").bootstrapTable('refresh');
					layer.close(index);
				}
			});
		}, function() {
			layer.close(index);
		});
	});
	
})

ability.deleteOne=function(id,index) {
	$('#abilityTable').bootstrapTable('uncheckAll');
	$('#abilityTable').bootstrapTable('check', index);
	
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/ability/batchDelete",
			data : {
				ids : id
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#exTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}

ability.editClick = function(vid,index) {
	$('#abilityTable').bootstrapTable('uncheckAll');
	$('#abilityTable').bootstrapTable('check', index);
	$modal=$('#abilityModal');
    $modal.load(rootPath+"/system/ability/edit.html?id="+vid,'', function(){
		$modal.modal();
    });
};

ability.operation = function(value,row,index) {
	return "<a href=javascript:ability.editClick('" + row.id + "',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
	+ "<a href=javascript:ability.deleteOne('"
	+ row.id + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};