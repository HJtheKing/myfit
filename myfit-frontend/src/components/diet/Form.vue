<template>
    <main>
        <h1>식단 추천 페이지</h1>
        <article>
            <h3>Q1. 식단이 필요한 이유는 무엇인가요?</h3>
            <div class="row">
                <div class="col-sm-4" @click="select('Bulkup')">
                    <div class="card bulkup justify-content-end" :class="{selected: purpose === 'Bulkup'}">
                        <button class="btn btn-dark">근육 키우기</button>
                    </div>
                </div>
                <div class="col-sm-4" @click="select('Diet')">
                    <div class="card diet justify-content-end" :class="{selected: purpose === 'Diet'}">
                            <button class="btn btn-dark">체중 감량</button>
                    </div>
                </div>
                <div class="col-sm-4" @click="select('Healthy')">
                    <div class="card healthy justify-content-end" :class="{selected: purpose === 'Healthy'}">
                        <button class="btn btn-dark">건강 유지</button>
                    </div>
                </div>
            </div>
        </article>
        <article>
            <h3>Q2. 알레르기 유발 식품이 있다면 작성해주세요.</h3>
            <input type="text" placeholder="알레르기 유발 식품" v-model="allergie">
        </article>
        <article>
            <h3>Q3. 그 외에 기피하는 음식이 있다면 작성해주세요.</h3>
            <input type="text" placeholder="싫어하는 음식" v-model="pastMeal">
        </article>
        <button class="btn please" @click="recommand"> 식단 추천 받기 </button>
        
    </main>
    <LoadingSpinner v-if="isLoading"></LoadingSpinner>
</template>

<script setup>
    import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
    import router from '@/router';
    import axios from 'axios'
    import { ref } from 'vue'

    const purpose = ref("");
    const select = function(item){
        purpose.value = item
        // console.log(purpose.value)
    }
    const allergie = ref("");
    const pastMeal = ref("");
    const isLoading = ref(false)

    const recommand = function(){
        isLoading.value = true
        if(purpose.value){
            if(!allergie.value){
                allergie.value = "없음"
            }
            if(!pastMeal.value){
                pastMeal.value = "없음"
            }
            axios.post("http://localhost:8080/api-diet/diet", {
                memberId: sessionStorage.getItem("memberId"),
                purpose: purpose.value,
                allergie: allergie.value,
                pastMeal: pastMeal.value
            })
            .then((response) => {
                router.push({name: 'result'})
                
            })
            .catch((err) => {
                isLoading.value=false
            })
        }else{
            isLoading.value=false
            alert("식단이 필요한 이유를 선택해야 합니다!")
        }
    }
</script>

<style scoped>
    * {
        font: var(--sm-pre-font);
    }

    main {
        overflow-y: scroll;
        box-sizing: border-box;
        padding: 24px 48px 94px;
    }

    h1 {
        font: var(--xl-pre-font);
    }

    h3 {
        font: var(--lg-pre-font);
        margin-top: 8px;
    }
    h5 {
        font: var(--base-pre-font);
    }

    .card{
        box-sizing: border-box;
        border: 5px solid #ffffff;
        width: 100%;
        aspect-ratio: 4/3;
        border: 0;
        cursor: pointer;
    }

    .bulkup{
        background-image: url("@/assets/img/btn-bulkup.jpg");
        background-size: 100%;
    }
    .diet{
        background-image: url("@/assets/img/btn-diet.jpg");
        background-size: 100%;
    }
    .healthy{
        background-image: url("@/assets/img/btn-healthy.jpg");
        background-size: 100%;
    }
    .btn{
        display: block;
        margin: 40% auto 0;
        width: 100%;
    }

    input[type="text"] {
        display: block;
        margin: 16px auto;
        border: 1px black solid;
        border-radius: 8px;
        width: 420px;
        height: 48px;
        padding: 0 20px;
        font: var(--lg-pre-font);
    }

    .please{
        display: block;
        margin: 0 auto;
        padding: 16px 48px;
        font:var(--lg-pre-font);
        background-color: var(--main-color);
        color: white;
    }

    .selected {
        border: 5px solid var(--action-color);
    }
</style>