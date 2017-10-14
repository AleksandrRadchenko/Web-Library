<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<style>
    <%@include file='test_css_sign_up.css' %>
</style>
<body>
<form action="#" id='send' method='POST'>
    <fieldset>
        <label>Имя</label>
        <input type='text' name='name' placeholder='Имя' required><span></span>
        <br>
        <label>Фамилия</label>
        <input type='text' name='surname' placeholder='Фамилия' required><span></span>
        <br>
        <label>Город</label>
        <input type='text' name='city' placeholder='Город' required><span></span>
        <br>
    </fieldset>
    <fieldset>
        <label>Эл. почта</label>
        <input type='email' name='email' placeholder='Эл. почта' required><span></span>
        <br>
        <label>Пароль</label>
        <input type='password' name='pass' placeholder='Пароль' required><span></span>
        <br>
        <label>Повторите</label>
        <input type='password' name='repeat' placeholder='Повторите' required><span></span>
        <br>
    </fieldset>
    <fieldset>
        <p>Введите число с картинки</p>
        <img src="https://c22blog.files.wordpress.com/2010/10/input-black.gif">
        <input type='text' name='capcha' placeholder='число' required><span></span>
    </fieldset>
</form>
<div class="sum">
    <input type="submit" value="Зарегестрироваться" form='send'>
</div>

</body>
</html>
