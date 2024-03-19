<script setup lang="ts">

import { ref } from 'vue'
import type { Movie } from '@/types/types'
import MovieList from '@/components/MovieList.vue'

const movies = ref<Array<Movie>>([])

const fetchMovies = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/movies')
    if (!response.ok) {
      return
    }
    movies.value = await response.json()
  } catch (error) {
  }
}
fetchMovies()
</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16">
      <movie-list :movies/>
    </div>
  </main>
</template>