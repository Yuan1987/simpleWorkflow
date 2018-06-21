<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="ie8 no-js">
    <head>
    
    	<style type="text/css">
    		.page-header.navbar .page-logo .logo-default {
				margin: 10px 10px 0 !important;
			}
    	</style>
    
        <meta charset="utf-8" />
        <title>cetc</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${ctx}/static/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${ctx}/static/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${ctx}/static/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${ctx}/static/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="${ctx}/static/assets/layouts/layout4/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/layouts/layout4/css/themes/light.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${ctx}/static/assets/layouts/layout4/css/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/static/assets/global/plugins/validator/css/bootstrapValidator.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="${ctx}/static/assets/global/plugins/layer/skin/layer.css" type="text/css">
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
        
        <!-- BEGIN CORE PLUGINS -->
        <script src="${ctx}/static/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${ctx}/static/assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${ctx}/static/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="${ctx}/static/assets/layouts/layout4/scripts/layout.min.js" type="text/javascript"></script>
        <script src="${ctx}/static/assets/layouts/layout4/scripts/demo.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        
        <!-- begin my -->
        <script src="${ctx}/static/assets/global/plugins/bootstrap-table/bootstrap-table.js"></script>
		<script src="${ctx}/static/assets/global/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
		<script src="${ctx}/static/assets/global/plugins/bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
		<script src="${ctx}/static/assets/global/plugins/table-export/tableExport.min.js"></script>
		
		<script src="${ctx}/static/assets/global/plugins/validator/js/bootstrapValidator.min.js"></script>
		<script src="${ctx}/static/assets/global/plugins/validator/js/language/zh_CN.js"></script>
		<script src="${ctx}/static/assets/global/plugins/layer/layer.js"></script>
        <!-- end my -->
        
        <script src="${ctx}/static/assets/global/plugins/respond.min.js"></script>
		<script src="${ctx}/static/assets/global/plugins/excanvas.min.js"></script> 
		<script src="${ctx}/static/assets/global/plugins/ie8.fix.min.js"></script> 
        
        </head>
    <!-- END HEAD -->

    <body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="${ctx}/home"> <img
					src="${ctx}/static/assets/layouts/layout4/img/logo-light.png"
					alt="logo" class="logo-default" />
				</a>
			</div>
			<!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN PAGE TOP -->
                <div class="page-top">
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user dropdown-dark">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <span class="username username-hide-on-mobile"> ${CURRENT_USER.displayname}</span>
                                    <!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
                                    <img alt="" class="img-circle" src="${ctx}/static/assets/layouts/layout4/img/avatar.png" /> </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                 <!--    <li>
                                        <a href="javascript:void(0);" id="userProfile">
                                            <i class="icon-user"></i> 个人资料 </a>
                                    </li> -->
                                    <li>
                                        <a href="${ctx}/logout">
                                            <i class="fa fa-key"></i> 退出</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END USER LOGIN DROPDOWN -->
                        </ul>
                    </div>
                    <!--END TOP NAVIGATION MENU -->
                </div>
                <!-- END PAGE TOP -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
					</ul>
                </div>
            </div>
       <!-- BEGIN CONTENT -->
       <sitemesh:write property='body' />
       <!-- END CONTENT -->
       </div>
        <!-- BEGIN FOOTER -->
      	 <div class="page-footer">
            <div class="page-footer-inner"> framework
                 &nbsp;|&nbsp;
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
        </div>
        <!-- END FOOTER -->
        
       <!-- 个人详细信息modal 与  usermanger 共用-->
       <div id="crop-avatar">
			<div class="modal fade" id="userModal" tabindex="-1"></div>
			<div class="modal fade" id="certificateModal" tabindex="-1"></div>
			<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
		      <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		          <form class="avatar-form" action="${ctx}/common/cutImg" enctype="multipart/form-data" method="post">
		            <div class="modal-header">
		              <button type="button" class="close" data-dismiss="modal">&times;</button>
		              <h4 class="modal-title" id="avatar-modal-label">图片上传</h4>
		            </div>
		            <div class="modal-body">
		              <div class="avatar-body">
		
		                <!-- Upload image and data -->
		                <div class="avatar-upload">
		                  <input type="hidden" class="avatar-src" name="avatar_src">
		                  <input type="hidden" class="avatar-data" name="avatar_data">
		                  <label for="avatarInput">本地上传</label>
		                  <input type="file" class="avatar-input" id="avatarInput" name="file">
		                </div>
		
		                <!-- Crop and preview -->
		                <div class="row">
		                  <div class="col-md-9">
		                    <div class="avatar-wrapper"></div>
		                  </div>
		                  <div class="col-md-3">
		                    <div class="avatar-preview preview-lg"></div>
		                  </div>
		                </div>
		
		                <div class="row avatar-btns">
		                   <div class="col-md-9">
							 <div class="btn-group">
								<button type="button" class="btn btn-primary"
									data-method="rotate" data-option="-90" title="向左旋转90°">向左旋转</button>
								<button type="button" class="btn btn-primary"
									data-method="rotate" data-option="-15">15°</button>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-primary"
									data-method="rotate" data-option="90" title="向右旋转90°">向右旋转</button>
								<button type="button" class="btn btn-primary"
									data-method="rotate" data-option="15">15°</button>
							</div>
						  </div>
		                  <div class="col-md-3">
		                    <button type="submit" class="btn btn-primary btn-block avatar-save">确定</button>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </form>
		        </div>
		      </div>
		    </div><!-- /.modal -->
		    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
		</div>
        
    </body>

<script type="text/javascript">

var rootPath='${ctx}';
var userId='${CURRENT_USER.id}';

$(function(){
	
	$(".page-sidebar-menu").empty();
	$.ajax({
		type : "POST",
		url : rootPath + "/system/resourceManager/menus",
		dataType:"json",
		success : function(data){
			/* $.each(data,function(index,item){  
       	
		       	var li=$("<li class=\"nav-item start\"></li>");
		       	var content="<a href=\"javascript:;\" class=\"nav-link nav-toggle\"> <i class=\""+item.classname+"\"></i>"
							+"<span class=\"title\">"+item.name+"</span><span class=\"selected\"></span><span class=\"arrow open\"></span>"
						+"</a>";
		       	li.append(content);
		       	var ul=$("<ul class=\"sub-menu\"></ul>");
		       	li.append(ul);
		       	$.each(item.childResource,function(i,child){
		       		var url=child.url;
		       		var childLi="<li class=\"nav-item start\"><a href=\""+rootPath+child.url+"\" class=\"nav-link\">"
					+"<i class=\""+child.classname+"\"></i><span class=\"title\">"+child.name+"</span><span class=\"selected\"></span>"
					+"</a></li>";
		       		ul.append(childLi);
		       	});
		       	$(".page-sidebar-menu").append(li);
	      }); */
	      loadURL(data);
	      $(".sub-menu").css("display","none");
		  $(".sub-menu>li").removeClass("active");
		  $(".sub-menu").parent().find(".arrow").removeClass("open");
		  $('.sub-menu>li>a').each(function(){
		  
	       if ($($(this))[0].href == String(window.location)){
	          var current= $(this).parent().addClass("active");
		      current.parents(".sub-menu").css("display","block");
		      current.parents(".nav-item").find(".arrow").addClass("open");
		      return false;
		    }
	     });
		}
	});

	$("#userProfile").click(function(){
		$modal=$('#userModal');
	    $modal.load(rootPath+"/user/userProfile/userProfile.html",'', function(){
			$modal.modal();
	    });
	});
});

function loadURL(data){

	$.each(data,function(index,item){
		var li=$("<li class=\"nav-item start\"></li>");
		var content="<a href=\"javascript:;\" class=\"nav-link nav-toggle\"> <i class=\""+item.classname+"\"></i>"
				+"<span class=\"title\">"+item.name+"</span><span class=\"selected\"></span><span class=\"arrow open\"></span>"
			+"</a>";
       	li.append(content);
		var cul=childURL(item.childResource);
		li.append(cul);
    	$(".page-sidebar-menu").append(li);
	});
}

function childURL(data){
	var ul=$("<ul class=\"sub-menu\"></ul>");
	$.each(data,function(index,item){
		var li=$("<li class=\"nav-item start\"></li>");
       	if(item.childResource!=undefined){
       		var content="<a href=\"javascript:;\" class=\"nav-link nav-toggle\"> <i class=\""+item.classname+"\"></i>"
					+"<span class=\"title\">"+item.name+"</span><span class=\"arrow open\"></span>"
				+"</a>";
	       	li.append(content);
	        li.append(childURL(item.childResource));
	        ul.append(li);
       	}else{
       		var content="<a href=\""+rootPath+item.url+"\" class=\"nav-link\">"
			+"<i class=\""+item.classname+"\"></i><span class=\"title\">"+item.name+"</span>"
			+"</a>";
       		li.append(content);
       		ul.append(li);
       	}
     });
     return ul;
}

</script>

</html>