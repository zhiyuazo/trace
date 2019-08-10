//Init Sub table
var initSubTable = function(index,row ,$subDetail){
	var search_key = row.heroId;
	console.log("in subtable: " + row.heroId)
	var child_content = $subDetail.html('<div></div>').load("subs/devilFrame.html", function(){
		child_devil_table = child_content.find("#all_devil");
		child_devil_table.bootstrapTable({
			queryParams: {search_key : search_key},
			ajaxOption :{},
			url : '../devil',
			method:'get',
			clickToselect:true,
			detailView:false,
			undefinedText:'-',
			detailViewByClick:true,
			classes:'table',
			cache:false,
			showFooter:true,
			clickToSelect:true,
			uniqueId:"devilId",
			formatNoMatches: function(){
				return [
					'没有找到匹配的记录,请添加     ',
					'<span class="glyphicon glyphicon-plus text-warning">',
					'</span>'
					].join('');
			},
//			formatLoadingMessage:function(){
//				return "载入数据中....."
//			},
			columns:[
				{checkbox:true},
				{field: 'action',
				 title:"操作",
				 formatter:actionFormatter4d,
				 events: window.actionEvents4d
				},
				{field:"devilId",
				 title:"恶魔ID",
				 formatter:devilIdFormatter
				},
				{field:"devilDesc",title:"战队匹配",editable:'DdescEdit'},
				{field:"devilName ",title:"恶魔名称",editable:'DdescEdit'},
				{field:"devilNation",title:"出生地区",editable:'DnationEdit'},
				{field:"devilEquipLevel",title:"武器级别",editable:'DequipEdit'},
				{field:"devilCate ",title:"恶魔种族",editable:'DcateEdit'},
				{field:"devilKind",title:"恶魔职业",editable:'DkindEdit'}
			],
			onEditableInit:function(){console.log("into editable init")},
			onEditableShown:DeditableShown,
			onEditableSave:DeditableSave,
			onEditableHidden:function(){"into editable hidden"}
		});
	});
};