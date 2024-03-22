import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useSnackbarStore = defineStore('snackbar', () => {
  const genericSnackbar = ref<genericSnackbarType>({ state: false, message: '', color: 'bg-green-500' })

  function setErrorSnackbar(errorMessage: string) {
    genericSnackbar.value = { state: true, message: errorMessage, color: 'bg-red-500' }
    setTimeout(() => {
      genericSnackbar.value.state = false
    }, 3000)
  }

  function setSuccessSnackbar(errorMessage: string) {
    genericSnackbar.value = { state: true, message: errorMessage, color: 'bg-green-500' }
    setTimeout(() => {
      genericSnackbar.value.state = false
    }, 3000)
  }

  type genericSnackbarType = {
    state: boolean,
    message: string,
    color: string
  }

  return {
    genericSnackbar,
    setErrorSnackbar,
    setSuccessSnackbar
  }
})
