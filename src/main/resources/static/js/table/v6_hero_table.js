//Init HeroTable
var initTable = function(url){
//	$("#all_hero").bootstrapTable("destroy");
	var d1 = new Date();
	console.log("start init table..: " + d1.getTime())
	$("#all_hero").bootstrapTable({
//			@BASIC-----------------------	
//			height:500,//超过此高度，则显示滚动条
//			rowAttributes:"",
//			local:"zh-CN",
//			virtualScrollItemHeight:20,
//			theadClasses:"",
//			footerStyle: {css:{'color':'white'}},//support func get value. 
//			escape:false, //Escapes a String into HTML
			rowStyle: {css:{'color':'white'}},//support func get value. 
			undefinedText:'-',
			exportDataType:'selected',
			virtualScroll:false,
			classes:'table table-bordered table-striped table-hover',
			striped:false,
			silent:true,
			minimumCountColumns:1,
			resizable:true,
			clickToSelect:true,
//			ignoreClickToSelectOn:"function()",//
			uniqueId:'heroId',
			cardView:false,
			detailView:true,
//			detailViewIcon:true , //show icon of detail View
//			detailViewByClick:true , //click TD , open detail view
//			detailFormatter:'function' , //detail View relate 
//			detailFilter:'function' , //detail Filter detail view relate
			multipleSelectRow:true,//ctrl + shift
//			filterOptions:{filterAlgorithm: 'and'}, //暂时不起作用？ 全字匹配，所有字段都有输入才可以
//			idField:'heroId', //那几个field used as checkbox
//			selectItemName:"heroId", //The name of radio or checkbox input
//			singleSelect:false, //o allow checkbox selecting only one row
//			checkboxHeader:true,//hide check-all in header
			maintainMetaData:true, //翻页后保持前面选择
			
			//@BASIC More-----------------------
			showHeader:true,
			showFooter:true,
			showButtonIcons:true,//refresh .etc function's icons 
			showButtonText:true,//refresh .etc function's texts 
//			showColumnsToggleAll:false, //列视图toggle
//			showFullscreen:true,
//			showColumns:false, //列视图toggle
//			showRefresh:false, //refresh toggle
//			showToggle:false, //view mode toggle
//			showPaginationSwitch:false,
//			smartDisplay:false, //display page or card view snartky
			
//			@ICONS-----------------------
			iconsPrefix:"glyphicon",
			iconsSize:"sm",
			icons:{
				detailOpen:"glyphicon-hand-down",
				detailClose: "glyphicon-hand-up"
			},
			
//			@ToolBar-----------------------
			toolbar:'#toolbar', //自定义toolbar
			toolbarAlign:'left',
//			buttonsToolbar:"#buttonToolBar", //toolbar 转成Buttons...
//			buttonClass:"secondary",
//			buttonAlign:"left",
//			buttonPrefix:"btn",
			
//			@Sort-----------------------
			sortable:true,
			sortName:'id',
			sortOrder:"asc",
			sortStable:false,
//			silentSort:true,
//			sortClass:"",
//			rememberOrder:false,
//			customSort:"function()",
			
			
			//@search...-----------------------
//			@Self search function, not very good...
//			search:true,
//			strictSearch:true, //开关comparsison check...
//			searchOnEnterKey:true, //不起作用...
//			searchTimeOut:500,
//			showSearchButton:true,
//			showSearchClearButton:true,
//			visibleSearch:true, //仅在显示的行列中搜索
//			trimOnSearch:true,  //不晓得什么作用
//			searchAlign:'left',
//			searchText:"",
//			customSearch:function(data,text){//自定义搜索
//				return data.filter(function(row,index,rowsArr){
//					//return true , keep this line
//					return row.heroName.indexOf(text) > -1;
//				})
//			},

//			@Pagination...-----------------------
			pagination:true,
			onlyinfoPagination:true,
			paginationLoop:false,//不让循环页面浏览...
//			服务器端分页
//			客户端选择页码，向后端发送ajax请求，默认包含参数:
//			limit,offset,search,sort,order
//			queryParams 支持传入更多的参数！
//				sidePagination:'server',
//			----------------------
			//客户端分页，一次加载所有数据，浏览器中进行分页，减少一次全数据显示的压力....
			//此方法还可以使用table自带的Searh&Filter进行全数据筛选...
			//适合数据量少的场景....
//			此外，客户端分页模式中，search直接完成搜索，不涉及后台通信，
//			search也是在全字段搜索，如果server模式下也用此search，要对数据库整个表进行搜寻，
//			因此，server分页模式，请重写search,或者利用filter,讲filter添加进queryParams
//			进而，后台取到filter,进行查询！
				sidePagination:'client',
			paginationHAlign:'left',
			paginationDetailHAlign:'right',
			paginationVAlign:'both',
			paginationPreText:'<<',
			paginationNextText:'>>',
			paginationSuccessivelySize:5, //页码条@总长度
			paginationPagesBySide:2, //页马条@左右两边长度
			paginationUseIntermediate:false,//页码条@中间展开控制
			pageNumber: 1 , //默认显示第几页，但是会跳转回第一页
			pageSize: "5" , //初始每页显示的记录数目 
			pageList: [10,15,20,25,30,50,100] , 
//			totalRows:10, // ???
//			totalNotFiltered: "???", 

//			@Filter plguin...-----------------------
			filterControl:true,
//			filterShowClear:true,
//			formatClearFilters: function(){return "hahaha"},
//			filterDataCollector:function(a,b,c){}//
//			alignmentSelectControlOptions:"right", //left right auto
//			hideUnusedSelectOptions:true, //隐藏表中没有的选项,当分页方式为server-side,此选项失效... 
//			disableControlWhenSearch:true , // server返回数据的时候禁用filter,当分页方式为server-side有效...
//			searchOnEnterKey:true, //这个没有生效过.....
			
//			@DATA-----------------------
//			data:"../data1.json", //self defined data
//			cache:false, //禁用AJAX请求缓存
//			contentType:'application/json', //请求Server时候request的ContentType
//			dataType:'json',//Expect data-type from Server 
//			ajax:'a self AJAX-function', //a function..
//			ajaxOptions:{},//AJAX 附加参数...
//			queryParams: "function(params){return params}", //请求时，通过重写此参数，添加一些额外的参数
//			queryParamsType:"limit", //limit to SEND query params with RESTful Type
//			responseHandler: "function(res){return res}" , //当server返回格式不匹配时，在这里面先处理一下!
//			totalField: "??" , //not understand
//			totalNotFilteredField: "???",  //not understand
//			dataField: "???" , //not understand
			url:url,
			method:'get',
			columns:[
					{checkbox:true,width:1,widthUnit:"%"},
					{field:'action4h',
					 title:"操作",
					 width:4,
					 widthUnit:"%",
					 formatter:actionFormatter4h,
					 events: window.actionEvents4h	 
					},
					//filter column configuration
//						Icons:{clear:'glyphicon-trash icon-clear'} ???什么玩意
//						filterControl:"input|select|datepicker"
//						filterData:"var:variable|url:'....'|json:{key:data}" //3种方式for select 
//						filterOrderBy:"asc" //the order for select items 
//						filterDefault:"..." //for all filter text; 
//						filterDatepickerOptions:"..." //for datepicker , JSON obj; 
//						filterStrictSearch:true //禁用模糊搜索
//						filterStarsWithSearch:true //什么东西？
//						filterControlPlaceholder:"xxxx"//提示值
					
					{field:"heroId",width:1,widthUnit:"%",title:"英雄排名",filterControl:"input",sortable:true,class:'uniqueHid'},
					{field:"heroName",title:"英雄名称",filterControl:"input",sortable:true,class:'uniqueHname',editable:'HnameEdit'},
					{field:"heroNation",title:"出生地",filterControl:"select",filterData:nation_dict(),sortable:true,editable:'HnationEdit'},
					{field:"heroDesc",title:"当前状态",filterControl:"input",sortable:true,editable:'HdescEdit'},
					{field:"heroPos",title:"所在战场",filterControl:"select",filterData:pos_dict(),sortable:true,editable:'HposEdit'},
					{field:"heroEquipLevel",title:"武器级别",filterControl:"input",sortable:true,editable:'HequipEdit'},
					{field:"heroCategory",title:"种族",filterControl:"select",filterData:cate_dict(),sortable:true,editable:'HcateEdit'},
					{field:"heroKind",title:"职业",filterControl:"select",filterData:kind_dict(),sortable:true,editable:'HkindEdit'},
					{field:"heroProp",title:"特长",filterControl:"select",filterData:prop_dict(),sortable:true,editable:'HpropEdit'}
			],
			//index 当前行的行索引
			//row 当前行的JSON数据对象。
			//$detail 当前行下面创建的新行里面的td对象，子表的table装载在这里
			onExpandRow: function(index,row,$subDetail){
					//InitSubTable on another js file 
					initSubTable(index,row,$subDetail) 
			},
			onEditableInit:function(){console.log("into editable init")},
			onEditableShown:HeditableShown,
			onEditableSave:HeditableSave,
			onEditableHidden:function(){"into editable hidden"}
	});
	var d2 = new Date();
	console.log("over init table..: " + d2.getTime());
	console.log("table load cost: " + Number(d2.getTime() - d1.getTime())); 
	return initTable;
};
