<#assign menuItems = ["home", "users", "orders", "items"]>
<#assign paginationRows = [10, 25, 100, 500]>

<#--
 * ==================================================
 * Display Menu.
 * ==================================================
-->
<#macro initList>

  <#assign url>${page}?<#if filters??><#list filters?keys as filter><#if filters[filter]?has_content>${filter}=${filters[filter]}<#if filter_has_next>&</#if></#if></#list></#if></#assign>

  <#assign hiddenFilters>
    <#if filters??>
      <#list filters?keys as filter>
        <#if filters[filter]?has_content><input type="hidden" name="${filter}" value="${filters[filter]}"/></#if>
      </#list>
    </#if>
  </#assign>

</#macro>

<#macro displayMenu>
	<nav>
		<ul id="menu">
			<#list menuItems as menuItem>
			<li <#if page == menuItem>class="active"</#if>><a href="${menuItem}">${menuItem?cap_first}</a></li>
			</#list>
		</ul>
	</nav>
	
</#macro>

<#--
 * ==================================================
 * Pagination Widget
 * ==================================================
-->
<#macro paginationWidget>
  <#assign navUrl="${url}&sortAsc=${result.csp.sortAsc?c}&sortItem=${result.csp.sortItem}&rows=${result.csp.rows?c}&dc=${result.csp.displayCount?c}"/>
  <form name="pagination" action="${url}" method="get">
  <input type="hidden" name="sortAsc" value="${result.csp.sortAsc?c}"/>
  <input type="hidden" name="sortItem" value="${result.csp.sortItem}"/>
  <input type="hidden" name="dc" value="${result.csp.displayCount?c}" id="pag_dc"/>
  <input type="hidden" name="page" value="${result.csp.page}" id="pag_page"/>
  ${hiddenFilters}  
  <span><strong>Display: </strong></span>
  <select name="rows" onchange="displayCountChange();">
    <#list paginationRows as curRow>
    <option <#if result.csp.rows=curRow> selected</#if>>${curRow}</option>
    </#list>
  </select>
  <span><strong>Counts:</strong> </span>
  <input type="checkbox" <#if result.csp.displayCount?? && result.csp.displayCount == true>checked</#if> onchange="toggleDisplayCounts();" />
  <span>
    <#if result.csp.page != 0>
      <a href="${navUrl}&page=0"><img alt="First" title="First" src="images/icons/navfirst.gif" height="16" width="16"/></a>
      <a href="${navUrl}&page=${(result.csp.page-1)?c}"><img alt="Previous" title="Previous" src="images/icons/navprev.gif" height="16" width="16"/></a>
    </#if>
  </span> 
  <span><strong>${result.csp.page + 1}</strong><#if result.csp.pages != -1> of <strong>${result.csp.pages}</strong></#if></span>
  <span>
    <#if result.csp.page &lt; result.csp.pages - 1 || result.csp.pages == -1>
      <a href="${navUrl}&page=${(result.csp.page+1)?c}"><img alt="Next" title="Next" src="images/icons/navnext.gif" height="16" width="16"/></a>
    </#if>
    <#if result.csp.page &lt; result.csp.pages - 1 && result.csp.pages != -1>
      <a href="${navUrl}&page=${(result.csp.pages-1)?c}"><img alt="Last" title="Last" src="images/icons/navlast.gif" height="16" width="16"/></a>
    </#if>
  </span>
  </form>
</#macro>


<#--
 * ==================================================
 * Display Menu.
 * ==================================================
-->
<#macro displayFooter>
</body>
</html>
</#macro>
