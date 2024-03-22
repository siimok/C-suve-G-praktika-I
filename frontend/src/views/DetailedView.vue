<script setup lang="ts">

import { useRoute } from 'vue-router'
import { ref } from 'vue'
import MovieDetails from '@/components/movieDetails/MovieDetails.vue'
import SessionList from '@/components/movieDetails/SessionList.vue'
import type { Movie } from '@/types/types'

const route = useRoute()

const movie = ref<Movie>({
  id: 0,
  name: '',
  rating: 10,
  published: 2024,
  minimumAge: 0,
  language: 'ESTONIAN',
  description: '',
  imageUrl: '',
  genres: [],
  sessions: []
})

const fetchMovie = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/movies/' + route.params.id)
    if (!response.ok) {
      return
    }
    movie.value = await response.json()
  } catch (error) {
    console.log('error')
  }
}
fetchMovie()

</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16">
      <movie-details :movie="movie" />
      <session-list :sessions="movie.sessions"></session-list>
    </div>
  </main>
</template>

<style scoped>

</style>