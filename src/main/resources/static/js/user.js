let index = {
    init: function () {
        $("#btn-save").on("click", () => {  // function(){}, ()=>{} this를 바인딩하기 위해서 사용
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function(){
        // alert("user의 save함수 호출됨");
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()

        };

        // ajax 호출시 default가 비동기 호출
        // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

        $.ajax({
            type : "POST",
            url  : "/auth/joinProc",
            data : JSON.stringify(data),  //http body 데이터
            contentType : "application/json; charset=utf-8", // body데이터가 어떤타입인지 (MIME)
            dataType    : "JSON" // 요청을 서버로 해서 응답이 왔을때 , 기본적으로 모든것이 문자열 (생긴게 json) => javascript object
        }).done(function(resp){
            if(resp.status === 500){
                alert("회원가입이 실패하였습니다.");
            }else{
            alert("회원가입이 완료되었습니다.");

            location.href = "/";

            }
        }).fail(function(er){
            alert(JSON.stringify(er));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청청
    },
    update: function(){

        let data = {
            id      : $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()

        };

        $.ajax({
            type : "PUT",
            url  : "/user",
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType    : "JSON"
        }).done(function(resp){
            alert("회원수정이 완료되었습니다.");
            // alert(resp);
            location.href = "/";
        }).fail(function(er){
            alert(JSON.stringify(er));
        });
    }
}

index.init();