<#list templates as t>
    <#if t.template==content.template>
    ${t.getName()}
    </#if>
</#list>