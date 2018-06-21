<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"
	type="text/css" />

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>详细信息</h3>
		</div>
		<div class="portlet-body form">
			<form action="#" class="form-horizontal">
				<div class="form-body">
					<div class="mt-repeater">
						<div data-repeater-list="group-a">
							<div class="form-group">
								<label class="col-md-2 control-label">邮箱地址</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-envelope"></i>
										</span> <input type="email" class="form-control" placeholder="邮箱地址">
									</div>
								</div>
							</div>
							<div data-repeater-item>
								<div class="form-group">
									<label class="col-md-2 control-label">邮箱地址</label>
									<div class="col-md-8">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-envelope"></i></span> 
											<input type="email" class="form-control" placeholder="邮箱地址">
											<span class="input-group-addon" data-repeater-delete> <i class="fa fa-close"></i></span> 
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2 col-sm-offset-7">
								<a href="javascript:;" data-repeater-create class="btn btn-success mt-repeater-add">
									<i class="fa fa-plus">增加邮箱地址</i> 
								</a>
							</div>
						</div>
					</div>
					<div class="mt-repeater">
						<div data-repeater-list="group-a">
							<div class="form-group">
								<label class="col-md-2 control-label">电话号码</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-phone"></i>
										</span> <input type="text" class="form-control" placeholder="电话号码">
									</div>
								</div>
							</div>
							<div data-repeater-item>
								<div class="form-group">
									<label class="col-md-2 control-label">电话号码</label>
									<div class="col-md-8">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-phone"></i></span> 
											<input type="text" class="form-control" placeholder="电话号码">
											<span class="input-group-addon" data-repeater-delete> <i class="fa fa-close"></i></span> 
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2 col-sm-offset-7">
								<a href="javascript:;" data-repeater-create class="btn btn-success mt-repeater-add">
									<i class="fa fa-plus">增加电话号码</i> 
								</a>
							</div>
						</div>
					</div>
				</div>
		       <div class="modal-footer">
		               <button type="submit" class="btn green">确认</button>
		               <button type="button" class="btn default">取消</button>
		       </div>
			</form>
		</div>
		<!-- <div class="modal-footer">
			<button type="submit" class="btn btn-primary">确认</button>
			<a href="javascript:modalHide();" class="btn btn-default"
				data-dismiss="modal">取消</a>
		</div> -->
	</div>
</div>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-repeater/jquery.repeater.js"></script>
<script type="text/javascript" src="${ctx}/static/user/userProfile.js"></script>
