<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../static/header.jsp"/>


<div class="hero-body">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-4 is-vertical is-parent">
                <div class="tile is-child box">
                    <p class="title">
                        Student: <br>${studentDTO.name} ${studentDTO.surname}<br>
                    </p>
                </div>
            </div>
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">Age:</label>
                        <div>
                            ${studentDTO.age}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Email:</label>
                        <div>
                            ${studentDTO.email}
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <c:if test="${empty studentDTO.teachers}">
                <div class="container has-text-centered">
                    <p class="title">
                        Lack of student's teachers<br>
                        <br>
                    </p>
                </div>
            </c:if>

            <c:if test="${not empty studentDTO.teachers}">
                <div class="container has-text-centered">
                    <p class="title">
                        Teachers list<br>
                        <br>
                    </p>
                </div>
                <table class="table is-fullwidth is-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th style="width:10%">Num</th>
                            <th style="width:15%">Name</th>
                            <th style="width:20%">Surname</th>
                            <th>Email</th>
                            <th style="width:10%">Subject</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="teacher" items="${studentDTO.teachers}" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <th>${teacher.name}</th>
                            <th>${teacher.surname}</th>
                            <th>${teacher.email}</th>
                            <th>${teacher.subject}</th>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="container has-text-centered">
                <div>
                    <a href="/students/all/1/ASC/surname">
                        <button type="submit" class="button button is-primary"><br>Show students list<br></button>
                    </a>
                </div>
            </div>
    </div>
</div>

<jsp:include page="../static/footer.jsp"/>