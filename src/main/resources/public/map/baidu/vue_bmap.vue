<template>
  <div id="allmap" ref="mymap"></div>
</template>

<style scoped>
  #allmap {
    width: 100%;
    height: calc(100vh - 50vw);
    margin-bottom: 9.3333vw;
  }
  .map-wrap /deep/ .anchorBL{
    display: none;
  } 
  

</style>

<script>
import BMap from 'BMap'
import loc  from '../../assets/image/organization/location.png'

export default {
  mounted(){
       this.initMap()
  },
  data () {
    return {
      center: {
        lng: 113.284,
        lat: 23.125
      },
      locationIcon: {
        url: loc,
        size: {width: 18, height: 18}
      },
      // 附近的人
      aroundList: [ 
        { 
          point: "113.264531|23.157003",
          userName: "1",
          atlas: loc,
          userId: '0'
        },
        { 
          point: "113.330934|23.113401",
          userName: "2",
          atlas: loc,
          userId: '1'
        },
        { 
          point: "113.310854|23.113605",
          userName: "3",
          atlas: loc,
          userId: '2'
        }
      ],
    }
  },
  methods: {
    initMap: function() {
      this.createMap()
      this.addMapControl()
      this.addAround(this.aroundList)
    },
    // 创建地图
    createMap: function() {
      var map = new BMap.Map(this.$refs.mymap)
      var point = new BMap.Point(this.center.lng,this.center.lat)
      map.centerAndZoom(point,15)
      window.map = map
    },
    // 添加控件
    addMapControl(){
      //向地图中添加缩放控件
      var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,type:BMAP_NAVIGATION_CONTROL_LARGE});
      map.addControl(ctrl_nav);
      // 创建当前用户标注
      var ctrl_marker = new BMap.Point(this.center.lng,this.center.lat)
      var myIcon = new BMap.Icon(loc,new BMap.Size(40,49))
      var marker_one = new BMap.Marker(ctrl_marker,{icon:myIcon})
      map.addOverlay(marker_one)
      // 创建一个圆
      var circle = new BMap.Circle(ctrl_marker,500,{strokeColor:"#f3c6b1",fillColor:"#f3c6b1", strokeWeight:1, strokeOpacity:0.5})
      map.addOverlay(circle); 
    },
    // 添加一个位置标记点
    addMarker: function(point){
      var marker = new BMap.Marker(point)
      map.addOverlay(marker,15)
    },
    //  加载附近的人信息
    addAround: function(markerList) {
      if(markerList.length>0){
        for(var i=0;i<markerList.length;i++){
          // 经度
          var info1 = markerList[i].point.split('|')[0] 
          // 纬度
          var info2 = markerList[i].point.split('|')[1]
          // 绘制附近人点坐标
          var pointA = new BMap.Point(this.center.lng,this.center.lat)
          var point = new BMap.Point(info1,info2)
          // 距离
          var path = map.getDistance(pointA, point).toFixed(2)
          var myIcon = new BMap.Icon(markerList[i].atlas,new BMap.Size(40,49))
          var marker = new BMap.Marker(point,{icon:myIcon})
          map.addOverlay(marker)
          //悬浮提示信息
          var content = { 
            userName:markerList[i].userName,
            atlas: markerList[i].atlas,
            userId: markerList[i].userId,
            distance: path
          }
          map.addOverlay(marker)
          this.addClickHandler(content,marker)
        }
      } else{
        return 
      }
    },
    //点击提示信息
    addClickHandler: function(content,marker){
      var _this = this
      var sContent = `<div id="ssst" style="display: flex;flex-direction: row;align-items: center;width: 50.3333vw;">
          <img src="${content.atlas}" alt="" style="width: 12.5333vw;height: 12.5333vw;border-radius: 50%;margin-right: 2.2667vw;">
          <div>
            <p style="font-size: 4vw;">${content.userName}</p>
            <p style="font-size: 3.2vw;color: #999;margin-top: 1.3333vw;">距你${content.distance}m</p>
          </div>
          </div>`
      var userid = content.userId
      var infoWindow = new BMap.InfoWindow(sContent)
      marker.addEventListener("click",function(e){
        e.preventDefault = true
        e.stop
        this.openInfoWindow(infoWindow)
        setTimeout(() => {
          e.preventDefault = true
          e.stop
          _this.chatWith(userid)
        }, 0);
      })
      
    },
    // 弹窗点击跳转事件
    chatWith(userid){
      var _this = this
      var ssst = document.getElementById('ssst')
      ssst.addEventListener('click',function() {
        _this.$router.push({
          path: '/home',
          query: {
            userId: userid
          }
        })
      })
    }
  }
}
</script>