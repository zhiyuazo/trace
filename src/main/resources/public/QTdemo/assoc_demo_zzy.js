function convertMillisecondToStdString(millisecondStr){
	if(millisecondStr==null){
		return millisecondStr;
	}
	var millisecond = parseInt(millisecondStr);
	var d = new Date(millisecond);
	var yyyy = d.getFullYear();
	if(isNaN(yyyy)){
		return "无效值";
	}
	var MM = d.getMonth()+1;
	MM = MM<10?"0"+MM:MM;
	var dd = d.getDate()<10?"0"+d.getDate():d.getDate();
	var HH = d.getHours()<10?"0"+d.getHours():d.getHours();
	var mm = d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes();
	var ss = d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds();
	var f = yyyy+"-"+MM+"-"+dd+"T"+HH+":"+mm+":"+ss;
	return f;
}
$(function(){
		var todo = "zzy";
	
	    //Group all data from 207-Fusion Res.. 
		var tsNowList = new Vue({
			el:"#frList",
			data:{
				fr_data:[],
				equipMappers :[],
				globalConstNations : window.globalConfig.nations,
				globalConstMBLB : window.globalConfig.kinds_syq,
				globalConstDWSX : window.globalConfig.camps_syq,
				globalConstMBMC : window.globalConfig.names_syq,
				ph_key:""
			},
			computed:{
				final_ph_key : function(){
					return Number.parseInt(this.ph_key);
				}
			},
			methods:{
				searchPH : function(){
					if(!isNaN(this.final_ph_key)){
				        $.ajax({
				            url: "/relate/data",
				            type:'get',
				            data:{
				            	"ph" : this.final_ph_key
				            },
				            success: function(result){
						        self.fr_data = result;
				            }
				        });
					}
				},
				fetchequips : function(){
					self = this;
			        $.ajax({
			            url: "/weaponAll",
			            type:'get',
			            async : false,
			            data:{},
			            success: function(result){
			            	self.equipMappers = result;
			            }
			        });
				},
				open_fr_detail : function(e, index ,fr){
					var prefix = "#fr_detail_"
					var detail_row_id  = prefix + index;
					$(detail_row_id).toggle();
				},
				open_fr_detail_onEearth : function(e, index ,fr){
					//add qwebchanel to Digital-ball function
	                var self = this;
					var lat = fr.latitude;
					var lon = fr.longitude;
					var locJSON = {
							"mbbh" : fr.TrackId,
							"lat": fr.latitude,
							"lon" :fr.longitude ,
							"alt" :fr.elevation
					}//disable current
					var strReq = JSON.stringify(locJSON);
                    var baseUrl = "ws://28.4.30.165:12345";
	                var socket = new WebSocket(baseUrl);
	                socket.onopen = function() {
	                    new QWebChannel(socket,(channel)=> {
	                        window.dialog = channel.objects.dialog;
	                        console.log(strReq);
	                        dialog.receiveRHLocation(strReq);
	                    });
	                }
	                socket.onerror = function(err) {
	                	console.log(err);
	                	return null;
	                }
	                socket.onclose = function() {
	                	console.log("close connection...");
	                	return null;
	                }
	                console.log("open on earthOK..")
				},
				getReadableDate : function(millis){
					return convertMillisecondToStdString(millis);
				},
				getReadableDWSX : function(index){
					var res = index;
					for(var i = 0 ;i < this.globalConstDWSX.length ; i++){
						if(this.globalConstDWSX[i].id == index){
							res = this.globalConstDWSX[i].text;
							break;
						}
					}
					return res;
				},
				getReadableMBLB : function(index){
					var res = index;
					for(var i = 0 ;i < this.globalConstMBLB.length ; i++){
						if(this.globalConstMBLB[i].id == index){
							res = this.globalConstMBLB[i].text;
							break;
						}
					}
					return res;
				},
				getReadableMBMC : function(ncode){
					var res = ncode;
					for(var i = 0 ;i < this.globalConstMBMC.length ; i++){
						if(this.globalConstMBMC[i].nameCode == ncode){
							res = this.globalConstMBMC[i].text;
							break;
						}
					}
					return res;
				},
				getJsonJCS : function(jcs_str){
					return  JSON.parse(jcs_str);
				},
				map2equipUnitJson : function(jc){
					var SubId ;
					if(jc.sub_ID > 100){
						SubId = jc.sub_ID -100;
					}else{
						SubId = jc.sub_ID;
					}
					var equip = "未知"
					$.each(this.equipMappers,function(index, value){
						if(SubId == value.subID){
							equip = value.componentName
						}else{;}
					})
					var res = "DNC" + "[" + jc.DNC_ID+ "]" + "-" +equip +"[" + jc.sub_ID + "]" + "-批号" + "[" + jc.count + "]";
					return res;
				},
				map2equipUnitStr : function(dnc , subid,count){
					var SubId ;
					if(jc.sub_ID > 100){
						SubId = subid -100;
					}else{
						SubId = subid;
					}
					var equip = "未知"
					$.each(this.equipMappers,function(index, value){
						if(SubId == value.subID){
							equip = value.componentName
						}else{;}
					})
					var res = "DNC" + "[" + dnc+ "]" + "-" +equip +"[" + SubId + "]" + "-批号" + "[" + count + "]";
					return res;
				},
				send2workArea:function(e,fr){
					e.preventDefault();
					if(yanpanWork.work_type == "关联"){
						yanpanWork.fr_data_vice.push(fr);
					}
					if(yanpanWork.work_type == "解关联"){
						yanpanWork.fr_data_debundle.push(fr);
					}
				},
				dragstart:function(e,fr){
					console.log("in dragstart...")
					e.dataTransfer.setData("text/plain",JSON.stringify(fr));
				},
				fetch_fr : function(){
					self = this;
					if(isNaN(this.final_ph_key)){
				        $.ajax({
				            url: "/relate/data",
				            type:'get',
				            data:{},
				            success: function(result){
						        self.fr_data = result;
				            }
				        });
					}
				}
			},
		    mounted:function(){ 
		    	this.fetchequips();
		    	if(this.timer){
		    		clearInterval(this.timer);
		    	}else{
		    		this.timer = setInterval(()=>{
		    			this.fetch_fr();
		    		},2000);
		    	}
		    }
		});
		
	    //Capture YanPan TS trackPoint from unknownList 
		var yanpanWork = new Vue({
			el:"#yanpanWork",
			data:{
				work_type : '关联',
				fr_data_vice:[],//关联所用副TS数据
				fr_data_main:[],//关联所用主TS数据
				fr_data_debundle:[{"auther_zzy":""}],//解关联所用主TS数据
				sensorChecks :[],
				equipMappers :[],
				globalConstNations : window.globalConfig.nations,
				globalConstMBLB : window.globalConfig.kinds_syq,
				globalConstDWSX : window.globalConfig.camps_syq,
				globalConstMBMC : window.globalConfig.names_syq
			},
			computed: {
			},
		    mounted:function(){ 
		    	this.fetchequips();
		    },
			methods:{
				fetchequips : function(){
					self = this;
			        $.ajax({
			            url: "/weaponAll",
			            type:'get',
			            async : false,
			            data:{},
			            success: function(result){
			            	self.equipMappers = result;
			            }
			        });
				},
				getJsonJCS : function(jcs_str){
					if(typeof(jcs_str) == 'undefined'){
						return {};
					}else{
						return  JSON.parse(jcs_str);
					}
				},
				getReadableDate : function(millis){
					return convertMillisecondToStdString(millis);
				},
				getReadableDWSX : function(index){
					var res = index;
					for(var i = 0 ;i < this.globalConstDWSX.length ; i++){
						if(this.globalConstDWSX[i].id == index){
							res = this.globalConstDWSX[i].text;
							break;
						}
					}
					return res;
				},
				getReadableMBLB : function(index){
					var res = index;
					for(var i = 0 ;i < this.globalConstMBLB.length ; i++){
						if(this.globalConstMBLB[i].id == index){
							res = this.globalConstMBLB[i].text;
							break;
						}
					}
					return res;
				},
				getReadableMBMC : function(ncode){
					var res = ncode;
					for(var i = 0 ;i < this.globalConstMBMC.length ; i++){
						if(this.globalConstMBMC[i].nameCode == ncode){
							res = this.globalConstMBMC[i].text;
							break;
						}
					}
					return res;
				},
				map2equipUnitJson : function(jc){
					var SubId ;
					if(jc.sub_ID > 100){
						SubId = jc.sub_ID -100;
					}else{
						SubId = jc.sub_ID;
					}
					var equip = "未知"
					$.each(this.equipMappers,function(index, value){
						if(SubId == value.subID){
							equip = value.componentName
						}else{;}
					})
					var res = "DNC" + "[" + jc.DNC_ID+ "]" + "-" +equip +"[" + jc.sub_ID + "]" + "-批号" + "[" + jc.count + "]";
					return res;
				},
				del_fr:function(e,index ,fr){
					e.preventDefault();
					this.fr_data_vice.splice(index,1)
					var jcs = this.getJsonJCS(fr.JoinedComponentId);
					for(var i in jcs){
						var tmp = JSON.stringify(jcs[i]); 
						for(var j in this.sensorChecks){
							var tmp_base = JSON.stringify(this.sensorChecks[j])
							if(tmp == tmp_base){
								this.sensorChecks.splice(j,1);
								break;
							}
						}
					}
				},
				bundle_fr : function(){
					var main_ph = this.fr_data_main[0].TrackId;
					var bundle_url = "/relate/add";
					var bundle_sensor = '';
					if(0 ==this.sensorChecks.length){
						alert("请至少选择一个传感器进行关联");
						return null;
					}else{
						bundle_sensor = JSON.stringify(this.sensorChecks);
				        $.ajax({
				            url: bundle_url,
				            type:'post',
				            data:{
				            	"ph":main_ph,
				            	"sensors":bundle_sensor
				            },
				            success: function(result){
								alert("关联请求发送成功...")
				            },
				            error:function(result){
				            	alert("关联请求发送错误，请重试...")
				            	return null;
				            }
				        });
					}
				},
				debundle_fr : function(jc){
					var main_ph = this.fr_data_debundle[0].TrackId;
					var debundle_url = "/relate/delete";
					var s_dnc =  jc.DNC_ID;
					var s_sub =  jc.sub_ID;
					var s_count =jc.count;
			        $.ajax({
			            url: debundle_url,
			            type:'post',
			            data:{
			            	"ph":main_ph,
			            	"sourceDnc":s_dnc,
			            	"sourceSub":s_sub,
			            	"sourceCount" : s_count
			            },
			            success: function(result){
			            	alert("解关联成功...")
			            },
			            error:function(result){
			            	alert("解关联错误，请重试...")
			            	return null;
			            }
			        });
				},
				drop:function(e){
					e.preventDefault();
					console.log("in drop...");
					var d = e.dataTransfer.getData("text/plain");
					if(this.work_type == "关联"){
						this.fr_data_main = []; 
						this.fr_data_main.push(JSON.parse(d));
					}
					if(this.work_type == "解关联"){
						this.fr_data_debundle = []; 
						this.fr_data_debundle.push(JSON.parse(d));
					}
				},
				allowDrop:function(e){
					e.preventDefault();
					console.log("in allowDrop...");
				}
			}
		});
		var assocLogList = new Vue({
			el:"#assocLogTrace",
			data:{
				log_data:[]
			},
			methods:{
				getReadableDate : function(millis){
					return convertMillisecondToStdString(millis);
				},
				fetch_fr : function(){
					self = this;
			        $.ajax({
			            url: "/relate/trace",
			            type:'get',
			            data:{},
			            success: function(result){
					        self.log_data = result;
			            }
			        });
				}
			},
		    mounted:function(){ 
    			this.fetch_fr();
		    	if(this.timer){
		    		clearInterval(this.timer);
		    	}else{
		    		this.timer = setInterval(()=>{
		    			this.fetch_fr();
		    		},10*1000);
		    	}
		    }
		});
		
	})
