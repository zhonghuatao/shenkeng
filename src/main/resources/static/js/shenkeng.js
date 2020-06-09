function postComment() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    if(!content){
        alert("回复不能为空！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "parentID": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=828f868f05d5910b3249&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable","true");
                    }
                }else {
                    console.log(response)
                }
            }
        },
        contentType: 'application/json',
        dataType: "json"
    });
}
