var evalu = window.NameSpace || {};
$(function() {
	
	$('#evaluTable').bootstrapTable({
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		queryParams : function(params) {
			return {
				page : params.pageNumber,
				size : params.pageSize,
				status : $("#thestatus").val()
			};
		},
		url : rootPath + "/model/evaluation/list",
		cache : false,
		pagination : true,
		searchOnEnterKey:true,
		sidePagination : 'server',
		toolbar:"#toolbar",
		onLoadSuccess : function() {},
		formatSearch : function() {
			return '名称';
		}
	});
	
	//搜索
	$('#thestatus').change(function() {
		$("#evaluTable").bootstrapTable('refresh');
	});
})

evalu.ck = function(id,processId,index) {
	$('#evaluTable').bootstrapTable('uncheckAll');
	$('#evaluTable').bootstrapTable('check', index);
	$modal=$('#evaluModal');
    $modal.load(rootPath+"/model/evaluation/checkDetail.html",{id:id,processId:processId}, function(){
		$modal.modal();
    });
};

evalu.operation = function(value,row,index){
	
	var efid=row.evaluationFormId==undefined?"":row.evaluationFormId;
	var str="<a href=javascript:evalu.editClick('" + row.id + "','"+row.seq+"','"+row.grade+"','"+efid+"') title='测评表'>测评表</a>&nbsp;&nbsp;&nbsp;"
		/*+ "<a href=javascript:evalu.commit('"+row.id + "',"+index+") title='提交'>提交</a>&nbsp;&nbsp;&nbsp;"*/
		;
	
	 if(row.evalProcessId!=undefined){
		str+="<a href=javascript:evalu.ck('" + row.evaluationFormId + "','"+row.evalProcessId+"',"+index+")>审核状态</a>&nbsp;&nbsp;&nbsp;"; 
	 }
	 
	 if(row.evalStatus=='已完成'){
		 str+="<a href=\""+rootPath+"/model/apply/ExportExcel?applyId="+row.id+"&efId="+row.evaluationFormId+"\">导出</a>";
	 }
	return str;
};

evalu.editClick=function(applyId,seq,grade,evaluationFormId){
	$modal=$('#evaluModal');
    $modal.load(rootPath+"/model/evaluation/edit.html",{applyId:applyId,serial:seq,level:grade,evaluationFormId:evaluationFormId}, function(){
    	$("#evaluTable").bootstrapTable('refresh');
		$modal.modal();
    });
};