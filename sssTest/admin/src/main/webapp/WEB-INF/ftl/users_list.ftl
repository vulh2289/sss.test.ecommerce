<#assign page="users"/>

<#include "/global/head.ftl">

<#assign filters = {
    "fId" : "${(result.filterId)!}",
    "fLoginName" : "${(result.filterLoginName)!}",
    "fActive" : "${(result.filterActive?c)!}"
}>

<@initList/>
<@displayMenu/>

<div id="container">
<h1>Users</h1>

<!-- List table -->
<form id="list_form" name="list_form" action="users" method="post">
<input id="list_action" type="hidden" name="action" value=""/> 

<table class="gradienttable">
  <thead>
    <tr>
      <th nowrap>ID</th>
      <th nowrap>Login Name</th>
      <th nowrap>Role</th>
      <th nowrap>First Name</th>
      <th nowrap>Last Name</th>
    </tr>
  </thead>
  <tbody>
  <#list result.users as user>
    <tr>
      <td nowrap>${user.id}</td>
      <td nowrap>${user.userName}</td>
      <td nowrap>${user.role}</td>
      <td nowrap>${user.firstName}</td>
      <td nowrap>${user.lastName}</td>
    </tr>
  </#list>
  </tbody>
</table>
</form>
</div>

<@displayFooter /> 
