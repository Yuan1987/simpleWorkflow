$(function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$('.date-picker').datepicker({
        orientation: "left",
        language: 'zh-CN',
        autoclose: true
    });
	
	
	$.ajax({
		dataType : "json",
		url : rootPath + "/model/myability/getPAByFileId",
		type : "post",
		data : "fileId="+fileId,
		success : function(data){
			var ids=[];
			$.each(data,function(i,n){
				ids.push(n.sysAbilityid);
			});
			$("#paid").select2({
				language : "zh-CN",
				width:"100%"
			}).val(ids).trigger("change");
		},
		error : function() {
		}
	});

	bootstrapValidator = $('#myAbilityForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		/*$("#myAbilityForm").ajaxSubmit({
			type : "POST",
			url : rootPath + "/model/myability/update",
			success : function(data) {

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
		});*/
		
		$.ajax({
			dataType : "json",
			url : rootPath + "/model/myability/update",
			type : "post",
			data : $("#myAbilityForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#AbilityTable").bootstrapTable('refresh');
					$("#abilityModal").modal("hide");
				}
				layer.alert(data.message, {
					title : '提示',
					icon : 0,
					skin : 'layer-ext-moon'
				});
			},
			error : function() {
				layer.alert('操作失败！',{
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
});
