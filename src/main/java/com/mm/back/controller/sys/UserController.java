package com.mm.back.controller.sys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.Menu;
import com.mm.back.common.Role;
import com.mm.back.common.WebResponse;
import com.mm.back.entity.UserEntity;
import com.mm.back.service.UserService;
import com.mm.back.utils.MD5Utils;
import com.mm.back.utils.UserUtils;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    /**
     * 用户列表
     *
     * @param state
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public WebResponse list(Integer state) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        webResponse.setData(this.userService.getUserList(state));
        return webResponse;

    }

    @RequestMapping("/saveOrUpdateUserRole")
    @ResponseBody
    public WebResponse saveOrUpdateUserRole(Integer userId, Integer roleId, Integer operate) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.userService.saveOrUpdateUserRole(userId, roleId, operate);
        return webResponse;

    }

    @RequestMapping("/getRoles")
    @ResponseBody
    public WebResponse getRoles(Integer userId) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        List<Role> list = this.userService.getRoles(userId);
        webResponse.setData(list);
        return webResponse;

    }

    /**
     * @param user
     * @return SystemUserEntity 返回类型
     * @Description: 修改
     * @author chenyanlong
     * @date 2015年11月26日 上午9:26:58
     */
    @RequestMapping("/update")
    @ResponseBody
    public WebResponse update(UserEntity user) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        UserEntity entity = this.userService.updateEntity(user);
        String password = user.getPassword();
        String username = user.getUserName();
        String pwd = MD5Utils.getMD5(username + password);
        user.setPassword(pwd);
        return webResponse;
    }

    /**
     * @param id
     * @return SystemUserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:51:24
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public WebResponse getUserInfo(Integer id) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        webResponse.setData(this.userService.getUserInfo(id));
        return webResponse;
    }

    /**
     * @param user
     * @return SystemUserEntity 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月11日 下午2:27:13
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public WebResponse saveOrUpdate(UserEntity user) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        if (null != user) {
            String userName = user.getUserName();
            String password = user.getPassword();
            String pwd = MD5Utils.getMD5(userName + password);
            user.setPassword(pwd);
            if (user.getId() != null) {
                this.userService.updateEntity(user);
            } else {
                this.userService.saveEntity(user);
            }
        }
        return webResponse;
    }

    /**
     * @Description:
     * @author chenyanlong
     * @date 2016年4月1日 上午11:31:42
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public WebResponse changePassword(Integer userId, String newPassword, String oldPassword) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.userService.changePassword(userId, newPassword, oldPassword);
        return webResponse;
    }

    /**
     * @param
     * @return Boolean 返回类型
     * @Description:
     * @author chenyanlong
     * @date 2015年12月21日 下午6:14:31
     */
    @RequestMapping("/del")
    @ResponseBody
    public WebResponse del(Integer id) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        this.userService.del(id);
        return webResponse;
    }

    /**
     * @return List<Module> 返回类型
     * @Description: 根据用户信息查询左边栏
     * @author chenyanlong
     * @date 2015年11月23日 下午2:26:35
     */
    @RequestMapping("/left")
    @ResponseBody
    public WebResponse getLeftModuleList() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        // 检查页面的cookie
        List<Menu> menus = UserUtils.getMenus();
        webResponse.setData(menus);
        return webResponse;
    }
}
