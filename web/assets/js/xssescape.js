/**
 * Created by ljam763 on 10/06/2017.
 */
var values = /<script[^]*<\/script>/i;
$("textarea").keyup(function () {
    var content = $(this).val();
    if(content.match(values)){
        $(this).val(content.replace(values, ""));
    }
});
$("input").keyup(function () {
    var content = $(this).val();
    if(content.match(values)){
        $(this).val(content.replace(values, ""));
    }
});