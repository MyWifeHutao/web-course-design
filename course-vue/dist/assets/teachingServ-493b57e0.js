import{$ as t}from"./index-d699cdc0.js";async function c(s){return(await t("/api/course/getCourseList",{numName:s})).data}async function o(s){return await t("/api/course/courseDelete",{courseId:s})}async function u(s){return await t("/api/course/courseSave",s)}async function p(){return(await t("/api/score/getStudentItemOptionList",null)).itemList}async function y(){return(await t("/api/essay/getTeacherItemOptionList",null)).itemList}async function f(){return(await t("/api/score/getCourseItemOptionList",null)).itemList}async function g(s,e){return(await t("/api/score/getScoreList",{studentId:s,courseId:e})).data}async function L(s){return(await t("/api/active/getActiveList",{studentId:s})).data}async function w(s,e,a,r){return await t("/api/score/scoreSave",{scoreId:s,studentId:e,courseId:a,mark:r})}async function v(s,e){return await t("/api/active/activeEditSave",{studentId:s,SheOrJain:e.SheOrJain,data:e.data,hour:e.hour,aware:e.aware,friend:e.friend})}async function S(s,e){return await t("/api/essay/essayEditSave",{teacherId:s,data:e.data,book:e.book})}async function l(s){return(await t("/api/essay/getEssayList",{teacherId:s})).data}async function d(s){return await t("/api/score/scoreDelete",{scoreId:s})}export{p as a,f as b,d as c,L as d,v as e,l as f,g,y as h,S as i,c as j,u as k,o as l,w as s};