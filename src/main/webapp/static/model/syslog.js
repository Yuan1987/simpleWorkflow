var dic = window.NameSpace || {};
$(function() {
	
	$('#syslogTable').bootstrapTable({
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
		url : rootPath + "/system/syslog/list",
		cache : false,
		pagination : true,
		searchOnEnterKey:true,
		sidePagination : 'server',
		toolbar:"#toolbar",
		pageSize : 15,
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '操作人';
		}
	});
})


dic.editClick = function(vid,index) {
	$('#syslogTable').bootstrapTable('uncheckAll');
	$('#syslogTable').bootstrapTable('check', index);
	$modal=$('#dicModal');
    $modal.load(rootPath+"/system/dictionary/edit.html?id="+vid,'', function(){
		$modal.modal();
    });
};

dic.operation = function(value,row,index) {
	return "<a href=javascript:dic.editClick('" + row.id + "',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
	+ "<a href=javascript:dic.deleteOne('"
	+ row.id + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};