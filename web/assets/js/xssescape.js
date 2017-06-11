/**
 * Created by ljam763 on 10/06/2017.
 */
$(document).ready(function () {
    var values = /<script[^]*<\/script>/g;
    var php = /<( )*?\?( )*?php[^]*>/i;
    $("textarea").keyup(function () {
        var content = $(this).val();

        if (content.match(php)) {
            console.log("matched the php");
            $(this).val(content.replace(php, ""));
        }
        else if (content.match(values)) {
            console.log("matched the script")
            $(this).val(content.replace(values, ""));
        }
    });
    $("input").keyup(function () {
        var content = $(this).val();
        if (content.match(values)) {
            console.log("matched the script")
            $(this).val(content.replace(values, ""));
        }
        else if (content.match(php)) {
            console.log("matched the php")
            $(this).val(content.replace(php, ""));
        }
    });
});