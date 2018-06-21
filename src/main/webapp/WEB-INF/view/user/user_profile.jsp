<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/user/css/userProfile.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css"type="text/css" />

<style>
	.portlet {
   		margin-top: 0;
	    margin-bottom: 0px;
	    padding: 0;
	    border-radius: 4px;
	}
</style>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="portlet light bordered">
			<div class="portlet-title tabbable-line">
				<div class="caption">
					<i class="icon-share font-dark"></i> <span
						class="caption-subject font-dark bold uppercase">【<c:out
							value="${user.displayname}" />】详细信息
					</span>
				</div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="#portlet_tab2_1" data-toggle="tab"
						aria-expanded="true">基本信息</a></li>
					<li class=""><a href="#portlet_tab2_2" data-toggle="tab"
						aria-expanded="false">个人履历</a></li>
				</ul>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<div class="tab-pane active" id="portlet_tab2_1">
						<form class="form-horizontal" id="userProfileForm">

							<input type="hidden" name="userId" value="${user.id}">
							<input type="hidden" name="id" value="${userProfile.id}">
							<div class="form-body">
								<div class="form-group">
									<label class="col-sm-2 control-label">姓名*</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="name"
											value="${userProfile.name}" required="required">
									</div>
									<label class="col-sm-2 control-label">员工类别*</label>
									<div class="col-sm-4">
										<select name="type" class="form-control">
											<option value="0">在职</option>
											<option value="1">外聘</option>
											<option value="2">劳务</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">联系电话*</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="phoneno"
											required="required" value="${userProfile.phoneno}">
									</div>
									<label class="col-sm-2 control-label">身份证号*</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="idno"
											required="required" value="${userProfile.idno}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">性别</label>
									<div class="col-sm-4">
										<select name="sex" class="form-control select2">
											<option></option>
											<c:forEach items="${xbList}" var="dic">
												<option value="${dic.code}" <c:if test="${dic.code ==userProfile.sex}">selected</c:if>  >${dic.detail}</option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-2 control-label">出生日期</label>
									<div class="col-sm-4">
										<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
											<input type="text" class="form-control" name="birthday" value="${userProfile.birthday}" readonly>
											<span class="input-group-addon"> <i
												class="fa fa-calendar"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">民族</label>
									<div class="col-sm-4">
										<select name="nation" class="form-control">
											<option></option>
											<c:forEach items="${mzList}" var="dic">
												<option value="${dic.code}" <c:if test="${dic.code ==userProfile.nation}">selected</c:if>  >${dic.detail}</option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-2 control-label">籍贯</label>
									<div class="col-sm-4">
										<input type="text" name="nativeplace" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">学历</label>
									<div class="col-sm-4">
										<select name="education" class="form-control">
											<option value="0">男</option>
											<option value="1">女</option>
										</select>
									</div>
									<label class="col-sm-2 control-label">毕业院校</label>
									<div class="col-sm-4">
										<select name="school" class="form-control">
											<option value="0">男</option>
											<option value="1">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">专业</label>
									<div class="col-sm-4">
										<select name="special" class="form-control">
											<option value="0">男</option>
											<option value="1">女</option>
										</select>
									</div>
									<label class="col-sm-2 control-label">技能职称</label>
									<div class="col-sm-4">
										<select name="title" class="form-control">
											<option value="0">男</option>
											<option value="1">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">职务</label>
									<div class="col-sm-4">
										<input type="text" name="duty" class="form-control" value="${userProfile.duty}">
									</div>
									<label class="col-sm-2 control-label">政治面貌</label>
									<div class="col-sm-4">
										<select name="politicalstatus" class="form-control">
											<option value="0">男</option>
											<option value="1">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">婚姻状况</label>
									<div class="col-sm-4">
										<select name="maritalstatus" class="form-control select2">
											<option></option>
											<c:forEach items="${hyzkList}" var="dic">
												<option value="${dic.code}" <c:if test="${dic.code ==userProfile.maritalstatus}">selected</c:if>  >${dic.detail}</option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-2 control-label">入党时间</label>
									<div class="col-sm-4">
										<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
											<input type="text" class="form-control"name="joinpartytime" 
											value="${userProfile.joinpartytime}" readonly>
											<span class="input-group-addon"> <i
												class="fa fa-calendar"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">居住地址</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="address" value="${userProfile.address}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">从事工作</label>
									<div class="col-sm-10">
										<textarea rows="2" name="workcontent" class="form-control">${userProfile.workcontent}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">备注</label>
									<div class="col-sm-10">
										<textarea rows="2" name="note" class="form-control">${userProfile.note}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">照片</label>
									<div class="col-sm-8">
										<div class="avatar-view" title="点击上传图片">
										  <c:if test="${not empty userProfile.portrait}">
										  	<img src="${imgBaseUrl}${userProfile.portrait}"> 
										  </c:if>
										  <c:if test="${empty userProfile.portrait}">
										  	<img src="${ctx}/static/user/css/portrait.png"> 
										  </c:if>
												<input type="hidden" name="file" id="base64File">
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">确定</button>
									<a href="javascript:modalHide();" class="btn btn-default"
										data-dismiss="modal">返回</a>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="portlet_tab2_2">
						<form class="form-horizontal" method="POST" id="userResumeForm">
							
							<input type="hidden" name="userId" value="${user.id}">
							<input type="hidden" name="id" value="${userResume.id}">
						
							<div class="form-group">
								<label class="col-sm-2 control-label">培训教育</label>
								<div class="col-sm-10">
									<textarea rows="4" name="education" class="form-control"
										placeholder="参加工作后，参加的单位内部和外部培训，包括取证培训、业务培训、继续教育、学历培训等">${userResume.education}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">工作简历</label>
								<div class="col-sm-10">
									<textarea rows="4" name="workresume" class="form-control"
										placeholder="详细描述自参加工作后，工作过的岗位、参建过的项目、担任过的职务等">${userResume.workresume}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">专长</label>
								<div class="col-sm-10">
									<textarea rows="3" name="speciality" class="form-control"
										placeholder="熟悉或擅长某一项专业技术或业务技能">${userResume.speciality}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">获奖情况</label>
								<div class="col-sm-10">
									<textarea rows="3" name="honor" class="form-control"
										placeholder="包括行政、党群、科研等内外部表单奖励">${userResume.honor}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">未来5年职业发展规划或设想</label>
								<div class="col-sm-10">
									<textarea rows="4" name="programme" class="form-control"
										placeholder="指未来5年职业规划或职业目标或个人发展方向">${userResume.programme}</textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">确定</button>
								<a href="javascript:modalHide();" class="btn btn-default"
									data-dismiss="modal">取消</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/crop/cropper.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/crop/base_crop.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/static/user/userProfile.js?2"></script>


