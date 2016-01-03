<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入页面</title>
<link rel="stylesheet" type="style/css" href="${rc.contextPath }/ui/index/css/login.css">
<script type="text/javascript" src="${rc.contextPath }/ui/index/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath }/ui/index/js/login.js"></script>
<script type="text/javascript">

$(function(){
	  //弹框的url  
    var popup_url = "${rc.contextPath}/login/login";  
    //弹框高度  
    var popup_height = "400";  
    //弹框宽度  
    var popup_width = "700";  
    //获得窗口的垂直位置  
    var popup_top_dist = (window.screen.availHeight - 30 - popup_height) / 2;  
    //获得窗口的水平位置  
    var popup_left_dist = (window.screen.availWidth - 10 - popup_width) / 2;  
    //打开连接弹框界面  
    winOpen = window.open(popup_url,'newwindow','width='+popup_width+',height='+popup_height+',top='+popup_top_dist+',left='+popup_left_dist+',toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');  
    //检查是否关闭新窗口，1s一次  
    winOpenClosedShow();//window.onload   
    window.setInterval("winOpenClosedShow()",1000);  
	});

/* //弹框关闭
function winOpenClosedShow(){  
    if(!winOpen || winOpen.closed){  
        $("#addcand").attr("disabled",false);  
    }  
}   */
</script>
</head>
<body>
</body>
</html>