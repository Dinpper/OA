<template>
  <div class="daily-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>日报提交</span>
        </div>
      </template>
      <el-form :model="form" label-width="100px">
        <el-form-item label="日期">
          <el-date-picker v-model="form.date" type="date" placeholder="选择日期" />
        </el-form-item>
        <el-form-item label="工作内容">
          <el-input v-model="form.content" type="textarea" rows="4" placeholder="请输入今日工作内容" />
        </el-form-item>
        <el-form-item label="完成情况">
          <el-input v-model="form.completion" type="textarea" rows="4" placeholder="请输入完成情况" />
        </el-form-item>
        <el-form-item label="明日计划">
          <el-input v-model="form.plan" type="textarea" rows="4" placeholder="请输入明日工作计划" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'

const form = reactive({
  date: new Date(),
  content: '',
  completion: '',
  plan: ''
})

const handleSubmit = () => {
  ElMessage.success('提交成功')
}
</script>

<style scoped>
.daily-container {
  padding: 20px;
}
</style> 