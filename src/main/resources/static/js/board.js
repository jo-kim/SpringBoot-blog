let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function(){
        // alert("user의 save함수 호출됨");
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };



        $.ajax({
            type : "POST",
            url  : "/api/board",
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType    : "JSON"
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.");

            location.href = "/";
        }).fail(function(er){
            alert(JSON.stringify(er));
        });
    }
}

index.init();