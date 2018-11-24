<html>
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <#include "../common/head.ftl">
        <div class="col-md-12 column">
            <form role="form" method="post" action="/sell/seller/category/save">
                <div class="form-group">
                    <label>类目名称</label><input name="categoryName" type="text" class="form-control"
                                            value="${(category.categoryName)!}"/>
                </div>
                <div class="form-group">
                    <label>类目编号</label><input name="categoryType" type="text" class="form-control"
                                            value="${(category.categoryType)!}"/>
                </div>
                <input hidden type="text" name="categoryId" value="${(category.categoryId)!}">
                <button type="submit" class="btn btn-default btn-success">确定</button>
                <button type="button" class="btn btn-default btn-danger"><a href="/sell/seller/category/list">取消</a></button>
            </form>
        </div>
    <#--分页-->

    </div>
</div>
</body>
</html>