<template>
    <el-container>
        <el-aside width="200px"><Aside></Aside></el-aside>
        <el-container>
            <el-header><Head></Head></el-header>
            <el-main><Main></Main></el-main>
            <el-footer><Footer></Footer></el-footer>
        </el-container>
    </el-container>
</template>

<script>
import Head from './layout/Head.vue'
import Aside from './layout/Aside.vue'
import Main from './layout/Main.vue'
import Footer from './layout/Footer.vue'

export default {
    props:{
        msg:String
    },
    components : {
        Head,
        Aside,
        Main,
        Footer
    }
}
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    padding-top:10px;
    padding-bottom:10px;
    height:200px !important;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 10px;
  }
</style>