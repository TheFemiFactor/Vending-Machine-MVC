<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <!--<link href="css/bootstrap.css" rel="stylesheet">-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/VendingMachineStyle.css">
    </head>
    <body>
        <div class="container">
            <h1 id="first" class="original text-center">Vending Machine</h1>
            <ul class="list-group" id="errorMessages"></ul>

            <div class="col-md-7">

                <c:forEach var="currentItem" items="${items}">

                    <div class ="box" >
                        <form id="invForm" role ="form" method="GET" action="getId">

                            <p style="text-align: left" >
                                <c:out value="${currentItem.idOfInventory}"/>
                            </p>
                            <p><c:out value="${currentItem.itemName}"/>
                            </p>
                            <p>$
                                <c:out value="${currentItem.price}"/>
                            </p>                      

                            <button
                                name="id"
                                value="${currentItem.idOfInventory}"
                                type="submit"
                                >
                                Quantity Left: 
                                <c:out value="${currentItem.invAmount}"/>

                            </button>
                        </form>
                    </div>
                </c:forEach>

            </div>


            <div class="col-md-5">


                <form class="text-center" id="formId" role="form" method="POST" action="addDollar" >
                    <h3>Total $ In</h3>

                    <input id="priceEntered" type="text" name="priceEntered" value="${amount}" readonly/>

                    <br>  

                    <button
                        id="dollar-button"
                        class="btn btn-default"
                        name="submitButton"
                        type="submit"
                        value="dollar">                          
                        Add Dollar
                    </button>
                    <button type="submit"
                            id="quarter-button"
                            class="btn btn-default"
                            name="submitButton"
                            value="quarter">
                        Add Quarter
                    </button><br>
                    <button type="sutbmit"
                            id="dime-button"
                            class="btn btn-default"
                            name="submitButton"
                            value="dime">
                        Add Dime
                    </button>
                    <button type="submit"
                            id="nickel-button"
                            class="btn btn-default"
                            name="submitButton"
                            value="nickel">
                        Add Nickel
                    </button>
                </form>


                <form class="text-center" id="formId" role="form" method="POST" action="makePurchase">
                    <h3>Messages</h3>
                    <input id="messages" type="text" name="messages" readonly> <br>
                    Item: 
                    <input id="itemName" value="${id}" type="text" name="item" readonly><br>
                    
                    <button type="submit"
                            id="makePurchase"
                            class="btn btn-default"
                            name="purchaseItem"
                            >
                        
                        Make Purchase
                    </button>
                    
                </form>



                <form form class="text-center" id="formId" role="form" method="POST" action="returnChange">
                    <h3>Change</h3>

                    <input id="messageChange" type="text" name="change" readonly><br>

                    <button type="submti"
                            id="returnChange"
                            name="returnChange"
                            class="btn btn-default">
                        Change Return
                    </button>
                </form>

            </div>


        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>
    </body>
</html>