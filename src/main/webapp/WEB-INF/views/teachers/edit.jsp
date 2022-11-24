<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Edit Teacher
                <br>
                <br>
            </p>
        </div>
        <form:form modelAttribute="teacherDTO" method="post" action="/teachers/add">
        <div class="field">
            <label class="label">Name:</label>
            <div>
                <form:input path="name" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="name"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Surname:</label>
            <div>
                <form:input path="surname" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="surname"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Age:</label>
            <div>
                <form:input path="age" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="age"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Email address:</label>
            <div>
                <form:input path="email" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="email"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Subject:</label>
            <div>
                <form:input path="subject" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="subject"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Students:</label>
            <div>
                <c:forEach var="student" items="${students}" varStatus="loop">
                    <div class="form-group">
                        <label>
                            <input type="checkbox" name="students" value="${student.id}"
                                   <c:if test="${teacherDTO.students.contains(student)}">checked</c:if>>
                            <span>${student.name} ${student.surname}</span>
                        </label>
                    </div>
                </c:forEach>
            </div>
            <br>
            <div>
                <input type="hidden" name="id" value="${teacherDTO.id}"/>
                <input type="submit" class="button is-link" value="Edit"/>
            </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../static/footer.jsp"/>
