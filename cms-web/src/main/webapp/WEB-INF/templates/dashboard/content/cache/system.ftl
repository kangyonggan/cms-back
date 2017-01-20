<#list systems as sys>
    <#if sys.code==system>
        ${sys.getName()}
    </#if>
</#list>