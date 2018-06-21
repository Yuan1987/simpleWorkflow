$(function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$("select").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});
	
	$('.date-picker').datepicker({
        orientation: "left",
        language: 'zh-CN',
        autoclose: true
    });

	bootstrapValidator = $('#exForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			score: {
	            validators: {
		            	between: {
	                        min: 0,
	                        max: 100,
	                        message: '分数应在0-100之间'
	                    }
		            }
		    }
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/exam/update",
			type : "post",
			data : $("#exForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#exTable").bootstrapTable('refresh');
					$("#exModal").modal("hide");
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
		});
	});
});
