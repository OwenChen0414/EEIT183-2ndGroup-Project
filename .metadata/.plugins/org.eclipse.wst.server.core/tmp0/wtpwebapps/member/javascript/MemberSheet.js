
$(function(){
    setTimeout(() => {
        $('.hint').hide();
    }, 3000);

    $('tbody tr').click(function(){
        if($('.editing').length > 0){
            return;
        }
        if($(this).hasClass('selected')){
            $(this).removeClass('selected');
            hideCRUDButton();
            $('.insert').show();
        } else {
            $('.selected').removeClass('selected');
            $(this).addClass('selected');
            showCRUDButton();
        }
    })
    // $('tbody tr').hover(function(){
    //     $(this).toggleClass('action');
    // })
    
    $('.insert').click(function(){
        hideCRUDButton();
        showSubmit();
        $('.selected').removeClass('selected');
        $('tbody').append(`<tr class="editing">
        <td>${Number($('tbody td').last().text())+1}</td>
        <td><input type="text" name="account" size="5"></td>
        <td><input type="text" name="password" size="5"></td>
        <td><input type="text" name="email" size="10"></td>
        <td>--<input type="text" name="registDate" size="7" style="display: none;"></td>
        <td>--<input type="text" name="lastLogin" size="15" style="display: none;"></td>
        <td><input type="text" name="role" size="7"></td>
        <td>--<input type="text" name="consumption" size="5" style="display: none;"></td>
        <td>--<input type="text" name="level" size="2" style="display: none;"></td>
        <td><input type="text" name="nickName" size="5"></td>
        <td><input type="text" name="memName" size="5"></td>
        <td><input type="date" name="birthday" size="7"></td>
        <td><input type="text" name="phone" size="7"></td>
        <td><input type="text" name="address" size="20"></td>
        <td><input type="text" name="sso" size="2"></td>
        <td><input type="text" name="accomAccount" size="2"></td>
            </tr>`);
            $('form').attr('action','InsertMember')
    })
        
    $('.update').click(function(){
        hideCRUDButton();
        showSubmit();
        let contain = `<tr class="editing">
            <td>--<input type="text" name="id" style="display: none;></td>
            <td><input type="text" name="account" size="5"></td>
            <td><input type="text" name="password" size="5"></td>
            <td><input type="text" name="email" size="10"></td>
            <td><input type="date" name="registDate" size="7"></td>
            <td><input type="text" name="lastLogin" style="display: none;"></td>
            <td><input type="text" name="role" size="7"></td>
            <td><input type="text" name="consumption" size="5"></td>
            <td><input type="text" name="level" style="display: none;"></td>
            <td><input type="text" name="nickName" size="5"></td>
            <td><input type="text" name="memName" size="5"></td>
            <td><input type="date" name="birthday" size="7"></td>
            <td><input type="text" name="phone" size="7"></td>
            <td><input type="text" name="address" size="20"></td>
            <td><input type="text" name="sso" size="2"></td>
            <td><input type="text" name="accomAccount" size="2"></td>
            </tr>`;
        $('.selected').hide();
        $('.selected').after(contain);
        $('.selected td').each(function(indx,elem){
            $('.editing td input').eq(indx).val($(this).text());
            if (indx === 5 || indx === 8) {
                $('.editing td').eq(indx).append($(this).text)
            }
        })
        
        $('form').attr('action','UpdateMember')
    })

    $('.delete').click(function(){
        hideCRUDButton();
        showSubmit();
        let value = $('.selected td').first().text();
        $('.selected td').first().append('<input type="text" name="id">');
        $('.selected td input').hide().val(value).addClass('editing');
        $('form').attr('action','DelectMember')
    })
        
    $('.cancel').click(function(){
        hideSubmit();
        if ($('tr.selected').length > 0) {
            showCRUDButton();
            $('.selected').show();
        } else {
            $('.insert').show();
        }
        $('tr.editing').remove();
        $('form').removeAttr('action');
    })
    
    function showSubmit() {
        $('.submit').show();
        $('.cancel').show();
    }
    function hideSubmit() {
        $('.submit').hide();
        $('.cancel').hide();
    }
    function showCRUDButton() {
        $('.insert').show();
        $('.update').show();
        $('.delete').show();
    }
    function hideCRUDButton() {
        $('.insert').hide();
        $('.update').hide();
        $('.delete').hide();
    }
})