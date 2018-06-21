var flow = window.NameSpace || {};
$.fn.modal.Constructor.prototype.enforceFocus = function () {};

$(function() {
	
	$("select").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});
	
    $('#planTable').bootstrapTable({
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        queryParamsType : 'pageSize',
        queryParams : function(params) {
            return {
                page : params.pageNumber,
                size : params.pageSize,
                searchText : params.searchText,
                sYear : $("#selyear").val(),
            };
        },
        url : rootPath + "/model/flowManager/list",
        cache : false,
        pagination : true,
        searchOnEnterKey:true,
        sidePagination : 'server',
        pageSize : 15,
        pageList : [ 5, 15, 30, 50, 100 ],
        onLoadSuccess : function() {},
        detailView: true,//父子表
        onExpandRow: function (index, row, $detail) {
            flow.getChildTable(index, row, $detail);
        },
        formatSearch : function() {
            return '申请人/被审核人';
        }
    });
    
  //验证
  	bootstrapValidator = $('#taskTransferForm').bootstrapValidator({
  		excluded : [ ':disabled' ],
  		fields : {
  		}
  	}).on('success.form.bv', function(e) {
  		e.preventDefault();
  		$.ajax({
  			dataType : "json",
  			url : rootPath+ "/model/flowManager/taskTransfer",				
  			type : "post",
  			data :$("#taskTransferForm").serialize(),
  			success : function(data){
  				if(data.message=="操作成功！"){
  					$("#theModal").modal("hide");
  					$("#child_table").bootstrapTable('refresh');
  				}
  				layer.alert(data.message, {
  					title : '提示',
  					icon : 0,
  					skin : 'layer-ext-moon'
  				});
  			},
  			error:function(){
  				layer.alert('操作失败', {
  					title : '提示',
  					icon : 2,
  					skin : 'layer-ext-moon'
  				});
  			}
  		});
  	});
    
})

flow.typeFormatter = function(value,row,index) {
	
	var str="申请环节";
	if(row.procDefId=='456'){
		str="评测环节";
	}
    return str;
};

//获取子表的测评信息
flow.getChildTable=function(index, row, $detail) {
  var pid = row.id;
  var cur_table = $detail.html('<table id="child_table"></table>').find('table');
  $(cur_table).bootstrapTable({
	  method : 'POST',
      contentType : "application/x-www-form-urlencoded",
      queryParamsType : 'pageSize',
      queryParams : function(params) {
      },
      url : rootPath + "/model/flowManager/taskList?procinstId="+pid,
      columns: [
            {
                field: 'name',
                title: '环节',
                align: 'center'
            },
           {
              field: 'username',
              title: '办理人',
              align: 'center',
           },
           {
               field: 'startTime',
               title: '开始时间',
               align: 'center'
           },
           {
               field: 'endTime',
               title: '完成时间',
               align: 'center'
           },
           {
               field: 'userId',
               title: '操作',
               align: 'center',
               formatter:function(val,row){
            	   if(!row.endTime){
            		   return "<a href=javascript:flow.taskTransfer('" + row.id +"','"+val+"'); title='转办'><span class='fa fa-share'></span></a>";
            	   }else{
            		   return "-";
            	   }
            	   
               }
           }
      ]
  });
};

flow.taskTransfer=function(taskId,oldUserId){
	$("#taskId").val(taskId);
	$("#oldUserId").val(oldUserId);
	$("#newUserId").select2("val","");
	$("#newUserId").val(null);
	$("#theModal").modal("show");
};


$('#theModal').on('hidden.bs.modal', function(e){
	$("#newUserId").val(null).trigger("change");
});
