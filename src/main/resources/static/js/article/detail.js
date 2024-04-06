const content = toastui.Editor.factory({
    el: document.querySelector(".content-detail"),
    viewer: true,
    useScript: true,
    initialValue: document.querySelector(".content-detail").innerHTML
});


var imageClicked = true;
let a = 0;
function changeImageAndCount() {
    var img = document.querySelector('.like-thumb');
    if (imageClicked) {
        console.log('????');
        img.src = '/images/recommend/like-true.jpg';
        fetch('/recommend', {
         method: 'GET'

        }).then((response) => {
        });
    } else {
        img.src = '/images/recommend/like-default.jpg';
    }
    imageClicked = !imageClicked;
}

document.querySelector('.delete-button').addEventListener('click', ()=>{
    let articleId = document.getElementById('articleId').value;
    let check = confirm("정말로 삭제하시겠습니까?");
    if (check == false)
        return;
    fetch("/article?id=" + articleId, {
    method: "delete"})
    .then((response)=>{
        if(response.status == 200) { //성공
            let str = response.text().then((res)=>{
                alert("글을 삭제하였습니다");
                location.href = res;
            })
        }
        else if(response.status == 451) { //로그인 안했을 경우
                response.text().then((res)=>{
                    alert(res);
            });
        }
        else if(response.status == 450) { // 유효성 미통과
            response.text().then((res)=>{
                let list = JSON.parse(res);
                console.log(list);
                let error = '';
                list['arrayList'].forEach((e)=>{
                    error += e['message'] + '\n';
                });
                alert(error);
            });
        }
    })
})
