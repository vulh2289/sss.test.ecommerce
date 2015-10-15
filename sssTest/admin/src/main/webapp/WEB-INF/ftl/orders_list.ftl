<#assign page="orders"/>

<#include "/global/head.ftl">

<#assign filters = {
    "fId" : "${(result.filterId)!}",
    "fLoginName" : "${(result.filterLoginName)!}",
    "fActive" : "${(result.filterActive?c)!}"
}>

<@initList/>
<@displayMenu/>

<div id="container">
<h1>Orders</h1>

<!-- List table -->
<table class="gradienttable">
  <thead>
    <tr>
      <th nowrap>Order Id</th>
      <th nowrap>Buyer </th>
      <th nowrap>Bought at</th>
      <th nowrap>Item</th>
      <th nowrap>Quantity</th>
      <th nowrap>Shipment Status</th>
      <th nowrap>Shipment apdated at</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  <#list result.orders as order>
  <#list order.details as detail>
    <tr>
      <td nowrap>${order.id}</td>
      <td nowrap>${order.buyer.userName}</td>
      <td nowrap>${order.createdAt}</td>
      <td nowrap>${detail.item.name}</td>
      <td nowrap>${detail.item.quantity}</td>
      <td nowrap>${detail.shipmentStatus}</td>
      <td nowrap>${detail.updatedAt}</td>
      <td>
	      <#if detail.shipmentStatus == "PENDING" && detail.id??>
	      	<a href="orders?action=dispatch&orderId=${order.id}&orderDetailsId=${detail.id}" onclick="return confirm('Are you sure this is item is dispatched [ ${detail.item.name} ]?')">Dispatch</a>
	      </#if>
      </td>
    </tr>
  </#list>
  </#list>
  </tbody>
</table>
</div>

<@displayFooter /> 
