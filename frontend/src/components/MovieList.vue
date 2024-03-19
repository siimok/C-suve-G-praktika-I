<script setup lang="ts">
import type { PropType } from 'vue'
import type { Movie } from '@/types/types'
import MovieCard from '@/components/MovieCard.vue'

const props = defineProps({ movies: { type: Object as PropType<Array<Movie>> } })
</script>

<template>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-2 justify-between">
    <div
      v-for="movie in movies"
      :key="movie.id"
    >
      <movie-card :movie></movie-card>
    </div>
    <div
      v-if="movies?.length === 0"
      class="text-2xl font-semibold mt-4 text-center"
    >No movies found
    </div>
  </div>

</template>