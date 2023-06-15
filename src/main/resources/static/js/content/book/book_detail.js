init();


//초기 작업 실행
function init() {
    const reserveBtn = document.getElementById('reBtn');
    const borrowBtn = document.getElementById('brBtn');

    const bookStockCntElement = document.getElementById('bookBr').elements['bookStockCnt'];
    const bookStockCnt = parseInt(bookStockCntElement.value);

    const borrowCntElement = document.getElementById('bookBr').elements['borrowCnt'];
    const borrowCnt = parseInt(borrowCntElement.value);
    
    const reserveCntElement = document.getElementById('bookBr').elements['reserveCnt'];
    const reserveCnt = parseInt(reserveCntElement.value);
   
    const returnDateElement = document.getElementById('bookBr').elements['returnDate'];
    const returnDate = returnDateElement.value.toString();


    if (borrowCnt >= bookStockCnt && returnDate == null) {
        borrowBtn.disabled = true;
    } 
    
    if (reserveCnt >= 2){
		reserveBtn.disabled = true;
	}
}






// 대여하기 버튼 클릭 시 실행
function borrow(memId, bookCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      title: "먼저 로그인 하세요.",
      text: "로그인 하시겠습니까?",
      icon: "warning", //"info,success,warning,error" 중 택1
      showCancelButton: true,
      confirmButtonText: "확인",
      cancelButtonText: "취소"
    }).then(function(result) {
      if (result.isConfirmed) {
        // 확인 버튼을 클릭한 경우 로그인 페이지로 이동
        location.href = '/member/loginForm';
      }
    });

    // 로그인 체크
    return;
  }

  // 동시에 대여 가능 여부를 확인하고 대여를 처리
  checkBorrow(memId, bookCode, function() {
    checkBorrowLimit(memId, bookCode, function() {
      borrowAjax(memId, bookCode);
    });
  });
}


// 중복 대여 여부
function checkBorrow(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        Swal.fire({
          title: "대여 불가",
          text: "이미 대여한 책입니다.",
          icon: "error" //"info,success,warning,error" 중 택1
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}


// 초과 대여 여부
function checkBorrowLimit(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 4) {
        Swal.fire({
          title: "대여 불가",
          text: "대여 가능한 권수(4권)을 초과하였습니다.",
          icon: "error" //"info,success,warning,error" 중 택1
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}

// 도서 대여
function borrowAjax(memId, bookCode) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'bookCode': bookCode, 'memId': memId },
    success: function(result) {
      Swal.fire({
        title: "대여 성공",
        text: "도서가 성공적으로 대여되었습니다.",
        icon: "success"
      }).then(function(result) {
        if (result.value) {
          location.href = `/book/bookDetail?bookCode=${bookCode}`;
        }
      });
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여에 실패했습니다.",
        icon: "error"
      });
    }
  });
}


//------------------------------예약하기


// 예약하기 버튼 클릭 시 실행
function reserve(memId, bookCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      title: "먼저 로그인 하세요.",
      text: "로그인 하시겠습니까?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "확인",
      cancelButtonText: "취소"
    }).then((result) => {
      if (result.isConfirmed) {
        // 확인 버튼을 클릭한 경우 로그인 페이지로 이동
        location.href = '/member/loginForm';
      }
    });

    // 로그인 체크
    return;
  }
  // 동시에 대여 가능 여부를 확인하고 대여를 처리
  checkBorrow1(memId, bookCode, function() {
    checkReserve(memId, bookCode, function() {
      checkReserveLimit(memId, bookCode, function() {
        reserveAjax(memId, bookCode);
      });
    });
  });
}




// 중복 대여 여부
function checkBorrow1(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        Swal.fire({
          title: "예약 불가",
          text: "이미 대여한 책은 예약이 불가합니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}



// 중복 예약 여부
function checkReserve(memId, bookCode, callback) {
  $.ajax({
    url: '/book/reserveAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 11) {
        Swal.fire({
          title: "예약 불가",
          text: "이미 예약한 책입니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "중복 제약 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}



// 초과 예약 여부
function checkReserveLimit(memId, bookCode, callback) {
  $.ajax({
    url: '/book/reserveAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 2) {
        Swal.fire({
          title: "예약 불가",
          text: "예약 가능한 권수를 초과하였습니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "초과 예약을 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}

// 도서 예약
function reserveAjax(memId, bookCode) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'bookCode': bookCode, 'memId': memId },
    success: function(result) {
      Swal.fire({
        title: "성공",
        text: "도서가 성공적으로 예약되었습니다.",
        icon: "success"
      }).then(function() {
        location.href = `/book/bookDetail?bookCode=${bookCode}`;
      });
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "대여에 실패했습니다.",
        icon: "error"
      });
    }
  });
}



//----------------이미지회전-----

const bookCovers = /*[[${bookCovers}]]*/ null;

function getBookMaterials(urlMap) {
	const materialNames = ['edge', 'spine', 'top', 'bottom', 'front', 'back'];
	return materialNames.map((name) => {
		if (!urlMap[name]) return new THREE.MeshBasicMaterial(0xffffff);

		const texture = new THREE.TextureLoader().load(urlMap[name]);

		// to create high quality texture
		texture.generateMipmaps = false;
		texture.minFilter = THREE.LinearFilter;
		texture.needsUpdate = true;

		return new THREE.MeshBasicMaterial({ map: texture });
	});
}

const aspect = 400 / 600;
const refCurrent = document.getElementById('book-container');
const scene = new THREE.Scene();
scene.background = new THREE.Color(0xffffff);

const planeGeometry = new THREE.PlaneGeometry(500, 500, 32, 32);
const planeMaterial = new THREE.ShadowMaterial();
planeMaterial.opacity = 0.5;

const plane = new THREE.Mesh(planeGeometry, planeMaterial);
plane.receiveShadow = true;
scene.add(plane);

const light = new THREE.DirectionalLight(0xffffff, 1);
light.position.set(-10, 10, 10);
light.castShadow = true;
scene.add(light);

const geometry = new THREE.BoxGeometry(3.5, 5, 0.5);
const cube = new THREE.Mesh(geometry, getBookMaterials(bookCovers));
cube.castShadow = true;
cube.position.set(0, 0, 0);
scene.add(cube);

let isMouseOver = false;
refCurrent.addEventListener('mouseover', () => {
	isMouseOver = true;
});
refCurrent.addEventListener('mouseleave', () => {
	isMouseOver = false;
});

let degrees = 90;

const camera = new THREE.PerspectiveCamera(70, aspect, 1, 1000);
camera.position.set(0, 0, 6);

const renderer = new THREE.WebGLRenderer();
renderer.shadowMap.enabled = true;
renderer.shadowMap.type = THREE.PCFSoftShadowMap;
renderer.setSize(400, 600);

const distance = camera.position.distanceTo(cube.position);

const rotate = () => {
	if (degrees < 135) {
		degrees += 2;
		const radian = degrees * (Math.PI / 180);
		camera.position.x = Math.cos(radian) * distance;
		camera.position.z = Math.sin(radian) * distance;
	}
};

const rotateBack = () => {
	if (degrees > 90) {
		degrees -= 2;
		const radian = degrees * (Math.PI / 180);
		camera.position.x = Math.cos(radian) * distance;
		camera.position.z = Math.sin(radian) * distance;
	}
};

const animate = () => {
	requestAnimationFrame(animate);
	isMouseOver ? rotate() : rotateBack();
	camera.lookAt(scene.position);
	renderer.render(scene, camera);
};

refCurrent.appendChild(renderer.domElement);
animate();










