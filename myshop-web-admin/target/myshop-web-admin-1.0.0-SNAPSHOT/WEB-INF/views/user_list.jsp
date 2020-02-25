<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}

                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <div class="row" style="padding-left:12px; padding-top: 10px;">
                                <div class="col-xs-12">
                                    <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus" >新增</i></a>&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-sm btn-default" onclick="deleteMulti();"><i class="fa fa-trash-o" >删除</i></button>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download" >导入</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload" >导出</i></a>&nbsp;&nbsp;&nbsp;
                                </div>
                            </div>
                            <div class="row" style="margin-top: 20px;">
                                    <form:form cssClass="form-horizontal" action="/user/search" method="post" modelAttribute="tbUser">
                                        <div class="col-xs-4">
                                            <div class="form-group">
                                                <label for="username" class="col-sm-2 control-label">姓名</label>
                                                <div class="col-sm-10">
                                                    <form:input cssClass="form-control" path="username" placeholder="姓名"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="form-group">
                                                <label for="email" class="col-sm-2 control-label">邮箱</label>
                                                <div class="col-sm-10">
                                                    <form:input cssClass="form-control" path="email" placeholder="邮箱"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="form-group">
                                                <label for="phone" class="col-sm-2 control-label">手机</label>
                                                <div class="col-sm-10">
                                                    <form:input cssClass="form-control" path="phone" placeholder="手机"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="padding-right: 20px;">
                                            <div class="col-xs-12">
                                                <button type="submit" class="btn btn-info pull-right">搜索</button>
                                            </div>
                                        </div>
                                    </form:form>
                            </div>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">

                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal check_master"></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbUsers}" var="tbUser">
                                    <tr>
                                        <td><input id="${tbUser.id}" type="checkbox" class="minimal"></td>
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.phone}</td>
                                        <td>${tbUser.email}</td>
                                        <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <button type="button" class="btn btn-sm btn-default" onclick="showDetail('/user/detail?id=${tbUser.id}');"><i class="fa fa-search" >查看</i></button>&nbsp;&nbsp;&nbsp;
                                            <a href="/user/form?id=${tbUser.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit" >编辑</i></a>&nbsp;&nbsp;&nbsp;
                                            <a href="#" type="button" class="btn btn-sm btn-danger" onclick="deleteMulti();"><i class="fa fa-trash-o" >删除</i></a>&nbsp;&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>

</div>




<jsp:include page="../includes/footer.jsp"/>
<sys:modal />

<script>

    function showDetail(url){
        //通过Ajax请求html的方式将jsp页面装载进模态框
        $.ajax({
            url:url,
            type:"get",
            dataType: "html",
            success:function (data) {
                $("#modal-detail-body").html(data)
                $("#modal-detail").modal("show");
            }
        });

    }

    $(function(){
        $("#dataTable").DataTable();
    });

    var idArray=new Array();

    $(function () {
        var _masterCheckbox=$('input[type="checkbox"].minimal.check_master');
        var _checkbox=$('input[type="checkbox"].minimal');

        _masterCheckbox.on("ifClicked",function (e) {
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            else {
                _checkbox.iCheck("check");
            }

        });

    })
    var deleteMulti=function () {

        var _checkbox=$('input[type="checkbox"].minimal');

        _checkbox.each(function () {
            var _id=$(this).attr("id");
            if(_id!=null&&_id!="undefine"&&$(this).is(":checked")){
                idArray.push(_id);
            }

        });
       if(idArray.length===0){

           $("#modal-message").html("您还没有选择任何数据，请至少选择一项");
       }
       else {
           $("#modal-message").html("您确定删除数据项吗?");
       }
        $("#modal-default").modal("show");
    }

    $(function () {
        $("#btnModalOk").bind("click",function () {
          del(idArray,"/user/delete")
        });
        function del(idArray,url) {
            $("#modal-default").modal("hide");
            if(idArray.length===0){
               //...
            }
            else {
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "data":{"ids" : idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            //请求成功后，无论时成功还是失败都要弹出模态框，所以这里要先解绑原来的click时间
                            $("#btnModalOk").unbind("click");
                            //请求成功
                            if(data.status===200){
                                //刷新页面
                                $("#btnModalOk").bind("click",function () {
                                    window.location.reload();
                                });

                            }
                            //请求失败
                            else{
                                //确定按钮的时间改为隐藏模态框
                                $("#btnModalOk").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });

                            }
                            //因为无论如何都要提示信息，所以这里的模态框是必须的
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);

            }

        }

    });
    

</script>
</body>

</html>
