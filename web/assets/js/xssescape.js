/**
 * Created by ljam763 on 10/06/2017.
 */
$(document).ready(function () {
    var values = /<script[^]*<\/script>/i;
    var php= /<( )*?\?( )*?php[^]*>/i;
    $("textarea").keyup(function () {
        var content = $(this).val();
        if(content.match(values)||content.match(php)){
            $(this).val(content.replace(values, ""));
            $(this).val(content.replace(php, ""));
        }
    });
    $("input").keyup(function () {
        var content = $(this).val();
        if(content.match(values)||content.match(php)){
            $(this).val(content.replace(values, ""));
            $(this).val(content.replace(php, ""));
        }
    });
});