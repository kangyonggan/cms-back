package com.kangyonggan.archetype.cms.web.controller.dashboard;

import com.kangyonggan.archetype.cms.biz.service.UserService;
import com.kangyonggan.archetype.cms.model.vo.ShiroUser;
import com.kangyonggan.archetype.cms.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2017/1/20
 */
@Controller
@RequestMapping("dashboard/user")
public class DashboardUserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 基本信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    @RequiresPermissions("USER_PROFILE")
    public String profile(Model model) {
        ShiroUser user = userService.getShiroUser();

        model.addAttribute("user", userService.findUserById(user.getId()));
        model.addAttribute("userProfile", userService.findUserProfileByUsername(user.getUsername()));
        return getPathRoot() + "/profile";
    }

}
