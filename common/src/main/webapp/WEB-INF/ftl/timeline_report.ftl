<#include "common_macros.ftl">
"Timeline report"	"${timelineReport.timePeriodConceptReports[0].concept.label}"	"${timelineReport.beginTime!}"	"to"	"${timelineReport.endTime!}"	"based on"	<#assign sxy="G1">${timelineReport.timePeriodConceptReports[0].concept.meta['samplingRate']}	"% sample rate"																					
"Time"		<@twitter_followers_headers/>	<@expensive_stats_headers timelineReport.topics/>
<#assign x = 1>"Begin"	<@inc_x/>"End"	<@twitter_followers_subheaders/>	<@expensive_stats_subheaders timelineReport.topics/>
<#assign yOff = 4>
<#list timelineReport.timePeriodConceptReports as y>	
<#assign x = 1>"${y.beginTime}"	<@inc_x/>"${y.endTime}"	<@twitter_followers y y_index/>	<@expensive_stats y y_index timelineReport.topics/>
</#list>