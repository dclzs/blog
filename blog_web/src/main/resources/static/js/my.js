//回到顶部按钮
$(window).scroll(function(){
    if ($(window).scrollTop()>100){
        $("#up_btn").show();
    } else {
        $("#up_btn").hide();
    }
});

//当点击跳转链接后，回到页面顶部位置
$("#up_btn").click(function(){
    $('body,html').animate({scrollTop:0}, 200);
    return false;
});