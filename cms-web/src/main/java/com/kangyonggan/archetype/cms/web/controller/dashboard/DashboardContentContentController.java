package com.kangyonggan.archetype.cms.web.controller.dashboard;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.archetype.cms.biz.service.ContentService;
import com.kangyonggan.archetype.cms.biz.service.DictionaryService;
import com.kangyonggan.archetype.cms.biz.service.UserService;
import com.kangyonggan.archetype.cms.model.constants.DictionaryType;
import com.kangyonggan.archetype.cms.model.vo.*;
import com.kangyonggan.archetype.cms.web.controller.BaseController;
import com.kangyonggan.archetype.cms.web.util.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/1/21
 */
@Controller
@RequestMapping("dashboard/content/content")
public class DashboardContentContentController extends BaseController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private FileUpload fileUpload;

    /**
     * 列表界面
     *
     * @param pageNum
     * @param template
     * @param title
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("CONTENT_CONTENT")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "template", required = false, defaultValue = "") String template,
                       @RequestParam(value = "title", required = false, defaultValue = "") String title,
                       Model model) {
        List<Content> contents = contentService.searchContents(pageNum, template, title);
        PageInfo<Content> page = new PageInfo(contents);
        List<Dictionary> templates = dictionaryService.findDictionariesByType(DictionaryType.TEMPLATE.getType());

        model.addAttribute("page", page);
        model.addAttribute("templates", templates);
        return getPathList();
    }

    /**
     * 添加界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("CONTENT_CONTENT")
    public String create(Model model) {
        model.addAttribute("content", new Content());
        model.addAttribute("templates", dictionaryService.findDictionariesByType(DictionaryType.TEMPLATE.getType()));
        return getPathForm();
    }

    /**
     * 保存内容
     *
     * @param attachments
     * @param content
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("CONTENT_CONTENT")
    @ResponseBody
    public Map<String, Object> save(@RequestParam(value = "attachment[]", required = false) List<MultipartFile> attachments,
                                    @ModelAttribute("content") @Valid Content content, BindingResult result) throws Exception {
        Map<String, Object> resultMap = getResultMap();

        if (!result.hasErrors()) {
            List<Attachment> files = null;
            if (attachments != null && !attachments.isEmpty()) {
                files = uploadAttachments(attachments);
            }

            contentService.saveContentWithAttachments(content, files);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 编辑界面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("CONTENT_CONTENT")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("content", contentService.findContentById(id));
        model.addAttribute("templates", dictionaryService.findDictionariesByType(DictionaryType.TEMPLATE.getType()));
        return getPathForm();
    }

    /**
     * 更新内容
     *
     * @param attachments
     * @param content
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("CONTENT_CONTENT")
    @ResponseBody
    public Map<String, Object> update(@RequestParam(value = "attachment[]", required = false) List<MultipartFile> attachments,
                                    @ModelAttribute("content") @Valid Content content, BindingResult result) throws Exception {
        Map<String, Object> resultMap = getResultMap();

        if (!result.hasErrors()) {
            List<Attachment> files = null;
            if (attachments != null && !attachments.isEmpty()) {
                files = uploadAttachments(attachments);
            }

            contentService.updateContentWithAttachments(content, files);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

    /**
     * 物理删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("CONTENT_CONTENT")
    public Map<String, Object> delete(@PathVariable("id") Long id) {
        contentService.deleteContent(id);
        return getResultMap();
    }

    /**
     * 上传附件
     *
     * @param attachments
     * @return
     * @throws FileUploadException
     */
    private List<Attachment> uploadAttachments(List<MultipartFile> attachments) throws FileUploadException {
        List<Attachment> files = new ArrayList();

        ShiroUser user = userService.getShiroUser();

        for (MultipartFile file : attachments) {
            if (file.isEmpty()) {
                continue;
            }

            String path = fileUpload.upload(file);

            Attachment attachment = new Attachment();
            attachment.setPath(path);
            attachment.setCreateUsername(user.getUsername());
            attachment.setName(file.getOriginalFilename());
            attachment.setType("content");

            files.add(attachment);
        }
        return files;
    }

}
