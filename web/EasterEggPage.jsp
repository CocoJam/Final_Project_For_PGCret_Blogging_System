<%--
  Created by IntelliJ IDEA.
  User: jwon117
  Date: 3/06/2017
  Time: 10:28 PM
  Introduction: This is the JSP page which allows a user to access the Pokemon easter egg game. Enjoy!
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pokemon!</title>
    <%@ include file="/component/Header(styling Template).html" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body class="signup-page">

<!-- Navigation bar-->
<%@ include file="/component/Template (HTML components)/NavBar-Login&Registration(Template).html" %>
<!-- Navigation bar ends -->

<%--The game div, good luck!--%>
<div class="wrapper">
    <div class="header header-filter" id="custom-bg-container"><!-- background div -->

        <div class="container">
            <div class="row">
                <div class="col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0">
                    <div class="card card-signup">
                        <iframe src="easteregg/index.html" id="easterEgg"></iframe>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer bar -->
        <%@ include file="/component/Footer(Template).html" %>
        <!-- Footer bar ends -->

    </div>
</div>

</body>
</html>
