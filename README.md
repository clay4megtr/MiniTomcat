# MiniTomcat
从零开始实现一个mini版的Tomcat

### 2018-3-15
三个类完成最简单的web服务器。

### 2018-03-20
第一个servlet容器实现的功能
1. 等待HTTP请求。
2. 构造一个ServletRequest对象和一个ServletResponse对象。
3. 假如该请求需要一个静态资源的话，调用StaticResourceProcessor实例的process方法，同时传递ServletRequest和ServletResponse对象。
4. 假如该请求需要一个servlet的话，加载servlet类并调用servlet的service方法，同时传递ServletRequest和ServletResponse对象。

### bug
仍存在一个bug，程序无法向浏览器回传数据，回传页面显示：该网页无法正常运作
待解决