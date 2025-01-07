<template>
  <div class="leave-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>请假申请</span>
        </div>
      </template>
      <el-form :model="form" label-width="100px">
        <el-form-item label="请假类型">
          <el-select v-model="form.type" placeholder="请选择请假类型">
            <el-option label="事假" value="personal" />
            <el-option label="病假" value="sick" />
            <el-option label="其他" value="other" />
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="form.startTime" type="datetime" placeholder="选择开始时间" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="form.endTime" type="datetime" placeholder="选择结束时间" />
        </el-form-item>
        <el-form-item label="请假原因">
          <el-input v-model="form.reason" type="textarea" rows="4" placeholder="请输入请假原因" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit">提交申请</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'

const form = reactive({
  type: '',
  startTime: '',
  endTime: '',
  reason: ''
})

const handleSubmit = () => {
  ElMessage.success('申请提交成功')
}
</script>

<style scoped>
.leave-container {
  padding: 20px;
}
</style> 