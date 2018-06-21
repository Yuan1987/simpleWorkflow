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
            <li><span class="active">评测表管理</span></li>
        </ul>
        <!-- END PAGE BREADCRUMB -->
        <!-- BEGIN PAGE BASE CONTENT -->
        <div class="row">
            <div class="col-md-12">
                <div class="m-top-up-15">
                     <div class="fixed-table-toolbar">
                        <div class="bs-bars pull-left">
                            <div id="toolbar">
                                <button id="dicAdd" class="btn btn-sm">
                                    <i class="fa fa-plus"></i> 新增
                                </button>
                               <%--   <a id="prview" class="btn btn-sm" href="${ctx}/system/base-evaluation/excelView?id=23c42b7f-7c2c-489a-8f1f-db088dc7db28" target="_blank">
                                    <i class="fa fa-print"></i> 预览
                                </a> --%>
                            </div>
                        </div>
                    </div>
                    <table id="evaluTable" class="table"
                           data-side-pagination="server" data-pagination="true"
                           data-row-style="rowStyle" data-side-pagination="server"
                           data-page-list="[10,15, 20, 50, 100, 200]"
                           data-search="true" data-show-refresh="true" data-show-toggle="false"
                           data-show-columns="false"
                           data-show-export="false">
                        <thead>
                        <tr>
                            <th data-field="name" data-halign="center"  data-align="center">测评表名称</th>
                            <th data-field="cnSerial" data-halign="center" data-align="center">所属序列</th>
                            <th data-field="cnLevel" data-halign="center" data-align="center">级别</th>
                            <th data-field="version" data-halign="center" data-align="center">版本号</th>
                            <th data-field="status" data-halign="center" data-align="center">状态</th>
                            <th data-formatter="evalu.operation" data-align="center">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div  class="modal fade" id="evaluModal"  role="dialog"   tabindex="-1"> </div>
<div  class="modal fade" id="evaluModal_ed"  role="dialog"   tabindex="-1"> </div>

<script src="${ctx}/static/model/sys_evaluation.js?v=1&${staticVersion}"></script>
<script>
    var rootPath = '${ctx}';
</script>
