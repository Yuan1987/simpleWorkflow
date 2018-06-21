<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/crop/cropper.min.css" type="text/css">
<script>
	var orgIds="${orgId}".split(",");
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="userModelTitle">新增</h3>
		</div>

		<form id="abilityForm" class="form-horizontal form" method="POST">
			<div class="form-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">*能力类别</label>
                        <div class="col-sm-8">
                                <select class="form-control"  name="typeid"  required="required">
                                <option></option>
                                	<c:forEach items="${dics_abilitykind}" var="dic">
                                	<option value="${dic.code}">${dic.detail}</option>
                                	</c:forEach>
                                </select>
                        </div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*能力名称</label>
					<div class="col-sm-8">
						<input type="text" id="name" name="name" class="form-control" required="required">
					</div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*所属序列</label>
                    <div class="col-sm-8">
                        <select class="form-control sp"   id="serial" name="serial" required="required">
                            <option></option>
                            <c:forEach items="${xlList}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*所属级别</label>
                    <div class="col-sm-8">
                        <select class="form-control sp"  name="level"  dname="level" id="level" required="required">
                            <option></option>
                            <c:forEach items="${jbList}" var="dic">
                                <option value="${dic.code}">${dic.detail}</option>
                            </c:forEach>
                        </select>
                    </div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*能力分值</label>
                        <div class="col-sm-8" >
                           <input type="text" class="form-control" name="score" required="required">
                        </div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">*计算公式</label>
                        <div class="col-sm-8">
                           <select class="form-control"  name="formula"   id="formula" required="required">
                              <option></option>
                                  <c:forEach items="${dics_formula}" var="dic">
                                     <option value="${dic.code}">${dic.detail}</option>
                                  </c:forEach>
                          </select>
                  	</div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*权重</label>
                     <div class="col-sm-8">
                          <input type="text" class="form-control" name="passmark" required="required">
                     </div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">*证据的输出件</label>
                     <div class="col-sm-8">
                          <textarea  class="form-control" name="evidence"  rows="3"  maxlength="500"  required="required"></textarea>
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
<script type="text/javascript" src="${ctx}/static/model/sys_ability_add.js"></script>

