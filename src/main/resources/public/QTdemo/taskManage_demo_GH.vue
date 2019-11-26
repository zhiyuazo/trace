<template>
    <div class="manageInfo">
        <aside>
            <com_aside></com_aside>
        </aside>
        <div class="right-table">
            <el-table style="width:100%;height:90%;" :data="taskDataList" height="90%">
                <!--<el-table-column type="selection" :span="2"></el-table-column>-->
                <!--<el-table-column prop="TASK_ID" label="任务ID" :span="1" ></el-table-column>-->
                <el-table-column prop="TASK_CODE" label="任务编号" :span="1"></el-table-column>
                <el-table-column prop="TASK_NAME" label="任务名称" :span="3"></el-table-column>
                <!--<el-table-column prop="IS_EMERGENCY" label="是否紧急" :span="1"></el-table-column>-->
                <el-table-column prop="PRIORITY" label="优先级" :span="1" ></el-table-column>
                <el-table-column prop="SCOUT_PURPOSE" label="侦察目的" :span="3"></el-table-column>
                <!--<el-table-column prop="CREATE_TIME" label="接收时间" :span="5" ></el-table-column>-->
                <el-table-column prop="START_TIME" label="开始时间" :span="5" ></el-table-column>
                <el-table-column prop="END_TIME" label="结束时间" :span="5" ></el-table-column>
                <el-table-column prop="REPORT_TIME" label="上报时间间隔(s)" :span="3"></el-table-column>
                <!--<el-table-column prop="planned_times" label="已规划次数" :span="1"  :sortable="true" :sort-by="planned_times"></el-table-column>-->
                <!--<el-table-column prop="sensor_count" label="已使用传感器" :span="2" :sortable="true" :sort-by="sensor_count"></el-table-column>-->
                <!--<el-table-column prop="START_TIME" label="开始时间" :span="6" :sortable="true" :sort-by="START_TIME"></el-table-column>-->
                <!--<el-table-column prop="END_TIME" label="结束时间" :span="6" :sortable="true" :sort-by="END_TIME"></el-table-column>-->
                <el-table-column label="指标要求" :span="3">
                    <template slot-scope="scope">
                        <el-button class="my-btn" @click="selectTaskRow(scope.row)">查看</el-button>
                    </template>
                </el-table-column>
                <el-table-column label="实时状态" fixed="right" :span="3">
                    <template slot-scope="scope">
                        <el-button :type="taskStatusButtonColor[taskStatusArray[scope.row.TASK_STATUS]]" @click="toChart(scope.row.TASK_ID)">
                            {{taskStatusArray[scope.row.TASK_STATUS]}}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column label="是否删除" fixed="right" :span="3">
                    <template slot-scope="scope">
                        <el-button  @click="activateTaskDialog(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-col :span="22">
                <div class="paginationContent">
                    <el-pagination background
                                   layout="total,prev,pager,next"
                                   :current-page="currentPageTask"
                                   :total="totalCountTask"
                                   :page-size="pageSize"
                                   @current-change="currentPageChange"
                    >
                    </el-pagination>
                </div>
            </el-col>
            <el-col :span="2">
                <el-button style="margin-top:15px" @click="newTask">新建任务</el-button>
            </el-col>
            <!--<el-col :span="2">-->
                <!--<el-button  style="margin-top:15px">删除任务</el-button>-->
            <!--</el-col>-->
        </div>
        <el-dialog title="任务参数详情" :visible.sync="taskDialogVisible" width="70%">
            <el-table :data="taskParamList">
                <!--<el-table-column prop="IS_EMERGENCY" label="是否紧急" :span="5"></el-table-column>-->
                <!--<el-table-column prop="PRIORITY" label="优先级" :span="5"></el-table-column>-->
                <!--<el-table-column prop="SCOUT_PURPOSE" label="侦察目的" :span="5"></el-table-column>-->
                <!--<el-table-column prop="START_TIME" label="开始时间" :span="5" ></el-table-column>-->
                <!--<el-table-column prop="END_TIME" label="结束时间" :span="5" ></el-table-column>-->
                <el-table-column prop="USER_ID" label="提报用户" :span="4"></el-table-column>
                <el-table-column prop="DEMAND_RESOLUTION" label="分辨率要求" :span="2"></el-table-column>
                <el-table-column prop="DEMAND_PRECISION" label="精确度要求" :span="2"></el-table-column>
                <el-table-column prop="DEMAND_CONFIDENCE" label="置信度要求" :span="2"></el-table-column>
                <el-table-column prop="DEMAND_FREQUENCY" label="频域要求" :span="2"></el-table-column>
                <el-table-column prop="DEMAND_DURATION" label="持续性要求" :span="2"></el-table-column>
            </el-table>
        </el-dialog>
        <el-dialog title="新建任务" :visible.sync="newTaskDialogVisible" width="70%">
            <el-form ref="form" :model="createForm" labelWidth="140px">
                <el-form-item label="任务名称" >
                    <el-input v-model="createForm.TASK_NAME" style="width: 80%"></el-input>
                </el-form-item>
                <div style="display: flex">
                    <el-form-item label="任务类型">
                        <el-select v-model="createForm.SCOUT_PURPOSE" placeholder="请选择任务类型">
                            <!--<el-option label="搜索" value="SEARCH"></el-option>-->
                            <!--<el-option label="识别" value="IDENTIFY"></el-option>-->
                            <!--<el-option label="定位" value="LOCATE"></el-option>-->
                            <!--<el-option label="追踪" value="TRACE"></el-option>-->
                            <el-option v-for="item in selsectData"  :label="item.DICT_NAME" :value="item.DICT_CODE"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="侦察时间">
                        <el-date-picker
                                v-model="CREATE_TIME"
                                type="datetimerange"
                                range-separator="至"
                                start-placeholder="选择开始时间"
                                end-placeholder="选择结束时间">
                        </el-date-picker>
                    </el-form-item>
                </div>
                <el-form-item label="类型">
                    <el-radio-group v-model="TASK_TYPE" @change="selectType">
                        <el-radio label="根据目标位置" :key="0"></el-radio>
                        <el-radio label="根据目标区域" :key="1"></el-radio>
                        <el-radio label="根据目标Id" :key="2"></el-radio>
                        <!--<el-radio label="根据目标半径" :key="3"></el-radio>-->
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="目标位置" v-show="showLocation">
                    <!--<el-input v-model="createForm.targetList[0].TARGET_LOCATION" style="width: 50%"></el-input>-->
                    <el-input v-model="TARGETPOINT_LAT"  placeholder="请输入经度" style="width:20%"></el-input>
                    <el-input v-model="TARGETPOINT_LON" placeholder="请输入纬度" style="width: 20%;margin-left: 20px"></el-input>
                    <el-button style="margin-left: 10px;" @click="selectLocation">选取位置</el-button>
                    <el-button style="margin-left: 10px;" @click="previewLocation">预览位置</el-button>
                </el-form-item>
                <el-form-item label="目标区域" v-if="showArea">
                    <el-input v-model="TARGET_LAT" placeholder="请输入经度" style="width:20%"></el-input>
                    <el-input v-model="TARGET_LON" placeholder="请输入纬度" style="width: 20%;margin-left: 20px"></el-input>
                    <el-button style="margin-left: 10px;" @click="selectArea">选取区域</el-button>
                    <el-table :data="tableData" v-if="tableLocation" style="margin-top: 10px" class="locationTable">
                        <el-table-column prop="LON" label="经度">
                            <template slot-scope="scope">
                                <el-input ref="LON" v-model="scope.row.LON" style="width: 100%;" >
                                </el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="LAT" label="纬度">
                            <template slot-scope="scope">
                                <el-input ref="LAT" v-model="scope.row.LAT" style="width: 100%;" >
                                </el-input>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-button style="margin-left: 20px;margin-top: 10px;" @click="conformLocation">新增坐标</el-button>
                    <el-button  @click="delLocation">清空坐标</el-button>
                </el-form-item>
                <div style="display: flex;text-align: left">
                    <el-form-item label="目标" v-if="showTagertId">
                        <!--<el-input v-model="createForm.targetList[0].mbId" style="width: 50%"></el-input>-->
                        <el-select v-model="createForm.targetList[0].TARGET_ID" placeholder="请选择目标" @change="getTargetzbnm" style="width: 75%">
                            <el-option v-for="item in selectTaegetId" :label="item.zbmc" :value="item.zbnm"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="目标Id" v-if="showTagertId" style="margin-left:-15px">
                        <el-input v-model="createForm.targetList[0].mbId" style="width: 80%"></el-input>
                    </el-form-item>
                </div>
                <!--<el-form-item label="目标半径" v-if="showCirle">-->
                    <!--<el-input v-model="TARGETCircle_LAT" placeholder="请输入经度" style="width:20%"></el-input>-->
                    <!--<el-input v-model="TARGETCircle_LON" placeholder="请输入纬度" style="width: 20%;margin-left: 20px"></el-input>-->
                    <!--<el-input v-model="TARGET_CIRLE" placeholder="请输入半径" style="width: 20%;margin-left: 20px"></el-input>-->
                    <!--<el-button style="margin-left: 10px;" @click="selectCircle">绘制圆形</el-button>-->
                <!--</el-form-item>-->
                <div style="display: flex;">
                    <el-form-item label="升限" >
                        <el-input v-model="createForm.targetList[0].sysx" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="脉冲宽度">
                        <el-input v-model="createForm.targetList[0].mckd" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="脉冲重复间隔">
                        <el-input v-model="createForm.targetList[0].mccfjg" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="中心频率">
                        <el-input v-model="createForm.targetList[0].zxpl" style="width: 80%"></el-input>
                    </el-form-item>
                </div>
                <div style="display: flex;width: 100%;text-align: left">
                    <el-form-item label="雷达散射截面积" >
                        <el-input v-model="createForm.targetList[0].rcs" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="可见光特性:长度(米)">
                        <el-input v-model="createForm.targetList[0].zctx_kjgtx_cd" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="可见光特性:高度(米)">
                        <el-input v-model="createForm.targetList[0].zctx_kjgtx_gd" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="分辨率要求">
                        <el-input v-model="createForm.DEMAND_RESOLUTION" style="width: 80%"></el-input>
                    </el-form-item>
                    <!--<el-form-item label="脉冲宽度(um)" >-->
                        <!--<el-input v-model="createForm.targetList[0].mckd_ship" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                </div>
                <div style="display: flex;width: 100%;text-align: left">
                    <el-form-item label="精确度要求" >
                        <el-input v-model="createForm.DEMAND_PRECISION" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="置信度要求">
                        <el-input v-model="createForm.DEMAND_CONFIDENCE" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="频域要求">
                        <el-input v-model="createForm.DEMAND_FREQUENCY" style="width: 80%"></el-input>
                    </el-form-item>
                    <el-form-item label="持续性要求">
                        <el-input v-model="createForm.DEMAND_DURATION" style="width: 80%"></el-input>
                    </el-form-item>
                </div>
                <!--<div style="display: flex;width: 100%;text-align: left">-->
                    <!--<el-form-item label="脉冲重复间隔(us)" >-->
                        <!--<el-input v-model="createForm.targetList[0].mccfjg_ship" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="中心频率(kHz)" >-->
                        <!--<el-input v-model="createForm.targetList[0].zxpl_ship" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="雷达散射截面积(平方米)" >-->
                        <!--<el-input v-model="createForm.targetList[0].rcs_ship" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="多目标跟踪数" >-->
                        <!--<el-input v-model="createForm.targetList[0].dmbgzs" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                <!--</div>-->
                <!--<div style="display: flex;width: 100%;text-align: left">-->
                    <!--<el-form-item label="天线形式" >-->
                        <!--<el-input v-model="createForm.targetList[0].txxs" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="天线尺寸" >-->
                        <!--<el-input v-model="createForm.targetList[0].txcc" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="频率下限(MHz)" >-->
                        <!--<el-input v-model="createForm.targetList[0].plxx" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="频率上限(MHz)" >-->
                        <!--<el-input v-model="createForm.targetList[0].plsx" style="width: 100%"></el-input>-->
                    <!--</el-form-item>-->
                <!--</div>-->
                <el-form-item style="text-align: right">
                    <el-button @click="createSubmit">立即创建</el-button>
                    <el-button @click="cancelCreate">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-dialog title="删除任务" :visible.sync="delDialogVisible" width="60%">
            <p style="text-align: center;font-size: 20px;margin-bottom: 30px;margin-top: 30px;">是否确认删除?</p>
            <div style="text-align: right">
                <el-button @click="delTask">确认删除</el-button>
                <el-button @click="cancelDel">取消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import com_aside from './aside';
    import bus from '../../utils/eventbus.js';
    import {QWebChannel} from '../../utils/qwebchannel';
    export default {
        name: "taskManage",
        components:{
            com_aside
        },
        data:function () {
            return{
                START_TIME: "",
                IS_EMERGENCY: '0',
                TASK_STATUS: "",
                TASK_NAME: "",
                SCOUT_PURPOSE: "",
                USER_ID: "",
                sensor_count: '0',
                PRIORITY: "",
                END_TIME: "",
                planned_times: '0',
                INTEL_TIME: "",
                sensorList:[],
                CREATE_TIME: "",
                REPORT_TIME: '0',
                TASK_CODE: "",
                TASK_ID: '0',
                taskDataList:[
                    // {
                    //     START_TIME: "",
                    //     IS_EMERGENCY: 0,
                    //     TASK_STATUS: "",
                    //     TASK_NAME: "",
                    //     SCOUT_PURPOSE: "",
                    //     USER_ID: "",
                    //     sensor_count: 0,
                    //     PRIORITY: "",
                    //     END_TIME: "",
                    //     planned_times: 0,
                    //     INTEL_TIME: "",
                    //     sensorList:[],
                    //     CREATE_TIME: "",
                    //     REPORT_TIME: 0,
                    //     TASK_CODE: "",
                    //     TASK_ID: 0
                    // }
                    ],
                ruleDataList:[],
                taskParamList:[],
                flightDialogVisible:false,
                taskDialogVisible:false,
                ruleDialogVisible:false,
                newTaskDialogVisible:false,
                flightDataList:[],
                flightCapacityList:[],
                totalCountTask:-1,
                currentPageTask:1,
                pageSize:9,
                CREATE_TIME:['',''],
                TARGET_LAT:'',
                TARGET_LON:'',
                TARGET_CIRLE:'',
                TARGETPOINT_LAT:null,
                TARGETPOINT_LON:null,
                TARGETCircle_LAT:'',
                TARGETCircle_LON:'',
                createForm:{
                    TASK_NAME:'',
                    SCOUT_PURPOSE:'',
                    START_TIME:'',
                    END_TIME:'',
                    TARGET_LON:'',
                    TARGET_LAT:'',
                    DEMAND_RESOLUTION:'300',
                    DEMAND_PRECISION:'0.8',
                    DEMAND_CONFIDENCE:'0.75',
                    DEMAND_FREQUENCY:'10',
                    DEMAND_DURATION:'30',
                    targetList:[{
                        TARGET_ID:'',
                        TARGET_LOCATION:'',
                        sysx:'1',
                        mckd:'1',
                        mccfjg:'1',
                        zxpl:'300',
                        rcs:'1',
                        zctx_kjgtx_cd:'1',
                        zctx_kjgtx_gd:'1',
                        mbId:''
                    }]
                },
                delDialogVisible:false,
                tableData:[],
                tableLocation:false,
                TASK_TYPE:'',
                isEmergency:{0:'否',1:'是'},
                scoutPurpose:{'SEARCH':'搜索','IDENTIFY':'识别','LOCATE':'定位','TRACE':'追踪',
                                'AIRCRAFT_SCOUT':'航母周边潜艇侦察', 'AIR_SEARCH':'大范围空域搜索', 'PLANE_IDENTIFY':'飞机目标识别检测',
                                'PLANE_TRACK':'飞机目标跟踪交接', 'SEA_SEARCH':'大范围海域搜索', 'SHIP_IDENTIFY':'舰船目标识别检测',
                                'SHIP_LOCATE':'舰船目标定位', 'SHIP_TRACK':'舰船目标持续跟踪'},
                taskStatusArray:{0:'预处理',1:'待规划',2:'规划中',3:'待执行',4:'执行中',5:'已完成',6:'已取消',7:'执行失败'},
                taskStatusButtonColor:{
                    '预处理':'success',
                    '待规划':'primary',
                    '规划中':'waitplan',
                    '待执行':'info',
                    '已完成':'complete',
                    '执行中':'uncomplete',
                    '已取消':'cancel',
                    '执行失败':'danger',
                },
                subContent:[],
                result:[],
                notifiShow:{},
                targetViewId:1,
                selsectData:[],
                showLocation:false,
                showArea:false,
                showTagertId:false,
                showCirle:false,
                delTskId:[],
                selectTaegetId:[],
                messageData:[],
                targetLocation:''
            }
        },
        watch:{
            // targetViewId(newId,old){
            //     this.targetViewId = newId;
            //     console.log(newId)
            //     // this.getSubTasks(this.targetViewId);
            // }
        },
        methods: {
            selectFlightRow(row){
                let _this = this;
                let ability = []
                this.$get('/api/v1/rule/getRuleSensorType', {
                    ruleId: row.RULE_ID
                }).then((res) => {
                    console.log(res)
                    _this.flightCapacityList = res;
//                    for (let j = 0; j < _this.flightCapacityList.length; j++) {
//                        _this.flightCapacityList[j].activate = _this.canOrNot[_this.flightCapacityList[j].activate]
//                        if (_this.flightDataList[j].activate === 1) {
//                            _this.flightDataList[j].activate = '能'
//                        } else if (_this.flightDataList[j].activate === 0) {
//                            _this.flightDataList[j].activate = '不能'
//                        }
//                        for (let i = 0; i < _this.abilityArray.length; i++) {
//                            if (_this.flightCapacityList[j][_this.abilityArray[i].title] === 1) {
//                                ability.push(_this.abilityArray[i].ch_title)
//                            }
//                        }
//                        _this.flightCapacityList[j].activate = _this.canOrNot[_this.flightCapacityList[j].activate]
//                        _this.flightCapacityList[j].ability = ability.join(',')
//                        ability = []
//                    }
                })
                _this.flightDialogVisible = true
            },
            getTaskDynamicData(start, size){
                let _this = this;
                this.$get('/api/v1/taskWithSubTask/getTaskInfoDynamicAll', {start: start, size: size}).then((res) => {
                    if(res){
                        for(var i=0;i<res.dataList.length;i++){
                            res.dataList[i].IS_EMERGENCY = _this.isEmergency[res.dataList[i].IS_EMERGENCY];
                            res.dataList[i].SCOUT_PURPOSE = _this.scoutPurpose[res.dataList[i].SCOUT_PURPOSE];
                        }
                        _this.taskDataList = res.dataList;
                        // console.log(_this.taskDataList);
                        _this.totalCountTask = res.totalCount
                    }
                    // console.log( _this.taskDataList)
//                    for(let j=0;j<_this.taskDataList.length;j++){
//                        _this.taskDataList[j].IS_REPLAN = _this.isYesOrNo[_this.taskDataList[j].IS_REPLAN]
//                        _this.taskDataList[j].IS_EMERGENCY = _this.isYesOrNo[_this.taskDataList[j].IS_EMERGENCY]
//                        _this.taskDataList[j].IS_VALID = _this.isYesOrNo[_this.taskDataList[j].IS_VALID]
//                        _this.taskDataList[j].APPLY_RECALL = _this.applyRecallArray[_this.taskDataList[j].APPLY_RECALL]
//                        _this.taskDataList[j].task_status_name = _this.taskStatusArray[_this.taskDataList[j].task_status_name]
//                    }
                })
            },
            currentPageChange(current){
                // console.log('current-page:',current)
                let _this = this;
                let startItem = -1
                startItem = (current-1)*_this.pageSize+1;
                _this.getTaskDynamicData(startItem,_this.pageSize)
                _this.currentPageTask = current
                startItem = -1
            },
            // handleSelection (val){
            //     let _this = this
            //     console.log(val)
            //     // _this.multiTableSelection = val
            // },
            newTask(){
                this.newTaskDialogVisible = true;
                this.getRuleSensorTypeDic()
                this.getTargetId();
            },
            getRuleSensorTypeDic(){
                this.$get('api/v1/rule/getRuleSensorTypeDic',{dict_cat:'SCOUT_PURPOSE'}).then(res=>{
                    let _this = this;
                    _this.selsectData = res;
                })
            },
            getTargetId(){
                this.$get('api/v1/taskWithSubTask/tjwjwqzbfj').then(res=>{
                    let _this = this;
                    _this.selectTaegetId = res.results;
                    // console.log(_this.selectTaegetId)
                })
            },
            getTargetzbnm(val){
                console.log(val)
                this.selectTaegetId.forEach(item=>{
                    if(val==item.zbnm){
                        console.log(item)
                        // if(item.zblx=='1'){
                            this.createForm.targetList[0].sysx = item.sysx;
                            this.createForm.targetList[0].mckd = item.mckd;
                            this.createForm.targetList[0].mccfjg = item.mccfjg;
                            this.createForm.targetList[0].zxpl = item.zxpl;
                            this.createForm.targetList[0].rcs = item.rcs;
                        // }else if(item.zblx=='2'){
                            this.createForm.targetList[0].zctx_kjgtx_cd = item.zctx_kjgtx_cd;
                            this.createForm.targetList[0].zctx_kjgtx_gd = item.zctx_kjgtx_gd;
                        // }
                    }
                })
            },
            selectType(val){
                if(val=='根据目标位置'){
                    this.showLocation = true;
                    this.showArea = false;
                    this.showTagertId = false;
                    this.showCirle = false;
                    this.createForm.targetList[0].TARGET_ID = '';
                }else if(val=='根据目标区域'){
                    this.showLocation = false;
                    this.showArea = true;
                    this.showTagertId = false;
                    this.showCirle = false;
                    this.createForm.targetList[0].TARGET_ID = '';
                }else if(val=='根据目标Id'){
                    this.showLocation = false;
                    this.showArea = false;
                    this.showTagertId = true;
                    this.showCirle = false;
                    this.TARGET_LAT = '';
                    this.TARGET_LON = '';
                }
                // else if(val=='根据目标半径'){
                //     this.showLocation = false;
                //     this.showArea = false;
                //     this.showTagertId = false;
                //     this.showCirle = true;
                //     this.TARGET_LAT = '';
                //     this.TARGET_LON = '';
                // }
            },
            selectLocation(){
                if (location.search != "")
                    var baseUrl = (/[?&]webChannelBaseUrl=([A-Za-z0-9\-:/\.]+)/.exec(location.search)[1]);
                else
                    var baseUrl = "ws://28.4.30.165:12345";
                var socket = new WebSocket(baseUrl);
                let _this = this;
                socket.onopen = function() {
                    new QWebChannel(socket,(channel)=> {
                        window.dialog = channel.objects.dialog;
                        let text = '';
                        dialog.receivePosSelect(text);
                        dialog.sendPoint.connect(function(message) {
                            console.log(message)
                            _this.TARGETPOINT_LAT = message.split(',')[0];
                            _this.TARGETPOINT_LON = message.split(',')[1];
                        });
                    });
                }
            },
            previewLocation(){
                if (location.search != "")
                    var baseUrl = (/[?&]webChannelBaseUrl=([A-Za-z0-9\-:/\.]+)/.exec(location.search)[1]);
                else
                    var baseUrl = "ws://28.4.30.165:12345";
                var socket = new WebSocket(baseUrl);
                let _this = this;
                socket.onopen = function() {
                    new QWebChannel(socket,(channel)=> {
                        window.dialog = channel.objects.dialog;
                        let text = JSON.stringify({lon:_this.TARGETPOINT_LAT,lat:_this.TARGETPOINT_LON});
                        //let text = JSON.stringify({lon:_this.createForm.TARGET_LON,lat:_this.createForm.TARGET_LAT});
                        console.log(text)
                        dialog.receiveEditPos(text);
                    });
                }
            },
            selectArea(){
                try{
                    if (location.search != "")
                        var baseUrl = (/[?&]webChannelBaseUrl=([A-Za-z0-9\-:/\.]+)/.exec(location.search)[1]);
                    else
                        var baseUrl = "ws://28.4.30.165:12345";
                    var socket = new WebSocket(baseUrl);
                    let _this = this;
                    socket.onopen = function() {
                        new QWebChannel(socket,(channel)=> {

                            window.dialog = channel.objects.dialog;
                            let text = '';
                            dialog.receiveDrawPolyline(text);
                            dialog.sendRegion.connect(function(message) {
                                _this.tableData = new Array();
                                _this.targetLocation = "";
                                _this.createForm.targetList[0].TARGET_LOCATION = "";

                                _this.messageData = JSON.parse(message).data;
                                _this.messageData.forEach(item=>{
                                    _this.targetLocation += item.split(',')[0] + ',' + item.split(',')[1] +';';
                                    _this.createForm.targetList[0].TARGET_LOCATION = _this.targetLocation.substr(0,_this.targetLocation.length-1);
                                    _this.tableData.push({
                                        LON:item.split(',')[0],
                                        LAT:item.split(',')[1]
                                    });
                                });
                                _this.tableLocation = true;
                            });
                        });
                    }
                }catch (e) {
                    console.log(e)
                }

            },
            selectCircle(){
                if (location.search != "")
                    var baseUrl = (/[?&]webChannelBaseUrl=([A-Za-z0-9\-:/\.]+)/.exec(location.search)[1]);
                else
                    var baseUrl = "ws://28.4.30.165:12345";
                var socket = new WebSocket(baseUrl);
                let _this = this;
                socket.onopen = function() {
                    new QWebChannel(socket,(channel)=> {
                        window.dialog = channel.objects.dialog;
                        let text = JSON.stringify({lon:_this.TARGETCircle_LAT,lat:_this.TARGETCircle_LON,radius:_this.TARGET_CIRLE});
                        console.log(text)
                        dialog.receiveEditCircle(text);
                    });
                }
            },
            createSubmit(){
                this.newTaskDialogVisible = false;
                if(this.TARGETPOINT_LAT&&this.TARGETPOINT_LON){
                    this.createForm.targetList[0].TARGET_LOCATION = this.TARGETPOINT_LAT +','+this.TARGETPOINT_LON;
                }
                this.createForm.START_TIME = this.CREATE_TIME[0];
                this.createForm.END_TIME = this.CREATE_TIME[1];
                console.log(this.createForm)
                this.$post('api/v1/taskWithSubTask/saveTaskInfo',this.createForm).then(res=>{
                    // console.log(res)
                })
            },
            activateTaskDialog(row){
                this.delDialogVisible = true;
                this.delTskId = [];
                this.delTskId.push(row.TASK_ID);
            },
            delTask(){
                this.$post('api/v1/taskWithSubTask/deleteTaskInfo',this.delTskId).then(res=>{
                    if(res){
                        this.delDialogVisible = false;
                        this.getTaskDynamicData(this.currentPageTask-1,this.pageSize);
                    }
                })
            },
            cancelDel(){
                this.delDialogVisible = false;
            },
            conformLocation(){
                if(this.messageData.length>0){
                    if (location.search != "")
                        var baseUrl = (/[?&]webChannelBaseUrl=([A-Za-z0-9\-:/\.]+)/.exec(location.search)[1]);
                    else
                        var baseUrl = "ws://28.4.30.165:12345";
                    var socket = new WebSocket(baseUrl);
                    let _this = this;
                    socket.onopen = function() {
                        new QWebChannel(socket,(channel)=> {
                            window.dialog = channel.objects.dialog;
                            let text = JSON.stringify(_this.tableData);
                            dialog.receiveEditPolyline(text);
                        });
                    }
                }
                if(this.TARGET_LAT&&this.TARGET_LON){
                    this.tableData.push({
                        LON:this.TARGET_LAT,
                        LAT:this.TARGET_LON
                    })
                    this.createForm.targetList[0].TARGET_LOCATION += this.TARGET_LAT + ',' + this.TARGET_LON +';';
                    this.targetLocation += this.TARGET_LAT + ',' + this.TARGET_LON +';';
                    this.createForm.targetList[0].TARGET_LOCATION = this.targetLocation.substr(0,this.targetLocation.length-1);
                    this.tableLocation = true;
                }
            },
            delLocation(){
              this.tableData = [];
              this.TARGET_LAT = '';
              this.TARGET_LON = '';
              this.tableLocation = false;
            },
            cancelCreate(){
                let _this = this;
                _this.newTaskDialogVisible = false
            },
            toChart(id){
                // console.log(id)
                sessionStorage.setItem('taskId',id)
                this.$router.push({
                    name:'subtaskPage',
                    params:{
                        taskId:id
                    }
                })
            },
            getNewTask(taskId){
                this.$router.push({
                    name:'subtaskPage',
                    params:{
                        taskId:taskId
                    }
                })
                bus.$emit('taskId',taskId)
            },
            getNewTaskRefresh(){
                const h = this.$createElement
                this.$get('api/v1/taskWithSubTask/getNewTaskRefresh').then((res)=>{
                    if(res != null && res.length > 0){
                        this.result = res;
                        if(this.result[0].PARENT_ID=='0'&&res != null){
                            var offset = 0;
                            for(var i=0;i<this.result.length;i++){
                                this.notifiShow = this.$notify({
                                    title:'已接收新任务',
                                    dangerouslyUseHTMLString:true,
                                    message:h('p',null,[
                                        h('span',null,this.result[i].TASK_NAME + '' + this.result[i].TASK_ID),
                                        // h('span',null,'请前往任务管理页面查看详情'),
                                        h('a',{on:{click:this.getNewTask.bind(this,this.result[i].TASK_ID)}},'查看详情')
                                    ]),
                                    duration:0,
                                    type:'warning',
                                    offset:offset
                                })
                                offset +=70;
                            }
                        }
                    }
                })
            },
            selectTaskRow(row){
                // var start_time = new Date().getMilliseconds();
                let _this = this;
                _this.taskParamList= [];
                this.$get('/api/v1/taskWithSubTask/getTaskInfoStatic', {
                    taskId: row.TASK_ID
                }).then((res) => {
                    // res.IS_EMERGENCY = _this.isEmergency[res.IS_EMERGENCY];
                    // res.SCOUT_PURPOSE = _this.scoutPurpose[res.SCOUT_PURPOSE];
                    _this.taskParamList.push(res);
                });
                _this.taskDialogVisible=true;
                // var end_time = new Date().getMilliseconds();
                // console.log("open page cost " + (end_time - start_time) + "ms");
            }
        },
        mounted() {
            this.getTaskDynamicData(1,this.pageSize);
            let _this = this;
            //定时刷新任务列表状态
            setInterval(()=>{
              this.getNewTaskRefresh();
              if(_this.currentPageTask>=1){
                  let startItem = (_this.currentPageTask-1)*_this.pageSize+1;
                  _this.getTaskDynamicData(startItem,_this.pageSize);
              }
            },3000);
//            if(this.$route.params){
//                this.flightDataList = this.$route.params.dataCon;
//            }
            this.createForm.SCOUT_PURPOSE = "SEA_SEARCH";
            this.createForm.TASK_NAME = "搜索南海区域_1";
            this.CREATE_TIME[0] = new Date();
            this.CREATE_TIME[1] = new Date(new Date().getTime() + 86400000);
            this.selectType('根据目标Id');
        }
    }
</script>

<style lang="scss" >
    #app .layout_body .layout_main {
         padding-left: 0;
    }
    .el-notification__content{
        font-size: 18px;
        a{
            text-decoration: underline;
            cursor: pointer;
        }
    }
    .com_aside{
        top: 0;
    }
    .el-table .cell{
        max-height: 80px!important;
        padding-left: 0!important;
    }
    .el-table_body-wrapper{
        overflow: auto;
    }
    .el-table th.is-leaf, .el-table td{
        text-align: center;
    }
    .el-table thead tr{
        height: 50px!important;
    }
    .el-table tr{
        height: 80px;
    }
    .el-button{
        border-radius: 5px;
    }
    .manageInfo{
        position: relative;
        /*width: 100%;*/
        height: 100%;
        width: calc(100vw);
        overflow: auto;
        .right-table{
            height: 100%;
            margin-left: 68px;
        }
        .el-table{
            /*margin-left: 68px;*/
        }
    }
    .locationTable  tr{
        height: 50px;
    }
    .tableviewContent{
        margin-top: 14px;
        margin-left: 70px;
        height: calc(100% - 150px);
        overflow-y: auto;
        overflow-x: auto;
    }
    .paginationContent{
        margin-top:20px;
        height: 50px;
    }
    .el-menu {
        /*border-right: solid 1px #e6e6e6;*/
        list-style: none;
        position: relative;
        margin: 0;
        padding-left: 0;
        background-color: #001e28; }
    .el-menu::before,
    .el-menu::after {
        display: table;
        content: ""; }
    .el-menu::after {
        clear: both; }
    .el-menu.el-menu--horizontal {
        border-bottom: none!important;
    }
    .el-menu--horizontal {
        border-right: none; }
    .el-menu--horizontal > .el-menu-item {
        float: left;
        height: 60px;
        line-height: 60px;
        margin: 0;
        font-size: 18px;
        /*border-bottom: 2px solid transparent;*/
        color: #FFFFFF;}
    .el-menu--horizontal > .el-menu-item a,
    .el-menu--horizontal > .el-menu-item a:hover {
        color: inherit; }
    .el-menu--horizontal > .el-menu-item:not(.is-disabled):hover, .el-menu--horizontal > .el-menu-item:not(.is-disabled):focus {
        background-color: #0050b3; }
    .el-menu--horizontal > .el-menu-item:not(.is-disabled):hover, .el-menu--horizontal > .el-menu-item:not(.is-disabled):hover {
        background-color: #0050b3; }
    .el-menu--horizontal > .el-submenu {
        float: left; }
    .el-menu--horizontal > .el-submenu:focus, .el-menu--horizontal > .el-submenu:hover {
        outline: none;
        background-color: #0050b3;}
    .el-menu--horizontal > .el-submenu:focus .el-submenu__title, .el-menu--horizontal > .el-submenu:hover .el-submenu__title {
        color: #ffffff;
        background-color: #0050b3;}
    .el-menu--horizontal > .el-submenu.is-active .el-submenu__title {
        border-bottom: 2px solid #0d3449;
        color: #ffffff; }
    .el-menu--horizontal > .el-submenu .el-submenu__title {
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        font-size: 18px;
        color: #FFFFFF; }
    .el-menu--horizontal > .el-submenu .el-submenu__title:hover {
        background-color: #0050b3; }
    .el-menu--horizontal > .el-submenu .el-submenu__icon-arrow {
        position: static;
        vertical-align: middle;
        margin-left: 8px;
        margin-top: -3px; }
    .el-menu--horizontal .el-menu .el-menu-item,
    .el-menu--horizontal .el-menu .el-submenu__title {
        background-color: #0d3449;
        float: none;
        height: 50px;
        font-size: 15px;
        line-height: 50px;
        padding: 0 10px;
        color: #dddddd; }
    .el-menu--horizontal .el-menu .el-submenu:hover{
        background-color: #0050b3;
    }
    .el-menu--horizontal .el-menu .el-submenu .el-submenu__title:hover{
        color: #ffffff;
        background-color: #0050b3;
    }
    .el-menu--horizontal .el-menu .el-menu-item.is-active,
    .el-menu--horizontal .el-menu .el-submenu.is-active > .el-submenu__title {
        color: #ffffff;
        background-color: #0050b3;
    }
    .el-menu--horizontal .el-menu .el-submenu > .el-submenu__title:hover {
        color: #ffffff;
        background-color: #0050b3;
    }
    .el-menu-item:hover{
        background-color: #0050b3!important;
    }
    .el-menu--horizontal .el-menu-item:not(.is-disabled):hover,
    .el-menu--horizontal .el-menu-item:not(.is-disabled):focus {
        outline: none;
        color: #ffffff; }
    .el-menu--horizontal > .el-menu-item.is-active {
        border-bottom: 2px solid #0d3449;
        color: #ffffff; }
    .el-menu--collapse {
        width: 100px; }
    .el-menu--collapse > .el-menu-item [class^="el-icon-"],
    .el-menu--collapse > .el-submenu > .el-submenu__title [class^="el-icon-"] {
        margin: 0;
        vertical-align: middle;
        width: 24px;
        text-align: center; }
    .el-menu--collapse > .el-menu-item .el-submenu__icon-arrow,
    .el-menu--collapse > .el-submenu > .el-submenu__title .el-submenu__icon-arrow {
        display: none; }
    .el-menu--collapse > .el-menu-item span,
    .el-menu--collapse > .el-submenu > .el-submenu__title span {
        height: 0;
        width: 0;
        overflow: hidden;
        visibility: hidden;
        display: inline-block; }
    .el-menu--collapse > .el-menu-item.is-active i {
        color: inherit; }
    .el-menu--collapse .el-menu .el-submenu {
        min-width: 200px; }
    .el-menu--collapse .el-submenu {
        position: relative; }
    .el-menu--collapse .el-submenu .el-menu {
        position: absolute;
        margin-left: 5px;
        top: 0;
        left: 100%;
        z-index: 10;
        border: 1px solid #2c4b5d;
        border-radius: 0px;
        -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); }
    .el-menu--collapse .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow {
        -webkit-transform: none;
        transform: none; }
    .el-menu--collapse .el-submenu.is-opened > .el-submenu__title:hover{
        background-color: #0b987f;
    }
    .el-menu--popup {
        z-index: 100;
        min-width: 200px;
        border: none;
        padding: 5px 0;
        border-radius: 0px;
        -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); }
    .el-menu--popup-bottom-start {
        margin-top: 5px; }
    .el-menu--popup-right-start {
        margin-left: 5px;
        margin-right: 5px; }

    .el-menu-item {
        height: 56px;
        line-height: 56px;
        font-size: 20px;
        color: #00ffff;
        min-width: 120px;
        padding: 0 40px;
        list-style: none;
        cursor: pointer;
        position: relative;
        -webkit-transition: border-color .3s, background-color .3s, color .3s;
        transition: border-color .3s, background-color .3s, color .3s;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        white-space: nowrap; }
    .el-menu-item * {
        vertical-align: middle;
        horiz-align: center}
    .el-menu-item i {
        color: #dddddd; }
    .el-menu-item:hover, .el-menu-item:focus {
        outline: none;
        background-color: #0595ab; }
    .el-menu-item.is-disabled {
        opacity: 0.25;
        cursor: not-allowed;
        background: none !important; }
    .el-menu-item [class^="el-icon-"] {
        margin-right: 5px;
        width: 24px;
        text-align: center;
        font-size: 18px;
        vertical-align: middle; }
    .el-menu-item.is-active {
        color: #0d3449; }
    .el-menu-item.is-active i {
        color: inherit; }

    .el-submenu {
        list-style: none;
        margin: 0;
        padding-left: 0; }
    .el-submenu__title {
        height: 56px;
        line-height: 56px;
        font-size: 20px;
        min-width: 120px;
        color: #00ffff;
        padding: 0 40px;
        list-style: none;
        cursor: pointer;
        position: relative;
        -webkit-transition: border-color .3s, background-color .3s, color .3s;
        transition: border-color .3s, background-color .3s, color .3s;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        white-space: nowrap; }
    .el-submenu__title * {
        vertical-align: middle; }
    .el-submenu__title i {
        color: #dddddd; }
    .el-submenu__title:hover, .el-submenu__title:focus {
        outline: none;
        background-color: #0595ab; }
    .el-submenu__title.is-disabled {
        opacity: 0.25;
        cursor: not-allowed;
        background: none !important; }

    .el-submenu .el-menu {
        border: none; }
    .el-submenu .el-menu-item {
        height: 50px;
        line-height: 50px;
        padding: 0 45px;
        font-size: 15px;
        min-width: 250px; }
    .el-submenu .el-menu-item:hover{
        background-color: #1d39c4;
    }
    .el-submenu__icon-arrow {
        position: absolute;
        top: 50%;
        right: 20px;
        margin-top: -7px;
        -webkit-transition: -webkit-transform .3s;
        transition: -webkit-transform .3s;
        transition: transform .3s;
        transition: transform .3s, -webkit-transform .3s;
        font-size: 12px; }
    .el-submenu.is-active .el-submenu__title {
        border-bottom-color: #0d3449; }
    .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow {
        -webkit-transform: rotateZ(180deg);
        transform: rotateZ(180deg); }
    .el-submenu.is-disabled .el-submenu__title,
    .el-submenu.is-disabled .el-menu-item {
        opacity: 0.25;
        cursor: not-allowed;
        background: none !important; }
    .el-submenu [class^="el-icon-"] {
        vertical-align: middle;
        margin-right: 5px;
        width: 24px;
        text-align: center;
        font-size: 18px; }

    .el-menu-item-group > ul {
        padding: 0; }

    .el-menu-item-group__title {
        padding: 7px 0 7px 20px;
        line-height: normal;
        font-size: 12px;
        color: #dddddd; }

    .horizontal-collapse-transition .el-submenu__title .el-submenu__icon-arrow {
        -webkit-transition: .2s;
        transition: .2s;
        opacity: 0; }

    .el-button{
        border-radius: 5px;
    }
    .el-button--cancel{
        border-radius: 5px;
        background: #ff7a45;
        border: 1px solid #ff7a45;
        color: #ffffff;
    }
    .el-button--cancel:hover, .el-button--cancel:focus {
        color: #ffffff;
        border-color: #ff7a45;
        background-color: #ff7a45;
    }
    .el-button--cancel:active {
        color: #ffffff;
        border-color: #fa541c;
        background-color: #fa541c;
        outline: none;
    }

    .el-button--success{
        border-radius: 5px;
        background: #409eff;
        border: 1px solid #409eff;
        color: #ffffff;
    }
    .el-button--success:hover, .el-button--success:focus {
        color: #ffffff;
        border-color: #409eff;
        background-color: #409eff;
    }
    .el-button--success:active {
        color: #ffffff;
        border-color: #409eff;
        background-color: #409eff;
        outline: none;
    }
    .el-button--danger {
        border-radius: 5px;
        background: #cf1322;
        border: 1px solid #cf1322;
        color: #ffffff;
    }
    .el-button--danger:hover, .el-button--danger:focus {
        color: #ffffff;
        border-color: #cf1322;
        background-color: #cf1322;
    }
    .el-button--danger:active {
        color: #ffffff;
        border-color: #a8071a;
        background-color: #a8071a;
        outline: none;
    }
    .el-button--primary {
        border-radius: 5px;
        background: #230af9;
        border: 1px solid #230af9;
        color: #ffffff;
    }
    .el-button--primary:hover, .el-button--primary:focus {
        color: #ffffff;
        border-color: #230af9;
        background-color: #230af9;
    }
    .el-button--primary:active {
        color: #ffffff;
        border-color: #230af9;
        background-color: #230af9;
        outline: none;
    }
    .el-button--info {
        color: #ffffff;
        background-color: #3eb388;
        border-color: #3eb388;
    }
    .el-button--info:hover, .el-button--info:focus {
        color: #ffffff;
        background-color: #3eb388;
        border-color: #3eb388;
    }
    .el-button--info:active {
        background: #3eb388;
        border-color: #3eb388;
        color: #ffffff;
        outline: none;
    }
    .el-button--info.is-active {
        background: #3eb388;
        border-color: #3eb388;
        color: #ffffff;
        outline: none;
    }

    .el-button--complete {
        color: #ffffff;
        background-color: #8c8c8c;
        border-color: #8c8c8c;
    }
    .el-button--complete:hover, .el-button--complete:focus {
        background: #8c8c8c;
        border-color: #8c8c8c;
        color: #ffffff;
    }
    .el-button--complete:active {
        background: #8c8c8c;
        border-color: #8c8c8c;
        color: #ffffff;
        outline: none;
    }
    .el-button--waitplan {
        color: #ffffff;
        background-color: #ded349;
        border-color: #ded349;
    }
    .el-button--waitplan:hover, .el-button--waitplan:focus {
        background: #ded349;
        border-color: #ded349;
        color: #ffffff;
    }
    .el-button--waitplan:active {
        background: #fabd14;
        border-color: #fabd14;
        color: #ffffff;
        outline: none;
    }

    .el-button--uncomplete {
        color: #ffffff;
        background-color: #52c41a;
        border-color: #52c41a;
    }
    .el-button--uncomplete:hover, .el-button--complete:focus {
        background: #52c41a;
        border-color: #52c41a;
        color: #ffffff;
    }
    .el-button--uncomplete:active {
        background: #52c41a;
        border-color: #52c41a;
        color: #ffffff;
        outline: none;
    }
</style>