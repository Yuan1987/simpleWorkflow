<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"type="text/css" />
<div class="modal-dialog" id="importModalDialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">excel导入</h3>
		</div>
		<form id="importForm" class="form-horizontal" method="POST" enctype="multipart/form-data">
			<div class="form-group margin-top-10">
				<label class="col-sm-2 control-label">*excel</label>
				<div class="col-sm-7">
					<div class="fileinput fileinput-new" data-provides="fileinput">
                            <div class="input-group input-large">
                                <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
                                    <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                    <span class="fileinput-filename"> </span>
                                </div>
                                <span class="input-group-addon btn default btn-file">
                                    <span class="fileinput-new">选择文件</span>
                                    <span class="fileinput-exists">更改</span>
                                    <input type="file" name="file" required="required"> </span>
                                <a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput">删除</a>
                            </div>
                        </div>
				</div>
				<div class="col-sm-2 control-label">
					<a href="${ctx}/static/import/template/${templateName}" target="blank">模板下载</a>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确认</button>
				<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
			</div>
		</form>
	</div>
</div>
<script>
	var url="${url}";
	var tableId="${tableId}";
</script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script type="text/javascript" src="${ctx}/static/import/import.js"></script>

