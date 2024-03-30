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
        location.href = '/article/detail?id='+articleId
        // '/article?id='+articleId 로 변경해야함
        // window.location.reload();
    });
}
