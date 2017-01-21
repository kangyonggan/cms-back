<#assign ctx="${(rca.contextPath)!''}">

<div class="space-20"></div>

<div class="col-xs-10 col-xs-offset-1">
    <div class="widget-box transparent">
        <div class="widget-header widget-header-large">
            <h3 class="widget-title grey lighter">
                <i class="ace-icon fa fa-leaf green"></i>
            ${content.title}
            </h3>

            <div class="widget-toolbar invoice-info">
                <span class="blue"><@c.relative_date datetime=content.createdTime/></span>
            </div>
        </div>

        <div class="space-10"></div>

        <div class="widget-body">
            <div class="widget-main">
                <div class="row markdown-content">
                ${content.body}
                </div>
            </div>
        </div>

    <#if attachments?size gt 0>

        <div class="space-10"></div>

        <div class="hr hr-18 dotted"></div>

        <h4>附件:</h4>

        <div class="space-10"></div>

        <@apps>
            <#list attachments as attachment>
                <a href="${ftpUrl}/${attachment.path}" target="_blank">${attachment.name}</a>
                <div class="space-10"></div>
            </#list>
        </@apps>
    </#if>
    </div>
</div>

<script>
    var title = '${content.title}';
</script>
<script src="${ctx}/static/app/js/web/content/detail.js"></script>
