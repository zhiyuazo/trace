var initFatherTable = function(url){
//	$("#all_target").bootstrapTable("destroy");
	$("#fatherTable").bootstrapTable({
//			@BASIC-----------------------	
//			height:500,//超过此高度，则显示滚动条
//			rowAttributes:"",
//			local:"zh-CN",
//			virtualScrollItemHeight:20,
//			theadClasses:"",
//			footerStyle: {css:{'color':'white'}},//support func get value. 
//			escape:false, //Escapes a String into HTML
//			rowStyle: {css:{'color':'white'}},//support func get value. 
//			undefinedText:'[空]',
			exportDataType:'selected',
			virtualScroll:false,
			theadClasses:"thead-dark",
//			classes:'table table-bordered table-striped table-hover',
			striped:false,
			silent:true,
			minimumCountColumns:1,
			resizable:true,
			clickToSelect:true,
//			ignoreClickToSelectOn:"function()",//
			uniqueId:'id',
			cardView:false,
			detailView:true,
//			detailViewIcon:true , //show icon of detail View
//			detailViewByClick:true , //click TD , open detail view
//			detailFormatter:'function' , //detail View relate 
//			detailFilter:'function' , //detail Filter detail view relate
			multipleSelectRow:true,//ctrl + shift
//			filterOptions:{filterAlgorithm: 'and'}, //暂时不起作用？ 全字匹配，所有字段都有输入才可以
//			idField:'targetId', //那几个field used as checkbox
//			selectItemName:"TargetId", //The name of radio or checkbox input
//			singleSelect:false, //o allow checkbox selecting only one row
//			checkboxHeader:true,//hide check-all in header
			maintainMetaData:true, //翻页后保持前面选择
			
			//@BASIC More-----------------------
			showHeader:true,
//			stickyHeader:true,
//			stickyHeaderOffsetY:50,
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
//					return row.targetName.indexOf(text) > -1;
//				})
//			},

//			@Pagination...-----------------------
			pagination:true,
			onlyinfoPagination:true,
			paginationLoop:false,//不让循环页面浏览...
//			服务器端分页
//			客户端选择页码，向后端发送ajax请求
//			----------------------
			//客户端分页，一次加载所有数据，浏览器中进行分页，减少一次全数据显示的压力....
			//此方法还可以使用table自带的Searh&Filter进行全数据筛选...
			//适合数据量少的场景....
//			此外，客户端分页模式中，search直接完成搜索，不涉及后台通信，
//			search也是在全字段搜索，如果server模式下也用此search，要对数据库整个表进行搜寻，
//			因此，server分页模式，请重写search,或者利用filter,讲filter添加进queryParams
//			进而，后台取到filter,进行查询！
				sidePagination:'client', //server一次返回所有数据,请求时：默认传入id,asc 两个参数
//				sidePagination:'server', //server返回特定格式数据，请求时默认传入id asc , offset,limit参数, limit根据前面的分页配置而确定，后台根据收到的offset和limit进行数据的返回. 
				//url[get] 与 queryParams 配合使用完成客户端/服务端分页请求...
				//ajax 请求方式稍后测试....
//					queryParams: function(params){
//						params.addtest = "test";
//						params.search_key = search_key;
//						return params
//					},
//					url : '/devil',
//					method:'get',
			paginationHAlign:'left',
			paginationDetailHAlign:'right',
			paginationVAlign:'both',
			paginationPreText:'<<',
			paginationNextText:'>>',
			paginationSuccessivelySize:5, //页码条@总长度
			paginationPagesBySide:2, //页马条@左右两边长度
			paginationUseIntermediate:false,//页码条@中间展开控制
			pageNumber: 1 , //默认显示第几页，但是会跳转回第一页
			pageSize: "15" , //初始每页显示的记录数目 
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
			disableControlWhenSearch:true , // server返回数据的时候禁用filter,当分页方式为server-side有效...
//			searchOnEnterKey:true, //这个没有生效过.....
			
//			@DATA-----------------------
//			data:"../data1.json", //self defined data
//			cache:false, //禁用AJAX请求缓存
//			contentType:'application/json', //请求Server时候request的ContentType
//			dataType:'json',//Expect data-type from Server 
//			ajax:'a self AJAX-function', //a function..
//			ajaxOptions:{},//AJAX 附加参数...
//			queryParamsType:"limit", //limit to SEND query params with RESTful Type
//			responseHandler: "function(res){return res}" , //当server返回格式不匹配时，在这里面先处理一下!
//			totalField: "??" , //not understand
//			totalNotFilteredField: "???",  //not understand
//			dataField: "???" , //not understand
			queryParams: function(params){params.fathertest="father";return params}, //请求时，通过重写此参数，添加一些额外的参数
			url:url,
			method:'get',
			columns:[
					{checkbox:true,width:1,widthUnit:"%"},
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
					
					{field:"heroId",title:"英雄ID"},
					{field:"heroName",title:"英雄名称"},
					{field:"heroNation",title:"英雄国家"},
					{field:"heroDesc",title:"英雄描述"},
					{field:"heroPos",title:"英雄位置"},
					{field:"heroEquipLevel",title:"装备武器"},
					{field:"heroCategory",title:"英雄种类"},
					{field:"heroKind",title:"英雄类别"},
					{field:"heroProp",title:"英雄性质"}
			],
			//index 当前行的行索引
			//row 当前行的JSON数据对象。
			//$detail 当前行下面创建的新行里面的td对象，子表的table装载在这里
			onExpandRow: function(index,row,$subDetail){
					//InitSubTable on another js file 
					initSonTable(index,row,$subDetail) 
			}
	});
	return initFatherTable;
};

var initSonTable = function(index,row ,$subDetail){
	var search_key = row.heroId;
	var child_content = $subDetail.html('<div><table style="color:#EC870E;aligh:right;background-color:#B7B7B7" id="all_devil"></table></div>');
	child_rule_table = child_content.find("#all_devil");
	child_rule_table.bootstrapTable({
			//url[get] 与 queryParams 配合使用
			//ajax 请求方式稍后测试....
				queryParams: function(params){
					params.addtest = "test";
					params.search_key = search_key;
					return params
				},
				url : '/devil',
				method:'get',
			//url[get] 与 queryParams 配合使用
			//客户端分页....
			ajaxOption :{},
			clickToselect:true,
			detailView:false,
			detailViewByClick:false,
			classes:'table',
			cache:false,
			showFooter:true,
			clickToSelect:true,
			uniqueId:"id",
			formatNoMatches: function(){
				return [
					'没有找到匹配的记录,请添加     ',
					'<span class="glyphicon glyphicon-plus text-warning">',
					'</span>'
					].join('');
			},
			columns:[
				{checkbox:true},
				{field:"devilId",title:"恶魔编号"},
				{field:"devilName",title:"恶魔名称"},
				{field:"devilDesc",title:"恶魔简介"},
				{field:"devilNation",title:"恶魔出生地"},
				{field:"devilEquipLevel",title:"恶魔武器"},
				{field:"devilKind",title:"恶魔类型"},
				{field:"devilCate",title:"装备种族"}
			],
//		});
	});
};
$(function(){
	initFatherTable("/hero_s");
});

