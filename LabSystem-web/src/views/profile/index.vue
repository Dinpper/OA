<template>
  <div class="profile-container">
    <el-card class="profile-card">
      <template #header>
        <div class="card-header">
          <span class="header-title">个人信息</span>
          <el-button type="primary" @click="handleEdit" :icon="Edit">编辑资料</el-button>
        </div>
      </template>
      <div class="profile-content">
        <div class="basic-info">
          <div class="avatar-section">
            <el-avatar 
              :size="120" 
              :src="defaultAvatar"
              class="user-avatar"
            >
              {{ userInfo.name?.[0]?.toUpperCase() || 'U' }}
            </el-avatar>
            <div class="user-info">
              <h2 class="user-name">{{ userInfo.name }}</h2>
              <div class="user-id">ID: {{ userInfo.account }}</div>
            </div>
          </div>
          <div class="info-divider"></div>
        </div>
        
        <el-descriptions 
          :column="2" 
          border 
          class="info-descriptions"
        >
          <el-descriptions-item label="账号">
            <el-tag size="small">{{ userInfo.account }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="姓名">{{ userInfo.name }}</el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag 
              :type="userInfo.sex === 0 ? 'info' : 'danger'"
              size="small"
            >
              {{ userInfo.sex === 0 ? '男' : '女' }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="电话">
            <el-link type="primary" :underline="false">
              <el-icon><Phone /></el-icon>
              {{ userInfo.phone }}
            </el-link>
          </el-descriptions-item>
          <el-descriptions-item label="年级">
            <el-tag type="success" size="small">{{ userInfo.grade }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="邮箱">
            <el-link type="primary" :underline="false">
              <el-icon><Message /></el-icon>
              {{ userInfo.email }}
            </el-link>
          </el-descriptions-item>
          <el-descriptions-item label="学号">
            <el-tag type="info" size="small">{{ userInfo.stuNumber }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="班级">{{ userInfo.className }}</el-descriptions-item>
          <el-descriptions-item label="创建时间" :span="2">
            <el-icon><Clock /></el-icon>
            {{ formatDate(userInfo.inputDate) }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>

    <!-- 编辑对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="编辑个人信息"
      width="500px"
      class="edit-dialog"
    >
      <el-form :model="editForm" label-width="100px">
        <el-form-item label="手机号">
          <el-input v-model="editForm.phone" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'
import { Edit, Phone, Message, Clock } from '@element-plus/icons-vue'
import dayjs from 'dayjs'

const defaultAvatar = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'

const userInfo = ref({
  account: '',
  name: '',
  sex: 0,
  phone: '',
  grade: '',
  email: '',
  stuNumber: '',
  className: '',
  inputDate: ''
})

const dialogVisible = ref(false)
const editForm = ref({
  phone: '',
  email: ''
})

const formatDate = (date) => {
  return date ? dayjs(date).format('YYYY-MM-DD HH:mm:ss') : '-'
}

// 获取用户信息
const getUserInfo = async () => {
  try {
    const response = await request.post('/user/queryUserMessage', {
      account: localStorage.getItem('username')
    })
    userInfo.value = response.data
    editForm.value = {
      phone: response.data.phone,
      email: response.data.email
    }
  } catch (error) {
    console.error('获取用户信息失败:', error)
    ElMessage.error('获取用户信息失败')
  }
}

const handleEdit = () => {
  dialogVisible.value = true
}

const handleSave = () => {
  // TODO: 调用保存接口
  ElMessage.success('保存成功')
  dialogVisible.value = false
}

onMounted(() => {
  getUserInfo()
})
</script>

<style scoped>
.profile-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: calc(100vh - 150px);
}

.profile-card {
  max-width: 900px;
  margin: 0 auto;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
}

.header-title {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.basic-info {
  padding: 20px 0;
  margin-bottom: 30px;
}

.avatar-section {
  display: flex;
  align-items: center;
  padding: 0 20px;
}

.user-avatar {
  border: 4px solid #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.user-info {
  margin-left: 24px;
}

.user-name {
  margin: 0 0 8px 0;
  font-size: 24px;
  color: #303133;
}

.user-id {
  font-size: 14px;
  color: #909399;
}

.info-divider {
  margin: 30px 0;
  height: 1px;
  background-color: #ebeef5;
}

.info-descriptions {
  padding: 0 20px;
}

:deep(.el-descriptions__label) {
  width: 120px;
  justify-content: flex-end;
  padding: 12px 20px;
  font-weight: 600;
}

:deep(.el-descriptions__content) {
  padding: 12px 20px;
}

:deep(.el-tag) {
  font-weight: 500;
}

.el-link {
  display: inline-flex;
  align-items: center;
  gap: 4px;
}

:deep(.el-descriptions__body) {
  background-color: #fff;
}

:deep(.el-descriptions__cell) {
  vertical-align: middle;
}

:deep(.edit-dialog .el-dialog__body) {
  padding-top: 30px;
}
</style> 