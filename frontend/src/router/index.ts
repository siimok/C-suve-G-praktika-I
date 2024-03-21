import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import movieListView from '@/views/ListView.vue'
import movieDetailedView from '@/views/DetailedView.vue'
import sessionView from '@/views/SessionView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/movies',
      name: 'movies',
      component: movieListView
    },
    {
      path: '/movie/:id',
      name: 'movie',
      component: movieDetailedView
    },
    {
      path: '/session/:id',
      name: 'session',
      component: sessionView
    }
  ]
})

export default router
