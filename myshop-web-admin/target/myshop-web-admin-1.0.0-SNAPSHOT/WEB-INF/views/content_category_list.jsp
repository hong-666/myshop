<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
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
                内容管理
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
                            <h3 class="box-title">分类列表</h3>

                            <div class="row" style="padding-left:12px; padding-top: 10px;">
                                <div class="col-xs-12">
                                    <a href="/contect/category/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus" >新增</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download" >导入</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload" >导出</i></a>&nbsp;&nbsp;&nbsp;
                                </div>
                            </div>


                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>排序</th>
                                    <th>操作</th>
                                </tr>

                                </thead>
                                <tbody>
                                <c:forEach items="${tbContentCategories}" var="tbContentCategory">
                                    <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parentId}">
                                        <td>${tbContentCategory.id}</td>
                                        <td>${tbContentCategory.name}</td>
                                        <td>${tbContentCategory.sortOrder}</td>
                                        <td>
                                            <a href="/contect/category/form?id=${tbContentCategory.id}&parentCategory.id=${tbContentCategory.parentCategory.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit" >编辑</i></a>&nbsp;&nbsp;&nbsp;
                                            <button type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash-o" >删除</i></button>&nbsp;&nbsp;&nbsp;
                                            <a href="/contect/category/form?parentCategory.id=${tbContentCategory.id}&parentCategory.name=${tbContentCategory.name}" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus" >新增下级菜单</i></a>
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
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<sys:modal />

<script>
    $(function () {
        $('#treeTable').treeTable({
            column:1,
            expandLevel:2
        });

    });
</script>

</body>

</html>
