import{B as t}from"./index-82efd59e.js";async function i(){return(await t("/api/base/getRoleOptionItemList",{})).itemList}async function r(e){return(await t("/api/base/getDictionaryOptionItemList",{code:e})).itemList}async function o(){return await t("/api/base/getMenuTreeNodeList",{})}async function c(e){return await t("/api/base/menuDelete",{id:e})}async function u(e,a){return await t("/api/base/menuSave",{editType:e,node:a})}async function y(){return await t("/api/base/getDictionaryTreeNodeList",{})}async function p(e){return await t("/api/base/dictionaryDelete",{id:e})}async function m(e){return await t("/api/base/dictionarySave",e)}export{o as a,c as b,y as c,m as d,p as e,r as f,i as g,u as m};
