import{d as w,m as l,l as c,_ as h,c as _,e as s,w as r,v as n,o as P}from"./index-d699cdc0.js";const p=w({data:()=>({passw:"password",oldPassword:"",newPassword:"",checkPassword:"",msg:""}),methods:{submitPassword(){var e="";if(this.oldPassword===void 0||this.oldPassword==="")e="旧密码为空不能修改";else if(this.newPassword===void 0||this.newPassword==="")e="新密码为空不能修改";else if(this.oldPassword===this.newPassword)e="新密码和旧密码相同，不能修改";else if(this.checkPassword!==this.newPassword)e="新密码和确认密码不相同，不能修改";else{for(var o,a=!1,t=!1,i=!1,d=0;d<this.newPassword.length;d++)o=this.newPassword.charAt(d),o>="a"&&o<="z"||o>="A"&&o<="Z"?a=!0:o>="0"&&o<="9"?t=!0:i=!0;!a||!t||!i?e="密码至少包含大写字母、小写字母、数字和符号两种以上的类型，请重新输入！":this.newPassword.length<8&&(e="密码长度必须大于等于8个字符，请重新输入！")}e!==""?l(this,e):c({oldPassword:this.oldPassword,newPassword:this.newPassword}).then(u=>{u.code==0?l(this,"提交成功"):l(this,u.msg)})}}});const v={class:"base_form"},f=s("div",{class:"base_header"},[s("div",{class:"blue_column"}),s("div",{class:"base_title"},"修改密码")],-1),B=s("div",{class:"base_prompt"}," 密码强度要求三种字符类型（大小写、数字、特殊字符）及以上且密码长度为8位及以上！ ",-1),m={class:"oldPassword flex-row"},E=s("div",{class:"oldPass"},"旧密码",-1),F={class:"newPassword flex-row"},C=s("div",{class:"newPass"},"请输入新密码",-1),A=s("div",{class:"alarm"},"*密码长度最大为20位",-1),g={class:"reconfirmP flex-row"},b=s("div",{class:"rPass"},"再次输入新密码",-1),k=s("div",{class:"alarm"},"*密码长度最大为20位",-1),D={class:"main8 flex-row justify-between"},y=s("span",{class:"word20"},"修改",-1),$=[y],x=s("div",{class:"group9 flex-col"},[s("span",{class:"info8"},"重置")],-1);function V(e,o,a,t,i,d){return P(),_("div",v,[f,B,s("div",m,[E,s("div",null,[r(s("input",{class:"inputWidth","onUpdate:modelValue":o[0]||(o[0]=u=>e.oldPassword=u),type:"password"},null,512),[[n,e.oldPassword]])])]),s("div",F,[C,s("div",null,[r(s("input",{class:"inputWidth","onUpdate:modelValue":o[1]||(o[1]=u=>e.newPassword=u),type:"password"},null,512),[[n,e.newPassword]])])]),A,s("div",g,[b,s("div",null,[r(s("input",{class:"inputWidth","onUpdate:modelValue":o[2]||(o[2]=u=>e.checkPassword=u),type:"password"},null,512),[[n,e.checkPassword]])])]),k,s("div",D,[s("div",{class:"group8 flex-col",onClick:o[3]||(o[3]=u=>e.submitPassword())},$),x])])}const W=h(p,[["render",V]]);export{W as default};
