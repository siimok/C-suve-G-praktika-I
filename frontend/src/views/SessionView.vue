<script setup lang="ts">

import { useRoute } from 'vue-router'
import { ref } from 'vue'
import SeatPlan from '@/components/SessionDetails/SeatPlan.vue'

const route = useRoute()

const session = ref({
  id: 0,
  movieId: 0,
  startTime: '',
  tickets: []
})
const fetchSession = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/sessions/' + route.params.id)
    if (!response.ok) {
      return
    }
    session.value = await response.json()
  } catch (error) {
    console.log('error')
  }
}
fetchSession()
</script>

<template>
  <main class="flex justify-center">
    <div class="max-w-[75rem] w-full mx-4 pt-4 md:pt-16">
      {{ session }}
      <seat-plan :tickets="session.tickets" />
    </div>
  </main>
</template>

<style scoped>

</style>