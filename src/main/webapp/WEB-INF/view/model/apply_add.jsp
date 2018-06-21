<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">新建</h3>
		</div>
		<form id="applyForm" class="form-horizontal" method="get" action="#">
			<div class="portlet light bordered">
				<h4 class="form-section">基本信息</h4>
				<div class="form-group">
					<label class="col-sm-2 control-label">*姓名</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" 
							required="required" name="username" value="${user.displayname}" readonly="readonly"> 
							<input type="hidden" name="userid" value="${user.id}">
					</div>
					<!-- <label class="col-sm-2 control-label">所在部门</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" 
							required="required">
					</div> -->
					<label class="col-sm-2 control-label">*申报序列</label>
					<div class="col-sm-4">
						<select name="seq" class="form-control" id="seq" required="required">
							<option></option>
							<c:forEach items="${xlList}" var="dic">
								<option value="${dic.code}">${dic.detail}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*申报级别</label>
					<div class="col-sm-4">
						<select name="grade" class="form-control" id="grade" required="required">
							<option></option>
							<c:forEach items="${jbList}" var="dic">
								<option value="${dic.code}">${dic.detail}</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-sm-2 control-label">*申请类型</label>
					<div class="col-sm-4">
						<select name="type" class="form-control" id="authType" disabled="disabled" required="required">
							<option></option>
							<c:forEach items="${sqlxList}" var="dic">
								<option value="${dic.code}">${dic.detail}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
					<h4 class="form-section">现职状况</h4>
					<div class="form-group">
						<label class="col-sm-2 control-label">岗位职种</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" value="${upi.jobstype}" readonly>
							<input type="hidden" id="jobstypeid" name="jobstype" value="${upi.jobstypeid}">
						</div>
						<label class="col-sm-2 control-label">岗位层级</label>
						<div class="col-sm-4">
							<input type="hidden" class="form-control"  id="jobsgrade"
								name="jobsgrade" required="required" value="${upi.jobsgrade}">
							<select class="form-control" disabled="disabled">
								<option></option>
								<c:forEach items="${jbList}" var="dic">
									<option value="${dic.code}" <c:if test="${dic.code ==upi.jobsgrade}">selected</c:if> >${dic.detail}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				<div id="fpg">
					<div class="form-group">
						<label class="col-sm-2 control-label">已具备资格序列</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="havequalificationseq"
								 value="${upi.havequalificationseq}" readonly>
							<input type="hidden" id="havequalificationseqid" name="havequalificationseq" value="${upi.havequalificationseqid}">
						</div>
						<label class="col-sm-2 control-label">已具备资格级别</label>
						<div class="col-sm-4">
							<input type="hidden" class="form-control" id="havequalificationgrade"
								name="havequalificationgrade" value="${upi.havequalificationgrade}">
							<select class="form-control" disabled="disabled">
								<option></option>
								<c:forEach items="${jbList}" var="dic">
									<option value="${dic.code}" <c:if test="${dic.code ==upi.havequalificationgrade}">selected</c:if> >${dic.detail}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">资格取得时间</label>
						<div class="col-sm-4">
							<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="havequalificationgradeTime"  value="${upi.havequalificationgradetime}" readonly>
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-sm-2 control-label">已补充条件序列</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" 
								name="workconditionsseq"  value="${upi.workconditionsseq}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">已补充条件层级</label>
						<div class="col-sm-4">
							<input type="hidden" class="form-control" 
								name="workconditionsgrade" value="${upi.workconditionsgrade}" readonly>
							<select class="form-control" disabled="disabled">
								<option></option>
								<c:forEach items="${jbList}" var="dic">
									<option value="${dic.code}" <c:if test="${dic.code ==upi.workconditionsgrade}">selected</c:if> >${dic.detail}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-2 control-label">补充年度</label>
						<div class="col-sm-4">
							<div class="input-group date date-picker year"  data-date-format="yyyy">
								<input type="text" class="form-control" name="workconditionsgradeAnnual"  value="${upi.workconditionsgradeannual}" readonly>
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<h4 class="form-section">学历</h4>
					<div class="form-group">
						<label class="col-sm-2 control-label">毕业院校</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="graduateschool" value="${upi.graduateschool}">
						</div>
						<label class="col-sm-2 control-label">所学专业</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="majors" value="${upi.majors}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">学历</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" 
								name="educational" value="${upi.educational}">
						</div>
						<label class="col-sm-2 control-label">学位</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="degree"  value="${upi.degree}">
						</div>
					</div>
					<h4 class="form-section">经历</h4>
					<div class="form-group">
						<label class="col-sm-2 control-label">经历简诉</label>
						<div class="col-sm-10">
							<textarea rows="5" placeholder="经历情况简述（500字以内，所填内容不涉密）"
								name="experience" class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">社会标准满足情况</label>
						<div class="col-sm-10">
							<textarea rows="5" placeholder="社会标准满足情况（如有，请填写名称及获得时间）"
								name="standardsmatching" class="form-control"></textarea>
						</div>
					</div>
				</div>

				<div id="fh" style="display: none;">
					<div class="form-group">
						<label class="col-sm-2 control-label">资格取得时间</label>
						<div class="col-sm-4">
							<div class="input-group date date-picker"  data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="havequalificationgradeTime2"  readonly>
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">取得资格时间后年度考核结果是否有优秀或排序靠前</label>
						<div class="col-sm-2">
							 <div class="input-group">
	                              <div class="icheck-inline">
	                                  <label>
	                                      <input type="radio" name="nuclearresultsfront" value="1" class="icheck">是</label>
	                                  <label>
	                                      <input type="radio" name="nuclearresultsfront" value="0" checked class="icheck">否</label>
	                              </div>
                          	</div>
						</div>
						<label class="col-sm-2 control-label">年度</label>
						<div class="col-sm-4">
							<div class="input-group date date-picker year"  data-date-format="yyyy">
								<input type="text" class="form-control" name="nuclearresultsfrontAnnual"  readonly>
								<span class="input-group-addon"> <i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">改进计划是否闭环 </label>
						<div class="col-sm-4">
							 <div class="input-group">
	                              <div class="icheck-inline">
	                                  <label>
	                                      <input type="radio" name="improvementplanclosedloop" value="1"  class="icheck">是</label>
	                                  <label>
	                                      <input type="radio" name="improvementplanclosedloop" value="0" checked class="icheck">否</label>
	                              </div>
                          	</div>
						</div>
					</div>
				</div>
				
				<div id="pg" style="display: none;">
					<h4 class="form-section">破格原因</h4>
					<div class="form-group">
						<label class="col-sm-2 control-label">不满足的任职资格申请条件</label>
						<div class="col-sm-10">
							<textarea rows="3" name="unqualifiedqualifications" placeholder="不满足的任职资格申请条件"
								class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">破格原因</label>
						<div class="col-sm-10">
							<textarea rows="3" name="abnormalityreasons"
								placeholder="写明所级以上个人荣誉名称，或在集团/事业部价值导向下的核心业务领域做出的突出贡献"
								class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">申报任职资格级别典型能力满足情况</label>
						<div class="col-sm-10">
							<textarea rows="4" placeholder="申请人简要举证自己的哪项工作能证明具备该项能力"
								name="qualificationmatching" class="form-control"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<a href="javascript:modalHide();" class="btn btn-default"
						data-dismiss="modal">取消</a>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/static/model/apply_add.js?1"></script>

