<script setup lang="ts">
import { computed, type PropType } from 'vue'
import type { Session } from '@/types/types'

const day = computed(() => {
  const startTime = props.session.startTime
  return startTime ? new Date(startTime).toLocaleDateString('en-US', { weekday: 'long' }) : ''
})

const startTime = computed(() => {
  const startTime = props.session.startTime
  return startTime ? new Date(startTime).toLocaleTimeString('en-US', {
    hour: '2-digit',
    minute: '2-digit',
    hourCycle: 'h24'
  }) : ''
})

const sessionDate = computed(() => {
  const startTime = props.session.startTime
  return startTime ? new Date(startTime).toLocaleDateString('en-US').replace(/\//g, '.') : ''
})


const props = defineProps({ session: { type: Object as PropType<Session> } })
</script>

<template>
  <div class="flex items-center border rounded-lg px-6 py-4 mt-4">
    <div class="flex items-center mr-8">
      <p class="font-semibold text-2xl mr-8">{{ startTime }}</p>
      <div class="flex flex-col space-y-4">
        <p>{{ day }}</p>
        <p>{{ sessionDate }}</p>
      </div>
    </div>
    <p class="mr-4">Available seats: {{ 70 - session.tickets.length }}/70</p>
    <button class="bg-yellow-500 rounded-lg p-3 text-white text-xl font-semibold">Buy a ticket</button>
  </div>
</template>