$(function () {
    $(document).ready(function(){
        $('.venobox').venobox();
    })

    new WOW().init();

    var slider = tns({
        container: '.testimonial-section',
        items: 1,
        slideBy: 'page',
        autoplay: false,
        nav: false,
        controlsText: ['<i class="fa-solid fa-angle-left prev"></i>', '<i class="fa-solid fa-angle-right next"></i>']
    });
});
