var process = window.NameSpace || {};
$(function() {
	
	$('#processTable').bootstrapTable({
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
		url : rootPath + "/system/process/list",
		cache : false,
		pagination : true,
		searchOnEnterKey:true,
		sidePagination : 'server',
		toolbar:"#toolbar",
		pageSize : 15,
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {}
	});
	
	$("#add").click(function(){
		$modal=$('#processModal');
	    $modal.load(rootPath+"/system/process/add.html",'', function(){
			$modal.modal();
	    });
	});
})

process.ck = function(value,row,index) {
	return "<a target='_blank' href="+rootPath+"/system/process/lookprocessdefine?deploymentId="+row.deploymentId+"&resourceName="+row.diagramResourceName+">"+value+"</a>"
};

process.operation = function(value,row,index) {
	return "<a href=javascript:process.deleteOne('"
	+ row.deploymentId + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};

process.deleteOne=function(id,index) {
	$('#processTable').bootstrapTable('uncheckAll');
	$('#processTable').bootstrapTable('check', index);
	
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/system/process/delete",
			data : {
				deploymentId : id
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#processTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}