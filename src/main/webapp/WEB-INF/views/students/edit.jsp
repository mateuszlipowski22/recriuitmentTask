<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Edit Student
                <br>
                <br>
            </p>
        </div>
        <form:form modelAttribute="studentDTO" method="post" action="/students/edit">
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
            <label class="label">Specialization:</label>
            <div>
                <form:input path="specialization" cssClass="input"></form:input>
            </div>
            <div>
                <form:errors path="specialization"></form:errors>
            </div>
        </div>
        <div class="field">
            <label class="label">Teachers:</label>
            <div>
                <form:select itemValue="id" itemLabel="surname" path="teachers" items="${teachers}"/>
                <div>
                    <form:errors path="teachers"></form:errors>
                </div>
            </div>
            <br>
            <div>
                <input type="hidden" name="id" value="${studentDTO.id}"/>
                <input type="submit" class="button is-link" value="Edit"/>
            </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../static/footer.jsp"/>
