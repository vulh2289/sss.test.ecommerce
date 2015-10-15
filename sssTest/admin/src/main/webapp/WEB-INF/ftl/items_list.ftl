<#assign page="items"/>

<#include "/global/head.ftl">

<#assign filters = {
    "fId" : "${(result.filterId)!}",
    "fLoginName" : "${(result.filterLoginName)!}",
    "fActive" : "${(result.filterActive?c)!}"
}>

<@initList/>
<@displayMenu/>

<div id="container">
<h1>Items</h1>

<!-- List table -->
<form id="list_form" name="list_form" action="items" method="post">
<input id="list_action" type="hidden" name="action" value=""/> 

<table class="gradienttable">
  <thead>
    <tr>
      <th nowrap>ID</th>
      <th nowrap>Name</th>
      <th nowrap>price</th>
      <th nowrap>quantity</th>
    </tr>
  </thead>
  <tbody>
  <#list result.items as item>
    <tr>
      <td nowrap>${item.id}</td>
      <td nowrap>${item.name}</td>
      <td nowrap>${item.price}</td>
      <td nowrap>${item.quantity}</td>
    </tr>
  </#list>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="28">
        <#if result.items?size != 0><@paginationWidget/><#else>No content</#if>
      </td>
    </tr>
  </tfoot>
</table>
</form>

</div>

<@displayFooter /> 
