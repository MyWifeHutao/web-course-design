import{d as y,q as b,s as A,x as D,m as n,y as w,z as I,_ as k,c as L,e as t,A as l,B as f,h as i,C as c,k as P,D as S,F as j,G as x,o as H,p as N,i as T,H as $}from"./index-d699cdc0.js";/* empty css                     *//* empty css                 *//* empty css                  *//* empty css            */import{s as v,h as R,E as V}from"./jspdf.es.min-b630eacc.js";import{e as q}from"./index-eea72723.js";const C=q,G=y({data:()=>({authHeader:b(),valid:!1,uploadIsLoading:!1,studentId:null,imgStr:"",info:{},feeList:[],markList:[],scoreList:[],nameRules:[],emailRules:[]}),async created(){let e=await A(this.studentId);this.info=e.data.info,this.studentId=this.info.studentId,this.feeList=e.data.feeList,this.markList=e.data.markList,this.scoreList=e.data.scoreList,e=await D("photo/"+this.info.name+this.info.num+".jpg"),this.imgStr=e.data,this.drawEcharts()},mounted(){const e=document.getElementById("mylog");document.getElementById("mylog").addEventListener("click",()=>{v.isEnabled&&v.request(e)})},computed:{filteredList(){return this.scoreList.filter(e=>e.mark>0)},filteredList2(){return this.markList.filter(e=>parseInt(e.value)>0)}},methods:{htmlToPDF:async(e,u="报表",o="#fff")=>{let F=document.getElementById(e);F.style.padding="0 10px !important";const r=595.28,_=841.89;let d=await R(F,{scale:2,useCORS:!0,backgroundColor:o}),h=d.width/r*_,a=d.height,p=0,E=r,g=r/d.width*d.height,B=d.toDataURL("image/jpeg",1),m=new V("p","pt","a4");if(a<h)m.addImage(B,"JPEG",0,0,E,g);else for(;a>0;)m.addImage(B,"JPEG",0,p,E,g),a-=h,p-=_,a>0&&m.addPage();m.save(u+".pdf")},drawEcharts(){C.init(document.getElementById("myChartBar")).setOption({title:{text:"日常消费"},tooltip:{},xAxis:{data:this.feeList.map(o=>o.title)},yAxis:{},series:[{name:"消费",type:"bar",data:this.feeList.map(o=>o.value)}]}),C.init(document.getElementById("myChartPie")).setOption({title:{text:"成绩分布"},tooltip:{},legend:{orient:"horizontal",x:"center",y:"bottom",data:this.filteredList2.map(o=>o.title)},series:[{type:"pie",data:this.markList}]})},async uploadFile(){const e=document.querySelector("#file");if(e.files==null||e.files.length==0){n(this,"请选择文件！");return}(await w("photo/"+this.info.name+this.info.num+".jpg",e.files[0])).code===0?n(this,"上传成功"):n(this,"上传失败")},downloadPdf(){const e=I("/api/student/getStudentIntroduceItextPdf",this.info.num+".pdf",{studentId:this.studentId});console.log(e)},onSuccess(e){e.code==0?n(this,"上传成功！"):n(this,e.msg)}}});const s=e=>(N("data-v-e8699680"),e=e(),T(),e),O={id:"mylog",class:"base",style:{"background-color":"white","background-image":"url('https://i.miji.bid/2023/12/11/5aa93d090ad41d9e9e7e3a122af55a26.png')"}},W={class:"base_header",style:{"background-color":"white"}},z=s(()=>t("div",{class:"blue_column",style:{"background-color":"white"}},null,-1)),J=s(()=>t("div",{class:"base_title"},"个人画像",-1)),U={class:"outer"},K={class:"section1"},M={class:"head"},Q=["src"],X={class:"name"},Y={class:"contact"},Z={class:"block"},tt=s(()=>t("img",{class:"icon1",referrerpolicy:"no-referrer",src:"https://i.miji.bid/2023/12/10/78ecd946ba4e09e34f873ead5c78ccb6.png"},null,-1)),et={class:"block"},st=s(()=>t("img",{class:"icon1",referrerpolicy:"no-referrer",src:"https://i.miji.bid/2023/12/10/14ecf3b6f88df9c1391f1e70a21e83db.png"},null,-1)),it=s(()=>t("div",{class:"block"},[t("img",{class:"icon1",referrerpolicy:"no-referrer",src:"https://i.miji.bid/2023/12/10/84ec409301b1f17eff1b676f68a710ab.png"}),c("https://github.com/")],-1)),at=P('<div class="selfintro" data-v-e8699680><span class="title" data-v-e8699680>自我介绍</span><div class="block" data-v-e8699680><img class="icon2" src="https://i.miji.bid/2023/12/10/98962ca08228979c13d7a4f01d33caec.png" data-v-e8699680>能力项：沟通协调能力、执行力 </div><p data-v-e8699680>2022级软件工程本科生，拥有较强的执行力，做事干净利落不拖沓、不卡DDL；有一定的领导能力，担任多次小组作业的队长或主要队员；熟练掌握各类办公软件，在创新创业课程中绘制项目宣传PPT；熟练掌握PS等设计软件；熟练掌握剪辑软件</p></div><div class="educatedbg" data-v-e8699680><span class="title" data-v-e8699680>教育背景</span><div style="display:flex;flex-direction:row;" data-v-e8699680><div class="inner1" data-v-e8699680><p class="bword" data-v-e8699680>山东大学<br data-v-e8699680></p>软件工程 <div class="greyword" data-v-e8699680>（本科）</div></div><div class="inner1" data-v-e8699680><p class="greyword" data-v-e8699680>2022.09~至今</p></div></div></div>',2),ut={class:"section2"},ot={class:"score"},dt={class:"moredetails"},lt=s(()=>t("span",{class:"title"},"更多信息",-1)),nt={class:"content"},ct=s(()=>t("td",null,"学号",-1)),rt=s(()=>t("td",null,"学院",-1)),ht=s(()=>t("td",null,"专业",-1)),pt=s(()=>t("td",null,"班级",-1)),mt=s(()=>t("td",null,"证件号码",-1)),ft=s(()=>t("td",null,"性别",-1)),Ft=s(()=>t("td",null,"出生日期",-1)),_t=s(()=>t("td",null,"邮箱",-1)),Et=s(()=>t("td",null,"地址",-1)),gt={colspan:"3"},Bt=s(()=>t("div",{class:"chart"},[t("div",{style:{width:"100%"}},[t("div",{id:"myChartBar",style:{width:"200px",height:"300px"}}),t("div",{id:"myChartPie",style:{width:"350px",height:"300px"}})])],-1));function vt(e,u,o,F,r,_){const d=S,h=j,a=$,p=x;return H(),L("div",O,[t("div",W,[z,J,t("div",null,[l(h,{style:{display:"inline-block","margin-left":"5px"},headers:e.authHeader,action:"/api/student/importFeeDataWeb",data:{studentId:e.studentId},accept:".xlsx,.xls","show-file-list":!0,limit:1,multiple:!1,"on-success":e.onSuccess},{default:f(()=>[l(d,{class:"spacial"},{default:f(()=>[c("消费记录上传")]),_:1})]),_:1},8,["headers","data","on-success"]),l(d,{style:{"margin-left":"5px"},class:"spacial",onClick:u[0]||(u[0]=E=>e.htmlToPDF("mylog","test pdf"))},{default:f(()=>[c("附件下载")]),_:1})])]),t("div",U,[t("div",K,[t("div",M,[t("img",{class:"headimg",referrerpolicy:"no-referrer",src:e.imgStr,onClick:u[1]||(u[1]=()=>{})},null,8,Q),t("span",X,i(e.info.name),1)]),t("div",Y,[t("div",Z,[tt,c(i(e.info.phone),1)]),t("div",et,[st,c(i(e.info.email),1)]),it]),at]),t("div",ut,[t("div",ot,[l(p,{data:e.filteredList,style:{width:"100%"}},{default:f(()=>[l(a,{prop:"courseNum",label:"课程号",width:"180"}),l(a,{prop:"courseName",label:"课程名",width:"180"}),l(a,{prop:"credit",label:"学分"}),l(a,{prop:"mark",label:"成绩"})]),_:1},8,["data"])]),t("div",dt,[lt,t("table",nt,[t("tr",null,[ct,t("td",null,i(e.info.num),1),rt,t("td",null,i(e.info.dept),1)]),t("tr",null,[ht,t("td",null,i(e.info.major),1),pt,t("td",null,i(e.info.className),1)]),t("tr",null,[mt,t("td",null,i(e.info.card),1),ft,t("td",null,i(e.info.genderName),1)]),t("tr",null,[Ft,t("td",null,i(e.info.birthday),1),_t,t("td",null,i(e.info.email),1)]),t("tr",null,[Et,t("td",gt,i(e.info.address),1)])])]),Bt])])])}const kt=k(G,[["render",vt],["__scopeId","data-v-e8699680"]]);export{kt as default};