<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rashmi.util.Mappings" %>

<html>
<head>
    <title>Grocery List Application</title>
</head>
<body>
    <div align="center">
        <c:url var="itemsLink" value="${Mappings.ITEMS}"/>
        <h2><a href="${itemsLink}">Show Grocery Items</a></h2>
    </div>
</body>
</html>