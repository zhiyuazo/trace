//below ars editable functions
function nation_dict(){
	return "url:../getNationDict"; 
}
function pos_dict(){
	return "url:../getPosDict"; 
}
function cate_dict(){
	return "url:../getCateDict"; 
}
function kind_dict(){
	return "url:../getKindDict"; 
}
function prop_dict(){
	return "url:../getPropDict"; 
}

function HnameEdit(){
	var e = { type:"text",
			  title:"修改英雄名称",
			  mode:"popup",
			  placeholder:"请输入英雄名称",
			  toggle:"click",
			  showbuttons:"bottom",
			  // highlight:"#FFFF80",
			  validate:function(v){
			 	 if(!v) return '不能为空';
			  }
			}
	return e;
}

//Note, for Edit  TDs, if source get the selections via ajax function
//the Page will be get refresh a lot of times at start...
//this is not goot, oh, bad...
//so, use source:"url" and sourceCache:true to resolve this..
function HnationEdit(){
	var e = { type:"select",
			  title: '英雄出生地',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			  sourceCache: true,
//			  source: "subs/getNationDict.json"
			  source: "../getNationDict"
			}
	return e;
}

function HdescEdit(){
	var e = { type:"text",
			  title:"英雄状态更新",
			  mode:"popup",
			  placeholder:"时间地点人物？",
			  toggle:"click",
			  showbuttons:"bottom"
			}
	return e;
}

function HposEdit(){
	var e = { type:"select",
			  title: '英雄位置更新',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			  sourceCache: true,
//			  source: "subs/getPosDict.json"
			  source: "../getPosDict"
			}
	return e;
}

function HequipEdit(){
	var e = { type:"text",
			  title: '武器级别更新',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			}
	return e;
}

function HkindEdit(){
	var e = { type:"select",
			  title: '英雄职业',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			  sourceCache: true,
//			  source: "subs/getKindDict.json"
			  source: "../getKindDict"
			}
	return e;
}

function HcateEdit(){
	var e = { type:"select",
			  title: '英雄种族',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			  sourceCache: true,
//			  source: "subs/getCateDict.json"
			  source: "../getCateDict"
			}
	return e;
}
function HpropEdit(){
	var e = { type:"select",
			  title: '英雄特长',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			  sourceCache: true,
//			  source: "subs/getPropDict.json" 
			  source: "../getPropDict"
			}
	return e;
}

						//列标识，行数据 ，行索引，旧值，元素
function HeditableSave(field, row, rowIndex, oldValue, el){
	console.log("into editable Save");
	Hmark(row,el)
	HmarkRow(row,el);
	HModifiedCount();
}
function HeditableShown(field, rowdata, el, oldValue){
	console.log("into editable shown");
//	console.log(field  +  " : " +  iswhat(field)) ;
//	console.log(rowdata  +  " : " + iswhat(rowdata) ) ;
//	console.log(el[0].tagName  +  " : " + iswhat(el) ) ;
//	console.log(oldValue  +  " : " + iswhat(oldValue) ) ;
//	console.log("--------------------------------------------")		 	
//    for( i in oldValue){
//  		console.log(i  + ":_____ " + oldValue[i]);
//  	}
//  	el.focus();
			 
}
function HeditableInit(field, rowdata, el, oldValue){
	console.log("into editable init");
	console.log(field  +  " : " +  iswhat(field)) ;
	console.log(rowdata  +  " : " + iswhat(rowdata) ) ;
	console.log(el[0].tagName  +  " : " + iswhat(el) ) ;
	console.log(oldValue  +  " : " + iswhat(oldValue) ) ;
	console.log("--------------------------------------------")		 	
  for( i in oldValue){
  		console.log(i  + ":_____ " + oldValue[i]);
  }
			 
}
iswhat= function(o){
	if(typeof o === 'string'){
		return "type==string";
	}
	if(typeof o === 'number'){
		return "type==number";
	}
	
	if(typeof o === 'boolean'){
		return "type==boolean";
	}
	if(typeof o === 'undefined'){
		return "type==undefined";
	}
	if(typeof o === 'object'){
		if(o === null){
			return "type==object_null";
		}
		if(o instanceof Array){
			return "type==object_Array";
		}
		if(o instanceof Event){
			return "type==object_Event";
		}
		if(o instanceof String){
			return "type==object_String";
		}
		if(o instanceof Date){
			return "type==object_Date";
		}
		if(o instanceof Number){
			return "type==object_Number";
		}
		if(o instanceof RegExp){
			return "type==object_RegExp";
		}
		
		if(isJquery(o)){
			return "type==object_Jquery";
		}
		if(isDom(o)){
			return "type==object_Dom";
		}
		return "type==object_unkonwn"
	}
}

isJquery = function(o){
	if(o instanceof jQuery){
		return true;	
	}else{
		return false;	
	}
}

isDom = function(o){
	if(typeof HTMLElement === "object"){
		return o instanceof HTMLElement;
	}else{
		return o && 
			   typeof o ==="object" && 
			   o.nodeType === 1 &&
			   typeof o.nodeName === 'string';
	}
}


function Hmark(row,el){
	el.addClass('EHmark');
}

function HmarkRow(row,el){
	if(el.parent().parent().attr('Elabel') == 'true'){
		console.log("already marked! :hero ");		
	}else{
		console.log("first marked! :hero");		
		el.parent().parent().attr('Elabel',"true");
	}
}

function HModifiedCount(){
	var v = Number($("#countModified").html());
	var markRows = $("#all_hero").find('tr[Elabel]');
	v=markRows.length;
	if(v > 1){
		$("#btn-save-all").removeClass("disabled");
	}else{
		$("#btn-save-all").addClass("disabled");
	}
	$("#countModified").html(v);
}


function HeditableHidden(field, row, el, reason){
	console.log(field + row +  el[0].tagName + reason);
	if(reason == 'manual' || reason == 'save') {
		console.log("add class to el")
		el.addClass("Emarker");
		console.log("get el class: " + el.attr("class"))
	}
	console.log(el.html());
	console.log("on hidden");
}

//对传入的值，做二次处理....另外一个重要用法就是添加链接或按钮！
function actionFormatter4h(value,row,index,field){
	return [
		'<a class="devil_add" href="javascript:void(0)">',
			'<i class="glyphicon glyphicon-plus" style="font-size:14px"></i>',
		'</a>' ,
		'<a class="hero_single_send"  href="javascript:void(0)">',
			'<i class="glyphicon glyphicon-send text-danger" style="font-size:14px"></i>',
		'</a>'
	].join('');
}

window.actionEvents4h = {
	'click .devil_add':function(e,value,row,index){
		console.log(e.type +" : " + value+" : "+row.heroName+" : "+index);
		//expand this row firstly...
		$("#all_hero").bootstrapTable('expandRow',index);
		//open add dialog
		BootstrapDialog.show({
		size: BootstrapDialog.SIZE_NORMAL,
		type: BootstrapDialog.TYPE_PRIMARY,
		draggable:true,
		animate:true,
		title:"增加恶魔",
		message:function(dialogRef){
			 var content= $("<div></div>").load('subs/devilDesc.html',function(){
				 var btn_devilAdd_ok = dialogRef.getButton("btn_devilAdd_ok");
				 var form = content.find('#form');
				 var new_devil_data = content.find("#new_devil_data");
				 //fill add
//				 content.find('#devilId').val(function(){
//				 	var v = 20+ Math.floor(Math.round(Math.random()*100+1));
//				 	$(this).attr("readonly","true");
//				 	return v;
//				 });
				 
				 content.find('#ref2hero').val(function(){
				 	$(this).attr("readonly","true");
				 	return row.heroId;
				 	
				 });
				 content.find('#devilDesc').keyup({btn_devilAdd_ok:btn_devilAdd_ok},function(){
				 		var all = JSON.stringify($("form").serializeObject());
				 		new_devil_data.val(all);
				 		new_devil_data.trigger("input");
				 		if($(this).val().length !=0){
				 			btn_devilAdd_ok.enable();
				 		}else{
				 			btn_devilAdd_ok.disable();
				 		}
				 });
				 content.find('#devilName').val(function(){
				 	return row.heroName;
				 });
			
				 content.find('#devilEquipLevel').keyup(function(){
				 		var all = JSON.stringify($("form").serializeObject());
				 		new_devil_data.val(all);
				 		new_devil_data.trigger("input");
				 });
				 new_devil_data.bind("input propertychange",function(){
				 		console.log("data area change: " + new_devil_data.val());
				 		dialogRef.setData("new_devil",new_devil_data.val());
				 });
			 });
			 return content;
		},
		
		//data-field not support get data from a function???..
		data:{},
		onshow : function(dialogRef){
			dialogRef.getButton('btn_devilAdd_ok').disable();
		},
		buttons:[
			{
				id:"btn_devilAdd_close",
				label:'关闭',
				cssClass:'btn btn-danger',
				action: function(dialogRef){
					dialogRef.close();
				}
			},
			{
				id:'btn_devilAdd_ok',
				label:'增加',
				cssClass:'btn btn-success',
				action: function(dialogRef){
			        var newD_str = dialogRef.getData('new_devil');
			        $.ajax({
			            url: "../devil_add",
			            type:'post',
			            data:{"para_newD":newD_str},
			            success: function(result){
					        $("#all_devil").bootstrapTable('refresh');
			            }
			        });
					dialogRef.close();
				}
			 }
		   ]
		});	
	},
	
	'click .hero_single_send' : function(e,value,row,index){
		var row2 = row; 
		var ind = index;
		var editH_str = JSON.stringify(row2);	
		var markRows = $("#all_hero").find('tr[Elabel]');
		if(markRows.length > 1){
			alert("You Modify multi-lines,please use the save-all button...");
			return ;
		}
		if(markRows.length == 0){
			alert("Data not changed,click this after change somethings...")
			return ;
		}
		if(markRows.length == 1){
			//index need +1 to avoid the <thead> row 
			if($("#all_hero").find('tr').eq(index + 1).attr('Elabel') != 'true'){
				alert("You Click Wrong Place, Please click the send button on modified Row...")
				return ;
			};
		}
		console.log(markRows.length)
		console.log(editH_str);
		$.ajax({
		    url: "../hero_update",
		    type:'post',
		    data:{"para_editH":editH_str},
		    success: function(result){
		        $("#all_hero").bootstrapTable('updateRow',{index:ind,row:row2});
		    }
		});
		alert("save hero done..");
	}
}

//form serialize to Json Object
$.fn.serializeObject = function(){
	var o = {};
	var a = this.serializeArray();
	
	$.each(a,function(){
		if(o[this.name] !== undefined){
			if(!o[this.name].push){
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value||'');
		}else{
			o[this.name] = this.value||'';
		}
	});
	return o;
}

//toolbar actions
$(function(){
	
	url = "../hero";
	initTable(url);
	
	$("#btn-collapse").click(function(){
		var selectedRows = $("#all_hero").bootstrapTable('getAllSelections');
		if(selectedRows.length<=0){
			alert('请选择要展开的行,按住CTRL+鼠标单击以进行多选....');
			return;			
		}
		console.log(selectedRows);
//			var heroIds = $.map(selectedRows,function(row){
//			return row.heroId;	
//		});
//		$.each(heroIds)
//		$("#all_hero").bootstrapTable('toggleDetailView',[0,1])
	});
	
	$.contextMenu({
		selector:".uniqueHname",
		items:{
			add_ts:{name:"为此英雄添加状态",callback:function(key,opt){
							alert("添加ing...")
						}},
			view_trace:{name:"回溯此英雄历史战绩",callback:function(key,opt){
							alert("打开对战图...")
						}}
		}
	})

	$("#btn-refresh").click(function(){
		$("#all_hero").bootstrapTable('refresh');
	});
	
	
	$("#btn-test").click(function(){
//		To be Avaliable
		BootstrapDialog.alert("To be Avaliable...");
	});
	
	$("#btn-intoConflict").click(function(){
//		To be Avaliable
		BootstrapDialog.alert("To be Avaliable...");
	});
	
	$("#btn-save-all").addClass("disabled");
	$("#btn-save-all").click(function(){
		var markRows = $("#all_hero").find('tr[Elabel]');
		var uniqueHid_s = [];
		$.each(markRows, function(index,row){
			uniqueHid_s.push(Number($(row).find(".uniqueHid").text()));
		})
		console.log("modified lines: " + uniqueHid_s);
		$("#all_hero").bootstrapTable('checkBy',{field:"heroId",values:uniqueHid_s})
		var selectedRows = $("#all_hero").bootstrapTable('getSelections');
		editHduo_str = JSON.stringify(selectedRows)
		$.ajax({
		    url: "../hero_update_duo",
		    type:'post',
		    data:{"para_editHduo":editHduo_str},
		    success: function(result){
//		        $("#all_hero").bootstrapTable('refresh');
				$.each(markRows, function(index,row){
					var rowv = row;
					var idv = $(row).find(".uniqueHid").text();
					console.log("idv: " + rowv.id + "_" + typeof rowv)
			        $("#all_hero").bootstrapTable('refresh');
//			        $("#all_hero").bootstrapTable('updateByUniqueId',{id:idv,row:rowv});
					$("#all_hero").bootstrapTable('uncheckBy',{field:"heroId",values:idv})
				});
		        $("#countModified").html("0");
		        $("#btn-save-all").addClass("disabled");
		        alert("多条数据，已保存.....")
		    }
		});
	});
	
	
	$("#btn-clear").click(function(){
        $("#all_hero").bootstrapTable('clearFilterControl');
        $("#all_hero").bootstrapTable('triggerSearch');
	});
	
	$("#btn-delete").click(function(){
		var selectedRows = $("#all_hero").bootstrapTable('getSelections');
		if(selectedRows.length<=0){
			alert('请选择要删除的数据');
			return;			
		}
		var heroIds = $.map(selectedRows,function(row){
			return row.heroId;	
		});
		var heroIds_str = heroIds.join(',');		
		
        $.ajax({
            url: "../hero_del",
            type:'post',
            data:{"para_delH":heroIds_str},
            success: function(result,status){
            	alert(result + " : " + status);
		        $("#all_hero").bootstrapTable('clearFilterControl');
		        $("#all_hero").bootstrapTable('triggerSearch');
		        $("#all_hero").bootstrapTable('refresh');
            }
        });
	});
	
	$("#btn-add").click(function(){
		BootstrapDialog.show({
			size: BootstrapDialog.SIZE_NORMAL,
			type: BootstrapDialog.TYPE_PRIMARY,
			draggable:true,
			animate:true,
			title:"增加英雄",
			message:function(dialogRef){
				 var content= $("<div></div>").load('subs/heroDesc.html',function(){
					 var btn_heroAdd_ok = dialogRef.getButton("btn_heroAdd_ok");
					 var form = content.find('#form');
					 var new_hero_data = content.find("#new_hero_data");
//					 //fill add
//					 content.find('#heroId_t').val(function(){
//					 	var v = 20+ Math.floor(Math.round(Math.random()*100+1));
//					 	$(this).attr("readonly","true");
//					 	return v;
//					 	
//					 });
					 content.find('#heroName').keyup({btn_heroAdd_ok:btn_heroAdd_ok},function(){
					 		var all = JSON.stringify($("form").serializeObject());
					 		new_hero_data.val(all);
					 		new_hero_data.trigger("input");
					 		if($(this).val().length !=0){
					 			btn_heroAdd_ok.enable();
					 		}else{
					 			btn_heroAdd_ok.disable();
					 		}
						});
					 content.find('#heroDesc').keyup(function(){
					 		var all = JSON.stringify($("form").serializeObject());
					 		new_hero_data.val(all);
					 		new_hero_data.trigger("input");
					 });
					 content.find('#heroEquipLevel').keyup(function(){
					 		var all = JSON.stringify($("form").serializeObject());
					 		new_hero_data.val(all);
					 		new_hero_data.trigger("input");
					 });
					 new_hero_data.bind("input propertychange",function(){
					 		console.log("data area change: " + new_hero_data.val());
					 		dialogRef.setData("new_hero",new_hero_data.val());
					 		console.log("read from dialog frame: " + dialogRef.getData("new_hero"));
					 });
				 });
				 return content;
			},
			
			//data-field not support get data from a function???..
			data:{},
			onshow : function(dialogRef){
				dialogRef.getButton('btn_heroAdd_ok').disable();
			},
			buttons:[
				{
					id:"btn_heroAdd_close",
					label:'关闭',
					cssClass:'btn btn-danger',
					action: function(dialogRef){
						dialogRef.close();
					}
				},
				{
					id:'btn_heroAdd_ok',
					label:'增加',
					cssClass:'btn btn-success',
					action: function(dialogRef){
//						alert("添加： " + dialogRef.getData('new_hero'));
						var newH_str = dialogRef.getData('new_hero');
				        console.log(JSON.stringify(newH_str));
				        $.ajax({
				            url: "../hero_add",
				            type:'post',
				            data:{"para_newH":newH_str},
				            success: function(result){
						        $("#all_hero").bootstrapTable('refresh');
				            }
				        });
						dialogRef.close();
					}
				}
			]
		});	
	});
	
});
