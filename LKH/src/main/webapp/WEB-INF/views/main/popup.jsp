<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용공고</title>
<script>
jQuery(document).ready(function($) {
    pevent();
});
 
function pevent(){
    function getCookie(name){
        var nameOfCookie = name + "=";
        var x = 0;
        while (x <= document.cookie.length){
            var y = (x + nameOfCookie.length);
            if (document.cookie.substring(x, y) == nameOfCookie){
            if ((endOfCookie = document.cookie.indexOf(";", y)) == -1){
            endOfCookie = document.cookie.length;
            }
            return unescape (document.cookie.substring(y, endOfCookie));
            }
            x = document.cookie.indexOf (" ", x) + 1;
            if (x == 0) break;
        }
        return "";
    }
    if (getCookie("popname") != "done"){
        var popUrl = "/pop1";
        var popOption = "width=400%, height=235%, resizable=no, scrollbars=no, status=no;";
        window.open(popUrl,"",popOption);
    }
}
</script>

</head>
<body>
		<img src="/resources/images/popup.png" style="width:100%; height:110%;" >

</body>
</html>