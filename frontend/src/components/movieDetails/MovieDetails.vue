<script setup lang="ts">

import type { PropType } from 'vue'
import type { Movie } from '@/types/types'

const props = defineProps({ movie: { type: Object as PropType<Movie> } })

</script>

<template>
  <div class="md:flex">
    <div class="w-1/3">
      <img
        :src="movie?.imageUrl"
        class="object-cover w-full h-full"
      >
    </div>
    <div class="w-2/3 md:pl-10">
      <h1 class="text-3xl font-bold">{{ movie?.name }}</h1>

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
          v-for="(genre, index) in movie?.genres"
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
</template>

<style scoped>

</style>