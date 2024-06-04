$(function () {
    $('.menu1 li').mouseenter(function () {
        $(this).children('.menu2').css('visibility', 'visible')
    })
    $('.menu1 li').mouseleave(function () {
        $(this).children('.menu2').css('visibility', 'hidden');
    })
    $('.menu2 li').mouseenter(function () {
        $(this).css('background', 'rgb(202, 235, 247)')
    })
    $('.menu2 li').mouseleave(function () {
        $(this).css('background', '#e8f0ec');
    })
})