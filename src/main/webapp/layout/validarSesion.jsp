<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
    // Verificar si la sesión está activa y si el usuario está logueado
    if (request.getSession(false) == null || request.getSession(false).getAttribute("username") == null) {
        // Si la sesión no está activa o el atributo 'username' no existe, redirigir al login
        response.sendRedirect("login.jsp");
        return;  // Evitar que el resto del código se ejecute
    }
%>
