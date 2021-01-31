import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import Blogs from '@/views/Blogs'
import BlogEdit from '@/views/BlogEdit'
import BlogDetail from '@/views/BlogDetail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: {name: 'Blogs'}
    },
    {
      path: '/blog/blogs',
      name: 'Blogs',
      component: Blogs
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/blog/add',
      name: 'BlogEdit',
      component: BlogEdit
    },
    {
      path: '/blog/:blogId',
      name: 'BlogDetail',
      component: BlogDetail
    },
    {
      path: '/blog/:blogId/edit',
      name: 'BlogEdit',
      component: BlogEdit
    }
  ]
})
