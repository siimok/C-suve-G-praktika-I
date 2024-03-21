<script setup lang="ts">

import type { PropType } from 'vue'
import type { Session } from '@/types/types'
import SessionCard from '@/components/movieDetails/SessionCard.vue'

const props = defineProps({ sessions: { type: Object as PropType<Array<Session>> } })

</script>

<template>
  <div>
    <div
      v-for="session in sessions"
      :key="session.id"
      class="flex"
    >
      <session-card :session/>
    </div>
  </div>
</template>

<style scoped>

</style>