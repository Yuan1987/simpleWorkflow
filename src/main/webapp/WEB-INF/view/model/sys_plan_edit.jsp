<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header padding-sty">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3 id="userModelTitle">修改</h3>
        </div>
        <form id="planForm" class="form-horizontal form" method="POST">
            <div class="form-body">
                <div class="form-group">
                    <label class="col-sm-2 control-label">*序列</label>
                    <div class="col-sm-8">
                        <input type="hidden" name="id"  class="form-control"  value="${plan.id}" />
                        <input type="hidden" name="status"  class="form-control"  value="${plan.status}" />
                        <select  class="form-control sp" name="serial"  required="required">
                            <option></option>
                            <c:forEach items="${xlList}" var="dic">
                                <option value="${dic.code}" <c:if test="${dic.code ==plan.serial}">selected</c:if>>${dic.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">*计划名称</label>
                    <div class="col-sm-8">
                        <input type="text" id="name" name="name"  value="${plan.name}" class="form-control" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">*时间段</label>
                    <div class="col-sm-8" >
                        <div class="input-group input-large date-picker input-daterange"   data-date-format="yyyy-mm-dd">
                            <input type="text" value="${plan.starttime}"  id="starttime" class="form-control" name="starttime" required="required">
                            <span class="input-group-addon"> 至 </span>
                            <input type="text" value="${plan.endtime}" id="endtime" class="form-control" name="endtime" required="required"> </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">确认</button>
                <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/static/model/sys_plan_edit.js"></script>

