<template>
    <div class="col-sm-4">
        <RouterLink class="img-card" :style="style" :to="`/video/${videoDetail.videoId}`">
                <h3 v-html="videoDetail.title"></h3>
                <div class="infos">
                    <h4>{{ videoDetail.channelName }}&nbsp;&nbsp;</h4>
                    <span class="badge rounded-pill">{{ videoDetail.exerciseArea }}</span>
                </div>
        </RouterLink>
    </div>
</template>

<script setup>
    import { useVideoStore } from '@/stores/video';

    const store = useVideoStore();
    const props = defineProps({
        videoDetail: {
            type: Object,
            required: true
        }
    })

    const backgroundURL = `URL(https://i.ytimg.com/vi/${props.videoDetail.youtubeId}/hqdefault.jpg)`
    const style = {
        backgroundImage: backgroundURL
    }
</script>

<style scoped>
    *{
        font: var(--sm-pre-font);
        color: white;
    }
    .img-card{
        display: block;
        text-decoration: none;
        aspect-ratio: 5/3;
        width: 100%;
        height: 100%;
        border: 1px var(--surface-color) solid;
        border-radius: 8px;
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        justify-content: end;
        cursor: pointer;
    }
    h3{
        width: 100%;
        margin: 0;
        font: var(--base-pre-font);
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        background-color: rgba(0, 0, 0, 0.7);
        padding: 8px 8px 0;
    }
    h4{
        color: var(--surface-color);
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        margin: 0;
    }
    .infos{
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 0 8px 8px;
        background-color: rgba(0, 0, 0, 0.7);
        border-end-start-radius: 8px;
        border-end-end-radius: 8px;
    }
    .badge{
        font: var(--xs-pre-font);
        padding: 2px 8px;
        box-sizing: border-box;
        background-color: var(--main-color);
        border: 0.5px white solid;
    }
</style>