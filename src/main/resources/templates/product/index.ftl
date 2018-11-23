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
            <form role="form" method="post" action="/sell/seller/product/save">
                <div class="form-group">
                    <label>名称</label><input name="productName" type="text" class="form-control"
                                            value="${(productInfo.productName)!}"/>
                </div>
                <div class="form-group">
                    <label>价格</label><input name="productPrice" type="text" class="form-control"
                                            value="${(productInfo.productPrice)!}"/>
                </div>
                <div class="form-group">
                    <label>库存</label><input name="productStock" type="text" class="form-control"
                                            value="${(productInfo.productStock)!}"/>
                </div>
                <div class="form-group">
                    <label>商品描述</label><input name="productDescription" type="text" class="form-control"
                                              value="${(productInfo.productDescription)!}"/>
                </div>
                <div class="form-group">
                    <label>图片</label><img src="${(productInfo.productIcon)!}" width="80" height="80"><input
                        name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!}"/>
                </div>
                <div class="form-group">
                    <label>类目</label>
                    <select name="categoryType" class="form-control">
                        <#list categoryList as category>
                            <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)?? &&productInfo.categoryType==category.categoryType>
                                    selected
                                    </#if>
                            >${category.categoryName}</option>
                        </#list>

                    </select>
                </div>
                <input hidden type="text" name="productId" value="${(productInfo.productId)!}">
                <button type="submit" class="btn btn-default btn-success">确定</button>
                <button type="button" class="btn btn-default btn-danger"><a href="/sell/seller/product/list">取消</a></button>
            </form>
        </div>
    <#--分页-->

    </div>
</div>
</body>
</html>