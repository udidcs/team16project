// 댓글 생성 기능
function submitReply(button) {
    const articleId = document.getElementById('articleId').value;
    const comment = document.getElementById('comment-input').value;

    fetch(`/reply`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            articleId: articleId,
            comments: comment
        }),
    }).then((response) => {
        // 유효성 검증 로직
        alert('등록 완료되었습니다');
        location.href = '/article?id='+articleId
        // '/article?id='+articleId 로 변경해야함
        // window.location.reload();
    });
}

let str = document.querySelector('.content-detail').innerHTML;
console.log(str);
const content = toastui.Editor.factory({
    el: document.querySelector('.content-detail'),
    viewer: true,
    useScript: true,
    initialValue: str
});

