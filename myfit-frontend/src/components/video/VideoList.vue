<template>
    <div class="wrapper">
        <VideoSearchInput></VideoSearchInput>
        <ul class="row row-cols-md-3">
            <VideoListItem v-for="video in currentPageVideoList" :key="video.videoId" :video="video"/>
        </ul>
    </div>
    <nav aria-label="Page navigation">
            <ul class="pagination d-flex justify-content-center">
                <li class="page-item">
                <a
                    class="page-link"
                    @click.prevent="currentPage--"
                    :class="{ disabled: currentPage <= 1 }"
                    href="#"
                    >&lt;
                </a>
                </li>
                <li
                class="page-item"
                :class="{ active: currentPage === page }"
                v-for="page in pageCount"
                :key="page"
                >
                <a class="page-link" href="#" @click.prevent="clickPage(page)">{{
                    page
                }}</a>
                </li>
                <li class="page-item">
                <a
                    class="page-link"
                    @click.prevent="currentPage++"
                    :class="{ disabled: currentPage >= pageCount }"
                    href="#"
                    >&gt;</a
                >
                </li>
            </ul>
        </nav>
</template>

<script setup>
    import { ref, computed, onMounted } from 'vue';
    import { useVideoStore } from '@/stores/video';
    import VideoListItem from './VideoListItem.vue';
    import VideoSearchInput from './VideoSearchInput.vue';

    const store = useVideoStore();
    onMounted(async ()=>{
        await store.getVideo();
    })

    const perPage = 6;
    const currentPage = ref(1);

    const pageCount = computed(() => {
        return Math.ceil(store.videoList.length / perPage);
    });

    const clickPage = function (page) {
        currentPage.value = page;
    };

    const currentPageVideoList = computed(() => {
        return store.videoList.slice(
            (currentPage.value - 1) * perPage,
            currentPage.value * perPage
        );
    });
</script>

<style scoped>
    ul{
        padding: 0;
    }
    .wrapper{
        padding: 24px 48px;
    }
</style>