function DnameEdit(){
	var e = { type:"text",
			  title:"修改恶魔名称",
			  mode:"popup",
			  placeholder:"请输入恶魔名称",
			  toggle:"click",
			  showbuttons:"bottom",
			  // highlight:"#FFFF80",
			  validate:function(v){
			 	 if(!v) return '不能为空';
			  }
			}
	return e;
}

function DnationEdit(){
	var e = { type:"select",
			  title: '国家地区',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
  			  sourceCache: true,
//			  source: "subs/getNationDict.json"
			  source: "../getNationDict"
			}
	return e;
}

function DdescEdit(){
	var e = { type:"text",
			  title:"修改战队匹配",
			  mode:"popup",
			  placeholder:"时间地点人物？",
			  toggle:"click",
			  showbuttons:"bottom"
			}
	return e;
}

function DequipEdit(){
	var e = { type:"text",
			  title: '武器级别',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
			}
	return e;
}

function DkindEdit(){
	var e = { type:"select",
			  title: '恶魔职业',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
  			  sourceCache: true,
//			  source: "subs/getKindDict.json"
			  source: "../getKindDict"
			}
	return e;
}

function DcateEdit(){
	var e = { type:"select",
			  title: '恶魔种族',
			  mode:"popup",
			  highlight: "0FFFF80",
			  unsavedclass:"editable-unsaved",
  			  sourceCache: true,
//			  source: "subs/getKindDict.json"
			  source: "../getCateDict"
			}
	return e;
}

						//列标识，行数据 ，行索引，旧值，元素
function DeditableSave(field, row, rowIndex, oldValue, el){
	console.log("into editable Save");
	Dmark(row,el)
	DmarkRow(row,el);
}
function DeditableShown(field, rowdata, el, oldValue){
	console.log("into editable shown");
//	console.log(field  +  " : " +  iswhat(field)) ;
//	console.log(rowdata  +  " : " + iswhat(rowdata) ) ;
//	console.log(el[0].tagName  +  " : " + iswhat(el) ) ;
//	console.log(oldValue  +  " : " + iswhat(oldValue) ) ;
//	console.log("--------------------------------------------")		 	
//  for( i in oldValue){
//  		console.log(i  + ":_____ " + oldValue[i]);
//  }
}

function DeditableHidden(field, row, el, reason){
	console.log(field + row +  el[0].tagName + reason);
	if(reason == 'manual' || reason == 'save') {
		console.log("add class to el")
		el.addClass("Dmarker");
		console.log("get el class: " + el.attr("class"))
	}
	console.log(el.html());
	console.log("on hidden");
}

function Dmark(row,el){
	el.addClass('EDmark');
}
function DmarkRow(row,el){
	if(el.parent().parent().attr('EDlabel') == 'true'){
		console.log("already marked! :devil");		
	}else{
		console.log("first marked! :devil");		
		el.parent().parent().attr('EDlabel',"true");
	}
}

function actionFormatter4d(value,row,index,field){
	console.log(value+" : "+row.devilId+" : "+index+" : "+field);
	return [
		'<a class="devil_single_send" href="javascript:void(0)">',
			'<i class="glyphicon glyphicon-send" style="font-size:20px"></i>',
		'</a>' ,
		'<a class="devil_remove" href="javascript:void(0)">',
			'<i class="glyphicon glyphicon-trash text-danger" style="font-size:20px"></i>',
		'</a>'
	].join('');
}

function devilIdFormatter(value,row,index,field){
	console.log(value+" : "+row.id+" : "+index+" : "+field);
	return value;
}

$(function(){
	window.actionEvents4d = {
		'click .devil_remove':function(e,value,row,index){
			console.log(e +" : " + value+" : "+row+" : "+index);
	        var dId =row.devilId;
	        $.ajax({
	            url: "../devil_del",
	            type:'post',
	            data:{"para_delD":dId},
	            success: function(result,status){
			        $("#all_devil").bootstrapTable('refresh');
	            }
	        });	
		},
		
		//the devil_single_send function only support on child table actions
		//IF open a lot of child table, meet errors
		// two way to resolv this problem, 
		//     no.1. js file with parameters
		//     no.2. Jquery find Element improved.. 
		'click .devil_single_send':function(e,value,row,index){
			var row2 = row; 
			var ind = index;
			var editD_str = JSON.stringify(row2);		
			var markRows = $("#all_devil").find('tr[EDlabel]');
			if(markRows.length > 1){
				alert("暂不支持恶魔多条编辑...");
				return ;
			}
			if(markRows.length == 0){
				alert("恶魔未曾改变...")
				return ;
			}
			if(markRows.length == 1){
				//index need +1 to avoid the <thead> row 
				if($("#all_devil").find('tr').eq(index + 1).attr('EDlabel') != 'true'){
					alert("点错了，请选择修改过的恶魔进行保存...")
					return ;
				};
			}
			console.log(markRows.length)
			$.ajax({
			    url: "../devil_update",
			    type:'post',
			    data:{"para_editD":editD_str},
			    success: function(result){
			        $("#all_devil").bootstrapTable('updateRow',{index:ind,row:row2});
			    }
			});
			alert("save devil done..");
		}
	}
});
