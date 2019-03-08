// Scroll Move
function scrollMove(t,h,m){
	"use strict";
	if(h==undefined) h=0;
	if(m!=undefined && jQuery(window).width()<993) h=m;
	var o = jQuery('html, body');
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

// Parallax
	var bgRatioDefault = 20/15;
	var tD = 100;
	var wW = w.width();
	var hGap = 50;

	// disable scroll
	function disableScroll(){
		w.on("mousewheel.disableScroll DOMMouseScroll.disableScroll touchmove.disableScroll", function(e) {
			e.preventDefault();
			return;
		});
	}
	function eableScroll(){
		w.off(".disableScroll");
	}
	if($('.sct-panel').length && w.width()>1280 && w.height()>768){
		function move () {
			w.on("mousewheel DOMMouseScroll",function(e){
				if($("html,body").is(":animated")){
					return false;
				}
				var t = $('.last-panel');
				var tT = t.offset().top;
				var sT = w.scrollTop();
				if(tT-70<=sT+hGap){
					eableScroll();
					return;
				} else {
					disableScroll();
				}
				var evt=e.originalEvent.wheelDelta || e.originalEvent.detail*-1;
				console.log(evt);
				if(evt<0){
					$('#snb li a.active').parent('li').next().children('a').click();
				} else {
					$('#snb li a.active').parent('li').prev().children('a').click();
				}
			});
		}
		move();
		disableScroll();
		$('.modal').on({
			'show.bs.modal':function  () {
				eableScroll();
				w.off("mousewheel DOMMouseScroll touchstart touchmove touchend");
			},
			'hidden.bs.modal':function  () {
				move();
			}
		});
	}
});
