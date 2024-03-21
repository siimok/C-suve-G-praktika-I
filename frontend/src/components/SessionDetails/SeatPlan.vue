<script setup lang="ts">

import { computed, type PropType, type Ref, ref, type UnwrapRef } from 'vue'
import type { Ticket } from '@/types/types'

const props = defineProps({ tickets: { type: Object as PropType<Array<Ticket>>, required: true } })

const partySize = ref(1)

const alreadyBooked = computed(() => {
  return props.tickets.map(ticket => {
    return parseInt(ticket.rowNumber + 1) * 10 + parseInt(ticket.seatNumber + 1)
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

//Home-brewed algorithm.
//First it downgrades seating plan into a string and groupsize into a substring of free seats.
//Per row tries to find most optimal place.
function findBestSeats(groupSize: number) {
  let seatingPlan = Array.from({ length: 7 }, () => '0000000000')

  const groupString = '0'.repeat(groupSize)

  //mark booked seats.
  alreadyBooked.value.forEach(seatNo => {
    let rowNumber = Math.floor(seatNo / 10) - 1
    let seatNumber = seatNo % 10
    seatingPlan[rowNumber] = replaceCharAtIndex(seatingPlan[rowNumber], seatNumber - 1)
  })

  let bestScore = 100
  let bestStartingPosition

  //Optimal place to start seating the party
  let optimalStartSeat = 6 - Math.ceil(groupSize / 2)
  let optimalRow = 4

  for (let i = 0; i < 7; i++) {
    let currentScore = 0

    let availableStartingPositions = findAllIndexes(seatingPlan[i], groupString)

    //continue if party wont fit into current row
    if (availableStartingPositions.length < 1) continue
    let bestAvailableStartSeat = availableStartingPositions.reduce((closest, current) => {
      return Math.abs(current - optimalStartSeat) < Math.abs(closest - optimalStartSeat) ?
        current : closest
    })

    currentScore = Math.abs(bestAvailableStartSeat - optimalStartSeat) + Math.abs(i - optimalRow) * 2
    console.log('Score for row: ' + i + ' is ' + currentScore)
    console.log('Row penalty: ' + Math.abs(bestAvailableStartSeat - optimalStartSeat) + ' Seat penalty: ' + Math.abs(i - optimalRow) * 2)
    if (bestScore > currentScore) {
      bestScore = currentScore
      bestStartingPosition = i * 10 + bestAvailableStartSeat
    }
  }

  if (bestStartingPosition) {
    let bestSeats = []

    for (let i = 0; i < groupSize; i++) {
      bestSeats.push(bestStartingPosition + i)
    }

    selectedSeats.value = bestSeats

  }


  return bestStartingPosition
}

function replaceCharAtIndex(str: string, index: number) {
  if (index >= 0 && index < str.length) {
    return str.substring(0, index) + '1' + str.substring(index + 1)
  } else {
    return str
  }
}

function findAllIndexes(str: string, substring: string) {
  if (substring.length === 0) return []
  let indexes = []
  let index = -1
  while ((index = str.indexOf(substring, index + 1)) !== -1) {
    indexes.push(index + 1)
  }
  return indexes
}

</script>

<template>
  <div>
    {{ alreadyBooked }}
    {{ selectedSeats }}
    <div class="flex">
      <input
        v-model="partySize"
        class="border mr-5 w-24 h-10"
        type="number"
        id="tentacles"
        name="tentacles"
        min="1"
        max="10"
      />
      <button
        @click="console.log(findBestSeats(partySize))"
      >Generate seats
      </button>
    </div>
  </div>

  <div class="bg-gray-700 p-5 w-80 mt-4">
    <div class="bg-white h-2 mb-4"></div>
    <div
      v-for="i in 7"
      :key="i"
    >
      <button
        v-for="j in 10"
        :key="j"
        class="rounded-b mr-1 mb-3 rounded-lg w-6 h-6 "
        :class="{
          'bg-red-500': alreadyBooked.includes(i * 10 + j),
          'bg-blue-500': selectedSeats.includes(i * 10 + j),
          'bg-gray-100': !alreadyBooked.includes(i * 10 + j) && !selectedSeats.includes(i * 10 + j)
        }"
        :disabled="alreadyBooked.includes(i * 10 + j)"
        @click="selectSeat(i * 10 + j)"
      >
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>