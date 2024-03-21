<script setup lang="ts">

import { computed, type PropType, type Ref, ref, type UnwrapRef } from 'vue'
import type { Movie, Ticket } from '@/types/types'

const props = defineProps({ tickets: { type: Object as PropType<Array<Ticket>>, required: true } })

const alreadyBooked = computed(() => {
  return props.tickets.map(ticket => {
    return parseInt(ticket.rowNumber) * 10 + parseInt(ticket.seatNumber)
  })
})

const selectedSeats: Ref<UnwrapRef<number[]>> = ref([])

function selectSeat(seat: number) {
  if (selectedSeats.value.includes(seat)) {
    selectedSeats.value = selectedSeats.value.filter(value => value !== seat)
  } else {
    selectedSeats.value.push(seat)
  }
}

</script>

<template>
  <div>
    <div
      v-for="i in 7"
      :key="i"
    >
      <button
        v-for="j in 10"
        :key="j"
        class="rounded-b mr-1 mb-3 rounded-lg w-6 h-6 "
        :class="[
          alreadyBooked.includes(i * 10 + j) ? 'bg-red-500' : 'bg-gray-500',
          selectedSeats.includes(i * 10 + j) ? 'bg-blue-500' : ''
        ]"
        :disabled="alreadyBooked.includes(i * 10 + j)"
        @click="selectSeat(i * 10 + j)"
      >
      </button>
    </div>
  </div>
  {{ tickets }}
  {{ alreadyBooked }}
</template>

<style scoped>

</style>