package practice02_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice02_back.dto.PaginationDTO;
import practice02_back.mapper.GaokaoMapper;
import practice02_back.mapper.ProfessionMapper;
import practice02_back.mapper.SchoolMapper;
import practice02_back.model.Gaokao;
import practice02_back.model.Profession;
import practice02_back.service.ProfessionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfessionController {
    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private GaokaoMapper gaokaoMapper;
    @Autowired
    private ProfessionService professionService;

    /**
     *根据学校scid查找该学校开设的专业
     */

    @GetMapping("/profession/{scid}")
    public String profession(Model model,
                             @PathVariable(name = "scid") int scid){
        List<Profession> professionByScid = professionMapper.getProfessionByScid(scid);
        model.addAttribute("professions",professionByScid);
        return "";
    }


    /*
    * 查找所有的学科门类和门类下所包含的专业pid,专业名称，专业代码
    * 对应网站（“查专业”）
    */
    @GetMapping("/majors/Homepage")
    public String majors(Model model) {
        List<Profession> majors = professionMapper.selectDistinctMajor();
        model.addAttribute("majors",majors);
        return "zhaozhuanye";
    }

    /*
    * 根据输入的专业名称查找该专业的相关信息
    * 对应网站（“查专业”-->"输入关键词，搜索"）
    * */

    @RequestMapping("/majors")
    @ResponseBody
    public PaginationDTO getProfession(HttpServletRequest request, Model model){
        String profession = request.getParameter("profession");
        PaginationDTO professionByProname = professionService.getProfessionByProname(profession);
        model.addAttribute("majors", professionByProname);
//        return "zhaozhuanye";
        return professionByProname;
    }

    //查找所有的学科，如工学，农学，文学
    @RequestMapping("/majorList")
    @ResponseBody
    public List<Profession> getMajorList(HttpServletRequest request, Model model){
        List<Profession> distinctMajor = professionMapper.getDistinctMajor();
        return distinctMajor;
    }



    /*
    * 根据专业pid查找对应专业的具体信息，如专业描述，修业年限等信息
    * 对应“查专业”页面中点击专业操作
    * */
    @GetMapping("/profession_desc/{pid}")
    public String profession(Model model,
                             @PathVariable(name="pid") Integer pid){
        Profession profession = professionMapper.selectByPid(pid);
        List<Gaokao> schoolByPid = schoolMapper.getSchoolByPid(pid);
        model.addAttribute("profession",profession);
        model.addAttribute("schools",schoolByPid);
        return "profession_desc";
    }

    /*
    * 首页“找大学/查专业”----查专业
    * */
    @RequestMapping("/indexSearch/profession")
    @ResponseBody
    public PaginationDTO getProfessions(HttpServletRequest request, Model model){
        String indexSearchInput = request.getParameter("indexSearchInput");
        PaginationDTO professionByProname = professionService.getProfessionByProname(indexSearchInput);
        model.addAttribute("professions",professionByProname);
//        return "indexsearch";
        return professionByProname;
    }

    //根据学科类别查找对应的专业，如工科，农科，文科下的专业
    @RequestMapping("/major")
    @ResponseBody
    public PaginationDTO getProfessions(@RequestParam("major") String major,
                                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "15") Integer size){
        PaginationDTO professions = professionService.listProfessionByMajor(major, page, size);
        return professions;
    }

    //根据专业名称查找专业介绍及开设该专业的院校
    @RequestMapping("/professionInfo/{proname}")
    @ResponseBody
    public Profession getProfessionInfo(@PathVariable("proname") String proname){
        Profession professionDescByProname = professionMapper.getProfessionDescByProname(proname);
        List<Gaokao> schoolByProname = gaokaoMapper.getSchoolByProname(proname);
        professionDescByProname.setGaokaos(schoolByProname);
        return  professionDescByProname;
    }
}
