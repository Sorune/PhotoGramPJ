
// file upload drag
const dropArea = document.getElementById("uploadedImages");		//이미지 드래그 드롭 필드 등록
var fileInput = document.getElementById("file-input");			//input type="file" 태그
const imagePreview = document.getElementById("image-preview");
const dataTranster = new DataTransfer();						//데이터 전송용 객체
var uploadedImages = document.getElementById("uploadedImages");	//이미지 표시할 캐러셀
var uploadImageButton = document.getElementById("uploadImage");	//캐러셀 이미지 등록 버튼
var deleteImageButton = document.getElementById("deleteImage");	//캐러셀 이미지 삭제 버튼


if (!(uploadedImages == null)) {							//캐러셀이 없을 경우
    uploadedImages = uploadedImages.querySelector("div");	//이미지 드롭 필드 변경
}
const inputFile = $("input[name='uploadFile']");

function onClickFunction() {	//필드 클릭시 이미지 등록 이벤트 등록
    fileInput.click();
}

function createUploadFileInput(form) {  //폼을 받아 이미지 input태그를 폼에 삽입하는 메서드
    let nodes = document.querySelector("#uploadedImages").querySelectorAll("img");
    let inputString = "";
    $(nodes).each(function(i, obj) {
        let inputFileName = document.createElement("input");
        let inputFilePath = document.createElement("input");
        let inputFileUuid = document.createElement("input");
        inputFileName.type = "hidden";
        inputFileName.setAttribute('name', "imgList[" + i + "].fileName");
        inputFileName.setAttribute('value', obj.getAttribute("fileName"));
        inputFilePath.type = "hidden";
        inputFilePath.setAttribute('name', "imgList[" + i + "].uploadPath");
        inputFilePath.setAttribute('value', obj.getAttribute("uploadPath"));
        inputFileUuid.type = "hidden";
        inputFileUuid.setAttribute('name', "imgList[" + i + "].uuid");
        inputFileUuid.setAttribute('value', obj.getAttribute("uuid"));
        inputString += inputFileName.outerHTML+inputFilePath.outerHTML+inputFileUuid.outerHTML;
    });
    form.append(inputString).submit();
}

function createCarouselInner(fileList) {	//업로드된 이미지를 캐러셀에 등록
    let nodes = document.querySelector("#uploadedImages").querySelector("div").querySelectorAll("div");
    for (var i = 0; i < nodes.length; i++) {
        nodes[i].remove();
    };
    $(fileList).each(function(i, obj) {
        const carouselInner = document.createElement("div");	//div 태그 생성
        if (i == 0) {
            carouselInner.className = "carousel-item active";
        } else {
            carouselInner.className = "carousel-item";
        }
        const img = document.createElement("img");				//img 태그 생성
        var urlString = '/'+ window.location.pathname.split("/")[1];
        var fileCallPath = encodeURIComponent(urlString+"/"+obj.uploadPath.replaceAll('\\','/') + "/" + obj.uuid + "_" + obj.fileName);
        console.log(fileCallPath);
        img.src = "/download?fileName=" + fileCallPath;
        img.setAttribute('width', "100%");
        img.setAttribute('height', "auto");
        img.setAttribute('class', "d-block w-100");
        img.setAttribute('uploadPath', obj.uploadPath.replaceAll('\\','/'));
        img.setAttribute('uuid', obj.uuid);
        img.setAttribute('fileName', obj.fileName);				//img태그에 데이터 등록
        carouselInner.appendChild(img);							//img태그를 div에 등록
        uploadedImages.appendChild(carouselInner);				//완성된 div를 캐러셀에 등록
    });

    //createUploadFileInput(formObj);
    console.log(uploadedImages);
    dropArea.removeAttribute('eventList');						//이벤트 등록 정보 태그 삭제
    dropArea.removeEventListener("click", onClickFunction);		//캐러셀 클릭 이벤트 제거
    console.log("event remove");

    let nodeList = document.querySelector("#uploadedImages").querySelector("div").querySelectorAll("div");	//캐러셀 이너 div 선택
    console.log(nodeList);
    if (nodeList.length == 0) {																				//캐러셀 이너 div에 내용이 없을 경우 기본 이미지 등록
        var emptyNode = document.createElement("div");
        var emptyP = document.createElement("p");
        emptyNode.setAttribute("id", "drop-area");
        emptyNode.setAttribute("style", "width:100%;");
        emptyP.innerText = "이미지를 드래그 앤 드롭 하거나 클릭하여 업로드하세요.";
        emptyNode.appendChild(emptyP);
        document.querySelector("#uploadedImages").querySelector("div").appendChild(emptyNode);

        dropArea.setAttribute('eventList', 'click');														//클릭 이벤트 등록 정보 태그 생성
        dropArea.addEventListener("click", onClickFunction);												//클릭 이벤트 등록
        console.log("event set");
    }

};


if (!(dropArea == null)) {
    // 드래그 앤 드롭 이벤트 처리
    dropArea.addEventListener("dragover", (e) => {		//마우스 위치 이벤트 등록
        e.preventDefault();
        dropArea.style.backgroundColor = "#eee";
    });

    dropArea.addEventListener("dragleave", () => {		//마우스 위치 이벤트 등록
        dropArea.style.backgroundColor = "#fff";
    });

    dropArea.addEventListener("drop", (e) => {			//이미지 드롭 이벤트 등록
        const dataTranster = new DataTransfer();
        const inputFile = document.querySelector("#file-input");
        e.preventDefault();								//기본 이벤트 정지, 정지하지 않을 경우 새 창에서 이미지 열림
        dropArea.style.backgroundColor = "#fff";
        const files = e.dataTransfer.files;				//input태그에 담긴 파일들을 데이터 트랜스퍼 객체를 통해 옮겨담음
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            if (file && file.type.startsWith("image")) {
                dataTranster.items.add(file);			//이미지일 경우 추가
            }
        };
        inputFile.files = dataTranster.files;			//인풋태그의 파일들 교체
        console.log(inputFile);
        imgUpload(inputFile.files);						//서버로 이미지 데이터 전송
    });



    // 클릭 이벤트 처리
    uploadImageButton.addEventListener("click", function() {
        fileInput.click();
    });

    deleteImageButton.addEventListener("click", function() {
        let nodes = document.querySelector("#uploadedImages").querySelector("div").querySelectorAll("div");
        let deleteFile;
        for (let i = 0; i < nodes.length; i++) {
            if (nodes[i].getAttribute("class").includes("active")) {
                deleteFile = nodes[i];
            }
        }
        if(deleteFile != null){
            let deleteFilePath = deleteFile.querySelector("img").getAttribute("uploadpath") + "\\" + deleteFile.querySelector("img").getAttribute('uuid') + "_" + deleteFile.querySelector("img").getAttribute('fileName');
            console.log(deleteFilePath);
            let deleteFileType = "zip";
            $.ajax({
                url: '/deleteFile',
                data: {fileName : deleteFilePath, type : deleteFileType},
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                type: 'POST',
                success: function(result) {
                    console.log(result);

                    deleteFile.remove();
                    nodes = document.querySelector("#uploadedImages").querySelector("div").querySelectorAll("div");
                    //console.log(document.querySelectorAll('[fileName='+'"'+deleteFile.querySelector("img").getAttribute('fileName')+'"'+']'));
                    //document.querySelector('[fileName='+'"'+deleteFile.querySelector("img").getAttribute('fileName')+'"'+']').remove();
                    if (nodes.length != 0) {
                        nodes[0].setAttribute("class", nodes[0].getAttribute("class") + " active");
                    } else {
                        var emptyNode = document.createElement("div");
                        var emptyP = document.createElement("p");
                        emptyNode.setAttribute("id", "drop-area");
                        emptyNode.setAttribute("style", "width:100%;");
                        emptyP.innerText = "이미지를 드래그 앤 드롭 하거나 클릭하여 업로드하세요.";
                        emptyNode.appendChild(emptyP);
                        document.querySelector("#uploadedImages").querySelector("div").appendChild(emptyNode);
                    }

                },
                error: function(result) {
                    console.log(result);
                }
            }); //$.ajax
        }
    });

};

if (!(document.getElementById("drop-area") === null)&&!(document.querySelector('input[id="file-input"]')===null)) {

    dropArea.setAttribute('eventList', 'click');
    dropArea.addEventListener("click", onClickFunction);
    console.log("event set");
} else {
    if (document.getElementById("drop-area") === null&&!(document.querySelector('input[id="file-input"]')===null)) {
        if (!(dropArea.getAttribute('eventList') === null)) {
            dropArea.removeAttribute('eventList');
            dropArea.removeEventListener("click", onClickFunction);
            console.log("event remove");
        }
    }
}

if (!(fileInput == null)) {
    // 파일 입력 필드 변경 이벤트 처리
    fileInput.addEventListener("change", function(e) {
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        console.log(csrfHeader + " : " + csrfToken);
        var inputFile = document.querySelector("#file-input");
        var formData = new FormData();
        var files = inputFile.files;
        console.log(files);
        for (var i = 0; i < files.length; i++) {
            //if(!checkExtension(files[i].name, files[i].size)){
            //console.log(!checkExtension(files[i].name, files[i].size));
            //	return false;
            //}
            console.log(i + files[i] + files[i].name);
            formData.append("uploadFile", files[i], files[i].name);
        };
        for (var pair of formData.entries()) {
            console.log(pair[0] + ', ' + pair[1]);
        }
        var urlString = '/'+ window.location.pathname.split("/")[1]+'/uploadAjaxAction';		//REST방식, 주소 파싱하여 해당 테이블로 전송하기 위한 URI 생성
        console.log(urlString);
        $.ajax({
            url: urlString/*'/uploadAjaxAction'*/,
            processData: false,
            contentType: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            data: formData,
            type: 'POST',
            success: function(result) {
                console.log(result);
                createCarouselInner(result);			//이미지 업로드 성공시 업로드된 이미지를 보여줄 캐러셀 이미지 생성
            },
            error: function(result) {
                alert("uploadFail");
                console.log(result);
            }
        }); //$.ajax
    });

};

function imgUpload(files) {
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var formData = new FormData();
    console.log(files);
    for (var i = 0; i < files.length; i++) {
        //if(!checkExtension(files[i].name, files[i].size)){			//파일 사이즈 검증 로직, 우선 막음
        //console.log(!checkExtension(files[i].name, files[i].size));
        //	return false;
        //}
        console.log(i + files[i] + files[i].name);
        formData.append("uploadFile", files[i], files[i].name);			//폼에 파일 데이터 추가
    };
    for (var pair of formData.entries()) {
        console.log(pair[0] + ', ' + pair[1]);
    }																	//폼 데이타 파일 출력 코드
    var urlString = '/'+ window.location.pathname.split("/")[1]+'/uploadAjaxAction';		//REST방식, 주소 파싱하여 해당 테이블로 전송하기 위한 URI 생성
    console.log(urlString);
    $.ajax({
        url: urlString/*'/uploadAjaxAction'*/,
        processData: false,
        contentType: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data: formData,
        type: 'POST',
        success: function(result) {
            console.log(result);
            createCarouselInner(result);			//이미지 업로드 성공시 업로드된 이미지를 보여줄 캐러셀 이미지 생성
        },
        error: function(result) {
            alert("uploadFail");
            console.log(result);
        }
    }); //$.ajax
}
