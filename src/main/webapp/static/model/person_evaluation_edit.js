$(function(){
	
	var showFooter=false;
	
	if(efstatus=='已完成'&&needMark=="true"){
		showFooter=true;
	}
	
	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.emptytext = '空';
	var curRow = {};
	$('#evaluFormTable').bootstrapTable({
		classes:"table",
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		showFooter:showFooter,
		queryParams : function(params) {
			return {
				serial:serial,
				level:serial,
				applyId:applyId,
				evaluationFormId:evaluationFormId
			};
		},
		url : rootPath + "/model/evaluation/table",
		cache : false,
		sidePagination : 'server',
		toolbar:"#toolbar",
		onClickRow: function (row, $element) {
            curRow = row;
        },
		onLoadSuccess : function(data) {                                
            var data = $('#evaluFormTable').bootstrapTable('getData', true);
            
            //合并单元格
            mergeCells(data, "typeV", 1, $('#evaluFormTable'));
            
            //注释掉 editable
            /*$(".editableA").editable({
                url: function (params) {
                    var sName = $(this).attr("name");
                    curRow[sName] = params.value;
                    $.ajax({
                        type: 'POST',
                        url: rootPath+"/model/evaluation/editAbility",
                        data: curRow,
                        dataType: 'JSON',
                        success: function (data) {
                        	layer.alert(data.message, {
            					title : '提示',
            					icon : 1
            				});
                        },
                        error: function () {
                        	layer.alert('操作失败！', {
            					title : '提示',
            					icon : 2
            				});
                        }
                    });
                },
            });*/
		}
	});
});

/**
 * 合并单元格
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称
 * @param colspan   合并列
 * @param target    目标表格对象
 */
function mergeCells(data,fieldName,colspan,target){
    //声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    for(var i = 0 ; i < data.length ; i++){
        for(var prop in data[i]){
            if(prop == fieldName){
                var key = data[i][prop]
                if(sortMap.hasOwnProperty(key)){
                    sortMap[key] = sortMap[key] * 1 + 1;
                } else {
                    sortMap[key] = 1;
                }
                break;
            } 
        }
    }
    var index = 0;
    for(var prop in sortMap){
        var count = sortMap[prop] * 1;
        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
        index += count;
    }
}


eval.description=function (value, row, index) {
	
	/* 注释掉 editable if(value==undefined){
		value="";
	}
    return "<a href=\"#\"  class=\"editableA\" name=\"description\" data-type=\"textarea\"  data-placeholder='' data-pk=\""+row.id+"\" data-title=\"能力描述\">" + value + "</a>";*/
	
	var str="";
	$.each(value,function(i,n){
		if(n.description){
			str=str+n.description+",";
		}
	});
	return str;
};


eval.fileInput=function(paId){
	$modal=$('#paFileModal');
    $modal.load(rootPath+"/model/evaluation/fileInput.html",{paId:paId,efstatus:efstatus}, function(){
		$modal.modal();
    });
};

eval.file=function (value, row, index) {
	
	if(value==undefined||value.length==0){
		if((efstatus=='草稿'||efstatus=='审核未通过')&&row.evidence!=""){
			value="编辑";
		}else{
			value="";
		}
	}else{
		/*var str="";
		$.each(value,function(i,n){
			str=str+n.friendlyfilename+",";
		});
		value=str;*/
		var str="";
		$.each(value,function(i,n){
			str+="<div class=\"tdDiv\"><span>"+n.friendlyfilename+"</span><span>"+n.description+"</span></div>";
		});
		value=str;
	}
    return "<a onclick=\"eval.fileInput('"+row.id+"')\" title='查看或修改' class=\"fileInput\">"+value+"</a>";
};

eval.commit = function(vid) {
	
	var allData = $('#evaluFormTable').bootstrapTable('getData');
	$("#cAdd").attr("disabled","disabled");
	var abled=true;
	var aName="";
	
	$.each(allData,function(i,n){
		var files=n.files;
		aName=n.name;
		if(n.evidence!=""&&files.length==0){
			abled=false;
			return false;
		}
	});
	
	if(!abled){
		layer.alert("请上传【"+aName+"】的证据输出件", {
			title : '提示',
			icon : 2,
			skin : 'layer-ext-moon'
		});
		$("#cAdd").removeAttr("disabled");
		return;
	}
	$.ajax({
		dataType : "json",
		url : rootPath + "/model/evaluation/commit2?id="+vid,
		type : "post",
		success : function(data) {
			if (data.message == "操作成功！") {
				$("#evaluTable").bootstrapTable('refresh');
				$("#evaluModal").modal("hide");
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
		},
		complete:function(){
			$("#cAdd").removeAttr("disabled");
		}
	});
};

eval.total2=function(value){
	var total=0;
	$.each(value,function(i,n){
		var totalscoe=0;
		var paes=value[i].paes;
		$.each(paes,function(i,n){
			var scoe=n.scoring==undefined?0:n.scoring;
			totalscoe+=scoe;
		});
		var avg=totalscoe/paes.length;
		avg=avg.toFixed(1);
		total+=Number(avg);
	});
	return "合计:"+total.toFixed(1);
};

//计算得分
var scorings=[];//每一个能力项 分数
eval.count=function(value, row){
	var str="";
	if(value==undefined||value.length==0){//无测评人
		return str;
	}else{
		var totalscoe=0;
		var paeId="";
		$.each(value,function(i,n){
			var scoe=n.scoring==undefined?0:n.scoring;
			totalscoe=totalscoe+scoe;
		});
		str=totalscoe/value.length;
		str=str.toFixed(1);
		var scoring={};
		scoring["id"]=row.id;
		scoring["scoring"]=str;
		scorings.push(scoring);
	}
  return str;
};