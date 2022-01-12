"use strict";(self.webpackChunkkotestdocs=self.webpackChunkkotestdocs||[]).push([[305],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return h}});var s=n(7294);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function r(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(e);t&&(s=s.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,s)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?r(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):r(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function a(e,t){if(null==e)return{};var n,s,o=function(e,t){if(null==e)return{};var n,s,o={},r=Object.keys(e);for(s=0;s<r.length;s++)n=r[s],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(s=0;s<r.length;s++)n=r[s],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var l=s.createContext({}),c=function(e){var t=s.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},p=function(e){var t=c(e.components);return s.createElement(l.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return s.createElement(s.Fragment,{},t)}},d=s.forwardRef((function(e,t){var n=e.components,o=e.mdxType,r=e.originalType,l=e.parentName,p=a(e,["components","mdxType","originalType","parentName"]),d=c(n),h=o,m=d["".concat(l,".").concat(h)]||d[h]||u[h]||r;return n?s.createElement(m,i(i({ref:t},p),{},{components:n})):s.createElement(m,i({ref:t},p))}));function h(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var r=n.length,i=new Array(r);i[0]=d;var a={};for(var l in t)hasOwnProperty.call(t,l)&&(a[l]=t[l]);a.originalType=e,a.mdxType="string"==typeof e?e:o,i[1]=a;for(var c=2;c<r;c++)i[c]=n[c];return s.createElement.apply(null,i)}return s.createElement.apply(null,n)}d.displayName="MDXCreateElement"},1965:function(e,t,n){n.r(t),n.d(t,{frontMatter:function(){return a},contentTitle:function(){return l},metadata:function(){return c},toc:function(){return p},default:function(){return d}});var s=n(7462),o=n(3366),r=(n(7294),n(3905)),i=["components"],a={id:"spring",title:"Spring",sidebar_label:"Spring",slug:"spring.html"},l=void 0,c={unversionedId:"extensions/spring",id:"extensions/spring",isDocsHomePage:!1,title:"Spring",description:"Kotest offers a Spring extension that allows you to test code that uses the Spring framework for dependency injection.",source:"@site/docs/extensions/spring.md",sourceDirName:"extensions",slug:"/extensions/spring.html",permalink:"/docs/extensions/spring.html",editUrl:"https://github.com/kotest/kotest/blob/master/documentation/docs/extensions/spring.md",tags:[],version:"current",frontMatter:{id:"spring",title:"Spring",sidebar_label:"Spring",slug:"spring.html"},sidebar:"extensions",previous:{title:"Introduction",permalink:"/docs/extensions/extensions.html"},next:{title:"Ktor",permalink:"/docs/extensions/ktor.html"}},p=[{value:"Constructor Injection",id:"constructor-injection",children:[],level:3},{value:"TestContexts",id:"testcontexts",children:[],level:3},{value:"Test Method Callbacks",id:"test-method-callbacks",children:[],level:3},{value:"Final Classes",id:"final-classes",children:[],level:3}],u={toc:p};function d(e){var t=e.components,n=(0,o.Z)(e,i);return(0,r.kt)("wrapper",(0,s.Z)({},u,n,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("p",null,"Kotest offers a Spring extension that allows you to test code that uses the Spring framework for dependency injection."),(0,r.kt)("div",{className:"admonition admonition-tip alert alert--success"},(0,r.kt)("div",{parentName:"div",className:"admonition-heading"},(0,r.kt)("h5",{parentName:"div"},(0,r.kt)("span",{parentName:"h5",className:"admonition-icon"},(0,r.kt)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"12",height:"16",viewBox:"0 0 12 16"},(0,r.kt)("path",{parentName:"svg",fillRule:"evenodd",d:"M6.5 0C3.48 0 1 2.19 1 5c0 .92.55 2.25 1 3 1.34 2.25 1.78 2.78 2 4v1h5v-1c.22-1.22.66-1.75 2-4 .45-.75 1-2.08 1-3 0-2.81-2.48-5-5.5-5zm3.64 7.48c-.25.44-.47.8-.67 1.11-.86 1.41-1.25 2.06-1.45 3.23-.02.05-.02.11-.02.17H5c0-.06 0-.13-.02-.17-.2-1.17-.59-1.83-1.45-3.23-.2-.31-.42-.67-.67-1.11C2.44 6.78 2 5.65 2 5c0-2.2 2.02-4 4.5-4 1.22 0 2.36.42 3.22 1.19C10.55 2.94 11 3.94 11 5c0 .66-.44 1.78-.86 2.48zM4 14h5c-.23 1.14-1.3 2-2.5 2s-2.27-.86-2.5-2z"}))),"tip")),(0,r.kt)("div",{parentName:"div",className:"admonition-content"},(0,r.kt)("p",{parentName:"div"},"If you prefer to see an example rather than read docs, then there is a sample project using spring webflux ",(0,r.kt)("a",{parentName:"p",href:"https://github.com/kotest/kotest-examples-spring-webflux"},"here")))),(0,r.kt)("p",null,"In order to use this extension, you need to add ",(0,r.kt)("inlineCode",{parentName:"p"},"io.kotest.extensions:kotest-extensions-spring")," module to your test compile path. The latest version can always be found on maven central ",(0,r.kt)("a",{parentName:"p",href:"https://search.maven.org/search?q=a:kotest-extensions-spring%20AND%20g:io.kotest.extensions"},"here"),"."),(0,r.kt)("p",null,"Note: The maven group id differs from the core test framework (io.kotest.extensions)."),(0,r.kt)("p",null,(0,r.kt)("a",{parentName:"p",href:"https://search.maven.org/artifact/io.kotest.extensions/kotest-extensions-spring"},(0,r.kt)("img",{src:"https://img.shields.io/maven-central/v/io.kotest.extensions/kotest-extensions-spring.svg?label=latest%20release"})),"\n",(0,r.kt)("a",{parentName:"p",href:"https://oss.sonatype.org/content/repositories/snapshots/io/kotest/extensions/kotest-extensions-spring/"},(0,r.kt)("img",{src:"https://img.shields.io/nexus/s/https/oss.sonatype.org/io.kotest.extensions/kotest-extensions-spring.svg?label=latest%20snapshot"}))),(0,r.kt)("p",null,"The Spring extension requires you to activate it for all test classes, or per test class. To activate it globally,\nregister the ",(0,r.kt)("inlineCode",{parentName:"p"},"SpringExtension")," in ",(0,r.kt)("a",{parentName:"p",href:"/docs/framework/project-config.html"},"project config"),":"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class ProjectConfig : AbstractProjectConfig() {\n   override fun extensions() = listOf(SpringExtension)\n}\n")),(0,r.kt)("p",null,"To activate it per test class:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyTestSpec : FunSpec() {\n   override fun extensions() = listOf(SpringExtension)\n}\n")),(0,r.kt)("p",null,"In order to let Spring know which configuration class to use, you must annotate your Spec classes with ",(0,r.kt)("inlineCode",{parentName:"p"},"@ContextConfiguration"),".\nThis should point to a class annotated with the Spring ",(0,r.kt)("inlineCode",{parentName:"p"},"@Configuration")," annotation. Alternatively, you can use ",(0,r.kt)("inlineCode",{parentName:"p"},"@ActiveProfile")," to\npoint to a ",(0,r.kt)("a",{parentName:"p",href:"https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html"},"specific application context file"),"."),(0,r.kt)("div",{className:"admonition admonition-note alert alert--secondary"},(0,r.kt)("div",{parentName:"div",className:"admonition-heading"},(0,r.kt)("h5",{parentName:"div"},(0,r.kt)("span",{parentName:"h5",className:"admonition-icon"},(0,r.kt)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"14",height:"16",viewBox:"0 0 14 16"},(0,r.kt)("path",{parentName:"svg",fillRule:"evenodd",d:"M6.3 5.69a.942.942 0 0 1-.28-.7c0-.28.09-.52.28-.7.19-.18.42-.28.7-.28.28 0 .52.09.7.28.18.19.28.42.28.7 0 .28-.09.52-.28.7a1 1 0 0 1-.7.3c-.28 0-.52-.11-.7-.3zM8 7.99c-.02-.25-.11-.48-.31-.69-.2-.19-.42-.3-.69-.31H6c-.27.02-.48.13-.69.31-.2.2-.3.44-.31.69h1v3c.02.27.11.5.31.69.2.2.42.31.69.31h1c.27 0 .48-.11.69-.31.2-.19.3-.42.31-.69H8V7.98v.01zM7 2.3c-3.14 0-5.7 2.54-5.7 5.68 0 3.14 2.56 5.7 5.7 5.7s5.7-2.55 5.7-5.7c0-3.15-2.56-5.69-5.7-5.69v.01zM7 .98c3.86 0 7 3.14 7 7s-3.14 7-7 7-7-3.12-7-7 3.14-7 7-7z"}))),"note")),(0,r.kt)("div",{parentName:"div",className:"admonition-content"},(0,r.kt)("p",{parentName:"div"},"In Kotest 4.3 and earlier, the Spring extension was called ",(0,r.kt)("inlineCode",{parentName:"p"},"SpringListener"),". This extension has now been deprecated in favour of ",(0,r.kt)("inlineCode",{parentName:"p"},"SpringExtension"),". The usage is the same, but the SpringExtension has more functionality. "))),(0,r.kt)("h3",{id:"constructor-injection"},"Constructor Injection"),(0,r.kt)("p",null,"For constructor injection, Kotest automatically registers a ",(0,r.kt)("inlineCode",{parentName:"p"},"SpringAutowireConstructorExtension"),"\nwhen the spring module is added to the build."),(0,r.kt)("p",null,"This extension will intercept each call to create a Spec instance\nand will autowire the beans declared in the primary constructor."),(0,r.kt)("p",null,"The following example is a test class which requires a service called ",(0,r.kt)("inlineCode",{parentName:"p"},"UserService")," in its primary constructor. This service\nclass is just a regular spring bean which has been annotated with @Component."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},'@ContextConfiguration(classes = [(Components::class)])\nclass SpringAutowiredConstructorTest(service: UserService) : WordSpec() {\n  init {\n    "SpringExtension" should {\n      "have autowired the service" {\n        service.repository.findUser().name shouldBe "system_user"\n      }\n    }\n  }\n}\n')),(0,r.kt)("h3",{id:"testcontexts"},"TestContexts"),(0,r.kt)("p",null,"The Spring extensions makes available the ",(0,r.kt)("inlineCode",{parentName:"p"},"TestContextManager")," via the coroutine context that tests execute in. You can\ngain a handle to this instance through the ",(0,r.kt)("inlineCode",{parentName:"p"},"testContextManager()")," extension method."),(0,r.kt)("p",null,"From this you can get the ",(0,r.kt)("inlineCode",{parentName:"p"},"testContext")," that Spring is using."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},'class MySpec(service: UserService) : WordSpec() {\n  init {\n    "SpringExtension" should {\n      "provide the test context manager" {\n         println("The context is " + testContextManager().testContext)\n      }\n    }\n  }\n}\n')),(0,r.kt)("h3",{id:"test-method-callbacks"},"Test Method Callbacks"),(0,r.kt)("p",null,"Spring has various test callbacks such as ",(0,r.kt)("inlineCode",{parentName:"p"},"beforeTestMethod")," that are based around the idea that tests are methods.\nThis assumption is fine for legacy test frameworks like JUnit but not applicable to modern test frameworks like Kotest where tests are functions."),(0,r.kt)("p",null,"Therefore, when using a ",(0,r.kt)("a",{parentName:"p",href:"/docs/framework/testing-styles.html"},"spec style")," that is nested, you can customize when the test method callbacks are fired.\nBy default, this is on the leaf node. You can set these to fire on the root nodes by passing a SpringTestLifecycleMode argument to the extension:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class ProjectConfig : AbstractProjectConfig() {\n   override fun extensions() = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))\n}\n")),(0,r.kt)("h3",{id:"final-classes"},"Final Classes"),(0,r.kt)("p",null,"When using a final class, you may receive a warning from Kotest:"),(0,r.kt)("p",null,(0,r.kt)("inlineCode",{parentName:"p"},"Using SpringListener on a final class. If any Spring annotation fails to work, try making this class open")),(0,r.kt)("p",null,"If you wish, you can disable this warning by setting the system property ",(0,r.kt)("inlineCode",{parentName:"p"},"kotest.listener.spring.ignore.warning")," to true."))}d.isMDXComponent=!0}}]);