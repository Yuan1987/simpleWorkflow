/**
 * Created by ganlu on 2017/9/18.
 */
var evalu = window.NameSpace || {};
$(function() {

$("#dicAdd").click(function () {
    $modal=$('#evaluModal');
    $modal.load(rootPath+"/system/base-evaluation/add.html",'', function(){
        $modal.modal();
    });
});

    $('#evaluTable').bootstrapTable({
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        queryParamsType : 'pageSize',
        queryParams : function(params) {
            return {
                page : params.pageNumber,
                size : params.pageSize,
                searchText : params.searchText
            };
        },
        url : rootPath + "/system/base-evaluation/list",
        cache : false,
        pagination : true,
        searchOnEnterKey:true,
        sidePagination : 'server',
        toolbar:"#toolbar",
        pageSize : 15,
        pageList : [ 5, 15, 30, 50, 100 ],
        onLoadSuccess : function() {},
        formatSearch : function() {
            return '测评表名称';
        }
    });
})



evalu.editClick = function(vid,index) {
    var md = $("#evaluModal_ed");
    md.load(rootPath + "/system/base-evaluation/edit.html?id=" + vid, '', function () {
        md.modal();
    });
};

evalu.changeStatus=function(vid,status){
	
	
	var text="";
	
	if(status=="作废"){
		text="确认作废?";
	}else{
		text="确认发布?";
	}
	
	var l = layer.confirm(text,{
		btn : [ '确认', '取消' ]
	},function(){
		$.ajax({
			type:"POST",
			url : rootPath + "/system/base-evaluation/status",
            data : {
                id : vid,
                status:status
            },
            success : function(data) {
                layer.msg(data.message, {
                    icon : 1
                });
                $("#evaluTable").bootstrapTable('refresh');
                layer.close(l);
            }
        });
    }, function() {
        layer.close(l);
    });
	
}
	
evalu.deleteOne=function (vid) {

    var index = layer.confirm('确认删除选中的数据吗？', {
        btn : [ '确认', '取消' ] //按钮
    }, function() {
        $.ajax({
            type : "POST",
            url : rootPath + "/system/base-evaluation/delete",
            data : {
                id : vid
            },
            success : function(data) {
                layer.msg(data.message, {
                    icon : 1
                });
                $("#evaluTable").bootstrapTable('refresh');
                layer.close(index);
            }
        });
    }, function() {
        layer.close(index);
    });
}

evalu.operation = function(value,row) {
	var text="作废";
	var status="";
	if(row.status=="已发布"){
		text="作废";
		status="已作废";
	}else{
		text="发布";
		status="已发布";
	}
	
	return "<a href=javascript:evalu.changeStatus('" + row.id + "','"+status+"')>"+text+"</span></a>&nbsp;&nbsp;&nbsp;"
	+"<a href=javascript:evalu.editClick('" + row.id + "') title='修改'><span>修改</span></a>&nbsp;&nbsp;&nbsp;"
    + "<a href=javascript:evalu.deleteOne('"+ row.id + "') title='删除'><span>删除</span></a>&nbsp;&nbsp;&nbsp;"
    + "<a href=javascript:evalu.prview('"+ row.id + "') title='预览'><span>预览</span></a>&nbsp;&nbsp;&nbsp;";
	
};

evalu.prview = function(id) {
	window.open(rootPath+"/system/base-evaluation/excelView?id="+id);
};

