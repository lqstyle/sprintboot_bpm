$(document).ready(function () {
    $("#cancelBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    $('#submitBtn').click(function () {
        $("#loginForm").ajaxForm(function (data) {
            if (data != "" && data != null) {
                layer.alert("新增成功");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                layer.alert("新增失败");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
            window.parent.searchClick();
        });
    });

});

