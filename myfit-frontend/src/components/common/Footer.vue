<template>
    <footer>
        <div class="upper">
            <img src="@/assets/img/logo.png" alt="MYFIT Logo" class="logo"/>
        </div>
        <h5>&copy; 2024 박창빈, 최현준</h5>
        <ul class="nav justify-content-end align-items-center lower">
            <li class="nav-item">
                전체 서비스
            </li>
            <li class="nav-item">
                이용약관
            </li>
            <li class="nav-item">
                개인정보처리방침
            </li>
            <li class="nav-item">
                고객센터
            </li>
        </ul>
    </footer>
</template>

<script setup>

</script>

<style scoped>
    footer{
        position: absolute;
        bottom: 0;
        transform: translate(-50%, 0);
        left: 50%;
        width: 100%;
        max-width: 1200px;
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        background-color: var(--surface-color);
        /* height: 120px; */
        box-sizing: border-box;
        padding: 16px 48px;
    }
    .upper{
        display: flex;
        flex-direction: column;
        justify-content: center;
        flex-wrap: nowrap;
    }
    .logo{
        width: 160px;
    }
    h5{
        margin-top: 10px;
        text-align: center;
        color: white;
        font: var(--sm-pre-font);
    }
    .nav-item{
        margin-left: 10px;
        text-align: center;
        color: white;
        font: var(--sm-pre-font);
    }
    @media (max-width: 620px) {
        footer{
            justify-content: center;
        }
        .lower{
            display: none;
        }
        footer{
            padding: 24px 48px;
        }
    }
</style>