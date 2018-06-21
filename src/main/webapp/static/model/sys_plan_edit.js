$(function() {

    $.fn.modal.Constructor.prototype.enforceFocus = function () {};


    $('.date-picker').datepicker({
        orientation: "left",
        language: 'zh-CN',
        autoclose: true
    });

    bootstrapValidator = $('#planForm').bootstrapValidator({
        excluded : [ ':disabled' ],
        fields : {
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();

        var st=$("#starttime").val().trim();
        var et=$("#endtime").val().trim();
        if(st.substring(0,4)!=et.substring(0,4)) {
            layer.alert("开始日期与结束日期不在同一年！", {
                title: '提示',
                icon: 2,
                skin: 'layer-ext-moon'
            });
        }else {
            $.ajax({
                dataType: "json",
                url: rootPath + "/system/plan/update",
                type: "post",
                data: $("#planForm").serialize(),
                success: function (data) {
                    if (data.message == "操作成功！") {
                        $("#planTable").bootstrapTable('refresh');
                        $("#planModal").modal("hide");
                    }
                    layer.alert(data.message, {
                        title: '提示',
                        icon: 0,
                        skin: 'layer-ext-moon'
                    });
                },
                error: function () {
                    layer.alert(data.message, {
                        title: '提示',
                        icon: 2,
                        skin: 'layer-ext-moon'
                    });
                }
            });
        }
    });
});
