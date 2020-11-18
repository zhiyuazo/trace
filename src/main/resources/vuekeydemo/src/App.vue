<template>
  <div id="app">
    <!-- comment it, due to it's demon code
    <img alt="Vue logo" src="./assets/logo.png">
    -->
    <!--
    <select v-model="wantLink">
      <option></option>
      <option>home</option>
      <option>router1</option>
      <option>router2</option>
    </select>
    <button v-on:click="selectURL">确定</button>
    -->
    <router-link to="/home"></router-link>
    <router-view></router-view>

  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  data:function(){
    return {
      currentLink:'',
      wantLink:'',
    }
  },
  methods:{
    selectURL:function(){
      this.currentLink ='/' + this.wantLink;
      console.log(this.currentLink)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
