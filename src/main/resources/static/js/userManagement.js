$(document).ready(function () {
    $("#userTable").jqGrid({
        url: '/user/restGetUsers',
        mtype: "post",
        ajaxGridOptions: {contentType: 'application/json; charset=utf-8'},
        serializeGridData: function (postData) {
            return JSON.stringify(postData);
        },
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', hidden: true, key: true, width: 20, align: "center"},
            {label: 'loginName', name: 'loginName', width: 30, align: "center"},
            {label: 'email', name: 'email', width: 60, align: "center"},
            {label: 'employeeNo', name: 'employeeNo', width: 60, align: "center"},
            {label: 'delFlag', name: 'delFlag', width: 60, align: "center"},
            {label: 'operation', name: 'operation', width: 30, formatter: editLink, align: "center"}
        ],
        viewrecords: true,
        autowidth: true,
        height: '288px',
        rowNum: 30,
        rowList: [10, 20, 30],
        pager: "#userPage"
    });

    $("#searchBut").click(function () {
        var loginName = $.trim(($("#loginName").val()));
        var postData = $('#userTable').jqGrid("getGridParam", "postData");
        postData["loginName"] = loginName;
        postData["page"] = "1";
        postData["rows"] = $('#userTable').jqGrid("getGridParam", "pageNum");
        $("#userTable").jqGrid('setGridParam', {
            url: '/user/restGetUsers',
            postData: postData, //发送数据
        }).trigger("reloadGrid", {page: '1'}); //重新载入
    });

    $('#createUser').click(function () {
        //iframe窗
        layer.open({
            type: 2,
            title: false,
            closeBtn: 1, //显示关闭按钮
            shade: [0],
            area: ['800px', '500px'],
            offset: 'cb', //中间弹出
            anim: 2,
            content: ['/user/addUserPage', 'no'],
        });
    });


});

function editLink(cellValue, options, rowdata, action) {
    return '<button class="table-button" style="background-color: #7965cb;width:50px;" href="#" onclick="Confirm(\'' + rowdata.id + '\');" title="注销">' +
        '注销' +
        '</button>';
}

function Confirm(id) {
    //询问框
    layer.confirm('是否注销此用户？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            type: "GET",
            url: "/user/updateUser?userId=" + id,
            dataType: "text",
            success: function (data) {
                if(data!=null &&  data!="" && data=="1"){
                    layer.msg('注销成功', {icon: 1});
                    $("#searchBut").click();
                }else{
                    layer.alert('注销失败');
                }
            },
            error: function () {
                layer.alert('请求失败');
            }
        });
    }, function () {

    });
}

function searchClick() {
    $("#searchBut").click();
}

