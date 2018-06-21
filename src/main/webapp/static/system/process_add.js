var user = window.NameSpace || {};

$(function(){
	
	bootstrapValidator = $('#importForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		var formData=new FormData($("#importForm")[0]);
		
		$.ajax({
			dataType : "json",
			url : rootPath +"/system/process/deploy",
			type : "post",
			data : formData,
			processData: false,
		    contentType: false,
			success : function(data){
				$("#processTable").bootstrapTable('refresh');
				$("#processModal").modal("hide");
			},
			error : function() {
				layer.alert("操作失败", {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
	
	
});
