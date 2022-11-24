<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Search result<br>
                <br>
            </p>
        </div>
        <c:if test="${empty teachersDTO}">
            <div class="container has-text-centered">
                <p class="title">
                    Lack of search results<br>
                    <br>
                </p>
            </div>
        </c:if>

        <c:if test="${not empty teachersDTO}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
                <thead>
                <tr>
                    <th style="width:10%">Num</th>
                    <th style="width:15%">Name</th>
                    <th style="width:20%">Surname</th>
                    <th>Email</th>
                    <th style="width:10%">Subject</th>
                    <th style="width:20%">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="teacher" items="${teachersDTO}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.surname}</td>
                        <td>${teacher.email}</td>
                        <td>${teacher.subject}</td>
                        <td>
                            <nav class="navbar">
                                <div class="container">
                                    <div class="navbar-menu">
                                        <a href="/teachers/${teacher.id}/show">
                                            <button type="submit" class="button button is-primary">Show</button>
                                        </a>
                                    </div>
                                    <div class="navbar-menu">
                                        <a href="/teachers/${teacher.id}/edit">
                                            <button type="submit" class="button button is-primary">Edit</button>
                                        </a>
                                    </div>
                                    <div class="navbar-menu">
                                        <a href="/teachers/${teacher.id}/delete">
                                            <button type="submit" class="button button is-primary">Delete</button>
                                        </a>
                                    </div>
                                </div>
                            </nav>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <div class="container has-text-centered">
            <div>
                <a href="/teachers/all">
                    <button type="submit" class="button button is-primary"><br>Show all teachers<br></button>
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../static/footer.jsp"/>