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
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>类目ID</th>
                    <th>类目名称</th>
                    <th>类目编号</th>

                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                <#--<th colspan="2">操作</th>-->
                </tr>
                </thead>
                <tbody>
<#list productCategoryPage.content as category>
<tr>
    <td>${category.categoryId}</td>
    <td>${category.categoryName}</td>
    <td>${category.categoryType}</td>
    <td>${category.createTime}</td>
    <td>${category.updateTime}</td>
    <td><a href="/sell/seller/category/index1?categoryId=${category.categoryId}">查看商品</a></td>
    <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
</tr>
</#list>
                </tbody>
            </table>
        </div>
    <#--分页-->
        <div class="col-md-12 column">
            <button type="button" class="btn btn-default btn-success"><a href="/sell/seller/category/index">新增类目</a></button>
            <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
                <#else >
                 <li>
                     <a href="/sell/seller/category/list?page=${currentPage-1}&size=${size}">上一页</a>
                 </li>
                </#if>
                <#list 1..productCategoryPage.getTotalPages() as index>
                    <#if currentPage==index>
                <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                <li><a href="/sell/seller/category/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte productCategoryPage.getTotalPages()>
                <li class="disabled">
                    <a href="#">下一页</a>
                </li>
                <#else >
                <li>
                    <a href="/sell/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a>
                </li>
                </#if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>