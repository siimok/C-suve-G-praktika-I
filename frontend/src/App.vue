<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header class="h-16 text-2xl bg-gray-950 text-white flex justify-center items-center">
    <div class="max-w-[75rem] w-full mx-4">
      <nav>
        <RouterLink
          class="mr-4"
          to="/">Movies
        </RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
</style>
