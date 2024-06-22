function validateSelectGameForm() {
    let selectGame = document.getElementById("SelectGame");
    if (selectGame.value === "") {
        alert("請選擇一個遊戲");
        return false;
    }
    return true;
}


