<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                All teachers<br>
                <br>
            </p>
        </div>
        <c:if test="${empty teachersDTO}">
            <div class="container has-text-centered">
                <p class="title">
                    Lack of any teachers in database<br>
                    <br>
                </p>
            </div>
        </c:if>

        <c:if test="${not empty teachersDTO}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
                <thead>
                <tr>
                    <th style="width:10%">Num</th>
                    <th style="width:15%">Name <a href="/teachers/all/${page}/ASC/name">▲</a> | <a href="/teachers/all/${page}/DESC/name">▼</a></th>
                    <th style="width:20%">Surname <a href="/teachers/all/${page}/ASC/surname">▲</a> | <a href="/teachers/all/${page}/DESC/surname">▼</a></th>
                    <th>Email</th>
                    <th style="width:10%">Specialization</th>
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
                <tfoot>
                <form method="post" action="/teachers/search">
                    <tr>
                        <th colspan="2">Name:  <input type="text" name="name"></th>
                        <th colspan="2">Surname:  <input type="text" name="surname"></th>
                        <th colspan="2"><button type="submit" class="button button is-primary">Search</button></th>
                    </tr>
                </form>
                </tfoot>
            </table>

            <c:if test="${teachersDTO.size() > 0 }">
                <div class="container">
                    <div class="tabs is-centered">
                        Current page: ${page} out of ${totalPages}
                        <ul>
                            <c:forEach begin="0" end="${totalPages-1}" var="page">
                                <li class="is-centered">
                                    <a href="/teachers/all/${page+1}/${direction}/${field}" class="page-link">${page+1}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>

        </c:if>
        <div class="container has-text-centered">
            <div>
                <a href="/teachers/add">
                    <button type="submit" class="button button is-primary"><br>Add new teacher<br></button>
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../static/footer.jsp"/>