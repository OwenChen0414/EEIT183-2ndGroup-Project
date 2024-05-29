
$(function(){
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
        $('tbody').append(`<tr class="editing">
            <td><input type="text" name="empno" size="10"></td>
            <td><input type="text" name="ename" size="15"></td>
            <td><input type="text" name="hiredate" size="15"></td>
            <td><input type="text" name="salary" size="10"></td>
            <td><input type="text" name="deptno" size="10"></td>
            <td><input type="text" name="title" size="15"></td>
            </tr>`);
            $('form').attr('action','InsertEmp')
    })
        
    $('.update').click(function(){
        hideCRUDButton();
        showSubmit();
        let contain = `<tr class="editing">
            <td><input type="text" name="empno"></td>
            <td><input type="text" name="ename" size="15""></td>
            <td><input type="text" name="hiredate" size="15"}"></td>
            <td><input type="text" name="salary" size="10"}"></td>
            <td><input type="text" name="deptno" size="10"}"></td>
            <td><input type="text" name="title" size="15"}"></td>
            </tr>`;
        $('.selected').hide();
        $('.selected').after(contain);
        $('.selected td').each(function(indx,elem){
            $('.editing td input').eq(indx).val($(this).text());
        })
        $('.editing td input').first().after('<input type="text" name="empnoShow" size="10" disabled>').hide();
        $('.editing td input').eq(1).val($('.selected td').first().text());
        $('form').attr('action','UpdateEmp')
    })

    $('.delete').click(function(){
        hideCRUDButton();
        showSubmit();
        let value = $('.selected td').first().text();
        $('.selected td').first().append('<input type="text" name="empno">');
        $('.selected td input').hide().val(value).addClass('editing');
        $('form').attr('action','DelectEmp')
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