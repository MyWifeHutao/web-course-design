<template>
  <div class="base_form">
    <div class="base_header">
      <div class="blue_column"></div>
      <div class="base_title">课程管理</div>
    </div>

    <div class="base_query_oneLine">
      <div class="query_left">
        <el-button color="#96282d" @click="dialogFormVisible = true">添加</el-button>
      </div>
      <div class="query_right">
        <span style="margin-top: 5px">课程号或课程名</span>
        <el-input type="text" v-model="numName" style="margin-left: 10px; width: 230px" />
        <el-button style="margin-left: 5px" color="#96282d" @click="query()">查询</el-button>
      </div>
    </div>
    <div class="table_center" style="margin-top: 5px">
      <table class="content">
        <tr class="table_th">
          <td>课程号</td>
          <td>课程名</td>
          <td>学分</td>
          <td>材料路径</td>
          <td>前序课</td>
          <td>操作</td>
        </tr>
        <tr v-for="item in courseList" :key="item.courseId">
          <td>{{ item.num }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.credit }}</td>
          <td>{{ item.coursePath }}</td>
          <td>{{ item.preCourse }}</td>
          <td>
            <el-button color="#96282d" @click="editItem(item)">编辑</el-button>
            <el-button color="#fa581e" style="color: white;" @click="deleteItem(item.courseId)">删除</el-button>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <!-- 对话框显示 -->
  <el-dialog
    class="dialog"
    v-model="dialogFormVisible"
    onclose="close()"
    title="课程添加修改对话框"
    style="width: 40%;"
  >
  <div class="rowDiv">
    <div class="headDiv">课程号</div>
    <el-input v-model="form.num" style="width: 100%;"></el-input>
  </div>
  <div class="rowDiv">
    <div class="headDiv">课程名</div>
    <el-input v-model="form.name" style="width: 100%;"></el-input>
  </div>
  <div class="rowDiv">
    <div class="headDiv">学分</div>
    <el-input v-model="form.credit" style="width: 100%;"></el-input>
  </div>
  <div class="rowDiv">
    <div class="headDiv">资料路径</div>
    <el-input v-model="form.coursePath" style="width: 100%;"></el-input>
  </div>
  <div class="rowDiv">
    <div class="headDiv">前序课</div>
    <el-select v-model="form.preCourseId" placeholder="请选择前序课程" style="width: 100%;">
      <el-option v-for="item in courseSelectList" :key="item.id" :value="item.id">{{ item.title }}</el-option>
    </el-select>
  </div>
  <div class="rowDiv">
    <el-button @click="dialogFormVisible = false">取消</el-button>
    <el-button @click="confirm()">确认</el-button>
  </div>
  </el-dialog>
</template>

<script lang="ts">
import { type CourseItem, type OptionItem } from '~/models/general'
import { defineComponent,ref } from 'vue'
import { getCourseList, courseDelete, courseSave } from '~/services/teachingServ'
import { message, messageConform } from '~/tools/messageBox'
import { getDialog } from '~/tools/comMethod'
import { ElLoading } from 'element-plus'
export default defineComponent({
  // 双向绑定数据
  data: () => ({
    numName: '',
    courseList: [] as CourseItem[],
    courseSelectList: [] as OptionItem[],
    deleteId: -1,
    dialogFormVisible : ref(false),
    form: {} as CourseItem
    
  }),
  //初始加载一次,直接获取教师列表
  created() {
    this.query()
  },

  methods: {
    //设置课程选择列表
    makeSelectCourseList() {
      this.courseSelectList = []
      for (let i = 0; i < this.courseList.length; i++) {
        const item = this.courseList[i]
        this.courseSelectList.push({
          id: item.courseId,
          value: item.num,
          title: item.num + '-' + item.name
        })
      }
    },
    //查询课程列表
    async query() {
      this.courseList = await getCourseList(this.numName)
      this.makeSelectCourseList()
    },
    //添加课程,显示对话框
    addItem() {
      this.form = {} as CourseItem
      getDialog('favDialog').show()
    },
    //编辑课程,显示对话框
    editItem(item: CourseItem) {
      this.form = item
      this.dialogFormVisible = true
    },
    //关闭对话框
    close() {
      getDialog('favDialog').close()
    },
    //确认对话框
    async confirm() {
      this.dialogFormVisible = false;
      const res = await courseSave(this.form)
      if (res.code == 0) {
        message(this, '保存成功')
        this.query()
      } else {
        message(this, res.msg)
      }
    },
    //删除课程
    async deleteItem(courseId: number) {
      const result = await messageConform('确认删除吗?')
      if (!result) {
        return
      }
      const res = await courseDelete(courseId)
      if (res.code == 0) {
        message(this, '删除成功')
        this.query()
      } else {
        message(this, res.msg)
      }
    }
  }
})
</script>
<style scoped>
  .dialog{
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .rowDiv{
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 32px;
    justify-content: center;
  }
  .headDiv{
    display: flex;
    flex-direction: row;
    height: 32px;
    width: 80px;
    align-items: center;
  }</style>
