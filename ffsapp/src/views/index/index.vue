<template>
<transition  enter-active-class="animated rollln"
   leave-active-class="animated rollOut"
   :duration="200">
    <router-view></router-view>

</transition></template>

<script>

export default {
  name: 'app',

}
</script>

<style>

</style>
