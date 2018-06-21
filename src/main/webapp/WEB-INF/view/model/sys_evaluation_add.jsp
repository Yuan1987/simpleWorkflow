<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"type="text/css" />

 <%--<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css"type="text/css" />--%>





<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header padding-sty">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3 id="userModelTitle">测评表新增</h3>
        </div>

        <form id="evluForm" class="form-horizontal form" method="POST" enctype="multipart/form-data">
            <div class="form-body">
                <%--<h4 class="form-section">测评表</h4>--%>
                <div id="dvevaluation">

                <div class="form-group">
                    <label class="col-sm-2 control-label">表名称*</label>
                    <div class="col-sm-4">
                        <input type="text" id="name" dname="name" name="name" maxlength="25"
                               class="form-control" placeholder="" required="required">
                    </div>
                    <label class="col-sm-2 control-label">版本号*</label>
                    <div class="col-sm-4">
                        <input type="text" id="version" dname="version" name="version"
                               maxlength="25"  class="form-control" placeholder="" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">所属序列*</label>
                    <div class="col-sm-4">

                        <select class="form-control sp"  dname="serial" id="serial" name="serial" required="required">
                            <option></option>
                            <c:forEach items="${dics_sbxl}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>



                    </div>
                    <label class="col-sm-2 control-label">所属级别*</label>
                    <div class="col-sm-4">


                        <select class="form-control sp"  name="level"  dname="level" id="level" required="required">
                            <option></option>
                            <c:forEach items="${dics_ssjb}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>


                    </div>
                </div>
           <!--      <div class="form-group">
                    <label class="col-sm-2 control-label">评委人数*</label>
                    <div class="col-sm-4">
                        <input type="text" id="judgesnumber" dname="judgesnumber" name="judgesnumber"
                               class="form-control" placeholder="" required="required">
                    </div>
                    <label class="col-sm-2 control-label">通过分数*</label>
                    <div class="col-sm-4">
                        <input type="text" id="passmark" dname="passmark" name="passmark"
                               class="form-control" placeholder="" required="required">
                    </div>
                </div> -->
                <div class="form-group">
                    <label class="col-sm-2 control-label">评分标准*</label>
                    <div class="col-sm-8">
                        <input type="hidden" id="standardFile" dname="standardFile"
                               class="form-control"  />

                        <div class="fileinput fileinput-new" data-provides="fileinput">
                            <div class="input-group input-large">
                                <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
                                    <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                    <span class="fileinput-filename"> </span>
                                </div>
                                <span class="input-group-addon btn default btn-file">
                                    <span class="fileinput-new">选择文件</span>
                                    <span class="fileinput-exists">更改</span>
                                    <input type="file" name="file"  required="required" > </span>
                                <a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput">删除</a>
                            </div>
                        </div>

                    </div>
                </div>
                </div>
                <h4 class="form-section">能力项</h4>
                    <div class="mt-repeater">
                    <div data-repeater-list="group-a">
                        <div class="dataItem" data-repeater-item>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">能力名称*</label>
                                <div class="col-sm-4" >
                                        <div class="input-group">
                                            <div class="input-group-btn">

                                                <button name="btnSelect"  type="button" onclick="pageInfo.getAbilies(this)" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" >
                                                </ul>
                                            </div>
                                            <input type="hidden"     name="id"    data_name="id">
                                            <input type="text"  required="required"  maxlength="25"  data_name="name"   name="name" class="form-control">
                                        </div>

                                </div>
                                <label class="col-sm-2 control-label">能力类别*</label>
                                <div class="col-sm-4">
                                    <%--<input type="text" class="form-control" name="typeid" required="required"--%>
                                           <%--data_name="typeid" >--%>

                                    <select class="form-control"  name="typeid"  data_name="typeid"   required="required">
                                        <option></option>
                                        <c:forEach items="${dics_abilitykind}" var="dic">
                                            <option value="${dic.code}">${dic.detail}</option>
                                        </c:forEach>
                                    </select>


                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">能力分值*</label>
                                <div class="col-sm-2" >
                                    <input type="text" class="form-control"
                                           onkeyup="if(isNaN(value))execCommand('undo')"
                                           onafterpaste="if(isNaN(value))execCommand('undo')" name="score" required="required"
                                           data_name="score" >

                                </div>
                                <label class="col-sm-2 control-label">计算公式*</label>
                                <div class="col-sm-2">


                                    <select class="form-control"  name="formula"  data_name="formula" id="formula" required="required">
                                        <option></option>
                                        <c:forEach items="${dics_formula}" var="dic">
                                            <option value="${dic.code}">${dic.detail}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="col-sm-2 control-label">权重*</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control"
                                           onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"
                                           name="passmark" required="required"
                                           data_name="passmark" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">证据的输出件</label>
                                <div class="col-sm-8">
                                    <textarea  class="form-control" 
                                               data_name="evidence"  name="evidence"  rows="3"  maxlength="500"  ></textarea>
                                </div>
                                <div class="col-sm-2">
                                <div class="mt-repeater-input">
                                    <a href="javascript:;"   name="deleteitem" data-repeater-delete class="btn btn-danger mt-repeater-delete">
                                        <i class="fa fa-close"></i> 删除</a>
                                </div>
                                </div>
                            </div>

                            <hr>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-2 col-sm-offset-9">
                            <a href="javascript:;"  id="btnAbiliy" data-repeater-create class="btn btn-success mt-repeater-add">
                                <i class="fa fa-plus">增加能力</i>
                            </a>
                        </div>


                    </div>
                </div>


            </div>


            <div class="modal-footer">
                <button type="submit" id="btnSaveAll" class="btn btn-primary">确定</button>
                <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
            </div>

            <input type="hidden"   name="operationtype" value="add" />
            <input type="hidden" id="hidEvaluationInfo" name="hidEvaluationInfo" />
            <input type="hidden" id="hidabilityInfo" name="hidabilityInfo" />
        </form>
    </div>
</div>



 <script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-repeater/jquery.repeater.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>

<%--<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.min.js"></script>--%>

<script type="text/javascript" src="${ctx}/static/model/sys_evaluation_add.js?version=1"></script>



