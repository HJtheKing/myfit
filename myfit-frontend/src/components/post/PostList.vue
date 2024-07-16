<template>
    <div class="container">
        <h3>게시글 목록</h3>
        <hr />
        <table class="table table-hover text-center">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>쓰니</th>
            <th>등록일</th>
        </tr>
        <tr v-for="post in currentPagePostList" :key="post.id">
            <td>{{ post.postId }}</td>
            <td>
            <RouterLink :to="`/post/${post.postId}`">{{ post.title }}</RouterLink>
            </td>
            <td>{{ post.writer }}</td>
            <td>{{ post.regDate }}</td>
        </tr>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination d-flex justify-content-center">
                <li class="page-item">
                <a
                    class="page-link"
                    @click.prevent="currentPage--"
                    :class="{ disabled: currentPage <= 1 }"
                    href="#"
                    >&lt;</a
                >
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
  </div>
</template>

<script setup>
    import { usePostStore } from '@/stores/post';
    import { computed, onMounted, ref } from 'vue';

    const store = usePostStore();
    onMounted(() => {
        store.getPostList();
    });

    const perPage = 10;
    const currentPage = ref(1);

    const pageCount = computed(() => {
        return Math.ceil(store.postList.length / perPage);
    });

    const clickPage = function (page) {
        currentPage.value = page;
    };

    const currentPagePostList = computed(() => {
        return store.postList.slice(
            (currentPage.value - 1) * perPage,
            currentPage.value * perPage
        );
    });
</script>

<style scoped>
    *{
        font: var(--base-pre-font);
    }
    h3{
        font: var(--lg-pre-font);
        padding-top: 20px;
    }
</style>