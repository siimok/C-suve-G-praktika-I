<script setup lang="ts">

import type { PropType } from 'vue'
import type { Movie } from '@/types/types'
import MovieStars from '@/components/MovieStars.vue'

const props = defineProps({ movie: { type: Object as PropType<Movie> } })

</script>

<template>
  <div class="flex flex-auto relative border border-gray-200 rounded-lg shadow hover:bg-gray-100 w-full h-64 m-4">
    <div class="h-100 w-1/2 bg-gray-100">Image placeholder</div>
    <div class="w-1/2 p-4">
      <h2 class="h-16 text-center font-bold text-2xl capitalize overflow-ellipsis overflow-hidden">
        {{ movie?.name }}
      </h2>

      <movie-stars></movie-stars>

      <h3 class="capitalize flex justify-between text-right mt-2">
        <p>Language: {{ movie?.language ? movie?.language : 'Estonian' }}</p>
        <p>{{ movie?.published }}</p>
      </h3>

      <p class="h-[4.5rem] capitalize text-ellipsis overflow-hidden">
        {{ movie?.description }}
      </p>
      {{ props }}
    </div>
  </div>
</template>

<style scoped>

</style>