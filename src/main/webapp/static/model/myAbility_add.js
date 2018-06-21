$(function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$("select").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});
	

	bootstrapValidator = $('#myAbilityForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			istrue: {
				trigger:"change",
				 validators: {
		             notEmpty: {
			             message: '请确认文件已脱密'
			         }
		         } 
		    }
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$("#myAbilityForm").ajaxSubmit({
			type : "POST",
			url : rootPath + "/model/myability/add.do",
			success : function(data) {
				
				data= eval('(' + data + ')');

				if (data.message == "操作成功！") {
					$("#AbilityTable").bootstrapTable('refresh');
					$("#abilityModal").modal("hide");
				}

				layer.alert(data.message, {
					title : '提示',
					icon : 0
				});
			},
			error : function(msg) {
				layer.alert('操作失败！', {
					title : '提示',
					icon : 2
				});
			}
		});
		return false;
	});
	
});
