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

	bootstrapValidator = $('#ucForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/userManager/certificate",
			type : "post",
			data : $("#ucForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#certificateTable").bootstrapTable('refresh');
					$("#certificateModal").modal("hide");
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
