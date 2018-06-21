$(function() {

	bootstrapValidator = $('#userForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			orgIds: {
		 		trigger:"change",
				 validators: {
		             notEmpty: {
			         }
		         } 
		    },roleIds: {
		 		trigger:"change",
				 validators: {
		             notEmpty: {
			         }
		         } 
		    }
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			dataType : "json",
			url : rootPath + "/system/userManager/add",
			type : "post",
			data : $("#userForm").serialize(),
			success : function(data) {
				
				if(data.message=='数据重复！'){
					var orgs=data.orgs;
					var orgNames="";
					var userId=data.userId;
					
					$.each(orgs,function(i,n){
						orgNames+=n.displayname+"、";
					});
					orgNames=orgNames.substring(0,orgNames.length-1);
					var index = layer.confirm('该账号已存在于【'+orgNames+'】，是否为同一人员并将该人员添加到当前部门？', {
						btn : [ '是', '否' ] //按钮
					}, function() {
						$("#userId").val(userId);
						$.ajax({
							type : "POST",
							url : rootPath + "/system/userManager/userHandle",
							data : $("#userForm").serialize(),
							success : function(data) {
								layer.msg(data.message, {
									icon : 1
								});
								$("#userTable").bootstrapTable('refresh');
								$("#userModal").modal("hide");
								layer.close(index);
							}
						});
					}, function() {
						layer.close(index);
					});
					
				}else{
					if (data.message == "操作成功！") {
						$("#userTable").bootstrapTable('refresh');
						$("#userModal").modal("hide");
					}
					layer.alert(data.message, {
						title : '提示',
						icon : 0,
						skin : 'layer-ext-moon'
					});
				}
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
	
	$("#role").select2({
		language : "zh-CN",
		width:"100%",
		ajax:{
			url : rootPath + "/system/roleManager/roleListByOrgId?orgId=" + orgIds,
			dataType : 'json',
			type : "post",
			quietMillis : 250,
			data : function(term, page) {
				return {
					query : term, // search term
				};
			},
			processResults : function(data, page) {
				
				return {
					results : data
				};
			},
			cache : true
		},
        templateSelection: function(item){
        	return item.text;
        }
	});
});

function orgChange(){
	var orgIds=$("#orgIds").val();
	$("#role").val("");
	$("#role").select2({
		language : "zh-CN",
		width:"100%",
		ajax:{
			url : rootPath + "/system/roleManager/roleListByOrgId?orgId=" + orgIds,
			dataType : 'json',
			type : "post",
			quietMillis : 250,
			data : function(term, page) {
				return {
					query : term, // search term
				};
			},
			processResults : function(data, page) {
				return {
					results : data
				};
			},
			cache : true
		},
        templateSelection: function(item){
        	return item.text;
        }
	});
}
