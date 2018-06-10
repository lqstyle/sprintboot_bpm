$(document).ready(function () {
    $("#projectTable").jqGrid({
        url: '/task/restTask',
        mtype: "post",
        ajaxGridOptions: {contentType: 'application/json; charset=utf-8'},
        serializeGridData: function (postData) {
            return JSON.stringify(postData);
        },
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', hidden: true, key: true, width: 75, align: "center"},
            {label: 'taskname', name: 'taskname', width: 100, align: "center"},
            {label: 'assignee', name: 'assignee', width: 75, align: "center"},
            {label: 'priority', name: 'priority', width: 75, align: "center"},
            {label: 'suspensionState', name: 'suspensionState', width: 75, align: "center"},
            {
                label: 'executionId',
                name: 'executionId',
                width: 75,
                align: "center"
            },
            /*
                        {label: 'startTime', name: 'startTime', width: 75, align: "center"},
            */
            {label: 'detail', name: 'detail', width: 75, formatter: editLink, align: "center"}
        ],
        viewrecords: true,
        autowidth: true,
        height: '288px',
        rowNum: 30,
        rowList: [10, 20, 30],
        pager: "#projectPager"
    });

    $("#searchBut").click(function () {
        var taskName = $.trim(($("#taskName").val()));
        var postData = $('#projectTable').jqGrid("getGridParam", "postData");
        postData["taskName"] = taskName;
        postData["page"] = "1";
        postData["rows"] = $('#projectTable').jqGrid("getGridParam", "pageNum");
        $("#projectTable").jqGrid('setGridParam', {
            url: '/task/restTask',
            postData: postData, //发送数据
        }).trigger("reloadGrid", {page: '1'}); //重新载入
    });

    $('#createVacation').click(function () {
        //iframe窗
        layer.open({
            type: 2,
            title: false,
            closeBtn: 1, //显示关闭按钮
            shade: [0],
            area: ['600px', '300px'],
            offset: 'cb', //中间弹出
            anim: 2,
            content: ['/task/vacationApply', 'no'],
        });
    });


});

function editLink(cellValue, options, rowdata, action) {
    return '<button class="table-button" style="background-color: #7965cb;width:50px;" href="#" onclick="Confirm(\''+rowdata.id+'\');" title="审批">' +
        '审批' +
        '</button>';
}

function Confirm(id) {
    //询问框
    layer.confirm('是否批准该条请假申请？', {
        btn: ['批准', '拒绝'] //按钮
    }, function () {
        $.ajax({
            type: "GET",
            url: "/task/approvalVacationOrNot?way=approval&taskId="+id,
            dataType: "text",
            success: function (data) {
                layer.msg('该条请假已通过', {icon: 1});
                $("#searchBut").click();
            },
            error: function () {
                layer.alert('操作失败');
            }
        });
    }, function () {
        $.ajax({
            type: "GET",
            url: "/task/approvalVacationOrNot?way=reject&taskId="+id,
            data: "",
            dataType: "text",
            success: function (data) {
                layer.msg('该条请假记录已拒绝', {icon: 1});
                $("#searchBut").click();
            },
            error: function () {
                layer.alert('操作失败');
            }
        });
    });
}

function searchClick() {
    $("#searchBut").click();
}

