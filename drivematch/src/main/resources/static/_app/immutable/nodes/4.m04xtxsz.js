import{s as Et,n as ft,r as yt,c as ht}from"../chunks/scheduler.B2kig3xY.js";import{S as It,i as Tt,e as m,s as A,c as d,l as R,f as k,a as y,d as u,m as _,g as P,h as o,n as Q,o as tt,q as _t,t as et,b as lt,j as mt,r as dt}from"../chunks/index.DkwajFew.js";import{e as W}from"../chunks/each.D6YF6ztN.js";import{p as At}from"../chunks/stores.tOnwfUHy.js";import{a as pt,j as kt}from"../chunks/store.CgiLbMJU.js";function vt(r,l,a){const s=r.slice();return s[11]=l[a],s[13]=a,s}function gt(r,l,a){const s=r.slice();return s[3]=l[a],s}function bt(r){let l,a,s=r[3].name+"",i,v,n,c=r[3].email+"",I,E;return{c(){l=m("tr"),a=m("td"),i=et(s),v=A(),n=m("td"),I=et(c),E=A()},l(f){l=d(f,"TR",{});var C=y(l);a=d(C,"TD",{});var L=y(a);i=lt(L,s),L.forEach(u),v=k(C),n=d(C,"TD",{});var T=y(n);I=lt(T,c),T.forEach(u),E=k(C),C.forEach(u)},m(f,C){P(f,l,C),o(l,a),o(a,i),o(l,v),o(l,n),o(n,I),o(l,E)},p(f,C){C&4&&s!==(s=f[3].name+"")&&mt(i,s),C&4&&c!==(c=f[3].email+"")&&mt(I,c)},d(f){f&&u(l)}}}function Ct(r){let l,a,s=r[13]+1+"",i,v;return{c(){l=m("li"),a=m("a"),i=et(s),v=A(),this.h()},l(n){l=d(n,"LI",{class:!0});var c=y(l);a=d(c,"A",{class:!0,href:!0});var I=y(a);i=lt(I,s),I.forEach(u),v=k(c),c.forEach(u),this.h()},h(){_(a,"class","page-link"),_(a,"href","/instructors?page="+(r[13]+1)),dt(a,"active",r[0]==r[13]+1),_(l,"class","page-item")},m(n,c){P(n,l,c),o(l,a),o(a,i),o(l,v)},p(n,c){c&1&&dt(a,"active",n[0]==n[13]+1)},d(n){n&&u(l)}}}function Lt(r){let l,a="Create Instructor",s,i,v,n,c,I="Name",E,f,C,L,T,p,w="E-Mail",j,D,X,B,at="Submit",F,M,nt="All Instructors",Y,N,V,rt='<tr><th scope="col">Name</th> <th scope="col">E-Mail</th></tr>',Z,q,G,z,H,$,st,O=W(r[2]),g=[];for(let t=0;t<O.length;t+=1)g[t]=bt(gt(r,O,t));let U=W(Array(r[1])),b=[];for(let t=0;t<U.length;t+=1)b[t]=Ct(vt(r,U,t));return{c(){l=m("h1"),l.textContent=a,s=A(),i=m("form"),v=m("div"),n=m("div"),c=m("label"),c.textContent=I,E=A(),f=m("input"),C=A(),L=m("div"),T=m("div"),p=m("label"),p.textContent=w,j=A(),D=m("input"),X=A(),B=m("button"),B.textContent=at,F=A(),M=m("h1"),M.textContent=nt,Y=A(),N=m("table"),V=m("thead"),V.innerHTML=rt,Z=A(),q=m("tbody");for(let t=0;t<g.length;t+=1)g[t].c();G=A(),z=m("nav"),H=m("ul");for(let t=0;t<b.length;t+=1)b[t].c();this.h()},l(t){l=d(t,"H1",{class:!0,"data-svelte-h":!0}),R(l)!=="svelte-1d4wv4r"&&(l.textContent=a),s=k(t),i=d(t,"FORM",{class:!0});var h=y(i);v=d(h,"DIV",{class:!0});var e=y(v);n=d(e,"DIV",{class:!0});var x=y(n);c=d(x,"LABEL",{class:!0,for:!0,"data-svelte-h":!0}),R(c)!=="svelte-15kbfjq"&&(c.textContent=I),E=k(x),f=d(x,"INPUT",{class:!0,id:!0,type:!0}),x.forEach(u),e.forEach(u),C=k(h),L=d(h,"DIV",{class:!0});var ot=y(L);T=d(ot,"DIV",{class:!0});var J=y(T);p=d(J,"LABEL",{class:!0,for:!0,"data-svelte-h":!0}),R(p)!=="svelte-14n6ern"&&(p.textContent=w),j=k(J),D=d(J,"INPUT",{class:!0,id:!0,type:!0}),J.forEach(u),ot.forEach(u),X=k(h),B=d(h,"BUTTON",{type:!0,class:!0,"data-svelte-h":!0}),R(B)!=="svelte-iid99c"&&(B.textContent=at),h.forEach(u),F=k(t),M=d(t,"H1",{"data-svelte-h":!0}),R(M)!=="svelte-5ubvwn"&&(M.textContent=nt),Y=k(t),N=d(t,"TABLE",{class:!0});var K=y(N);V=d(K,"THEAD",{"data-svelte-h":!0}),R(V)!=="svelte-5i5eob"&&(V.innerHTML=rt),Z=k(K),q=d(K,"TBODY",{});var it=y(q);for(let S=0;S<g.length;S+=1)g[S].l(it);it.forEach(u),K.forEach(u),G=k(t),z=d(t,"NAV",{});var ct=y(z);H=d(ct,"UL",{class:!0});var ut=y(H);for(let S=0;S<b.length;S+=1)b[S].l(ut);ut.forEach(u),ct.forEach(u),this.h()},h(){_(l,"class","mt-3"),_(c,"class","form-label"),_(c,"for","name"),_(f,"class","form-control"),_(f,"id","name"),_(f,"type","text"),_(n,"class","col"),_(v,"class","row mb-3"),_(p,"class","form-label"),_(p,"for","email"),_(D,"class","form-control"),_(D,"id","earnings"),_(D,"type","email"),_(T,"class","col"),_(L,"class","row mb-3"),_(B,"type","button"),_(B,"class","btn btn-primary"),_(i,"class","mb-5"),_(N,"class","table"),_(H,"class","pagination")},m(t,h){P(t,l,h),P(t,s,h),P(t,i,h),o(i,v),o(v,n),o(n,c),o(n,E),o(n,f),Q(f,r[3].name),o(i,C),o(i,L),o(L,T),o(T,p),o(T,j),o(T,D),Q(D,r[3].email),o(i,X),o(i,B),P(t,F,h),P(t,M,h),P(t,Y,h),P(t,N,h),o(N,V),o(N,Z),o(N,q);for(let e=0;e<g.length;e+=1)g[e]&&g[e].m(q,null);P(t,G,h),P(t,z,h),o(z,H);for(let e=0;e<b.length;e+=1)b[e]&&b[e].m(H,null);$||(st=[tt(f,"input",r[7]),tt(D,"input",r[8]),tt(B,"click",r[4])],$=!0)},p(t,[h]){if(h&8&&f.value!==t[3].name&&Q(f,t[3].name),h&8&&D.value!==t[3].email&&Q(D,t[3].email),h&4){O=W(t[2]);let e;for(e=0;e<O.length;e+=1){const x=gt(t,O,e);g[e]?g[e].p(x,h):(g[e]=bt(x),g[e].c(),g[e].m(q,null))}for(;e<g.length;e+=1)g[e].d(1);g.length=O.length}if(h&3){U=W(Array(t[1]));let e;for(e=0;e<U.length;e+=1){const x=vt(t,U,e);b[e]?b[e].p(x,h):(b[e]=Ct(x),b[e].c(),b[e].m(H,null))}for(;e<b.length;e+=1)b[e].d(1);b.length=U.length}},i:ft,o:ft,d(t){t&&(u(l),u(s),u(i),u(F),u(M),u(Y),u(N),u(G),u(z)),_t(g,t),_t(b,t),$=!1,yt(st)}}}let Pt=4;function Dt(r,l,a){let s,i;ht(r,kt,p=>a(5,s=p)),ht(r,At,p=>a(6,i=p));const v=i.url.origin;let n,c=0,I=[],E={id:null,email:null,name:null};onMount(()=>{f()});function f(){let p="?pageSize="+Pt+"&pageNumber="+n;var w={method:"get",url:v+"/api/instructor"+p,headers:{Authorization:"Bearer "+s}};pt(w).then(function(j){a(2,I=j.data.content),a(1,c=j.data.totalPages)}).catch(function(j){alert("Could not get instructors"),console.log(j)})}function C(){var p={method:"post",url:v+"/api/instructor",headers:{"Content-Type":"application/json",Authorization:"Bearer "+s},data:E};pt(p).then(function(w){alert("Instructor created"),f()}).catch(function(w){alert("Could not create Instructor"),console.log(w)})}function L(){E.name=this.value,a(3,E)}function T(){E.email=this.value,a(3,E)}return r.$$.update=()=>{if(r.$$.dirty&96&&s!==""){let p=i.url.searchParams;p.has("page")?a(0,n=p.get("page")):a(0,n="1"),f()}},[n,c,I,E,C,s,i,L,T]}class Mt extends It{constructor(l){super(),Tt(this,l,Dt,Lt,Et,{})}}export{Mt as component};
