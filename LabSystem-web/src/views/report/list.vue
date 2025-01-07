<template>
  <div class="report-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>报告列表</span>
          <el-button type="primary" @click="handleAdd">新建报告</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="status" label="状态" width="100" />
        <el-table-column label="操作" width="150">
          <template #default="scope">
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
    date: '2024-01-07',
    title: '周报告',
    status: '已提交'
  }
])

const handleAdd = () => {
  // 处理新建
}

const handleEdit = (row) => {
  // 处理编辑
}

const handleDelete = (row) => {
  // 处理删除
}
</script>

<style scoped>
.report-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 