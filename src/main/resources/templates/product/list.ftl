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
                    <th>商品ID</th>
                    <th>商品小图</th>
                    <th>商品名称</th>
                    <th>商品价格</th>
                    <th>商品描述</th>
                    <th>库存</th>
                    <th>类别</th>
                    <th>上架时间</th>
                    <th>状态</th>
                    <th>操作</th>
                    <#--<th colspan="2">操作</th>-->
                </tr>
                </thead>
                <tbody>
<#list productInfoPage.content as productInfo>
<tr>
    <td>${productInfo.productId}</td>
    <td><img src="${productInfo.productIcon}" width="100" height="100"></td>
    <td>${productInfo.productName}</td>
    <td>${productInfo.productPrice}</td>
    <td>${productInfo.productDescription}</td>
    <td>${productInfo.productStock}</td>
    <td>${productInfo.categoryType}</td>
    <td>${productInfo.createTime}</td>
    <td>${productInfo.getproductStatusEnum().msg}</td>
    <#--<td><a>修改</a></td>-->
    <#--<td><a>下架</a></td>-->
    <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
    <#if productInfo.getproductStatusEnum().msg=="在架">
    <td><a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a></td>
    <#else >
    <td><a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a></td>
    </#if>
</tr>
</#list>
                </tbody>
            </table>
        </div>
    <#--分页-->
        <div class="col-md-12 column">
            <button type="button" class="btn btn-default btn-success"><a href="/sell/seller/product/index">新增商品</a></button>
            <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
                <#else >
                 <li>
                     <a href="/sell/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a>
                 </li>
                </#if>
                <#list 1..productInfoPage.getTotalPages() as index>
                    <#if currentPage==index>
                <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte productInfoPage.getTotalPages()>
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