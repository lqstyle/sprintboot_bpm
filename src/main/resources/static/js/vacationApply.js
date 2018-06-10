$(function () {
    $("#cancelBtn").click(function () {
        /* $(".layui-layer-close2").hide();*/
        /*
                $(".layui-layer-close2").hide();
        */
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);

    });

    $('#submitBtn').click(function () {
        $.ajax({
            type: "GET",
            url: "/processes/vacation_application/start",
            data: "",
            dataType: "text",
            success: function (data) {
                layer.alert('创建成功');
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            error: function () {
                layer.alert('创建失败');
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }

        });
        window.parent.searchClick();
    });
    $("#beginTime").datepicker();
    $("#endTime").datepicker();
});

