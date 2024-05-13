import{s as K,g as Q,u as R,h as W,j as X,c as P}from"../chunks/scheduler.B2kig3xY.js";import{S as Y,i as Z,e as p,s as y,c as g,a as H,l as D,f as I,d,m as n,g as k,h as L,w as ee,x as te,t as ae,b as le,o as se,j as ne}from"../chunks/index.B_VNfN9e.js";import{a as ie}from"../chunks/auth.service.s0lGaw1h.js";import{i as re,u as oe}from"../chunks/store.cPuX3XdM.js";const ue=!1,ce=!0,be=Object.freeze(Object.defineProperty({__proto__:null,prerender:ce,ssr:ue},Symbol.toStringTag,{value:"Module"}));function $(r){let e,a='<a class="nav-link" aria-current="page" href="/instructors">Instructors</a>';return{c(){e=p("li"),e.innerHTML=a,this.h()},l(t){e=g(t,"LI",{class:!0,"data-svelte-h":!0}),D(e)!=="svelte-1zibag"&&(e.innerHTML=a),this.h()},h(){n(e,"class","nav-item")},m(t,u){k(t,e,u)},d(t){t&&d(e)}}}function F(r){let e,a='<a class="nav-link" href="/lessons">Lessons</a>';return{c(){e=p("li"),e.innerHTML=a,this.h()},l(t){e=g(t,"LI",{class:!0,"data-svelte-h":!0}),D(e)!=="svelte-m7md9p"&&(e.innerHTML=a),this.h()},h(){n(e,"class","nav-item")},m(t,u){k(t,e,u)},d(t){t&&d(e)}}}function G(r){let e,a='<a class="nav-link" href="/account">Account</a>';return{c(){e=p("li"),e.innerHTML=a,this.h()},l(t){e=g(t,"LI",{class:!0,"data-svelte-h":!0}),D(e)!=="svelte-1mqkhhp"&&(e.innerHTML=a),this.h()},h(){n(e,"class","nav-item")},m(t,u){k(t,e,u)},d(t){t&&d(e)}}}function J(r){let e,a=r[1].name+"",t,u,c,o="Log Out",m,E;return{c(){e=p("span"),t=ae(a),u=y(),c=p("button"),c.textContent=o,this.h()},l(i){e=g(i,"SPAN",{class:!0});var s=H(e);t=le(s,a),s.forEach(d),u=I(i),c=g(i,"BUTTON",{type:!0,class:!0,"data-svelte-h":!0}),D(c)!=="svelte-ha1d8x"&&(c.textContent=o),this.h()},h(){n(e,"class","navbar-text me-2"),n(c,"type","button"),n(c,"class","btn btn-primary")},m(i,s){k(i,e,s),L(e,t),k(i,u,s),k(i,c,s),m||(E=se(c,"click",ie.logout),m=!0)},p(i,s){s&2&&a!==(a=i[1].name+"")&&ne(t,a)},d(i){i&&(d(e),d(u),d(c)),m=!1,E()}}}function fe(r){let e,a,t,u="DriveMatch",c,o,m='<span class="navbar-toggler-icon"></span>',E,i,s,x=r[0]&&r[1].user_roles&&r[1].user_roles.includes("admin"),O,S,U,C,V,M,N,v=x&&$(),_=r[0]&&F(),h=r[0]&&G(),f=r[0]&&J(r);const w=r[3].default,b=Q(w,r,r[2],null);return{c(){e=p("nav"),a=p("div"),t=p("a"),t.textContent=u,c=y(),o=p("button"),o.innerHTML=m,E=y(),i=p("div"),s=p("ul"),v&&v.c(),O=y(),_&&_.c(),S=y(),h&&h.c(),U=y(),C=p("div"),f&&f.c(),V=y(),M=p("div"),b&&b.c(),this.h()},l(l){e=g(l,"NAV",{class:!0});var T=H(e);a=g(T,"DIV",{class:!0});var j=H(a);t=g(j,"A",{class:!0,href:!0,"data-svelte-h":!0}),D(t)!=="svelte-1j6dbgj"&&(t.textContent=u),c=I(j),o=g(j,"BUTTON",{class:!0,type:!0,"data-bs-toggle":!0,"data-bs-target":!0,"aria-controls":!0,"aria-expanded":!0,"aria-label":!0,"data-svelte-h":!0}),D(o)!=="svelte-1nsq9qc"&&(o.innerHTML=m),E=I(j),i=g(j,"DIV",{class:!0,id:!0});var q=H(i);s=g(q,"UL",{class:!0});var A=H(s);v&&v.l(A),O=I(A),_&&_.l(A),S=I(A),h&&h.l(A),A.forEach(d),U=I(q),C=g(q,"DIV",{class:!0});var z=H(C);f&&f.l(z),z.forEach(d),q.forEach(d),j.forEach(d),T.forEach(d),V=I(l),M=g(l,"DIV",{class:!0});var B=H(M);b&&b.l(B),B.forEach(d),this.h()},h(){n(t,"class","navbar-brand"),n(t,"href","/"),n(o,"class","navbar-toggler"),n(o,"type","button"),n(o,"data-bs-toggle","collapse"),n(o,"data-bs-target","#navbarNav"),n(o,"aria-controls","navbarNav"),n(o,"aria-expanded","false"),n(o,"aria-label","Toggle navigation"),n(s,"class","navbar-nav me-auto mb-2 mb-lg-0"),n(C,"class","d-flex"),n(i,"class","collapse navbar-collapse"),n(i,"id","navbarNav"),n(a,"class","container-fluid"),n(e,"class","navbar navbar-expand-lg bg-light"),n(M,"class","container mt-3")},m(l,T){k(l,e,T),L(e,a),L(a,t),L(a,c),L(a,o),L(a,E),L(a,i),L(i,s),v&&v.m(s,null),L(s,O),_&&_.m(s,null),L(s,S),h&&h.m(s,null),L(i,U),L(i,C),f&&f.m(C,null),k(l,V,T),k(l,M,T),b&&b.m(M,null),N=!0},p(l,[T]){T&3&&(x=l[0]&&l[1].user_roles&&l[1].user_roles.includes("admin")),x?v||(v=$(),v.c(),v.m(s,O)):v&&(v.d(1),v=null),l[0]?_||(_=F(),_.c(),_.m(s,S)):_&&(_.d(1),_=null),l[0]?h||(h=G(),h.c(),h.m(s,null)):h&&(h.d(1),h=null),l[0]?f?f.p(l,T):(f=J(l),f.c(),f.m(C,null)):f&&(f.d(1),f=null),b&&b.p&&(!N||T&4)&&R(b,w,l,l[2],N?X(w,l[2],T,null):W(l[2]),null)},i(l){N||(ee(b,l),N=!0)},o(l){te(b,l),N=!1},d(l){l&&(d(e),d(V),d(M)),v&&v.d(),_&&_.d(),h&&h.d(),f&&f.d(),b&&b.d(l)}}}function de(r,e,a){let t,u;P(r,re,m=>a(0,t=m)),P(r,oe,m=>a(1,u=m));let{$$slots:c={},$$scope:o}=e;return r.$$set=m=>{"$$scope"in m&&a(2,o=m.$$scope)},[t,u,o,c]}class pe extends Y{constructor(e){super(),Z(this,e,de,fe,K,{})}}export{pe as component,be as universal};
