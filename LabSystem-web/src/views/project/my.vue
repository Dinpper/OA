<template>
  <div class="project-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>我的项目</span>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="项目名称" />
        <el-table-column prop="role" label="担任角色" width="120" />
        <el-table-column prop="status" label="状态" width="100" />
        <el-table-column prop="progress" label="进度" width="180">
          <template #default="scope">
            <el-progress :percentage="scope.row.progress" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button link type="primary" @click="handleView(scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const tableData = ref([
  {
    name: '实验室管理系统',
    role: '开发者',
    status: '进行中',
    progress: 30
  }
])

const handleView = (row) => {
  // 处理查看项目
}
</script>

<style scoped>
.project-container {
  padding: 20px;
}
</style> 