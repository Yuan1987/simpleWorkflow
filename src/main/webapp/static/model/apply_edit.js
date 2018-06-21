$(function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$("#applyForm select").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"100%"
	});
	
	$('.year').datepicker({
        orientation: "left",
        language: 'zh-CN',
        autoclose: true,startView: 2,  maxViewMode: 2,minViewMode:2
    });
	
	 $('.date-picker').datepicker({
        orientation: "left",
        language: 'zh-CN',
        autoclose: true
    });
	 
	
	
	$("#authType").change(function(){
		var val=this.value;
		if(val=="05"){
			$("#pg").css("display","block");
			$("#fpg").css("display","none");
			$("#fh").css("display","none");
		}else if(val=="04"){
			$("#pg").css("display","none");
			$("#fpg").css("display","none");
			$("#fh").css("display","block");
		}else{
			$("#pg").css("display","none");
			$("#fpg").css("display","block");
			$("#fh").css("display","none");
		}
	});
	
	$("#authType").change();

	bootstrapValidator = $('#applyForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$("#authType").removeAttr("disabled");
		$.ajax({
			dataType : "json",
			url : rootPath + "/model/apply/edit",
			type : "post",
			data : $("#applyForm").serialize(),
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#applyTable").bootstrapTable('refresh');
					$("#theModal").modal("hide");
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
	
	$("#seq,#grade").change(function(){
		var authType="05";
		var seq=$("#seq").val();
		var currentSeq=$("#jobstypeid").val();
		if(seq==""){
			return;
		}
		if(seq!=currentSeq){//跨序列
			$("#authType").val("03").trigger("change");
		}else{
			//申报级别
			var jb=$("#grade").val();
			if(jb==""||seq==""){
				return;
			}
			//当前级别
			var currentJb=$("#jobsgrade").val();
			//已具备级别
			var hjb=$("#havequalificationgrade").val();
			var hseq=$("#havequalificationseqid").val();
			console.info(hseq);
			console.info("-----"+jb+"--hjb"+hjb+"---hseq"+hseq+" ---seq"+seq);
			
			console.info((jb==hjb)+"----"+(hseq==seq));
			if(jb==currentJb){
				if((jb==hjb)&&(hseq==seq)){//如他在a岗位b层上，申请认证a序列的b级任职资格，系统先判断其是否已有a序列b级的任职资格，若有，复核；若没有，平级认证
					authType="04";
				}else{
					authType="01";
				}
				
			}else if(jb-currentJb==1){//如他在a岗位b层上，申请认证a序列的b+1级任职资格，系统先判断其是否已有a序列b+1级的任职资格，若有，复核；若没有，高一级认证
				if(jb==hjb&&hseq==seq){
					authType="04";
				}else{
					authType="02";
				}
			}else{
				authType="05";
			}
			$("#authType").val(authType).trigger("change");
		}
	});
});
