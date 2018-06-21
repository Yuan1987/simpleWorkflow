var efCheck = window.NameSpace || {};
$(function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	var url=rootPath + "/model/efcheck/list";
	
	$('#efCheckTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				status : $("#status").val(),
				node:$("#node").val()
			};
		},
		url : url,
		cache : false,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : 'server',
		pageSize : 15,
		toolbar : "#toolbar",
		pageList : [ 5, 15, 30, 50, 100 ],
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '名称';
		}
	});

	//搜索
	$('#status,#node').change(function() {
		$("#efCheckTable").bootstrapTable('refresh');
	});
	
	$('#btn_query').click(function() {
		$("#efCheckTable").bootstrapTable('refresh');
	});
	
	//批量审核
	$("#batchCheckApply").click(function(){
		var data = $('#efCheckTable').bootstrapTable('getData',true);
		$.each(data,function(i,v){
			if((v.taskKey!="syb"&&v.taskKey!='hz')){//当为事业部，才能批量审核
				$('#efCheckTable').bootstrapTable('uncheck',i);
			}
		});
		
		var checked=$('#efCheckTable').bootstrapTable('getSelections');
		var chk_value =[];
		for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push({id:checked[i].evaluationFormId,taskId:checked[i].taskId});
		}
		if(chk_value.length==0){
			layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	  		return;
		}
		$modal=$('#doCheck');
	    $modal.load(rootPath+"/model/efcheck/toBacthCheck.html",{data:JSON.stringify(chk_value)}, function(){
			$modal.modal();
	   });
	});
	
	//批量指定
	$("#batchChose").click(function(){
		var data = $('#efCheckTable').bootstrapTable('getData',true);
		$.each(data,function(i,v){
			if(v.taskKey!="xlxz"){//当不为xlxz时，不能批量指定
				$('#efCheckTable').bootstrapTable('uncheck',i);
			}
		});
		
		var checked=$('#efCheckTable').bootstrapTable('getSelections');
		var chk_value =[];
		for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push({id:checked[i].evaluationFormId,taskId:checked[i].taskId});
		}
		if(chk_value.length==0){
			layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	  		return;
		}
		$modal=$('#theModal');
	   $modal.load(rootPath+"/model/efcheck/toBacthChose.html",{data:JSON.stringify(chk_value)}, function(){
			$modal.modal();
	   });
	});
	
	//批量指定
	$("#batchPublish").click(function(){
		var data = $('#efCheckTable').bootstrapTable('getData',true);
		$.each(data,function(i,v){
			if(v.taskKey!="gb"){//当不为gb时，不能公布
				$('#efCheckTable').bootstrapTable('uncheck',i);
			}
		});
		
		var checked=$('#efCheckTable').bootstrapTable('getSelections');
		var chk_value =[];
		for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push({id:checked[i].evaluationFormId,taskId:checked[i].taskId});
		}
		if(chk_value.length==0){
			layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	  		return;
		}
	   
	  var index = layer.confirm('确定公布吗？', {
			btn : [ '确定', '取消' ] //按钮
		}, function() {
			layer.close(index);
			$("#batchPublish").attr("disabled","disabled");
			$.ajax({
				dataType : "json",
				async:false,
				url : rootPath + "/model/efcheck/batchCheck",
				type : "post",
				data : "data="+JSON.stringify(chk_value)+"&result=true&opinion=",
				success : function(data) {
					$("#efCheckTable").bootstrapTable('refresh');
				},
				error : function() {
					layer.alert(data.message, {
						title : '提示',
						icon : 2,
						skin : 'layer-ext-moon'
					});
				},
				complete:function(){
					$("#batchPublish").removeAttr("disabled");
				}
			});
		}, function() {
			layer.close(index);
		});
	});
});

efCheck.checkInfo = function(vid, index, taskId, complete,taskKey) {
	/*$('#efCheckTable').bootstrapTable('uncheckAll');
	$('#efCheckTable').bootstrapTable('check', index);*/
	$modal = $('#theModal');
	$modal.load(rootPath + "/model/efcheck/checkApplyInfo.html", {
		efid : vid,
		taskId : taskId,
		complete : complete,
		taskKey:taskKey
	}, function() {
		$("#authType").change(function() {
			var val = this.value;
			if (val == "05") {
				$("#pg").css("display", "block");
				$("#fpg").css("display", "none");
				$("#fh").css("display", "none");
			} else if (val == "04") {
				$("#pg").css("display", "none");
				$("#fpg").css("display", "none");
				$("#fh").css("display", "block");
			} else {
				$("#pg").css("display", "none");
				$("#fpg").css("display", "block");
				$("#fh").css("display", "none");
			}
		});
		$("#authType").change();
		$modal.modal();
	});
};

/*efCheck.check = function(vid, pass, taskId) {
	$modal = $('#doCheck');
	$modal.load(rootPath + "/model/efcheck/toCheck.html", {
		id : vid,
		pass : pass,
		taskId : taskId
	}, function() {
		$modal.modal();
	});
};*/

efCheck.operation = function(value, row, index) {
	
	var text = "审核";
	
	if(row.taskKey=='xlxz'){
		text="指定评测人";
	}else if(row.taskKey=='cpr'){
		text="测评";
	}
	
	if (row.complete||row.taskKey=='gb') {
		text = "查看";
	}
	return "<a href=javascript:efCheck.checkInfo('" + row.evaluationFormId + "'," + index +
			",'" + row.taskId + "','" + row.complete + "','" + row.taskKey + "')>" + text + "</a>"
};

efCheck.stateFormatter=function(value, row, index) {
	//测评表流程中只有syb审批可以批量审核||序列小组批量指定人
	if (!row.complete&&(row.taskKey=='syb'||row.taskKey=="xlxz"||row.taskKey=='gb'||row.taskKey=='hz')){
		return value;
	}else{
		return {
			disabled:true
		}
	}
}


