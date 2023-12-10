<template>
  <div class="dark" style="height: 100%">
    <!-- 当登录成功后显示此DIV -->
    <div
      style="height: 100%"
      v-if="
        userInfo.username &&
        userInfo.username.length > 0 &&
        userInfo.loggedIn
      "
    >
      <el-container style="height: 100%;background-color: white;">
        <!-- 主页面的头部区域 -->
        <el-aside width= "isCollapse ? '64px':'250px'" style="height: 100%;background-color: white;">
            
            <el-menu 
              default-active="2"
              class="el-menu-vertical-demo"
              :collapse="isCollapse"
              @open="handleOpen"
              @close="handleClose"
            >

            <img @click="switchCollapse()" class="icon1" referrerpolicy="no-referrer" src="https://picst.sunbangyan.cn/2023/12/01/be22f794e3145d6d9ee91b995441ab22.jpeg" />
            <template v-if="userInfo.roles == 'ROLE_ADMIN'">
                        <el-sub-menu index="1" >
                          <template #title>
                            <el-icon><location /></el-icon>
                            <span>个人信息</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/MainPage" @click=goToMain()>主界面</el-menu-item>
                              <el-menu-item index="/Login" @click=changePswd()>修改密码</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="2" >
                          <template #title>
                            <el-icon><Connection /></el-icon>
                            <span>系统管理</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/menu-manage-panel" @click=goToMenu()>菜单管理</el-menu-item>
                              <el-menu-item index="/dictionary-manage-panel" @click=goToDictionary()>字典管理</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="3" >
                          <template #title>
                            <el-icon><document /></el-icon>
                            <span>人员管理</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/student-panel" @click=goToStudent()>学生管理</el-menu-item>
                              <el-menu-item index="/teacher-panel" @click=goToTeacher()>教师管理</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="4" >
                          <template #title>
                            <el-icon><setting /></el-icon>
                            <span>教务管理</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/course-panel" @click=goToCourse()>课程管理</el-menu-item>
                              <el-menu-item index="/score-table-panel" @click=goToScore()>成绩管理</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>  
            </template>
            <template v-if="userInfo.roles == 'ROLE_STUDENT'">

                        <el-sub-menu index="1" >
                          <template #title>
                            <el-icon><location /></el-icon>
                            <span>个人信息</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/MainPage" @click=goToMain()>主界面</el-menu-item>
                              <el-menu-item index="/Introduce" @click=studentIntroduce()>个人简介</el-menu-item>
                              <el-menu-item index="/Login" @click=changePswd()>修改密码</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>  
                        <el-sub-menu index="2" >
                          <template #title>
                            <el-icon><Notebook /></el-icon>
                            <span>智慧教学</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/Choose" @click=courseChoose()>学生选课</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>  
            </template>
            <template v-if="userInfo.roles == 'ROLE_TEACHER'">

                        <el-sub-menu index="1" >
                          <template #title>
                            <el-icon><location /></el-icon>
                            <span>个人信息</span>
                          </template>
                          <el-menu-item-group>
                              <el-menu-item index="/MainPage" @click=goToMain()>主界面</el-menu-item>
                              <el-menu-item index="/Introduce" @click=teacherIntroduce()>个人简介</el-menu-item>
                              <el-menu-item index="/Login" @click=changePswd()>修改密码</el-menu-item>
                              </el-menu-item-group>
                        </el-sub-menu>  
            </template>
                
                
              </el-menu>
                    </el-aside>
          
        <el-container>
          <el-header class="header2" height="100px">
            <div class="header" style="width: 100%;">
              <div style="width: 15%;height:70;">
                <img @click="switchCollapse()" class="icon2" referrerpolicy="no-referrer" src="https://picdm.sunbangyan.cn/2023/11/22/a26e4043c981cd03f814bb1e6c32613c.jpeg" />
                <img @click="FullScreen()" class="icon3" referrerpolicy="no-referrer" src="https://i.miji.bid/2023/12/10/0e19d5a57df07a6a9da8a5775ce359f8.png" />
              </div>
              <div style="width: 65%;background-image: url('https://i.miji.bid/2023/12/10/2aecb1a35c4ff834bc74b6b4e890a183.png');">
                
              </div>
              <div style="width: 20%;height:70;" class="flex-row">
                  <img @click="drawer = true" class="icon1" referrerpolicy="no-referrer" :src="imgStr" />
                <div class="section3 flex-col justify-between">
                  <span class="word1">您好～</span>
                  <span class="info"
                    >{{ userInfo.perName }}({{ userInfo.username }})
                    {{ currentTime }}</span
                  ></div>
            </div>
            
            
           <el-drawer
            v-model="drawer"
            title="个人信息"
            :before-close="handleClose1"
            >
            <span>Hi, there!</span>
            <div class="drawer">
              <img width="200"
                   referrerpolicy="no-referrer"
                   :src="imgStr"
                   />
              <input
              style="margin-left: 20%"
              type="file"
              id="file"
              accept=".jpg"
            />
            <div>
              <el-button type="primary" plain @click="uploadFile()" >图片上传</el-button>
              <el-button type="danger" plain @click="logout(),drawer=false">退出登录</el-button>
            </div>
              <el-carousel class="carousel" trigger="click" >
              <el-carousel-item v-for="item in filteredList" :key="item.studentId">
                <el-card class="box-card">
                  <template #header>
                    <div class="card-header">
                      <span>{{ item.courseName }}</span>
                    </div>
                  </template>
                    <div>课序号：{{ item.courseNum }}</div>
                    <div>学分：{{ item.credit }}</div>
                    <div>成绩：{{ item.mark }}</div>
                    <div>成绩：{{ item.mark }}</div>
                <hr/>
                    <div>
                      <span v-if="item.mark>=90">评级：优</span>
                      <span v-if="item.mark>=80 && item.mark<90">评级：良</span>
                      <span v-if="item.mark>=70 && item.mark<80">评级：中</span>
                      <span v-if="item.mark>=60 && item.mark<70">评级：差</span>
                      <span v-if="item.mark<60">评级：差</span>
                    </div>
                  
                </el-card>
              </el-carousel-item>
            </el-carousel>

            
            </div>
            
            
            
           </el-drawer>
            </div>
            <div style="height: 30px;background-image: url('https://i.miji.bid/2023/12/10/0a17de75275b2d60c3892f4a8ee19115.png');width: 100%;">

</div>
          </el-header>
          <!--   router-view 控制显示VUE页面，VUE会根据路由路径不同.vue文件的内容，我们设计的所有vue文件内容都在这里渲染 -->
          <el-main>
            <router-view />
          </el-main>
        </el-container>
      </el-container>
    </div>
    <!--  当登录前显示此DIV router-view 控制显示VUE页面，VUE会根据路由路径不同.vue文件的内容， 初始路由路径为/，显示Login.vue内容 -->
    <div style="height: 100%" v-else>
      <router-view />
    </div>
  </div>
</template>

<script lang="ts" >
// 引入使用的组件、函数和数据接口
import { mapState } from "pinia";
import { defineComponent } from "vue";
import { useAppStore } from "~/stores/app";
import router from "~/router";
import { type StudentItem,type MenuInfo, OptionItem, ScoreItem } from "~/models/general";
import { formatTime } from "~/tools/comMethod";
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus'
import{getPhotoImageStr,getStudentIntroduceData,uploadPhoto} from '~/services/infoServ'
import { message } from "~/tools/messageBox";
// vue3中新增了 defineComponent ，它并没有实现任何的逻辑，只是把接收的 Object 直接返回，它的存在就是完全为了服务 TypeScript 而存在的。
// 我都知道普通的组件就是一个普通的对象，既然是一个普通的对象，那自然就不会获得自动的提示，
export default defineComponent({
  // templte中使用的数据
  data: () => ({
    drawer: ref(false),
    isCollapse: true,
    leList: [] as MenuInfo[],
    funId: "",
    studentId: null as number | null,
    imgStr: "",
    markList: [] as OptionItem[],
    scoreList: [] as ScoreItem[],
    info: {} as StudentItem,
    timer: null as any,
    currentTime: formatTime(new Date()),
    handleOpen : (key: string, keyPath: string[]) => {
                                console.log(key, keyPath)},
    handleClose : (key: string, keyPath: string[]) => {
                                console.log(key, keyPath)
}
  }),
  async created(){
    message(this,""+this.info.personId)
    let res = await getPhotoImageStr("photo/" + this.userInfo.perName+this.userInfo.username + ".jpg");
    this.imgStr = res.data;
    res = await getStudentIntroduceData(this.studentId);
    this.info = res.data.info;
    this.studentId = this.info.studentId;
    this.markList = res.data.markList;
    this.scoreList = res.data.scoreList;
    console.log(this.scoreList);
  },
  //生命周期函数  mounted() 在实例挂载之后调用， 设置定期刷新控制台时间
  mounted() {
  window.addEventListener('unload', e => this.unloadHandler());

    if (this.timer) {
      clearInterval(this.timer);
    } else {
      this.timer = setInterval(() => {
        this.currentTime = formatTime(new Date());
      }, 1000);
    }
  },
  // 生命周期函数  unmounted() 在实例销毁之后调用，清除定期刷新控制台时间
  unmounted() {
    clearInterval(this.timer);
  },
  // 计算属性 用于计算属性，计算属性的结果会被缓存，除非依赖的响应式属性变化才会重新计算 当用户信息和菜单更新后页面会重新显然，显示主界面
  computed: {
    ...mapState(useAppStore, ["systemConfig"]),
    ...mapState(useAppStore, ["userInfo"]),
    filteredList() {
      return this.scoreList.filter(item => item.mark > 0);
    },
  },
  methods: {
    FullScreen(){
      document.getElementById('app').requestFullscreen()
    },
    async uploadFile() {
      const file = document.querySelector("#file") as any;
      if (file.files == null || file.files.length == 0) {
        message(this, "请选择文件！");
        return;
      }
      const res = await uploadPhoto(
        "photo/" + this.userInfo.perName+this.userInfo.username + ".jpg",
        file.files[0]
      );

      if (res.code === 0) {
        message(this, "上传成功");
        window.location.reload();
      } else {
        message(this, "上传失败");
      }
    },
    handleClose1(done: () => void){
    ElMessageBox.confirm('确认关闭？')
    .then(() => {
    done();
    })
    },
    teacherIntroduce(){
      router.push("/TeacherIntroduce")
    },
    goToScore(){
      router.push("/score-table-panel")
    },
    goToCourse(){
      router.push("/course-panel")
    },
    goToTeacher(){
      router.push("/teacher-panel")
    },
    goToStudent(){
      router.push("/student-panel")
    },
    goToMenu(){
      router.push("/menu-manage-panel")
    },
    goToDictionary(){
      router.push("/dictionary-manage-panel")
    },
    studentIntroduce(){
      router.push("/StudentIntroduce");
    },
    courseChoose(){
      router.push("/CourseChoose");
    },
    goToMain(){
      router.push("/MainPage");
    },
    changePswd(){
      router.push("/Password");
    },
    unloadHandler () {
    this.logout();
  },
    switchCollapse(){
      this.isCollapse = !this.isCollapse;
    },
    // 退出登录
    logout() {
      const store = useAppStore();
      store.logout();
      router.push("/Login");
    },
    // 获取二级菜单
    // 路由跳转 如果路由名为ProjectHtml或者ProjectVideo则打开新的窗口 其他的路由跳转到对应的页面
    routerName(name: string) {
      if (name == null || name == undefined || name == "") {
        return;
      }
      if (name == "ProjectHtml") {
        this.openProjectHtml();
        return;
      }
      if (name == "ProjectVideo") {
        this.openProjectVideo();
        return;
      }
      router.push("/" + name);
    },
    // 打开新窗口 显示projectDoc.html内容，这个文件在public目录下,同学要修改该页面的内容，作为项目详细介绍的页面，用于检查作业的老师直接打开该页面即可看到作业的详细介绍
    openProjectHtml() {
      window.open("projectDoc.html");
    },
    // 打开新窗口 显示projectVideo.html内容，这个文件在public目录下,同学要录制项目特色视频，作为项目特色介绍视频包房，用于检查作业的老师直接打开该页面查看项目特色
    openProjectVideo() {
      window.open("projectVideo.html");
    },
    
  },
});
</script>
<!-- 这个是系统主页面的样式，同学可以根据自己的喜好修改 -->
<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.carousel{
  height:500px;
  align-items: center;
  width: 80%;
}
.box-card {
  margin-top: 50px;
  height: 220px;
  width: 95%;
}
.icon1 {
  border-radius: 50%;
  width: 44px;
  height: 44px;
}
.icon3 {
  width: 44px;
  height: 44px;
  margin-top: 8px;
}
.el-menu--horizontal > .el-menu-item {
  height: 44px;
  line-height: 44px;
  font-family: SourceHanSansCN-Regular;
  font-size: 16px;
  color: #ffffff;
  letter-spacing: 0;
  font-weight: 400;
}
.el-menu--horizontal > .el-menu-item.is-active {
  background-color: rgb(197, 24, 32) !important;
}
.topHeader {
  padding: 0px;
}
.m-header {
  height: 60px !important;
  background-color: rgb(255, 255, 255);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.header2 {
  display: flex;
  flex-direction: column;
  margin: 0;
}
.header {
  display: flex;
  flex-direction: row;
  margin: 0;
}
.right {
  cursor: pointer;
  display: flex;
  flex-direction: row;
}
.scrollar {
  height: 100%;
  overflow-x: hidden;
  background-color: rgb(44, 48, 57);
}

.pername {
  margin-top: 15px;
  font-size: 20px;
  color: gray;
  font-weight: bold;
}

.titile {
  font-size: 29px;
  color: #0067ff;
  letter-spacing: 0;
  font-weight: bold;
  background-color: #ffffff;
  width: 180px;
  height: 40px;
  margin-top: 10px;
  margin-left: 40px;
}
.right-header {
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #47474a;
  letter-spacing: 0;
  font-weight: 400;
  margin-top: 20px;
  margin-right: 30px;
}
.shuxian {
  margin-top: 5px;
  margin-bottom: 5px;
  height: 50px;
  width: 1px;
  background: #cfd2d7;
  margin-right: 30px;
}
.tuichu {
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #47474a;
  letter-spacing: 0;
  font-weight: 400;
  margin-top: 20px;
  margin-right: 30px;
}
.icon_tuichu {
  margin-top: 23px;
  margin-right: 10px;
  background: url("/loginOut.png");
  width: 14px;
  height: 14px;
}
.image {
  width: 30px;
  margin-top: 15px;
  margin-right: 20px;
  height: 30px;
}
.search {
  margin-top: 23px;
  margin-right: 30px;
  background: url("/serch.png");
  width: 18px;
  height: 18px;
}
.question {
  margin-top: 23px;
  margin-right: 30px;
  background: url("/question.png");
  width: 18px;
  height: 18px;
}
.alarm {
  margin-top: 23px;
  margin-right: 80px;
  background: url("/alarm.png");
  width: 18px;
  height: 18px;
}
.leftIcon {
  cursor: pointer;
  margin-top: 23px;
  margin-left: 18px;
  background: url("/leftright.png");
  width: 18px;
  height: 18px;
}

.outer1 {
  background-color: rgba(255, 255, 255, 1);
  height: 70px;
  width: 100%;
}

.block1 {
  width: 95%;
  height: 44px;
  margin: 13px 0 0 35px;
}

.section1 {
  width: 135px;
  height: 40px;
  background: url("/sdu.png") 100% no-repeat;
  margin-top: 2px;
}

.section2 {
  background-color: rgba(147, 14, 20, 1);
  width: 1px;
  height: 35px;
  margin: 5px 0 0 26px;
}

.txt1 {
  width: 216px;
  height: 29px;
  overflow-wrap: break-word;
  color: rgba(147, 14, 20, 1);
  font-size: 24px;
  font-family: jiangxizhuokai;
  text-align: right;
  white-space: nowrap;
  line-height: 29px;
  display: block;
  margin: 8px 0 0 19px;
}

.icon1 {
  width: 64px;
  height: 64px;
}
.icon2{
  height: 70px;
}
.section3 {
  width: 140px;
  height: 33px;
  margin: 7px 0 0 14px;
  background-color: rgba(255, 255, 255, 1);
}

.word1 {
  width: 36px;
  height: 12px;
  overflow-wrap: break-word;
  color: #2d2d2d;
  font-size: 12px;
  font-family: SourceHanSansCN-Regular;
  text-align: right;
  white-space: nowrap;
  line-height: 12px;
  display: block;
}

.info {
  width: 140px;
  height: 14px;
  font-size: 12px;
  overflow-wrap: break-word;
  font-family: SourceHanSansCN-Regular, SourceHanSansCN;
  font-weight: 400;
  color: #2d2d2d;
  white-space: nowrap;
  line-height: 21px;
}

.section4 {
  background-color: rgba(229, 227, 227, 1);
  width: 1px;
  height: 33px;
  margin: 7px 0 0 25px;
}

.section5 {
  width: 20px;
  height: 33px;
  margin: 9px 0 0 35px;
  cursor: pointer;
}

.section6 {
  width: 20px;
  height: 32px;
  margin: 9px 0 0 25px;
  cursor: pointer;
}

.info2 {
  width: 20px;
  height: 10px;
  overflow-wrap: break-word;
  color: rgba(45, 45, 45, 1);
  font-size: 10px;
  font-family: SourceHanSansCN-Regular;
  text-align: right;
  white-space: nowrap;
  line-height: 10px;
  margin-top: 8px;
  display: block;
}

.flex-col {
  display: flex;
  flex-direction: column;
}
.flex-row {
  display: flex;
  flex-direction: row;
}
.drawer {

  display: flex;
  flex-direction: column;
  align-items: center;
}

.justify-between {
  display: flex;
  justify-content: space-between;
}

.shuaxin {
  width: 20px;
  height: 10px;
  overflow-wrap: break-word;
  color: rgba(45, 45, 45, 1);
  font-size: 10px;
  text-align: right;
  white-space: nowrap;
  line-height: 10px;
  margin-top: 8px;
}

.shuxinImage {
  width: 16px;
  height: 14px;
  background: url("/refresh.png");
  margin-left: 3px;
}
.tuichuImage {
  width: 14px;
  height: 14px;
  background: url("/tuichu.png");
  margin-left: 5px;
}
.box1 {
  width: 235px;
  height: 24px;
  background: url("/gradms.png") -1px -1px no-repeat;
  margin: 12px 0 0 19px;
}
</style>
