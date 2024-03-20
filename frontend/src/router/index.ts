import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import movieListView from '@/views/MovieListView.vue'
import movieDetailedView from '@/views/MovieDetailedView.vue'

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
    }
  ]
})

export default router
