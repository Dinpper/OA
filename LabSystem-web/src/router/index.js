import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layout/index.vue'

export const constantRoutes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        meta: { title: '首页', icon: 'House', requiresAuth: true }
      }
    ]
  },
  {
    path: '/checkin',
    component: Layout,
    children: [
      {
        path: '',
        name: 'CheckIn',
        component: () => import('@/views/checkin/index.vue'),
        meta: { title: '签到', icon: 'Calendar', requiresAuth: true }
      }
    ]
  },
  {
    path: '/report',
    component: Layout,
    redirect: '/report/daily',
    name: 'Report',
    meta: { title: '报告管理', icon: 'Document' },
    children: [
      {
        path: 'daily',
        name: 'Daily',
        component: () => import('@/views/report/daily.vue'),
        meta: { title: '日报提交', icon: 'Edit', requiresAuth: true }
      },
      {
        path: 'list',
        name: 'ReportList',
        component: () => import('@/views/report/list.vue'),
        meta: { title: '报告', icon: 'Files', requiresAuth: true }
      }
    ]
  },
  {
    path: '/meeting',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Meeting',
        component: () => import('@/views/meeting/index.vue'),
        meta: { title: '会议', icon: 'Bell', requiresAuth: true }
      }
    ]
  },
  {
    path: '/leave',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Leave',
        component: () => import('@/views/leave/index.vue'),
        meta: { title: '请假', icon: 'Timer', requiresAuth: true }
      }
    ]
  },
  {
    path: '/project',
    component: Layout,
    redirect: '/project/my',
    name: 'Project',
    meta: { title: '项目管理', icon: 'Folder' },
    children: [
      {
        path: 'my',
        name: 'MyProjects',
        component: () => import('@/views/project/my.vue'),
        meta: { title: '相关项目', icon: 'Collection', requiresAuth: true }
      },
      {
        path: 'all',
        name: 'AllProjects',
        component: () => import('@/views/project/all.vue'),
        meta: { title: '全部项目', icon: 'Grid', requiresAuth: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Profile',
        component: () => import('@/views/profile/index.vue'),
        meta: { title: '个人设置', icon: 'User', requiresAuth: true }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  // 如果访问的不是登录页且没有token，重定向到登录页
  if (to.path !== '/login' && !token) {
    next({ path: '/login' })
  } else {
    // 如果已登录且访问登录页，重定向到首页
    if (to.path === '/login' && token) {
      next({ path: '/dashboard' })
    } else {
      next()
    }
  }
})

export default router 