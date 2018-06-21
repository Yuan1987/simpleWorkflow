var apply = window.NameSpace || {};
$(function() {
	
	$('#applyTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				status : $("#thestatus").val(),
			};
		},
		url : rootPath + "/model/apply/list",
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
	
	$("#add").click(function(){
		$modal=$('#theModal');
	    $modal.load(rootPath+"/model/apply/add.html",'', function(){
			$modal.modal();
	    });
	});
	
	//搜索
	$('#thestatus').change(function() {
		$("#applyTable").bootstrapTable('refresh');
	});
})

apply.deleteOne=function(id,index) {
	$('#applyTable').bootstrapTable('uncheckAll');
	$('#applyTable').bootstrapTable('check', index);
	
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/model/apply/delete",
			data : {
				ids : id
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#applyTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}

apply.commit = function(vid,index) {
	$('#applyTable').bootstrapTable('uncheckAll');
	$('#applyTable').bootstrapTable('check', index);
	$modal=$('#theModal');
    $modal.load(rootPath+"/model/apply/commit.html",{ids:vid}, function(){
		$modal.modal();
    }); //取消原来选人后再提交的方式
	
	/*$.ajax({
		dataType : "json",
		url : rootPath + "/model/apply/commit?id="+vid,
		type : "post",
		success : function(data) {
			if (data.message == "操作成功！") {
				$("#applyTable").bootstrapTable('refresh');
				$("#theModal").modal("hide");
			}
			layer.alert(data.message, {
				title : '提示',
				icon : 0,
				skin : 'layer-ext-moon'
			});
		},
		error : function() {
			layer.alert(data.message, {
				title : '提示',
				icon : 2,
				skin : 'layer-ext-moon'
			});
		}
	});*/
};

apply.editClick = function(vid,index) {
	$('#applyTable').bootstrapTable('uncheckAll');
	$('#applyTable').bootstrapTable('check', index);
	$modal=$('#theModal');
    $modal.load(rootPath+"/model/apply/edit.html?id="+vid,'', function(){
		$modal.modal();
    });
};

apply.ck = function(id,processId,index) {
	$('#applyTable').bootstrapTable('uncheckAll');
	$('#applyTable').bootstrapTable('check', index);
	$modal=$('#theModal');
    $modal.load(rootPath+"/model/apply/checkDetail.html",{id:id,processId:processId}, function(){
		$modal.modal();
    });
};

apply.operation = function(value,row,index){
	
	var str="<a href=javascript:apply.ck('" + row.id + "','"+row.processid+"',"+index+") title=''>审核状态</a>&nbsp;&nbsp;&nbsp;"

	if(row.status=="草稿"||row.status=="被退回"){
		str="<a href=javascript:apply.editClick('" + row.id + "',"+index+") title='修改'>修改</a>&nbsp;&nbsp;&nbsp;"
		+ "<a href=javascript:apply.deleteOne('"+row.id + "',"+index+") title='删除'>删除</a>&nbsp;&nbsp;&nbsp;"
		+ "<a href=javascript:apply.commit('"+row.id + "',"+index+") title='提交'>提交</a>&nbsp;&nbsp;&nbsp;";
	}
	
	 //str+="<a href=\""+rootPath+"/model/apply/ExportExcel?applyId="+row.id+"\">导出</a>";
	
	return str;
};