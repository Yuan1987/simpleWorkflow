$(function(){
	
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
	
    //userProfile form 操作
	bootstrapValidator = $('#userProfileForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			phoneno: {
                validators: {
                    phone: {
                      country:"CN"
                        }
                  }
          }
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/userManager/addUserProfile",
			type : "post",
			data : $("#userProfileForm").serialize(),
			success : function(data) {
				layer.alert(data.message, {
					title : '提示',
					icon : 0,
					skin : 'layer-ext-moon'
				});
			},
			error : function(a,b) {
				var message='操作失败';
				if(a.status==401){
					message='没有权限';
				}
				layer.alert(message, {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			}
		});
	});
	
	//userResume form 操作
	bootstrapValidator = $('#userResumeForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/userManager/userResume",
			type : "post",
			data : $("#userResumeForm").serialize(),
			success : function(data) {
				layer.alert(data.message, {
					title : '提示',
					icon : 0
				});
			},
			error : function(a,b) {
				var message='操作失败';
				if(a.status==401){
					message='没有权限';
				}
				layer.alert(message, {
					title : '提示',
					icon : 2
				});
			}
		});
	});
	
	//证书信息新增
	$("#cAdd").click(function(){
		$modal=$('#certificateModal');
		var userId=$("#userId").val();
		
		var data={userId:userId}
		
	    $modal.load(rootPath+"/system/userManager/certificate.html",data, function(){
			$modal.modal();
	    });
	});
	
	$('#certificateTable').bootstrapTable({
	});
	
	$("#cEdite").click(function(){
		$modal=$('#certificateModal');
		
		var checked=$('#certificateTable').bootstrapTable('getSelections');
	   	var chk_value =[];
	   	for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push(checked[i].id)
	   	}
	   	
	   	if(chk_value.length!=1){
	   		layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	   		return;
	   	}
		var userId=$("#userId").val();
		var data={userId:userId,id:chk_value[0]}
	    $modal.load(rootPath+"/system/userManager/certificate.html",data, function(){
			$modal.modal();
	    });
	});
	
	
	$("#cDelete").click(function(){
		
		var checked=$('#certificateTable').bootstrapTable('getSelections');
	   	var chk_value =[];
	   	for (var i = 0,len=checked.length; i <len; i++){
			chk_value.push(checked[i].id);
	   	}
	   	
	   	if(chk_value.length==0){
	   		layer.alert("请选择一条数据", {
				title : '提示',
				icon : 0
			});
	   		return;
	   	}
		index = layer.confirm('确认删除选中的数据吗？', {
			btn : [ '确认', '取消' ] //按钮
		}, function() {
			$.ajax({
				type : "POST",
				url : rootPath + "/system/userManager/certificateDelete",
				data : {
					ids : chk_value.join()
				},
				success : function(data) {
					layer.msg(data.message, {
						icon : 1
					});
					$("#certificateTable").bootstrapTable('refresh');
					layer.close(index);
				}
			});
		}, function() {
			layer.close(index);
		});
		
		
	});
	
	
	
});


        	
