<template>
    <main>
        <!-- <h1> 결과 페이지 입니다.</h1> -->
        <h1>당신의 목표 달성을 MYFIT이 응원합니다!</h1>
        <div class="row">
            <div class="col-sm-4">
                <div class="card-body">
                    <h3 class="card-title">아침</h3>
                    <div class="food-item">
                        <h5 class="card-text">{{ store.result[0].foodList.split("'")[1] }}</h5>
                        <h5 class="card-text">{{ store.result[0].foodList.split("'")[3] }}</h5>
                        <h5 class="card-text">{{ store.result[0].foodList.split("'")[5] }}</h5>
                    </div>
                    <p class="card-text">총 열량 : {{ store.result[0].calories }} kcal</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card-body">
                    <h3 class="card-title">점심</h3>
                    <div class="food-item">
                        <h5 class="card-text">{{ store.result[1].foodList.split("'")[1] }}</h5>
                        <h5 class="card-text">{{ store.result[1].foodList.split("'")[3] }}</h5>
                        <h5 class="card-text">{{ store.result[1].foodList.split("'")[5] }}</h5>
                    </div>
                    <p class="card-text">총 열량 : {{ store.result[1].calories }} kcal</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card-body">
                    <h3 class="card-title">저녁</h3>
                    <div class="food-item">
                        <h5 class="card-text">{{ store.result[2].foodList.split("'")[1] }}</h5>
                        <h5 class="card-text">{{ store.result[2].foodList.split("'")[3] }}</h5>
                        <h5 class="card-text">{{ store.result[2].foodList.split("'")[5] }}</h5>
                    </div>
                    <p class="card-text">총 열량 : {{ store.result[2].calories }} kcal</p>
                </div>
            </div>
        </div>
        <h2>건강한 식단과 함께 이런 운동은 어때요?</h2>
        <RecommandList></RecommandList>
    </main>
</template>

<script setup>
    import { useDietStore } from '@/stores/diet';
    import { useVideoStore } from '@/stores/video';
    import { ref, onMounted, onBeforeMount } from 'vue';
    import RecommandList from '../common/RecommandList.vue';
    import router from '@/router';
    
    const store = useDietStore();
    const videoStore = useVideoStore();

    onBeforeMount(async ()=>{
        await store.getDiets();
        await videoStore.getRecommandVideo(store.result[2].purpose);
    })
    // onUpdated(()=>{
    //     videoStore.getRecommandVideo(store.result[2].purpose);
    // })

    // 결과보기 페이지에 들어 왔다면, 식단 추천 DB를 불러오기
    
</script>

<style scoped>
    *{
        font: var(--sm-pre-font);
    }
    main{
        padding: 48px;
    }
    h1{
        font: var(--xl-pre-font);
    }
    h2{
        padding: 24px;
        font: var(--lg-pre-font);
        text-align: center;
    }
    h3{
        font: var(--lg-pre-font);
        color: white;
        /* padding: 0 16px; */
    }
    h5{
        font: var(--base-pre-font);
        text-align: center;
    }
    
    p {
        font-weight: bold;
        text-align: center;
        margin-top: 16px;
    }

    .card {
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .card-body {
        padding: 0 16px 8px;
        border-radius: 10px;
        border: 3px solid var(--main-color);
        background: url('../../assets/img/bg-diet.jpg') center;
        background-size: cover;
    }

    .food-item {
        border-bottom: 1px solid #eee;
        padding: 8px 0;
        background-color: rgba(255, 255, 255, 0.7);
        border-radius: 10px;
    }

    .food-item:last-child {
        border-bottom: none;
    }
</style>