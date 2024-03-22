<script setup lang="ts">

import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

</script>

<template>
  <div
    v-show="snackbarStore.genericSnackbar.state"
    class="fixed bottom-10 left-1/2 transform -translate-x-1/2
    py-3 px-6 flex justify-center items-center rounded-lg text-center"
    :class="snackbarStore.genericSnackbar.color"
  >
    <span class="text-white text-xl font-semibold">{{ snackbarStore.genericSnackbar.message }}</span>
  </div>
</template>