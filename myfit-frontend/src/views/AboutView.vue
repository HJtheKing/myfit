<template>
    <main>
        <div class="wrapper">
        <div>
          <img src="../assets/img/img-workout.jpg" class="d-block" alt="main img 1" />
            <h5>오직 당신을 위한 트레이너, MYFIT이 추천하는 운동!</h5>
        </div>
        <div>
          <img src="../assets/img/img-recommand.jpg" class="d-block" alt="main img 2" />
            <h5>체성분 분석 결과를 바탕으로한 운동 추천 기능과</h5>
        </div>
        <div>
          <img src="../assets/img/img-diet.jpg" class="d-block" alt="main img 3" />
            <h5>당신의 목적과 기호에 맞춘 식단 추천 기능을 만나보세요!</h5>
        </div>
      </div>
    </main>
</template>

<script setup>

</script>

<style scoped>
    main{
        padding-bottom: 70px;
    }
    .wrapper{
        display: flex;
        flex-direction: column;
        position: relative;
    }
    .carousel-indicators{
        margin-bottom: 0;
    }
    div{
        margin: 0;
        position: relative;
    }
    img{
        position: relative;
        aspect-ratio: 3;
        margin: 0 auto;
        width: 100%;
        height: 100%;
    }
    h5{
        margin: 0;
        position: absolute;
        transform: translate(-50%, 0);
        left: 50%;
        top: 300px;
        z-index: 5;
        color: black;
        width: fit-content;
        margin: 0 auto;
        padding: 8px 16px;
        font: var(--lg-pre-font);
        background-color: rgba(255, 255, 255, 0.7);
        border-radius: 8px;
    }
</style>