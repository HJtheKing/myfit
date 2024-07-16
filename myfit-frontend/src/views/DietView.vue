<template>
    <RouterView></RouterView>
</template>

<script setup>
    import router from '@/router';
    import { useDietStore } from '@/stores/diet';
    import { onMounted } from 'vue';
    
    const store = useDietStore();
</script>

<style scoped>
    
</style>