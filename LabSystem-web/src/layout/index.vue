<template>
  <div class="layout-container">
    <!-- 左侧导航栏 -->
    <div class="sidebar" :class="{ 'is-collapsed': isCollapse }">
      <div class="logo-container">
        <div class="logo-content">
          <img src="@/assets/logo.png" alt="logo" />
          <h1 class="title" :class="{ 'hidden': isCollapse }">实验室管理系统</h1>
        </div>
      </div>
      <el-menu
        :default-active="activeMenu"
        class="sidebar-menu"
        :collapse="isCollapse"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        router
      >
        <template v-for="route in routes">
          <!-- 没有子菜单的情况 -->
          <el-menu-item 
            v-if="!route.children || route.children.length === 1" 
            :key="route.path"
            :index="getRouteIndex(route)"
          >
            <el-icon>
              <component :is="route.meta?.icon || route.children?.[0].meta?.icon" />
            </el-icon>
            <template #title>{{ route.meta?.title || route.children?.[0].meta?.title }}</template>
          </el-menu-item>

          <!-- 有子菜单的情况 -->
          <el-sub-menu 
            v-else 
            :key="route.path"
            :index="route.path"
          >
            <template #title>
              <el-icon>
                <component :is="route.meta?.icon" />
              </el-icon>
              <span>{{ route.meta.title }}</span>
            </template>
            <el-menu-item
              v-for="child in route.children"
              :key="child.path"
              :index="route.path + '/' + child.path"
            >
              <el-icon>
                <component :is="child.meta?.icon" />
              </el-icon>
              <template #title>{{ child.meta.title }}</template>
            </el-menu-item>
          </el-sub-menu>
        </template>
      </el-menu>
    </div>

    <!-- 右侧内容区 -->
    <div class="main-container" :class="{ 'is-collapsed': isCollapse }">
      <!-- 顶部导航 -->
      <div class="navbar">
        <div class="left-area">
          <div class="hamburger" @click="toggleSideBar">
            <el-icon :class="{ 'is-active': !isCollapse }"><Fold /></el-icon>
          </div>
          <!-- 标签导航 -->
          <div class="tags-nav">
            <el-tag
              v-for="tag in visitedViews"
              :key="tag.path"
              :closable="true"
              :effect="activeMenu === tag.path ? 'dark' : 'plain'"
              @click="handleTagClick(tag)"
              @close="handleTagClose(tag)"
            >
              {{ tag.title }}
            </el-tag>
          </div>
        </div>
        <div class="right-menu">
          <el-dropdown trigger="click">
            <div class="avatar-wrapper">
              <el-avatar :size="40" src="https://placeholder.com/40" />
              <span class="user-name">{{ username }}</span>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="handleProfile">个人设置</el-dropdown-item>
                <el-dropdown-item divided @click="handleLogout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>

      <!-- 主要内容区 -->
      <div class="app-main">
        <router-view v-slot="{ Component }">
          <keep-alive :include="cachedViews">
            <component :is="Component" />
          </keep-alive>
        </router-view>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { constantRoutes } from '@/router'
import {
  House,
  Calendar,
  Document,
  Bell,
  Timer,
  Folder,
  Fold,
  Edit,
  Files,
  Collection,
  Grid
} from '@element-plus/icons-vue'

const router = useRouter()
const route = useRoute()
const isCollapse = ref(false)
const username = computed(() => localStorage.getItem('username'))

// 过滤掉登录页面的路由
const routes = computed(() => {
  return constantRoutes.filter(route => route.path !== '/login')
})

// 获取路由索引
const getRouteIndex = (route) => {
  if (route.children && route.children.length === 1) {
    return route.path === '/' ? '/dashboard' : `${route.path}/${route.children[0].path}`.replace('//', '/')
  }
  return route.path
}

// 当前激活的菜单
const activeMenu = computed(() => route.path)

// 访问过的页面标签
const visitedViews = ref([
  { path: '/dashboard', title: '首页' }
])

// 缓存的组件名称
const cachedViews = ref(['Dashboard'])

// 切换侧边栏
const toggleSideBar = () => {
  isCollapse.value = !isCollapse.value
}

// 处理标签点击
const handleTagClick = (tag) => {
  router.push(tag.path)
}

// 处理标签关闭
const handleTagClose = (tag) => {
  // 只保留条件：不允许关闭最后一个标签
  if (visitedViews.value.length <= 1) {
    return
  }

  const index = visitedViews.value.findIndex(v => v.path === tag.path)
  visitedViews.value.splice(index, 1)
  
  // 如果关闭的是当前页面，则跳转到最后一个标签
  if (route.path === tag.path) {
    const lastTag = visitedViews.value[visitedViews.value.length - 1]
    router.push(lastTag.path)
  }
}

// 监听路由变化，添加标签
router.beforeEach((to, from, next) => {
  if (to.meta?.title && to.path !== '/login') {
    const index = visitedViews.value.findIndex(tag => tag.path === to.path)
    if (index === -1) {
      visitedViews.value.push({
        path: to.path,
        title: to.meta.title
      })
      if (to.name) {
        cachedViews.value.push(to.name)
      }
    }
  }
  next()
})

// 退出登录
const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('username')
  router.push('/login')
}

// 处理个人设置
const handleProfile = () => {
  router.push('/profile')
}

// 注册图标组件
const icons = {
  House,
  Calendar,
  Document,
  Bell,
  Timer,
  Folder,
  Fold,
  Edit,
  Files,
  Collection,
  Grid
}
</script>

<style scoped>
.layout-container {
  position: relative;
  height: 100vh;
  width: 100%;
  overflow: hidden;
}

.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  width: 210px;
  background-color: #304156;
  transition: width 0.3s;
  z-index: 1001;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.sidebar.is-collapsed {
  width: 64px;
}

.logo-container {
  height: 60px;
  padding: 10px;
  background-color: #304156;
  border-bottom: 1px solid #1f2d3d;
  width: 100%;
}

.logo-content {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 10px;
}

.logo-content img {
  width: 32px;
  height: 32px;
  margin-right: 12px;
  object-fit: contain;
}

.title {
  color: #fff;
  font-size: 16px;
  font-weight: 600;
  white-space: nowrap;
  transition: opacity 0.3s;
}

.title.hidden {
  opacity: 0;
}

.main-container {
  margin-left: 210px;
  min-height: 100vh;
  transition: margin-left 0.3s;
  background-color: #f0f2f5;
}

.main-container.is-collapsed {
  margin-left: 64px;
}

.navbar {
  height: 60px;
  padding: 0 20px;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.hamburger {
  cursor: pointer;
  font-size: 20px;
}

.right-menu {
  display: flex;
  align-items: center;
}

.avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.user-name {
  margin-left: 8px;
  font-size: 14px;
}

.app-main {
  padding: 20px;
  min-height: calc(100vh - 60px);
  box-sizing: border-box;
}

.left-area {
  display: flex;
  align-items: center;
}

.tags-nav {
  margin-left: 20px;
  display: flex;
  align-items: center;
  gap: 5px;
}

.el-tag {
  cursor: pointer;
  user-select: none;
  margin-right: 5px;
}

.el-tag:last-child {
  margin-right: 0;
}

.hamburger .el-icon {
  transition: transform 0.3s;
}

.hamburger .el-icon.is-active {
  transform: rotate(180deg);
}

.el-menu {
  border-right: none !important;
  width: 100%;
}
</style> 