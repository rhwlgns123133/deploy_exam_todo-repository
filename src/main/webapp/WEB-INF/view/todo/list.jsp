<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-10-13
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>고지훈</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<body>
<div class="container-fluid">
  <div class="row">
    <!-- 기존의 <h1>Header</h1> -->
    <div class="row">
      <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
                <a class="nav-link" href="#">Features</a>
                <a class="nav-link" href="#">Pricing</a>
                <a class="nav-link disabled">Disabled</a>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
    <!-- header end -->

    <!-- 추가 코드-->
    <div class="row content">
      <div class="col">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Search</h5>
            <form action="/todo/list" method="get">
              <input type="hidden" name="size" value="${pageRequestDTO.size}">
              <div class="mb-3">
                <input type="checkbox" name="finished"${pageRequestDTO.finished ? "checked" : ""}>완료여부
              </div>
              <div class="mb-3">
                <input type="checkbox" name="types" value="t"${pageRequestDTO.checkType("t") ? " checked" : ""}>제목
                <input type="checkbox" name="types" value="w"${pageRequestDTO.checkType("w") ? " checked" : ""}>작성자
                <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
              </div>
              <div class="input-group mb-3 dueDateDiv">
                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
              </div>
              <div class="input-group mb-3">
                <div class="float-end">
                  <button class="btn btn-primary" type="submit">Search</button>
                  <button class="btn btn-info clearBtn" type="reset">Clear</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!--추가된 코드 끝-->
    <!-- 기존의 <h1>Header</h1>끝 -->
    <div class="row content">
      <div class="col">
        <div class="card">
          <div class="card-header">
            Featured
          </div>
          <div class="card-body">
            <h5 class="card-title">Special title treatment</h5>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Tno</th>
                <th scope="col">Title</th>
                <th scope="col">Writer</th>
                <th scope="col">DueDate</th>
                <th scope="col">Finished</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="dto" items="${responseDTO.dtoList}" >
                <tr>
                  <th scope="row">${dto.tno}</th>
                  <th><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none" data-tno="${dto.tno}">
                  <c:out value="${dto.title}"/>
                  </a></th>
                  <th>${dto.writer}</th>
                  <th>${dto.dueDate}</th>
                  <th>${dto.finished}</th>
                </tr>
              </c:forEach>
              </tbody>
            </table>
            <div class="float-end">
              <ul class="pagination flex-wrap">
                <c:if test="${responseDTO.prev}">
                  <li class="page-item">
                    <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                  </li>
                </c:if>
                <c:forEach var="num" begin="${responseDTO.start}" end="${responseDTO.end}">
                  <li class="page-item ${responseDTO.page == num ? "active" : ""}">
                    <a class="page-link" data-num="${num}"> ${num} </a>
                  </li>
                </c:forEach>
                <c:if test="${responseDTO.next}">
                  <li class="page-item">
                    <a class="page-link" data-num="${responseDTO.start + 1}">Next</a>
                  </li>
                </c:if>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row content">
    <h1>Content</h1>
  </div>
  <div class="row footer">
    <!--        <h1>Footer</h1>-->
    <div class="row fixed-bottom" style="z-index: -100">
      <footer class="py-1 my-1 ">
        <p class="text-center text-muted">Footer</p>
      </footer>
    </div>
  </div>
</div>
<script>
  document.querySelector('.pagination').addEventListener('click',function (e) {
    e.preventDefault();
    e.stopPropagation();

    const target = e.target;
    if (target.tagName !== 'A') {
      return
    }
    const num = target.getAttribute('data-num');
    const frmPage = document.querySelector('form');
    frmPage.innerHTML += `<input type="hidden" name="page" value="\${num}">`;
    frmPage.submit();
    //self.location = `/todo/list?page=\${num}`;//백틱 템플릿처리
  });
</script>
<script>
  document.querySelector('.clearBtn').addEventListener('click', function (e){
    self.location = '/todo/list';
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>