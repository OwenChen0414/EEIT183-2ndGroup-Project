<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>刪除文章詳細資料</title>
	<style>
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

        .d1 {
            background-color: rgb(193, 99, 255);
            margin: 30%;
            border-radius: 20px;
        }

        .d2 {
            text-align: center;
            line-height: 25px;
            height: 20px;
            color: #34ff44;
        }

        .d3 {
            text-align: center;
        }

        .d4 {
            text-align: center;
            padding-top: -20px;
            padding-bottom: 20px;
        }


        .d5,
        .d6,
        .d8 {
            flex: 1;
            padding: 20px;
        }

        .d5 {
            text-align: center;
            margin: auto;
            border-bottom-right-radius: 20px;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }


        .d6 {
            text-align: center;
            margin: auto;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

        .d8 {
            text-align: center;
            margin: auto;
            border-bottom-left-radius: 20px;
            border: 5px outset darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }
        
        .d5:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }

        .d6:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }
        
        .d8:hover {
            background-color: gold;
            cursor: pointer;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }

        .d7 {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .d7>div {
            flex: 1;
            text-align: center;
        }

        @media screen and (max-width: 600px) {
            h1 {
                margin: 10%;
            }

            .d2 {
                margin: 15%;
            }

            .d7 {
                flex-direction: column;
                /* 在小型螢幕上將按鈕改為垂直排列 */
            }

            .d7 a {
                margin-bottom: 10px;
                /* 垂直排列時的按鈕間距 */
            }

            .d5,
            .d8 {
                border-radius: 0;
            }

            .d5 {
                margin-bottom: 40px;
            }
        }


        #i1 {
            margin: auto;
            background-color: black;
            color: #34ff44;
            text-align: center;
        }

        #i2 {
            margin: auto;
            border: 10px groove darkorange;
            background-color: hsl(313, 100%, 73%);
            color: #fcfcff;
        }

        #i2:hover {
            background-color: gold;
            color: #eb3427;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4) inset;
            transform: translateY(2px) scale(0.98);
        }
    </style>
    <script>
    function showConfirmation(event) {
        // 顯示確認框
        var confirmation = confirm("確認要刪除嗎？");
        // 如果用戶點擊確認
        if (confirmation) {
            // 如果確認刪除，顯示警告框
            alert("真的真der要刪除嗎~~~");
        } else {
            // 如果取消刪除，顯示提示信息
            alert("取消删除操作！");
            // 取消事件的默認行為，阻止表單提交或鏈接跳轉等
            event.preventDefault();
        }
    }
    </script>
</head>
<body>
<div class="d1">
        <h1>資料安息之地</h1>

        <form method="post" action="/PlayCentric/texts/deleteText">
            <div class="d2">我們懷念它~(輸入文章編號) :</div> <br><br>
            <div class="d3"><input id="i1" type="text" name="textsId" /></div>
            <p>
            <div class="d4"><button type="submit" onclick="showConfirmation(event)" id="i2">さよなら!</button></div>
            <div class="d7">
                <a class="d8" href="addTexts">新增<br>資料</a>
                <a class="d6" href="findTexts">查一<br>資料</a>
                <a class="d5" href="editTexts">更新<br>資料</a>
            </div>
        </form>
    </div>    
</body>
</html>
