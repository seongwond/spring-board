// 댓글 목록을 가져오는 함수
function fetchComments() {
    fetch('/api/comments/' + boardId)
        .then(response => response.json())
        .then(comments => {
            const commentList = document.getElementById('comment-list');
            commentList.innerHTML = ''; // 기존 댓글 목록 초기화
            comments.forEach(comment => {
                const commentDiv = document.createElement('div');
                commentDiv.className = 'comment';
                commentDiv.innerHTML = `
                    <div class="comment-header">
                        <span>작성자: ${comment.writer}</span>
                        <span>작성일: ${new Date(comment.createdAt).toLocaleString()}</span>
                    </div>
                    <div class="comment-content">${comment.content}</div>
                `;
                commentList.appendChild(commentDiv);
            });
        })
        .catch(error => console.error('댓글을 불러오는 중 오류 발생:', error));
}

// 댓글 등록 버튼 클릭 이벤트
document.getElementById('submit-comment').addEventListener('click', () => {
    const content = document.getElementById('comment-content').value;
    if (content.trim() === '') {
        alert('댓글 내용을 입력하세요.');
        return;
    }

    fetch('/api/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            boardId: boardId,
            content: content
        })
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('comment-content').value = ''; // 폼 초기화
            fetchComments(); // 댓글 목록 새로고침
        } else {
            response.text().then(text => alert('댓글 등록 실패: ' + text));
        }
    })
    .catch(error => console.error('댓글 등록 중 오류 발생:', error));
});

// 페이지 로드 시 댓글 목록을 바로 불러옴
document.addEventListener('DOMContentLoaded', fetchComments);