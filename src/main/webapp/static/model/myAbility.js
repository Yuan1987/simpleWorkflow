var myAbility = window.NameSpace || {};
$(function() {
    $('#AbilityTable').bootstrapTable({
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
        url : rootPath + "/model/myability/list",
        cache : false,
        pagination : true,
        searchOnEnterKey:true,
        sidePagination : 'server',
        toolbar:"#toolbar",
        pageSize : 5,
        pageList : [ 5, 15, 30, 50, 100 ],
        onLoadSuccess : function() {},
        formatSearch : function() {
            return '名称';
        }
    });
    
    $("#btnAdd").click(function(){
		$modal=$('#abilityModal');
	    $modal.load(rootPath+"/model/myability/add.html",'', function(){
			$modal.modal();
	    });
	});
})

myAbility.editClick = function(vid,index) {
	$('#AbilityTable').bootstrapTable('uncheckAll');
	$('#AbilityTable').bootstrapTable('check', index);
	$modal=$('#abilityModal');
    $modal.load(rootPath+"/model/myability/edit.html?id="+vid,'', function(){
		$modal.modal();
    });
};

myAbility.operation = function(value,row,index) {
	return "<a href=javascript:myAbility.editClick('" + row.guid + "',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
	+ "<a href=javascript:myAbility.deleteOne('"
	+ row.guid + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};

myAbility.download=function(value,row){
	var str="<a target='_blank' href="+fileBasePath+row.filephysicalpath+">"+value+"</a>";
    return str;
};

myAbility.paList=function(value,row){
	var str="";
	if(value!=undefined){
		$.each(value,function(i,n){
			str+=n.name+",";
		});
	}
	return str;
};

myAbility.deleteOne=function(id,index) {
	$('#AbilityTable').bootstrapTable('uncheckAll');
	$('#AbilityTable').bootstrapTable('check', index);
	
	var index = layer.confirm('确认删除选中的数据吗？', {
		btn : [ '确认', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : "POST",
			url : rootPath + "/model/myability/delete",
			data : {
				fileId : id
			},
			success : function(data) {
				layer.msg(data.message, {
					icon : 1
				});
				$("#AbilityTable").bootstrapTable('refresh');
				layer.close(index);
			}
		});
	}, function() {
		layer.close(index);
	});
}