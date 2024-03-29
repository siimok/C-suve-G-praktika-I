<script setup lang="ts">

import { useRoute } from 'vue-router'
import { computed, ref } from 'vue'
import SeatPlan from '@/components/SessionDetails/SeatPlan.vue'
import MovieDetails from '@/components/movieDetails/MovieDetails.vue'
import type { Movie, Session, Ticket } from '@/types/types'
import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

const route = useRoute()

const day = computed(() => {
  const startTime = session.value.startTime
  return startTime ? new Date(startTime).toLocaleDateString('en-US', { weekday: 'long' }) : ''
})

const startTime = computed(() => {
  const startTime = session.value.startTime
  return startTime ? new Date(startTime).toLocaleTimeString('en-US', {
    hour: '2-digit',
    minute: '2-digit',
    hourCycle: 'h24'
  }) : ''
})

const sessionDate = computed(() => {
  const startTime = session.value.startTime
  return startTime ? new Date(startTime).toLocaleDateString('en-US').replace(/\//g, '.') : ''
})

const movie = ref<Movie>({
  id: 0,
  name: '',
  rating: 10,
  published: 2024,
  minimumAge: 0,
  language: 'ESTONIAN',
  description: '',
  imageUrl: '',
  genres: [],
  sessions: [] as Session[]
})

const session = ref({
  id: 0,
  movieId: 0,
  startTime: '',
  tickets: [] as Ticket[]
})

const fetchSession = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/sessions/' + route.params.id)
    if (!response.ok) {
      return
    }
    session.value = await response.json()
    // Call fetchMovie after setting session.value
    fetchMovie(session.value.movieId)
  } catch (error) {
    console.error('Error fetching session:', error)
  }
}
fetchSession()

const fetchMovie = async (movieId: number) => {
  try {
    const response = await fetch('http://localhost:8080/api/movies/' + movieId)
    if (!response.ok) {
      return
    }
    movie.value = await response.json()
  } catch (error) {
    console.error('Error fetching movie:', error)
  }
}

function buyTickets(seatList: number[]) {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(generateTickets(seatList))
  }

  fetch('http://localhost:8080/api/tickets', requestOptions)
    .then(async response => {
      if (!response.ok) {
        throw new Error('Failed to buy tickets')
      }
      // Parse the response JSON
      return response.json()
    })
    .then((data: Ticket[]) => {
      // Handle the response data
      snackbarStore.setSuccessSnackbar('Ticket(s) were bought!')

      session.value.tickets.push(...data)
    })
    .catch(error => {
      // Handle any errors that occurred during the fetch request
      console.error('Error buying tickets:', error.message)
    })
}

function generateTickets(seatList: number[]) {
  let ticketList: Ticket[] = []

  for (let seat of seatList) {

    let ticketRow = Math.floor(seat / 10) + 1
    let ticketSeat = (seat % 10) + 1

    let ticket: Ticket = {
      sessionId: session.value.id,
      rowNumber: ticketRow,
      seatNumber: ticketSeat
    }
    ticketList.push(ticket)
  }
  return ticketList
}

</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16">
      <movie-details :movie="movie" />

      <div class="my-12 font-semibold text-2xl">
        <div class="flex">
          <p class=" mr-8">{{ startTime }}</p>
          <p class=" mr-5">{{ day }}</p>
          <p class="mr-5">{{ sessionDate }}</p>
        </div>

        <div class="block">
          <seat-plan
            :tickets="session.tickets"
            @selected-seats="buyTickets"
          />
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>

</style>