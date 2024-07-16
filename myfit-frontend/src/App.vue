<script setup>
  import Header from '@/components/common/Header.vue'
  import Footer from '@/components/common/Footer.vue'
</script>

<template>
  <Header></Header>

  <RouterView />

  <Footer></Footer>
</template>

<style>
body{
  background-color: #f8f8f8;
  margin-left: 5px;
}
body > div{
  position: relative;
  overflow: hidden;
}
main {
  position: relative;
  max-width: 1200px;
  height: fit-content;
  min-height: calc( 100vh - 120px );
  box-sizing: border-box;
  padding: 0 24px;
  margin: 0 auto;
  padding-bottom: 86px;
  background-color: white;
}
</style>
