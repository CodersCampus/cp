/**
 * @license
 * Copyright 2019 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */
const t=window,i=t.ShadowRoot&&(void 0===t.ShadyCSS||t.ShadyCSS.nativeShadow)&&"adoptedStyleSheets"in Document.prototype&&"replace"in CSSStyleSheet.prototype,e=Symbol(),n=new WeakMap;class s{constructor(t,i,n){if(this._$cssResult$=!0,n!==e)throw Error("CSSResult is not constructable. Use `unsafeCSS` or `css` instead.");this.cssText=t,this.t=i}get styleSheet(){let t=this.o;const e=this.t;if(i&&void 0===t){const i=void 0!==e&&1===e.length;i&&(t=n.get(e)),void 0===t&&((this.o=t=new CSSStyleSheet).replaceSync(this.cssText),i&&n.set(e,t))}return t}toString(){return this.cssText}}const r=i?t=>t:t=>t instanceof CSSStyleSheet?(t=>{let i="";for(const e of t.cssRules)i+=e.cssText;return(t=>new s("string"==typeof t?t:t+"",void 0,e))(i)})(t):t
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */;var o;const h=window,a=h.trustedTypes,u=a?a.emptyScript:"",c=h.reactiveElementPolyfillSupport,l={toAttribute(t,i){switch(i){case Boolean:t=t?u:null;break;case Object:case Array:t=null==t?t:JSON.stringify(t)}return t},fromAttribute(t,i){let e=t;switch(i){case Boolean:e=null!==t;break;case Number:e=null===t?null:Number(t);break;case Object:case Array:try{e=JSON.parse(t)}catch(t){e=null}}return e}},f=(t,i)=>i!==t&&(i==i||t==t),d={attribute:!0,type:String,converter:l,reflect:!1,hasChanged:f},p="finalized";class v extends HTMLElement{constructor(){super(),this._$Ei=new Map,this.isUpdatePending=!1,this.hasUpdated=!1,this._$El=null,this._$Eu()}static addInitializer(t){var i;this.finalize(),(null!==(i=this.h)&&void 0!==i?i:this.h=[]).push(t)}static get observedAttributes(){this.finalize();const t=[];return this.elementProperties.forEach(((i,e)=>{const n=this._$Ep(e,i);void 0!==n&&(this._$Ev.set(n,e),t.push(n))})),t}static createProperty(t,i=d){if(i.state&&(i.attribute=!1),this.finalize(),this.elementProperties.set(t,i),!i.noAccessor&&!this.prototype.hasOwnProperty(t)){const e="symbol"==typeof t?Symbol():"__"+t,n=this.getPropertyDescriptor(t,e,i);void 0!==n&&Object.defineProperty(this.prototype,t,n)}}static getPropertyDescriptor(t,i,e){return{get(){return this[i]},set(n){const s=this[t];this[i]=n,this.requestUpdate(t,s,e)},configurable:!0,enumerable:!0}}static getPropertyOptions(t){return this.elementProperties.get(t)||d}static finalize(){if(this.hasOwnProperty(p))return!1;this[p]=!0;const t=Object.getPrototypeOf(this);if(t.finalize(),void 0!==t.h&&(this.h=[...t.h]),this.elementProperties=new Map(t.elementProperties),this._$Ev=new Map,this.hasOwnProperty("properties")){const t=this.properties,i=[...Object.getOwnPropertyNames(t),...Object.getOwnPropertySymbols(t)];for(const e of i)this.createProperty(e,t[e])}return this.elementStyles=this.finalizeStyles(this.styles),!0}static finalizeStyles(t){const i=[];if(Array.isArray(t)){const e=new Set(t.flat(1/0).reverse());for(const t of e)i.unshift(r(t))}else void 0!==t&&i.push(r(t));return i}static _$Ep(t,i){const e=i.attribute;return!1===e?void 0:"string"==typeof e?e:"string"==typeof t?t.toLowerCase():void 0}_$Eu(){var t;this._$E_=new Promise((t=>this.enableUpdating=t)),this._$AL=new Map,this._$Eg(),this.requestUpdate(),null===(t=this.constructor.h)||void 0===t||t.forEach((t=>t(this)))}addController(t){var i,e;(null!==(i=this._$ES)&&void 0!==i?i:this._$ES=[]).push(t),void 0!==this.renderRoot&&this.isConnected&&(null===(e=t.hostConnected)||void 0===e||e.call(t))}removeController(t){var i;null===(i=this._$ES)||void 0===i||i.splice(this._$ES.indexOf(t)>>>0,1)}_$Eg(){this.constructor.elementProperties.forEach(((t,i)=>{this.hasOwnProperty(i)&&(this._$Ei.set(i,this[i]),delete this[i])}))}createRenderRoot(){var e;const n=null!==(e=this.shadowRoot)&&void 0!==e?e:this.attachShadow(this.constructor.shadowRootOptions);return((e,n)=>{i?e.adoptedStyleSheets=n.map((t=>t instanceof CSSStyleSheet?t:t.styleSheet)):n.forEach((i=>{const n=document.createElement("style"),s=t.litNonce;void 0!==s&&n.setAttribute("nonce",s),n.textContent=i.cssText,e.appendChild(n)}))})(n,this.constructor.elementStyles),n}connectedCallback(){var t;void 0===this.renderRoot&&(this.renderRoot=this.createRenderRoot()),this.enableUpdating(!0),null===(t=this._$ES)||void 0===t||t.forEach((t=>{var i;return null===(i=t.hostConnected)||void 0===i?void 0:i.call(t)}))}enableUpdating(t){}disconnectedCallback(){var t;null===(t=this._$ES)||void 0===t||t.forEach((t=>{var i;return null===(i=t.hostDisconnected)||void 0===i?void 0:i.call(t)}))}attributeChangedCallback(t,i,e){this._$AK(t,e)}_$EO(t,i,e=d){var n;const s=this.constructor._$Ep(t,e);if(void 0!==s&&!0===e.reflect){const r=(void 0!==(null===(n=e.converter)||void 0===n?void 0:n.toAttribute)?e.converter:l).toAttribute(i,e.type);this._$El=t,null==r?this.removeAttribute(s):this.setAttribute(s,r),this._$El=null}}_$AK(t,i){var e;const n=this.constructor,s=n._$Ev.get(t);if(void 0!==s&&this._$El!==s){const t=n.getPropertyOptions(s),r="function"==typeof t.converter?{fromAttribute:t.converter}:void 0!==(null===(e=t.converter)||void 0===e?void 0:e.fromAttribute)?t.converter:l;this._$El=s,this[s]=r.fromAttribute(i,t.type),this._$El=null}}requestUpdate(t,i,e){let n=!0;void 0!==t&&(((e=e||this.constructor.getPropertyOptions(t)).hasChanged||f)(this[t],i)?(this._$AL.has(t)||this._$AL.set(t,i),!0===e.reflect&&this._$El!==t&&(void 0===this._$EC&&(this._$EC=new Map),this._$EC.set(t,e))):n=!1),!this.isUpdatePending&&n&&(this._$E_=this._$Ej())}async _$Ej(){this.isUpdatePending=!0;try{await this._$E_}catch(t){Promise.reject(t)}const t=this.scheduleUpdate();return null!=t&&await t,!this.isUpdatePending}scheduleUpdate(){return this.performUpdate()}performUpdate(){var t;if(!this.isUpdatePending)return;this.hasUpdated,this._$Ei&&(this._$Ei.forEach(((t,i)=>this[i]=t)),this._$Ei=void 0);let i=!1;const e=this._$AL;try{i=this.shouldUpdate(e),i?(this.willUpdate(e),null===(t=this._$ES)||void 0===t||t.forEach((t=>{var i;return null===(i=t.hostUpdate)||void 0===i?void 0:i.call(t)})),this.update(e)):this._$Ek()}catch(t){throw i=!1,this._$Ek(),t}i&&this._$AE(e)}willUpdate(t){}_$AE(t){var i;null===(i=this._$ES)||void 0===i||i.forEach((t=>{var i;return null===(i=t.hostUpdated)||void 0===i?void 0:i.call(t)})),this.hasUpdated||(this.hasUpdated=!0,this.firstUpdated(t)),this.updated(t)}_$Ek(){this._$AL=new Map,this.isUpdatePending=!1}get updateComplete(){return this.getUpdateComplete()}getUpdateComplete(){return this._$E_}shouldUpdate(t){return!0}update(t){void 0!==this._$EC&&(this._$EC.forEach(((t,i)=>this._$EO(i,this[i],t))),this._$EC=void 0),this._$Ek()}updated(t){}firstUpdated(t){}}
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */
var w;v[p]=!0,v.elementProperties=new Map,v.elementStyles=[],v.shadowRootOptions={mode:"open"},null==c||c({ReactiveElement:v}),(null!==(o=h.reactiveElementVersions)&&void 0!==o?o:h.reactiveElementVersions=[]).push("1.6.3");const y=window,g=y.trustedTypes,m=g?g.createPolicy("lit-html",{createHTML:t=>t}):void 0,b="$lit$",E=`lit$${(Math.random()+"").slice(9)}$`,I="?"+E,_=`<${I}>`,T=document,A=()=>T.createComment(""),S=t=>null===t||"object"!=typeof t&&"function"!=typeof t,k=Array.isArray,N="[ \t\n\f\r]",O=/<(?:(!--|\/[^a-zA-Z])|(\/?[a-zA-Z][^>\s]*)|(\/?$))/g,D=/-->/g,R=/>/g,C=RegExp(`>|${N}(?:([^\\s"'>=/]+)(${N}*=${N}*(?:[^ \t\n\f\r"'\`<>=]|("|')|))|$)`,"g"),P=/'/g,M=/"/g,$=/^(?:script|style|textarea|title)$/i,L=(t=>(i,...e)=>({_$litType$:t,strings:i,values:e}))(1),x=Symbol.for("lit-noChange"),U=Symbol.for("lit-nothing"),j=new WeakMap,F=T.createTreeWalker(T,129,null,!1);function B(t,i){if(!Array.isArray(t)||!t.hasOwnProperty("raw"))throw Error("invalid template strings array");return void 0!==m?m.createHTML(i):i}const V=(t,i)=>{const e=t.length-1,n=[];let s,r=2===i?"<svg>":"",o=O;for(let i=0;i<e;i++){const e=t[i];let h,a,u=-1,c=0;for(;c<e.length&&(o.lastIndex=c,a=o.exec(e),null!==a);)c=o.lastIndex,o===O?"!--"===a[1]?o=D:void 0!==a[1]?o=R:void 0!==a[2]?($.test(a[2])&&(s=RegExp("</"+a[2],"g")),o=C):void 0!==a[3]&&(o=C):o===C?">"===a[0]?(o=null!=s?s:O,u=-1):void 0===a[1]?u=-2:(u=o.lastIndex-a[2].length,h=a[1],o=void 0===a[3]?C:'"'===a[3]?M:P):o===M||o===P?o=C:o===D||o===R?o=O:(o=C,s=void 0);const l=o===C&&t[i+1].startsWith("/>")?" ":"";r+=o===O?e+_:u>=0?(n.push(h),e.slice(0,u)+b+e.slice(u)+E+l):e+E+(-2===u?(n.push(void 0),i):l)}return[B(t,r+(t[e]||"<?>")+(2===i?"</svg>":"")),n]};class z{constructor({strings:t,_$litType$:i},e){let n;this.parts=[];let s=0,r=0;const o=t.length-1,h=this.parts,[a,u]=V(t,i);if(this.el=z.createElement(a,e),F.currentNode=this.el.content,2===i){const t=this.el.content,i=t.firstChild;i.remove(),t.append(...i.childNodes)}for(;null!==(n=F.nextNode())&&h.length<o;){if(1===n.nodeType){if(n.hasAttributes()){const t=[];for(const i of n.getAttributeNames())if(i.endsWith(b)||i.startsWith(E)){const e=u[r++];if(t.push(i),void 0!==e){const t=n.getAttribute(e.toLowerCase()+b).split(E),i=/([.?@])?(.*)/.exec(e);h.push({type:1,index:s,name:i[2],strings:t,ctor:"."===i[1]?J:"?"===i[1]?X:"@"===i[1]?Y:W})}else h.push({type:6,index:s})}for(const i of t)n.removeAttribute(i)}if($.test(n.tagName)){const t=n.textContent.split(E),i=t.length-1;if(i>0){n.textContent=g?g.emptyScript:"";for(let e=0;e<i;e++)n.append(t[e],A()),F.nextNode(),h.push({type:2,index:++s});n.append(t[i],A())}}}else if(8===n.nodeType)if(n.data===I)h.push({type:2,index:s});else{let t=-1;for(;-1!==(t=n.data.indexOf(E,t+1));)h.push({type:7,index:s}),t+=E.length-1}s++}}static createElement(t,i){const e=T.createElement("template");return e.innerHTML=t,e}}function H(t,i,e=t,n){var s,r,o,h;if(i===x)return i;let a=void 0!==n?null===(s=e._$Co)||void 0===s?void 0:s[n]:e._$Cl;const u=S(i)?void 0:i._$litDirective$;return(null==a?void 0:a.constructor)!==u&&(null===(r=null==a?void 0:a._$AO)||void 0===r||r.call(a,!1),void 0===u?a=void 0:(a=new u(t),a._$AT(t,e,n)),void 0!==n?(null!==(o=(h=e)._$Co)&&void 0!==o?o:h._$Co=[])[n]=a:e._$Cl=a),void 0!==a&&(i=H(t,a._$AS(t,i.values),a,n)),i}class G{constructor(t,i){this._$AV=[],this._$AN=void 0,this._$AD=t,this._$AM=i}get parentNode(){return this._$AM.parentNode}get _$AU(){return this._$AM._$AU}u(t){var i;const{el:{content:e},parts:n}=this._$AD,s=(null!==(i=null==t?void 0:t.creationScope)&&void 0!==i?i:T).importNode(e,!0);F.currentNode=s;let r=F.nextNode(),o=0,h=0,a=n[0];for(;void 0!==a;){if(o===a.index){let i;2===a.type?i=new q(r,r.nextSibling,this,t):1===a.type?i=new a.ctor(r,a.name,a.strings,this,t):6===a.type&&(i=new Q(r,this,t)),this._$AV.push(i),a=n[++h]}o!==(null==a?void 0:a.index)&&(r=F.nextNode(),o++)}return F.currentNode=T,s}v(t){let i=0;for(const e of this._$AV)void 0!==e&&(void 0!==e.strings?(e._$AI(t,e,i),i+=e.strings.length-2):e._$AI(t[i])),i++}}class q{constructor(t,i,e,n){var s;this.type=2,this._$AH=U,this._$AN=void 0,this._$AA=t,this._$AB=i,this._$AM=e,this.options=n,this._$Cp=null===(s=null==n?void 0:n.isConnected)||void 0===s||s}get _$AU(){var t,i;return null!==(i=null===(t=this._$AM)||void 0===t?void 0:t._$AU)&&void 0!==i?i:this._$Cp}get parentNode(){let t=this._$AA.parentNode;const i=this._$AM;return void 0!==i&&11===(null==t?void 0:t.nodeType)&&(t=i.parentNode),t}get startNode(){return this._$AA}get endNode(){return this._$AB}_$AI(t,i=this){t=H(this,t,i),S(t)?t===U||null==t||""===t?(this._$AH!==U&&this._$AR(),this._$AH=U):t!==this._$AH&&t!==x&&this._(t):void 0!==t._$litType$?this.g(t):void 0!==t.nodeType?this.$(t):(t=>k(t)||"function"==typeof(null==t?void 0:t[Symbol.iterator]))(t)?this.T(t):this._(t)}k(t){return this._$AA.parentNode.insertBefore(t,this._$AB)}$(t){this._$AH!==t&&(this._$AR(),this._$AH=this.k(t))}_(t){this._$AH!==U&&S(this._$AH)?this._$AA.nextSibling.data=t:this.$(T.createTextNode(t)),this._$AH=t}g(t){var i;const{values:e,_$litType$:n}=t,s="number"==typeof n?this._$AC(t):(void 0===n.el&&(n.el=z.createElement(B(n.h,n.h[0]),this.options)),n);if((null===(i=this._$AH)||void 0===i?void 0:i._$AD)===s)this._$AH.v(e);else{const t=new G(s,this),i=t.u(this.options);t.v(e),this.$(i),this._$AH=t}}_$AC(t){let i=j.get(t.strings);return void 0===i&&j.set(t.strings,i=new z(t)),i}T(t){k(this._$AH)||(this._$AH=[],this._$AR());const i=this._$AH;let e,n=0;for(const s of t)n===i.length?i.push(e=new q(this.k(A()),this.k(A()),this,this.options)):e=i[n],e._$AI(s),n++;n<i.length&&(this._$AR(e&&e._$AB.nextSibling,n),i.length=n)}_$AR(t=this._$AA.nextSibling,i){var e;for(null===(e=this._$AP)||void 0===e||e.call(this,!1,!0,i);t&&t!==this._$AB;){const i=t.nextSibling;t.remove(),t=i}}setConnected(t){var i;void 0===this._$AM&&(this._$Cp=t,null===(i=this._$AP)||void 0===i||i.call(this,t))}}class W{constructor(t,i,e,n,s){this.type=1,this._$AH=U,this._$AN=void 0,this.element=t,this.name=i,this._$AM=n,this.options=s,e.length>2||""!==e[0]||""!==e[1]?(this._$AH=Array(e.length-1).fill(new String),this.strings=e):this._$AH=U}get tagName(){return this.element.tagName}get _$AU(){return this._$AM._$AU}_$AI(t,i=this,e,n){const s=this.strings;let r=!1;if(void 0===s)t=H(this,t,i,0),r=!S(t)||t!==this._$AH&&t!==x,r&&(this._$AH=t);else{const n=t;let o,h;for(t=s[0],o=0;o<s.length-1;o++)h=H(this,n[e+o],i,o),h===x&&(h=this._$AH[o]),r||(r=!S(h)||h!==this._$AH[o]),h===U?t=U:t!==U&&(t+=(null!=h?h:"")+s[o+1]),this._$AH[o]=h}r&&!n&&this.j(t)}j(t){t===U?this.element.removeAttribute(this.name):this.element.setAttribute(this.name,null!=t?t:"")}}class J extends W{constructor(){super(...arguments),this.type=3}j(t){this.element[this.name]=t===U?void 0:t}}const K=g?g.emptyScript:"";class X extends W{constructor(){super(...arguments),this.type=4}j(t){t&&t!==U?this.element.setAttribute(this.name,K):this.element.removeAttribute(this.name)}}class Y extends W{constructor(t,i,e,n,s){super(t,i,e,n,s),this.type=5}_$AI(t,i=this){var e;if((t=null!==(e=H(this,t,i,0))&&void 0!==e?e:U)===x)return;const n=this._$AH,s=t===U&&n!==U||t.capture!==n.capture||t.once!==n.once||t.passive!==n.passive,r=t!==U&&(n===U||s);s&&this.element.removeEventListener(this.name,this,n),r&&this.element.addEventListener(this.name,this,t),this._$AH=t}handleEvent(t){var i,e;"function"==typeof this._$AH?this._$AH.call(null!==(e=null===(i=this.options)||void 0===i?void 0:i.host)&&void 0!==e?e:this.element,t):this._$AH.handleEvent(t)}}class Q{constructor(t,i,e){this.element=t,this.type=6,this._$AN=void 0,this._$AM=i,this.options=e}get _$AU(){return this._$AM._$AU}_$AI(t){H(this,t)}}const Z=y.litHtmlPolyfillSupport;null==Z||Z(z,q),(null!==(w=y.litHtmlVersions)&&void 0!==w?w:y.litHtmlVersions=[]).push("2.8.0");
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */
var tt,it;class et extends v{constructor(){super(...arguments),this.renderOptions={host:this},this._$Do=void 0}createRenderRoot(){var t,i;const e=super.createRenderRoot();return null!==(t=(i=this.renderOptions).renderBefore)&&void 0!==t||(i.renderBefore=e.firstChild),e}update(t){const i=this.render();this.hasUpdated||(this.renderOptions.isConnected=this.isConnected),super.update(t),this._$Do=((t,i,e)=>{var n,s;const r=null!==(n=null==e?void 0:e.renderBefore)&&void 0!==n?n:i;let o=r._$litPart$;if(void 0===o){const t=null!==(s=null==e?void 0:e.renderBefore)&&void 0!==s?s:null;r._$litPart$=o=new q(i.insertBefore(A(),t),t,void 0,null!=e?e:{})}return o._$AI(t),o})(i,this.renderRoot,this.renderOptions)}connectedCallback(){var t;super.connectedCallback(),null===(t=this._$Do)||void 0===t||t.setConnected(!0)}disconnectedCallback(){var t;super.disconnectedCallback(),null===(t=this._$Do)||void 0===t||t.setConnected(!1)}render(){return x}}et.finalized=!0,et._$litElement$=!0,null===(tt=globalThis.litElementHydrateSupport)||void 0===tt||tt.call(globalThis,{LitElement:et});const nt=globalThis.litElementPolyfillSupport;null==nt||nt({LitElement:et}),(null!==(it=globalThis.litElementVersions)&&void 0!==it?it:globalThis.litElementVersions=[]).push("3.3.3");
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */
const st=(t,i)=>"method"===i.kind&&i.descriptor&&!("value"in i.descriptor)?{...i,finisher(e){e.createProperty(i.key,t)}}:{kind:"field",key:Symbol(),placement:"own",descriptor:{},originalKey:i.key,initializer(){"function"==typeof i.initializer&&(this[i.key]=i.initializer.call(this))},finisher(e){e.createProperty(i.key,t)}};
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */function rt(t){return(i,e)=>void 0!==e?((t,i,e)=>{i.constructor.createProperty(e,t)})(t,i,e):st(t,i)
/**
 * @license
 * Copyright 2021 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */}var ot;null===(ot=window.HTMLSlotElement)||void 0===ot||ot.prototype.assignedElements;
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const ht=function(t){const i=[];let e=0;for(let n=0;n<t.length;n++){let s=t.charCodeAt(n);s<128?i[e++]=s:s<2048?(i[e++]=s>>6|192,i[e++]=63&s|128):55296==(64512&s)&&n+1<t.length&&56320==(64512&t.charCodeAt(n+1))?(s=65536+((1023&s)<<10)+(1023&t.charCodeAt(++n)),i[e++]=s>>18|240,i[e++]=s>>12&63|128,i[e++]=s>>6&63|128,i[e++]=63&s|128):(i[e++]=s>>12|224,i[e++]=s>>6&63|128,i[e++]=63&s|128)}return i},at={byteToCharMap_:null,charToByteMap_:null,byteToCharMapWebSafe_:null,charToByteMapWebSafe_:null,ENCODED_VALS_BASE:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",get ENCODED_VALS(){return this.ENCODED_VALS_BASE+"+/="},get ENCODED_VALS_WEBSAFE(){return this.ENCODED_VALS_BASE+"-_."},HAS_NATIVE_SUPPORT:"function"==typeof atob,encodeByteArray(t,i){if(!Array.isArray(t))throw Error("encodeByteArray takes an array as a parameter");this.init_();const e=i?this.byteToCharMapWebSafe_:this.byteToCharMap_,n=[];for(let i=0;i<t.length;i+=3){const s=t[i],r=i+1<t.length,o=r?t[i+1]:0,h=i+2<t.length,a=h?t[i+2]:0,u=s>>2,c=(3&s)<<4|o>>4;let l=(15&o)<<2|a>>6,f=63&a;h||(f=64,r||(l=64)),n.push(e[u],e[c],e[l],e[f])}return n.join("")},encodeString(t,i){return this.HAS_NATIVE_SUPPORT&&!i?btoa(t):this.encodeByteArray(ht(t),i)},decodeString(t,i){return this.HAS_NATIVE_SUPPORT&&!i?atob(t):function(t){const i=[];let e=0,n=0;for(;e<t.length;){const s=t[e++];if(s<128)i[n++]=String.fromCharCode(s);else if(s>191&&s<224){const r=t[e++];i[n++]=String.fromCharCode((31&s)<<6|63&r)}else if(s>239&&s<365){const r=((7&s)<<18|(63&t[e++])<<12|(63&t[e++])<<6|63&t[e++])-65536;i[n++]=String.fromCharCode(55296+(r>>10)),i[n++]=String.fromCharCode(56320+(1023&r))}else{const r=t[e++],o=t[e++];i[n++]=String.fromCharCode((15&s)<<12|(63&r)<<6|63&o)}}return i.join("")}(this.decodeStringToByteArray(t,i))},decodeStringToByteArray(t,i){this.init_();const e=i?this.charToByteMapWebSafe_:this.charToByteMap_,n=[];for(let i=0;i<t.length;){const s=e[t.charAt(i++)],r=i<t.length?e[t.charAt(i)]:0;++i;const o=i<t.length?e[t.charAt(i)]:64;++i;const h=i<t.length?e[t.charAt(i)]:64;if(++i,null==s||null==r||null==o||null==h)throw new ut;const a=s<<2|r>>4;if(n.push(a),64!==o){const t=r<<4&240|o>>2;if(n.push(t),64!==h){const t=o<<6&192|h;n.push(t)}}}return n},init_(){if(!this.byteToCharMap_){this.byteToCharMap_={},this.charToByteMap_={},this.byteToCharMapWebSafe_={},this.charToByteMapWebSafe_={};for(let t=0;t<this.ENCODED_VALS.length;t++)this.byteToCharMap_[t]=this.ENCODED_VALS.charAt(t),this.charToByteMap_[this.byteToCharMap_[t]]=t,this.byteToCharMapWebSafe_[t]=this.ENCODED_VALS_WEBSAFE.charAt(t),this.charToByteMapWebSafe_[this.byteToCharMapWebSafe_[t]]=t,t>=this.ENCODED_VALS_BASE.length&&(this.charToByteMap_[this.ENCODED_VALS_WEBSAFE.charAt(t)]=t,this.charToByteMapWebSafe_[this.ENCODED_VALS.charAt(t)]=t)}}};class ut extends Error{constructor(){super(...arguments),this.name="DecodeBase64StringError"}}const ct=function(t){return function(t){const i=ht(t);return at.encodeByteArray(i,!0)}(t).replace(/\./g,"")},lt=function(t){try{return at.decodeString(t,!0)}catch(t){console.error("base64Decode failed: ",t)}return null};
/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const ft=()=>
/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function(){if("undefined"!=typeof self)return self;if("undefined"!=typeof window)return window;if("undefined"!=typeof global)return global;throw new Error("Unable to locate global object.")}().p,dt=()=>{try{return ft()||(()=>{if("undefined"==typeof process||void 0===process.env)return;const t=process.env.p;return t?JSON.parse(t):void 0})()||(()=>{if("undefined"==typeof document)return;let t;try{t=document.cookie.match(/__FIREBASE_DEFAULTS__=([^;]+)/)}catch(t){return}const i=t&&lt(t[1]);return i&&JSON.parse(i)})()}catch(t){return void console.info(`Unable to get __FIREBASE_DEFAULTS__ due to: ${t}`)}},pt=t=>{var i,e;return null===(e=null===(i=dt())||void 0===i?void 0:i.emulatorHosts)||void 0===e?void 0:e[t]},vt=()=>{var t;return null===(t=dt())||void 0===t?void 0:t.config},wt=t=>{var i;return null===(i=dt())||void 0===i?void 0:i[`_${t}`]};
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class yt{constructor(){this.reject=()=>{},this.resolve=()=>{},this.promise=new Promise(((t,i)=>{this.resolve=t,this.reject=i}))}wrapCallback(t){return(i,e)=>{i?this.reject(i):this.resolve(e),"function"==typeof t&&(this.promise.catch((()=>{})),1===t.length?t(i):t(i,e))}}}
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function gt(){return"undefined"!=typeof navigator&&"string"==typeof navigator.userAgent?navigator.userAgent:""}class mt extends Error{constructor(t,i,e){super(i),this.code=t,this.customData=e,this.name="FirebaseError",Object.setPrototypeOf(this,mt.prototype),Error.captureStackTrace&&Error.captureStackTrace(this,bt.prototype.create)}}class bt{constructor(t,i,e){this.service=t,this.serviceName=i,this.errors=e}create(t,...i){const e=i[0]||{},n=`${this.service}/${t}`,s=this.errors[t],r=s?function(t,i){return t.replace(Et,((t,e)=>{const n=i[e];return null!=n?String(n):`<${e}?>`}))}(s,e):"Error",o=`${this.serviceName}: ${r} (${n}).`;return new mt(n,o,e)}}const Et=/\{\$([^}]+)}/g;function It(t,i){if(t===i)return!0;const e=Object.keys(t),n=Object.keys(i);for(const s of e){if(!n.includes(s))return!1;const e=t[s],r=i[s];if(_t(e)&&_t(r)){if(!It(e,r))return!1}else if(e!==r)return!1}for(const t of n)if(!e.includes(t))return!1;return!0}function _t(t){return null!==t&&"object"==typeof t}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Tt(t){const i=[];for(const[e,n]of Object.entries(t))Array.isArray(n)?n.forEach((t=>{i.push(encodeURIComponent(e)+"="+encodeURIComponent(t))})):i.push(encodeURIComponent(e)+"="+encodeURIComponent(n));return i.length?"&"+i.join("&"):""}class At{constructor(t,i){this.observers=[],this.unsubscribes=[],this.observerCount=0,this.task=Promise.resolve(),this.finalized=!1,this.onNoObservers=i,this.task.then((()=>{t(this)})).catch((t=>{this.error(t)}))}next(t){this.forEachObserver((i=>{i.next(t)}))}error(t){this.forEachObserver((i=>{i.error(t)})),this.close(t)}complete(){this.forEachObserver((t=>{t.complete()})),this.close()}subscribe(t,i,e){let n;if(void 0===t&&void 0===i&&void 0===e)throw new Error("Missing Observer.");n=function(t,i){if("object"!=typeof t||null===t)return!1;for(const e of i)if(e in t&&"function"==typeof t[e])return!0;return!1}(t,["next","error","complete"])?t:{next:t,error:i,complete:e},void 0===n.next&&(n.next=St),void 0===n.error&&(n.error=St),void 0===n.complete&&(n.complete=St);const s=this.unsubscribeOne.bind(this,this.observers.length);return this.finalized&&this.task.then((()=>{try{this.finalError?n.error(this.finalError):n.complete()}catch(t){}})),this.observers.push(n),s}unsubscribeOne(t){void 0!==this.observers&&void 0!==this.observers[t]&&(delete this.observers[t],this.observerCount-=1,0===this.observerCount&&void 0!==this.onNoObservers&&this.onNoObservers(this))}forEachObserver(t){if(!this.finalized)for(let i=0;i<this.observers.length;i++)this.sendOne(i,t)}sendOne(t,i){this.task.then((()=>{if(void 0!==this.observers&&void 0!==this.observers[t])try{i(this.observers[t])}catch(t){"undefined"!=typeof console&&console.error&&console.error(t)}}))}close(t){this.finalized||(this.finalized=!0,void 0!==t&&(this.finalError=t),this.task.then((()=>{this.observers=void 0,this.onNoObservers=void 0})))}}function St(){}
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function kt(t){return t&&t._delegate?t._delegate:t}class Nt{constructor(t,i,e){this.name=t,this.instanceFactory=i,this.type=e,this.multipleInstances=!1,this.serviceProps={},this.instantiationMode="LAZY",this.onInstanceCreated=null}setInstantiationMode(t){return this.instantiationMode=t,this}setMultipleInstances(t){return this.multipleInstances=t,this}setServiceProps(t){return this.serviceProps=t,this}setInstanceCreatedCallback(t){return this.onInstanceCreated=t,this}}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Ot="[DEFAULT]";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Dt{constructor(t,i){this.name=t,this.container=i,this.component=null,this.instances=new Map,this.instancesDeferred=new Map,this.instancesOptions=new Map,this.onInitCallbacks=new Map}get(t){const i=this.normalizeInstanceIdentifier(t);if(!this.instancesDeferred.has(i)){const t=new yt;if(this.instancesDeferred.set(i,t),this.isInitialized(i)||this.shouldAutoInitialize())try{const e=this.getOrInitializeService({instanceIdentifier:i});e&&t.resolve(e)}catch(t){}}return this.instancesDeferred.get(i).promise}getImmediate(t){var i;const e=this.normalizeInstanceIdentifier(null==t?void 0:t.identifier),n=null!==(i=null==t?void 0:t.optional)&&void 0!==i&&i;if(!this.isInitialized(e)&&!this.shouldAutoInitialize()){if(n)return null;throw Error(`Service ${this.name} is not available`)}try{return this.getOrInitializeService({instanceIdentifier:e})}catch(t){if(n)return null;throw t}}getComponent(){return this.component}setComponent(t){if(t.name!==this.name)throw Error(`Mismatching Component ${t.name} for Provider ${this.name}.`);if(this.component)throw Error(`Component for ${this.name} has already been provided`);if(this.component=t,this.shouldAutoInitialize()){if(function(t){return"EAGER"===t.instantiationMode}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(t))try{this.getOrInitializeService({instanceIdentifier:Ot})}catch(t){}for(const[t,i]of this.instancesDeferred.entries()){const e=this.normalizeInstanceIdentifier(t);try{const t=this.getOrInitializeService({instanceIdentifier:e});i.resolve(t)}catch(t){}}}}clearInstance(t=Ot){this.instancesDeferred.delete(t),this.instancesOptions.delete(t),this.instances.delete(t)}async delete(){const t=Array.from(this.instances.values());await Promise.all([...t.filter((t=>"INTERNAL"in t)).map((t=>t.INTERNAL.delete())),...t.filter((t=>"_delete"in t)).map((t=>t._delete()))])}isComponentSet(){return null!=this.component}isInitialized(t=Ot){return this.instances.has(t)}getOptions(t=Ot){return this.instancesOptions.get(t)||{}}initialize(t={}){const{options:i={}}=t,e=this.normalizeInstanceIdentifier(t.instanceIdentifier);if(this.isInitialized(e))throw Error(`${this.name}(${e}) has already been initialized`);if(!this.isComponentSet())throw Error(`Component ${this.name} has not been registered yet`);const n=this.getOrInitializeService({instanceIdentifier:e,options:i});for(const[t,i]of this.instancesDeferred.entries()){e===this.normalizeInstanceIdentifier(t)&&i.resolve(n)}return n}onInit(t,i){var e;const n=this.normalizeInstanceIdentifier(i),s=null!==(e=this.onInitCallbacks.get(n))&&void 0!==e?e:new Set;s.add(t),this.onInitCallbacks.set(n,s);const r=this.instances.get(n);return r&&t(r,n),()=>{s.delete(t)}}invokeOnInitCallbacks(t,i){const e=this.onInitCallbacks.get(i);if(e)for(const n of e)try{n(t,i)}catch(t){}}getOrInitializeService({instanceIdentifier:t,options:i={}}){let e=this.instances.get(t);if(!e&&this.component&&(e=this.component.instanceFactory(this.container,{instanceIdentifier:(n=t,n===Ot?void 0:n),options:i}),this.instances.set(t,e),this.instancesOptions.set(t,i),this.invokeOnInitCallbacks(e,t),this.component.onInstanceCreated))try{this.component.onInstanceCreated(this.container,t,e)}catch(t){}var n;return e||null}normalizeInstanceIdentifier(t=Ot){return this.component?this.component.multipleInstances?t:Ot:t}shouldAutoInitialize(){return!!this.component&&"EXPLICIT"!==this.component.instantiationMode}}class Rt{constructor(t){this.name=t,this.providers=new Map}addComponent(t){const i=this.getProvider(t.name);if(i.isComponentSet())throw new Error(`Component ${t.name} has already been registered with ${this.name}`);i.setComponent(t)}addOrOverwriteComponent(t){this.getProvider(t.name).isComponentSet()&&this.providers.delete(t.name),this.addComponent(t)}getProvider(t){if(this.providers.has(t))return this.providers.get(t);const i=new Dt(t,this);return this.providers.set(t,i),i}getProviders(){return Array.from(this.providers.values())}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */var Ct;!function(t){t[t.DEBUG=0]="DEBUG",t[t.VERBOSE=1]="VERBOSE",t[t.INFO=2]="INFO",t[t.WARN=3]="WARN",t[t.ERROR=4]="ERROR",t[t.SILENT=5]="SILENT"}(Ct||(Ct={}));const Pt={debug:Ct.DEBUG,verbose:Ct.VERBOSE,info:Ct.INFO,warn:Ct.WARN,error:Ct.ERROR,silent:Ct.SILENT},Mt=Ct.INFO,$t={[Ct.DEBUG]:"log",[Ct.VERBOSE]:"log",[Ct.INFO]:"info",[Ct.WARN]:"warn",[Ct.ERROR]:"error"},Lt=(t,i,...e)=>{if(i<t.logLevel)return;const n=(new Date).toISOString(),s=$t[i];if(!s)throw new Error(`Attempted to log a message with an invalid logType (value: ${i})`);console[s](`[${n}]  ${t.name}:`,...e)};class xt{constructor(t){this.name=t,this._logLevel=Mt,this._logHandler=Lt,this._userLogHandler=null}get logLevel(){return this._logLevel}set logLevel(t){if(!(t in Ct))throw new TypeError(`Invalid value "${t}" assigned to \`logLevel\``);this._logLevel=t}setLogLevel(t){this._logLevel="string"==typeof t?Pt[t]:t}get logHandler(){return this._logHandler}set logHandler(t){if("function"!=typeof t)throw new TypeError("Value assigned to `logHandler` must be a function");this._logHandler=t}get userLogHandler(){return this._userLogHandler}set userLogHandler(t){this._userLogHandler=t}debug(...t){this._userLogHandler&&this._userLogHandler(this,Ct.DEBUG,...t),this._logHandler(this,Ct.DEBUG,...t)}log(...t){this._userLogHandler&&this._userLogHandler(this,Ct.VERBOSE,...t),this._logHandler(this,Ct.VERBOSE,...t)}info(...t){this._userLogHandler&&this._userLogHandler(this,Ct.INFO,...t),this._logHandler(this,Ct.INFO,...t)}warn(...t){this._userLogHandler&&this._userLogHandler(this,Ct.WARN,...t),this._logHandler(this,Ct.WARN,...t)}error(...t){this._userLogHandler&&this._userLogHandler(this,Ct.ERROR,...t),this._logHandler(this,Ct.ERROR,...t)}}const Ut=(t,i)=>i.some((i=>t instanceof i));let jt,Ft;const Bt=new WeakMap,Vt=new WeakMap,zt=new WeakMap,Ht=new WeakMap,Gt=new WeakMap;let qt={get(t,i,e){if(t instanceof IDBTransaction){if("done"===i)return Vt.get(t);if("objectStoreNames"===i)return t.objectStoreNames||zt.get(t);if("store"===i)return e.objectStoreNames[1]?void 0:e.objectStore(e.objectStoreNames[0])}return Kt(t[i])},set:(t,i,e)=>(t[i]=e,!0),has:(t,i)=>t instanceof IDBTransaction&&("done"===i||"store"===i)||i in t};function Wt(t){return t!==IDBDatabase.prototype.transaction||"objectStoreNames"in IDBTransaction.prototype?(Ft||(Ft=[IDBCursor.prototype.advance,IDBCursor.prototype.continue,IDBCursor.prototype.continuePrimaryKey])).includes(t)?function(...i){return t.apply(Xt(this),i),Kt(Bt.get(this))}:function(...i){return Kt(t.apply(Xt(this),i))}:function(i,...e){const n=t.call(Xt(this),i,...e);return zt.set(n,i.sort?i.sort():[i]),Kt(n)}}function Jt(t){return"function"==typeof t?Wt(t):(t instanceof IDBTransaction&&function(t){if(Vt.has(t))return;const i=new Promise(((i,e)=>{const n=()=>{t.removeEventListener("complete",s),t.removeEventListener("error",r),t.removeEventListener("abort",r)},s=()=>{i(),n()},r=()=>{e(t.error||new DOMException("AbortError","AbortError")),n()};t.addEventListener("complete",s),t.addEventListener("error",r),t.addEventListener("abort",r)}));Vt.set(t,i)}(t),Ut(t,jt||(jt=[IDBDatabase,IDBObjectStore,IDBIndex,IDBCursor,IDBTransaction]))?new Proxy(t,qt):t)}function Kt(t){if(t instanceof IDBRequest)return function(t){const i=new Promise(((i,e)=>{const n=()=>{t.removeEventListener("success",s),t.removeEventListener("error",r)},s=()=>{i(Kt(t.result)),n()},r=()=>{e(t.error),n()};t.addEventListener("success",s),t.addEventListener("error",r)}));return i.then((i=>{i instanceof IDBCursor&&Bt.set(i,t)})).catch((()=>{})),Gt.set(i,t),i}(t);if(Ht.has(t))return Ht.get(t);const i=Jt(t);return i!==t&&(Ht.set(t,i),Gt.set(i,t)),i}const Xt=t=>Gt.get(t);const Yt=["get","getKey","getAll","getAllKeys","count"],Qt=["put","add","delete","clear"],Zt=new Map;function ti(t,i){if(!(t instanceof IDBDatabase)||i in t||"string"!=typeof i)return;if(Zt.get(i))return Zt.get(i);const e=i.replace(/FromIndex$/,""),n=i!==e,s=Qt.includes(e);if(!(e in(n?IDBIndex:IDBObjectStore).prototype)||!s&&!Yt.includes(e))return;const r=async function(t,...i){const r=this.transaction(t,s?"readwrite":"readonly");let o=r.store;return n&&(o=o.index(i.shift())),(await Promise.all([o[e](...i),s&&r.done]))[0]};return Zt.set(i,r),r}qt=(t=>({...t,get:(i,e,n)=>ti(i,e)||t.get(i,e,n),has:(i,e)=>!!ti(i,e)||t.has(i,e)}))(qt);
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class ii{constructor(t){this.container=t}getPlatformInfoString(){return this.container.getProviders().map((t=>{if(function(t){const i=t.getComponent();return"VERSION"===(null==i?void 0:i.type)}(t)){const i=t.getImmediate();return`${i.library}/${i.version}`}return null})).filter((t=>t)).join(" ")}}const ei="@firebase/app",ni="0.9.18",si=new xt("@firebase/app"),ri="[DEFAULT]",oi={[ei]:"fire-core","@firebase/app-compat":"fire-core-compat","@firebase/analytics":"fire-analytics","@firebase/analytics-compat":"fire-analytics-compat","@firebase/app-check":"fire-app-check","@firebase/app-check-compat":"fire-app-check-compat","@firebase/auth":"fire-auth","@firebase/auth-compat":"fire-auth-compat","@firebase/database":"fire-rtdb","@firebase/database-compat":"fire-rtdb-compat","@firebase/functions":"fire-fn","@firebase/functions-compat":"fire-fn-compat","@firebase/installations":"fire-iid","@firebase/installations-compat":"fire-iid-compat","@firebase/messaging":"fire-fcm","@firebase/messaging-compat":"fire-fcm-compat","@firebase/performance":"fire-perf","@firebase/performance-compat":"fire-perf-compat","@firebase/remote-config":"fire-rc","@firebase/remote-config-compat":"fire-rc-compat","@firebase/storage":"fire-gcs","@firebase/storage-compat":"fire-gcs-compat","@firebase/firestore":"fire-fst","@firebase/firestore-compat":"fire-fst-compat","fire-js":"fire-js",firebase:"fire-js-all"},hi=new Map,ai=new Map;function ui(t,i){try{t.container.addComponent(i)}catch(e){si.debug(`Component ${i.name} failed to register with FirebaseApp ${t.name}`,e)}}function ci(t){const i=t.name;if(ai.has(i))return si.debug(`There were multiple attempts to register component ${i}.`),!1;ai.set(i,t);for(const i of hi.values())ui(i,t);return!0}function li(t,i){const e=t.container.getProvider("heartbeat").getImmediate({optional:!0});return e&&e.triggerHeartbeat(),t.container.getProvider(i)}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const fi=new bt("app","Firebase",{"no-app":"No Firebase App '{$appName}' has been created - call initializeApp() first","bad-app-name":"Illegal App name: '{$appName}","duplicate-app":"Firebase App named '{$appName}' already exists with different options or config","app-deleted":"Firebase App named '{$appName}' already deleted","no-options":"Need to provide options, when not being deployed to hosting via source.","invalid-app-argument":"firebase.{$appName}() takes either no argument or a Firebase App instance.","invalid-log-argument":"First argument to `onLog` must be null or a function.","idb-open":"Error thrown when opening IndexedDB. Original error: {$originalErrorMessage}.","idb-get":"Error thrown when reading from IndexedDB. Original error: {$originalErrorMessage}.","idb-set":"Error thrown when writing to IndexedDB. Original error: {$originalErrorMessage}.","idb-delete":"Error thrown when deleting from IndexedDB. Original error: {$originalErrorMessage}."});
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class di{constructor(t,i,e){this._isDeleted=!1,this._options=Object.assign({},t),this._config=Object.assign({},i),this._name=i.name,this._automaticDataCollectionEnabled=i.automaticDataCollectionEnabled,this._container=e,this.container.addComponent(new Nt("app",(()=>this),"PUBLIC"))}get automaticDataCollectionEnabled(){return this.checkDestroyed(),this._automaticDataCollectionEnabled}set automaticDataCollectionEnabled(t){this.checkDestroyed(),this._automaticDataCollectionEnabled=t}get name(){return this.checkDestroyed(),this._name}get options(){return this.checkDestroyed(),this._options}get config(){return this.checkDestroyed(),this._config}get container(){return this._container}get isDeleted(){return this._isDeleted}set isDeleted(t){this._isDeleted=t}checkDestroyed(){if(this.isDeleted)throw fi.create("app-deleted",{appName:this._name})}}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const pi="10.3.1";function vi(t,i={}){let e=t;if("object"!=typeof i){i={name:i}}const n=Object.assign({name:ri,automaticDataCollectionEnabled:!1},i),s=n.name;if("string"!=typeof s||!s)throw fi.create("bad-app-name",{appName:String(s)});if(e||(e=vt()),!e)throw fi.create("no-options");const r=hi.get(s);if(r){if(It(e,r.options)&&It(n,r.config))return r;throw fi.create("duplicate-app",{appName:s})}const o=new Rt(s);for(const t of ai.values())o.addComponent(t);const h=new di(e,n,o);return hi.set(s,h),h}function wi(t=ri){const i=hi.get(t);if(!i&&t===ri&&vt())return vi();if(!i)throw fi.create("no-app",{appName:t});return i}function yi(t,i,e){var n;let s=null!==(n=oi[t])&&void 0!==n?n:t;e&&(s+=`-${e}`);const r=s.match(/\s|\//),o=i.match(/\s|\//);if(r||o){const t=[`Unable to register library "${s}" with version "${i}":`];return r&&t.push(`library name "${s}" contains illegal characters (whitespace or "/")`),r&&o&&t.push("and"),o&&t.push(`version name "${i}" contains illegal characters (whitespace or "/")`),void si.warn(t.join(" "))}ci(new Nt(`${s}-version`,(()=>({library:s,version:i})),"VERSION"))}
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const gi="firebase-heartbeat-database",mi=1,bi="firebase-heartbeat-store";let Ei=null;function Ii(){return Ei||(Ei=function(t,i,{blocked:e,upgrade:n,blocking:s,terminated:r}={}){const o=indexedDB.open(t,i),h=Kt(o);return n&&o.addEventListener("upgradeneeded",(t=>{n(Kt(o.result),t.oldVersion,t.newVersion,Kt(o.transaction),t)})),e&&o.addEventListener("blocked",(t=>e(t.oldVersion,t.newVersion,t))),h.then((t=>{r&&t.addEventListener("close",(()=>r())),s&&t.addEventListener("versionchange",(t=>s(t.oldVersion,t.newVersion,t)))})).catch((()=>{})),h}(gi,mi,{upgrade:(t,i)=>{if(0===i)t.createObjectStore(bi)}}).catch((t=>{throw fi.create("idb-open",{originalErrorMessage:t.message})}))),Ei}async function _i(t,i){try{const e=(await Ii()).transaction(bi,"readwrite"),n=e.objectStore(bi);await n.put(i,Ti(t)),await e.done}catch(t){if(t instanceof mt)si.warn(t.message);else{const i=fi.create("idb-set",{originalErrorMessage:null==t?void 0:t.message});si.warn(i.message)}}}function Ti(t){return`${t.name}!${t.options.appId}`}
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ai{constructor(t){this.container=t,this._heartbeatsCache=null;const i=this.container.getProvider("app").getImmediate();this._storage=new ki(i),this._heartbeatsCachePromise=this._storage.read().then((t=>(this._heartbeatsCache=t,t)))}async triggerHeartbeat(){const t=this.container.getProvider("platform-logger").getImmediate().getPlatformInfoString(),i=Si();if(null===this._heartbeatsCache&&(this._heartbeatsCache=await this._heartbeatsCachePromise),this._heartbeatsCache.lastSentHeartbeatDate!==i&&!this._heartbeatsCache.heartbeats.some((t=>t.date===i)))return this._heartbeatsCache.heartbeats.push({date:i,agent:t}),this._heartbeatsCache.heartbeats=this._heartbeatsCache.heartbeats.filter((t=>{const i=new Date(t.date).valueOf();return Date.now()-i<=2592e6})),this._storage.overwrite(this._heartbeatsCache)}async getHeartbeatsHeader(){if(null===this._heartbeatsCache&&await this._heartbeatsCachePromise,null===this._heartbeatsCache||0===this._heartbeatsCache.heartbeats.length)return"";const t=Si(),{heartbeatsToSend:i,unsentEntries:e}=function(t,i=1024){const e=[];let n=t.slice();for(const s of t){const t=e.find((t=>t.agent===s.agent));if(t){if(t.dates.push(s.date),Ni(e)>i){t.dates.pop();break}}else if(e.push({agent:s.agent,dates:[s.date]}),Ni(e)>i){e.pop();break}n=n.slice(1)}return{heartbeatsToSend:e,unsentEntries:n}}(this._heartbeatsCache.heartbeats),n=ct(JSON.stringify({version:2,heartbeats:i}));return this._heartbeatsCache.lastSentHeartbeatDate=t,e.length>0?(this._heartbeatsCache.heartbeats=e,await this._storage.overwrite(this._heartbeatsCache)):(this._heartbeatsCache.heartbeats=[],this._storage.overwrite(this._heartbeatsCache)),n}}function Si(){return(new Date).toISOString().substring(0,10)}class ki{constructor(t){this.app=t,this._canUseIndexedDBPromise=this.runIndexedDBEnvironmentCheck()}async runIndexedDBEnvironmentCheck(){return!!function(){try{return"object"==typeof indexedDB}catch(t){return!1}}()&&new Promise(((t,i)=>{try{let e=!0;const n="validate-browser-context-for-indexeddb-analytics-module",s=self.indexedDB.open(n);s.onsuccess=()=>{s.result.close(),e||self.indexedDB.deleteDatabase(n),t(!0)},s.onupgradeneeded=()=>{e=!1},s.onerror=()=>{var t;i((null===(t=s.error)||void 0===t?void 0:t.message)||"")}}catch(t){i(t)}})).then((()=>!0)).catch((()=>!1))}async read(){if(await this._canUseIndexedDBPromise){const t=await async function(t){try{const i=await Ii();return await i.transaction(bi).objectStore(bi).get(Ti(t))}catch(t){if(t instanceof mt)si.warn(t.message);else{const i=fi.create("idb-get",{originalErrorMessage:null==t?void 0:t.message});si.warn(i.message)}}}(this.app);return t||{heartbeats:[]}}return{heartbeats:[]}}async overwrite(t){var i;if(await this._canUseIndexedDBPromise){const e=await this.read();return _i(this.app,{lastSentHeartbeatDate:null!==(i=t.lastSentHeartbeatDate)&&void 0!==i?i:e.lastSentHeartbeatDate,heartbeats:t.heartbeats})}}async add(t){var i;if(await this._canUseIndexedDBPromise){const e=await this.read();return _i(this.app,{lastSentHeartbeatDate:null!==(i=t.lastSentHeartbeatDate)&&void 0!==i?i:e.lastSentHeartbeatDate,heartbeats:[...e.heartbeats,...t.heartbeats]})}}}function Ni(t){return ct(JSON.stringify({version:2,heartbeats:t})).length}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */var Oi;function Di(t,i){var e={};for(var n in t)Object.prototype.hasOwnProperty.call(t,n)&&i.indexOf(n)<0&&(e[n]=t[n]);if(null!=t&&"function"==typeof Object.getOwnPropertySymbols){var s=0;for(n=Object.getOwnPropertySymbols(t);s<n.length;s++)i.indexOf(n[s])<0&&Object.prototype.propertyIsEnumerable.call(t,n[s])&&(e[n[s]]=t[n[s]])}return e}function Ri(){return{"dependent-sdk-initialized-before-auth":"Another Firebase SDK was initialized and is trying to use Auth before Auth is initialized. Please be sure to call `initializeAuth` or `getAuth` before starting any other Firebase SDK."}}Oi="",ci(new Nt("platform-logger",(t=>new ii(t)),"PRIVATE")),ci(new Nt("heartbeat",(t=>new Ai(t)),"PRIVATE")),yi(ei,ni,Oi),yi(ei,ni,"esm2017"),yi("fire-js",""),"function"==typeof SuppressedError&&SuppressedError;const Ci=Ri,Pi=new bt("auth","Firebase",{"dependent-sdk-initialized-before-auth":"Another Firebase SDK was initialized and is trying to use Auth before Auth is initialized. Please be sure to call `initializeAuth` or `getAuth` before starting any other Firebase SDK."}),Mi=new xt("@firebase/auth");function $i(t,...i){Mi.logLevel<=Ct.ERROR&&Mi.error(`Auth (${pi}): ${t}`,...i)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Li(t,...i){throw ji(t,...i)}function xi(t,...i){return ji(t,...i)}function Ui(t,i,e){const n=Object.assign(Object.assign({},Ci()),{[i]:e});return new bt("auth","Firebase",n).create(i,{appName:t.name})}function ji(t,...i){if("string"!=typeof t){const e=i[0],n=[...i.slice(1)];return n[0]&&(n[0].appName=t.name),t._errorFactory.create(e,...n)}return Pi.create(t,...i)}function Fi(t,i,...e){if(!t)throw ji(i,...e)}function Bi(t){const i="INTERNAL ASSERTION FAILED: "+t;throw $i(i),new Error(i)}function Vi(t,i){t||Bi(i)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function zi(){var t;return"undefined"!=typeof self&&(null===(t=self.location)||void 0===t?void 0:t.href)||""}function Hi(){var t;return"undefined"!=typeof self&&(null===(t=self.location)||void 0===t?void 0:t.protocol)||null}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Gi(){return"undefined"==typeof navigator||!navigator||!("onLine"in navigator)||"boolean"!=typeof navigator.onLine||"http:"!==Hi()&&"https:"!==Hi()&&!function(){const t="object"==typeof chrome?chrome.runtime:"object"==typeof browser?browser.runtime:void 0;return"object"==typeof t&&void 0!==t.id}()&&!("connection"in navigator)||navigator.onLine}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class qi{constructor(t,i){this.shortDelay=t,this.longDelay=i,Vi(i>t,"Short delay should be less than long delay!"),this.isMobile="undefined"!=typeof window&&!!(window.cordova||window.phonegap||window.PhoneGap)&&/ios|iphone|ipod|ipad|android|blackberry|iemobile/i.test(gt())||"object"==typeof navigator&&"ReactNative"===navigator.product}get(){return Gi()?this.isMobile?this.longDelay:this.shortDelay:Math.min(5e3,this.shortDelay)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Wi(t,i){Vi(t.emulator,"Emulator should always be set here");const{url:e}=t.emulator;return i?`${e}${i.startsWith("/")?i.slice(1):i}`:e}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ji{static initialize(t,i,e){this.fetchImpl=t,i&&(this.headersImpl=i),e&&(this.responseImpl=e)}static fetch(){return this.fetchImpl?this.fetchImpl:"undefined"!=typeof self&&"fetch"in self?self.fetch:void Bi("Could not find fetch implementation, make sure you call FetchProvider.initialize() with an appropriate polyfill")}static headers(){return this.headersImpl?this.headersImpl:"undefined"!=typeof self&&"Headers"in self?self.Headers:void Bi("Could not find Headers implementation, make sure you call FetchProvider.initialize() with an appropriate polyfill")}static response(){return this.responseImpl?this.responseImpl:"undefined"!=typeof self&&"Response"in self?self.Response:void Bi("Could not find Response implementation, make sure you call FetchProvider.initialize() with an appropriate polyfill")}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Ki={CREDENTIAL_MISMATCH:"custom-token-mismatch",MISSING_CUSTOM_TOKEN:"internal-error",INVALID_IDENTIFIER:"invalid-email",MISSING_CONTINUE_URI:"internal-error",INVALID_PASSWORD:"wrong-password",MISSING_PASSWORD:"missing-password",EMAIL_EXISTS:"email-already-in-use",PASSWORD_LOGIN_DISABLED:"operation-not-allowed",INVALID_IDP_RESPONSE:"invalid-credential",INVALID_PENDING_TOKEN:"invalid-credential",FEDERATED_USER_ID_ALREADY_LINKED:"credential-already-in-use",MISSING_REQ_TYPE:"internal-error",EMAIL_NOT_FOUND:"user-not-found",RESET_PASSWORD_EXCEED_LIMIT:"too-many-requests",EXPIRED_OOB_CODE:"expired-action-code",INVALID_OOB_CODE:"invalid-action-code",MISSING_OOB_CODE:"internal-error",CREDENTIAL_TOO_OLD_LOGIN_AGAIN:"requires-recent-login",INVALID_ID_TOKEN:"invalid-user-token",TOKEN_EXPIRED:"user-token-expired",USER_NOT_FOUND:"user-token-expired",TOO_MANY_ATTEMPTS_TRY_LATER:"too-many-requests",PASSWORD_DOES_NOT_MEET_REQUIREMENTS:"password-does-not-meet-requirements",INVALID_CODE:"invalid-verification-code",INVALID_SESSION_INFO:"invalid-verification-id",INVALID_TEMPORARY_PROOF:"invalid-credential",MISSING_SESSION_INFO:"missing-verification-id",SESSION_EXPIRED:"code-expired",MISSING_ANDROID_PACKAGE_NAME:"missing-android-pkg-name",UNAUTHORIZED_DOMAIN:"unauthorized-continue-uri",INVALID_OAUTH_CLIENT_ID:"invalid-oauth-client-id",ADMIN_ONLY_OPERATION:"admin-restricted-operation",INVALID_MFA_PENDING_CREDENTIAL:"invalid-multi-factor-session",MFA_ENROLLMENT_NOT_FOUND:"multi-factor-info-not-found",MISSING_MFA_ENROLLMENT_ID:"missing-multi-factor-info",MISSING_MFA_PENDING_CREDENTIAL:"missing-multi-factor-session",SECOND_FACTOR_EXISTS:"second-factor-already-in-use",SECOND_FACTOR_LIMIT_EXCEEDED:"maximum-second-factor-count-exceeded",BLOCKING_FUNCTION_ERROR_RESPONSE:"internal-error",RECAPTCHA_NOT_ENABLED:"recaptcha-not-enabled",MISSING_RECAPTCHA_TOKEN:"missing-recaptcha-token",INVALID_RECAPTCHA_TOKEN:"invalid-recaptcha-token",INVALID_RECAPTCHA_ACTION:"invalid-recaptcha-action",MISSING_CLIENT_TYPE:"missing-client-type",MISSING_RECAPTCHA_VERSION:"missing-recaptcha-version",INVALID_RECAPTCHA_VERSION:"invalid-recaptcha-version",INVALID_REQ_TYPE:"invalid-req-type"},Xi=new qi(3e4,6e4);
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Yi(t,i){return t.tenantId&&!i.tenantId?Object.assign(Object.assign({},i),{tenantId:t.tenantId}):i}async function Qi(t,i,e,n,s={}){return Zi(t,s,(async()=>{let s={},r={};n&&("GET"===i?r=n:s={body:JSON.stringify(n)});const o=Tt(Object.assign({key:t.config.apiKey},r)).slice(1),h=await t._getAdditionalHeaders();return h["Content-Type"]="application/json",t.languageCode&&(h["X-Firebase-Locale"]=t.languageCode),Ji.fetch()(te(t,t.config.apiHost,e,o),Object.assign({method:i,headers:h,referrerPolicy:"no-referrer"},s))}))}async function Zi(t,i,e){t._canInitEmulator=!1;const n=Object.assign(Object.assign({},Ki),i);try{const i=new ie(t),s=await Promise.race([e(),i.promise]);i.clearNetworkTimeout();const r=await s.json();if("needConfirmation"in r)throw ee(t,"account-exists-with-different-credential",r);if(s.ok&&!("errorMessage"in r))return r;{const i=s.ok?r.errorMessage:r.error.message,[e,o]=i.split(" : ");if("FEDERATED_USER_ID_ALREADY_LINKED"===e)throw ee(t,"credential-already-in-use",r);if("EMAIL_EXISTS"===e)throw ee(t,"email-already-in-use",r);if("USER_DISABLED"===e)throw ee(t,"user-disabled",r);const h=n[e]||e.toLowerCase().replace(/[_\s]+/g,"-");if(o)throw Ui(t,h,o);Li(t,h)}}catch(i){if(i instanceof mt)throw i;Li(t,"network-request-failed",{message:String(i)})}}function te(t,i,e,n){const s=`${i}${e}?${n}`;return t.config.emulator?Wi(t.config,s):`${t.config.apiScheme}://${s}`}class ie{constructor(t){this.auth=t,this.timer=null,this.promise=new Promise(((t,i)=>{this.timer=setTimeout((()=>i(xi(this.auth,"network-request-failed"))),Xi.get())}))}clearNetworkTimeout(){clearTimeout(this.timer)}}function ee(t,i,e){const n={appName:t.name};e.email&&(n.email=e.email),e.phoneNumber&&(n.phoneNumber=e.phoneNumber);const s=xi(t,i,n);return s.customData._tokenResponse=e,s}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function ne(t){if(t)try{const i=new Date(Number(t));if(!isNaN(i.getTime()))return i.toUTCString()}catch(t){}}function se(t){return 1e3*Number(t)}function re(t){const[i,e,n]=t.split(".");if(void 0===i||void 0===e||void 0===n)return $i("JWT malformed, contained fewer than 3 sections"),null;try{const t=lt(e);return t?JSON.parse(t):($i("Failed to decode base64 JWT payload"),null)}catch(t){return $i("Caught error parsing JWT payload as JSON",null==t?void 0:t.toString()),null}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
async function oe(t,i,e=!1){if(e)return i;try{return await i}catch(i){throw i instanceof mt&&function({code:t}){return"auth/user-disabled"===t||"auth/user-token-expired"===t}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(i)&&t.auth.currentUser===t&&await t.auth.signOut(),i}}class he{constructor(t){this.user=t,this.isRunning=!1,this.timerId=null,this.errorBackoff=3e4}_start(){this.isRunning||(this.isRunning=!0,this.schedule())}_stop(){this.isRunning&&(this.isRunning=!1,null!==this.timerId&&clearTimeout(this.timerId))}getInterval(t){var i;if(t){const t=this.errorBackoff;return this.errorBackoff=Math.min(2*this.errorBackoff,96e4),t}{this.errorBackoff=3e4;const t=(null!==(i=this.user.stsTokenManager.expirationTime)&&void 0!==i?i:0)-Date.now()-3e5;return Math.max(0,t)}}schedule(t=!1){if(!this.isRunning)return;const i=this.getInterval(t);this.timerId=setTimeout((async()=>{await this.iteration()}),i)}async iteration(){try{await this.user.getIdToken(!0)}catch(t){return void("auth/network-request-failed"===(null==t?void 0:t.code)&&this.schedule(!0))}this.schedule()}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ae{constructor(t,i){this.createdAt=t,this.lastLoginAt=i,this._initializeTime()}_initializeTime(){this.lastSignInTime=ne(this.lastLoginAt),this.creationTime=ne(this.createdAt)}_copy(t){this.createdAt=t.createdAt,this.lastLoginAt=t.lastLoginAt,this._initializeTime()}toJSON(){return{createdAt:this.createdAt,lastLoginAt:this.lastLoginAt}}}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function ue(t){var i;const e=t.auth,n=await t.getIdToken(),s=await oe(t,async function(t,i){return Qi(t,"POST","/v1/accounts:lookup",i)}(e,{idToken:n}));Fi(null==s?void 0:s.users.length,e,"internal-error");const r=s.users[0];t._notifyReloadListener(r);const o=(null===(i=r.providerUserInfo)||void 0===i?void 0:i.length)?r.providerUserInfo.map((t=>{var{providerId:i}=t,e=Di(t,["providerId"]);return{providerId:i,uid:e.rawId||"",displayName:e.displayName||null,email:e.email||null,phoneNumber:e.phoneNumber||null,photoURL:e.photoUrl||null}})):[];const h=function(t,i){const e=t.filter((t=>!i.some((i=>i.providerId===t.providerId))));return[...e,...i]}(t.providerData,o),a=t.isAnonymous,u=!(t.email&&r.passwordHash||(null==h?void 0:h.length)),c=!!a&&u,l={uid:r.localId,displayName:r.displayName||null,photoURL:r.photoUrl||null,email:r.email||null,emailVerified:r.emailVerified||!1,phoneNumber:r.phoneNumber||null,tenantId:r.tenantId||null,providerData:h,metadata:new ae(r.createdAt,r.lastLoginAt),isAnonymous:c};Object.assign(t,l)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class ce{constructor(){this.refreshToken=null,this.accessToken=null,this.expirationTime=null}get isExpired(){return!this.expirationTime||Date.now()>this.expirationTime-3e4}updateFromServerResponse(t){Fi(t.idToken,"internal-error"),Fi(void 0!==t.idToken,"internal-error"),Fi(void 0!==t.refreshToken,"internal-error");const i="expiresIn"in t&&void 0!==t.expiresIn?Number(t.expiresIn):function(t){const i=re(t);return Fi(i,"internal-error"),Fi(void 0!==i.exp,"internal-error"),Fi(void 0!==i.iat,"internal-error"),Number(i.exp)-Number(i.iat)}(t.idToken);this.updateTokensAndExpiration(t.idToken,t.refreshToken,i)}async getToken(t,i=!1){return Fi(!this.accessToken||this.refreshToken,t,"user-token-expired"),i||!this.accessToken||this.isExpired?this.refreshToken?(await this.refresh(t,this.refreshToken),this.accessToken):null:this.accessToken}clearRefreshToken(){this.refreshToken=null}async refresh(t,i){const{accessToken:e,refreshToken:n,expiresIn:s}=
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */await async function(t,i){const e=await Zi(t,{},(async()=>{const e=Tt({grant_type:"refresh_token",refresh_token:i}).slice(1),{tokenApiHost:n,apiKey:s}=t.config,r=te(t,n,"/v1/token",`key=${s}`),o=await t._getAdditionalHeaders();return o["Content-Type"]="application/x-www-form-urlencoded",Ji.fetch()(r,{method:"POST",headers:o,body:e})}));return{accessToken:e.access_token,expiresIn:e.expires_in,refreshToken:e.refresh_token}}(t,i);this.updateTokensAndExpiration(e,n,Number(s))}updateTokensAndExpiration(t,i,e){this.refreshToken=i||null,this.accessToken=t||null,this.expirationTime=Date.now()+1e3*e}static fromJSON(t,i){const{refreshToken:e,accessToken:n,expirationTime:s}=i,r=new ce;return e&&(Fi("string"==typeof e,"internal-error",{appName:t}),r.refreshToken=e),n&&(Fi("string"==typeof n,"internal-error",{appName:t}),r.accessToken=n),s&&(Fi("number"==typeof s,"internal-error",{appName:t}),r.expirationTime=s),r}toJSON(){return{refreshToken:this.refreshToken,accessToken:this.accessToken,expirationTime:this.expirationTime}}_assign(t){this.accessToken=t.accessToken,this.refreshToken=t.refreshToken,this.expirationTime=t.expirationTime}_clone(){return Object.assign(new ce,this.toJSON())}_performRefresh(){return Bi("not implemented")}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function le(t,i){Fi("string"==typeof t||void 0===t,"internal-error",{appName:i})}class fe{constructor(t){var{uid:i,auth:e,stsTokenManager:n}=t,s=Di(t,["uid","auth","stsTokenManager"]);this.providerId="firebase",this.proactiveRefresh=new he(this),this.reloadUserInfo=null,this.reloadListener=null,this.uid=i,this.auth=e,this.stsTokenManager=n,this.accessToken=n.accessToken,this.displayName=s.displayName||null,this.email=s.email||null,this.emailVerified=s.emailVerified||!1,this.phoneNumber=s.phoneNumber||null,this.photoURL=s.photoURL||null,this.isAnonymous=s.isAnonymous||!1,this.tenantId=s.tenantId||null,this.providerData=s.providerData?[...s.providerData]:[],this.metadata=new ae(s.createdAt||void 0,s.lastLoginAt||void 0)}async getIdToken(t){const i=await oe(this,this.stsTokenManager.getToken(this.auth,t));return Fi(i,this.auth,"internal-error"),this.accessToken!==i&&(this.accessToken=i,await this.auth._persistUserIfCurrent(this),this.auth._notifyListenersIfCurrent(this)),i}getIdTokenResult(t){return async function(t,i=!1){const e=kt(t),n=await e.getIdToken(i),s=re(n);Fi(s&&s.exp&&s.auth_time&&s.iat,e.auth,"internal-error");const r="object"==typeof s.firebase?s.firebase:void 0,o=null==r?void 0:r.sign_in_provider;return{claims:s,token:n,authTime:ne(se(s.auth_time)),issuedAtTime:ne(se(s.iat)),expirationTime:ne(se(s.exp)),signInProvider:o||null,signInSecondFactor:(null==r?void 0:r.sign_in_second_factor)||null}}(this,t)}reload(){return async function(t){const i=kt(t);await ue(i),await i.auth._persistUserIfCurrent(i),i.auth._notifyListenersIfCurrent(i)}(this)}_assign(t){this!==t&&(Fi(this.uid===t.uid,this.auth,"internal-error"),this.displayName=t.displayName,this.photoURL=t.photoURL,this.email=t.email,this.emailVerified=t.emailVerified,this.phoneNumber=t.phoneNumber,this.isAnonymous=t.isAnonymous,this.tenantId=t.tenantId,this.providerData=t.providerData.map((t=>Object.assign({},t))),this.metadata._copy(t.metadata),this.stsTokenManager._assign(t.stsTokenManager))}_clone(t){const i=new fe(Object.assign(Object.assign({},this),{auth:t,stsTokenManager:this.stsTokenManager._clone()}));return i.metadata._copy(this.metadata),i}_onReload(t){Fi(!this.reloadListener,this.auth,"internal-error"),this.reloadListener=t,this.reloadUserInfo&&(this._notifyReloadListener(this.reloadUserInfo),this.reloadUserInfo=null)}_notifyReloadListener(t){this.reloadListener?this.reloadListener(t):this.reloadUserInfo=t}_startProactiveRefresh(){this.proactiveRefresh._start()}_stopProactiveRefresh(){this.proactiveRefresh._stop()}async _updateTokensIfNecessary(t,i=!1){let e=!1;t.idToken&&t.idToken!==this.stsTokenManager.accessToken&&(this.stsTokenManager.updateFromServerResponse(t),e=!0),i&&await ue(this),await this.auth._persistUserIfCurrent(this),e&&this.auth._notifyListenersIfCurrent(this)}async delete(){const t=await this.getIdToken();return await oe(this,async function(t,i){return Qi(t,"POST","/v1/accounts:delete",i)}(this.auth,{idToken:t})),this.stsTokenManager.clearRefreshToken(),this.auth.signOut()}toJSON(){return Object.assign(Object.assign({uid:this.uid,email:this.email||void 0,emailVerified:this.emailVerified,displayName:this.displayName||void 0,isAnonymous:this.isAnonymous,photoURL:this.photoURL||void 0,phoneNumber:this.phoneNumber||void 0,tenantId:this.tenantId||void 0,providerData:this.providerData.map((t=>Object.assign({},t))),stsTokenManager:this.stsTokenManager.toJSON(),_redirectEventId:this._redirectEventId},this.metadata.toJSON()),{apiKey:this.auth.config.apiKey,appName:this.auth.name})}get refreshToken(){return this.stsTokenManager.refreshToken||""}static _fromJSON(t,i){var e,n,s,r,o,h,a,u;const c=null!==(e=i.displayName)&&void 0!==e?e:void 0,l=null!==(n=i.email)&&void 0!==n?n:void 0,f=null!==(s=i.phoneNumber)&&void 0!==s?s:void 0,d=null!==(r=i.photoURL)&&void 0!==r?r:void 0,p=null!==(o=i.tenantId)&&void 0!==o?o:void 0,v=null!==(h=i._redirectEventId)&&void 0!==h?h:void 0,w=null!==(a=i.createdAt)&&void 0!==a?a:void 0,y=null!==(u=i.lastLoginAt)&&void 0!==u?u:void 0,{uid:g,emailVerified:m,isAnonymous:b,providerData:E,stsTokenManager:I}=i;Fi(g&&I,t,"internal-error");const _=ce.fromJSON(this.name,I);Fi("string"==typeof g,t,"internal-error"),le(c,t.name),le(l,t.name),Fi("boolean"==typeof m,t,"internal-error"),Fi("boolean"==typeof b,t,"internal-error"),le(f,t.name),le(d,t.name),le(p,t.name),le(v,t.name),le(w,t.name),le(y,t.name);const T=new fe({uid:g,auth:t,email:l,emailVerified:m,displayName:c,isAnonymous:b,photoURL:d,phoneNumber:f,tenantId:p,stsTokenManager:_,createdAt:w,lastLoginAt:y});return E&&Array.isArray(E)&&(T.providerData=E.map((t=>Object.assign({},t)))),v&&(T._redirectEventId=v),T}static async _fromIdTokenResponse(t,i,e=!1){const n=new ce;n.updateFromServerResponse(i);const s=new fe({uid:i.localId,auth:t,stsTokenManager:n,isAnonymous:e});return await ue(s),s}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const de=new Map;function pe(t){Vi(t instanceof Function,"Expected a class definition");let i=de.get(t);return i?(Vi(i instanceof t,"Instance stored in cache mismatched with class"),i):(i=new t,de.set(t,i),i)}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ve{constructor(){this.type="NONE",this.storage={}}async _isAvailable(){return!0}async _set(t,i){this.storage[t]=i}async _get(t){const i=this.storage[t];return void 0===i?null:i}async _remove(t){delete this.storage[t]}_addListener(t,i){}_removeListener(t,i){}}ve.type="NONE";const we=ve;
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function ye(t,i,e){return`firebase:${t}:${i}:${e}`}class ge{constructor(t,i,e){this.persistence=t,this.auth=i,this.userKey=e;const{config:n,name:s}=this.auth;this.fullUserKey=ye(this.userKey,n.apiKey,s),this.fullPersistenceKey=ye("persistence",n.apiKey,s),this.boundEventHandler=i._onStorageEvent.bind(i),this.persistence._addListener(this.fullUserKey,this.boundEventHandler)}setCurrentUser(t){return this.persistence._set(this.fullUserKey,t.toJSON())}async getCurrentUser(){const t=await this.persistence._get(this.fullUserKey);return t?fe._fromJSON(this.auth,t):null}removeCurrentUser(){return this.persistence._remove(this.fullUserKey)}savePersistenceForRedirect(){return this.persistence._set(this.fullPersistenceKey,this.persistence.type)}async setPersistence(t){if(this.persistence===t)return;const i=await this.getCurrentUser();return await this.removeCurrentUser(),this.persistence=t,i?this.setCurrentUser(i):void 0}delete(){this.persistence._removeListener(this.fullUserKey,this.boundEventHandler)}static async create(t,i,e="authUser"){if(!i.length)return new ge(pe(we),t,e);const n=(await Promise.all(i.map((async t=>{if(await t._isAvailable())return t})))).filter((t=>t));let s=n[0]||pe(we);const r=ye(e,t.config.apiKey,t.name);let o=null;for(const e of i)try{const i=await e._get(r);if(i){const n=fe._fromJSON(t,i);e!==s&&(o=n),s=e;break}}catch(t){}const h=n.filter((t=>t._shouldAllowMigration));return s._shouldAllowMigration&&h.length?(s=h[0],o&&await s._set(r,o.toJSON()),await Promise.all(i.map((async t=>{if(t!==s)try{await t._remove(r)}catch(t){}}))),new ge(s,t,e)):new ge(s,t,e)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function me(t){const i=t.toLowerCase();if(i.includes("opera/")||i.includes("opr/")||i.includes("opios/"))return"Opera";if(_e(i))return"IEMobile";if(i.includes("msie")||i.includes("trident/"))return"IE";if(i.includes("edge/"))return"Edge";if(be(i))return"Firefox";if(i.includes("silk/"))return"Silk";if(Ae(i))return"Blackberry";if(Se(i))return"Webos";if(Ee(i))return"Safari";if((i.includes("chrome/")||Ie(i))&&!i.includes("edge/"))return"Chrome";if(Te(i))return"Android";{const i=/([a-zA-Z\d\.]+)\/[a-zA-Z\d\.]*$/,e=t.match(i);if(2===(null==e?void 0:e.length))return e[1]}return"Other"}function be(t=gt()){return/firefox\//i.test(t)}function Ee(t=gt()){const i=t.toLowerCase();return i.includes("safari/")&&!i.includes("chrome/")&&!i.includes("crios/")&&!i.includes("android")}function Ie(t=gt()){return/crios\//i.test(t)}function _e(t=gt()){return/iemobile/i.test(t)}function Te(t=gt()){return/android/i.test(t)}function Ae(t=gt()){return/blackberry/i.test(t)}function Se(t=gt()){return/webos/i.test(t)}function ke(t=gt()){return/iphone|ipad|ipod/i.test(t)||/macintosh/i.test(t)&&/mobile/i.test(t)}function Ne(){return function(){const t=gt();return t.indexOf("MSIE ")>=0||t.indexOf("Trident/")>=0}()&&10===document.documentMode}function Oe(t=gt()){return ke(t)||Te(t)||Se(t)||Ae(t)||/windows phone/i.test(t)||_e(t)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function De(t,i=[]){let e;switch(t){case"Browser":e=me(gt());break;case"Worker":e=`${me(gt())}-${t}`;break;default:e=t}const n=i.length?i.join(","):"FirebaseCore-web";return`${e}/JsCore/${pi}/${n}`}
/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Re{constructor(t){this.auth=t,this.queue=[]}pushCallback(t,i){const e=i=>new Promise(((e,n)=>{try{e(t(i))}catch(t){n(t)}}));e.onAbort=i,this.queue.push(e);const n=this.queue.length-1;return()=>{this.queue[n]=()=>Promise.resolve()}}async runMiddleware(t){if(this.auth.currentUser===t)return;const i=[];try{for(const e of this.queue)await e(t),e.onAbort&&i.push(e.onAbort)}catch(t){i.reverse();for(const t of i)try{t()}catch(t){}throw this.auth._errorFactory.create("login-blocked",{originalMessage:null==t?void 0:t.message})}}}
/**
 * @license
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ce{constructor(t){var i,e,n,s;const r=t.customStrengthOptions;this.customStrengthOptions={},this.customStrengthOptions.minPasswordLength=null!==(i=r.minPasswordLength)&&void 0!==i?i:6,r.maxPasswordLength&&(this.customStrengthOptions.maxPasswordLength=r.maxPasswordLength),void 0!==r.containsLowercaseCharacter&&(this.customStrengthOptions.containsLowercaseLetter=r.containsLowercaseCharacter),void 0!==r.containsUppercaseCharacter&&(this.customStrengthOptions.containsUppercaseLetter=r.containsUppercaseCharacter),void 0!==r.containsNumericCharacter&&(this.customStrengthOptions.containsNumericCharacter=r.containsNumericCharacter),void 0!==r.containsNonAlphanumericCharacter&&(this.customStrengthOptions.containsNonAlphanumericCharacter=r.containsNonAlphanumericCharacter),this.enforcementState=t.enforcementState,"ENFORCEMENT_STATE_UNSPECIFIED"===this.enforcementState&&(this.enforcementState="OFF"),this.allowedNonAlphanumericCharacters=null!==(n=null===(e=t.allowedNonAlphanumericCharacters)||void 0===e?void 0:e.join(""))&&void 0!==n?n:"",this.forceUpgradeOnSignin=null!==(s=t.forceUpgradeOnSignin)&&void 0!==s&&s,this.schemaVersion=t.schemaVersion}validatePassword(t){var i,e,n,s,r,o;const h={isValid:!0,passwordPolicy:this};return this.validatePasswordLengthOptions(t,h),this.validatePasswordCharacterOptions(t,h),h.isValid&&(h.isValid=null===(i=h.meetsMinPasswordLength)||void 0===i||i),h.isValid&&(h.isValid=null===(e=h.meetsMaxPasswordLength)||void 0===e||e),h.isValid&&(h.isValid=null===(n=h.containsLowercaseLetter)||void 0===n||n),h.isValid&&(h.isValid=null===(s=h.containsUppercaseLetter)||void 0===s||s),h.isValid&&(h.isValid=null===(r=h.containsNumericCharacter)||void 0===r||r),h.isValid&&(h.isValid=null===(o=h.containsNonAlphanumericCharacter)||void 0===o||o),h}validatePasswordLengthOptions(t,i){const e=this.customStrengthOptions.minPasswordLength,n=this.customStrengthOptions.maxPasswordLength;e&&(i.meetsMinPasswordLength=t.length>=e),n&&(i.meetsMaxPasswordLength=t.length<=n)}validatePasswordCharacterOptions(t,i){let e;this.updatePasswordCharacterOptionsStatuses(i,!1,!1,!1,!1);for(let n=0;n<t.length;n++)e=t.charAt(n),this.updatePasswordCharacterOptionsStatuses(i,e>="a"&&e<="z",e>="A"&&e<="Z",e>="0"&&e<="9",this.allowedNonAlphanumericCharacters.includes(e))}updatePasswordCharacterOptionsStatuses(t,i,e,n,s){this.customStrengthOptions.containsLowercaseLetter&&(t.containsLowercaseLetter||(t.containsLowercaseLetter=i)),this.customStrengthOptions.containsUppercaseLetter&&(t.containsUppercaseLetter||(t.containsUppercaseLetter=e)),this.customStrengthOptions.containsNumericCharacter&&(t.containsNumericCharacter||(t.containsNumericCharacter=n)),this.customStrengthOptions.containsNonAlphanumericCharacter&&(t.containsNonAlphanumericCharacter||(t.containsNonAlphanumericCharacter=s))}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Pe{constructor(t,i,e,n){this.app=t,this.heartbeatServiceProvider=i,this.appCheckServiceProvider=e,this.config=n,this.currentUser=null,this.emulatorConfig=null,this.operations=Promise.resolve(),this.authStateSubscription=new $e(this),this.idTokenSubscription=new $e(this),this.beforeStateQueue=new Re(this),this.redirectUser=null,this.isProactiveRefreshEnabled=!1,this.EXPECTED_PASSWORD_POLICY_SCHEMA_VERSION=1,this._canInitEmulator=!0,this._isInitialized=!1,this._deleted=!1,this._initializationPromise=null,this._popupRedirectResolver=null,this._errorFactory=Pi,this._agentRecaptchaConfig=null,this._tenantRecaptchaConfigs={},this._projectPasswordPolicy=null,this._tenantPasswordPolicies={},this.lastNotifiedUid=void 0,this.languageCode=null,this.tenantId=null,this.settings={appVerificationDisabledForTesting:!1},this.frameworks=[],this.name=t.name,this.clientVersion=n.sdkClientVersion}_initializeWithPersistence(t,i){return i&&(this._popupRedirectResolver=pe(i)),this._initializationPromise=this.queue((async()=>{var e,n;if(!this._deleted&&(this.persistenceManager=await ge.create(this,t),!this._deleted)){if(null===(e=this._popupRedirectResolver)||void 0===e?void 0:e._shouldInitProactively)try{await this._popupRedirectResolver._initialize(this)}catch(t){}await this.initializeCurrentUser(i),this.lastNotifiedUid=(null===(n=this.currentUser)||void 0===n?void 0:n.uid)||null,this._deleted||(this._isInitialized=!0)}})),this._initializationPromise}async _onStorageEvent(){if(this._deleted)return;const t=await this.assertedPersistence.getCurrentUser();return this.currentUser||t?this.currentUser&&t&&this.currentUser.uid===t.uid?(this._currentUser._assign(t),void await this.currentUser.getIdToken()):void await this._updateCurrentUser(t,!0):void 0}async initializeCurrentUser(t){var i;const e=await this.assertedPersistence.getCurrentUser();let n=e,s=!1;if(t&&this.config.authDomain){await this.getOrInitRedirectPersistenceManager();const e=null===(i=this.redirectUser)||void 0===i?void 0:i._redirectEventId,r=null==n?void 0:n._redirectEventId,o=await this.tryRedirectSignIn(t);e&&e!==r||!(null==o?void 0:o.user)||(n=o.user,s=!0)}if(!n)return this.directlySetCurrentUser(null);if(!n._redirectEventId){if(s)try{await this.beforeStateQueue.runMiddleware(n)}catch(t){n=e,this._popupRedirectResolver._overrideRedirectResult(this,(()=>Promise.reject(t)))}return n?this.reloadAndSetCurrentUserOrClear(n):this.directlySetCurrentUser(null)}return Fi(this._popupRedirectResolver,this,"argument-error"),await this.getOrInitRedirectPersistenceManager(),this.redirectUser&&this.redirectUser._redirectEventId===n._redirectEventId?this.directlySetCurrentUser(n):this.reloadAndSetCurrentUserOrClear(n)}async tryRedirectSignIn(t){let i=null;try{i=await this._popupRedirectResolver._completeRedirectFn(this,t,!0)}catch(t){await this._setRedirectUser(null)}return i}async reloadAndSetCurrentUserOrClear(t){try{await ue(t)}catch(t){if("auth/network-request-failed"!==(null==t?void 0:t.code))return this.directlySetCurrentUser(null)}return this.directlySetCurrentUser(t)}useDeviceLanguage(){this.languageCode=function(){if("undefined"==typeof navigator)return null;const t=navigator;return t.languages&&t.languages[0]||t.language||null}()}async _delete(){this._deleted=!0}async updateCurrentUser(t){const i=t?kt(t):null;return i&&Fi(i.auth.config.apiKey===this.config.apiKey,this,"invalid-user-token"),this._updateCurrentUser(i&&i._clone(this))}async _updateCurrentUser(t,i=!1){if(!this._deleted)return t&&Fi(this.tenantId===t.tenantId,this,"tenant-id-mismatch"),i||await this.beforeStateQueue.runMiddleware(t),this.queue((async()=>{await this.directlySetCurrentUser(t),this.notifyAuthListeners()}))}async signOut(){return await this.beforeStateQueue.runMiddleware(null),(this.redirectPersistenceManager||this._popupRedirectResolver)&&await this._setRedirectUser(null),this._updateCurrentUser(null,!0)}setPersistence(t){return this.queue((async()=>{await this.assertedPersistence.setPersistence(pe(t))}))}_getRecaptchaConfig(){return null==this.tenantId?this._agentRecaptchaConfig:this._tenantRecaptchaConfigs[this.tenantId]}async validatePassword(t){this._getPasswordPolicyInternal()||await this._updatePasswordPolicy();const i=this._getPasswordPolicyInternal();return i.schemaVersion!==this.EXPECTED_PASSWORD_POLICY_SCHEMA_VERSION?Promise.reject(this._errorFactory.create("unsupported-password-policy-schema-version",{})):i.validatePassword(t)}_getPasswordPolicyInternal(){return null===this.tenantId?this._projectPasswordPolicy:this._tenantPasswordPolicies[this.tenantId]}async _updatePasswordPolicy(){const t=await async function(t,i={}){return Qi(t,"GET","/v2/passwordPolicy",Yi(t,i))}
/**
 * @license
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(this),i=new Ce(t);null===this.tenantId?this._projectPasswordPolicy=i:this._tenantPasswordPolicies[this.tenantId]=i}_getPersistence(){return this.assertedPersistence.persistence.type}_updateErrorMap(t){this._errorFactory=new bt("auth","Firebase",t())}onAuthStateChanged(t,i,e){return this.registerStateListener(this.authStateSubscription,t,i,e)}beforeAuthStateChanged(t,i){return this.beforeStateQueue.pushCallback(t,i)}onIdTokenChanged(t,i,e){return this.registerStateListener(this.idTokenSubscription,t,i,e)}authStateReady(){return new Promise(((t,i)=>{if(this.currentUser)t();else{const e=this.onAuthStateChanged((()=>{e(),t()}),i)}}))}toJSON(){var t;return{apiKey:this.config.apiKey,authDomain:this.config.authDomain,appName:this.name,currentUser:null===(t=this._currentUser)||void 0===t?void 0:t.toJSON()}}async _setRedirectUser(t,i){const e=await this.getOrInitRedirectPersistenceManager(i);return null===t?e.removeCurrentUser():e.setCurrentUser(t)}async getOrInitRedirectPersistenceManager(t){if(!this.redirectPersistenceManager){const i=t&&pe(t)||this._popupRedirectResolver;Fi(i,this,"argument-error"),this.redirectPersistenceManager=await ge.create(this,[pe(i._redirectPersistence)],"redirectUser"),this.redirectUser=await this.redirectPersistenceManager.getCurrentUser()}return this.redirectPersistenceManager}async _redirectUserForId(t){var i,e;return this._isInitialized&&await this.queue((async()=>{})),(null===(i=this._currentUser)||void 0===i?void 0:i._redirectEventId)===t?this._currentUser:(null===(e=this.redirectUser)||void 0===e?void 0:e._redirectEventId)===t?this.redirectUser:null}async _persistUserIfCurrent(t){if(t===this.currentUser)return this.queue((async()=>this.directlySetCurrentUser(t)))}_notifyListenersIfCurrent(t){t===this.currentUser&&this.notifyAuthListeners()}_key(){return`${this.config.authDomain}:${this.config.apiKey}:${this.name}`}_startProactiveRefresh(){this.isProactiveRefreshEnabled=!0,this.currentUser&&this._currentUser._startProactiveRefresh()}_stopProactiveRefresh(){this.isProactiveRefreshEnabled=!1,this.currentUser&&this._currentUser._stopProactiveRefresh()}get _currentUser(){return this.currentUser}notifyAuthListeners(){var t,i;if(!this._isInitialized)return;this.idTokenSubscription.next(this.currentUser);const e=null!==(i=null===(t=this.currentUser)||void 0===t?void 0:t.uid)&&void 0!==i?i:null;this.lastNotifiedUid!==e&&(this.lastNotifiedUid=e,this.authStateSubscription.next(this.currentUser))}registerStateListener(t,i,e,n){if(this._deleted)return()=>{};const s="function"==typeof i?i:i.next.bind(i);let r=!1;const o=this._isInitialized?Promise.resolve():this._initializationPromise;if(Fi(o,this,"internal-error"),o.then((()=>{r||s(this.currentUser)})),"function"==typeof i){const s=t.addObserver(i,e,n);return()=>{r=!0,s()}}{const e=t.addObserver(i);return()=>{r=!0,e()}}}async directlySetCurrentUser(t){this.currentUser&&this.currentUser!==t&&this._currentUser._stopProactiveRefresh(),t&&this.isProactiveRefreshEnabled&&t._startProactiveRefresh(),this.currentUser=t,t?await this.assertedPersistence.setCurrentUser(t):await this.assertedPersistence.removeCurrentUser()}queue(t){return this.operations=this.operations.then(t,t),this.operations}get assertedPersistence(){return Fi(this.persistenceManager,this,"internal-error"),this.persistenceManager}_logFramework(t){t&&!this.frameworks.includes(t)&&(this.frameworks.push(t),this.frameworks.sort(),this.clientVersion=De(this.config.clientPlatform,this._getFrameworks()))}_getFrameworks(){return this.frameworks}async _getAdditionalHeaders(){var t;const i={"X-Client-Version":this.clientVersion};this.app.options.appId&&(i["X-Firebase-gmpid"]=this.app.options.appId);const e=await(null===(t=this.heartbeatServiceProvider.getImmediate({optional:!0}))||void 0===t?void 0:t.getHeartbeatsHeader());e&&(i["X-Firebase-Client"]=e);const n=await this._getAppCheckToken();return n&&(i["X-Firebase-AppCheck"]=n),i}async _getAppCheckToken(){var t;const i=await(null===(t=this.appCheckServiceProvider.getImmediate({optional:!0}))||void 0===t?void 0:t.getToken());return(null==i?void 0:i.error)&&function(t,...i){Mi.logLevel<=Ct.WARN&&Mi.warn(`Auth (${pi}): ${t}`,...i)}(`Error while retrieving App Check token: ${i.error}`),null==i?void 0:i.token}}function Me(t){return kt(t)}class $e{constructor(t){this.auth=t,this.observer=null,this.addObserver=function(t,i){const e=new At(t,i);return e.subscribe.bind(e)}((t=>this.observer=t))}get next(){return Fi(this.observer,this.auth,"internal-error"),this.observer.next.bind(this.observer)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Le(t){return new Promise(((i,e)=>{const n=document.createElement("script");var s,r;n.setAttribute("src",t),n.onload=i,n.onerror=t=>{const i=xi("internal-error");i.customData=t,e(i)},n.type="text/javascript",n.charset="UTF-8",(null!==(r=null===(s=document.getElementsByTagName("head"))||void 0===s?void 0:s[0])&&void 0!==r?r:document).appendChild(n)}))}function xe(t,i,e){const n=Me(t);Fi(n._canInitEmulator,n,"emulator-config-failed"),Fi(/^https?:\/\//.test(i),n,"invalid-emulator-scheme");const s=!!(null==e?void 0:e.disableWarnings),r=Ue(i),{host:o,port:h}=function(t){const i=Ue(t),e=/(\/\/)?([^?#/]+)/.exec(t.substr(i.length));if(!e)return{host:"",port:null};const n=e[2].split("@").pop()||"",s=/^(\[[^\]]+\])(:|$)/.exec(n);if(s){const t=s[1];return{host:t,port:je(n.substr(t.length+1))}}{const[t,i]=n.split(":");return{host:t,port:je(i)}}}(i),a=null===h?"":`:${h}`;n.config.emulator={url:`${r}//${o}${a}/`},n.settings.appVerificationDisabledForTesting=!0,n.emulatorConfig=Object.freeze({host:o,port:h,protocol:r.replace(":",""),options:Object.freeze({disableWarnings:s})}),s||function(){function t(){const t=document.createElement("p"),i=t.style;t.innerText="Running in emulator mode. Do not use with production credentials.",i.position="fixed",i.width="100%",i.backgroundColor="#ffffff",i.border=".1em solid #000000",i.color="#b50000",i.bottom="0px",i.left="0px",i.margin="0px",i.zIndex="10000",i.textAlign="center",t.classList.add("firebase-emulator-warning"),document.body.appendChild(t)}"undefined"!=typeof console&&"function"==typeof console.info&&console.info("WARNING: You are using the Auth Emulator, which is intended for local testing only.  Do not use with production credentials.");"undefined"!=typeof window&&"undefined"!=typeof document&&("loading"===document.readyState?window.addEventListener("DOMContentLoaded",t):t())}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */()}function Ue(t){const i=t.indexOf(":");return i<0?"":t.substr(0,i+1)}function je(t){if(!t)return null;const i=Number(t);return isNaN(i)?null:i}class Fe{constructor(t,i){this.providerId=t,this.signInMethod=i}toJSON(){return Bi("not implemented")}_getIdTokenResponse(t){return Bi("not implemented")}_linkToIdToken(t,i){return Bi("not implemented")}_getReauthenticationResolver(t){return Bi("not implemented")}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */async function Be(t,i){return async function(t,i,e,n,s={}){const r=await Qi(t,i,e,n,s);return"mfaPendingCredential"in r&&Li(t,"multi-factor-auth-required",{_serverResponse:r}),r}(t,"POST","/v1/accounts:signInWithIdp",Yi(t,i))}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ve extends Fe{constructor(){super(...arguments),this.pendingToken=null}static _fromParams(t){const i=new Ve(t.providerId,t.signInMethod);return t.idToken||t.accessToken?(t.idToken&&(i.idToken=t.idToken),t.accessToken&&(i.accessToken=t.accessToken),t.nonce&&!t.pendingToken&&(i.nonce=t.nonce),t.pendingToken&&(i.pendingToken=t.pendingToken)):t.oauthToken&&t.oauthTokenSecret?(i.accessToken=t.oauthToken,i.secret=t.oauthTokenSecret):Li("argument-error"),i}toJSON(){return{idToken:this.idToken,accessToken:this.accessToken,secret:this.secret,nonce:this.nonce,pendingToken:this.pendingToken,providerId:this.providerId,signInMethod:this.signInMethod}}static fromJSON(t){const i="string"==typeof t?JSON.parse(t):t,{providerId:e,signInMethod:n}=i,s=Di(i,["providerId","signInMethod"]);if(!e||!n)return null;const r=new Ve(e,n);return r.idToken=s.idToken||void 0,r.accessToken=s.accessToken||void 0,r.secret=s.secret,r.nonce=s.nonce,r.pendingToken=s.pendingToken||null,r}_getIdTokenResponse(t){return Be(t,this.buildRequest())}_linkToIdToken(t,i){const e=this.buildRequest();return e.idToken=i,Be(t,e)}_getReauthenticationResolver(t){const i=this.buildRequest();return i.autoCreate=!1,Be(t,i)}buildRequest(){const t={requestUri:"http://localhost",returnSecureToken:!0};if(this.pendingToken)t.pendingToken=this.pendingToken;else{const i={};this.idToken&&(i.id_token=this.idToken),this.accessToken&&(i.access_token=this.accessToken),this.secret&&(i.oauth_token_secret=this.secret),i.providerId=this.providerId,this.nonce&&!this.pendingToken&&(i.nonce=this.nonce),t.postBody=Tt(i)}return t}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ze{constructor(t){this.providerId=t,this.defaultLanguageCode=null,this.customParameters={}}setDefaultLanguage(t){this.defaultLanguageCode=t}setCustomParameters(t){return this.customParameters=t,this}getCustomParameters(){return this.customParameters}}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class He extends ze{constructor(){super(...arguments),this.scopes=[]}addScope(t){return this.scopes.includes(t)||this.scopes.push(t),this}getScopes(){return[...this.scopes]}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ge extends He{constructor(){super("facebook.com")}static credential(t){return Ve._fromParams({providerId:Ge.PROVIDER_ID,signInMethod:Ge.FACEBOOK_SIGN_IN_METHOD,accessToken:t})}static credentialFromResult(t){return Ge.credentialFromTaggedObject(t)}static credentialFromError(t){return Ge.credentialFromTaggedObject(t.customData||{})}static credentialFromTaggedObject({_tokenResponse:t}){if(!t||!("oauthAccessToken"in t))return null;if(!t.oauthAccessToken)return null;try{return Ge.credential(t.oauthAccessToken)}catch(t){return null}}}Ge.FACEBOOK_SIGN_IN_METHOD="facebook.com",Ge.PROVIDER_ID="facebook.com";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class qe extends He{constructor(){super("google.com"),this.addScope("profile")}static credential(t,i){return Ve._fromParams({providerId:qe.PROVIDER_ID,signInMethod:qe.GOOGLE_SIGN_IN_METHOD,idToken:t,accessToken:i})}static credentialFromResult(t){return qe.credentialFromTaggedObject(t)}static credentialFromError(t){return qe.credentialFromTaggedObject(t.customData||{})}static credentialFromTaggedObject({_tokenResponse:t}){if(!t)return null;const{oauthIdToken:i,oauthAccessToken:e}=t;if(!i&&!e)return null;try{return qe.credential(i,e)}catch(t){return null}}}qe.GOOGLE_SIGN_IN_METHOD="google.com",qe.PROVIDER_ID="google.com";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class We extends He{constructor(){super("github.com")}static credential(t){return Ve._fromParams({providerId:We.PROVIDER_ID,signInMethod:We.GITHUB_SIGN_IN_METHOD,accessToken:t})}static credentialFromResult(t){return We.credentialFromTaggedObject(t)}static credentialFromError(t){return We.credentialFromTaggedObject(t.customData||{})}static credentialFromTaggedObject({_tokenResponse:t}){if(!t||!("oauthAccessToken"in t))return null;if(!t.oauthAccessToken)return null;try{return We.credential(t.oauthAccessToken)}catch(t){return null}}}We.GITHUB_SIGN_IN_METHOD="github.com",We.PROVIDER_ID="github.com";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Je extends He{constructor(){super("twitter.com")}static credential(t,i){return Ve._fromParams({providerId:Je.PROVIDER_ID,signInMethod:Je.TWITTER_SIGN_IN_METHOD,oauthToken:t,oauthTokenSecret:i})}static credentialFromResult(t){return Je.credentialFromTaggedObject(t)}static credentialFromError(t){return Je.credentialFromTaggedObject(t.customData||{})}static credentialFromTaggedObject({_tokenResponse:t}){if(!t)return null;const{oauthAccessToken:i,oauthTokenSecret:e}=t;if(!i||!e)return null;try{return Je.credential(i,e)}catch(t){return null}}}Je.TWITTER_SIGN_IN_METHOD="twitter.com",Je.PROVIDER_ID="twitter.com";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Ke{constructor(t){this.user=t.user,this.providerId=t.providerId,this._tokenResponse=t._tokenResponse,this.operationType=t.operationType}static async _fromIdTokenResponse(t,i,e,n=!1){const s=await fe._fromIdTokenResponse(t,e,n),r=Xe(e);return new Ke({user:s,providerId:r,_tokenResponse:e,operationType:i})}static async _forOperation(t,i,e){await t._updateTokensIfNecessary(e,!0);const n=Xe(e);return new Ke({user:t,providerId:n,_tokenResponse:e,operationType:i})}}function Xe(t){return t.providerId?t.providerId:"phoneNumber"in t?"phone":null}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ye extends mt{constructor(t,i,e,n){var s;super(i.code,i.message),this.operationType=e,this.user=n,Object.setPrototypeOf(this,Ye.prototype),this.customData={appName:t.name,tenantId:null!==(s=t.tenantId)&&void 0!==s?s:void 0,_serverResponse:i.customData._serverResponse,operationType:e}}static _fromErrorAndOperation(t,i,e,n){return new Ye(t,i,e,n)}}function Qe(t,i,e,n){return("reauthenticate"===i?e._getReauthenticationResolver(t):e._getIdTokenResponse(t)).catch((e=>{if("auth/multi-factor-auth-required"===e.code)throw Ye._fromErrorAndOperation(t,e,i,n);throw e}))}const Ze="__sak";
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class tn{constructor(t,i){this.storageRetriever=t,this.type=i}_isAvailable(){try{return this.storage?(this.storage.setItem(Ze,"1"),this.storage.removeItem(Ze),Promise.resolve(!0)):Promise.resolve(!1)}catch(t){return Promise.resolve(!1)}}_set(t,i){return this.storage.setItem(t,JSON.stringify(i)),Promise.resolve()}_get(t){const i=this.storage.getItem(t);return Promise.resolve(i?JSON.parse(i):null)}_remove(t){return this.storage.removeItem(t),Promise.resolve()}get storage(){return this.storageRetriever()}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class en extends tn{constructor(){super((()=>window.localStorage),"LOCAL"),this.boundEventHandler=(t,i)=>this.onStorageEvent(t,i),this.listeners={},this.localCache={},this.pollTimer=null,this.safariLocalStorageNotSynced=function(){const t=gt();return Ee(t)||ke(t)}()&&function(){try{return!(!window||window===window.top)}catch(t){return!1}}(),this.fallbackToPolling=Oe(),this._shouldAllowMigration=!0}forAllChangedKeys(t){for(const i of Object.keys(this.listeners)){const e=this.storage.getItem(i),n=this.localCache[i];e!==n&&t(i,n,e)}}onStorageEvent(t,i=!1){if(!t.key)return void this.forAllChangedKeys(((t,i,e)=>{this.notifyListeners(t,e)}));const e=t.key;if(i?this.detachListener():this.stopPolling(),this.safariLocalStorageNotSynced){const n=this.storage.getItem(e);if(t.newValue!==n)null!==t.newValue?this.storage.setItem(e,t.newValue):this.storage.removeItem(e);else if(this.localCache[e]===t.newValue&&!i)return}const n=()=>{const t=this.storage.getItem(e);(i||this.localCache[e]!==t)&&this.notifyListeners(e,t)},s=this.storage.getItem(e);Ne()&&s!==t.newValue&&t.newValue!==t.oldValue?setTimeout(n,10):n()}notifyListeners(t,i){this.localCache[t]=i;const e=this.listeners[t];if(e)for(const t of Array.from(e))t(i?JSON.parse(i):i)}startPolling(){this.stopPolling(),this.pollTimer=setInterval((()=>{this.forAllChangedKeys(((t,i,e)=>{this.onStorageEvent(new StorageEvent("storage",{key:t,oldValue:i,newValue:e}),!0)}))}),1e3)}stopPolling(){this.pollTimer&&(clearInterval(this.pollTimer),this.pollTimer=null)}attachListener(){window.addEventListener("storage",this.boundEventHandler)}detachListener(){window.removeEventListener("storage",this.boundEventHandler)}_addListener(t,i){0===Object.keys(this.listeners).length&&(this.fallbackToPolling?this.startPolling():this.attachListener()),this.listeners[t]||(this.listeners[t]=new Set,this.localCache[t]=this.storage.getItem(t)),this.listeners[t].add(i)}_removeListener(t,i){this.listeners[t]&&(this.listeners[t].delete(i),0===this.listeners[t].size&&delete this.listeners[t]),0===Object.keys(this.listeners).length&&(this.detachListener(),this.stopPolling())}async _set(t,i){await super._set(t,i),this.localCache[t]=JSON.stringify(i)}async _get(t){const i=await super._get(t);return this.localCache[t]=JSON.stringify(i),i}async _remove(t){await super._remove(t),delete this.localCache[t]}}en.type="LOCAL";const nn=en;
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class sn extends tn{constructor(){super((()=>window.sessionStorage),"SESSION")}_addListener(t,i){}_removeListener(t,i){}}sn.type="SESSION";const rn=sn;
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class on{constructor(t){this.eventTarget=t,this.handlersMap={},this.boundEventHandler=this.handleEvent.bind(this)}static _getInstance(t){const i=this.receivers.find((i=>i.isListeningto(t)));if(i)return i;const e=new on(t);return this.receivers.push(e),e}isListeningto(t){return this.eventTarget===t}async handleEvent(t){const i=t,{eventId:e,eventType:n,data:s}=i.data,r=this.handlersMap[n];if(!(null==r?void 0:r.size))return;i.ports[0].postMessage({status:"ack",eventId:e,eventType:n});const o=Array.from(r).map((async t=>t(i.origin,s))),h=await function(t){return Promise.all(t.map((async t=>{try{return{fulfilled:!0,value:await t}}catch(t){return{fulfilled:!1,reason:t}}})))}(o);i.ports[0].postMessage({status:"done",eventId:e,eventType:n,response:h})}_subscribe(t,i){0===Object.keys(this.handlersMap).length&&this.eventTarget.addEventListener("message",this.boundEventHandler),this.handlersMap[t]||(this.handlersMap[t]=new Set),this.handlersMap[t].add(i)}_unsubscribe(t,i){this.handlersMap[t]&&i&&this.handlersMap[t].delete(i),i&&0!==this.handlersMap[t].size||delete this.handlersMap[t],0===Object.keys(this.handlersMap).length&&this.eventTarget.removeEventListener("message",this.boundEventHandler)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function hn(t="",i=10){let e="";for(let t=0;t<i;t++)e+=Math.floor(10*Math.random());return t+e}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */on.receivers=[];class an{constructor(t){this.target=t,this.handlers=new Set}removeMessageHandler(t){t.messageChannel&&(t.messageChannel.port1.removeEventListener("message",t.onMessage),t.messageChannel.port1.close()),this.handlers.delete(t)}async _send(t,i,e=50){const n="undefined"!=typeof MessageChannel?new MessageChannel:null;if(!n)throw new Error("connection_unavailable");let s,r;return new Promise(((o,h)=>{const a=hn("",20);n.port1.start();const u=setTimeout((()=>{h(new Error("unsupported_event"))}),e);r={messageChannel:n,onMessage(t){const i=t;if(i.data.eventId===a)switch(i.data.status){case"ack":clearTimeout(u),s=setTimeout((()=>{h(new Error("timeout"))}),3e3);break;case"done":clearTimeout(s),o(i.data.response);break;default:clearTimeout(u),clearTimeout(s),h(new Error("invalid_response"))}}},this.handlers.add(r),n.port1.addEventListener("message",r.onMessage),this.target.postMessage({eventType:t,eventId:a,data:i},[n.port2])})).finally((()=>{r&&this.removeMessageHandler(r)}))}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function un(){return window}
/**
 * @license
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function cn(){return void 0!==un().WorkerGlobalScope&&"function"==typeof un().importScripts}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const ln="firebaseLocalStorageDb",fn="firebaseLocalStorage",dn="fbase_key";class pn{constructor(t){this.request=t}toPromise(){return new Promise(((t,i)=>{this.request.addEventListener("success",(()=>{t(this.request.result)})),this.request.addEventListener("error",(()=>{i(this.request.error)}))}))}}function vn(t,i){return t.transaction([fn],i?"readwrite":"readonly").objectStore(fn)}function wn(){const t=indexedDB.open(ln,1);return new Promise(((i,e)=>{t.addEventListener("error",(()=>{e(t.error)})),t.addEventListener("upgradeneeded",(()=>{const i=t.result;try{i.createObjectStore(fn,{keyPath:dn})}catch(t){e(t)}})),t.addEventListener("success",(async()=>{const e=t.result;e.objectStoreNames.contains(fn)?i(e):(e.close(),await function(){const t=indexedDB.deleteDatabase(ln);return new pn(t).toPromise()}(),i(await wn()))}))}))}async function yn(t,i,e){const n=vn(t,!0).put({[dn]:i,value:e});return new pn(n).toPromise()}function gn(t,i){const e=vn(t,!0).delete(i);return new pn(e).toPromise()}class mn{constructor(){this.type="LOCAL",this._shouldAllowMigration=!0,this.listeners={},this.localCache={},this.pollTimer=null,this.pendingWrites=0,this.receiver=null,this.sender=null,this.serviceWorkerReceiverAvailable=!1,this.activeServiceWorker=null,this._workerInitializationPromise=this.initializeServiceWorkerMessaging().then((()=>{}),(()=>{}))}async _openDb(){return this.db||(this.db=await wn()),this.db}async _withRetries(t){let i=0;for(;;)try{const i=await this._openDb();return await t(i)}catch(t){if(i++>3)throw t;this.db&&(this.db.close(),this.db=void 0)}}async initializeServiceWorkerMessaging(){return cn()?this.initializeReceiver():this.initializeSender()}async initializeReceiver(){this.receiver=on._getInstance(cn()?self:null),this.receiver._subscribe("keyChanged",(async(t,i)=>({keyProcessed:(await this._poll()).includes(i.key)}))),this.receiver._subscribe("ping",(async(t,i)=>["keyChanged"]))}async initializeSender(){var t,i;if(this.activeServiceWorker=await async function(){if(!(null===navigator||void 0===navigator?void 0:navigator.serviceWorker))return null;try{return(await navigator.serviceWorker.ready).active}catch(t){return null}}(),!this.activeServiceWorker)return;this.sender=new an(this.activeServiceWorker);const e=await this.sender._send("ping",{},800);e&&(null===(t=e[0])||void 0===t?void 0:t.fulfilled)&&(null===(i=e[0])||void 0===i?void 0:i.value.includes("keyChanged"))&&(this.serviceWorkerReceiverAvailable=!0)}async notifyServiceWorker(t){var i;if(this.sender&&this.activeServiceWorker&&((null===(i=null===navigator||void 0===navigator?void 0:navigator.serviceWorker)||void 0===i?void 0:i.controller)||null)===this.activeServiceWorker)try{await this.sender._send("keyChanged",{key:t},this.serviceWorkerReceiverAvailable?800:50)}catch(i){}}async _isAvailable(){try{if(!indexedDB)return!1;const t=await wn();return await yn(t,Ze,"1"),await gn(t,Ze),!0}catch(t){}return!1}async _withPendingWrite(t){this.pendingWrites++;try{await t()}finally{this.pendingWrites--}}async _set(t,i){return this._withPendingWrite((async()=>(await this._withRetries((e=>yn(e,t,i))),this.localCache[t]=i,this.notifyServiceWorker(t))))}async _get(t){const i=await this._withRetries((i=>async function(t,i){const e=vn(t,!1).get(i),n=await new pn(e).toPromise();return void 0===n?null:n.value}(i,t)));return this.localCache[t]=i,i}async _remove(t){return this._withPendingWrite((async()=>(await this._withRetries((i=>gn(i,t))),delete this.localCache[t],this.notifyServiceWorker(t))))}async _poll(){const t=await this._withRetries((t=>{const i=vn(t,!1).getAll();return new pn(i).toPromise()}));if(!t)return[];if(0!==this.pendingWrites)return[];const i=[],e=new Set;for(const{fbase_key:n,value:s}of t)e.add(n),JSON.stringify(this.localCache[n])!==JSON.stringify(s)&&(this.notifyListeners(n,s),i.push(n));for(const t of Object.keys(this.localCache))this.localCache[t]&&!e.has(t)&&(this.notifyListeners(t,null),i.push(t));return i}notifyListeners(t,i){this.localCache[t]=i;const e=this.listeners[t];if(e)for(const t of Array.from(e))t(i)}startPolling(){this.stopPolling(),this.pollTimer=setInterval((async()=>this._poll()),800)}stopPolling(){this.pollTimer&&(clearInterval(this.pollTimer),this.pollTimer=null)}_addListener(t,i){0===Object.keys(this.listeners).length&&this.startPolling(),this.listeners[t]||(this.listeners[t]=new Set,this._get(t)),this.listeners[t].add(i)}_removeListener(t,i){this.listeners[t]&&(this.listeners[t].delete(i),0===this.listeners[t].size&&delete this.listeners[t]),0===Object.keys(this.listeners).length&&this.stopPolling()}}mn.type="LOCAL";const bn=mn;
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function En(t,i){return i?pe(i):(Fi(t._popupRedirectResolver,t,"argument-error"),t._popupRedirectResolver)}
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */new qi(3e4,6e4);class In extends Fe{constructor(t){super("custom","custom"),this.params=t}_getIdTokenResponse(t){return Be(t,this._buildIdpRequest())}_linkToIdToken(t,i){return Be(t,this._buildIdpRequest(i))}_getReauthenticationResolver(t){return Be(t,this._buildIdpRequest())}_buildIdpRequest(t){const i={requestUri:this.params.requestUri,sessionId:this.params.sessionId,postBody:this.params.postBody,tenantId:this.params.tenantId,pendingToken:this.params.pendingToken,returnSecureToken:!0,returnIdpCredential:!0};return t&&(i.idToken=t),i}}function _n(t){
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
return async function(t,i,e=!1){const n="signIn",s=await Qe(t,n,i),r=await Ke._fromIdTokenResponse(t,n,s);return e||await t._updateCurrentUser(r.user),r}(t.auth,new In(t),t.bypassAuthState)}function Tn(t){const{auth:i,user:e}=t;return Fi(e,i,"internal-error"),
/**
 * @license
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
async function(t,i,e=!1){const{auth:n}=t,s="reauthenticate";try{const r=await oe(t,Qe(n,s,i,t),e);Fi(r.idToken,n,"internal-error");const o=re(r.idToken);Fi(o,n,"internal-error");const{sub:h}=o;return Fi(t.uid===h,n,"user-mismatch"),Ke._forOperation(t,s,r)}catch(t){throw"auth/user-not-found"===(null==t?void 0:t.code)&&Li(n,"user-mismatch"),t}}(e,new In(t),t.bypassAuthState)}async function An(t){const{auth:i,user:e}=t;return Fi(e,i,"internal-error"),async function(t,i,e=!1){const n=await oe(t,i._linkToIdToken(t.auth,await t.getIdToken()),e);return Ke._forOperation(t,"link",n)}(e,new In(t),t.bypassAuthState)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Sn{constructor(t,i,e,n,s=!1){this.auth=t,this.resolver=e,this.user=n,this.bypassAuthState=s,this.pendingPromise=null,this.eventManager=null,this.filter=Array.isArray(i)?i:[i]}execute(){return new Promise((async(t,i)=>{this.pendingPromise={resolve:t,reject:i};try{this.eventManager=await this.resolver._initialize(this.auth),await this.onExecution(),this.eventManager.registerConsumer(this)}catch(t){this.reject(t)}}))}async onAuthEvent(t){const{urlResponse:i,sessionId:e,postBody:n,tenantId:s,error:r,type:o}=t;if(r)return void this.reject(r);const h={auth:this.auth,requestUri:i,sessionId:e,tenantId:s||void 0,postBody:n||void 0,user:this.user,bypassAuthState:this.bypassAuthState};try{this.resolve(await this.getIdpTask(o)(h))}catch(t){this.reject(t)}}onError(t){this.reject(t)}getIdpTask(t){switch(t){case"signInViaPopup":case"signInViaRedirect":return _n;case"linkViaPopup":case"linkViaRedirect":return An;case"reauthViaPopup":case"reauthViaRedirect":return Tn;default:Li(this.auth,"internal-error")}}resolve(t){Vi(this.pendingPromise,"Pending promise was never set"),this.pendingPromise.resolve(t),this.unregisterAndCleanUp()}reject(t){Vi(this.pendingPromise,"Pending promise was never set"),this.pendingPromise.reject(t),this.unregisterAndCleanUp()}unregisterAndCleanUp(){this.eventManager&&this.eventManager.unregisterConsumer(this),this.pendingPromise=null,this.cleanUp()}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const kn=new qi(2e3,1e4);async function Nn(t,i,e){const n=Me(t);!function(t,i,e){if(!(i instanceof e))throw e.name!==i.constructor.name&&Li(t,"argument-error"),Ui(t,"argument-error",`Type of ${i.constructor.name} does not match expected instance.Did you pass a reference from a different Auth SDK?`)}(t,i,ze);const s=En(n,e);return new On(n,"signInViaPopup",i,s).executeNotNull()}class On extends Sn{constructor(t,i,e,n,s){super(t,i,n,s),this.provider=e,this.authWindow=null,this.pollId=null,On.currentPopupAction&&On.currentPopupAction.cancel(),On.currentPopupAction=this}async executeNotNull(){const t=await this.execute();return Fi(t,this.auth,"internal-error"),t}async onExecution(){Vi(1===this.filter.length,"Popup operations only handle one event");const t=hn();this.authWindow=await this.resolver._openPopup(this.auth,this.provider,this.filter[0],t),this.authWindow.associatedEvent=t,this.resolver._originValidation(this.auth).catch((t=>{this.reject(t)})),this.resolver._isIframeWebStorageSupported(this.auth,(t=>{t||this.reject(xi(this.auth,"web-storage-unsupported"))})),this.pollUserCancellation()}get eventId(){var t;return(null===(t=this.authWindow)||void 0===t?void 0:t.associatedEvent)||null}cancel(){this.reject(xi(this.auth,"cancelled-popup-request"))}cleanUp(){this.authWindow&&this.authWindow.close(),this.pollId&&window.clearTimeout(this.pollId),this.authWindow=null,this.pollId=null,On.currentPopupAction=null}pollUserCancellation(){const t=()=>{var i,e;(null===(e=null===(i=this.authWindow)||void 0===i?void 0:i.window)||void 0===e?void 0:e.closed)?this.pollId=window.setTimeout((()=>{this.pollId=null,this.reject(xi(this.auth,"popup-closed-by-user"))}),8e3):this.pollId=window.setTimeout(t,kn.get())};t()}}On.currentPopupAction=null;
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const Dn="pendingRedirect",Rn=new Map;class Cn extends Sn{constructor(t,i,e=!1){super(t,["signInViaRedirect","linkViaRedirect","reauthViaRedirect","unknown"],i,void 0,e),this.eventId=null}async execute(){let t=Rn.get(this.auth._key());if(!t){try{const i=await async function(t,i){const e=function(t){return ye(Dn,t.config.apiKey,t.name)}(i),n=function(t){return pe(t._redirectPersistence)}(t);if(!await n._isAvailable())return!1;const s="true"===await n._get(e);return await n._remove(e),s}(this.resolver,this.auth),e=i?await super.execute():null;t=()=>Promise.resolve(e)}catch(i){t=()=>Promise.reject(i)}Rn.set(this.auth._key(),t)}return this.bypassAuthState||Rn.set(this.auth._key(),(()=>Promise.resolve(null))),t()}async onAuthEvent(t){if("signInViaRedirect"===t.type)return super.onAuthEvent(t);if("unknown"!==t.type){if(t.eventId){const i=await this.auth._redirectUserForId(t.eventId);if(i)return this.user=i,super.onAuthEvent(t);this.resolve(null)}}else this.resolve(null)}async onExecution(){}cleanUp(){}}function Pn(t,i){Rn.set(t._key(),i)}async function Mn(t,i,e=!1){const n=Me(t),s=En(n,i),r=new Cn(n,s,e),o=await r.execute();return o&&!e&&(delete o.user._redirectEventId,await n._persistUserIfCurrent(o.user),await n._setRedirectUser(null,i)),o}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class $n{constructor(t){this.auth=t,this.cachedEventUids=new Set,this.consumers=new Set,this.queuedRedirectEvent=null,this.hasHandledPotentialRedirect=!1,this.lastProcessedEventTime=Date.now()}registerConsumer(t){this.consumers.add(t),this.queuedRedirectEvent&&this.isEventForConsumer(this.queuedRedirectEvent,t)&&(this.sendToConsumer(this.queuedRedirectEvent,t),this.saveEventToCache(this.queuedRedirectEvent),this.queuedRedirectEvent=null)}unregisterConsumer(t){this.consumers.delete(t)}onEvent(t){if(this.hasEventBeenHandled(t))return!1;let i=!1;return this.consumers.forEach((e=>{this.isEventForConsumer(t,e)&&(i=!0,this.sendToConsumer(t,e),this.saveEventToCache(t))})),this.hasHandledPotentialRedirect||!function(t){switch(t.type){case"signInViaRedirect":case"linkViaRedirect":case"reauthViaRedirect":return!0;case"unknown":return xn(t);default:return!1}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(t)||(this.hasHandledPotentialRedirect=!0,i||(this.queuedRedirectEvent=t,i=!0)),i}sendToConsumer(t,i){var e;if(t.error&&!xn(t)){const n=(null===(e=t.error.code)||void 0===e?void 0:e.split("auth/")[1])||"internal-error";i.onError(xi(this.auth,n))}else i.onAuthEvent(t)}isEventForConsumer(t,i){const e=null===i.eventId||!!t.eventId&&t.eventId===i.eventId;return i.filter.includes(t.type)&&e}hasEventBeenHandled(t){return Date.now()-this.lastProcessedEventTime>=6e5&&this.cachedEventUids.clear(),this.cachedEventUids.has(Ln(t))}saveEventToCache(t){this.cachedEventUids.add(Ln(t)),this.lastProcessedEventTime=Date.now()}}function Ln(t){return[t.type,t.eventId,t.sessionId,t.tenantId].filter((t=>t)).join("-")}function xn({type:t,error:i}){return"unknown"===t&&"auth/no-auth-event"===(null==i?void 0:i.code)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const Un=/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/,jn=/^https?/;async function Fn(t){if(t.config.emulator)return;const{authorizedDomains:i}=await async function(t,i={}){return Qi(t,"GET","/v1/projects",i)}(t);for(const t of i)try{if(Bn(t))return}catch(t){}Li(t,"unauthorized-domain")}function Bn(t){const i=zi(),{protocol:e,hostname:n}=new URL(i);if(t.startsWith("chrome-extension://")){const s=new URL(t);return""===s.hostname&&""===n?"chrome-extension:"===e&&t.replace("chrome-extension://","")===i.replace("chrome-extension://",""):"chrome-extension:"===e&&s.hostname===n}if(!jn.test(e))return!1;if(Un.test(t))return n===t;const s=t.replace(/\./g,"\\.");return new RegExp("^(.+\\."+s+"|"+s+")$","i").test(n)}
/**
 * @license
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Vn=new qi(3e4,6e4);function zn(){const t=un().q;if(null==t?void 0:t.H)for(const i of Object.keys(t.H))if(t.H[i].r=t.H[i].r||[],t.H[i].L=t.H[i].L||[],t.H[i].r=[...t.H[i].L],t.CP)for(let i=0;i<t.CP.length;i++)t.CP[i]=null}function Hn(t){return new Promise(((i,e)=>{var n,s,r;function o(){zn(),gapi.load("gapi.iframes",{callback:()=>{i(gapi.iframes.getContext())},ontimeout:()=>{zn(),e(xi(t,"network-request-failed"))},timeout:Vn.get()})}if(null===(s=null===(n=un().gapi)||void 0===n?void 0:n.iframes)||void 0===s?void 0:s.Iframe)i(gapi.iframes.getContext());else{if(!(null===(r=un().gapi)||void 0===r?void 0:r.load)){const i=`__${"iframefcb"}${Math.floor(1e6*Math.random())}`;return un()[i]=()=>{gapi.load?o():e(xi(t,"network-request-failed"))},Le(`https://apis.google.com/js/api.js?onload=${i}`).catch((t=>e(t)))}o()}})).catch((t=>{throw Gn=null,t}))}let Gn=null;
/**
 * @license
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const qn=new qi(5e3,15e3),Wn="__/auth/iframe",Jn="emulator/auth/iframe",Kn={style:{position:"absolute",top:"-100px",width:"1px",height:"1px"},"aria-hidden":"true",tabindex:"-1"},Xn=new Map([["identitytoolkit.googleapis.com","p"],["staging-identitytoolkit.sandbox.googleapis.com","s"],["test-identitytoolkit.sandbox.googleapis.com","t"]]);function Yn(t){const i=t.config;Fi(i.authDomain,t,"auth-domain-config-required");const e=i.emulator?Wi(i,Jn):`https://${t.config.authDomain}/${Wn}`,n={apiKey:i.apiKey,appName:t.name,v:pi},s=Xn.get(t.config.apiHost);s&&(n.eid=s);const r=t._getFrameworks();return r.length&&(n.fw=r.join(",")),`${e}?${Tt(n).slice(1)}`}async function Qn(t){const i=await function(t){return Gn=Gn||Hn(t),Gn}(t),e=un().gapi;return Fi(e,t,"internal-error"),i.open({where:document.body,url:Yn(t),messageHandlersFilter:e.iframes.CROSS_ORIGIN_IFRAMES_FILTER,attributes:Kn,dontclear:!0},(i=>new Promise((async(e,n)=>{await i.restyle({setHideOnLeave:!1});const s=xi(t,"network-request-failed"),r=un().setTimeout((()=>{n(s)}),qn.get());function o(){un().clearTimeout(r),e(i)}i.ping(o).then(o,(()=>{n(s)}))}))))}
/**
 * @license
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Zn={location:"yes",resizable:"yes",statusbar:"yes",toolbar:"no"};class ts{constructor(t){this.window=t,this.associatedEvent=null}close(){if(this.window)try{this.window.close()}catch(t){}}}function is(t,i,e,n=500,s=600){const r=Math.max((window.screen.availHeight-s)/2,0).toString(),o=Math.max((window.screen.availWidth-n)/2,0).toString();let h="";const a=Object.assign(Object.assign({},Zn),{width:n.toString(),height:s.toString(),top:r,left:o}),u=gt().toLowerCase();e&&(h=Ie(u)?"_blank":e),be(u)&&(i=i||"http://localhost",a.scrollbars="yes");const c=Object.entries(a).reduce(((t,[i,e])=>`${t}${i}=${e},`),"");if(function(t=gt()){var i;return ke(t)&&!!(null===(i=window.navigator)||void 0===i?void 0:i.standalone)}(u)&&"_self"!==h)return function(t,i){const e=document.createElement("a");e.href=t,e.target=i;const n=document.createEvent("MouseEvent");n.initMouseEvent("click",!0,!0,window,1,0,0,0,0,!1,!1,!1,!1,1,null),e.dispatchEvent(n)}
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(i||"",h),new ts(null);const l=window.open(i||"",h,c);Fi(l,t,"popup-blocked");try{l.focus()}catch(t){}return new ts(l)}const es="__/auth/handler",ns="emulator/auth/handler",ss=encodeURIComponent("fac");async function rs(t,i,e,n,s,r){Fi(t.config.authDomain,t,"auth-domain-config-required"),Fi(t.config.apiKey,t,"invalid-api-key");const o={apiKey:t.config.apiKey,appName:t.name,authType:e,redirectUrl:n,v:pi,eventId:s};if(i instanceof ze){i.setDefaultLanguage(t.languageCode),o.providerId=i.providerId||"",function(t){for(const i in t)if(Object.prototype.hasOwnProperty.call(t,i))return!1;return!0}(i.getCustomParameters())||(o.customParameters=JSON.stringify(i.getCustomParameters()));for(const[t,i]of Object.entries(r||{}))o[t]=i}if(i instanceof He){const t=i.getScopes().filter((t=>""!==t));t.length>0&&(o.scopes=t.join(","))}t.tenantId&&(o.tid=t.tenantId);const h=o;for(const t of Object.keys(h))void 0===h[t]&&delete h[t];const a=await t._getAppCheckToken(),u=a?`#${ss}=${encodeURIComponent(a)}`:"";return`${function({config:t}){if(!t.emulator)return`https://${t.authDomain}/${es}`;return Wi(t,ns)}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(t)}?${Tt(h).slice(1)}${u}`}const os="webStorageSupport";const hs=class{constructor(){this.eventManagers={},this.iframes={},this.originValidationPromises={},this._redirectPersistence=rn,this._completeRedirectFn=Mn,this._overrideRedirectResult=Pn}async _openPopup(t,i,e,n){var s;Vi(null===(s=this.eventManagers[t._key()])||void 0===s?void 0:s.manager,"_initialize() not called before _openPopup()");return is(t,await rs(t,i,e,zi(),n),hn())}async _openRedirect(t,i,e,n){await this._originValidation(t);return function(t){un().location.href=t}(await rs(t,i,e,zi(),n)),new Promise((()=>{}))}_initialize(t){const i=t._key();if(this.eventManagers[i]){const{manager:t,promise:e}=this.eventManagers[i];return t?Promise.resolve(t):(Vi(e,"If manager is not set, promise should be"),e)}const e=this.initAndGetManager(t);return this.eventManagers[i]={promise:e},e.catch((()=>{delete this.eventManagers[i]})),e}async initAndGetManager(t){const i=await Qn(t),e=new $n(t);return i.register("authEvent",(i=>{Fi(null==i?void 0:i.authEvent,t,"invalid-auth-event");return{status:e.onEvent(i.authEvent)?"ACK":"ERROR"}}),gapi.iframes.CROSS_ORIGIN_IFRAMES_FILTER),this.eventManagers[t._key()]={manager:e},this.iframes[t._key()]=i,e}_isIframeWebStorageSupported(t,i){this.iframes[t._key()].send(os,{type:os},(e=>{var n;const s=null===(n=null==e?void 0:e[0])||void 0===n?void 0:n[os];void 0!==s&&i(!!s),Li(t,"internal-error")}),gapi.iframes.CROSS_ORIGIN_IFRAMES_FILTER)}_originValidation(t){const i=t._key();return this.originValidationPromises[i]||(this.originValidationPromises[i]=Fn(t)),this.originValidationPromises[i]}get _shouldInitProactively(){return Oe()||Ee()||ke()}};var as="@firebase/auth",us="1.3.0";
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class cs{constructor(t){this.auth=t,this.internalListeners=new Map}getUid(){var t;return this.assertAuthConfigured(),(null===(t=this.auth.currentUser)||void 0===t?void 0:t.uid)||null}async getToken(t){if(this.assertAuthConfigured(),await this.auth._initializationPromise,!this.auth.currentUser)return null;return{accessToken:await this.auth.currentUser.getIdToken(t)}}addAuthTokenListener(t){if(this.assertAuthConfigured(),this.internalListeners.has(t))return;const i=this.auth.onIdTokenChanged((i=>{t((null==i?void 0:i.stsTokenManager.accessToken)||null)}));this.internalListeners.set(t,i),this.updateProactiveRefresh()}removeAuthTokenListener(t){this.assertAuthConfigured();const i=this.internalListeners.get(t);i&&(this.internalListeners.delete(t),i(),this.updateProactiveRefresh())}assertAuthConfigured(){Fi(this.auth._initializationPromise,"dependent-sdk-initialized-before-auth")}updateProactiveRefresh(){this.internalListeners.size>0?this.auth._startProactiveRefresh():this.auth._stopProactiveRefresh()}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @license
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const ls=wt("authIdTokenMaxAge")||300;let fs=null;var ds;ds="Browser",ci(new Nt("auth",((t,{options:i})=>{const e=t.getProvider("app").getImmediate(),n=t.getProvider("heartbeat"),s=t.getProvider("app-check-internal"),{apiKey:r,authDomain:o}=e.options;Fi(r&&!r.includes(":"),"invalid-api-key",{appName:e.name});const h={apiKey:r,authDomain:o,clientPlatform:ds,apiHost:"identitytoolkit.googleapis.com",tokenApiHost:"securetoken.googleapis.com",apiScheme:"https",sdkClientVersion:De(ds)},a=new Pe(e,n,s,h);return function(t,i){const e=(null==i?void 0:i.persistence)||[],n=(Array.isArray(e)?e:[e]).map(pe);(null==i?void 0:i.errorMap)&&t._updateErrorMap(i.errorMap),t._initializeWithPersistence(n,null==i?void 0:i.popupRedirectResolver)}(a,i),a}),"PUBLIC").setInstantiationMode("EXPLICIT").setInstanceCreatedCallback(((t,i,e)=>{t.getProvider("auth-internal").initialize()}))),ci(new Nt("auth-internal",(t=>(t=>new cs(t))(Me(t.getProvider("auth").getImmediate()))),"PRIVATE").setInstantiationMode("EXPLICIT")),yi(as,us,function(t){switch(t){case"Node":return"node";case"ReactNative":return"rn";case"Worker":return"webworker";case"Cordova":return"cordova";default:return}}(ds)),yi(as,us,"esm2017");
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
yi("firebase","10.3.1","app");var ps,vs="undefined"!=typeof globalThis?globalThis:"undefined"!=typeof window?window:"undefined"!=typeof global?global:"undefined"!=typeof self?self:{},ws=ws||{},ys=vs||self;function gs(t){var i=typeof t;return"array"==(i="object"!=i?i:t?Array.isArray(t)?"array":i:"null")||"object"==i&&"number"==typeof t.length}function ms(t){var i=typeof t;return"object"==i&&null!=t||"function"==i}var bs="closure_uid_"+(1e9*Math.random()>>>0),Es=0;function Is(t,i,e){return t.call.apply(t.bind,arguments)}function _s(t,i,e){if(!t)throw Error();if(2<arguments.length){var n=Array.prototype.slice.call(arguments,2);return function(){var e=Array.prototype.slice.call(arguments);return Array.prototype.unshift.apply(e,n),t.apply(i,e)}}return function(){return t.apply(i,arguments)}}function Ts(t,i,e){return(Ts=Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?Is:_s).apply(null,arguments)}function As(t,i){var e=Array.prototype.slice.call(arguments,1);return function(){var i=e.slice();return i.push.apply(i,arguments),t.apply(this,i)}}function Ss(t,i){function e(){}e.prototype=i.prototype,t.$=i.prototype,t.prototype=new e,t.prototype.constructor=t,t.ac=function(t,e,n){for(var s=Array(arguments.length-2),r=2;r<arguments.length;r++)s[r-2]=arguments[r];return i.prototype[e].apply(t,s)}}function ks(){this.s=this.s,this.o=this.o}ks.prototype.s=!1,ks.prototype.sa=function(){!this.s&&(this.s=!0,this.N(),0)&&function(t){Object.prototype.hasOwnProperty.call(t,bs)&&t[bs]||(t[bs]=++Es)}(this)},ks.prototype.N=function(){if(this.o)for(;this.o.length;)this.o.shift()()};const Ns=Array.prototype.indexOf?function(t,i){return Array.prototype.indexOf.call(t,i,void 0)}:function(t,i){if("string"==typeof t)return"string"!=typeof i||1!=i.length?-1:t.indexOf(i,0);for(let e=0;e<t.length;e++)if(e in t&&t[e]===i)return e;return-1};function Os(t){const i=t.length;if(0<i){const e=Array(i);for(let n=0;n<i;n++)e[n]=t[n];return e}return[]}function Ds(t,i){for(let i=1;i<arguments.length;i++){const e=arguments[i];if(gs(e)){const i=t.length||0,n=e.length||0;t.length=i+n;for(let s=0;s<n;s++)t[i+s]=e[s]}else t.push(e)}}function Rs(t,i){this.type=t,this.g=this.target=i,this.defaultPrevented=!1}Rs.prototype.h=function(){this.defaultPrevented=!0};var Cs=function(){if(!ys.addEventListener||!Object.defineProperty)return!1;var t=!1,i=Object.defineProperty({},"passive",{get:function(){t=!0}});try{ys.addEventListener("test",(()=>{}),i),ys.removeEventListener("test",(()=>{}),i)}catch(t){}return t}();function Ps(t){return/^[\s\xa0]*$/.test(t)}function Ms(){var t=ys.navigator;return t&&(t=t.userAgent)?t:""}function $s(t){return-1!=Ms().indexOf(t)}function Ls(t){return Ls[" "](t),t}Ls[" "]=function(){};var xs=$s("Opera"),Us=$s("Trident")||$s("MSIE"),js=$s("Edge"),Fs=js||Us,Bs=$s("Gecko")&&!(-1!=Ms().toLowerCase().indexOf("webkit")&&!$s("Edge"))&&!($s("Trident")||$s("MSIE"))&&!$s("Edge"),Vs=-1!=Ms().toLowerCase().indexOf("webkit")&&!$s("Edge");function zs(){var t=ys.document;return t?t.documentMode:void 0}t:{var Hs="",Gs=function(){var t=Ms();return Bs?/rv:([^\);]+)(\)|;)/.exec(t):js?/Edge\/([\d\.]+)/.exec(t):Us?/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/.exec(t):Vs?/WebKit\/(\S+)/.exec(t):xs?/(?:Version)[ \/]?(\S+)/.exec(t):void 0}();if(Gs&&(Hs=Gs?Gs[1]:""),Us){var qs=zs();if(null!=qs&&qs>parseFloat(Hs))break t}}function Ws(t,i){if(Rs.call(this,t?t.type:""),this.relatedTarget=this.g=this.target=null,this.button=this.screenY=this.screenX=this.clientY=this.clientX=0,this.key="",this.metaKey=this.shiftKey=this.altKey=this.ctrlKey=!1,this.state=null,this.pointerId=0,this.pointerType="",this.i=null,t){var e=this.type=t.type,n=t.changedTouches&&t.changedTouches.length?t.changedTouches[0]:null;if(this.target=t.target||t.srcElement,this.g=i,i=t.relatedTarget){if(Bs){t:{try{Ls(i.nodeName);var s=!0;break t}catch(t){}s=!1}s||(i=null)}}else"mouseover"==e?i=t.fromElement:"mouseout"==e&&(i=t.toElement);this.relatedTarget=i,n?(this.clientX=void 0!==n.clientX?n.clientX:n.pageX,this.clientY=void 0!==n.clientY?n.clientY:n.pageY,this.screenX=n.screenX||0,this.screenY=n.screenY||0):(this.clientX=void 0!==t.clientX?t.clientX:t.pageX,this.clientY=void 0!==t.clientY?t.clientY:t.pageY,this.screenX=t.screenX||0,this.screenY=t.screenY||0),this.button=t.button,this.key=t.key||"",this.ctrlKey=t.ctrlKey,this.altKey=t.altKey,this.shiftKey=t.shiftKey,this.metaKey=t.metaKey,this.pointerId=t.pointerId||0,this.pointerType="string"==typeof t.pointerType?t.pointerType:Js[t.pointerType]||"",this.state=t.state,this.i=t,t.defaultPrevented&&Ws.$.h.call(this)}}ys.document&&Us&&zs(),Ss(Ws,Rs);var Js={2:"touch",3:"pen",4:"mouse"};Ws.prototype.h=function(){Ws.$.h.call(this);var t=this.i;t.preventDefault?t.preventDefault():t.returnValue=!1};var Ks="closure_listenable_"+(1e6*Math.random()|0),Xs=0;function Ys(t,i,e,n,s){this.listener=t,this.proxy=null,this.src=i,this.type=e,this.capture=!!n,this.la=s,this.key=++Xs,this.fa=this.ia=!1}function Qs(t){t.fa=!0,t.listener=null,t.proxy=null,t.src=null,t.la=null}function Zs(t,i,e){for(const n in t)i.call(e,t[n],n,t)}function tr(t){const i={};for(const e in t)i[e]=t[e];return i}const ir="constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");function er(t,i){let e,n;for(let i=1;i<arguments.length;i++){for(e in n=arguments[i],n)t[e]=n[e];for(let i=0;i<ir.length;i++)e=ir[i],Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}}function nr(t){this.src=t,this.g={},this.h=0}function sr(t,i){var e=i.type;if(e in t.g){var n,s=t.g[e],r=Ns(s,i);(n=0<=r)&&Array.prototype.splice.call(s,r,1),n&&(Qs(i),0==t.g[e].length&&(delete t.g[e],t.h--))}}function rr(t,i,e,n){for(var s=0;s<t.length;++s){var r=t[s];if(!r.fa&&r.listener==i&&r.capture==!!e&&r.la==n)return s}return-1}nr.prototype.add=function(t,i,e,n,s){var r=t.toString();(t=this.g[r])||(t=this.g[r]=[],this.h++);var o=rr(t,i,n,s);return-1<o?(i=t[o],e||(i.ia=!1)):((i=new Ys(i,this.src,r,!!n,s)).ia=e,t.push(i)),i};var or="closure_lm_"+(1e6*Math.random()|0),hr={};function ar(t,i,e,n,s){if(n&&n.once)return cr(t,i,e,n,s);if(Array.isArray(i)){for(var r=0;r<i.length;r++)ar(t,i[r],e,n,s);return null}return e=yr(e),t&&t[Ks]?t.O(i,e,ms(n)?!!n.capture:!!n,s):ur(t,i,e,!1,n,s)}function ur(t,i,e,n,s,r){if(!i)throw Error("Invalid event type");var o=ms(s)?!!s.capture:!!s,h=vr(t);if(h||(t[or]=h=new nr(t)),(e=h.add(i,e,n,o,r)).proxy)return e;if(n=function(){function t(e){return i.call(t.src,t.listener,e)}const i=pr;return t}(),e.proxy=n,n.src=t,n.listener=e,t.addEventListener)Cs||(s=o),void 0===s&&(s=!1),t.addEventListener(i.toString(),n,s);else if(t.attachEvent)t.attachEvent(dr(i.toString()),n);else{if(!t.addListener||!t.removeListener)throw Error("addEventListener and attachEvent are unavailable.");t.addListener(n)}return e}function cr(t,i,e,n,s){if(Array.isArray(i)){for(var r=0;r<i.length;r++)cr(t,i[r],e,n,s);return null}return e=yr(e),t&&t[Ks]?t.P(i,e,ms(n)?!!n.capture:!!n,s):ur(t,i,e,!0,n,s)}function lr(t,i,e,n,s){if(Array.isArray(i))for(var r=0;r<i.length;r++)lr(t,i[r],e,n,s);else n=ms(n)?!!n.capture:!!n,e=yr(e),t&&t[Ks]?(t=t.i,(i=String(i).toString())in t.g&&(-1<(e=rr(r=t.g[i],e,n,s))&&(Qs(r[e]),Array.prototype.splice.call(r,e,1),0==r.length&&(delete t.g[i],t.h--)))):t&&(t=vr(t))&&(i=t.g[i.toString()],t=-1,i&&(t=rr(i,e,n,s)),(e=-1<t?i[t]:null)&&fr(e))}function fr(t){if("number"!=typeof t&&t&&!t.fa){var i=t.src;if(i&&i[Ks])sr(i.i,t);else{var e=t.type,n=t.proxy;i.removeEventListener?i.removeEventListener(e,n,t.capture):i.detachEvent?i.detachEvent(dr(e),n):i.addListener&&i.removeListener&&i.removeListener(n),(e=vr(i))?(sr(e,t),0==e.h&&(e.src=null,i[or]=null)):Qs(t)}}}function dr(t){return t in hr?hr[t]:hr[t]="on"+t}function pr(t,i){if(t.fa)t=!0;else{i=new Ws(i,this);var e=t.listener,n=t.la||t.src;t.ia&&fr(t),t=e.call(n,i)}return t}function vr(t){return(t=t[or])instanceof nr?t:null}var wr="__closure_events_fn_"+(1e9*Math.random()>>>0);function yr(t){return"function"==typeof t?t:(t[wr]||(t[wr]=function(i){return t.handleEvent(i)}),t[wr])}function gr(){ks.call(this),this.i=new nr(this),this.S=this,this.J=null}function mr(t,i){var e,n=t.J;if(n)for(e=[];n;n=n.J)e.push(n);if(t=t.S,n=i.type||i,"string"==typeof i)i=new Rs(i,t);else if(i instanceof Rs)i.target=i.target||t;else{var s=i;er(i=new Rs(n,t),s)}if(s=!0,e)for(var r=e.length-1;0<=r;r--){var o=i.g=e[r];s=br(o,n,!0,i)&&s}if(s=br(o=i.g=t,n,!0,i)&&s,s=br(o,n,!1,i)&&s,e)for(r=0;r<e.length;r++)s=br(o=i.g=e[r],n,!1,i)&&s}function br(t,i,e,n){if(!(i=t.i.g[String(i)]))return!0;i=i.concat();for(var s=!0,r=0;r<i.length;++r){var o=i[r];if(o&&!o.fa&&o.capture==e){var h=o.listener,a=o.la||o.src;o.ia&&sr(t.i,o),s=!1!==h.call(a,n)&&s}}return s&&!n.defaultPrevented}Ss(gr,ks),gr.prototype[Ks]=!0,gr.prototype.removeEventListener=function(t,i,e,n){lr(this,t,i,e,n)},gr.prototype.N=function(){if(gr.$.N.call(this),this.i){var t,i=this.i;for(t in i.g){for(var e=i.g[t],n=0;n<e.length;n++)Qs(e[n]);delete i.g[t],i.h--}}this.J=null},gr.prototype.O=function(t,i,e,n){return this.i.add(String(t),i,!1,e,n)},gr.prototype.P=function(t,i,e,n){return this.i.add(String(t),i,!0,e,n)};var Er=ys.JSON.stringify;function Ir(){var t=Or;let i=null;return t.g&&(i=t.g,t.g=t.g.next,t.g||(t.h=null),i.next=null),i}var _r=new class{constructor(t,i){this.i=t,this.j=i,this.h=0,this.g=null}get(){let t;return 0<this.h?(this.h--,t=this.g,this.g=t.next,t.next=null):t=this.i(),t}}((()=>new Tr),(t=>t.reset()));class Tr{constructor(){this.next=this.g=this.h=null}set(t,i){this.h=t,this.g=i,this.next=null}reset(){this.next=this.g=this.h=null}}function Ar(t){var i=1;t=t.split(":");const e=[];for(;0<i&&t.length;)e.push(t.shift()),i--;return t.length&&e.push(t.join(":")),e}function Sr(t){ys.setTimeout((()=>{throw t}),0)}let kr,Nr=!1,Or=new class{constructor(){this.h=this.g=null}add(t,i){const e=_r.get();e.set(t,i),this.h?this.h.next=e:this.g=e,this.h=e}},Dr=()=>{const t=ys.Promise.resolve(void 0);kr=()=>{t.then(Rr)}};var Rr=()=>{for(var t;t=Ir();){try{t.h.call(t.g)}catch(t){Sr(t)}var i=_r;i.j(t),100>i.h&&(i.h++,t.next=i.g,i.g=t)}Nr=!1};function Cr(t,i){gr.call(this),this.h=t||1,this.g=i||ys,this.j=Ts(this.qb,this),this.l=Date.now()}function Pr(t){t.ga=!1,t.T&&(t.g.clearTimeout(t.T),t.T=null)}function Mr(t,i,e){if("function"==typeof t)e&&(t=Ts(t,e));else{if(!t||"function"!=typeof t.handleEvent)throw Error("Invalid listener argument");t=Ts(t.handleEvent,t)}return 2147483647<Number(i)?-1:ys.setTimeout(t,i||0)}function $r(t){t.g=Mr((()=>{t.g=null,t.i&&(t.i=!1,$r(t))}),t.j);const i=t.h;t.h=null,t.m.apply(null,i)}Ss(Cr,gr),(ps=Cr.prototype).ga=!1,ps.T=null,ps.qb=function(){if(this.ga){var t=Date.now()-this.l;0<t&&t<.8*this.h?this.T=this.g.setTimeout(this.j,this.h-t):(this.T&&(this.g.clearTimeout(this.T),this.T=null),mr(this,"tick"),this.ga&&(Pr(this),this.start()))}},ps.start=function(){this.ga=!0,this.T||(this.T=this.g.setTimeout(this.j,this.h),this.l=Date.now())},ps.N=function(){Cr.$.N.call(this),Pr(this),delete this.g};class Lr extends ks{constructor(t,i){super(),this.m=t,this.j=i,this.h=null,this.i=!1,this.g=null}l(t){this.h=arguments,this.g?this.i=!0:$r(this)}N(){super.N(),this.g&&(ys.clearTimeout(this.g),this.g=null,this.i=!1,this.h=null)}}function xr(t){ks.call(this),this.h=t,this.g={}}Ss(xr,ks);var Ur=[];function jr(t,i,e,n){Array.isArray(e)||(e&&(Ur[0]=e.toString()),e=Ur);for(var s=0;s<e.length;s++){var r=ar(i,e[s],n||t.handleEvent,!1,t.h||t);if(!r)break;t.g[r.key]=r}}function Fr(t){Zs(t.g,(function(t,i){this.g.hasOwnProperty(i)&&fr(t)}),t),t.g={}}function Br(){this.g=!0}function Vr(t,i,e,n){t.info((function(){return"XMLHTTP TEXT ("+i+"): "+function(t,i){if(!t.g)return i;if(!i)return null;try{var e=JSON.parse(i);if(e)for(t=0;t<e.length;t++)if(Array.isArray(e[t])){var n=e[t];if(!(2>n.length)){var s=n[1];if(Array.isArray(s)&&!(1>s.length)){var r=s[0];if("noop"!=r&&"stop"!=r&&"close"!=r)for(var o=1;o<s.length;o++)s[o]=""}}}return Er(e)}catch(t){return i}}(t,e)+(n?" "+n:"")}))}xr.prototype.N=function(){xr.$.N.call(this),Fr(this)},xr.prototype.handleEvent=function(){throw Error("EventHandler.handleEvent not implemented")},Br.prototype.Ea=function(){this.g=!1},Br.prototype.info=function(){};var zr={},Hr=null;function Gr(){return Hr=Hr||new gr}function qr(t){Rs.call(this,zr.Ta,t)}function Wr(t){const i=Gr();mr(i,new qr(i))}function Jr(t,i){Rs.call(this,zr.STAT_EVENT,t),this.stat=i}function Kr(t){const i=Gr();mr(i,new Jr(i,t))}function Xr(t,i){Rs.call(this,zr.Ua,t),this.size=i}function Yr(t,i){if("function"!=typeof t)throw Error("Fn must not be null and must be a function");return ys.setTimeout((function(){t()}),i)}zr.Ta="serverreachability",Ss(qr,Rs),zr.STAT_EVENT="statevent",Ss(Jr,Rs),zr.Ua="timingevent",Ss(Xr,Rs);var Qr={NO_ERROR:0,rb:1,Eb:2,Db:3,yb:4,Cb:5,Fb:6,Qa:7,TIMEOUT:8,Ib:9};function Zr(){}function to(t){return t.h||(t.h=t.i())}Zr.prototype.h=null;var io,eo={OPEN:"a",vb:"b",Ra:"c",Hb:"d"};function no(){Rs.call(this,"d")}function so(){Rs.call(this,"c")}function ro(){}function oo(t,i,e,n){this.l=t,this.j=i,this.m=e,this.W=n||1,this.U=new xr(this),this.P=ao,t=Fs?125:void 0,this.V=new Cr(t),this.I=null,this.i=!1,this.s=this.A=this.v=this.L=this.G=this.Y=this.B=null,this.F=[],this.g=null,this.C=0,this.o=this.u=null,this.ca=-1,this.J=!1,this.O=0,this.M=null,this.ba=this.K=this.aa=this.S=!1,this.h=new ho}function ho(){this.i=null,this.g="",this.h=!1}Ss(no,Rs),Ss(so,Rs),Ss(ro,Zr),ro.prototype.g=function(){return new XMLHttpRequest},ro.prototype.i=function(){return{}},io=new ro;var ao=45e3,uo={},co={};function lo(t,i,e){t.L=1,t.v=Ro(So(i)),t.s=e,t.S=!0,fo(t,null)}function fo(t,i){t.G=Date.now(),yo(t),t.A=So(t.v);var e=t.A,n=t.W;Array.isArray(n)||(n=[String(n)]),Ho(e.i,"t",n),t.C=0,e=t.l.J,t.h=new ho,t.g=Hh(t.l,e?i:null,!t.s),0<t.O&&(t.M=new Lr(Ts(t.Pa,t,t.g),t.O)),jr(t.U,t.g,"readystatechange",t.nb),i=t.I?tr(t.I):{},t.s?(t.u||(t.u="POST"),i["Content-Type"]="application/x-www-form-urlencoded",t.g.ha(t.A,t.u,t.s,i)):(t.u="GET",t.g.ha(t.A,t.u,null,i)),Wr(),function(t,i,e,n,s,r){t.info((function(){if(t.g)if(r)for(var o="",h=r.split("&"),a=0;a<h.length;a++){var u=h[a].split("=");if(1<u.length){var c=u[0];u=u[1];var l=c.split("_");o=2<=l.length&&"type"==l[1]?o+(c+"=")+u+"&":o+(c+"=redacted&")}}else o=null;else o=r;return"XMLHTTP REQ ("+n+") [attempt "+s+"]: "+i+"\n"+e+"\n"+o}))}(t.j,t.u,t.A,t.m,t.W,t.s)}function po(t){return!!t.g&&("GET"==t.u&&2!=t.L&&t.l.Ha)}function vo(t,i,e){let n,s=!0;for(;!t.J&&t.C<e.length;){if(n=wo(t,e),n==co){4==i&&(t.o=4,Kr(14),s=!1),Vr(t.j,t.m,null,"[Incomplete Response]");break}if(n==uo){t.o=4,Kr(15),Vr(t.j,t.m,e,"[Invalid Chunk]"),s=!1;break}Vr(t.j,t.m,n,null),Io(t,n)}po(t)&&n!=co&&n!=uo&&(t.h.g="",t.C=0),4!=i||0!=e.length||t.h.h||(t.o=1,Kr(16),s=!1),t.i=t.i&&s,s?0<e.length&&!t.ba&&(t.ba=!0,(i=t.l).g==t&&i.ca&&!i.M&&(i.l.info("Great, no buffering proxy detected. Bytes received: "+e.length),Lh(i),i.M=!0,Kr(11))):(Vr(t.j,t.m,e,"[Invalid Chunked Response]"),Eo(t),bo(t))}function wo(t,i){var e=t.C,n=i.indexOf("\n",e);return-1==n?co:(e=Number(i.substring(e,n)),isNaN(e)?uo:(n+=1)+e>i.length?co:(i=i.slice(n,n+e),t.C=n+e,i))}function yo(t){t.Y=Date.now()+t.P,go(t,t.P)}function go(t,i){if(null!=t.B)throw Error("WatchDog timer not null");t.B=Yr(Ts(t.lb,t),i)}function mo(t){t.B&&(ys.clearTimeout(t.B),t.B=null)}function bo(t){0==t.l.H||t.J||jh(t.l,t)}function Eo(t){mo(t);var i=t.M;i&&"function"==typeof i.sa&&i.sa(),t.M=null,Pr(t.V),Fr(t.U),t.g&&(i=t.g,t.g=null,i.abort(),i.sa())}function Io(t,i){try{var e=t.l;if(0!=e.H&&(e.g==t||Yo(e.i,t)))if(!t.K&&Yo(e.i,t)&&3==e.H){try{var n=e.Ja.g.parse(i)}catch(t){n=null}if(Array.isArray(n)&&3==n.length){var s=n;if(0==s[0]){t:if(!e.u){if(e.g){if(!(e.g.G+3e3<t.G))break t;Uh(e),Nh(e)}$h(e),Kr(18)}}else e.Fa=s[1],0<e.Fa-e.V&&37500>s[2]&&e.G&&0==e.A&&!e.v&&(e.v=Yr(Ts(e.ib,e),6e3));if(1>=Xo(e.i)&&e.oa){try{e.oa()}catch(t){}e.oa=void 0}}else Bh(e,11)}else if((t.K||e.g==t)&&Uh(e),!Ps(i))for(s=e.Ja.g.parse(i),i=0;i<s.length;i++){let u=s[i];if(e.V=u[0],u=u[1],2==e.H)if("c"==u[0]){e.K=u[1],e.pa=u[2];const i=u[3];null!=i&&(e.ra=i,e.l.info("VER="+e.ra));const s=u[4];null!=s&&(e.Ga=s,e.l.info("SVER="+e.Ga));const c=u[5];null!=c&&"number"==typeof c&&0<c&&(n=1.5*c,e.L=n,e.l.info("backChannelRequestTimeoutMs_="+n)),n=e;const l=t.g;if(l){const t=l.g?l.g.getResponseHeader("X-Client-Wire-Protocol"):null;if(t){var r=n.i;r.g||-1==t.indexOf("spdy")&&-1==t.indexOf("quic")&&-1==t.indexOf("h2")||(r.j=r.l,r.g=new Set,r.h&&(Qo(r,r.h),r.h=null))}if(n.F){const t=l.g?l.g.getResponseHeader("X-HTTP-Session-Id"):null;t&&(n.Da=t,Do(n.I,n.F,t))}}e.H=3,e.h&&e.h.Ba(),e.ca&&(e.S=Date.now()-t.G,e.l.info("Handshake RTT: "+e.S+"ms"));var o=t;if((n=e).wa=zh(n,n.J?n.pa:null,n.Y),o.K){Zo(n.i,o);var h=o,a=n.L;a&&h.setTimeout(a),h.B&&(mo(h),yo(h)),n.g=o}else Mh(n);0<e.j.length&&Dh(e)}else"stop"!=u[0]&&"close"!=u[0]||Bh(e,7);else 3==e.H&&("stop"==u[0]||"close"==u[0]?"stop"==u[0]?Bh(e,7):kh(e):"noop"!=u[0]&&e.h&&e.h.Aa(u),e.A=0)}Wr()}catch(t){}}function _o(t,i){if(t.forEach&&"function"==typeof t.forEach)t.forEach(i,void 0);else if(gs(t)||"string"==typeof t)Array.prototype.forEach.call(t,i,void 0);else for(var e=function(t){if(t.ta&&"function"==typeof t.ta)return t.ta();if(!t.Z||"function"!=typeof t.Z){if("undefined"!=typeof Map&&t instanceof Map)return Array.from(t.keys());if(!("undefined"!=typeof Set&&t instanceof Set)){if(gs(t)||"string"==typeof t){var i=[];t=t.length;for(var e=0;e<t;e++)i.push(e);return i}i=[],e=0;for(const n in t)i[e++]=n;return i}}}(t),n=function(t){if(t.Z&&"function"==typeof t.Z)return t.Z();if("undefined"!=typeof Map&&t instanceof Map||"undefined"!=typeof Set&&t instanceof Set)return Array.from(t.values());if("string"==typeof t)return t.split("");if(gs(t)){for(var i=[],e=t.length,n=0;n<e;n++)i.push(t[n]);return i}for(n in i=[],e=0,t)i[e++]=t[n];return i}(t),s=n.length,r=0;r<s;r++)i.call(void 0,n[r],e&&e[r],t)}(ps=oo.prototype).setTimeout=function(t){this.P=t},ps.nb=function(t){t=t.target;const i=this.M;i&&3==Eh(t)?i.l():this.Pa(t)},ps.Pa=function(t){try{if(t==this.g)t:{const c=Eh(this.g);var i=this.g.Ia();this.g.da();if(!(3>c)&&(3!=c||Fs||this.g&&(this.h.h||this.g.ja()||Ih(this.g)))){this.J||4!=c||7==i||Wr(),mo(this);var e=this.g.da();this.ca=e;i:if(po(this)){var n=Ih(this.g);t="";var s=n.length,r=4==Eh(this.g);if(!this.h.i){if("undefined"==typeof TextDecoder){Eo(this),bo(this);var o="";break i}this.h.i=new ys.TextDecoder}for(i=0;i<s;i++)this.h.h=!0,t+=this.h.i.decode(n[i],{stream:r&&i==s-1});n.splice(0,s),this.h.g+=t,this.C=0,o=this.h.g}else o=this.g.ja();if(this.i=200==e,function(t,i,e,n,s,r,o){t.info((function(){return"XMLHTTP RESP ("+n+") [ attempt "+s+"]: "+i+"\n"+e+"\n"+r+" "+o}))}(this.j,this.u,this.A,this.m,this.W,c,e),this.i){if(this.aa&&!this.K){i:{if(this.g){var h,a=this.g;if((h=a.g?a.g.getResponseHeader("X-HTTP-Initial-Response"):null)&&!Ps(h)){var u=h;break i}}u=null}if(!(e=u)){this.i=!1,this.o=3,Kr(12),Eo(this),bo(this);break t}Vr(this.j,this.m,e,"Initial handshake response via X-HTTP-Initial-Response"),this.K=!0,Io(this,e)}this.S?(vo(this,c,o),Fs&&this.i&&3==c&&(jr(this.U,this.V,"tick",this.mb),this.V.start())):(Vr(this.j,this.m,o,null),Io(this,o)),4==c&&Eo(this),this.i&&!this.J&&(4==c?jh(this.l,this):(this.i=!1,yo(this)))}else(function(t){const i={};t=(t.g&&2<=Eh(t)&&t.g.getAllResponseHeaders()||"").split("\r\n");for(let n=0;n<t.length;n++){if(Ps(t[n]))continue;var e=Ar(t[n]);const s=e[0];if("string"!=typeof(e=e[1]))continue;e=e.trim();const r=i[s]||[];i[s]=r,r.push(e)}!function(t,i){for(const e in t)i.call(void 0,t[e],e,t)}(i,(function(t){return t.join(", ")}))})(this.g),400==e&&0<o.indexOf("Unknown SID")?(this.o=3,Kr(12)):(this.o=0,Kr(13)),Eo(this),bo(this)}}}catch(t){}},ps.mb=function(){if(this.g){var t=Eh(this.g),i=this.g.ja();this.C<i.length&&(mo(this),vo(this,t,i),this.i&&4!=t&&yo(this))}},ps.cancel=function(){this.J=!0,Eo(this)},ps.lb=function(){this.B=null;const t=Date.now();0<=t-this.Y?(function(t,i){t.info((function(){return"TIMEOUT: "+i}))}(this.j,this.A),2!=this.L&&(Wr(),Kr(17)),Eo(this),this.o=2,bo(this)):go(this,this.Y-t)};var To=RegExp("^(?:([^:/?#.]+):)?(?://(?:([^\\\\/?#]*)@)?([^\\\\/?#]*?)(?::([0-9]+))?(?=[\\\\/?#]|$))?([^?#]+)?(?:\\?([^#]*))?(?:#([\\s\\S]*))?$");function Ao(t){if(this.g=this.s=this.j="",this.m=null,this.o=this.l="",this.h=!1,t instanceof Ao){this.h=t.h,ko(this,t.j),this.s=t.s,this.g=t.g,No(this,t.m),this.l=t.l;var i=t.i,e=new Fo;e.i=i.i,i.g&&(e.g=new Map(i.g),e.h=i.h),Oo(this,e),this.o=t.o}else t&&(i=String(t).match(To))?(this.h=!1,ko(this,i[1]||"",!0),this.s=Co(i[2]||""),this.g=Co(i[3]||"",!0),No(this,i[4]),this.l=Co(i[5]||"",!0),Oo(this,i[6]||"",!0),this.o=Co(i[7]||"")):(this.h=!1,this.i=new Fo(null,this.h))}function So(t){return new Ao(t)}function ko(t,i,e){t.j=e?Co(i,!0):i,t.j&&(t.j=t.j.replace(/:$/,""))}function No(t,i){if(i){if(i=Number(i),isNaN(i)||0>i)throw Error("Bad port number "+i);t.m=i}else t.m=null}function Oo(t,i,e){i instanceof Fo?(t.i=i,function(t,i){i&&!t.j&&(Bo(t),t.i=null,t.g.forEach((function(t,i){var e=i.toLowerCase();i!=e&&(Vo(this,i),Ho(this,e,t))}),t)),t.j=i}(t.i,t.h)):(e||(i=Po(i,Uo)),t.i=new Fo(i,t.h))}function Do(t,i,e){t.i.set(i,e)}function Ro(t){return Do(t,"zx",Math.floor(2147483648*Math.random()).toString(36)+Math.abs(Math.floor(2147483648*Math.random())^Date.now()).toString(36)),t}function Co(t,i){return t?i?decodeURI(t.replace(/%25/g,"%2525")):decodeURIComponent(t):""}function Po(t,i,e){return"string"==typeof t?(t=encodeURI(t).replace(i,Mo),e&&(t=t.replace(/%25([0-9a-fA-F]{2})/g,"%$1")),t):null}function Mo(t){return"%"+((t=t.charCodeAt(0))>>4&15).toString(16)+(15&t).toString(16)}Ao.prototype.toString=function(){var t=[],i=this.j;i&&t.push(Po(i,$o,!0),":");var e=this.g;return(e||"file"==i)&&(t.push("//"),(i=this.s)&&t.push(Po(i,$o,!0),"@"),t.push(encodeURIComponent(String(e)).replace(/%25([0-9a-fA-F]{2})/g,"%$1")),null!=(e=this.m)&&t.push(":",String(e))),(e=this.l)&&(this.g&&"/"!=e.charAt(0)&&t.push("/"),t.push(Po(e,"/"==e.charAt(0)?xo:Lo,!0))),(e=this.i.toString())&&t.push("?",e),(e=this.o)&&t.push("#",Po(e,jo)),t.join("")};var $o=/[#\/\?@]/g,Lo=/[#\?:]/g,xo=/[#\?]/g,Uo=/[#\?@]/g,jo=/#/g;function Fo(t,i){this.h=this.g=null,this.i=t||null,this.j=!!i}function Bo(t){t.g||(t.g=new Map,t.h=0,t.i&&function(t,i){if(t){t=t.split("&");for(var e=0;e<t.length;e++){var n=t[e].indexOf("="),s=null;if(0<=n){var r=t[e].substring(0,n);s=t[e].substring(n+1)}else r=t[e];i(r,s?decodeURIComponent(s.replace(/\+/g," ")):"")}}}(t.i,(function(i,e){t.add(decodeURIComponent(i.replace(/\+/g," ")),e)})))}function Vo(t,i){Bo(t),i=Go(t,i),t.g.has(i)&&(t.i=null,t.h-=t.g.get(i).length,t.g.delete(i))}function zo(t,i){return Bo(t),i=Go(t,i),t.g.has(i)}function Ho(t,i,e){Vo(t,i),0<e.length&&(t.i=null,t.g.set(Go(t,i),Os(e)),t.h+=e.length)}function Go(t,i){return i=String(i),t.j&&(i=i.toLowerCase()),i}(ps=Fo.prototype).add=function(t,i){Bo(this),this.i=null,t=Go(this,t);var e=this.g.get(t);return e||this.g.set(t,e=[]),e.push(i),this.h+=1,this},ps.forEach=function(t,i){Bo(this),this.g.forEach((function(e,n){e.forEach((function(e){t.call(i,e,n,this)}),this)}),this)},ps.ta=function(){Bo(this);const t=Array.from(this.g.values()),i=Array.from(this.g.keys()),e=[];for(let n=0;n<i.length;n++){const s=t[n];for(let t=0;t<s.length;t++)e.push(i[n])}return e},ps.Z=function(t){Bo(this);let i=[];if("string"==typeof t)zo(this,t)&&(i=i.concat(this.g.get(Go(this,t))));else{t=Array.from(this.g.values());for(let e=0;e<t.length;e++)i=i.concat(t[e])}return i},ps.set=function(t,i){return Bo(this),this.i=null,zo(this,t=Go(this,t))&&(this.h-=this.g.get(t).length),this.g.set(t,[i]),this.h+=1,this},ps.get=function(t,i){return t&&0<(t=this.Z(t)).length?String(t[0]):i},ps.toString=function(){if(this.i)return this.i;if(!this.g)return"";const t=[],i=Array.from(this.g.keys());for(var e=0;e<i.length;e++){var n=i[e];const r=encodeURIComponent(String(n)),o=this.Z(n);for(n=0;n<o.length;n++){var s=r;""!==o[n]&&(s+="="+encodeURIComponent(String(o[n]))),t.push(s)}}return this.i=t.join("&")};var qo=class{constructor(t,i){this.g=t,this.map=i}};function Wo(t){this.l=t||Jo,ys.PerformanceNavigationTiming?t=0<(t=ys.performance.getEntriesByType("navigation")).length&&("hq"==t[0].nextHopProtocol||"h2"==t[0].nextHopProtocol):t=!!(ys.g&&ys.g.Ka&&ys.g.Ka()&&ys.g.Ka().ec),this.j=t?this.l:1,this.g=null,1<this.j&&(this.g=new Set),this.h=null,this.i=[]}var Jo=10;function Ko(t){return!!t.h||!!t.g&&t.g.size>=t.j}function Xo(t){return t.h?1:t.g?t.g.size:0}function Yo(t,i){return t.h?t.h==i:!!t.g&&t.g.has(i)}function Qo(t,i){t.g?t.g.add(i):t.h=i}function Zo(t,i){t.h&&t.h==i?t.h=null:t.g&&t.g.has(i)&&t.g.delete(i)}function th(t){if(null!=t.h)return t.i.concat(t.h.F);if(null!=t.g&&0!==t.g.size){let i=t.i;for(const e of t.g.values())i=i.concat(e.F);return i}return Os(t.i)}Wo.prototype.cancel=function(){if(this.i=th(this),this.h)this.h.cancel(),this.h=null;else if(this.g&&0!==this.g.size){for(const t of this.g.values())t.cancel();this.g.clear()}};var ih=class{stringify(t){return ys.JSON.stringify(t,void 0)}parse(t){return ys.JSON.parse(t,void 0)}};function eh(){this.g=new ih}function nh(t,i,e){const n=e||"";try{_o(t,(function(t,e){let s=t;ms(t)&&(s=Er(t)),i.push(n+e+"="+encodeURIComponent(s))}))}catch(t){throw i.push(n+"type="+encodeURIComponent("_badmap")),t}}function sh(t,i,e,n,s){try{i.onload=null,i.onerror=null,i.onabort=null,i.ontimeout=null,s(n)}catch(t){}}function rh(t){this.l=t.fc||null,this.j=t.ob||!1}function oh(t,i){gr.call(this),this.F=t,this.u=i,this.m=void 0,this.readyState=hh,this.status=0,this.responseType=this.responseText=this.response=this.statusText="",this.onreadystatechange=null,this.v=new Headers,this.h=null,this.C="GET",this.B="",this.g=!1,this.A=this.j=this.l=null}Ss(rh,Zr),rh.prototype.g=function(){return new oh(this.l,this.j)},rh.prototype.i=function(t){return function(){return t}}({}),Ss(oh,gr);var hh=0;function ah(t){t.j.read().then(t.Xa.bind(t)).catch(t.ka.bind(t))}function uh(t){t.readyState=4,t.l=null,t.j=null,t.A=null,ch(t)}function ch(t){t.onreadystatechange&&t.onreadystatechange.call(t)}(ps=oh.prototype).open=function(t,i){if(this.readyState!=hh)throw this.abort(),Error("Error reopening a connection");this.C=t,this.B=i,this.readyState=1,ch(this)},ps.send=function(t){if(1!=this.readyState)throw this.abort(),Error("need to call open() first. ");this.g=!0;const i={headers:this.v,method:this.C,credentials:this.m,cache:void 0};t&&(i.body=t),(this.F||ys).fetch(new Request(this.B,i)).then(this.$a.bind(this),this.ka.bind(this))},ps.abort=function(){this.response=this.responseText="",this.v=new Headers,this.status=0,this.j&&this.j.cancel("Request was aborted.").catch((()=>{})),1<=this.readyState&&this.g&&4!=this.readyState&&(this.g=!1,uh(this)),this.readyState=hh},ps.$a=function(t){if(this.g&&(this.l=t,this.h||(this.status=this.l.status,this.statusText=this.l.statusText,this.h=t.headers,this.readyState=2,ch(this)),this.g&&(this.readyState=3,ch(this),this.g)))if("arraybuffer"===this.responseType)t.arrayBuffer().then(this.Ya.bind(this),this.ka.bind(this));else if(void 0!==ys.ReadableStream&&"body"in t){if(this.j=t.body.getReader(),this.u){if(this.responseType)throw Error('responseType must be empty for "streamBinaryChunks" mode responses.');this.response=[]}else this.response=this.responseText="",this.A=new TextDecoder;ah(this)}else t.text().then(this.Za.bind(this),this.ka.bind(this))},ps.Xa=function(t){if(this.g){if(this.u&&t.value)this.response.push(t.value);else if(!this.u){var i=t.value?t.value:new Uint8Array(0);(i=this.A.decode(i,{stream:!t.done}))&&(this.response=this.responseText+=i)}t.done?uh(this):ch(this),3==this.readyState&&ah(this)}},ps.Za=function(t){this.g&&(this.response=this.responseText=t,uh(this))},ps.Ya=function(t){this.g&&(this.response=t,uh(this))},ps.ka=function(){this.g&&uh(this)},ps.setRequestHeader=function(t,i){this.v.append(t,i)},ps.getResponseHeader=function(t){return this.h&&this.h.get(t.toLowerCase())||""},ps.getAllResponseHeaders=function(){if(!this.h)return"";const t=[],i=this.h.entries();for(var e=i.next();!e.done;)e=e.value,t.push(e[0]+": "+e[1]),e=i.next();return t.join("\r\n")},Object.defineProperty(oh.prototype,"withCredentials",{get:function(){return"include"===this.m},set:function(t){this.m=t?"include":"same-origin"}});var lh=ys.JSON.parse;function fh(t){gr.call(this),this.headers=new Map,this.u=t||null,this.h=!1,this.C=this.g=null,this.I="",this.m=0,this.j="",this.l=this.G=this.v=this.F=!1,this.B=0,this.A=null,this.K=dh,this.L=this.M=!1}Ss(fh,gr);var dh="",ph=/^https?$/i,vh=["POST","PUT"];function wh(t,i){t.h=!1,t.g&&(t.l=!0,t.g.abort(),t.l=!1),t.j=i,t.m=5,yh(t),mh(t)}function yh(t){t.F||(t.F=!0,mr(t,"complete"),mr(t,"error"))}function gh(t){if(t.h&&void 0!==ws&&(!t.C[1]||4!=Eh(t)||2!=t.da()))if(t.v&&4==Eh(t))Mr(t.La,0,t);else if(mr(t,"readystatechange"),4==Eh(t)){t.h=!1;try{const o=t.da();t:switch(o){case 200:case 201:case 202:case 204:case 206:case 304:case 1223:var i=!0;break t;default:i=!1}var e;if(!(e=i)){var n;if(n=0===o){var s=String(t.I).match(To)[1]||null;!s&&ys.self&&ys.self.location&&(s=ys.self.location.protocol.slice(0,-1)),n=!ph.test(s?s.toLowerCase():"")}e=n}if(e)mr(t,"complete"),mr(t,"success");else{t.m=6;try{var r=2<Eh(t)?t.g.statusText:""}catch(t){r=""}t.j=r+" ["+t.da()+"]",yh(t)}}finally{mh(t)}}}function mh(t,i){if(t.g){bh(t);const e=t.g,n=t.C[0]?()=>{}:null;t.g=null,t.C=null,i||mr(t,"ready");try{e.onreadystatechange=n}catch(t){}}}function bh(t){t.g&&t.L&&(t.g.ontimeout=null),t.A&&(ys.clearTimeout(t.A),t.A=null)}function Eh(t){return t.g?t.g.readyState:0}function Ih(t){try{if(!t.g)return null;if("response"in t.g)return t.g.response;switch(t.K){case dh:case"text":return t.g.responseText;case"arraybuffer":if("mozResponseArrayBuffer"in t.g)return t.g.mozResponseArrayBuffer}return null}catch(t){return null}}function _h(t){let i="";return Zs(t,(function(t,e){i+=e,i+=":",i+=t,i+="\r\n"})),i}function Th(t,i,e){t:{for(n in e){var n=!1;break t}n=!0}n||(e=_h(e),"string"==typeof t?null!=e&&encodeURIComponent(String(e)):Do(t,i,e))}function Ah(t,i,e){return e&&e.internalChannelParams&&e.internalChannelParams[t]||i}function Sh(t){this.Ga=0,this.j=[],this.l=new Br,this.pa=this.wa=this.I=this.Y=this.g=this.Da=this.F=this.na=this.o=this.U=this.s=null,this.fb=this.W=0,this.cb=Ah("failFast",!1,t),this.G=this.v=this.u=this.m=this.h=null,this.aa=!0,this.Fa=this.V=-1,this.ba=this.A=this.C=0,this.ab=Ah("baseRetryDelayMs",5e3,t),this.hb=Ah("retryDelaySeedMs",1e4,t),this.eb=Ah("forwardChannelMaxRetries",2,t),this.xa=Ah("forwardChannelRequestTimeoutMs",2e4,t),this.va=t&&t.xmlHttpFactory||void 0,this.Ha=t&&t.dc||!1,this.L=void 0,this.J=t&&t.supportsCrossDomainXhr||!1,this.K="",this.i=new Wo(t&&t.concurrentRequestLimit),this.Ja=new eh,this.P=t&&t.fastHandshake||!1,this.O=t&&t.encodeInitMessageHeaders||!1,this.P&&this.O&&(this.O=!1),this.bb=t&&t.bc||!1,t&&t.Ea&&this.l.Ea(),t&&t.forceLongPolling&&(this.aa=!1),this.ca=!this.P&&this.aa&&t&&t.detectBufferingProxy||!1,this.qa=void 0,t&&t.longPollingTimeout&&0<t.longPollingTimeout&&(this.qa=t.longPollingTimeout),this.oa=void 0,this.S=0,this.M=!1,this.ma=this.B=null}function kh(t){if(Oh(t),3==t.H){var i=t.W++,e=So(t.I);if(Do(e,"SID",t.K),Do(e,"RID",i),Do(e,"TYPE","terminate"),Ch(t,e),(i=new oo(t,t.l,i)).L=2,i.v=Ro(So(e)),e=!1,ys.navigator&&ys.navigator.sendBeacon)try{e=ys.navigator.sendBeacon(i.v.toString(),"")}catch(t){}!e&&ys.Image&&((new Image).src=i.v,e=!0),e||(i.g=Hh(i.l,null),i.g.ha(i.v)),i.G=Date.now(),yo(i)}Vh(t)}function Nh(t){t.g&&(Lh(t),t.g.cancel(),t.g=null)}function Oh(t){Nh(t),t.u&&(ys.clearTimeout(t.u),t.u=null),Uh(t),t.i.cancel(),t.m&&("number"==typeof t.m&&ys.clearTimeout(t.m),t.m=null)}function Dh(t){if(!Ko(t.i)&&!t.m){t.m=!0;var i=t.Na;kr||Dr(),Nr||(kr(),Nr=!0),Or.add(i,t),t.C=0}}function Rh(t,i){var e;e=i?i.m:t.W++;const n=So(t.I);Do(n,"SID",t.K),Do(n,"RID",e),Do(n,"AID",t.V),Ch(t,n),t.o&&t.s&&Th(n,t.o,t.s),e=new oo(t,t.l,e,t.C+1),null===t.o&&(e.I=t.s),i&&(t.j=i.F.concat(t.j)),i=Ph(t,e,1e3),e.setTimeout(Math.round(.5*t.xa)+Math.round(.5*t.xa*Math.random())),Qo(t.i,e),lo(e,n,i)}function Ch(t,i){t.na&&Zs(t.na,(function(t,e){Do(i,e,t)})),t.h&&_o({},(function(t,e){Do(i,e,t)}))}function Ph(t,i,e){e=Math.min(t.j.length,e);var n=t.h?Ts(t.h.Va,t.h,t):null;t:{var s=t.j;let i=-1;for(;;){const t=["count="+e];-1==i?0<e?(i=s[0].g,t.push("ofs="+i)):i=0:t.push("ofs="+i);let r=!0;for(let o=0;o<e;o++){let e=s[o].g;const h=s[o].map;if(e-=i,0>e)i=Math.max(0,s[o].g-100),r=!1;else try{nh(h,t,"req"+e+"_")}catch(t){n&&n(h)}}if(r){n=t.join("&");break t}}}return t=t.j.splice(0,e),i.F=t,n}function Mh(t){if(!t.g&&!t.u){t.ba=1;var i=t.Ma;kr||Dr(),Nr||(kr(),Nr=!0),Or.add(i,t),t.A=0}}function $h(t){return!(t.g||t.u||3<=t.A)&&(t.ba++,t.u=Yr(Ts(t.Ma,t),Fh(t,t.A)),t.A++,!0)}function Lh(t){null!=t.B&&(ys.clearTimeout(t.B),t.B=null)}function xh(t){t.g=new oo(t,t.l,"rpc",t.ba),null===t.o&&(t.g.I=t.s),t.g.O=0;var i=So(t.wa);Do(i,"RID","rpc"),Do(i,"SID",t.K),Do(i,"AID",t.V),Do(i,"CI",t.G?"0":"1"),!t.G&&t.qa&&Do(i,"TO",t.qa),Do(i,"TYPE","xmlhttp"),Ch(t,i),t.o&&t.s&&Th(i,t.o,t.s),t.L&&t.g.setTimeout(t.L);var e=t.g;t=t.pa,e.L=1,e.v=Ro(So(i)),e.s=null,e.S=!0,fo(e,t)}function Uh(t){null!=t.v&&(ys.clearTimeout(t.v),t.v=null)}function jh(t,i){var e=null;if(t.g==i){Uh(t),Lh(t),t.g=null;var n=2}else{if(!Yo(t.i,i))return;e=i.F,Zo(t.i,i),n=1}if(0!=t.H)if(i.i)if(1==n){e=i.s?i.s.length:0,i=Date.now()-i.G;var s=t.C;mr(n=Gr(),new Xr(n,e)),Dh(t)}else Mh(t);else if(3==(s=i.o)||0==s&&0<i.ca||!(1==n&&function(t,i){return!(Xo(t.i)>=t.i.j-(t.m?1:0)||(t.m?(t.j=i.F.concat(t.j),0):1==t.H||2==t.H||t.C>=(t.cb?0:t.eb)||(t.m=Yr(Ts(t.Na,t,i),Fh(t,t.C)),t.C++,0)))}(t,i)||2==n&&$h(t)))switch(e&&0<e.length&&(i=t.i,i.i=i.i.concat(e)),s){case 1:Bh(t,5);break;case 4:Bh(t,10);break;case 3:Bh(t,6);break;default:Bh(t,2)}}function Fh(t,i){let e=t.ab+Math.floor(Math.random()*t.hb);return t.isActive()||(e*=2),e*i}function Bh(t,i){if(t.l.info("Error code "+i),2==i){var e=null;t.h&&(e=null);var n=Ts(t.pb,t);e||(e=new Ao("//www.google.com/images/cleardot.gif"),ys.location&&"http"==ys.location.protocol||ko(e,"https"),Ro(e)),function(t,i){const e=new Br;if(ys.Image){const n=new Image;n.onload=As(sh,e,n,"TestLoadImage: loaded",!0,i),n.onerror=As(sh,e,n,"TestLoadImage: error",!1,i),n.onabort=As(sh,e,n,"TestLoadImage: abort",!1,i),n.ontimeout=As(sh,e,n,"TestLoadImage: timeout",!1,i),ys.setTimeout((function(){n.ontimeout&&n.ontimeout()}),1e4),n.src=t}else i(!1)}(e.toString(),n)}else Kr(2);t.H=0,t.h&&t.h.za(i),Vh(t),Oh(t)}function Vh(t){if(t.H=0,t.ma=[],t.h){const i=th(t.i);0==i.length&&0==t.j.length||(Ds(t.ma,i),Ds(t.ma,t.j),t.i.i.length=0,Os(t.j),t.j.length=0),t.h.ya()}}function zh(t,i,e){var n=e instanceof Ao?So(e):new Ao(e);if(""!=n.g)i&&(n.g=i+"."+n.g),No(n,n.m);else{var s=ys.location;n=s.protocol,i=i?i+"."+s.hostname:s.hostname,s=+s.port;var r=new Ao(null);n&&ko(r,n),i&&(r.g=i),s&&No(r,s),e&&(r.l=e),n=r}return e=t.F,i=t.Da,e&&i&&Do(n,e,i),Do(n,"VER",t.ra),Ch(t,n),n}function Hh(t,i,e){if(i&&!t.J)throw Error("Can't create secondary domain capable XhrIo object.");return(i=e&&t.Ha&&!t.va?new fh(new rh({ob:!0})):new fh(t.va)).Oa(t.J),i}function Gh(){}function qh(t,i){gr.call(this),this.g=new Sh(i),this.l=t,this.h=i&&i.messageUrlParams||null,t=i&&i.messageHeaders||null,i&&i.clientProtocolHeaderRequired&&(t?t["X-Client-Protocol"]="webchannel":t={"X-Client-Protocol":"webchannel"}),this.g.s=t,t=i&&i.initMessageHeaders||null,i&&i.messageContentType&&(t?t["X-WebChannel-Content-Type"]=i.messageContentType:t={"X-WebChannel-Content-Type":i.messageContentType}),i&&i.Ca&&(t?t["X-WebChannel-Client-Profile"]=i.Ca:t={"X-WebChannel-Client-Profile":i.Ca}),this.g.U=t,(t=i&&i.cc)&&!Ps(t)&&(this.g.o=t),this.A=i&&i.supportsCrossDomainXhr||!1,this.v=i&&i.sendRawJson||!1,(i=i&&i.httpSessionIdParam)&&!Ps(i)&&(this.g.F=i,null!==(t=this.h)&&i in t&&(i in(t=this.h)&&delete t[i])),this.j=new Kh(this)}function Wh(t){no.call(this),t.tt&&(this.headers=t.tt,this.statusCode=t.it,delete t.tt,delete t.it);var i=t.et;if(i){t:{for(const e in i){t=e;break t}t=void 0}(this.i=t)&&(t=this.i,i=null!==i&&t in i?i[t]:void 0),this.data=i}else this.data=t}function Jh(){so.call(this),this.status=1}function Kh(t){this.g=t}function Xh(){this.blockSize=-1,this.blockSize=64,this.g=Array(4),this.m=Array(this.blockSize),this.i=this.h=0,this.reset()}function Yh(t,i,e){e||(e=0);var n=Array(16);if("string"==typeof i)for(var s=0;16>s;++s)n[s]=i.charCodeAt(e++)|i.charCodeAt(e++)<<8|i.charCodeAt(e++)<<16|i.charCodeAt(e++)<<24;else for(s=0;16>s;++s)n[s]=i[e++]|i[e++]<<8|i[e++]<<16|i[e++]<<24;i=t.g[0],e=t.g[1],s=t.g[2];var r=t.g[3],o=i+(r^e&(s^r))+n[0]+3614090360&4294967295;o=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=(e=(s=(r=(i=e+(o<<7&4294967295|o>>>25))+((o=r+(s^i&(e^s))+n[1]+3905402710&4294967295)<<12&4294967295|o>>>20))+((o=s+(e^r&(i^e))+n[2]+606105819&4294967295)<<17&4294967295|o>>>15))+((o=e+(i^s&(r^i))+n[3]+3250441966&4294967295)<<22&4294967295|o>>>10))+((o=i+(r^e&(s^r))+n[4]+4118548399&4294967295)<<7&4294967295|o>>>25))+((o=r+(s^i&(e^s))+n[5]+1200080426&4294967295)<<12&4294967295|o>>>20))+((o=s+(e^r&(i^e))+n[6]+2821735955&4294967295)<<17&4294967295|o>>>15))+((o=e+(i^s&(r^i))+n[7]+4249261313&4294967295)<<22&4294967295|o>>>10))+((o=i+(r^e&(s^r))+n[8]+1770035416&4294967295)<<7&4294967295|o>>>25))+((o=r+(s^i&(e^s))+n[9]+2336552879&4294967295)<<12&4294967295|o>>>20))+((o=s+(e^r&(i^e))+n[10]+4294925233&4294967295)<<17&4294967295|o>>>15))+((o=e+(i^s&(r^i))+n[11]+2304563134&4294967295)<<22&4294967295|o>>>10))+((o=i+(r^e&(s^r))+n[12]+1804603682&4294967295)<<7&4294967295|o>>>25))+((o=r+(s^i&(e^s))+n[13]+4254626195&4294967295)<<12&4294967295|o>>>20))+((o=s+(e^r&(i^e))+n[14]+2792965006&4294967295)<<17&4294967295|o>>>15))+((o=e+(i^s&(r^i))+n[15]+1236535329&4294967295)<<22&4294967295|o>>>10))+((o=i+(s^r&(e^s))+n[1]+4129170786&4294967295)<<5&4294967295|o>>>27))+((o=r+(e^s&(i^e))+n[6]+3225465664&4294967295)<<9&4294967295|o>>>23))+((o=s+(i^e&(r^i))+n[11]+643717713&4294967295)<<14&4294967295|o>>>18))+((o=e+(r^i&(s^r))+n[0]+3921069994&4294967295)<<20&4294967295|o>>>12))+((o=i+(s^r&(e^s))+n[5]+3593408605&4294967295)<<5&4294967295|o>>>27))+((o=r+(e^s&(i^e))+n[10]+38016083&4294967295)<<9&4294967295|o>>>23))+((o=s+(i^e&(r^i))+n[15]+3634488961&4294967295)<<14&4294967295|o>>>18))+((o=e+(r^i&(s^r))+n[4]+3889429448&4294967295)<<20&4294967295|o>>>12))+((o=i+(s^r&(e^s))+n[9]+568446438&4294967295)<<5&4294967295|o>>>27))+((o=r+(e^s&(i^e))+n[14]+3275163606&4294967295)<<9&4294967295|o>>>23))+((o=s+(i^e&(r^i))+n[3]+4107603335&4294967295)<<14&4294967295|o>>>18))+((o=e+(r^i&(s^r))+n[8]+1163531501&4294967295)<<20&4294967295|o>>>12))+((o=i+(s^r&(e^s))+n[13]+2850285829&4294967295)<<5&4294967295|o>>>27))+((o=r+(e^s&(i^e))+n[2]+4243563512&4294967295)<<9&4294967295|o>>>23))+((o=s+(i^e&(r^i))+n[7]+1735328473&4294967295)<<14&4294967295|o>>>18))+((o=e+(r^i&(s^r))+n[12]+2368359562&4294967295)<<20&4294967295|o>>>12))+((o=i+(e^s^r)+n[5]+4294588738&4294967295)<<4&4294967295|o>>>28))+((o=r+(i^e^s)+n[8]+2272392833&4294967295)<<11&4294967295|o>>>21))+((o=s+(r^i^e)+n[11]+1839030562&4294967295)<<16&4294967295|o>>>16))+((o=e+(s^r^i)+n[14]+4259657740&4294967295)<<23&4294967295|o>>>9))+((o=i+(e^s^r)+n[1]+2763975236&4294967295)<<4&4294967295|o>>>28))+((o=r+(i^e^s)+n[4]+1272893353&4294967295)<<11&4294967295|o>>>21))+((o=s+(r^i^e)+n[7]+4139469664&4294967295)<<16&4294967295|o>>>16))+((o=e+(s^r^i)+n[10]+3200236656&4294967295)<<23&4294967295|o>>>9))+((o=i+(e^s^r)+n[13]+681279174&4294967295)<<4&4294967295|o>>>28))+((o=r+(i^e^s)+n[0]+3936430074&4294967295)<<11&4294967295|o>>>21))+((o=s+(r^i^e)+n[3]+3572445317&4294967295)<<16&4294967295|o>>>16))+((o=e+(s^r^i)+n[6]+76029189&4294967295)<<23&4294967295|o>>>9))+((o=i+(e^s^r)+n[9]+3654602809&4294967295)<<4&4294967295|o>>>28))+((o=r+(i^e^s)+n[12]+3873151461&4294967295)<<11&4294967295|o>>>21))+((o=s+(r^i^e)+n[15]+530742520&4294967295)<<16&4294967295|o>>>16))+((o=e+(s^r^i)+n[2]+3299628645&4294967295)<<23&4294967295|o>>>9))+((o=i+(s^(e|~r))+n[0]+4096336452&4294967295)<<6&4294967295|o>>>26))+((o=r+(e^(i|~s))+n[7]+1126891415&4294967295)<<10&4294967295|o>>>22))+((o=s+(i^(r|~e))+n[14]+2878612391&4294967295)<<15&4294967295|o>>>17))+((o=e+(r^(s|~i))+n[5]+4237533241&4294967295)<<21&4294967295|o>>>11))+((o=i+(s^(e|~r))+n[12]+1700485571&4294967295)<<6&4294967295|o>>>26))+((o=r+(e^(i|~s))+n[3]+2399980690&4294967295)<<10&4294967295|o>>>22))+((o=s+(i^(r|~e))+n[10]+4293915773&4294967295)<<15&4294967295|o>>>17))+((o=e+(r^(s|~i))+n[1]+2240044497&4294967295)<<21&4294967295|o>>>11))+((o=i+(s^(e|~r))+n[8]+1873313359&4294967295)<<6&4294967295|o>>>26))+((o=r+(e^(i|~s))+n[15]+4264355552&4294967295)<<10&4294967295|o>>>22))+((o=s+(i^(r|~e))+n[6]+2734768916&4294967295)<<15&4294967295|o>>>17))+((o=e+(r^(s|~i))+n[13]+1309151649&4294967295)<<21&4294967295|o>>>11))+((r=(i=e+((o=i+(s^(e|~r))+n[4]+4149444226&4294967295)<<6&4294967295|o>>>26))+((o=r+(e^(i|~s))+n[11]+3174756917&4294967295)<<10&4294967295|o>>>22))^((s=r+((o=s+(i^(r|~e))+n[2]+718787259&4294967295)<<15&4294967295|o>>>17))|~i))+n[9]+3951481745&4294967295,t.g[0]=t.g[0]+i&4294967295,t.g[1]=t.g[1]+(s+(o<<21&4294967295|o>>>11))&4294967295,t.g[2]=t.g[2]+s&4294967295,t.g[3]=t.g[3]+r&4294967295}function Qh(t,i){this.h=i;for(var e=[],n=!0,s=t.length-1;0<=s;s--){var r=0|t[s];n&&r==i||(e[s]=r,n=!1)}this.g=e}(ps=fh.prototype).Oa=function(t){this.M=t},ps.ha=function(t,i,e,n){if(this.g)throw Error("[goog.net.XhrIo] Object is active with another request="+this.I+"; newUri="+t);i=i?i.toUpperCase():"GET",this.I=t,this.j="",this.m=0,this.F=!1,this.h=!0,this.g=this.u?this.u.g():io.g(),this.C=this.u?to(this.u):to(io),this.g.onreadystatechange=Ts(this.La,this);try{this.G=!0,this.g.open(i,String(t),!0),this.G=!1}catch(t){return void wh(this,t)}if(t=e||"",e=new Map(this.headers),n)if(Object.getPrototypeOf(n)===Object.prototype)for(var s in n)e.set(s,n[s]);else{if("function"!=typeof n.keys||"function"!=typeof n.get)throw Error("Unknown input type for opt_headers: "+String(n));for(const t of n.keys())e.set(t,n.get(t))}n=Array.from(e.keys()).find((t=>"content-type"==t.toLowerCase())),s=ys.FormData&&t instanceof ys.FormData,!(0<=Ns(vh,i))||n||s||e.set("Content-Type","application/x-www-form-urlencoded;charset=utf-8");for(const[t,i]of e)this.g.setRequestHeader(t,i);this.K&&(this.g.responseType=this.K),"withCredentials"in this.g&&this.g.withCredentials!==this.M&&(this.g.withCredentials=this.M);try{bh(this),0<this.B&&((this.L=function(t){return Us&&"number"==typeof t.timeout&&void 0!==t.ontimeout}(this.g))?(this.g.timeout=this.B,this.g.ontimeout=Ts(this.ua,this)):this.A=Mr(this.ua,this.B,this)),this.v=!0,this.g.send(t),this.v=!1}catch(t){wh(this,t)}},ps.ua=function(){void 0!==ws&&this.g&&(this.j="Timed out after "+this.B+"ms, aborting",this.m=8,mr(this,"timeout"),this.abort(8))},ps.abort=function(t){this.g&&this.h&&(this.h=!1,this.l=!0,this.g.abort(),this.l=!1,this.m=t||7,mr(this,"complete"),mr(this,"abort"),mh(this))},ps.N=function(){this.g&&(this.h&&(this.h=!1,this.l=!0,this.g.abort(),this.l=!1),mh(this,!0)),fh.$.N.call(this)},ps.La=function(){this.s||(this.G||this.v||this.l?gh(this):this.kb())},ps.kb=function(){gh(this)},ps.isActive=function(){return!!this.g},ps.da=function(){try{return 2<Eh(this)?this.g.status:-1}catch(t){return-1}},ps.ja=function(){try{return this.g?this.g.responseText:""}catch(t){return""}},ps.Wa=function(t){if(this.g){var i=this.g.responseText;return t&&0==i.indexOf(t)&&(i=i.substring(t.length)),lh(i)}},ps.Ia=function(){return this.m},ps.Sa=function(){return"string"==typeof this.j?this.j:String(this.j)},(ps=Sh.prototype).ra=8,ps.H=1,ps.Na=function(t){if(this.m)if(this.m=null,1==this.H){if(!t){this.W=Math.floor(1e5*Math.random()),t=this.W++;const s=new oo(this,this.l,t);let r=this.s;if(this.U&&(r?(r=tr(r),er(r,this.U)):r=this.U),null!==this.o||this.O||(s.I=r,r=null),this.P)t:{for(var i=0,e=0;e<this.j.length;e++){var n=this.j[e];if(void 0===(n="nt"in n.map&&"string"==typeof(n=n.map.nt)?n.length:void 0))break;if(4096<(i+=n)){i=e;break t}if(4096===i||e===this.j.length-1){i=e+1;break t}}i=1e3}else i=1e3;i=Ph(this,s,i),Do(e=So(this.I),"RID",t),Do(e,"CVER",22),this.F&&Do(e,"X-HTTP-Session-Id",this.F),Ch(this,e),r&&(this.O?i="headers="+encodeURIComponent(String(_h(r)))+"&"+i:this.o&&Th(e,this.o,r)),Qo(this.i,s),this.bb&&Do(e,"TYPE","init"),this.P?(Do(e,"$req",i),Do(e,"SID","null"),s.aa=!0,lo(s,e,null)):lo(s,e,i),this.H=2}}else 3==this.H&&(t?Rh(this,t):0==this.j.length||Ko(this.i)||Rh(this))},ps.Ma=function(){if(this.u=null,xh(this),this.ca&&!(this.M||null==this.g||0>=this.S)){var t=2*this.S;this.l.info("BP detection timer enabled: "+t),this.B=Yr(Ts(this.jb,this),t)}},ps.jb=function(){this.B&&(this.B=null,this.l.info("BP detection timeout reached."),this.l.info("Buffering proxy detected and switch to long-polling!"),this.G=!1,this.M=!0,Kr(10),Nh(this),xh(this))},ps.ib=function(){null!=this.v&&(this.v=null,Nh(this),$h(this),Kr(19))},ps.pb=function(t){t?(this.l.info("Successfully pinged google.com"),Kr(2)):(this.l.info("Failed to ping google.com"),Kr(1))},ps.isActive=function(){return!!this.h&&this.h.isActive(this)},(ps=Gh.prototype).Ba=function(){},ps.Aa=function(){},ps.za=function(){},ps.ya=function(){},ps.isActive=function(){return!0},ps.Va=function(){},Ss(qh,gr),qh.prototype.m=function(){this.g.h=this.j,this.A&&(this.g.J=!0);var t=this.g,i=this.l,e=this.h||void 0;Kr(0),t.Y=i,t.na=e||{},t.G=t.aa,t.I=zh(t,null,t.Y),Dh(t)},qh.prototype.close=function(){kh(this.g)},qh.prototype.u=function(t){var i=this.g;if("string"==typeof t){var e={};e.nt=t,t=e}else this.v&&((e={}).nt=Er(t),t=e);i.j.push(new qo(i.fb++,t)),3==i.H&&Dh(i)},qh.prototype.N=function(){this.g.h=null,delete this.j,kh(this.g),delete this.g,qh.$.N.call(this)},Ss(Wh,no),Ss(Jh,so),Ss(Kh,Gh),Kh.prototype.Ba=function(){mr(this.g,"a")},Kh.prototype.Aa=function(t){mr(this.g,new Wh(t))},Kh.prototype.za=function(t){mr(this.g,new Jh)},Kh.prototype.ya=function(){mr(this.g,"b")},Ss(Xh,(function(){this.blockSize=-1})),Xh.prototype.reset=function(){this.g[0]=1732584193,this.g[1]=4023233417,this.g[2]=2562383102,this.g[3]=271733878,this.i=this.h=0},Xh.prototype.j=function(t,i){void 0===i&&(i=t.length);for(var e=i-this.blockSize,n=this.m,s=this.h,r=0;r<i;){if(0==s)for(;r<=e;)Yh(this,t,r),r+=this.blockSize;if("string"==typeof t){for(;r<i;)if(n[s++]=t.charCodeAt(r++),s==this.blockSize){Yh(this,n),s=0;break}}else for(;r<i;)if(n[s++]=t[r++],s==this.blockSize){Yh(this,n),s=0;break}}this.h=s,this.i+=i},Xh.prototype.l=function(){var t=Array((56>this.h?this.blockSize:2*this.blockSize)-this.h);t[0]=128;for(var i=1;i<t.length-8;++i)t[i]=0;var e=8*this.i;for(i=t.length-8;i<t.length;++i)t[i]=255&e,e/=256;for(this.j(t),t=Array(16),i=e=0;4>i;++i)for(var n=0;32>n;n+=8)t[e++]=this.g[i]>>>n&255;return t};var Zh={};function ta(t){return-128<=t&&128>t?function(t,i){var e=Zh;return Object.prototype.hasOwnProperty.call(e,t)?e[t]:e[t]=i(t)}(t,(function(t){return new Qh([0|t],0>t?-1:0)})):new Qh([0|t],0>t?-1:0)}function ia(t){if(isNaN(t)||!isFinite(t))return na;if(0>t)return aa(ia(-t));for(var i=[],e=1,n=0;t>=e;n++)i[n]=t/e|0,e*=ea;return new Qh(i,0)}var ea=4294967296,na=ta(0),sa=ta(1),ra=ta(16777216);function oa(t){if(0!=t.h)return!1;for(var i=0;i<t.g.length;i++)if(0!=t.g[i])return!1;return!0}function ha(t){return-1==t.h}function aa(t){for(var i=t.g.length,e=[],n=0;n<i;n++)e[n]=~t.g[n];return new Qh(e,~t.h).add(sa)}function ua(t,i){return t.add(aa(i))}function ca(t,i){for(;(65535&t[i])!=t[i];)t[i+1]+=t[i]>>>16,t[i]&=65535,i++}function la(t,i){this.g=t,this.h=i}function fa(t,i){if(oa(i))throw Error("division by zero");if(oa(t))return new la(na,na);if(ha(t))return i=fa(aa(t),i),new la(aa(i.g),aa(i.h));if(ha(i))return i=fa(t,aa(i)),new la(aa(i.g),i.h);if(30<t.g.length){if(ha(t)||ha(i))throw Error("slowDivide_ only works with positive integers.");for(var e=sa,n=i;0>=n.X(t);)e=da(e),n=da(n);var s=pa(e,1),r=pa(n,1);for(n=pa(n,2),e=pa(e,2);!oa(n);){var o=r.add(n);0>=o.X(t)&&(s=s.add(e),r=o),n=pa(n,1),e=pa(e,1)}return i=ua(t,s.R(i)),new la(s,i)}for(s=na;0<=t.X(i);){for(e=Math.max(1,Math.floor(t.ea()/i.ea())),n=48>=(n=Math.ceil(Math.log(e)/Math.LN2))?1:Math.pow(2,n-48),o=(r=ia(e)).R(i);ha(o)||0<o.X(t);)o=(r=ia(e-=n)).R(i);oa(r)&&(r=sa),s=s.add(r),t=ua(t,o)}return new la(s,t)}function da(t){for(var i=t.g.length+1,e=[],n=0;n<i;n++)e[n]=t.D(n)<<1|t.D(n-1)>>>31;return new Qh(e,t.h)}function pa(t,i){var e=i>>5;i%=32;for(var n=t.g.length-e,s=[],r=0;r<n;r++)s[r]=0<i?t.D(r+e)>>>i|t.D(r+e+1)<<32-i:t.D(r+e);return new Qh(s,t.h)}(ps=Qh.prototype).ea=function(){if(ha(this))return-aa(this).ea();for(var t=0,i=1,e=0;e<this.g.length;e++){var n=this.D(e);t+=(0<=n?n:ea+n)*i,i*=ea}return t},ps.toString=function(t){if(2>(t=t||10)||36<t)throw Error("radix out of range: "+t);if(oa(this))return"0";if(ha(this))return"-"+aa(this).toString(t);for(var i=ia(Math.pow(t,6)),e=this,n="";;){var s=fa(e,i).g,r=((0<(e=ua(e,s.R(i))).g.length?e.g[0]:e.h)>>>0).toString(t);if(oa(e=s))return r+n;for(;6>r.length;)r="0"+r;n=r+n}},ps.D=function(t){return 0>t?0:t<this.g.length?this.g[t]:this.h},ps.X=function(t){return ha(t=ua(this,t))?-1:oa(t)?0:1},ps.abs=function(){return ha(this)?aa(this):this},ps.add=function(t){for(var i=Math.max(this.g.length,t.g.length),e=[],n=0,s=0;s<=i;s++){var r=n+(65535&this.D(s))+(65535&t.D(s)),o=(r>>>16)+(this.D(s)>>>16)+(t.D(s)>>>16);n=o>>>16,r&=65535,o&=65535,e[s]=o<<16|r}return new Qh(e,-2147483648&e[e.length-1]?-1:0)},ps.R=function(t){if(oa(this)||oa(t))return na;if(ha(this))return ha(t)?aa(this).R(aa(t)):aa(aa(this).R(t));if(ha(t))return aa(this.R(aa(t)));if(0>this.X(ra)&&0>t.X(ra))return ia(this.ea()*t.ea());for(var i=this.g.length+t.g.length,e=[],n=0;n<2*i;n++)e[n]=0;for(n=0;n<this.g.length;n++)for(var s=0;s<t.g.length;s++){var r=this.D(n)>>>16,o=65535&this.D(n),h=t.D(s)>>>16,a=65535&t.D(s);e[2*n+2*s]+=o*a,ca(e,2*n+2*s),e[2*n+2*s+1]+=r*a,ca(e,2*n+2*s+1),e[2*n+2*s+1]+=o*h,ca(e,2*n+2*s+1),e[2*n+2*s+2]+=r*h,ca(e,2*n+2*s+2)}for(n=0;n<i;n++)e[n]=e[2*n+1]<<16|e[2*n];for(n=i;n<2*i;n++)e[n]=0;return new Qh(e,0)},ps.gb=function(t){return fa(this,t).h},ps.and=function(t){for(var i=Math.max(this.g.length,t.g.length),e=[],n=0;n<i;n++)e[n]=this.D(n)&t.D(n);return new Qh(e,this.h&t.h)},ps.or=function(t){for(var i=Math.max(this.g.length,t.g.length),e=[],n=0;n<i;n++)e[n]=this.D(n)|t.D(n);return new Qh(e,this.h|t.h)},ps.xor=function(t){for(var i=Math.max(this.g.length,t.g.length),e=[],n=0;n<i;n++)e[n]=this.D(n)^t.D(n);return new Qh(e,this.h^t.h)},qh.prototype.send=qh.prototype.u,qh.prototype.open=qh.prototype.m,qh.prototype.close=qh.prototype.close,Qr.NO_ERROR=0,Qr.TIMEOUT=8,Qr.HTTP_ERROR=6,eo.OPEN="a",eo.CLOSE="b",eo.ERROR="c",eo.MESSAGE="d",gr.prototype.listen=gr.prototype.O,fh.prototype.listenOnce=fh.prototype.P,fh.prototype.getLastError=fh.prototype.Sa,fh.prototype.getLastErrorCode=fh.prototype.Ia,fh.prototype.getStatus=fh.prototype.da,fh.prototype.getResponseJson=fh.prototype.Wa,fh.prototype.getResponseText=fh.prototype.ja,fh.prototype.send=fh.prototype.ha,fh.prototype.setWithCredentials=fh.prototype.Oa,Xh.prototype.digest=Xh.prototype.l,Xh.prototype.reset=Xh.prototype.reset,Xh.prototype.update=Xh.prototype.j,Qh.prototype.add=Qh.prototype.add,Qh.prototype.multiply=Qh.prototype.R,Qh.prototype.modulo=Qh.prototype.gb,Qh.prototype.compare=Qh.prototype.X,Qh.prototype.toNumber=Qh.prototype.ea,Qh.prototype.toString=Qh.prototype.toString,Qh.prototype.getBits=Qh.prototype.D,Qh.fromNumber=ia,Qh.fromString=function t(i,e){if(0==i.length)throw Error("number format error: empty string");if(2>(e=e||10)||36<e)throw Error("radix out of range: "+e);if("-"==i.charAt(0))return aa(t(i.substring(1),e));if(0<=i.indexOf("-"))throw Error('number format error: interior "-" character');for(var n=ia(Math.pow(e,8)),s=na,r=0;r<i.length;r+=8){var o=Math.min(8,i.length-r),h=parseInt(i.substring(r,r+o),e);8>o?(o=ia(Math.pow(e,o)),s=s.R(o).add(ia(h))):s=(s=s.R(n)).add(ia(h))}return s};var va=Qh;const wa="@firebase/firestore";
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ya{constructor(t){this.uid=t}isAuthenticated(){return null!=this.uid}toKey(){return this.isAuthenticated()?"uid:"+this.uid:"anonymous-user"}isEqual(t){return t.uid===this.uid}}ya.UNAUTHENTICATED=new ya(null),ya.GOOGLE_CREDENTIALS=new ya("google-credentials-uid"),ya.FIRST_PARTY=new ya("first-party-uid"),ya.MOCK_USER=new ya("mock-user");
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
let ga="10.3.1";
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const ma=new xt("@firebase/firestore");function ba(t,...i){if(ma.logLevel<=Ct.DEBUG){const e=i.map(Ia);ma.debug(`Firestore (${ga}): ${t}`,...e)}}function Ea(t,...i){if(ma.logLevel<=Ct.ERROR){const e=i.map(Ia);ma.error(`Firestore (${ga}): ${t}`,...e)}}function Ia(t){if("string"==typeof t)return t;try{
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
return function(t){return JSON.stringify(t)}(t)}catch(i){return t}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function _a(t="Unexpected state"){const i=`FIRESTORE (${ga}) INTERNAL ASSERTION FAILED: `+t;throw Ea(i),new Error(i)}function Ta(t,i){t||_a()}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */const Aa={OK:"ok",CANCELLED:"cancelled",UNKNOWN:"unknown",INVALID_ARGUMENT:"invalid-argument",DEADLINE_EXCEEDED:"deadline-exceeded",NOT_FOUND:"not-found",ALREADY_EXISTS:"already-exists",PERMISSION_DENIED:"permission-denied",UNAUTHENTICATED:"unauthenticated",RESOURCE_EXHAUSTED:"resource-exhausted",FAILED_PRECONDITION:"failed-precondition",ABORTED:"aborted",OUT_OF_RANGE:"out-of-range",UNIMPLEMENTED:"unimplemented",INTERNAL:"internal",UNAVAILABLE:"unavailable",DATA_LOSS:"data-loss"};class Sa extends mt{constructor(t,i){super(t,i),this.code=t,this.message=i,this.toString=()=>`${this.name}: [code=${this.code}]: ${this.message}`}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ka{constructor(){this.promise=new Promise(((t,i)=>{this.resolve=t,this.reject=i}))}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Na{constructor(t,i){this.user=i,this.type="OAuth",this.headers=new Map,this.headers.set("Authorization",`Bearer ${t}`)}}class Oa{getToken(){return Promise.resolve(null)}invalidateToken(){}start(t,i){t.enqueueRetryable((()=>i(ya.UNAUTHENTICATED)))}shutdown(){}}class Da{constructor(t){this.token=t,this.changeListener=null}getToken(){return Promise.resolve(this.token)}invalidateToken(){}start(t,i){this.changeListener=i,t.enqueueRetryable((()=>i(this.token.user)))}shutdown(){this.changeListener=null}}class Ra{constructor(t){this.t=t,this.currentUser=ya.UNAUTHENTICATED,this.i=0,this.forceRefresh=!1,this.auth=null}start(t,i){let e=this.i;const n=t=>this.i!==e?(e=this.i,i(t)):Promise.resolve();let s=new ka;this.o=()=>{this.i++,this.currentUser=this.u(),s.resolve(),s=new ka,t.enqueueRetryable((()=>n(this.currentUser)))};const r=()=>{const i=s;t.enqueueRetryable((async()=>{await i.promise,await n(this.currentUser)}))},o=t=>{ba("FirebaseAuthCredentialsProvider","Auth detected"),this.auth=t,this.auth.addAuthTokenListener(this.o),r()};this.t.onInit((t=>o(t))),setTimeout((()=>{if(!this.auth){const t=this.t.getImmediate({optional:!0});t?o(t):(ba("FirebaseAuthCredentialsProvider","Auth not yet detected"),s.resolve(),s=new ka)}}),0),r()}getToken(){const t=this.i,i=this.forceRefresh;return this.forceRefresh=!1,this.auth?this.auth.getToken(i).then((i=>this.i!==t?(ba("FirebaseAuthCredentialsProvider","getToken aborted due to token change."),this.getToken()):i?(Ta("string"==typeof i.accessToken),new Na(i.accessToken,this.currentUser)):null)):Promise.resolve(null)}invalidateToken(){this.forceRefresh=!0}shutdown(){this.auth&&this.auth.removeAuthTokenListener(this.o)}u(){const t=this.auth&&this.auth.getUid();return Ta(null===t||"string"==typeof t),new ya(t)}}class Ca{constructor(t,i,e){this.l=t,this.h=i,this.P=e,this.type="FirstParty",this.user=ya.FIRST_PARTY,this.I=new Map}T(){return this.P?this.P():null}get headers(){this.I.set("X-Goog-AuthUser",this.l);const t=this.T();return t&&this.I.set("Authorization",t),this.h&&this.I.set("X-Goog-Iam-Authorization-Token",this.h),this.I}}class Pa{constructor(t,i,e){this.l=t,this.h=i,this.P=e}getToken(){return Promise.resolve(new Ca(this.l,this.h,this.P))}start(t,i){t.enqueueRetryable((()=>i(ya.FIRST_PARTY)))}shutdown(){}invalidateToken(){}}class Ma{constructor(t){this.value=t,this.type="AppCheck",this.headers=new Map,t&&t.length>0&&this.headers.set("x-firebase-appcheck",this.value)}}class $a{constructor(t){this.A=t,this.forceRefresh=!1,this.appCheck=null,this.R=null}start(t,i){const e=t=>{null!=t.error&&ba("FirebaseAppCheckTokenProvider",`Error getting App Check token; using placeholder token instead. Error: ${t.error.message}`);const e=t.token!==this.R;return this.R=t.token,ba("FirebaseAppCheckTokenProvider",`Received ${e?"new":"existing"} token.`),e?i(t.token):Promise.resolve()};this.o=i=>{t.enqueueRetryable((()=>e(i)))};const n=t=>{ba("FirebaseAppCheckTokenProvider","AppCheck detected"),this.appCheck=t,this.appCheck.addTokenListener(this.o)};this.A.onInit((t=>n(t))),setTimeout((()=>{if(!this.appCheck){const t=this.A.getImmediate({optional:!0});t?n(t):ba("FirebaseAppCheckTokenProvider","AppCheck not yet detected")}}),0)}getToken(){const t=this.forceRefresh;return this.forceRefresh=!1,this.appCheck?this.appCheck.getToken(t).then((t=>t?(Ta("string"==typeof t.token),this.R=t.token,new Ma(t.token)):null)):Promise.resolve(null)}invalidateToken(){this.forceRefresh=!0}shutdown(){this.appCheck&&this.appCheck.removeTokenListener(this.o)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function La(t){const i="undefined"!=typeof self&&(self.crypto||self.msCrypto),e=new Uint8Array(t);if(i&&"function"==typeof i.getRandomValues)i.getRandomValues(e);else for(let i=0;i<t;i++)e[i]=Math.floor(256*Math.random());return e}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class xa{static V(){const t="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",i=62*Math.floor(256/62);let e="";for(;e.length<20;){const n=La(40);for(let s=0;s<n.length;++s)e.length<20&&n[s]<i&&(e+=t.charAt(n[s]%62))}return e}}function Ua(t){return"IndexedDbTransactionError"===t.name}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class ja{constructor(t,i,e,n,s,r,o,h,a){this.databaseId=t,this.appId=i,this.persistenceKey=e,this.host=n,this.ssl=s,this.forceLongPolling=r,this.autoDetectLongPolling=o,this.longPollingOptions=h,this.useFetchStreams=a}}class Fa{constructor(t,i){this.projectId=t,this.database=i||"(default)"}static empty(){return new Fa("","")}get isDefaultDatabase(){return"(default)"===this.database}isEqual(t){return t instanceof Fa&&t.projectId===this.projectId&&t.database===this.database}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */var Ba,Va;function za(){return"undefined"!=typeof document?document:null}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(Va=Ba||(Ba={}))[Va.OK=0]="OK",Va[Va.CANCELLED=1]="CANCELLED",Va[Va.UNKNOWN=2]="UNKNOWN",Va[Va.INVALID_ARGUMENT=3]="INVALID_ARGUMENT",Va[Va.DEADLINE_EXCEEDED=4]="DEADLINE_EXCEEDED",Va[Va.NOT_FOUND=5]="NOT_FOUND",Va[Va.ALREADY_EXISTS=6]="ALREADY_EXISTS",Va[Va.PERMISSION_DENIED=7]="PERMISSION_DENIED",Va[Va.UNAUTHENTICATED=16]="UNAUTHENTICATED",Va[Va.RESOURCE_EXHAUSTED=8]="RESOURCE_EXHAUSTED",Va[Va.FAILED_PRECONDITION=9]="FAILED_PRECONDITION",Va[Va.ABORTED=10]="ABORTED",Va[Va.OUT_OF_RANGE=11]="OUT_OF_RANGE",Va[Va.UNIMPLEMENTED=12]="UNIMPLEMENTED",Va[Va.INTERNAL=13]="INTERNAL",Va[Va.UNAVAILABLE=14]="UNAVAILABLE",Va[Va.DATA_LOSS=15]="DATA_LOSS",
/**
 * @license
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
new va([4294967295,4294967295],0);class Ha{constructor(t,i,e=1e3,n=1.5,s=6e4){this._i=t,this.timerId=i,this.xo=e,this.Oo=n,this.No=s,this.Bo=0,this.Lo=null,this.ko=Date.now(),this.reset()}reset(){this.Bo=0}qo(){this.Bo=this.No}Qo(t){this.cancel();const i=Math.floor(this.Bo+this.Ko()),e=Math.max(0,Date.now()-this.ko),n=Math.max(0,i-e);n>0&&ba("ExponentialBackoff",`Backing off for ${n} ms (base delay: ${this.Bo} ms, delay with jitter: ${i} ms, last attempt: ${e} ms ago)`),this.Lo=this._i.enqueueAfterDelay(this.timerId,n,(()=>(this.ko=Date.now(),t()))),this.Bo*=this.Oo,this.Bo<this.xo&&(this.Bo=this.xo),this.Bo>this.No&&(this.Bo=this.No)}$o(){null!==this.Lo&&(this.Lo.skipDelay(),this.Lo=null)}cancel(){null!==this.Lo&&(this.Lo.cancel(),this.Lo=null)}Ko(){return(Math.random()-.5)*this.Bo}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Ga{constructor(t,i,e,n,s){this.asyncQueue=t,this.timerId=i,this.targetTimeMs=e,this.op=n,this.removalCallback=s,this.deferred=new ka,this.then=this.deferred.promise.then.bind(this.deferred.promise),this.deferred.promise.catch((t=>{}))}get promise(){return this.deferred.promise}static createAndSchedule(t,i,e,n,s){const r=Date.now()+e,o=new Ga(t,i,r,n,s);return o.start(e),o}start(t){this.timerHandle=setTimeout((()=>this.handleDelayElapsed()),t)}skipDelay(){return this.handleDelayElapsed()}cancel(t){null!==this.timerHandle&&(this.clearTimeout(),this.deferred.reject(new Sa(Aa.CANCELLED,"Operation cancelled"+(t?": "+t:""))))}handleDelayElapsed(){this.asyncQueue.enqueueAndForget((()=>null!==this.timerHandle?(this.clearTimeout(),this.op().then((t=>this.deferred.resolve(t)))):Promise.resolve()))}clearTimeout(){null!==this.timerHandle&&(this.removalCallback(this),clearTimeout(this.timerHandle),this.timerHandle=null)}}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class qa{constructor(t,i,e,n){this.authCredentials=t,this.appCheckCredentials=i,this.asyncQueue=e,this.databaseInfo=n,this.user=ya.UNAUTHENTICATED,this.clientId=xa.V(),this.authCredentialListener=()=>Promise.resolve(),this.appCheckCredentialListener=()=>Promise.resolve(),this.authCredentials.start(e,(async t=>{ba("FirestoreClient","Received user=",t.uid),await this.authCredentialListener(t),this.user=t})),this.appCheckCredentials.start(e,(t=>(ba("FirestoreClient","Received new app check token=",t),this.appCheckCredentialListener(t,this.user))))}async getConfiguration(){return{asyncQueue:this.asyncQueue,databaseInfo:this.databaseInfo,clientId:this.clientId,authCredentials:this.authCredentials,appCheckCredentials:this.appCheckCredentials,initialUser:this.user,maxConcurrentLimboResolutions:100}}setCredentialChangeListener(t){this.authCredentialListener=t}setAppCheckTokenChangeListener(t){this.appCheckCredentialListener=t}verifyNotTerminated(){if(this.asyncQueue.isShuttingDown)throw new Sa(Aa.FAILED_PRECONDITION,"The client has already been terminated.")}terminate(){this.asyncQueue.enterRestrictedMode();const t=new ka;return this.asyncQueue.enqueueAndForgetEvenWhileRestricted((async()=>{try{this._onlineComponents&&await this._onlineComponents.terminate(),this._offlineComponents&&await this._offlineComponents.terminate(),this.authCredentials.shutdown(),this.appCheckCredentials.shutdown(),t.resolve()}catch(i){const e=function(t,i){if(Ea("AsyncQueue",`${i}: ${t}`),Ua(t))return new Sa(Aa.UNAVAILABLE,`${i}: ${t}`);throw t}(i,"Failed to shutdown persistence");t.reject(e)}})),t.promise}}
/**
 * @license
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */function Wa(t){const i={};return void 0!==t.timeoutSeconds&&(i.timeoutSeconds=t.timeoutSeconds),i
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */}const Ja=new Map;function Ka(t,i){if("_delegate"in t&&(t=t._delegate),!(t instanceof i)){if(i.name===t.constructor.name)throw new Sa(Aa.INVALID_ARGUMENT,"Type does not match the expected instance. Did you pass a reference from a different Firestore SDK?");{const e=function(t){if(void 0===t)return"undefined";if(null===t)return"null";if("string"==typeof t)return t.length>20&&(t=`${t.substring(0,20)}...`),JSON.stringify(t);if("number"==typeof t||"boolean"==typeof t)return""+t;if("object"==typeof t){if(t instanceof Array)return"an array";{const i=function(t){return t.constructor?t.constructor.name:null}(t);return i?`a custom ${i} object`:"an object"}}return"function"==typeof t?"a function":_a()}(t);throw new Sa(Aa.INVALID_ARGUMENT,`Expected type '${i.name}', but it was: ${e}`)}}return t}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Xa{constructor(t){var i,e;if(void 0===t.host){if(void 0!==t.ssl)throw new Sa(Aa.INVALID_ARGUMENT,"Can't provide ssl option if host option is not set");this.host="firestore.googleapis.com",this.ssl=!0}else this.host=t.host,this.ssl=null===(i=t.ssl)||void 0===i||i;if(this.credentials=t.credentials,this.ignoreUndefinedProperties=!!t.ignoreUndefinedProperties,this.localCache=t.localCache,void 0===t.cacheSizeBytes)this.cacheSizeBytes=41943040;else{if(-1!==t.cacheSizeBytes&&t.cacheSizeBytes<1048576)throw new Sa(Aa.INVALID_ARGUMENT,"cacheSizeBytes must be at least 1048576");this.cacheSizeBytes=t.cacheSizeBytes}(function(t,i,e,n){if(!0===i&&!0===n)throw new Sa(Aa.INVALID_ARGUMENT,`${t} and ${e} cannot be used together.`)})("experimentalForceLongPolling",t.experimentalForceLongPolling,"experimentalAutoDetectLongPolling",t.experimentalAutoDetectLongPolling),this.experimentalForceLongPolling=!!t.experimentalForceLongPolling,this.experimentalForceLongPolling?this.experimentalAutoDetectLongPolling=!1:void 0===t.experimentalAutoDetectLongPolling?this.experimentalAutoDetectLongPolling=!0:this.experimentalAutoDetectLongPolling=!!t.experimentalAutoDetectLongPolling,this.experimentalLongPollingOptions=Wa(null!==(e=t.experimentalLongPollingOptions)&&void 0!==e?e:{}),function(t){if(void 0!==t.timeoutSeconds){if(isNaN(t.timeoutSeconds))throw new Sa(Aa.INVALID_ARGUMENT,`invalid long polling timeout: ${t.timeoutSeconds} (must not be NaN)`);if(t.timeoutSeconds<5)throw new Sa(Aa.INVALID_ARGUMENT,`invalid long polling timeout: ${t.timeoutSeconds} (minimum allowed value is 5)`);if(t.timeoutSeconds>30)throw new Sa(Aa.INVALID_ARGUMENT,`invalid long polling timeout: ${t.timeoutSeconds} (maximum allowed value is 30)`)}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(this.experimentalLongPollingOptions),this.useFetchStreams=!!t.useFetchStreams}isEqual(t){return this.host===t.host&&this.ssl===t.ssl&&this.credentials===t.credentials&&this.cacheSizeBytes===t.cacheSizeBytes&&this.experimentalForceLongPolling===t.experimentalForceLongPolling&&this.experimentalAutoDetectLongPolling===t.experimentalAutoDetectLongPolling&&function(t,i){return t.timeoutSeconds===i.timeoutSeconds}(this.experimentalLongPollingOptions,t.experimentalLongPollingOptions)&&this.ignoreUndefinedProperties===t.ignoreUndefinedProperties&&this.useFetchStreams===t.useFetchStreams}}class Ya{constructor(t,i,e,n){this._authCredentials=t,this._appCheckCredentials=i,this._databaseId=e,this._app=n,this.type="firestore-lite",this._persistenceKey="(lite)",this._settings=new Xa({}),this._settingsFrozen=!1}get app(){if(!this._app)throw new Sa(Aa.FAILED_PRECONDITION,"Firestore was not initialized using the Firebase SDK. 'app' is not available");return this._app}get _initialized(){return this._settingsFrozen}get _terminated(){return void 0!==this._terminateTask}_setSettings(t){if(this._settingsFrozen)throw new Sa(Aa.FAILED_PRECONDITION,"Firestore has already been started and its settings can no longer be changed. You can only modify settings before calling any other methods on a Firestore object.");this._settings=new Xa(t),void 0!==t.credentials&&(this._authCredentials=function(t){if(!t)return new Oa;switch(t.type){case"firstParty":return new Pa(t.sessionIndex||"0",t.iamToken||null,t.authTokenFactory||null);case"provider":return t.client;default:throw new Sa(Aa.INVALID_ARGUMENT,"makeAuthCredentialsProvider failed due to invalid credential type")}}(t.credentials))}_getSettings(){return this._settings}_freezeSettings(){return this._settingsFrozen=!0,this._settings}_delete(){return this._terminateTask||(this._terminateTask=this._terminate()),this._terminateTask}toJSON(){return{app:this._app,databaseId:this._databaseId,settings:this._settings}}_terminate(){return function(t){const i=Ja.get(t);i&&(ba("ComponentProvider","Removing Datastore"),Ja.delete(t),i.terminate())}(this),Promise.resolve()}}function Qa(t,i,e,n={}){var s;const r=(t=Ka(t,Ya))._getSettings(),o=`${i}:${e}`;if("firestore.googleapis.com"!==r.host&&r.host!==o&&function(t,...i){if(ma.logLevel<=Ct.WARN){const e=i.map(Ia);ma.warn(`Firestore (${ga}): ${t}`,...e)}}("Host has been set in both settings() and connectFirestoreEmulator(), emulator host will be used."),t._setSettings(Object.assign(Object.assign({},r),{host:o,ssl:!1})),n.mockUserToken){let i,e;if("string"==typeof n.mockUserToken)i=n.mockUserToken,e=ya.MOCK_USER;else{i=function(t,i){if(t.uid)throw new Error('The "uid" field is no longer supported by mockUserToken. Please use "sub" instead for Firebase Auth User ID.');const e=i||"demo-project",n=t.iat||0,s=t.sub||t.user_id;if(!s)throw new Error("mockUserToken must contain 'sub' or 'user_id' field!");const r=Object.assign({iss:`https://securetoken.google.com/${e}`,aud:e,iat:n,exp:n+3600,auth_time:n,sub:s,user_id:s,firebase:{sign_in_provider:"custom",identities:{}}},t);return[ct(JSON.stringify({alg:"none",type:"JWT"})),ct(JSON.stringify(r)),""].join(".")}(n.mockUserToken,null===(s=t._app)||void 0===s?void 0:s.options.projectId);const r=n.mockUserToken.sub||n.mockUserToken.user_id;if(!r)throw new Sa(Aa.INVALID_ARGUMENT,"mockUserToken must contain 'sub' or 'user_id' field!");e=new ya(r)}t._authCredentials=new Da(new Na(i,e))}}
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */class Za{constructor(){this.Za=Promise.resolve(),this.Xa=[],this.eu=!1,this.tu=[],this.nu=null,this.ru=!1,this.iu=!1,this.su=[],this.Ho=new Ha(this,"async_queue_retry"),this.ou=()=>{const t=za();t&&ba("AsyncQueue","Visibility state changed to "+t.visibilityState),this.Ho.$o()};const t=za();t&&"function"==typeof t.addEventListener&&t.addEventListener("visibilitychange",this.ou)}get isShuttingDown(){return this.eu}enqueueAndForget(t){this.enqueue(t)}enqueueAndForgetEvenWhileRestricted(t){this._u(),this.au(t)}enterRestrictedMode(t){if(!this.eu){this.eu=!0,this.iu=t||!1;const i=za();i&&"function"==typeof i.removeEventListener&&i.removeEventListener("visibilitychange",this.ou)}}enqueue(t){if(this._u(),this.eu)return new Promise((()=>{}));const i=new ka;return this.au((()=>this.eu&&this.iu?Promise.resolve():(t().then(i.resolve,i.reject),i.promise))).then((()=>i.promise))}enqueueRetryable(t){this.enqueueAndForget((()=>(this.Xa.push(t),this.uu())))}async uu(){if(0!==this.Xa.length){try{await this.Xa[0](),this.Xa.shift(),this.Ho.reset()}catch(t){if(!Ua(t))throw t;ba("AsyncQueue","Operation failed with retryable error: "+t)}this.Xa.length>0&&this.Ho.Qo((()=>this.uu()))}}au(t){const i=this.Za.then((()=>(this.ru=!0,t().catch((t=>{this.nu=t,this.ru=!1;const i=function(t){let i=t.message||"";return t.stack&&(i=t.stack.includes(t.message)?t.stack:t.message+"\n"+t.stack),i}
/**
 * @license
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */(t);throw Ea("INTERNAL UNHANDLED ERROR: ",i),t})).then((t=>(this.ru=!1,t))))));return this.Za=i,i}enqueueAfterDelay(t,i,e){this._u(),this.su.indexOf(t)>-1&&(i=0);const n=Ga.createAndSchedule(this,t,i,e,(t=>this.cu(t)));return this.tu.push(n),n}_u(){this.nu&&_a()}verifyOperationInProgress(){}async lu(){let t;do{t=this.Za,await t}while(t!==this.Za)}hu(t){for(const i of this.tu)if(i.timerId===t)return!0;return!1}Pu(t){return this.lu().then((()=>{this.tu.sort(((t,i)=>t.targetTimeMs-i.targetTimeMs));for(const i of this.tu)if(i.skipDelay(),"all"!==t&&i.timerId===t)break;return this.lu()}))}Iu(t){this.su.push(t)}cu(t){const i=this.tu.indexOf(t);this.tu.splice(i,1)}}class tu extends Ya{constructor(t,i,e,n){super(t,i,e,n),this.type="firestore",this._queue=new Za,this._persistenceKey=(null==n?void 0:n.name)||"[DEFAULT]"}_terminate(){return this._firestoreClient||function(t){var i,e,n;const s=t._freezeSettings(),r=function(t,i,e,n){return new ja(t,i,e,n.host,n.ssl,n.experimentalForceLongPolling,n.experimentalAutoDetectLongPolling,Wa(n.experimentalLongPollingOptions),n.useFetchStreams)}(t._databaseId,(null===(i=t._app)||void 0===i?void 0:i.options.appId)||"",t._persistenceKey,s);t._firestoreClient=new qa(t._authCredentials,t._appCheckCredentials,t._queue,r),(null===(e=s.localCache)||void 0===e?void 0:e._offlineComponentProvider)&&(null===(n=s.localCache)||void 0===n?void 0:n._onlineComponentProvider)&&(t._firestoreClient._uninitializedComponentsProvider={_offlineKind:s.localCache.kind,_offline:s.localCache._offlineComponentProvider,_online:s.localCache._onlineComponentProvider})}(this),this._firestoreClient.terminate()}}!function(t,i=!0){!function(t){ga=t}(pi),ci(new Nt("firestore",((t,{instanceIdentifier:e,options:n})=>{const s=t.getProvider("app").getImmediate(),r=new tu(new Ra(t.getProvider("auth-internal")),new $a(t.getProvider("app-check-internal")),function(t,i){if(!Object.prototype.hasOwnProperty.apply(t.options,["projectId"]))throw new Sa(Aa.INVALID_ARGUMENT,'"projectId" not provided in firebase.initializeApp.');return new Fa(t.options.projectId,i)}(s,e),s);return n=Object.assign({useFetchStreams:i},n),r._setSettings(n),r}),"PUBLIC").setMultipleInstances(!0)),yi(wa,"4.1.3",t),yi(wa,"4.1.3","esm2017")}();const iu=vi({apiKey:"AIzaSyDIhcIW_rMTpNTldd7H6L22tLAaUUOPZ-w",authDomain:"firewise-pete-c.firebaseapp.com",projectId:"firewise-pete-c",storageBucket:"firewise-pete-c.appspot.com",messagingSenderId:"306688356910",appId:"1:306688356910:web:6c5df20f68fb6add84e5bf"}),eu=function(t=wi()){const i=li(t,"auth");if(i.isInitialized())return i.getImmediate();const e=
/**
 * @license
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function(t,i){const e=li(t,"auth");if(e.isInitialized()){const t=e.getImmediate();if(It(e.getOptions(),null!=i?i:{}))return t;Li(t,"already-initialized")}return e.initialize({options:i})}(t,{popupRedirectResolver:hs,persistence:[bn,nn,rn]}),n=wt("authTokenSyncURL");if(n){const t=(s=n,async t=>{const i=t&&await t.getIdTokenResult(),e=i&&((new Date).getTime()-Date.parse(i.issuedAtTime))/1e3;if(e&&e>ls)return;const n=null==i?void 0:i.token;fs!==n&&(fs=n,await fetch(s,{method:n?"POST":"DELETE",headers:n?{Authorization:`Bearer ${n}`}:{}}))});!function(t,i,e){kt(t).beforeAuthStateChanged(i,e)}(e,t,(()=>t(e.currentUser))),function(t,i,e,n){kt(t).onIdTokenChanged(i,e,n)}(e,(i=>t(i)))}var s;const r=pt("auth");return r&&xe(e,`http://${r}`),e}(iu);!function(t,i){const e="string"==typeof t?t:i||"(default)",n=li("object"==typeof t?t:wi(),"firestore").getImmediate({identifier:e});if(!n._initialized){const t=(t=>{const i=pt(t);if(!i)return;const e=i.lastIndexOf(":");if(e<=0||e+1===i.length)throw new Error(`Invalid host ${i} with no separate hostname and port!`);const n=parseInt(i.substring(e+1),10);return"["===i[0]?[i.substring(1,e-1),n]:[i.substring(0,e),n]})("firestore");t&&Qa(n,...t)}}(iu);var nu=function(t,i,e,n){for(var s,r=arguments.length,o=r<3?i:null===n?n=Object.getOwnPropertyDescriptor(i,e):n,h=t.length-1;h>=0;h--)(s=t[h])&&(o=(r<3?s(o):r>3?s(i,e,o):s(i,e))||o);return r>3&&o&&Object.defineProperty(i,e,o),o};let su=class extends et{constructor(){super(),this.showHideUser=!1,this.user=null,this.headless=!1,this._initAuth()}render(){var t,i;const e=L`
      <div class="full-screen">
        <button @click=${this._handleLoginClick} class="login-button">Login</button>
      </div>`,n=L`
    <div class="full-width-div">
      <img height="50" width="50" src="${null===(t=this.user)||void 0===t?void 0:t.photoURL}"/>
      <h2>${null===(i=this.user)||void 0===i?void 0:i.displayName}</h2>
      <button id="logout" @click=${this._handleLogoutClick}>
        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="12" viewBox="0 0 60 12">
          <text x="3" y="10" style="font-style:normal;font-variant:normal;font-weight:normal;font-stretch:extra-condensed;font-size:12.7px;font-family:'Arial';-inkscape-font-specification:'DIN Condensed, Normal';font-variant-ligatures:normal;font-variant-caps:normal;font-variant-numeric:normal;font-variant-east-asian:normal;word-spacing:-0.0211667px;stroke-width:0;stroke-dasharray:none" fill="black">LOGOUT</text>
        </svg>
      </button>
    </div>
    ${this.showHideUser?L`<pre>${JSON.stringify(this.user,null,2)}</pre>`:""}
    <slot></slot>
    `,s=L`<slot></slot>`;return this.headless?this.user?s:e:this.user?n:e}updated(t){t.forEach(((t,i)=>{"user"===i&&(eu.currentUser&&(console.log("SETTING CLAIMS"),eu.currentUser.getIdToken(!0).then((t=>{localStorage.setItem("authToken",t),sessionStorage.setItem("authToken",t),document.cookie=`authToken=${t}; path=/; secure; samesite=strict; max-age=3600`})).catch((t=>{console.error("Error getting ID token:",t)}))),this.dispatchEvent(new CustomEvent("user-changed",{detail:{newValue:this.user},bubbles:!0,composed:!0})))}))}async _initAuth(){eu.onAuthStateChanged((async t=>{if(t){console.log("LOGGING IN - FOUND USER");const t=await eu.currentUser;this.user=t}else console.log("LOGGING OUT")}))}async _handleLoginClick(){const t=await Nn(eu,new qe);this.user=t.user}async _handleLogoutClick(t){t.altKey?this.showHideUser?this.showHideUser=!1:this.showHideUser=!0:(await this._signMeOut(),this.user=null)}async _signMeOut(){(function(t){return kt(t).signOut()})(eu).then((()=>{this.user=null})).catch((t=>{alert(t)}))}};su.styles=((t,...i)=>{const n=1===t.length?t[0]:i.reduce(((i,e,n)=>i+(t=>{if(!0===t._$cssResult$)return t.cssText;if("number"==typeof t)return t;throw Error("Value passed to 'css' function must be a 'css' function result: "+t+". Use 'unsafeCSS' to pass non-literal values, but take care to ensure page security.")})(e)+t[n+1]),t[0]);return new s(n,t,e)})`
    :host {
      padding: 1px;
    }
    .full-width-div {
      width: 100%;
      height: 50px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 50px;
      border-bottom: 1px solid #000;
    }

    .full-width-div > div {
        text-align: center;
        flex-grow: 1;
    }

    button {
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 10px;
      border: 1px solid #ccc;
      padding: 5px 10px;
      cursor: pointer;
    }

    .full-screen {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      width: 100vw;
    }

    .login-button {
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
    }

    #logout {
      min-width: 80px;
    }
  `,nu([rt({type:Boolean})],su.prototype,"showHideUser",void 0),nu([rt({type:Object})],su.prototype,"user",void 0),nu([rt({type:Boolean})],su.prototype,"headless",void 0),su=nu([(t=>i=>"function"==typeof i?((t,i)=>(customElements.define(t,i),i))(t,i):((t,i)=>{const{kind:e,elements:n}=i;return{kind:e,elements:n,finisher(i){customElements.define(t,i)}}})(t,i))("fbauth-element")],su);export{su as FBAuthElement};
