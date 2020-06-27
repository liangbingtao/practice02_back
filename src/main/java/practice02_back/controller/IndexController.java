package practice02_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "new";
    }

    @GetMapping("/myceping")
    public String myceping() {
        return "myceping";
    }

    @GetMapping("/myfuwu")
    public String myfuwu() {
        return "myfuwu";
    }

    @GetMapping("/zixun")
    public String zixun() {
        return "zixun";
    }

    @GetMapping("/xingaokao")
    public String xingaokao() {
        return "xingaokao";
    }

    @GetMapping("/xueyeceping")
    public String xueyeceping() {
        return "xueyeceping";
    }

    @GetMapping("/jiangtang")
    public String jiangtang() {
        return "jiangtang";
    }

    @GetMapping("/kecheng")
    public String kecheng() {
        return "kecheng";
    }

    @GetMapping("/fuwu")
    public String fuwu() {
        return "fuwu";
    }

    @GetMapping("/zixunshi")
    public String zixunshi() {
        return "zixunshi";
    }

    @GetMapping("/xuanke")
    public String xuanke() {
        return "xuanke";
    }

    @GetMapping("/xuanke2")
    public String xuanke2() {
        return "xuanke2";
    }

    @GetMapping("/school2profession")
    public String school2profession() {
        return "school2profession";
    }

    @GetMapping("/profession2school")
    public String profession2school() {
        return "profession2school";
    }

    @GetMapping("/zhaozhuanye")
    public String zhaozhuanye() {
        return "zhaozhuanye";
    }

    @GetMapping("/zhaodaxue")
    public String zhaodaxue() {
        return "zhaodaxueBefore";
    }

    @GetMapping("/weici")
    public String weici() {
        return "weici";
    }

    @GetMapping("/fenshuxian")
    public String fenshuxian() {
        return "fenshuxian";
    }

    @GetMapping("/picixian")
    public String picixian() {
        return "picixian";
    }

    @GetMapping("/zhinengtianbao")
    public String zhinengtianbao() {
        return "zhinengtianbao";
    }

    @GetMapping("/1dui1")
    public String oneToone(){
        return "1dui1";
    }

    @GetMapping("/kaoshi")
    public String kaoshi(){
        return "kaoshi";
    }

    @GetMapping("/subject")
    public String subject(){
        return "subject";
    }
}
