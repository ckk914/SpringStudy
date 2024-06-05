<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8" />
            <title>File Insert title here</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f0f0f0;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    max-width: 800px;
                    margin: 50px auto;
                    padding: 20px;
                    background-color: #fff;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                }

                #img-input {
                    display: none;
                }

                .upload-box {
                    width: 300px;
                    height: 200px;
                    border: 3px dashed #007bff;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    color: #007bff;
                    font-weight: bold;
                    cursor: pointer;
                    transition: background-color 0.3s ease-in-out, border-color 0.3s ease-in-out;
                }

                .upload-box:hover {
                    background-color: #f0f0f0;
                    border-color: #0056b3;
                }

                .drop-area {
                    border: 2px dashed #ccc;
                    width: 400px;
                    height: 250px;
                    text-align: center;
                    padding: 20px;
                    margin: 20px auto;
                    transition: background-color 0.3s ease-in-out;
                }

                .drop-area:hover {
                    background-color: #f0f0f0;
                }

                #image-preview {
                    margin: 20px auto;
                    max-width: 100%;
                    max-height: 300px;
                    display: none;
                    border: 1px solid #ccc;
                    padding: 10px;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                }
            </style>
        </head>

        <body>
            <!-- 동기 방식으로 진행 -->
            <h1>/파일 업로드 테스트/</h1>

            <div class="upload-box">"드래그앤 드롭"<br><br>
                또는<br><br> 여기를 눌러 "파일을 선택"</div>
            <form action="/upload/file" method="post" enctype="multipart/form-data">
                <input type="file" name="thumbnail" id="img-input" accept="image/*" />

                <button type="submit">전송</button>
                <h2>업로드 된 파일</h2>
                <img id="img-preview" src="" alt="업로드된 이미지" />
                <div></div>
            </form>
            <script>
                //클릭 이벤트
                document.querySelector(`.upload-box`).onclick = (e) => {
                    document.getElementById("img-input").click();
                };

                //

                //드래그 드롭 걸기
                const $dropArea = document.querySelector(".upload-box");
                const $imagePreview = document.querySelector("#img-preview");

                // 드래그 앤 드롭 이벤트 처리
                $dropArea.addEventListener("dragover", (e) => {
                    e.preventDefault();
                    $dropArea.style.backgroundColor = "#eee";
                });

                $dropArea.addEventListener("dragleave", () => {
                    $dropArea.style.backgroundColor = "#fff";
                });

                $dropArea.addEventListener("drop", (e) => {
                    e.preventDefault();
                    $dropArea.style.backgroundColor = "#fff";
                    const file = e.dataTransfer.files[0];
                    if (file && file.type.startsWith("image")) {
                        displayImage(file);
                    }

                    const $input = document.getElementById("img-input");
                    $input.files = e.dataTransfer.files;
                    console.log("첨부파일: ", $input.files);

                });

                // 이미지 표시 함수
                function displayImage(file) {
                    const reader = new FileReader();
                    reader.onload = () => {
                        $imagePreview.src = reader.result;
                        $imagePreview.style.display = "block";
                    };
                    reader.readAsDataURL(file);
                    //input 에 file 넣기

                    // uploadFile(file); // 파일 업로드 처리 추가
                }



                // function uploadFile(file) {
                //     const formData = new FormData();
                //     formData.append('thumbnail', file);

                //     fetch('/upload/file', {
                //         method: 'POST',
                //         body: formData
                //     })
                //         .then(response => {
                //             if (response.ok) {
                //                 console.log('파일 업로드 성공');
                //             } else {
                //                 console.error('파일 업로드 실패');
                //             }
                //         })
                //         .catch(error => {
                //             console.error('파일 업로드 중 오류 발생:', error);
                //         });
                // }
            </script>
        </body>

        </html>