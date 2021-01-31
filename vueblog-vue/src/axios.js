import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'

axios.defaults.baseURL = 'http://localhost:8081'

// 前置拦截
axios.interceptors.request.use(config => {
  return config
})

// 后置拦截
axios.interceptors.response.use(response => {
  let res = response.data
  console.log('===========')
  console.log(res)
  console.log('===========')

  if (res.code === 200) {
    return response
  } else {
    Element.Message.error(res.mess, {duration: 1 * 100})
    return Promise.reject(response.data.mess)
  }
}, error => {
  // console.log(error)
  if (error.response.data) {
    error.message = error.response.data.mess
  }
  if (error.response.status === 401) {
    store.commit('REMOVE_INFO')
    router.push('/login')
  }
  Element.Message.error(error.message, {duration: 1 * 100})
  return Promise.reject(error)
})
