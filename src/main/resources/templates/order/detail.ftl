<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>订单总金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <#--订单详情表数据-->
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>商品ID</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productQuantity*orderDetail.productPrice}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().msg=="新订单">
            <button type="button" class="btn btn-default btn-success"><a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a></button>
            <button type="button" class="btn btn-default btn-danger"><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a></button>
            </#if>
            <button type="button" class="btn btn-default btn-info"><a href="/sell/seller/order/list">返回</a></button>
        </div>
    </div>
</div>
</body>
</html>