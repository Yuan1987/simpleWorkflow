/**
 * Created by ganlu on 2017/9/18.
 */


var FormRepeater = function () {
    return {
        //main function to initiate the module
        init: function () {
            $('.mt-repeater').each(function(){
                $(this).repeater({
                    show: function () {
                        $(this).slideDown();
                    },
                    hide: function (deleteElement) {
                        if(confirm('确定要删除吗?')) {
                            $(this).slideUp(deleteElement);
                        }
                    },
                    ready: function (setIndexes) {}
                });

            });
        }

    };

}();

var ComponentsBootstrapTouchSpin = function () {
    var t = function () {
            $("#judgesnumber").TouchSpin();
            $("#passmark").TouchSpin({ initval: 60 });
    };
    return {
        init: function () { t() }
    }
}();


$(function() {
    $.fn.modal.Constructor.prototype.enforceFocus = function () {};
   FormRepeater.init();
   // ComponentsBootstrapTouchSpin.init();

    // $("select").select2({
    //     language : "zh-CN",
    //     placeholder : "请选择",
    //     width:"100%"
    // });

    $("select.sp").change(function () {
        $('.dataItem').each(function () {
            $(this).find('.form-control').each(function () {
                if($(this).attr("data_name")!=null&&$(this).attr("data_name")!=undefined) {
                    $(this).val("")
                }
            })
        })
    });




    $('#evluForm').on('init.field.bv', function(e, data) {

    }).bootstrapValidator({
        excluded : [ ':disabled' ],
        fields : {
            passmark:{
                validators: {
                    notEmpty: {
                        message: '通过分数不能为空'
                    },
                    numeric: {
                        message: '通过分数只能是数字'
                    }
                }
            },
            judgesnumber:{
                validators: {
                    notEmpty: {
                        message: '通过分数不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]\d*$/,
                        message: '评委人数只能是数字'
                    }
                }
            }
        }
    }) .on('added.field.fv', function(e, data) {

     }).on('click','#btnAbiliy',function (e) {
         var options= $('.dataItem:last').find(".form-control").not($("textarea"));
         
         console.info(options);
         $("#evluForm").bootstrapValidator("addField", options);
         
     })
         .on('click',".mt-repeater-delete",function (e) {
             if($(e.target).attr("name")!=""&&$(e.target).attr("name")!=undefined) {
                 var preName = $(e.target).attr("name").replace("[deleteitem]", "");
                 var sv1 = $("[name='" + preName + "[name]" + "']"),
                     sv2 = $("[name='" + preName + "[typeid]" + "']"),
                     sv3 = $("[name='" + preName + "[score]" + "']"),
                     sv4 = $("[name='" + preName + "[formula]" + "']"),
                     sv5 = $("[name='" + preName + "[passmark]" + "']");
                     //sv6 = $("[name='" + preName + "[evidence]" + "']");

                 $('#evluForm')
                     .bootstrapValidator('revalidateField', sv1)
                     .bootstrapValidator('revalidateField', sv2)
                     .bootstrapValidator('revalidateField', sv3)
                     .bootstrapValidator('revalidateField', sv4)
                     .bootstrapValidator('revalidateField', sv5)
                    // .bootstrapValidator('revalidateField', sv6)
                     .bootstrapValidator('removeField', sv1)
                     .bootstrapValidator('removeField', sv2)
                     .bootstrapValidator('removeField', sv3)
                     .bootstrapValidator('removeField', sv4)
                     .bootstrapValidator('removeField', sv5);
                     //.bootstrapValidator('removeField', sv6);
             }
     })

         .on('success.form.bv', function(e) {
       // console.log( $('.mt-repeater').repeaterVal());
        e.preventDefault();
       pageInfo.setPostdata();
        var formData=new FormData($("#evluForm")[0]);
        $.ajax({
            dataType : "json",
            url : rootPath + "/system/base-evaluation/save",
            type : "post",
            processData: false,
            contentType: false,
            data : formData,
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
            }
        });
    });

});




var pageInfo={

    /**
     *获取能力
     * @param clickobj
     * @param serial
     * @param theLevel
     */
    getAbilies:function (clickobj) {
        var serial=$("#serial").val();
        var theLevel=$("#level").val();
        var preName=$(clickobj).attr("name").replace("[btnSelect]","");
        $.ajax({
            dataType : "json",
            url : rootPath + "/system/base-evaluation/getabilities",
            type : "post",
            data : {serial:serial,theLevel:theLevel},
            success : function(data) {
                var arr = new Array();
                var mStr='pageInfo.selectAbilicty("'+preName+'","strinput","","","")';
                arr.push("<li><a onclick='"+mStr+"' >----自行输入------</a></li>");
                $(data.rows).each(function (index, element) {
                    arr.push("<li>");
                    var mStr='pageInfo.selectAbilicty("' + preName + '","'
                        +element['name']+'", "'
                        +element['id']+'","'
                        +element['typeid']+'","'
                        +element['evidence']+ '","'+element['score']+'","'+element['formula']+'","'+element['passmark']+'")';
                    arr.push("<a  onclick='"+mStr+"' data-id='" + element['id'] + "' >" + element['name'] + "</a>");
                    arr.push("</li>");
                })
                $(clickobj).next().html(arr.join(""));
            },
            error : function() {

            }
        });
    },
    selectAbilicty:function (preName,sname,id,typeid,evidence,score,formula,passmark) {
        console.log(typeid);
        if (sname == "strinput") {
            $("[name='" + preName + "[name]" + "']").val("").removeAttr("readonly");
            $("[name='" + preName + "[typeid]" + "']").val("").removeAttr("disabled");
            $("[name='" + preName + "[evidence]" + "']").val("").removeAttr("readonly");
            $("[name='" + preName + "[score]" + "']").val("").removeAttr("readonly");
            $("[name='" + preName + "[formula]" + "']").val("").removeAttr("disabled");
            $("[name='" + preName + "[passmark]" + "']").val("").removeAttr("readonly");
        } else {

            $("[name='" + preName + "[name]" + "']").val(sname).attr("readonly","readonly");
            $("[name='" + preName + "[typeid]" + "']").val(typeid).attr("disabled","disabled");
            $("[name='" + preName + "[evidence]" + "']").val(evidence).attr("readonly","readonly");
            $("[name='" + preName + "[score]" + "']").val(score).attr("readonly","readonly");
            $("[name='" + preName + "[formula]" + "']").val(formula).attr("disabled","disabled");
            $("[name='" + preName + "[passmark]" + "']").val(passmark).attr("readonly","readonly");
        }
        $("[name='" + preName + "[id]" + "']").val(id);

        $('#evluForm').bootstrapValidator('revalidateField',  $("[name='" + preName + "[name]" + "']"))
            .bootstrapValidator('revalidateField',  $("[name='" + preName + "[typeid]" + "']"))
            .bootstrapValidator('revalidateField',  $("[name='" + preName + "[score]" + "']"))
            .bootstrapValidator('revalidateField',  $("[name='" + preName + "[formula]" + "']"))
            .bootstrapValidator('revalidateField',  $("[name='" + preName + "[passmark]" + "']"))
            .bootstrapValidator('revalidateField', $("[name='" + preName + "[evidence]" + "']"));


    },
    //提交拼装数据
    setPostdata:function () {
        var abilitystr = "[";
        $('.dataItem').each(function () {
            abilitystr += "{";
            $(this).find('.form-control').each(function () {
                if($(this).attr("data_name")!=null&&$(this).attr("data_name")!=undefined) {
                    abilitystr += "\"" + $(this).attr("data_name") + "\"" + ":" + "\"" + $(this).val() + "\","
                }
            })
            abilitystr = abilitystr.substring(0, abilitystr.length - 1) + "},";
        })
        abilitystr = abilitystr.substring(0, abilitystr.length - 1) + "]";
        var evlstr = "{";
        $('#dvevaluation').find(".form-control").each(function () {
            if($(this).attr("dname")!=null&&$(this).attr("dname")!=undefined) {
                evlstr += "\"" + $(this).attr("dname") + "\"" + ":" + "\"" + $(this).val() + "\","
            }
        });
        evlstr = evlstr.substring(0, evlstr.length - 1) + "}";
        $("#hidEvaluationInfo").val(evlstr);
        $("#hidabilityInfo").val(abilitystr);
    }
};




