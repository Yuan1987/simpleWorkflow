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
			<li><span class="active">能力管理</span></li>
		</ul>
		<!-- END PAGE BREADCRUMB -->
		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="row">
					<div class="col-md-12">
						<div class="m-top-up-15">
							<div id="toolbar">
										<div class="form-inline">
										<button id="abilityAdd" class="btn btn-sm">
											<i class="fa fa-plus"></i> 添加
										</button>
										<button id="btn-delete" class="btn btn-sm btn-danger">
											<i class="glyphicon glyphicon-remove"></i> 删除
										</button>
											<span>能力类别</span>
									  		 <select class="form-control sp"   id="abilitykind" name="abilitykind" >
                           				 <option value="">全部</option>
                           				 <c:forEach items="${dics_abilitykind}" var="dic">
												<option value="${dic.code}">${dic.detail}</option>
												</c:forEach>
                        					</select>
							</div>
						</div>
						<table id="abilityTable" class="table"
								data-side-pagination="server" data-pagination="true"
								data-row-style="rowStyle" data-side-pagination="server"
				  				data-page-list="[10,15, 20, 50, 100, 200]"
								data-search="true" data-show-refresh="true" data-show-toggle="false"
								data-show-columns="false"
								data-show-export="true">
								<thead>
									<tr>
										<th data-checkbox="true"></th>
										<th data-field="typeV" data-halign="center"
											data-align="center">能力类别</th>
										<th data-field="name" data-halign="center"
											data-align="center">能力名称</th>
										<th data-field="cnSerial" data-halign="center"
											data-align="center">序列</th>
										<th data-field="cnTheLevel" data-halign="center"
											data-align="center">级别</th>
										<th data-field="evidence" data-halign="center"
											data-align="center">可以作为证据的输出件</th>
										<th data-formatter="ability.operation" data-align="center">操作</th>
									</tr>
								</thead>
							</table>
					</div>
		</div>
	</div>
</div>
</div>

		
<div class="modal fade" id="abilityModal" tabindex="-1"></div>

<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>

<script src="${ctx}/static/model/sys_ability.js"></script>
<script>
	var rootPath = '${ctx}';
</script>
