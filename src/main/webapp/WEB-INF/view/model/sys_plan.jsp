<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />

<div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
        <!-- BEGIN PAGE HEAD-->
        <ul class="page-breadcrumb breadcrumb">
            <li><a href="#">系统管理</a> <i class="fa fa-circle"></i></li>
            <li><span class="active">计划管理</span></li>
        </ul>
        <!-- END PAGE BREADCRUMB -->
        <!-- BEGIN PAGE BASE CONTENT -->
        <div class="row">
            <div class="col-md-12">
                <div class="m-top-up-15">
                    <div id="toolbar">
                        <div class="form-inline">
                           <button id="planAdd" class="btn btn-sm">
                               <i class="fa fa-plus"></i> 新增
                           </button>
                            <span>年度</span>
                             <select class="form-control sp"   id="selyear"  >
                                <option value="0">全部</option>
                            </select>
                    </div>
                </div>
                <table id="planTable" class="table"
                       data-side-pagination="server" data-pagination="true"
                       data-row-style="rowStyle" data-side-pagination="server"
                       data-page-list="[10,15, 20, 50, 100, 200]"
                       data-search="true" data-show-refresh="true" data-show-toggle="false"
                       data-show-columns="false"
                       data-show-export="true">
                    <thead>
                    <tr>
                        <th data-checkbox="true"></th>
                        <th data-field="name" data-halign="center"
                            data-align="center">计划名称</th>
                        <th data-field="cnSerial" data-halign="center"
                            data-align="center">序列</th>
                        <th data-field="syear" data-halign="center"
                            data-align="center">年度</th>
                        <th data-field="starttime" data-halign="center"
                            data-align="center">开始日期</th>
                        <th data-field="endtime" data-halign="center"
                            data-align="center">结束日期</th>

                        <th data-formatter="plan.operation" data-align="center">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

</div>

<div class="modal fade" id="planModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>

<script src="${ctx}/static/model/sys_plan.js"></script>
<script>
    var rootPath = '${ctx}';
</script>
