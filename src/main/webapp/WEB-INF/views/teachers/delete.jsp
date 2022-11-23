<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body ">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title" style="marker-offset: 30px">
                Do you want to delete below teacher?
            </p>
        </div>
        <table class="table is-fullwidth is-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${teacherDTO.name}</td>
                <td>${teacherDTO.surname}</td>
            </tr>
            </tbody>
        </table>
        <div class="level-item has-text-centered" style="">
                <form method="post" action="/teachers/delete">
                    <input type="hidden" name="id" value="${teacherDTO.id}"/>
                    <div class="control">
                        <button type="submit" class="button is-link">Yes</button>
                    </div>
                </form>
                <form method="get" action="/teachers/all">
                    <div class="control">
                        <button type="submit" class="button is-link">No</button>
                    </div>
                </form>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>