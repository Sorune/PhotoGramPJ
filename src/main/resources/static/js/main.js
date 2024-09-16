// DOMContentLoaded 이벤트 리스너를 추가하여 문서 로딩 완료 시 실행
window.addEventListener('load', () => {
  AOS.init(); // Initialize AOS
});


document.addEventListener("DOMContentLoaded", function() {
  // userName 요소를 가져옴
  const userNameElement = document.getElementById('userName');
  console.log(userNameElement);
  // userName이 존재하고 비어 있지 않은 경우
  if (userNameElement && userNameElement.innerText.trim() !== '') {
    // 로컬 스토리지에서 accessToken을 가져옴
    const accessToken = localStorage.getItem('accessToken');

    // accessToken이 존재하는 경우
    if (accessToken) {
      // 사용자 정보를 가져오는 API 호출
      fetch('/api/userinfo', {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      })
          .then(response => {
            // 응답이 성공적이지 않은 경우 오류 처리
            if (!response.ok) {
              throw new Error('Unauthorized');
            }
            return response.json();
          })
          .then(data => {
            console.log(data);
            // 사용자 정보를 UI에 업데이트
            const userName = document.getElementById('userName');
            const userImage = document.getElementById('userImage');

            if (userName && userImage) {
              userName.innerText = data.nickName;
              userImage.src = data.profileImage;
            }
          })
          .catch(error => {
            // 인증 오류 발생 시 처리
            if (error.message === 'Unauthorized') {
              // 사용자에게 로그인 필요 메시지 표시
              const confirmed = confirm('로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?');
              if (confirmed) {
                // 로그인 페이지로 리디렉션 및 accessToken 제거
                window.location.href = '/login';
                localStorage.removeItem('accessToken');
              } else {
                // 홈 페이지로 리디렉션
                window.location.href = '/home';
              }
            } else {
              // 기타 오류 발생 시 콘솔에 오류 로그 출력
              console.error('Error fetching user info:', error);
            }
          });
    }
  }
});

const select = (el, all = false) => {
  el = el.trim()
  if (all) {
    return [...document.querySelectorAll(el)]
  } else {
    return document.querySelector(el)
  }
}

  const on = (type, el, listener, all = false) => {
  let selectEl = select(el, all)
  if (selectEl) {
    if (all) {
      selectEl.forEach(e => e.addEventListener(type, listener))
    } else {
      selectEl.addEventListener(type, listener)
    }
  }
}
 /**
 * Imgcell isotope and filter
 */
window.addEventListener('load', () => {
  let imgcellContainer = select('.imgcell-container');
  if (imgcellContainer) {
    let imgcellIsotope = new Isotope(imgcellContainer, {
      itemSelector: '.imgcell-item'
    });

    let imgcellFilters = select('#imgcell-filters li', true);

    on('click', '#imgcell-filters li', function(e) {
      e.preventDefault();
      imgcellFilters.forEach(function(el) {
        el.classList.remove('filter-active');
      });
      this.classList.add('filter-active');

      imgcellIsotope.arrange({
        filter: this.getAttribute('data-filter')
      });
      imgcellIsotope.on('arrangeComplete', function() {
        AOS.refresh()
      });
    }, true);
  }

});

/**
 * Initiate imgcell lightbox
 */
const imgcellLightbox = GLightbox({
  selector: '.imgcell-lightbox'
});

/**
 * imgcell details slider
 */
new Swiper('.imgcell-details-slider', {
  speed: 400,
  loop: true,
  autoplay: {
    delay: 5000,
    disableOnInteraction: false
  },
  pagination: {
    el: '.swiper-pagination',
    type: 'bullets',
    clickable: true
  },
  navigation: {
    nextEL: '.swiper-button-next',
    prevEL: '.swiper-button-prev',
  },
  scrollbar:{
    el: '.swiper-scrollbar',
  }
});
