<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header padding-sty">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3 id="modelTitle">附件信息</h3>
			<form id="fileForm" class="form-horizontal" method="get"
				enctype="multipart/form-data">
				
				<input name="paId" value="${paId}" hidden="hidden" id="paId" /> 
				<div class="portlet light bordered" id="portlet">
					<c:forEach var="af" items="${pa.files}">
						<div class="form-group" id="div${af.guid}">
							<label class="col-sm-1 control-label">附件</label>
							<div class="col-sm-5">
								<%-- <input type="file" name="file"  id="${af.guid}" class="input-ke-3"/> --%>
								<a href="${fileBasePath}${af.filephysicalpath}" title="下载">${af.friendlyfilename}</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${efstatus=='草稿'||efstatus=='被退回'}">
									<a href="javascript:filedelete('${paId}','${af.guid}');" title="删除">删除</a>
								</c:if>
							</div>
							<label class="col-sm-1 control-label">描述</label>
							<div class="col-sm-5">
								<input name="fileIds" type="hidden" value="${af.guid}">
								<textarea rows="5" name="description" class="form-control">${af.description}</textarea>
							</div>
						</div>
						
						<!-- <script>
							$(function(){
							
								var status="${efstatus}";
								var showDelete=false;
								
								if(status=='草稿'||status=='审核未通过'||status=='被退回'){
									showDelete=true;
								}
								
								var initialPreviewConfig=[];
								var initialPreview=[];
								initialPreview.push("${fileBasePath}${af.filephysicalpath}");
								var config={
									caption:"${af.friendlyfilename}",
									size:"${af.filesize}",
									url:"${ctx}/model/evaluation/fileDelete?paId=${paId}&fileId=${af.guid}",
									key:"${af.guid}",
									downloadUrl:"${fileBasePath}${af.filephysicalpath}"
								};
								initialPreviewConfig.push(config);
								var a=$("#"+"${af.guid}").fileinput({
									language : 'zh',
									hideThumbnailContent : true, // hide image, pdf, text or other content in the thumbnail preview
									theme : "explorer",
									//uploadUrl : "/file-upload-batch/2",
									showRemove : false,
									showBrowse:false,
									showCaption:false,
									overwriteInitial : false,
									preferIconicPreview: true,
									showUpload : false, //是否显示上传按钮  
									initialPreview:initialPreview,
									initialPreviewAsData : true, // defaults markup  
									initialPreviewConfig:initialPreviewConfig,
									fileActionSettings: {showDrag:false,showDelete:showDelete}
								});
							});
						</script> -->
					</c:forEach>
					<c:if test="${efstatus=='草稿'||efstatus=='审核未通过'||efstatus=='被退回'}">
						<div class="form-group">
							<label class="col-sm-1 control-label">附件</label>
							<div class="col-sm-5">
								<input type="file" name="file" onchange="addInput();"/>
							</div>
							<label class="col-sm-1 control-label">描述</label>
							<div class="col-sm-5">
								<textarea rows="5" name="newDescription" class="form-control"></textarea>
							</div>
						</div>
						<div id="appendDiv">
						</div>
						<div class="form-group">
							 <label class="col-sm-1 control-label"></label>
		                     <div class="col-sm-5">
		                         <input type="checkbox" id="istrue"  name="istrue" required="required"><span id="tmSpan">我已确认文件已脱密</span>
		                    </div>
						</div>
					</c:if>
					<div class="modal-footer">
						<c:if test="${efstatus=='草稿'||efstatus=='审核未通过'||efstatus=='被退回'}">
							<button type="submit" class="btn btn-primary">确定</button>
						</c:if>
						<a href="javascript:modalHide();" class="btn btn-default"
							data-dismiss="modal">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	var fileBasePath="${fileBasePath}";
	$(function() {
	
		$('.input-ke-3').on('filedeleted', function(event, id){
		   $("#"+"div"+id).remove();
	       $("#evaluFormTable").bootstrapTable('refresh'); 
		}); 
		
		$("#tmSpan").click(function(){
			
			var obj=$("#istrue");
			
			if(obj.is(':checked')){
				obj.prop({checked:false}).change();
			}else{
				obj.prop({checked:true}).change();
			}
		});
	});
	
	function addInput(){
		var str="<div class=\"form-group\">"
			+	"<label class=\"col-sm-1 control-label\">附件</label>"
			+	"<div class=\"col-sm-5\">"
			+		"<input type=\"file\" name=\"file\" onchange=\"addInput();\"/>"
			+	"</div>"
			+	"<label class=\"col-sm-1 control-label\">描述</label>"
			+	"<div class=\"col-sm-5\">"
			+		"<textarea rows=\"5\" name=\"newDescription\" class=\"form-control\"></textarea>"
			+	"</div>"
			+"</div>";
		$("#appendDiv").append(str);
	}

	bootstrapValidator = $('#fileForm').bootstrapValidator({
		excluded : [ ':disabled' ],
		fields : {
			istrue: {
				trigger:"change",
				 validators: {
		             notEmpty: {
			             message: '请确认文件已脱密'
			         }
		         } 
		    }
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$("#fileForm").ajaxSubmit({
			type : "POST",
			url : rootPath + "/model/evaluation/fileUpload.do",
			success : function(data) {
			
				data= eval('(' + data + ')');

				if (data.message == "操作成功！") {
					$("#evaluFormTable").bootstrapTable('refresh');
					$("#paFileModal").modal("hide");
				}

				layer.alert(data.message, {
					title : '提示',
					icon : 0
				});
			},
			error : function(msg) {
				layer.alert('操作失败！', {
					title : '提示',
					icon : 2
				});
			}
		});
		return false;
	});
	
	function filedelete(paid,fileId){
		$.ajax({
			dataType : "json",
			url : rootPath + "/model/evaluation/fileDelete?paId="+paid+"&fileId="+fileId,
			type : "get",
			success : function(data) {
				if (data.message == "操作成功！") {
					$("#"+"div"+fileId).remove();
	       			$("#evaluFormTable").bootstrapTable('refresh'); 
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
			}
		});
	}
</script>