var ex = window.NameSpace || {};
$(function() {
	
	$('#exTable').bootstrapTable({
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
		url : rootPath + "/system/exam/list",
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
	
	$("#exAdd").click(function(){
		$modal=$('#exModal');
	    $modal.load(rootPath+"/system/exam/add.html",'', function(){
			$modal.modal();
	    });
	});
	
	$("#import").click(function(){
		$modal=$('#exModal');
	    $modal.load(rootPath+"/system/exam/import.html",'', function(){
			$modal.modal();
	    });
	});
	
	//批量删除
		$("#btn-delete").click(function() {
			var checked=$('#exTable').bootstrapTable('getSelections');
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
					url : rootPath + "/system/exam/batchDelete",
					data : {
						ids : chk_value.join()
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
		});
	
})

ex.deleteOne=function(id,index) {
	$('#exTable').bootstrapTable('uncheckAll');
	$('#exTable').bootstrapTable('check', index);
	
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/exam/batchDelete",
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


ex.editClick = function(vid,index) {
	$('#exTable').bootstrapTable('uncheckAll');
	$('#exTable').bootstrapTable('check', index);
	$modal=$('#exModal');
    $modal.load(rootPath+"/system/exam/edit.html?id="+vid,'', function(){
		$modal.modal();
    });
};


ex.operation = function(value,row,index) {
	return "<a href=javascript:ex.editClick('" + row.id + "',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
	+ "<a href=javascript:ex.deleteOne('"
	+ row.id + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};