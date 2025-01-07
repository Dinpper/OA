<template>
  <div class="checkin-container">
    <el-card class="checkin-card">
      <template #header>
        <div class="card-header">
          <span>签到</span>
        </div>
      </template>
      <el-button type="primary" size="large" @click="handleCheckin">
        {{ isCheckedIn ? '已签到' : '签到' }}
      </el-button>
      <div class="checkin-time" v-if="isCheckedIn">
        签到时间：{{ checkinTime }}
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const isCheckedIn = ref(false)
const checkinTime = ref('')

const handleCheckin = () => {
  if (!isCheckedIn.value) {
    isCheckedIn.value = true
    checkinTime.value = new Date().toLocaleString()
    ElMessage.success('签到成功！')
  }
}
</script>

<style scoped>
.checkin-container {
  padding: 20px;
}
.checkin-card {
  max-width: 400px;
  margin: 0 auto;
}
.checkin-time {
  margin-top: 20px;
  color: #666;
}
</style> 