/**
 * Created by Mr.D on 2017/6/7.
 */

$('.lazy').lazyload({threshold: 200});
var player='';
$(function () {
    exposure._init();

    var videoNum = 0 || count.video;
    var teacherNum = 0 || count.teacher;
    var userNum = 0 || count.user;
    var csrfToken = $('meta[name="csrf-token"]').attr("content");
    //数字变换和滚动事件
    var options = {
        useEasing: true,
        useGrouping: true,
        separator: '',
        decimal: '.',
        prefix: '',
        suffix: ''
    };
    var countUp1 = new CountUp("videoNumSpan", 0, videoNum, 0, 2.5, options);
    var countUp2 = new CountUp("teacherNumSpan", 0, teacherNum, 0, 2.5, options);
    var countUp3 = new CountUp("userNumspan", 0, userNum, 0, 2.5, options);

    $(window).scroll(function () {
        //数字开始滚动
        if ($(window).scrollTop() >= jQuery("#videoNumSpan").offset().top - jQuery(window).height()) {
            countUp1.start();
            countUp2.start();
            countUp3.start();
        }
    });

    //轮播
    $("#slide-box").slide({mainCell: ".bd ul", autoPlay: true, trigger: "click", interTime: 4000});

    //banner点击计数
    var banner_a_click = $('a[data-bind="banner-a-click"]');
    banner_a_click.bind('click', function () {

        var id = $(this).attr('data-id');

        $.ajax({
            url: Url.bannerClickStatisticsUrl,
            data: {id: id},
            type: 'Post',
            dataType: 'JSON',
            success: function ($res) {
            }
        });

        if ($(this).attr('data-auth') == 1 && Param.isLogin != 1) {
            toLogin();
            return false;
        } else {
            var data_url = $(this).attr('data-url');
            if (id == 47) {
                buttonClickRecord(12);
            }
            window.open(data_url);
        }
    });

    /**
     * 点击标签 切换对应模块内容
     */
    $('ul.tit-nav').find('li').each(function () {
        var $url = $(this).attr('data-bind-url'); // 每个模块对应的 更多按钮和查看更多按钮的链接
        $(this).bind('click', function () {
            if ($(this).hasClass('on')) { // 如果该模块已经选中 则不处理后续操作
                return false;
            }

            var moduleId = $(this).attr('data-module-id');                     // 模块ID
            var cont = $(this).parents('.main-wrap').find('.flow-list').eq(0); // 改变内容的div 只改变第一个模块内容 避免空div填充内容时样式问题
            var viewMore = $(this).parents('.main-wrap').find('.view-more a'); // 改变链接的div 查看更多
            var more = $(this).parents('.main-wrap').find('.pt-right a');      // 改变链接的div 更多

            // 点击选中
            $(this).addClass('on').siblings('li').removeClass('on');

            // ajax更新对应视频列表
            $.ajax({
                url: Url.getVideoList,
                type: 'post',
                data: {
                    '_csrf-frontend': csrfToken,
                    'module_id': moduleId
                },
                success: function (res) {
                    var $res = JSON.parse(res);

                    // 查看更多按钮链接改变
                    $(viewMore).attr('href', $url);
                    $(more).attr('href', $url);

                    // 改变视频列表
                    $(cont).html($res.list);
                    $('.lazy').lazyload({threshold: 200});
                    exposure.ids = $res.ids;
                }
            });
        });
    });

    /**
     * 点击公告记录进cookie
     */
    $('.new-tips .record').click(function () {
        var advertId = $('.new-tips .record').attr('data-id');
        $.ajax({
            url:Url.recordNotice,
            type: 'post',
            data:{
                'advertId':advertId,
                '_csrf-frontend': csrfToken
            },
            success:function (data) {
                $('.new-tips').addClass('hide');
            }
        })
    });


    /**
     * 拼团关闭按钮
     */
    $('.down-money').on('click', '.close', function() {
        $(".down-money").addClass('hide');

        $.ajax({
            url:Url.groupBuyCookie,
            type: 'post',
            data:{'type': 2, '_csrf-frontend': csrfToken},
            success:function (data) {}
        });
    });

    /**
     * 拼团跳转按钮
     */
    $(".down-money a").on('click', function () {
        $.ajax({
            url:Url.groupBuyCookie,
            type: 'post',
            data:{'type': 1, '_csrf-frontend': csrfToken},
            success:function (data) {}
        });
    });

    // 广告抽屉动画
    $('.hand-once .hob-close-btn-ad').on('click', function () {
        var n = 1;
        var uom = new Date(new Date()-0+n*86400000);
        document.cookie="homePageIsShow=1;expires=" + uom.toGMTString();

        $('.hand-once').stop().animate({
            left: "0"
        }, 600, function () {
            $('.hand-once .hand-once-bar-ad').stop().animate({
                left: '0'
            }, 300).show();
            $(".hand-once").css('width', '127px');
        });
    })

    function showdate(n)
    {
        var uom = new Date(new Date()-0+n*86400000);
        uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate();
        return uom;
    }

    // 讲师切换
    var clone = $('#teacher-slide').clone()
    $('#teacher-slide').slide({
        mainCell:".slide-m",
        effect:"leftLoop",
        autoPage:true,
        prevCell:".prev-btn",
        nextCell:".next-btn",
        autoPlay:false,
        vis:4,
        startFun:function(){
            $('.teacher-slide img.lazy').lazyload()
        }
    });
    $(window).resize(function() {
        $('#teacher-slide').remove();
        $('.teacher-con').append(clone);
        $('#teacher-slide').slide({
            mainCell:".slide-m",
            effect:"leftLoop",
            autoPage:true,
            prevCell:".prev-btn",
            nextCell:".next-btn",
            autoPlay:false,
            vis:4,
            startFun:function(){
                $('.teacher-slide img.lazy').lazyload()
            }
        });
    });

    // 首页学习进度加载动画
    function c1(){var slots={},c=document.getElementById('loadingProgress1'),ctx=c.getContext('2d');hasLoaded=0;loading=false;var num=parseInt($('#loadedNum1').text())+1;ulp(num);function ulp(percent){loading=true;var i=0,draw=null;draw=setInterval(function(){if(hasLoaded>100){loading=false;clearInterval(draw);draw=null;return true}if(i<percent){d();i++;hasLoaded+=1}else{clearInterval(draw);draw=null}},20)}function d(){var lp=document.getElementById('loadedNum1');lp.innerHTML=hasLoaded;var loaded=hasLoaded*2/100*Math.PI,cw=120,hcw=60;ctx.clearRect(0,0,cw,cw);ctx.beginPath();ctx.arc(hcw,hcw,hcw-6,0,loaded,false);ctx.lineWidth=12;ctx.strokeStyle='#ffd500';ctx.lineCap="round";ctx.stroke()}}
    if ($('#loadedNum1').length) {
        c1();
    }
    function c2(){var slots={},cx=document.getElementById('loadingProgress2'),ctxx=cx.getContext('2d');hasLoaded2=0;loading2=false;var num2=parseInt($('#loadedNum2').text())+1;ulp2(num2);function ulp2(percentw){loading=true;var i=0,draw=null;draw=setInterval(function(){if(hasLoaded>100){loading2=false;clearInterval(draw);draw=null;return true}if(i<percentw){d();i++;hasLoaded2+=1}else{clearInterval(draw);draw=null}},20)}function d(){var lp2=document.getElementById('loadedNum2');lp2.innerHTML=hasLoaded2;var loaded=hasLoaded2*2/100*Math.PI,cw=120,hcw=60;ctxx.clearRect(0,0,cw,cw);ctxx.beginPath();ctxx.arc(hcw,hcw,hcw-6,0,loaded,false);ctxx.lineWidth=12;ctxx.lineJoin="round";ctxx.strokeStyle='#ffd500';ctxx.lineCap="round";ctxx.stroke()}}
    if ($('#loadedNum2').length) {
        c2();
    }
    
    // 点击弹出视频播放弹窗
    $('.banner-cover').on('click', '.conduct-img', function() {
        if( !player ){
        	player =  new TcPlayer('video-container', {
                "mp4": "https://static/video/propaganda.mp4",
                "autoplay" : false,
                "width" :  '770',//视频的显示宽度，请尽量使用视频分辨率宽度
                "height" : '430'//视频的显示高度，请尽量使用视频分辨率高度
            });
        }
        $('.video-box-win').removeClass('hide');
        player.play();
    });
    // 点击关闭视频播放弹窗
    $('.video-box-win').on('click', '.close,.win-bg', function() {
        $('.video-box-win').addClass('hide');
        player.pause();
    });

    // 快进快退 音量加减
    function time(t){
        var nowTime = player.currentTime();
        player.currentTime(nowTime + t);
    }
    function volumes(v){
        var nowVolume = player.volume();
        player.volume(nowVolume + v);
    }
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0],
            hasKlass = $('.video-box-win').hasClass('hide');
        if ( hasKlass == false ) {
            if(e && e.keyCode==39){
                time(5);//快进5秒
            }
            if(e && e.keyCode==37){
                time(-5);//快退5秒
            }
            if(e && e.keyCode==38){
                volumes(0.05);//音量加5%
            }
            if(e && e.keyCode==40){
                volumes(-0.05);//音量减5%
            }
        }
    };
});