// **주의: 이 파일에서 'const boardId = ...'와 'const contextPath = ...' 코드를 삭제해야 합니다.**

// 댓글 목록 조회, 수정, 삭제를 위한 비동기 함수들을 포함하는 파일입니다.
// 이 파일은 detail.jsp에서 선언된 'boardId', 'contextPath', 'loginMemberUsername' 변수를 사용합니다.

// 댓글 목록을 가져와서 화면에 표시하는 함수
function fetchComments() {
    fetch(contextPath + '/api/comments/' + boardId)
        .then(response => response.json())
        .then(comments => {
            const commentList = document.getElementById('comment-list');
            commentList.innerHTML = ''; // 기존 댓글 목록 초기화
            comments.forEach(comment => {
                const commentDiv = document.createElement('div');
                commentDiv.className = 'comment';
                
                let buttonsHtml = '';
                // 로그인한 사용자가 댓글 작성자와 일치할 경우 수정/삭제 버튼을 추가
                if (loginMemberUsername && loginMemberUsername === comment.writer) {
                    buttonsHtml = `<div class="comment-actions">
                        <button onclick="editComment(${comment.commentId})">수정</button>
                        <button onclick="deleteComment(${comment.commentId})">삭제</button>
                    </div>`;
                }
                
                commentDiv.innerHTML = `
                    <div class="comment-header">
                        <span>작성자: ${comment.writer}</span>
                        <span>작성일: ${new Date(comment.createdAt).toLocaleString()}</span>
                        ${buttonsHtml}
                    </div>
                    <div class="comment-content">${comment.content}</div>
                `;
                commentList.appendChild(commentDiv);
            });
        })
        .catch(error => console.error('댓글 불러오기 오류:', error));
}

// 댓글 수정 기능
function editComment(commentId) {
    const newContent = prompt('수정할 내용을 입력하세요:');
    if (newContent) {
        fetch(contextPath + `/api/comments/${commentId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ content: newContent })
        })
        .then(response => {
            if (response.ok) {
                fetchComments(); // 댓글 목록 새로고침
            } else {
                response.text().then(text => alert('수정 실패: ' + text));
            }
        });
    }
}

// 댓글 삭제 기능
function deleteComment(commentId) {
    if (confirm('정말 삭제하시겠습니까?')) {
        fetch(contextPath + `/api/comments/${commentId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                fetchComments(); // 댓글 목록 새로고침
            } else {
                response.text().then(text => alert('삭제 실패: ' + text));
            }
        });
    }
}

// 댓글 등록 버튼 클릭 이벤트
document.getElementById('submit-comment').addEventListener('click', () => {
    const content = document.getElementById('comment-content').value;
    if (content.trim() === '') {
        alert('댓글 내용을 입력하세요.');
        return;
    }

    fetch(contextPath + '/api/comments', {
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