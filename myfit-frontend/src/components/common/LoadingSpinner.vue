<template>
    <div v-if="isLoading" class="loading-spinner">
        <div class="spinner"></div>
    </div>
</template>
  
<script setup>
    import { ref } from 'vue';

    defineProps({
      isLoading: Object,
      required: true
    })

    const isLoading = ref(true);
</script>
  
  <style scoped>
  .loading-spinner {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1;
  }
  
  .spinner {
    border: 16px solid #f3f3f3;
    border-top: 16px solid var(--action-color);
    border-radius: 50%;
    width: 120px;
    height: 120px;
    animation: spin 2s linear infinite;
  }
  
  @keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
  }
  </style>
  