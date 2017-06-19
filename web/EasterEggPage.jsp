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
    <title>Slash N - Easter Egg!</title>

    <%@ include file="/component/Header(styling Template).html" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="signup-page">

<!-- !!! NAVIGATION BAR START !!! -->
<c:choose>
    <c:when test="${log}">
        <%@ include file="/component/NavBar-AfterLogin(Template).jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/component/NavBar-Login&Registration(Template).jsp" %>
    </c:otherwise>
</c:choose>
<!-- !!! NAVIGATION BAR END !!! -->

<%--THE GAME!!!--%>
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container"><!-- background div -->

        <div class="container" >
            <div class="row">

                <div class="col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0">
                    <div class="card card-signup">

                        <iframe src="/easteregg/index.html" style="border: 0; width: 100%; height: 50%"> Your browser does not support iframe.
                        </iframe>

                        <%--; -webkit-transform:scale(0.8);-moz-transform-scale(0.8);--%>
                    </div>
                </div>
            </div>
        </div>

        <%--THE GAME ENDS--%>

        <!-- FOOTER START -->
        <%@ include file="/component/Footer(Template).html" %>
        <!-- FOOTER END -->


    </div><!-- background div end -->

</div><!-- Wrapper end -->

</body>
</html>
