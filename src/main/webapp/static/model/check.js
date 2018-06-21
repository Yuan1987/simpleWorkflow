var applyCheck = window.NameSpace || {};
$(function() {
	
	$('#applyCheckTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				status : $("#status").val()
			};
		},
		url : rootPath + "/model/check/list",
		cache : false,
		pagination : true,
		searchOnEnterKey:true,
		sidePagination : 'server',
		pageSize : 15,
		toolbar:"#toolbar",
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '名称';
		}
	});
	
	//搜索
	$('#searchButton').click(function() {
		$("#applyCheckTable").bootstrapTable('refresh');
	});
	
	//搜索
	$('#status').change(function() {
		$("#applyCheckTable").bootstrapTable('refresh');
	});
})

applyCheck.checkInfo = function(vid,index,taskId,complete) {
	/*$('#applyCheckTable').bootstrapTable('uncheckAll');
	$('#applyCheckTable').bootstrapTable('check', index);*/
	$modal=$('#theModal');
    $modal.load(rootPath+"/model/check/checkApplyInfo.html",{id:vid,taskId:taskId,complete:complete}, function(){
    	$("#authType").change(function(){
    		var val=this.value;
    		if(val=="05"){
    			$("#pg").css("display","block");
    			$("#fpg").css("display","none");
    			$("#fh").css("display","none");
    		}else if(val=="04"){
    			$("#pg").css("display","none");
    			$("#fpg").css("display","none");
    			$("#fh").css("display","block");
    		}else{
    			$("#pg").css("display","none");
    			$("#fpg").css("display","block");
    			$("#fh").css("display","none");
    		}
    	});
    	$("#authType").change();
		$modal.modal();
    });
};

applyCheck.check = function(vid,pass,taskId) {
	$modal=$('#doCheck');
    $modal.load(rootPath+"/model/check/toCheck.html",{id:vid,pass:pass,taskId:taskId}, function(){
		$modal.modal();
    });
};

applyCheck.processImg=function(processId){
	$modal=$('#processImgModal');
    $modal.load(rootPath+"/model/check/showProcessimg.html",{processId:processId}, function(){
		$modal.modal();
    });
};

applyCheck.operation = function(value,row,index) {
	
	var text="审核";
	if(row.complete){
		text="查看";
	}
	return "<a href=javascript:applyCheck.checkInfo('" + row.id + "',"+index+",'"+row.taskId+"','"+row.complete+"')>"+text+"</a>"
};

 applyCheck.stateFormatter=function(value, row, index) {
	var id = Number(row.id);
	if (row.complete||row.taskKey=='bbmld'){
		return {
			disabled:true
		}
	}
	return value;
}
 
//批量审核
$("#batchCheckApply").click(function() {
	var checked=$('#applyCheckTable').bootstrapTable('getSelections');
	var chk_value =[];
 	for (var i = 0,len=checked.length; i <len; i++){
		chk_value.push({id:checked[i].id,taskId:checked[i].taskId});
 	}
 	if(chk_value.length==0){
 		layer.alert("请选择一条数据", {
			title : '提示',
			icon : 0
		});
   		return;
 	}
 	$modal=$('#doCheck');
    $modal.load(rootPath+"/model/check/toBacthCheck.html",{data:JSON.stringify(chk_value)}, function(){
		$modal.modal();
    });
});

//退回 与测评表流程 共用
applyCheck.back= function(vid, taskId){
	$modal = $('#doCheck');
	$modal.load(rootPath + "/model/efcheck/toBack.html",{
		id : vid,
		taskId : taskId,
		type:"apply"
	}, function() {
		$modal.modal();
	});
};

