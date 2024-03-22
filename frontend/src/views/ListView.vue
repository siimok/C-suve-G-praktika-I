<script setup lang="ts">

import { ref, watch } from 'vue'
import type { Genre, Movie } from '@/types/types'
import MovieList from '@/components/movieList/MovieList.vue'

const genres = ref<Array<Genre>>([])

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

  if (ageLimit.value > 0) {
    queryParams.push('minimumAge=' + encodeURIComponent(ageLimit.value))
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


const ageLimit = ref(0)

watch(ageLimit, (newX) => {
  fetchMovies()
})

const genreId = ref(-1)

watch(genreId, (newX) => {
  fetchMovies()
})

function selectGenre(id: number) {
  genreId.value === id ? genreId.value = -1 : genreId.value = id
}

fetchMovies()
</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-6">
      <div class="flex justify-center">
        <input
          v-model="searchString"
          class="w-full max-w-[30rem] h-[2.75rem] border rounded border-gray-800 shadow pl-3 pr-4 mr-6 py-2.5 text-base"
          maxlength="100"
          placeholder="Film name..."
        >
        <div class="pt-2">
          <label class=" font-semibold text-xl mr-5">Age</label>
          <select
            v-model="ageLimit"
            class="border rounded border-gray-800 shadow"
          >
            <option disabled value="">Please select one</option>

            <option value="-1">No limit</option>
            <option
              v-for="n in [12, 13, 14, 15, 16, 17, 18]"
              :key="n"
              :value="n"
            >{{ n }}
            </option>
          </select>
        </div>
      </div>


      <div class="my-4 text-center">
        <button
          class="px-4 py-2 mr-2 border rounded-full text-white text-l font-semibold"
          :class="genreId == -1 ? 'bg-gray-500' : 'bg-yellow-500'"
          @click="genreId = -1"
        >
          All
        </button>

        <button
          v-for="genre in genres"
          :key="genre.id"
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