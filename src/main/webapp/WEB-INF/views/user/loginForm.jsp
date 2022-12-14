<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=bd7f51b1f5356c5bdee261995fa6c54e&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code "><img src="../image/kakao_login_btn.png" style="height: 39px"></a>
    </form>

</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>

