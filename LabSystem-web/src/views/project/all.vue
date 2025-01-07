<template>
  <div class="project-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>全部项目</span>
          <el-button type="primary" @click="handleAdd">新建项目</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="项目名称" />
        <el-table-column prop="leader" label="负责人" width="120" />
        <el-table-column prop="status" label="状态" width="100" />
        <el-table-column prop="progress" label="进度" width="180">
          <template #default="scope">
            <el-progress :percentage="scope.row.progress" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="handleView(scope.row)">查看</el-button>
            <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
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
    leader: '张三',
    status: '进行中',
    progress: 30
  }
])

const handleAdd = () => {
  // 处理新建项目
}

const handleView = (row) => {
  // 处理查看项目
}

const handleEdit = (row) => {
  // 处理编辑项目
}

const handleDelete = (row) => {
  // 处理删除项目
}
</script>

<style scoped>
.project-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 