import { defineStore } from 'pinia'
import axios from 'axios'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    username: localStorage.getItem('username') || ''
  }),

  actions: {
    async login(loginForm) {
      try {
        const response = await axios.post('http://127.0.0.1:8880/login/loginIn', loginForm)
        console.log('Login response in store:', response)
        
        if (response.data.code === '200') {
          // 即使后端没有返回 token，我们也存储一个临时的
          const token = response.data.data?.token || 'dummy-token'
          this.token = token
          this.username = loginForm.account
          localStorage.setItem('token', token)
          localStorage.setItem('username', loginForm.account)
        }
        return response.data
      } catch (error) {
        console.error('Login error in store:', error)
        throw error
      }
    },

    logout() {
      this.token = ''
      this.username = ''
      localStorage.removeItem('token')
      localStorage.removeItem('username')
    }
  }
}) 