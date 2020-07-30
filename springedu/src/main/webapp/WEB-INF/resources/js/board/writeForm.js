  const writeBtn = document.getElementById("writeBtn");
    const cancelBtn = document.getElementById("cancelBtn");
    const listBtn = document.getElementById("listBtn");
    const writeForm = document.getElementById("writeForm");
    writeBtn.addEventListener("click", writeBtn_f);
    cancelBtn.addEventListener("click", cancelBtn_f);
    listBtn.addEventListener("click", listBtn_f);

    function writeBtn_f(e) {
      console.log("writeBtn_f");
      //1)유효성 체크

      //2)서버 전송
      writeForm.submit();
    }
    function cancelBtn_f(e) {
      console.log("cancelBtn_f");
      //입력한 내용 clear
      writeForm.reset();
    }
    function listBtn_f(e) {
      console.log("listBtn_f");
      //목록리스트로 이동
      location.href = "../board/list";
    }