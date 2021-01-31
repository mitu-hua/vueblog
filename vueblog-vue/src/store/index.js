import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem('userInfo'))
  },
  mutations: {
    // set
    SET_TOKEN: (state, token) => {
      this.token = token
      localStorage.setItem('token', token)
    },
    SET_USERINFO: (state, userInfo) => {
      this.userInfo = userInfo
      sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    REMOVE_INFO: state => {
      state.token = ''
      state.userInfo = {}
      localStorage.setItem('token', '')
      sessionStorage.setItem('userInfo', JSON.stringify({}))
    }
  },
  getters: {
    // get
    getUser: state => {
      return state.userInfo
    }
  },
  actions: {

  },
  modules: {
  }
})
