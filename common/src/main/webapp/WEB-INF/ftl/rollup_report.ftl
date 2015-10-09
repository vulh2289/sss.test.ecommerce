<#include "common_macros.ftl">
<#macro aggregate group_column group_cell accumulator_op>${accumulator_op}IF(${group_column}${yOff?c}:${group_column}${yListEnd?c},${group_cell},${(x)?upper_abc}${yOff?c}:${(x)?upper_abc}${yListEnd?c})</#macro>
<#macro agg_safe group_column group_cell accumulator_op><@inc_x/>"=IF(ISERROR(<@aggregate group_column group_cell accumulator_op/>),"""", <@aggregate group_column group_cell accumulator_op/>)"</#macro>
<#macro sum_all group_column><@inc_x/>"=<@aggregate group_column "\"\"<>\"\"" "SUM"/>"</#macro>
<#macro avg_all group_column><@agg_safe group_column "\"\"<>\"\"" "AVERAGE"/></#macro>
<#macro sum_cat group_column><@inc_x/>"=<@aggregate group_column "A${yAggregate?c}" "SUM"/>"</#macro>
<#macro avg_cat group_column><@agg_safe group_column "A${yAggregate?c}" "AVERAGE"/></#macro>
<#macro sum_all_c><@sum_all "C"/></#macro>
<#macro avg_all_c><@avg_all "C"/></#macro>
<#macro sum_cat_c><@sum_cat "C"/></#macro>
<#macro sum_cat_d><@sum_cat "D"/></#macro>
<#macro sum_cat_e><@sum_cat "E"/></#macro>
<#macro avg_cat_c><@avg_cat "C"/></#macro>
<#macro avg_cat_d><@avg_cat "D"/></#macro>
<#macro avg_cat_e><@avg_cat "E"/></#macro>
<#macro prn_safe text><@inc_x/><#if text?has_content>"${text}"</#if></#macro>
"Rollup report"	"all concepts"	"${rollupReport.beginTime!}"	"to"	"${rollupReport.endTime!}"																												
"Concept"<#if (rollupReport.isPaExist())>			</#if>		<@twitter_followers_headers/>		<@expensive_stats_headers rollupReport.topics/>
<#assign x = 1>"Name"	<@inc_x/>"Twitter account"<#if (rollupReport.isPaExist())>	<@inc_x/>"Party"	<@inc_x/>"Constituency"	<@inc_x/>"Region"</#if>	<@twitter_followers_subheaders/>	<@inc_x/><#assign sx=x>"Sample rate (%)"	<@expensive_stats_subheaders rollupReport.topics/>
<#assign yOff = 4>
<#assign yListEnd = yOff - 1>
<#list rollupReport.timePeriodConceptReports as y>	
<#assign sxy=sx?upper_abc+(yOff + y_index)?c><#assign x = 1>"${y.concept.label}"	<@prn_safe (y.concept.twitterUserScreenName)!/><#if (rollupReport.isPaExist())>	<@prn_safe (y.concept.meta['pac.party'])!/>	<@prn_safe (y.concept.meta['pac.constituency'])!/>	<@prn_safe (y.concept.meta['pac.region'])!/></#if>	<@twitter_followers y y_index/>	<@inc_x/><#assign sx=x>${y.concept.meta['samplingRate']}	<@expensive_stats y y_index rollupReport.topics/>
<#assign yListEnd = yListEnd + 1>
</#list>
																																
<#if (rollupReport.isPaExist())>
<#assign yAggregate =  yListEnd + 3>
"AGGREGATES"																																
"All candidates"					<#assign x = 5><#list 0..2 as i><@sum_all_c/>	</#list><#list 0..4 as i><@avg_all_c/>	</#list><#list 0..1 as i><@sum_all_c/>	</#list><@avg_all_c/>	<@sum_all_c/>	<@avg_all_c/>	<#list rollupReport.topics as topic><#list 0..2 as i><@sum_all_c/>	</#list></#list><@sum_all_c/>	<#list rollupReport.topics as topic><@sum_all_c/>	</#list><#list 0..1 as i><@sum_all_c/>	</#list><@avg_all_c/>	<@sum_all_c/>	<@avg_all_c/>	<@sum_all_c/>
																																
<#assign yAggregate =  yAggregate + 1>
<#list rollupReport.parties as party>
<#assign yAggregate =  yAggregate + 1>
<@prn_safe party!/>					<#assign x = 5><#list 0..2 as i><@sum_cat_c/>	</#list><#list 0..4 as i><@avg_cat_c/>	</#list><#list 0..1 as i><@sum_cat_c/>	</#list><@avg_cat_c/>	<@sum_cat_c/>	<@avg_cat_c/>	<#list rollupReport.topics as topic><#list 0..2 as i><@sum_cat_c/>	</#list></#list><@sum_cat_c/>	<#list rollupReport.topics as topic><@sum_cat_c/>	</#list><#list 0..1 as i><@sum_cat_c/>	</#list><@avg_cat_c/>	<@sum_cat_c/>	<@avg_cat_c/>	<@sum_cat_c/>
</#list>
																																
<#assign yAggregate =  yAggregate + 1>
<#list rollupReport.constituencies as constituency>
<#assign yAggregate =  yAggregate + 1>
<@prn_safe constituency!/>					<#assign x = 5><#list 0..2 as i><@sum_cat_d/>	</#list><#list 0..4 as i><@avg_cat_d/>	</#list><#list 0..1 as i><@sum_cat_d/>	</#list><@avg_cat_d/>	<@sum_cat_d/>	<@avg_cat_d/>	<#list rollupReport.topics as topic><#list 0..2 as i><@sum_cat_d/>	</#list></#list><@sum_cat_d/>	<#list rollupReport.topics as topic><@sum_cat_d/>	</#list><#list 0..1 as i><@sum_cat_d/>	</#list><@avg_cat_d/>	<@sum_cat_d/>	<@avg_cat_d/>	<@sum_cat_d/>
</#list>
																																
<#assign yAggregate =  yAggregate + 1>
<#list rollupReport.regions as region>
<#assign yAggregate =  yAggregate + 1>
<@prn_safe region!/>					<#assign x = 5><#list 0..2 as i><@sum_cat_e/>	</#list><#list 0..4 as i><@avg_cat_e/>	</#list><#list 0..1 as i><@sum_cat_e/>	</#list><@avg_cat_e/>	<@sum_cat_e/>	<@avg_cat_e/>	<#list rollupReport.topics as topic><#list 0..2 as i><@sum_cat_e/>	</#list></#list><@sum_cat_e/>	<#list rollupReport.topics as topic><@sum_cat_e/>	</#list><#list 0..1 as i><@sum_cat_e/>	</#list><@avg_cat_e/>	<@sum_cat_e/>	<@avg_cat_e/>	<@sum_cat_e/>
</#list>
</#if>