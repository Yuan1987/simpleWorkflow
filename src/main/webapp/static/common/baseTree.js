var radioCheck = {
	enable : true,
	chkStyle : "radio",
	radioType : "all"
};

var boxCheck = {
		enable : true,
		autoCheckTrigger: true,
		chkboxType : {
			"Y" : "",
			"N" : ""
		}
	};

function showTreeWithCallBack(zNodes,checkNodeIds, tiggerId, treeId, target, targetHidden,
		treeContent,isCheckBox,callBackFunc) {
	showTreeInner(zNodes,checkNodeIds, tiggerId, treeId, target, targetHidden,
			treeContent,isCheckBox,callBackFunc);
	
}

function showTree(zNodes,checkNodeIds, tiggerId, treeId, target, targetHidden,
		treeContent,isCheckBox) {
	showTreeWithCallBack(zNodes,checkNodeIds, tiggerId, treeId, target, targetHidden,
			treeContent,isCheckBox);
	
}

/**
 * 
 * @param zNodes:节点数据
 * @param zNodes:初始选中的ID
 * @param tiggerId：选择触发的ID
 * @param treeId:树节点ID
 * @param target：显示name的目标ID
 * @param targetHidden：显示Ids的目标ID
 * @param treeContent：显示树的容器ID
 * @param radioCheck：是否多选展示，默认单选
 */
function showTreeInner(zNodes,checkNodeIds, tiggerId, treeId, target, targetHidden,
		treeContent,isCheckBox,callBackFunc) {
	var callBack = callBackFunc;
	var check = radioCheck;
	if(isCheckBox){
		check = boxCheck;
	}
	setting = {
		check : check,
		view : {
			showIcon: false,
			showLine : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck: onCheck
		}
	};
	$.fn.zTree.init($("#" + treeId), setting, zNodes);
	$("#" + tiggerId).click(function() {
		$("#" + treeContent).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	
	var roots = getRoots(zTree);
	if(roots && roots.length > 0){
			for (var i=0;i<roots.length ;i++ ) {
				zTree.expandNode(roots[i], true, false, false); 
			}
			zTree.expandNode(roots, true, false, false); 
	}
	if(checkNodeIds && checkNodeIds != null){
		var cids= checkNodeIds.split(","); //字符分割 
		var v = "", ids = "";
		for (i=0;i<cids.length ;i++ ) 
		{ 
			try{
				var node = zTree.getNodeByParam("id", cids[i], null);
				if(node){
					node.halfCheck = false;
					node.half =false;
					zTree.checkNode (node, true);
				    zTree.updateNode(node);	
				    setCheck(node);
				}
			}catch(e){
			}
		} 
	}
	
	function onBodyDown(event) {
		if (!(event.target.id == tiggerId || event.target.id == target
				|| event.target.id == treeContent || $(event.target).parents(
				"#" + treeContent).length > 0)) {
			hideMenu();
		}
	}
	
	function hideMenu() {
		$("#" + treeContent).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onCheck(event, treeId, treeNode) {
		setCheck(treeNode,'click'); 
	}
	
	// 增加sourceType：来源 如果来至 前端点击选中 ，当父节点被选中时 要选中子节点。如果是初始化选中的值时就不选中父级点下的子节点了（后台数据已包含子节点数据 ）
	function setCheck(treeNode,sourceType){
		   var cNode =  treeNode;
		   //更新自身
		   cNode.halfCheck = false;
		   cNode.half =false;
		   zTree.updateNode(cNode);	
		   if(isCheckBox){
			   recursiveCheckParentNodes(cNode);//判断父节点是否应该选中
			   if(sourceType=="click"){//前端选中
				   recursiveCheckChildrenNodes(cNode);//判断子节点是否应该选中   
			   }
		   }
		   //再计算所有的选中节点
		   var nodes = zTree.getCheckedNodes(true), v = "", ids = "";
		   for (var i = 0, l = nodes.length; i < l; i++) {  
               //如果本身是半选状态或者父节点是选中状态，则跳过		     
			     if(nodes[i].halfCheck||( nodes[i].getParentNode() && nodes[i].getParentNode().checked && !nodes[i].getParentNode().halfCheck)){
			    	 //continue;
			     }else{
			    	 v += nodes[i].name + ",";
				     //
			     }
			     //获取所有选中的节点的id， 包括半选中和父级点位选中的状态。name的还是按上面的逻辑
			     ids +=  nodes[i].id + ","; 
		    }
		    setValue(v,ids);
	}
	
	 function recursiveCheckParentNodes(cNode){//判断父节点是否应该选中
		  if(cNode){
			  var parentNode = cNode.getParentNode();
			  if(parentNode){ //判断父节点是否应该选中
				     var siblings = parentNode.children;
				     var selectCount = 0 ;
				     if(siblings && siblings.length > 0){
					    	for (var j = 0; j < siblings.length; j++) {
					    		if(siblings[j].checked){
					    			selectCount ++;
					    		}
					    		if(siblings[j].halfCheck){
					    			selectCount ++;
					    		}
					    	}
					    	 if(selectCount == siblings.length){ //半选和选中是两个属性，要区分开:如果刚好等于说明没有半选的
					    		 parentNode.halfCheck = false;
					    		 parentNode.half =false;
					    		 zTree.checkNode (parentNode, true);
						     }else if(selectCount > 0 ){ //存在一个以上就需要半选
						    	 zTree.checkNode (parentNode, true);
						    	 parentNode.halfCheck = true
						    	 parentNode.half =true;
						     }else{//否则一个都没有
						    	 parentNode.halfCheck = false;
						    	 parentNode.half =false;
						    	 zTree.checkNode (parentNode, false);
						     }
					 }
				     zTree.updateNode(parentNode);			
				     recursiveCheckParentNodes(parentNode);
			  }
		  }
	 }
	 
	function  recursiveCheckChildrenNodes(node){
		   if(node){
			   var children = node.children;
			   if(children && children.length > 0){
			    	for (var j = 0; j < children.length; j++) {
			    		zTree.checkNode (children[j], node.checked);
			    		children[j].halfCheck = false;
			    		recursiveCheckChildrenNodes(children[j]);
			    	}
			    }	
		   }
	}
	
	function setValue(v,ids){
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (ids.length > 0)
			ids = ids.substring(0, ids.length - 1);
		$("#" + target).attr("value", v);
		//$("#" + target).change();
		$("#" + targetHidden).attr("value", ids);
		if(callBack &&  typeof callBack === 'function' ){
			callBack(ids);	
		}
	}
	
	
	function getRoots(treeObj) {
		try{
			 //返回根节点集合
			   return treeObj.getNodesByFilter(function (node) { return node.level == 0 });
		}catch(e){
			return null;
		}
	   //   return treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
	}
}
