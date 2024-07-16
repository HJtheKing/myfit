<template>
    <article>
        <h3>{{ post.title }}</h3>
        <h4>{{ post.writer }} <span>{{ post.regidate }}</span></h4>
        <h5>{{ post.content }}</h5>
    </article>
</template>

<script setup>
    const props = defineProps({
        post: {
            type: Object,
            required: true
        }
    })
</script>

<style scoped>
    *{
        font: var(--base-pre-font);
    }
    span{
        font: var(--sm-pre-font);
        font-weight: 400;
        color: var(--surface-color);
    }
    h3{
        font: var(--lg-pre-font);
    }
    h5{
        font: var(--sm-pre-font);
    }
</style>