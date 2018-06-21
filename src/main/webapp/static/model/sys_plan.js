var plan = window.NameSpace || {};
$(function() {

    plan.init();

    $('#planTable').bootstrapTable({
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        queryParamsType : 'pageSize',
        queryParams : function(params) {
            return {
                page : params.pageNumber,
                size : params.pageSize,
                searchText : params.searchText,
                sYear : $("#selyear").val(),
            };
        },
        url : rootPath + "/system/plan/list",
        cache : false,
        pagination : true,
        searchOnEnterKey:true,
        sidePagination : 'server',
        toolbar:"#toolbar",
        pageSize : 15,
        pageList : [ 5, 15, 30, 50, 100 ],
        onLoadSuccess : function() {},
        formatSearch : function() {
            return '名称';
        }
    });
    //搜索
    $('#selyear').change(function() {
        $("#planTable").bootstrapTable('refresh');
    });
    $("#planAdd").click(function(){
        $modal=$('#planModal');
        $modal.load(rootPath+"/system/plan/add.html",'', function(){
            $modal.modal();
        });
    });

    $("#btn-delete").click(function() {
        var checked=$('#planTable').bootstrapTable('getSelections');
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
                url : rootPath + "/system/plan/batchDelete",
                data : {
                    ids : chk_value.join()
                },
                success : function(data) {
                    layer.msg(data.message, {
                        icon : 1
                    });
                    $("#planTable").bootstrapTable('refresh');
                    layer.close(index);
                }
            });
        }, function() {
            layer.close(index);
        });
    });

})

plan.init=function () {
    var date=new Date;
    var year=date.getFullYear();
    for(var i=2017;i<2030;i++) {
        if(i==year)
        {
            $('#selyear').append("<option selected='selected' value='" + i + "'>" + i + "年</option>");
        }
        else {
            $('#selyear').append("<option value='" + i + "'>" + i + "年</option>");
        }
     }


};

plan.deleteOne=function(id,index) {
    $('#planTable').bootstrapTable('uncheckAll');
    $('#planTable').bootstrapTable('check', index);

    var index = layer.confirm('确认删除选中的数据吗？', {
        btn : [ '确认', '取消' ] //按钮
    }, function() {
        $.ajax({
            type : "POST",
            url : rootPath + "/system/plan/batchDelete",
            data : {
                ids : id
            },
            success : function(data) {
                layer.msg(data.message, {
                    icon : 1
                });
                $("#planTable").bootstrapTable('refresh');
                layer.close(index);
            }
        });
    }, function() {
        layer.close(index);
    });
}

plan.editClick = function(vid,index) {
    $('#planTable').bootstrapTable('uncheckAll');
    $('#planTable').bootstrapTable('check', index);
    $modal=$('#planModal');
    $modal.load(rootPath+"/system/plan/edit.html?id="+vid,'', function(){
        $modal.modal();
    });
};

plan.operation = function(value,row,index) {
    return "<a href=javascript:plan.editClick('" + row.id + "',"+index+") title='修改'><span class='fa fa-edit'></span></a>&nbsp;&nbsp;&nbsp;"
        + "<a href=javascript:plan.deleteOne('"
        + row.id + "',"+index+") title='删除'><span class='glyphicon glyphicon-trash'></span></a>";
};