function loginWindow(){
	 //弹框的url  
  var popup_url = _path+"/login/login";  
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
}

 //弹框关闭
function winOpenClosedShow(){  
	  if(!winOpen || winOpen.closed){  
	      $("#loginbtn").attr("disabled",false);  
	  }  
}   
 
function logout(){
	window.location.href=_path+"/login/logout";  
}

function myInfo(){
	window.location.href=_path+"/person/list";  
}

$(function(){
	if( username != '' ){
		$('#loginbtn').hide();
		$('#logoutbtn').show();
		$('#myinfo').show();
	}else{
		$('#loginbtn').show();
		$('#logoutbtn').hide();
		$('#myinfo').hide();
	}
});

function mainToIndex(){
	window.location.href=_path+"/index";
}

function resetKey(){
//	$('#key').val('');
//	$('#value').val('');
var key =	document.getElementById("key");
var value = document.getElementById("value");
key.value = "";
value.value="";
}
