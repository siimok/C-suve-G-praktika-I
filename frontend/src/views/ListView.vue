<script setup lang="ts">

import { ref, watch } from 'vue'
import type { Genre, Movie } from '@/types/types'
import MovieList from '@/components/movieList/MovieList.vue'

const genres = ref<Array<Genre>>([])

const genreId = ref(-1)

function selectGenre(id: number) {
  genreId.value === id ? genreId.value = -1 : genreId.value = id
  fetchMovies()
}

const fetchGenres = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/genres')
    if (!response.ok) {
      return
    }
    genres.value = await response.json()
  } catch (error) {
  }
}
fetchGenres()

const movies = ref<Array<Movie>>([])

function generateQueryString() {
  let queryParams = []

  if (searchString.value.length > 0) {
    queryParams.push('title=' + encodeURIComponent(searchString.value))
  }
  if (genreId.value >= 0) {
    queryParams.push('genreId=' + encodeURIComponent(genreId.value))
  }

  let finalString = ''

  if (queryParams.length > 0) {
    finalString = '?'
    finalString += queryParams.join('&')
  }

  return finalString
}

const fetchMovies = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/movies' + generateQueryString())
    if (!response.ok) {
      return
    }
    movies.value = await response.json()
  } catch (error) {
  }
}

const searchString = ref('')
let timeoutId: number | null = null

watch(searchString, async () => {
  if (timeoutId !== null) {
    clearTimeout(timeoutId as number)
  }

  timeoutId = setTimeout(() => {
    fetchMovies()
  }, 300)
})

fetchMovies()
</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16">
      <div class="flex justify-center">
        <input
          v-model="searchString"
          class="w-full max-w-[30rem] h-[2.75rem] border rounded border-gray-800 shadow pl-3 pr-4 py-2.5 text-base"
          maxlength="100"
          placeholder="Film name..."
        >
      </div>

      <div class="my-4">
        <button
          v-for="genre in genres"
          class="px-4 py-2 mr-2 border rounded-full text-white text-l font-semibold"
          :class="genre.id == genreId ? 'bg-gray-500' : 'bg-yellow-500'"
          @click="selectGenre(genre.id)"
        >
          {{ genre.name }}
        </button>
      </div>

      <div class="md:ml-5">
        <movie-list :movies />
      </div>
    </div>
  </main>
</template>