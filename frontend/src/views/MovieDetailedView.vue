<script setup lang="ts">

import { useRoute } from 'vue-router'
import router from '@/router'
import { ref } from 'vue'

const route = useRoute()

const movie = ref({
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
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16 md:flex">
      <div class="w-1/3">
        <img
          :src="movie.imageUrl"
          class="object-cover w-full h-full"
        >
      </div>
      <div class="w-2/3 md:pl-10">
        <h1 class="text-3xl font-bold">{{ movie.name }}</h1>

        <h2 class="capitalize flex justify-between text-right mt-3">
          <p>Language:
            <span class="capitalize font-semibold">
              {{ movie?.language ? movie?.language : 'Estonian' }}
            </span>
          </p>
          <p>{{ movie?.published }}</p>
        </h2>

        <div class="my-4 flex">
          <div
            v-for="(genre, index) in movie.genres"
            :key="index"
            class="px-3 py-1   mr-2 border rounded-full"
          >
            {{ genre.name }}
          </div>
        </div>
        <p class="capitalize text-ellipsis overflow-hidden">
          {{ movie?.description }}
        </p>
      </div>
    </div>
  </main>
</template>

<style scoped>

</style>