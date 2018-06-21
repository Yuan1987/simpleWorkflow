<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/static/common/flow.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/common/tableFile.css" type="text/css">	
<div class="modal-dialog modal-full">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<c:if test="${complete==false}">
				<c:if test="${taskKey=='bbmld' || taskKey=='syb'}">
					<button type="button" class="btn" id="tg"
						onclick="eval.doSave('${apply.evaluationFormId}','true','${taskId}')"
						disabled="disabled">
						通过
					</button>
					<button type="button" class="btn"
						onclick="eval.doSave('${apply.evaluationFormId}','false','${taskId}')">不通过</button>
					<c:if test="${taskKey=='bbmld'}">
						<button type="button" class="btn"
							onclick="eval.jq('${apply.evaluationFormId}','${taskId}')">加签</button>
					</c:if>
					<button type="button" class="btn"  name="back"
						onclick="eval.back('${apply.evaluationFormId}','${taskId}')">退回</button>
				</c:if>
				<c:if test="${taskKey=='hz'}">
					<button type="button" class="btn" name="complete"
						onclick="eval.complete('${apply.evaluationFormId}',true,'${taskId}')">
						完成
					</button>
				</c:if>
			</c:if>
		</div>
		<div class="col-sm-12">
			<div class="portlet light bordered">
				<div class="portlet-title tabbable-line">
					<ul class="nav nav-tabs">
						<li class=""><a href="#portlet_tab2_1" data-toggle="tab"
							aria-expanded="false">申请表</a></li>
						<li class="active"><a href="#portlet_tab2_2"
							data-toggle="tab" aria-expanded="false">测评表</a></li>
						<li><a href="#portlet_tab2_3" data-toggle="tab"
							aria-expanded="false">审核过程</a></li>
					</ul>
				</div>
				<div class="portlet-body">
					<div class="tab-content">
						<div class="tab-pane" id="portlet_tab2_1">
							<form class="form-horizontal" method="POST">
								<h4 class="form-section">
									申请表信息 
								</h4>
								<div class="form-group">
									<label class="col-sm-2 control-label">*姓名</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" readonly
											name="username" value="${apply.username}"
											readonly="readonly"> <input type="hidden"
											name="userid" value="${apply.userid}">
									</div>
									<!-- <label class="col-sm-2 control-label">所在部门</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" 
													readonly>
											</div> -->
									<label class="col-sm-2 control-label">*申报序列</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" value="${apply.seqV}"
											readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">*申报级别</label>
									<div class="col-sm-4">
										<input type="text" class="form-control"
											value="${apply.gradeV}" readonly="readonly">
									</div>
									<label class="col-sm-2 control-label">*申请类型</label>
									<div class="col-sm-4">
										<input type="hidden" value="${apply.type}" id="authType">
										<input type="text" class="form-control"
											value="${apply.typeV}" readonly="readonly">
									</div>
								</div>
								<h4 class="form-section">现职状况</h4>
								<div class="form-group">
									<label class="col-sm-2 control-label">岗位职种</label>
									<div class="col-sm-4">
										<select class="form-control" disabled="disabled">
											<option></option>
											<c:forEach items="${xlList}" var="dic">
												<option value="${dic.code}" <c:if test="${dic.code==apply.jobstype}">selected</c:if>>${dic.detail}</option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-2 control-label">岗位层级</label>
									<div class="col-sm-4">
										<select class="form-control" disabled="disabled">
											<option></option>
											<c:forEach items="${jbList}" var="dic">
												<option value="${dic.code}" <c:if test="${dic.code ==apply.jobsgrade}">selected</c:if> >${dic.detail}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div id="fpg">
									<div class="form-group">
										<label class="col-sm-2 control-label">已具备资格序列</label>
										<div class="col-sm-4">
											<select class="form-control" disabled="disabled">
												<option></option>
												<c:forEach items="${xlList}" var="dic">
													<option value="${dic.code}" <c:if test="${dic.code ==apply.havequalificationseq}">selected</c:if> >${dic.detail}</option>
												</c:forEach>
											</select>
										</div>
										<label class="col-sm-2 control-label">已具备资格级别</label>
										<div class="col-sm-4">
											<select class="form-control" disabled="disabled">
												<option></option>
												<c:forEach items="${jbList}" var="dic">
													<option value="${dic.code}" <c:if test="${dic.code ==apply.havequalificationgrade}">selected</c:if> >${dic.detail}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">资格取得时间</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="havequalificationgradeTime" readonly
												value="${apply.havequalificationgradeTime}">
										</div>
										<label class="col-sm-2 control-label">已补充条件序列</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="workconditionsseq" readonly
												value="${apply.workconditionsseq}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">已补充条件层级</label>
										<div class="col-sm-4">
											<select class="form-control" disabled="disabled">
												<option></option>
												<c:forEach items="${jbList}" var="dic">
													<option value="${dic.code}" <c:if test="${dic.code ==apply.workconditionsgrade}">selected</c:if> >${dic.detail}</option>
												</c:forEach>
											</select>
										</div>
										<label class="col-sm-2 control-label">补充年度</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="workconditionsgradeAnnual" readonly
												value="${apply.workconditionsgradeAnnual}">
										</div>
									</div>
									<h4 class="form-section">学历</h4>
									<div class="form-group">
										<label class="col-sm-2 control-label">毕业院校</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="graduateschool" readonly
												value="${apply.graduateschool}">
										</div>
										<label class="col-sm-2 control-label">所学专业</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="majors"
												readonly value="${apply.majors}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">学历</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" readonly
												name="educational" value="${apply.educational}">
										</div>
										<label class="col-sm-2 control-label">学位</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="degree"
												readonly value="${apply.degree}">
										</div>
									</div>
									<h4 class="form-section">经历</h4>
									<div class="form-group">
										<label class="col-sm-2 control-label">经历简诉</label>
										<div class="col-sm-10">
											<textarea rows="5" name="experience" class="form-control"
												readonly>${apply.experience}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">社会标准满足情况</label>
										<div class="col-sm-10">
											<textarea rows="5" name="standardsmatching"
												class="form-control" readonly>${apply.experience}</textarea>
										</div>
									</div>
								</div>

								<div id="fh" style="display: none;">
									<div class="form-group">
										<label class="col-sm-2 control-label">资格取得时间</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="havequalificationgradeTime2"
												value="${apply.havequalificationgradeTime}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">取得资格时间后年度考核结果是否有优秀或排序靠前</label>
										<div class="col-sm-2">
											<div class="input-group">
												<div class="icheck-inline">
													<label> <input type="radio"
														name="nuclearresultsfront"
														<c:if test="${apply.nuclearresultsfront=='1'}">checked</c:if>
														class="icheck">是
													</label> <label> <input type="radio"
														name="nuclearresultsfront"
														<c:if test="${apply.nuclearresultsfront=='0'}">checked</c:if>
														class="icheck">否
													</label>
												</div>
											</div>
										</div>
										<label class="col-sm-2 control-label">年度</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												name="nuclearresultsfrontAnnual"
												value="${apply.nuclearresultsfrontAnnual}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">改进计划是否闭环 </label>
										<div class="col-sm-4">
											<div class="input-group">
												<div class="icheck-inline">
													<label> <input type="radio"
														name="improvementplanclosedloop"
														<c:if test="${apply.improvementplanclosedloop}">checked</c:if>
														class="icheck">是
													</label> <label> <input type="radio"
														name="improvementplanclosedloop"
														<c:if test="${!apply.improvementplanclosedloop}">checked</c:if>
														class="icheck">否
													</label>
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
											<textarea rows="3" name="unqualifiedqualifications" readonly
												class="form-control">${apply.unqualifiedqualifications}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">破格原因</label>
										<div class="col-sm-10">
											<textarea rows="3" name="abnormalityreasons" readonly
												class="form-control">${apply.abnormalityreasons}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">申报任职资格级别典型能力满足情况</label>
										<div class="col-sm-10">
											<textarea rows="4" readonly name="qualificationmatching"
												class="form-control">${apply.qualificationmatching}</textarea>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane active" id="portlet_tab2_2">
							<div class="form-body">
								<h4 class="form-section">
									测评表 
								</h4>
								<script>
									var users=[];//待选评分人
								</script>
								<div id="toolbar2">
									<form class="form-inline" method="post">
										<c:if test="${complete==false && taskKey=='xlxz'}">
											<select name="users" id="users" class="form-control" multiple="multiple">
												<c:forEach items="${users}" var="user">
													<option value="${user.uid}">${user.displayname}</option>
													<script>
														var u={};
									            		u["id"]="${user.uid}";
									            		u["text"]="${user.displayname}";
									            		users.push(u);
													</script>
												</c:forEach>
											</select>
											<button id="batch" class="btn" type="button">
												<i class="fa fa-plus"></i> 批量指定
											</button>
										</c:if>
										<a id="download" class="btn" 
											href="${ctx}/model/efcheck/download?peId=${apply.evaluationFormId}&name=${apply.username}">
											<i class="fa fa-download"></i> 打包下载
										</a>
									</form>
								</div>
								<table id="evaluFormTable" class="table">
									<thead>
										<tr>
											<th data-field="typeV" data-valign="middle" data-width="200px">任职资格模型评测项目</th>
											<th data-field="name" data-align="center" data-valign="middle">能力项</th>	
											<th data-field="evidence" data-align="center" data-valign="middle">可以作为证据的输出件</th>
											<th data-field="files" data-formatter="eval.file" data-align="center" data-width="400px" class="file">附件信息</th>
											<!-- <th data-field="files" data-formatter="eval.description" data-align="center">附件描述</th> -->
											<th data-field="score" data-align="center" data-valign="middle">分值</th>
											<c:if test="${taskKey=='xlxz'}">
												<th data-formatter="eval.operation" data-field="paes" data-align="center" data-valign="middle">测评人</th>
											</c:if>
											<%-- <c:if test="${taskKey=='cpr'||taskKey=='hz'}">
												<th data-formatter="eval.scoring" data-field="paes" data-align="center">评分</th>
											</c:if> --%>
											<c:if test="${needMark}">
												<c:if test="${taskKey=='hz'||taskKey=='gb'}">
													<th  data-formatter="eval.count" data-field="paes" data-footer-formatter="eval.total2" data-align="center" data-valign="middle">得分</th>
												</c:if>
											</c:if>
											<c:if test="${taskKey=='cpr'}">
												<th data-field="paes" data-align="center" data-formatter="eval.note" data-valign="middle">测评</th>
											</c:if>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="tab-pane" id="portlet_tab2_3">
							<form class="form-horizontal form" method="POST"
								id="userResumeForm">
								<div class="row">
									<div class="col-sm-12">
										<div class="mt-element-step" >
											<div class="row step-line flow" id="flow">
							               </div>
							           </div>
										<h4 class="form-section">审核过程</h4>
										<div class="bootstrap-table">
											<div class="fixed-table-container">
												<div class="fixed-table-body">
													<table id="userTable" class="table table-hover"
														data-side-pagination="server" data-pagination="true"
														data-row-style="rowStyle">
														<thead>
															<tr>
																<th style="text-align:center;" data-valign="middle"><div
																		class="th-inner ">环节名称</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">状态</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">时间</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">意见</div></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="detail" items="${detailList}">
																<tr data-index="0">
																	<td style="text-align: center; ">${detail.username}(${detail.nodename})</td>
																	
																	<c:choose>
																		<c:when test="${detail.nodecode=='hz' || detail.nodecode=='cpr'||detail.nodecode=='gb'}">
																			<td style="text-align: center; ">
																				<c:if test="${detail.result}">完成</c:if> 
																				<c:if test="${detail.result==false}">退回</c:if>
																			</td>
																		</c:when>
																		<c:otherwise>
																			<td style="text-align: center; ">
																				<c:if test="${detail.result}">通过</c:if> 
																				<c:if test="${detail.result==false}">不通过</c:if>
																			</td>
																		</c:otherwise>
																	</c:choose>
																	<td style="text-align: center; ">${detail.createdtime}</td>
																	<td style="text-align:left; ">${detail.opinion}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<h4 class="form-section">问题记录</h4>
										<div class="bootstrap-table">
											<div class="fixed-table-container">
												<div class="fixed-table-body">
													<table id="userTable" class="table table-hover"
														data-side-pagination="server" data-pagination="true"
														data-row-style="rowStyle">
														<thead>
															<tr>
																<th style="text-align:center;" data-valign="middle"><div
																		class="th-inner ">评测人</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">问题1</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">问题2</div></th>
																<th style="text-align:center;" data-align="center"><div
																		class="th-inner ">问题3</div></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="detail" items="${qList}">
																<tr data-index="0">
																	<td style="text-align: center; ">${detail.username}</td>
																	<td style="text-align:left; ">${detail.question1}</td>
																	<td style="text-align:left; ">${detail.question2}</td>
																	<td style="text-align:left; ">${detail.question3}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<%-- <div class="mt-comments">
											<c:forEach var="detail" items="${detailList}">
												<div class="mt-comment">
													<div class="mt-comment-img">
														<img src="${ctx}/static/assets/layouts/layout4/img/avatar.png" />
													</div>
													<div class="mt-comment-body">
														<div class="mt-comment-info">
															<span class="mt-comment-author">${detail.username}(${detail.nodename})</span>
															<span class="mt-comment-date">${detail.createdtime}</span>
														</div>
														<div class="mt-comment-text">
															<c:if test="${detail.result}">通过</c:if>
															<c:if test="${!detail.result}">不通过</c:if>
															${detail.opinion}
														</div>
													</div>
												</div>
											</c:forEach>
										</div> --%>
									</div>
								</div>
							</form>
						</div>
						<!-- tab3 end -->
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<c:if test="${complete==false && (taskKey=='cpr'||fn:endsWith(taskKey, '-add'))}">
				<button type="button" class="btn btn-primary" id="doSave"
						onclick="eval.doSave('${apply.evaluationFormId}','true','${taskId}')">确定</button>
			</c:if>
			<c:if test="${complete==false && taskKey=='xlxz'}">
				<button type="button" class="btn btn-primary"
						onclick="eval.chose('${apply.evaluationFormId}','true','${taskId}')" id="chose">确定</button>
			</c:if>
			<a href="#" class="btn btn-default" data-dismiss="modal">返回</a>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${ctx}/static/assets/global/plugins/bootstrap-editable/bootstrap-editable/js/bootstrap-editable.js?12222"></script>
<script>
$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.emptytext = '空';
$.fn.modal.Constructor.prototype.enforceFocus = function () {};
var efId="${apply.evaluationFormId}";
var currentUserId="${currentUserId}";
var taskKey="${taskKey}";

try{
	clearInterval(sh);
}catch(e){}
var i=5;
function ff(){
	$("#tg").text("通过（"+i+"）");
	i--;
	if(i<1){
		clearInterval(sh);
		$("#tg").text("通过").removeAttr("disabled");
	}
}
var sh=setInterval(ff,1000);

//测评
eval.note=function(value,row,index){
	var str="";
	if(value==undefined||value.length==0){//无测评人
		value="";
		return value;
	}else{
		var abled=false;//默认 本人无权限（不是指定的测评人）
		var note="";
		var paeId="";
		$.each(value,function(i,n){
			if(n.assessorid==currentUserId){
				abled=true;
				note=n.note==undefined?"":n.note;
				paeId=n.id;
				return false;
			}
		});
		if(abled){
		 	var text="测评";
		 	if(note!=""){
		 		text="修改";
		 	}
		 	if('${complete}'=='true'){
				text="查看";
			}
			str="<a onclick=\"eval.cp('"+paeId+"','"+row.id+"',"+index+")\" class=\"fileInput\">"+text+"</a>";
		}
	}
	return str;
};

/**
 * 合并单元格
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称
 * @param colspan   合并列
 * @param target    目标表格对象
 */
function mergeCells(data,fieldName,colspan,target){
    //声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    for(var i = 0 ; i < data.length ; i++){
        for(var prop in data[i]){
            if(prop == fieldName){
                var key = data[i][prop]
                if(sortMap.hasOwnProperty(key)){
                    sortMap[key] = sortMap[key] * 1 + 1;
                } else {
                    sortMap[key] = 1;
                }
                break;
            } 
        }
    }
    var index = 0;
    for(var prop in sortMap){
        var count = sortMap[prop] * 1;
        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
        index += count;
    }
}

//描述  已作废
eval.description=function (value, row, index) {
	var str="";
	$.each(value,function(i,n){
		if(n.description){
			str=str+n.description+",";
		}
	});
	return str;
};

//附件页面
eval.fileInput=function(paId){
	$modal=$('#paFileModal');
    $modal.load(rootPath+"/model/evaluation/fileInput.html",{paId:paId,efstatus:'审核中'}, function(){
		$modal.modal();
    });
};

//附件信息
eval.file=function (value, row, index) {
	if(value==undefined||value.length==0){
		value="";
	}else{
		/* var str="";
		$.each(value,function(i,n){
			str=str+n.friendlyfilename+",";
		});
		value=str; */
		var str="";
		$.each(value,function(i,n){
			str+="<div class=\"tdDiv\"><span>"+n.friendlyfilename+"</span><span>"+n.description+"</span></div>";
		});
		value=str;
	}
    return "<a onclick=\"eval.fileInput('"+row.id+"')\" title='查看' class=\"fileInput\">"+value+"</a>";
};

//序列小组 选择测评人
eval.operation=function(value, row){
	if(value==undefined||value.length==0){
		value="";
	}else{
		var str="";
		$.each(value,function(i,n){
			str=str+n.assessor+",";
		});
		if(str.length>0){
			str=str.substring(0,str.length-1);
		}
		value=str;
	}
	var str="<a href=\"#\" class=\"choseEditable\" name=\"assessorid\" data-type=\"select2\"  data-placeholder='选择测评人' data-pk=\""+row.id+"\" data-title=\"选择测评人\">" + value + "</a>";
	if('${complete}'=='true'){
		str=value;
	}
    return str;
};

//测评人 评分 editable 模式  2017.10.24 作废
eval.scoring=function(value, row){
	var str="";
	if(value==undefined||value.length==0){//无测评人
		value="";
		return value;
	}else{
		var abled=false;//默认 本人无权限（不是指定的测评人）
		var scoe="";
		var paeId="";
		$.each(value,function(i,n){
			if(n.assessorid==currentUserId){
				abled=true;
				scoe=n.scoring==undefined?"":n.scoring;
				paeId=n.id;
				return false;
			}
		});
		if(abled){
			str="<a href=\"#\" class=\"scoring\" name=\"scoring\" data-type=\"text\"  data-placeholder='' data-pk=\""+paeId+"\" data-title=\"得分\">"+scoe+"</a>";
		}
	}
	if('${complete}'=='true'||taskKey=='hz'){
		str="";
		$.each(value,function(i,n){
			var scoe=n.scoring==undefined?"-":n.scoring;
			str=str+n.assessor+":"+scoe+"</br>";
		});
	}
    return str;
};

//计算得分
var scorings=[];//每一个能力项 分数
eval.count=function(value, row){
	var str="";
	if(value==undefined||value.length==0){//无测评人
		return str;
	}else{
		var totalscoe=0;
		var paeId="";
		$.each(value,function(i,n){
			var scoe=n.scoring==undefined?0:n.scoring;
			totalscoe=totalscoe+scoe;
		});
		str=totalscoe/value.length;
		str=str.toFixed(1);
		var scoring={};
		scoring["id"]=row.id;
		scoring["scoring"]=str;
		scorings.push(scoring);
	}
    return str;
};

//保存 并走流程
eval.doSave= function(vid, pass, taskId){
	$("#doSave").attr("disabled","disabled");
	if(taskKey=='cpr'){
		var allData = $('#evaluFormTable').bootstrapTable('getData');
		
		console.info(allData);
		var abled=true;
		var aName="";
		
		$.each(allData,function(i,n){
			var paes=n.paes;
			aName=n.name;
			$.each(paes,function(i,n){
				var note=n.note;
				var scoring=n.scoring;
				var userId=n.assessorid;
				if(note==undefined&&userId==currentUserId){//本人没有测评
					abled=false;
					return false;
				}
			});
			if(!abled){
				return false;
			}
		});
		
		if(!abled){
			layer.alert("请填 测评【"+aName+"】", {
				title : '提示',
				icon : 2,
				skin : 'layer-ext-moon'
			});
			$("#doSave").removeAttr("disabled");
			return;
		}
	}
	$modal = $('#doCheck');
	$modal.load(rootPath + "/model/efcheck/toCheck.html",{
		id : vid,
		pass : pass,
		taskId : taskId
	}, function() {
		$modal.modal();
		$("#doSave").removeAttr("disabled");
	});
};

//退回
eval.back= function(vid, taskId){
	$modal = $('#doCheck');
	$modal.load(rootPath + "/model/efcheck/toBack.html",{
		id : vid,
		taskId : taskId,
		type:"cpb"
	}, function() {
		$modal.modal();
	});
};

//指定测评人
eval.chose= function(vid, pass, taskId) {

	var allData = $('#evaluFormTable').bootstrapTable('getData');//获取表格的所有内容行  
	$("#chose").attr("disabled","disabled");
	
	var abled=true;
	$(".choseEditable").each(function(i,n){
		if($(n).text()=='空'){
			abled=false;
			return false;
		}
	});
	
	if(!abled){
		layer.alert("请选择测评人", {
			title : '提示',
			icon : 2,
			skin : 'layer-ext-moon'
		});
		$("#chose").removeAttr("disabled");
		return;
	}
	$.ajax({
			dataType : "json",
			url : rootPath + "/model/efcheck/chose",
			type : "post",
			data : "jsonData="+JSON.stringify(allData)+"&taskId="+taskId+"&efId="+vid,
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#efCheckTable").bootstrapTable('refresh');
					$("#theModal").modal("hide");
				}
				layer.alert(data.message, {
					title : '提示',
					icon : 0,
					skin : 'layer-ext-moon'
				});
			},
			error : function() {
				layer.alert(data.message, {
					title : '提示',
					icon : 2,
					skin : 'layer-ext-moon'
				});
			},
			complete:function(){
				$("#chose").removeAttr("disabled");
			}
		});
};

//合计分数
eval.total=function(value){
	var total=0;
	for(var i in value){
		total+=value[i].scoring;
	}
	return "合计:"+total;
};
//合计分数 未保存最终得分的情况
eval.total2=function(value){
	var total=0;
	$.each(value,function(i,n){
		var totalscoe=0;
		var paes=value[i].paes;
		$.each(paes,function(i,n){
			var scoe=n.scoring==undefined?0:n.scoring;
			totalscoe+=scoe;
		});
		var avg=totalscoe/paes.length;
		avg=avg.toFixed(1);
		total+=(Number(avg));
	});
	return "合计:"+total.toFixed(1);
};

//新版的能力项测评页面
eval.cp=function(paeId,paId,index){
	$modal=$('#paFileModal');
    $modal.load(rootPath+"/model/efcheck/toAbilitycheck.html",{paeId:paeId,paId:paId,index:index,needMark:"${needMark}",complete:"${complete}"}, function(){
		$modal.modal();
    });
};

//获取子表的测评信息
function getChildTable(index, row, $detail) {
    var parentid = row.id;
    var cur_table = $detail.html('<table id="child_table"></table>').find('table');
    $(cur_table).bootstrapTable({
    	data:row.paes,
        columns: [
             {
                field: 'assessor',
                title: '测评人',
                align: 'center',
            }, {
                field: 'scoring',
                title: '分数',
                align: 'center',
            }, {
                field: 'note',
                title: '意见',
                align: 'center',
            }
        ]
    });
}

//加载table
$(function(){
	$("#users").select2({
		language : "zh-CN",
		placeholder : "请选择",
		width:"200px"
	});
	var showFooter=false;
	if((taskKey=='hz'||taskKey=='gb')&&"${needMark}"=="true"){
		showFooter=true;
	}
	var detailView=false;//是否显示能力项测评信息
	if(taskKey=='hz'||'${apply.evalStatus}'=='已完成'){
		detailView=true;
	}
	var curRow = {};
	$('#evaluFormTable').bootstrapTable({
		classes:"table",
		ajaxOptions:{async:false},
		method : 'POST',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : 'pageSize',
		showFooter:showFooter,
		detailView: detailView,//父子表
		//注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            getChildTable(index, row, $detail);
        },
		queryParams : function(params) {
			return {
				evaluationFormId:efId
			};
		},
		url : rootPath + "/model/evaluation/table",
		toolbar:"#toolbar2",
		cache : false,
		sidePagination : 'server',
		onClickRow: function (row, $element) {
            curRow = row;
        },
		onLoadSuccess : function(data) {                                
            var data = $('#evaluFormTable').bootstrapTable('getData',true);
            //合并单元格
            mergeCells(data, "typeV", 1, $('#evaluFormTable'));
            
            //打分
            /* $(".scoring").editable({
                url: function (params) {
                    var sName = $(this).attr("name");
                    curRow[sName] = params.value;
                    curRow["paeId"]=params.pk;
                    $.ajax({
                        type: 'POST',
                        url: rootPath+"/model/efcheck/markScore",
                        data: curRow,
                        dataType: 'JSON',
                        success: function (data) {
                        },
                        error: function () {
                        	layer.alert('操作失败！', {
            					title : '提示',
            					icon : 2
            				});
                        }
                    });
                },
                validate: function(value) {
                	var n=Number($.trim(value));
				    if(isNaN(n)||n<0||n>curRow.score){
				        return "值应在0至"+curRow.score+"之间";
				    }
				}
            }); */
            //评分人
            $(".choseEditable").editable({
            	source:users,
            	select2: {
	                multiple: true,
	                tokenSeparators : [","],  
	            },url: function (params) {
                    var sName = $(this).attr("name");
                    curRow[sName] = params.value;
                }
            });
		}
	});
	
	//批量指定
	$("#batch").click(function(){
		var data = $('#evaluFormTable').bootstrapTable('getData');
		var checks = $("#users").select2("data");
		
		if(checks.length==0){
			return;
		}
		
		var id="";
		var text="";
		$.each(checks,function(i,n){
			id+=n.id+",";
			text+=n.text+",";
		});
		
		for( i=0;i<data.length;i++){  
		   $('#evaluFormTable').bootstrapTable('updateCell', {  
			   index: i, 
			   field:'assessorid',
			   value:id
		    });  
		}
		//评分人
        $(".choseEditable").editable({
        	source:users,
         	select2: {
              multiple: true,
              tokenSeparators : [","],
            },url: function (params) {
              var sName = $(this).attr("name");
              curRow[sName] = params.value;
            }
         });
         $(".choseEditable").text(text);
	});
	
	//加载流程图
	$.ajax({
		dataType : "json",
		url : rootPath + "/model/check/lookprocessdefine?processId=${apply.evalProcessId}",
		type : "post",
		success : function(data) {
			var flowJson=JSON.parse(data.flowJson);
			var currentNode=data.currentNode;
			var status="done";
			$("#flow").append("<div class=\"mt-step-col first done\"><div class=\"mt-step-number bg-white\">开始</div><div class=\"mt-step-title uppercase font-grey-cascade\"></div></div>");
			$.each(flowJson,function(i,n){
			
				if(n.key==currentNode){
					status="active";
				}
				var str="<div class=\"mt-step-col "+status+"\">"
                       +"<div class=\"mt-step-word bg-white\">"+n.name+"</div>"
                       +"<div class=\"mt-step-title uppercase font-grey-cascade\"></div>"
                   	+"</div>";
                if(n.key==currentNode){
					status="";
				}
				$("#flow").append(str);
			});
			$("#flow").append("<div class=\"mt-step-col last\" id=\"last\"><div class=\"mt-step-number bg-white\">结束</div><div class=\"mt-step-title uppercase font-grey-cascade\"></div></div>");
			if(currentNode==undefined){
				$("#last").addClass("done");
			}
		},
		error : function() {
		}
	});
});

//加签
eval.jq= function(vid, taskId){
	$modal = $('#doCheck');
	$modal.load(rootPath + "/model/efcheck/toAddPreNode.html",{
		id : vid,
		taskId : taskId
	}, function() {
		$modal.modal();
	});
};

//完成 流程  无审核意见页面
eval.complete= function(vid, pass, taskId){
	var str="完成";
	index = layer.confirm('确定【'+str+'】吗？', {
			btn : [ '确定', '取消' ] //按钮
		}, function() {
			$("button[name='complete']").attr("disabled","disabled");
		/* 	$.ajax({
				dataType : "json",
				async:false,
				url : rootPath + "/model/efcheck/count",
				type : "post",
				data : "jsonData="+JSON.stringify(scorings),
				success : function(data) {
				},
				error : function() {
					layer.alert(data.message, {
						title : '提示',
						icon : 2,
						skin : 'layer-ext-moon'
					});
				},
				complete:function(){
					$("#doSave").removeAttr("disabled");
				}
			}); */
		
			$.ajax({
				dataType : "json",
				url : rootPath + "/model/efcheck/check2",
				type : "post",
				data : "result="+pass+"&taskid="+taskId+"&applyid="+vid,
				success : function(data) {
					if (data.message == "操作成功！") {
						$("#efCheckTable").bootstrapTable('refresh');
						$("#theModal").modal("hide");
					}
					layer.alert(data.message, {
						title : '提示',
						icon : 0,
						skin : 'layer-ext-moon'
					});
				},
				error : function() {
					layer.alert(data.message, {
						title : '提示',
						icon : 2,
						skin : 'layer-ext-moon'
					});
				},
				complete:function(){
					$("button[name='complete']").removeAttr("disabled");
				}
			});
		}, function() {
			layer.close(index);
		});
};
</script>