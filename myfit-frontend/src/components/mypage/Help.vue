<template>
    <div>
        <h1>고객센터</h1>
        <h3>사용하시면서 불편한 사항이 있었다면 아래 내용을 남겨주세요</h3>
        <div class="wrapper">
            <div class="fields">
                <div class="field">
                    <label for="name">성함</label>
                    <input type="text" name="name" id="name" :value="store.member.name"/>
                </div>
                <div class="field">
                    <label for="email">연락처</label>
                    <input type="text" name="email" id="email" :value="store.member.email"/>
                </div>
                <div class="field">
                    <label for="message">문의사항</label>
                    <textarea name="message" id="message" rows="3"></textarea>
                </div>
            </div>
            <button class="btn btn-submit" @click="submit()">제출하기</button>
            
        </div>
    </div>
</template>

<script setup>
    import router from '@/router';
    import { useMemberStore } from '@/stores/member';
    const store = useMemberStore();
    const submit = ()=>{
        alert("제출해주셔서 감사합니다.")
        router.push({name:'profile'})
    }
</script>

<style scoped>
*{
    font: var(--base-pre-font);
}

h1 {
    font: var(--xl-pre-font);
}
.wrapper{
    width: 80%;
    padding: 16px 24px;
    border: 2px #7cbbf3 solid;
    background-color: #fff;
    padding: 16px;
    border-radius: 10px;
    margin: 24px;
}
.fields {
    display: -moz-flex;
    display: -webkit-flex;
    display: -ms-flex;
    display: flex;
    -moz-flex-wrap: wrap;
    -webkit-flex-wrap: wrap;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    width: calc(100% + 3rem);
    margin: -1.5rem 0 2rem -1.5rem;	
}

.fields > .field {
        -moz-flex-grow: 0;
        -webkit-flex-grow: 0;
        -ms-flex-grow: 0;
        flex-grow: 0;
        -moz-flex-shrink: 0;
        -webkit-flex-shrink: 0;
        -ms-flex-shrink: 0;
        flex-shrink: 0;
        padding: 1.5rem 0 0 1.5rem;
        width: calc(100% - 1.5rem);
}

label {
    display: block;
    font: var(--lg-pre-font);
    line-height: 1.5;
    letter-spacing: 0.075em;
    text-transform: uppercase;
    margin: 0 0 0.75rem 0;
}

input[type="text"],
	input[type="password"],
	input[type="email"],
	textarea {
		-moz-appearance: none;
		-webkit-appearance: none;
		-ms-appearance: none;
		appearance: none;
		background: transparent;
		border-radius: 10px;
		border: solid 1px;
		color: inherit;
		display: block;
		outline: 0;
		padding: 1rem;
		text-decoration: none;
		width: 100%;
	}

    input[type="text"]:invalid,
    input[type="password"]:invalid,
    input[type="email"]:invalid,
    select:invalid,
    textarea:invalid {
        box-shadow: none;
    }
    .btn-submit{
        display: block;
        margin: 0 auto;
        background-color: var(--action-color);
        font: var(--base-pre-font);
        color: white;
    }
</style>