<template>
  <div class="meeting-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>会议列表</span>
          <el-button type="primary" @click="handleAdd">发起会议</el-button>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="title" label="会议主题" />
        <el-table-column prop="location" label="地点" width="150" />
        <el-table-column prop="status" label="状态" width="100" />
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button link type="primary" @click="handleJoin(scope.row)">参加</el-button>
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
    date: '2024-01-07 14:00',
    title: '项目进度会议',
    location: '会议室A',
    status: '未开始'
  }
])

const handleAdd = () => {
  // 处理发起会议
}

const handleJoin = (row) => {
  // 处理参加会议
}
</script>

<style scoped>
.meeting-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 