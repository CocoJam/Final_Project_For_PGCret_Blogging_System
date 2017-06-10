/**
 * Created by ljam763 on 10/06/2017.
 */
$(document).ready(function () {
    var values = /<script[^]*<\/script>/i;

    $("textarea").keyup(function () {
        console.log("Mathcing textarea");
        var content = $(this).val();
        if (content.match(values)) {
            $(this).val(content.replace(values, ""));
        }
    });
    $("input").keyup(function () {
        console.log("Mathcing input");
        var content = $(this).val();
        if (content.match(values)) {
            $(this).val(content.replace(values, ""));
        }
    });
    console.log($("textarea"));
});