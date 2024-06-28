

// 요소 선택
const loginButton = document.getElementById('login-button');
const modal = document.getElementById('login-modal');
const closeButton = document.querySelector('.close-button');

// 로그인 버튼 클릭 시 모달 표시
loginButton.addEventListener('click', function(event) {
  event.preventDefault(); // 기본 링크 동작 막기
  modal.style.display = 'block';
});

// 닫기 버튼 클릭 시 모달 숨기기
closeButton.addEventListener('click', function() {
  modal.style.display = 'none';
});

// 모달 외부 클릭 시 모달 숨기기
window.addEventListener('click', function(event) {
  if (event.target === modal) {
    modal.style.display = 'none';
  }
});