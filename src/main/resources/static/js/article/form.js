function checkLengthTitle(input) {
    if (input.value.length >= 510) {
        alert('제목의 길이는 50자 이하입니다');
        input.value = input.value.substr(0, 50);
    }
}

function checkLengthEditor(contentEditor) {
    if (contentEditor.getMarkdown().length > 30000) {
        alert('내용의 길이는 3000자 이하입니다');
        contentEditor.setMarkdown(contentEditor.getMarkdown().substr(0, 3000), false)
    }
}

const coteTextCount=document.querySelector('.article-text-count');
const contentEditor = new toastui.Editor({
    el: document.querySelector('.article-content'), // 에디터를 적용할 요소 (컨테이너)
    height: '700px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
    initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    previewStyle: 'vertical', // 마크다운 프리뷰 스타일 (tab || vertical)
    placeholder: '게시 내용을 적어주세요.',
    autofocus: false,
    events: {
        change: function () {
             checkLengthEditor(contentEditor);
        }
    },
    hooks: {
        async addImageBlobHook(blob, callback) {
            try{
                const formData = new FormData();
                formData.append('file', blob);

                const response = await fetch('/article/image/save', {
                    method : 'POST',
                    body : formData,
                });

                const fileName = await response.text();
                const imageUrl = `/article/image?filename=${fileName}`;

                callback(imageUrl, 'image alt attribute');

            } catch (error) {
                console.error('파일 업로드 실패 : ', error);
            }
        }
    }
});

document.querySelector('.submit-button').addEventListener('click', ()=>{
    let title = document.querySelector(".title").value;
    let contents = contentEditor.getMarkdown();

    fetch("/article", {
    method: "post",
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({"title": title,
    "contents": contents})})
    .then((response)=>{
        if(response.status == 200) { //성공
            let str = response.text().then((res)=>{
                alert("글을 작성하였습니다");
                location.href = res;
            })
        }
        else if(response.status == 500) { //로그인 안했을 경우
            alert("로그인이 필요합니다");
        }
        else if(response.status == 400) { // 유효성 미통과
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



