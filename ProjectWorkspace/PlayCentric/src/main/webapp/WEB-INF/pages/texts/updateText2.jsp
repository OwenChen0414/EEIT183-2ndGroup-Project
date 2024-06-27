<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>更新文章詳細資料</title>
	<style type="text/css">
	@font-face {
            font-family: 'PixelFont';
            src: url('/PlayCentric/fonts/ark-pixel-12px-monospaced-zh_tw.ttf') format('truetype');
        }
	body {
            font-family: 'PixelFont', Arial, sans-serif;
            background-image: url('/PlayCentric/images/texts/original.gif');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #34ff44;
            text-align: center;
            margin-top: 20%;
            padding-top: 10px;
            padding-bottom: 10px;
            border-bottom: 10px groove darkorange;
        }


        a {
            color: white;
            text-decoration: none;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            width: 100%;
        }

        .form-group label {
            color: #34ff44;
            width: 200px;
            text-align: left;
            margin-left: 4%;
            margin-right: -2%;
        }

        .form-group input {
            flex: 1;
            width: 20px;
            margin-right: 5%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: black;
            color: #34ff44;
        }

        .form-group textarea {
            flex: 1;
            margin-right: 5%;
            margin-left: auto;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: black;
            color: #34ff44;
            resize: none;
            font-size: 18px;
        }


        #d0 {
            background-color: rgb(193, 99, 255);
            margin: 20%;
            border-radius: 20px;
        }
        
         #t4{
           display: flex;
           margin-left: -20%;
        }

        #t11 {
            margin-left: 30%;
            width: 40%;
            height: 50px;
            text-align: center;
            border: 10px groove darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }


        #t11:hover {
            background-color: gold;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }

        #d13,
        #d14,
        #d15 {
            flex: 1;
            padding: 20px;
        }

        #d12 {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        #d12>div {
            flex: 1;
            text-align: center;
        }

        #d13 {
            text-align: center;
            margin: auto;
            border-bottom-left-radius: 20px;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

        #d14 {
            text-align: center;
            margin: auto;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

        #d15 {
            text-align: center;
            margin: auto;
            border-bottom-right-radius: 20px;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

        #d13:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }

        #d14:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }
        
        #d15:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }

        @media screen and (max-width: 600px) {
            h1 {
                margin: 10%;
            }

            #t7 {
                resize: none;
                height: 15px;
                width: 30%;
            }

            #t8 {
                resize: none;
                height: 100px;
                width: 30%;
            }

            #d12 {
                flex-direction: column;
            }

            #d12 a {
                margin-bottom: 10px;
            }

            #d15,
            #d13 {
                border-radius: 0;
            }

            #d15 {
                margin-bottom: 40px;
            }
        }
	</style>
</head>
<body>
 <div id="d0">
        <h1>更新文章詳細資料</h1>
        <form method="post" action="/PlayCentric/texts/updateTexts">
             <div id="d2" class="form-group">
                <label for="textsId">輸入文章編號 :</label>
                <input id="t2" type="text" name="textsId" />
            </div>
            <p>
            <div id="d3" class="form-group">
                <label for="forumId">輸入討論區編號 :</label>
                <input id="t6" type="text" name="forumId" />
            </div>
            <p>
            <div id="d4" class="form-group">
                <label for="memId">輸入作者(會員)編號 :</label>
                <input id="t3" type="text" name="memId" />
            </div>
            <p>
            <div id="d5" class="form-group">
                <label for="title">輸入標題 :</label>
                <textarea id="t7" name="title"></textarea>
            </div>
            <p>
            <div id="d6" class="form-group">
                <label for="textsContent">輸入內文 :</label>
                <textarea id="t8" name="textsContent" cols="50" rows="10"></textarea>
            </div>
            <p>
            <div id="d7" class="form-group">
                <label for="doneTime">創作時間 :</label>
                <input id="t10" type="datetime-local" name="doneTime" />
            </div>
            <p>
            <div id="d8" class="form-group">
                <label for="updatedTime">最後編輯時間:</label>
                <input id="t9" type="datetime-local" name="updatedTime" />
            </div>
            <p>
            <div id="d9" class="form-group">
                <label for="textsLikeNum">按讚數 :</label>
                <input id="t5" type="text" name="textsLikeNum" />
            </div>
            <p>
            <div id="d10" class="form-group" style="color: #34ff44">
                <label for="hideTexts">文章是否受隱藏 :</label>
                Y<input  type="radio" id="t4" value="true" name="hideTexts" >
                N<input type="radio" id="t4" name="hideTexts" value="false" checked>
            </div>
            <p>
            <div id="d11">
                <input id="t11" class="submit-btn" type="submit" value="一鍵加入" />
            </div>
            <div id="d12">
                <a id="d13" href="addTexts">新增<br>資料</a>
                <a id="d14" href="destroyTexts">刪除<br>資料</a>
                <a id="d15" href="findTexts">查一<br>資料</a>
            </div>
        </form>
    </div>
</body>
</html>
