package practice02_back.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice02_back.mapper.UserMapper;
import practice02_back.model.User;
import practice02_back.util.PhoneCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;


@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;


    @Data
    class UserInfo {
        private String phone;
        private String code;
    }

    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //注册方法
    @RequestMapping("/addregister")
    @ResponseBody
    public String register(HttpServletRequest request, Map<String, Object> map) {
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String password = request.getParameter("password")          ;
        String password2 = request.getParameter("password2");
        User userIdentify = userMapper.findByUsername(username);
        User dbuser = userMapper.selectByPhone(userPhone);
        if (dbuser == null) {
            //说明该手机号没有被注册，可以注册该手机号
            if (password.length() < 6 || password.length() > 16) {
                map.put("msg", "密码过短或过长");
                return "fault";          // 注册失败
            }else if (password.equals(password2) && userIdentify ==null){
                //密码输入正确，并且该用户名可以被使用，执行注册操作
                User user =new User();
                user.setUserName(username);
                user.setPassWord(password);
                user.setUserphone(userPhone);
                userMapper.save(user);
                map.put("msg","注册成功，请登录");
                return "success";        //注册成功
            }else{
                map.put("msg","用户名已存在或两次密码不一致");
                return "fault";      //注册失败
            }
        }else{
            map.put("msg", "该手机号已注册，请直接登录");
            return "success";
        }
    }

    //使用手机登录页面
    @GetMapping("/sendcode")
    public String sendcode() {
        return "sendcode";
    }

    //验证码页面
    @GetMapping("/phonelogin")
    public String phonelogin() {
        return "phonelogin";
    }

    @RequestMapping("/sendcode")
    public String sendcode(HttpServletRequest request, Map<String, Object> map) {
        String phone = request.getParameter("userPhone");
        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            HttpSession session = request.getSession();
            String code = PhoneCode.vcode();
            UserInfo userInfo = new UserInfo();
            userInfo.setPhone(phone);
            userInfo.setCode(code);
            String sms = PhoneCode.getPhonemsg(phone, code);
            if (sms.equals("-1")) {
                map.put("msg", "获取验证码失败,请稍后重试或联系管理员！");
                return "sendcode";
            }
            session.setAttribute("userInfo", userInfo);
            return "phonelogin";
        } else {
            map.put("msg", "该手机号尚未注册，请先注册！");
            return "sendcode";
        }

    }

    @RequestMapping("/phonelogin")
    public String phonelogin(HttpServletRequest request,
                             HttpServletResponse response,
                             Map<String, Object> map,
                             Model model) {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (!code.equals(userInfo.getCode())) {
            map.put("msg", "验证码输入错误！");
            return "phonelogin";
        } else {
            User user = userMapper.selectByPhone(userInfo.getPhone());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userMapper.updateUser(user);
            Cookie cookie =new Cookie("token",token);
            cookie.setMaxAge(60*60*24*7);       //设置cookie保存时间为一周
            response.addCookie(cookie);
            session.setAttribute("user", user);
            map.put("msg", "登陆成功");
            return "redirect:/";
        }
    }

    //登录页面
    @RequestMapping("/denglu")
    public String login() {
        return "denglu";
    }

    //登录方法
    @RequestMapping("/addlogin")
    @ResponseBody
    public User login(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password,
                      Map<String, Object> map) {
        HttpSession session = request.getSession();
        User dbUser = userMapper.findByUsernameAndPassword(username, password);
        return dbUser;
//        if (dbUser != null) {   //user不为空，说明数据库中已经有该用户信息，进行更新操作
//            String token = UUID.randomUUID().toString();
//            dbUser.setToken(token);
//            userMapper.updateUser(dbUser);
//            Cookie cookie =new Cookie("token",token);
//            System.out.println(token);
//            cookie.setMaxAge(60*60*24*7);       //设置cookie保存时间为一周
//            response.addCookie(cookie);
//            session.setAttribute("user", dbUser);
//            System.out.println(session.getId());
//            map.put("msg", "登陆成功");
////            model.addAttribute("msg", "登陆成功");
//            return "redirect:/";
//        } else {
//            map.put("msg", "密码或用户名错误！");
////            model.addAttribute("msg", "登陆失败");
//            return "denglu";
//        }
    }

    //更新用户信息
    @RequestMapping("/updateUser")
    @ResponseBody
    public Integer updateUser(@RequestBody User user){
        userMapper.updateUser(user);
        return 1;
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");   //移除session
        Cookie cookie = new Cookie("token", null);  //要删除cookie需要新建一个同名的cookie，并将value设置为null
        cookie.setMaxAge(0);    //立即删除型
        response.addCookie(cookie);//删除cookie
        return "redirect:/";
    }
}
