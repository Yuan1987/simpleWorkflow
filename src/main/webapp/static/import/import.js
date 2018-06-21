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
			url : rootPath + url,
			type : "post",
			data : formData,
			processData: false,
		    contentType: false,
			success : function(data) {
				
				if (data.count!=0) {
					layer.alert("成功导入："+data.count+"条", {
						title : '提示',
						icon : 0,
						skin : 'layer-ext-moon'
					});
					$("#"+tableId).bootstrapTable('refresh');
					$("#importModalDialog").parent().modal("hide");
				}else{
					layer.alert("操作失败", {
						title : '提示',
						icon : 2,
						skin : 'layer-ext-moon'
					});
				}
				
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
