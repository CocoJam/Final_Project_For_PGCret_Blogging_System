<%--
  Created by IntelliJ IDEA.
  User: jwon117
  Date: 3/06/2017
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Easter Egg!</title>

    <%@ include file="/component/Template (HTML components)/Header(styling Template).html" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<%@ include file="/component/Template (HTML components)/NavBar-Login&Registration(Template).html" %>
<!-- !!! NAVIGATION BAR END !!! -->

<%--THE GAME!!!--%>
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container"><!-- background div -->

        <div class="container" >
            <div class="row">

                <div class="col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0">
                    <div class="card card-signup">

                        <iframe src="/easteregg/index.html" style="border: 0; width: 100%; height: 50%"> doesnt work
                        </iframe>

                        <%--; -webkit-transform:scale(0.8);-moz-transform-scale(0.8);--%>
                    </div>
                </div>
            </div>
        </div>

        <%--THE GAME ENDS--%>

        <!-- FOOTER START -->
        <%@ include file="/component/Template (HTML components)/Footer(Template).html" %>
        <!-- FOOTER END -->


    </div><!-- background div end -->

</div><!-- Wrapper end -->

</body>
</html>
