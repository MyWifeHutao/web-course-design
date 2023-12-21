<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">我的论文著作</div>
      </div>
  
      <div class="base_query_oneLine">
        <div class="query_left">
          <button class="commButton" @click="addItem()">添加</button>
        </div>
        <div class="query_right">
        </div>
      </div>
      
      <div class="table_center" style="margin-top: 5px">
        <table class="content">
          <tr class="table_th">
            <td>发行日期</td>
            <td>论文著作</td>
          </tr>
          <tr v-for="item in essayList" :key="item.essayId">
            <td>{{ item.data }}</td>
            <td>{{ item.book }}</td>
          </tr>
        </table>
      </div>
    </div>
    <el-dialog
      class="dialog"
      v-model="addDialog"
      title="添加项目"
      id="favDialog"
      onclose="close()"
      style="width: 20%;"
      align-center
    >
      
      <div class="rowDiv">
        <div class="headDiv">发行日期</div>
        <el-date-picker v-model="editedItem.data" style="width: 100%;"></el-date-picker>
      </div>
      <div class="rowDiv">
        <div class="headDiv">论文著作</div>
        <el-input v-model="editedItem.book" style="width: 100%;"></el-input>
      </div>


      <div class="rowDiv">
        <el-button @click="close()">取消</el-button>
        <el-button @click="submit()">提交</el-button>
      </div>
      <!-- <div class="dialog-div">
        <table class="dialog-content">
            <tr>
                <td colspan="1" style="text-align: right">项目</td>
                <td colspan="1"><input v-model="editedItem.SheOrJain" style="width: 97%" /></td>
                </tr>
            <tr>
                <td colspan="1" style="text-align: right">日期</td>
                <td colspan="1"><input v-model="editedItem.data" style="width: 97%" /></td>
            </tr>
            <tr>
                <td colspan="1" style="text-align: right">时长</td>
                <td colspan="1"><input v-model="editedItem.hour" style="width: 97%" /></td>
            </tr>
            <tr>
                <td colspan="1" style="text-align: right">奖励</td>
                <td colspan="1"><input v-model="editedItem.aware" style="width: 97%" /></td>
            </tr>
            <tr>
                <td colspan="1" style="text-align: right">团队</td>
                <td colspan="1"><input v-model="editedItem.friend" style="width: 97%" /></td>
            </tr>
            <br>
      
          <tr>
            <td colspan="2">
              <button
                class="commButton"
                @click="close()"
                style="margin-right: 30px"
              >
                   取消
              </button>
              <button class="commButton" @click="submit">提交</button>
            </td>
          </tr>
        </table>
      </div> -->
    </el-dialog>
  </template>
  <script lang="ts">
  import { defineComponent,ref } from "vue";
  import {
    getScoreList,
    getCourseItemOptionList,
    getEssayList,
    scoreSave,
    scoreDelete,
    essayEditSave,
getTeacherItemOptionList,
  } from "~/services/teachingServ";
  import { TeacherItem, type OptionItem, type ScoreItem, EssayItem } from "~/models/general";
  import { message, messageConform } from "~/tools/messageBox";
  import { getTeacherIntroduceData } from "~/services/infoServ";
  
  export default defineComponent({
    data: () => ({
      info: {} as TeacherItem,
      essayList: [] as EssayItem[],
      teacherId: null,
      courseId: null,
      editedItem: {} as EssayItem,
      teacherList: [] as OptionItem[],
      courseList: [] as OptionItem[],
      deleteId: -1,
      addDialog : ref(false)
    }),
    computed: {},
    async created() {
      let res = await getTeacherIntroduceData(this.teacherId);
      this.info = res.data.info;
      this.initialize();
    },
  
    methods: {
      // 初始化,获取学生选择项列表和课程选择项列表
      async initialize() {
        this.essayList = await getEssayList(this.info.teacherId);
        this.teacherList = await getTeacherItemOptionList();
        this.courseList = await getCourseItemOptionList();
      },
      // 查询
      async query() {
        this.essayList = await getEssayList(this.info.teacherId);
      },
      // 添加成绩,显示成绩修改对画框
      addItem() {
        this.addDialog=true;
        this.editedItem = {} as EssayItem;
        const dialog = document.getElementById("favDialog") as HTMLDialogElement;
        dialog.show();
      },
      // 编辑成绩,显示成绩修改对画框
    //   editItem(item: ScoreItem) {
    //     this.editedItem = item;
    //     const dialog = document.getElementById("favDialog") as HTMLDialogElement;
    //     dialog.show();
    //   },
      // 关闭成绩修改对话框
      close() {
        this.addDialog=false;
      },
      // 确认成绩修改对话框
      async submit() {
            const res = await essayEditSave(this.info.teacherId, this.editedItem);
            if (res.code == 0) {
                message(this, "保存成功");
                this.query();
            } else {
                alert(res.msg);
            }
            this.addDialog=false;
        },
    //   async confirm() {
    //     this.close();
    //     const res = await essaySave(
    //       this.editedItem.essayId,
    //       this.info.teacherId,
    //       0,
    //       0
    //     );
    //     if (res.code == 0) {
    //       message(this, "保存成功");
    //       this.query();
    //     } else {
    //       message(this, res.msg);
    //     }
    //   },
      // 删除成绩
      async deleteItem(scoreId: number) {
        const result = await messageConform("确认删除吗?");
        if (!result) {
          return;
        }
        const res = await scoreDelete(scoreId);
        if (res.code == 0) {
          message(this, "删除成功");
          this.query();
        } else {
          message(this, res.msg);
        }
      },
    },
  });
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
  }
  </style>
  