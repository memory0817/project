// Scroll Move
function scrollMove(t,h,m){
	"use strict";
	if(h==undefined) h=0;
	if(m!=undefined && jQuery(window).width()<993) h=m;
	var o = jQuery('body, html');
	o.animate({
		scrollTop:jQuery(t).offset().top-h
	},500);
}

// ready
jQuery(function($){
	"use strict";
	var w = $(window);
	var $body = $('body');
	var wrap = $('#wrap');
	var snb = $('#snb');
	var view2Body = jQuery('body').not('index').find('#px0');
	var view2Side = jQuery('body').not('index').find('#px0 .media');

	// Parallax
	var bgRatioDefault = 20/15;
	var tD = 100;
	var wW = w.width();
	var hGap = 50;
	// resize
	function pxHeight(){
		// 반응형 처리
		if($('#prHd').length){
			hGap = 0;
		} else if(wW > 1400){
			hGap = 70;
		} else if(wW > 746){
			hGap = 60;
		}
		if (wW>1280) {
			$('.px_h').css('height',w.height()-hGap);
			$('.px_start').each(function(){
				var t = $(this);
				var bgRatio = bgRatioDefault;
				if(t.attr('data-bgRatio')){
					bgRatio = eval(t.attr('data-bgRatio'));
				}
				t.css('top',-Math.abs(bgRatio-1)*100+'%');
			});
		}
		$('#px0 .px_h').css('height',w.height());
		if (wW<1280) {
		}
	}
	pxHeight();
	w.resize(pxHeight);
	// loading;
	$('img').imagesLoaded()
	.done(function(){
		console.log('all images successfully loaded');
		$body.addClass('sub-start');
	})
	.fail(function(){
		console.log('all images loaded, at least one is broken');
		$body.removeClass('overflow');
		$body.addClass('sub-start');
	})
	.progress(function(){

	});
// scroll
	w.scroll(function(){
		var sT = w.scrollTop();
		if(sT>0){
			$body.addClass('if-scroll');
		} else {
			$body.removeClass('if-scroll');
		}
	}).trigger('scroll');

// GNB
	var gnb = $('#gnb'),
		gnbUl =$('.gnb-ul'),
		gLi = gnb.find('li');
	function gnbToggle(){
		var t = $(this);
		var n = t.nextAll('ul');
		if(n.is(':hidden') || n.length==0) {
			t.parent().parent().find('>li>ul').hide();
			n.slideDown(150)
		}
	};
	function gnbOut(){
		$(this).find('>ul').hide();
	};
	gnbUl.hover(
		function  () {
			gnb.addClass('gnb-open');
		},
		function  () {
			gnb.removeClass('gnb-open');
		}
	);
	gLi.find('>a').mouseover(gnbToggle).focus(gnbToggle);
	gLi.mouseleave(gnbOut);
	gnb.find('>li:last-child a:last').blur(function(){
		$(this).parents('.ul3,.ul2').hide()
	});

	function scrollSection(){
		wrap.find('section.px_sect').each(function(idx){
			var t = $(this);
			var tT = t.offset().top;
			var tH = t.innerHeight();
			var sT = w.scrollTop();
			var startPos2 = view2Body.offset().top;
			var wH = w.height();
			var vH2 = view2Side.height() + 70;

			if(t.attr('data-delay')){
				tD = t.attr('data-delay');
			}
			// intro animation
			if(tT-wH<sT-tD && tT+tH>sT){
				t.find('.animated').removeClass('ani_stop');
				t.addClass('section_start');
			} else {
				t.find('.animated').addClass('ani_stop');
				t.removeClass('section_start');
			}
			// snb
			if(tT-wH/2<sT && tT+tH-wH/2>sT){
				var t = snb.find('a').eq(idx);
				t.addClass('active').parent().siblings().find('a').removeClass('active');
				t.hasClass('bg_blk')?wrap.addClass('snb_blk'):wrap.removeClass('snb_blk');
			}
			// bg
			if(w.width()<769){
				return;
			}
			if(tT-wH<sT && tT+tH>sT){
				t.find('.px_start').each(function(){
					var bgRatio = bgRatioDefault;
					var t2 = $(this);
					if(t2.attr('data-bgRatio')){
						bgRatio = eval(t2.attr('data-bgRatio'));
					}
					var sV = Math.round((tH*Math.abs(bgRatio-1))*(sT-tT+wH)/(tH+wH));
					$(this).css({
						'-webkit-transform':'translate(0,'+sV+'px)',
						'transform':'translate(0,'+sV+'px)'
					});
				});
			}
		});
	}
	w.on("scroll",function(){
		scrollSection();
	});
	scrollSection();
	wrap.find('#snb a,a.scroll_move').on('click',function(){
		if( $('#prHd').length){
			scrollMove($(this).attr('href'),0);
		} else {
			scrollMove($(this).attr('href'),70);
		}
		return false;
	});

// enter with #
	var hashUrl = String(window.location).split('#')[1];
	if(hashUrl!=undefined){
		setTimeout(function(){
			scrollMove('#'+hashUrl,70,50);
		},500);
	}

// view
	$('a[data-url]').on('click',function(){
		var url = $(this).attr('href');
		$.ajax({
			url:url,
			success:function(data){
				var o = $(data).find('div.board_read');
				$('#viewTarget h1').html(o.find('h1 a:last-child').text());
				$('#viewDate').html(o.find('.time').text());
				$('#viewEx').html(o.find('div.exOut'));
				$('#viewEx th').each(function(){
					if($(this).text()==='주소'){
						$(this).parent().remove();
					}
				});
				$('#viewContent').html(o.find('div.xe_content'));
				$('#mdView').modal();
			}
		});
		return false;
	});

// FAQ
	$('.lst-faq .q').on('click',function(){
		var t = $(this);
		var url = t.attr('href');
		$.ajax({
			url:url,
			success:function(data){
				t.parent().toggleClass('open').children('.inner').slideToggle().parent('li').siblings().removeClass('open').children('.inner').slideUp();
				if(t.next().text().length){
					return;
				}
				t.next().html($(data).find('div.xe_content'));
			}
		});
		return false;
	});

// view
	$('a.view_modal').click(function(){
		jQuery.ajax({
			url:$(this).attr('href'),
			success:function(data){
				$('#view').modal('show').find('.modal-body').html(jQuery(data).find('div.rd'));
			}
		});
		return false;
	});
});
