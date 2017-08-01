//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var curWwwPath = window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
var pathP = curWwwPath.substring(0, pos);
var path = pathP+"/";

//var path = "http://120.77.35.124:8080/cloud_note/";//为了更改