document.getElementById("btn-submit").addEventListener("click", function() {
    var newNickname = document.getElementById("nickname").value;

    // AJAX를 사용, 서버에 닉네임 중복 체크 요청.
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/checkNickname", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 성공적으로 응답을 받은 경우
                var response = JSON.parse(xhr.responseText);
                if (response.available) {
                    // 사용 가능한 닉네임인 경우
                    alert("닉네임 변경이 완료되었습니다.");
                } else {
                    // 중복된 닉네임인 경우
                    alert("이미 사용중인 닉네임입니다.");
                }
            } else {
                // 서버에서 오류가 발생한 경우
                console.error("서버 오류 발생");
            }
        }
    };

    var data = JSON.stringify({ "nickname": newNickname });
    xhr.send(data);
});