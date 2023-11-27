<template>
    <div class="base_form">
      <div class="base_header">
        <div class="blue_column"></div>
        <div class="base_title">个人画像</div>
      </div>
      <div class="table_center" style="margin-top: 5px">
        <table class="content">
          <tr>
            <td>工号</td>
            <td>
              {{ info.num }}
            </td>
            <td>姓名</td>
            <td>
              {{ info.name }}
            </td>
            <td>学院</td>
            <td>
              {{ info.dept }}
            </td>
            <td rowspan="3">
              <img :src="imgStr" alt="个人照片" width="200" />
            </td>
          </tr>
          <tr>
            <td>职称</td>
            <td>
              {{ info.title }}
            </td>
            <td>学位</td>
            <td>
              {{ info.degree }}
            </td>
            <td>证件号码</td>
            <td>
              {{ info.card }}
            </td>
          </tr>
          <tr>
            <td>性别</td>
            <td>
              {{ info.genderName }}
            </td>
            <td>出生日期</td>
            <td>
              {{ info.birthday }}
            </td>
            <td>邮箱</td>
            <td>
              {{ info.email }}
            </td>
          </tr>
          <tr>
            <td>电话</td>
            <td>
              {{ info.phone }}
            </td>
            <td>地址</td>
            <td colspan="3">
              {{ info.address }}
            </td>
            <td>
              <input
                style="margin-left: 10px"
                type="file"
                id="file"
                accept=".jpg"
              />
              <input type="button" value="图片上传" @click="uploadFile()" />
            </td>
          </tr>
        </table>
      </div>
      <div class="table_center" style="margin-top: 5px">
        
      </div>
      <div style="width: 60%">
        <div id="myChartBar" :style="{ width: '300px', height: '300px' }"></div>
        <div id="myChartPie" :style="{ width: '300px', height: '300px' }"></div>
        <div id="myChartLine" :style="{ width: '300px', height: '300px' }"></div>
      </div>
    </div>
    <div>
      <el-upload
        style="display: inline-block; margin-left: 5px"
        :headers="authHeader"
        :action="'/api/student/importFeeDataWeb'"
        :data="{ teacherId: teacherId }"
        accept=".xlsx,.xls"
        :show-file-list="true"
        :limit="1"
        :multiple="false"
        :on-success="onSuccess"
      >
        <el-button class="spacial">消费记录上传</el-button>
      </el-upload>
  
      <el-button style="margin-left: 5px" class="spacial" @click="downloadPdf()"
        >附件下载</el-button
      >
    </div>
  </template>
  <script lang="ts">
  import { defineComponent } from "vue";
  import * as echarts from "echarts";
  import {
    type ScoreItem,
    type TeacherItem,
  } from "~/models/general";
  import { downloadPost, getAuthHeader } from "~/services/genServ";
  import {
    getTeacherIntroduceData,
    getPhotoImageStr,
    uploadPhoto,
  } from "~/services/infoServ";
  import { message } from "~/tools/messageBox";
  const echart = echarts;
  
  export default defineComponent({
    data: () => ({
      authHeader: getAuthHeader(),
      valid: false,
      uploadIsLoading: false,
      teacherId: null as number | null,
      imgStr: "",
      info: {} as TeacherItem,
      scoreList: [] as ScoreItem[],
      nameRules: [],
      emailRules: [],
    }),
    async created() {
      let res = await getTeacherIntroduceData(this.teacherId);
      this.info = res.data.info;
      this.teacherId = this.info.teacherId;
    //   res = await getPhotoImageStr("photo/" + this.info.personId + ".jpg");
    //   this.imgStr = res.data;
    },
    mounted() {},
  
    methods: {
      // 上传图片
      async uploadFile() {
        const file = document.querySelector("#file") as any;
        if (file.files == null || file.files.length == 0) {
          message(this, "请选择文件！");
          return;
        }
        const res = await uploadPhoto(
          "photo/" + this.info.personId + ".jpg",
          file.files[0]
        );
  
        if (res.code === 0) {
          message(this, "上传成功");
        } else {
          message(this, "上传失败");
        }
      },
      // 下载pdf
      downloadPdf() {
        const res = downloadPost(
          "/api/student/getTeacherIntroduceItextPdf",
          this.info.num + ".pdf",
          {
            teacherId: this.teacherId,
          }
        );
        console.log(res);
      },
      // 上传成功回调函数
      onSuccess(res: any) {
        if (res.code == 0) {
          message(this, "上传成功！");
        } else {
          message(this, res.msg);
        }
      },
    },
  });
  </script>
  <style scoped>
  #myChartBar {
    float: left;
  }
  #myChartLine {
    float: right;
  }
  #myChartPie {
    float: right;
  }
  </style>
  