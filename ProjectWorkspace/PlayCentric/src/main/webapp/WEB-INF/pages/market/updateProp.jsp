<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>修改道具</title> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/market/create.css" />
  </head>
  <body>
    <h2 class="center">修改道具</h2>
    <form
      method="post"
      action="${pageContext.request.contextPath}/updateProp"
      class="center"
      enctype="multipart/form-data"
    >
	  <input type="hidden"  name="propId" value="${propId}"/> 
      <label for="propName">名稱 :</label>
      <input type="text" name="propName" id="propName" value="${propName}" required/>
      <br />
      <label for="propType">種類 :</label>
      <input type="text" name="propType" id="propType"  value="${propType}" required/>
      <label for="propRarity">稀有度 :</label>
      <input type="text" name="propRarity" id="propRarity" value="${propRarity}" required/>
      <p>
        <label for="propDescription">描述 :</label>
        <textarea
          name="propDescription"
          id="propDescription"
          rows="2"
          cols="30"
          required
        >${propDescription}</textarea>
      </p>
      <input type="hidden"  name="gameId" value="${gameId}" required/> 
      <input type="hidden"  name="defaultPropImageName" value="${propImageName}" required/> 
      <label for="propImageName">上傳圖片 :</label>
      <input type="file" name="propImageName" id="propImageName" required/>
      <input type="submit" value="確定修改" />
    </form>
  </body>
</html>
