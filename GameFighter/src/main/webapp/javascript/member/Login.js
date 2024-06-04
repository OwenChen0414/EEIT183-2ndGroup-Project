$(function(){
    setTimeout(() => {
        $('.fail').hide();
    }, 3000);

    if($('#account').val() != ''){
        $('#password').focus();
    }
})