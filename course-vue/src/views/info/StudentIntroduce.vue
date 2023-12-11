<template>
  <div id="mylog" class="base" style="background-color: white;background-image: url('https://i.miji.bid/2023/12/11/5aa93d090ad41d9e9e7e3a122af55a26.png');">
    <div class="base_header" style="background-color: white;">
      <div class="blue_column" style="background-color: white;"></div>
      <div class="base_title">个人画像</div><div>
    <el-upload
      style="display: inline-block; margin-left: 5px"
      :headers="authHeader"
      :action="'/api/student/importFeeDataWeb'"
      :data="{ studentId: studentId }"
      accept=".xlsx,.xls"
      :show-file-list="true"
      :limit="1"
      :multiple="false"
      :on-success="onSuccess"
    >
      <el-button class="spacial">消费记录上传</el-button>
    </el-upload>

    <el-button style="margin-left: 5px" class="spacial" @click="htmlToPDF('mylog','test pdf')"
      >附件下载</el-button
    >
  </div>
    </div>
  <div class="outer">
    <div class="section1">
      <div class="head">
        <img class="headimg" referrerpolicy="no-referrer" :src="imgStr" @click=""/>
        <span class="name">{{ info.name }}</span>
      </div>
      <div class="contact">
        <div class="block"><img class="icon1" referrerpolicy="no-referrer" src="https://i.miji.bid/2023/12/10/78ecd946ba4e09e34f873ead5c78ccb6.png"/>{{ info.phone }}</div>
        <div class="block"><img class="icon1" referrerpolicy="no-referrer" src="https://i.miji.bid/2023/12/10/14ecf3b6f88df9c1391f1e70a21e83db.png"/>{{ info.email }}</div>
        <div class="block"><img class="icon1" referrerpolicy="no-referrer" src="https://i.miji.bid/2023/12/10/84ec409301b1f17eff1b676f68a710ab.png"/>https://github.com/</div>
      </div>
      <div class="selfintro">
        <span class="title">自我介绍</span>
      <div class="block">
        <img class="icon2" src="https://i.miji.bid/2023/12/10/98962ca08228979c13d7a4f01d33caec.png"/>能力项：沟通协调能力、执行力
      </div>
      <p>2022级软件工程本科生，拥有较强的执行力，做事干净利落不拖沓、不卡DDL；有一定的领导能力，担任多次小组作业的队长或主要队员；熟练掌握各类办公软件，在创新创业课程中绘制项目宣传PPT；熟练掌握PS等设计软件；熟练掌握剪辑软件</p>
      </div>
      <div class="educatedbg">
        <span class="title">教育背景</span>
        <div style="display: flex;flex-direction: row;">
          <div class="inner1">
          <p class="bword">山东大学<br/></p>软件工程
          <div class="greyword">（本科）</div>
          </div>
          <div class="inner1">
            <p class="greyword">2022.09~至今</p>
          </div>
        </div>
      </div>
    </div>
    <div class="section2">
      <div class="score">
        <el-table :data="filteredList" style="width: 100%">
          <el-table-column prop="courseNum" label="课程号" width="180" />
          <el-table-column prop="courseName" label="课程名" width="180" />
          <el-table-column prop="credit" label="学分" />
          <el-table-column prop="mark" label="成绩" />
        </el-table>
      </div>
      <div class="moredetails">
        <span class="title">更多信息</span>
        <table class="content">
          <tr>
            <td>学号</td>
            <td>{{ info.num }}</td>
            <td>学院</td>
            <td>{{ info.dept }}</td>
          </tr>
          <tr>
            <td>专业</td>
            <td>{{ info.major }}</td>
            <td>班级</td>
            <td>{{ info.className }}</td>
          </tr>
          <tr>
            <td>证件号码</td>
            <td>{{ info.card }}</td>
            <td>性别</td>
            <td>{{ info.genderName }}</td>
          </tr>
          <tr>
            <td>出生日期</td>
            <td>{{ info.birthday }}</td>
            <td>邮箱</td>
            <td>{{ info.email }}</td>
          </tr>
          <tr>
            <td>地址</td>
            <td colspan="3">{{ info.address }}</td>
          </tr>
        </table>
      </div>
      <div class="chart">
        <div style="width: 100%">
          <div id="myChartBar" :style="{ width: '200px', height: '300px' }"></div>
          <div id="myChartPie" :style="{ width: '350px', height: '300px' }"></div>
        </div>
      </div>
    </div>
  </div>
    
    
    
    
  </div>
  
</template>
<script lang="ts">
import { defineComponent } from "vue";
import * as echarts from "echarts";
import {
  type OptionItem,
  type StudentItem,
  type ScoreItem,
} from "~/models/general";
import { downloadPost, getAuthHeader } from "~/services/genServ";
import {
  getStudentIntroduceData,
  getPhotoImageStr,
  uploadPhoto,
} from "~/services/infoServ";
import { message } from "~/tools/messageBox";
import screenfull from "screenfull";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import { htmlToPDF } from "~/tools/html2pdf";
const echart = echarts;

export default defineComponent({
  data: () => ({
    
    authHeader: getAuthHeader(),
    valid: false,
    uploadIsLoading: false,
    studentId: null as number | null,
    imgStr: "",
    info: {} as StudentItem,
    feeList: [] as OptionItem[],
    markList: [] as OptionItem[],
    scoreList: [] as ScoreItem[],
    nameRules: [],
    emailRules: [],
  }),
  async created() {
    let res = await getStudentIntroduceData(this.studentId);
    this.info = res.data.info;
    this.studentId = this.info.studentId;
    this.feeList = res.data.feeList;
    this.markList = res.data.markList;
    this.scoreList = res.data.scoreList;
    res = await getPhotoImageStr("photo/" + this.info.name+this.info.num + ".jpg");
    this.imgStr = res.data;
    this.drawEcharts();
  },

  mounted() {
    const element = document.getElementById('mylog');//指定全屏区域元素
      document.getElementById('mylog').addEventListener('click', () => {
        if (screenfull.isEnabled) {
          screenfull.request(element);
        }
      });//实现模块全屏
  },
  computed : {
    filteredList() {
      return this.scoreList.filter(item => item.mark > 0);
    },
    filteredList2(){
      return this.markList.filter(item => parseInt(item.value) > 0);
    }
},
  methods: {
    htmlToPDF : async (htmlId: string, title: string = "报表", bgColor = "#fff") => {
    let pdfDom: HTMLElement | null = document.getElementById(htmlId) as HTMLElement
    pdfDom.style.padding = '0 10px !important'
    const A4Width = 595.28;
    const A4Height = 841.89;
    let canvas = await html2canvas(pdfDom, {
        scale: 2,
        useCORS: true,
        backgroundColor: bgColor,
    });
    let pageHeight = (canvas.width / A4Width) * A4Height;
    let leftHeight = canvas.height;
    let position = 0;
    let imgWidth = A4Width;
    let imgHeight = (A4Width / canvas.width) * canvas.height;
    /*
     根据自身业务需求  是否在此处键入下方水印代码
    */
    let pageData = canvas.toDataURL("image/jpeg", 1.0);
    let PDF = new jsPDF("p", 'pt', 'a4');
    if (leftHeight < pageHeight) {
        PDF.addImage(pageData, "JPEG", 0, 0, imgWidth, imgHeight);
    } else {
        while (leftHeight > 0) {
            PDF.addImage(pageData, "JPEG", 0, position, imgWidth, imgHeight);
            leftHeight -= pageHeight;
            position -= A4Height;
            if (leftHeight > 0) PDF.addPage();
        }
    }
    PDF.save(title + ".pdf");
},
    drawEcharts() {
      // 基于准备好的dom，初始化echarts实例
      let myChartBar = echart.init(
        document.getElementById("myChartBar") as any
      );
      // 绘制图表
      myChartBar.setOption({
        title: { text: "日常消费" },
        tooltip: {},
        xAxis: {
          data: this.feeList.map((item) => item.title),
        },
        yAxis: {},
        series: [
          {
            name: "消费",
            type: "bar",
            data: this.feeList.map((item) => item.value),
          },
        ],
      });
      let myChartPie = echart.init(
        document.getElementById("myChartPie") as any
      );
      // 绘制图表
      myChartPie.setOption({
        title: { text: "成绩分布" },
        tooltip: {},
        legend: {
          orient: "horizontal",
          x: "center",
          y: "bottom",
          data: this.filteredList2.map((item) => item.title),
        },
        series: [
          {
            type: "pie",
            data: this.markList,
          },
        ],
      });
    },
    // 上传图片
    async uploadFile() {
      const file = document.querySelector("#file") as any;
      if (file.files == null || file.files.length == 0) {
        message(this, "请选择文件！");
        return;
      }
      const res = await uploadPhoto(
        "photo/" + this.info.name+this.info.num + ".jpg",
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
        "/api/student/getStudentIntroduceItextPdf",
        this.info.num + ".pdf",
        {
          studentId: this.studentId,
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
.chart{
  width: 100%;
  height: 300px;
}
.moredetails{
  width: 100%;
  height: 25%;
  .title{
    font-size: 30px;
    color: cornflowerblue;
  }
}
.score{
  width: 100%;
  height: 36%;
}
.educatedbg{
  width: 70%;
  height: 12%;
  display: flex;
  flex-direction: column;
  margin-left: 50px;
  .block{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .title{
    font-size: 30px;
    color: cornflowerblue;
  }
  .inner1{
    height: 100%;
    width: 50%;
  }
  .greyword{
    color: grey;
  }
  .bword{
    font-weight: 1000;
  }
}
.selfintro{
  width: 70%;
  height: 30%;
  display: flex;
  flex-direction: column;
  margin-left: 50px;
  .block{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .title{
    font-size: 30px;
    color: cornflowerblue;
  }
  .icon2{
    margin-right: 10px;
    width: 25px;
    height: 25px;
  }
}
.contact{
  width: 70%;
  height: 12%;
  display: flex;
  flex-direction: column;
  margin-left: 50px;
  .block{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .icon1{
    margin-right: 10px;
    width: 30px;
    height: 30px;
  }
}
.head{
  width: 100%;
  height: 24%;
  display: flex;
  flex-direction: column;
  align-items: center;
  .headimg{
  margin-top: 5%;
  border-radius: 50%;
  width: 100px;
  height: 100px;
  align-items: center;
  }
  .name{
    margin-top: 2.5%;
    font-size: 30px;
  }
}
.section1{
  background-color: white;
  height: 100%;
  width: 40%;
}
.section2{
  background-color: white;
  height: 100%;
  width: 60%;
}
.base {
  height: 795px;
  width: 100%;
  
}
.outer {
  margin-left: 20%;
  height: 100%;
  width: 60%;
  display: flex;
}
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
