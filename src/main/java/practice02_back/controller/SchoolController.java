package practice02_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import practice02_back.dto.PaginationDTO;
import practice02_back.mapper.SchoolMapper;
import practice02_back.model.Gaokao;
import practice02_back.model.Profession;
import practice02_back.model.School;
import practice02_back.service.SchoolService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
//@ResponseBody
public class SchoolController {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolService schoolService;


    /**
     * @Description 在找大学页面展示所有大学按地区排序
     * @Param 无
     * @Return school类
     * @Author Mr.Liang
     * @Date 2020/4/4
     * @Time 9:36
     * @return
     */
    @RequestMapping("/schoolHomepage")
    public String findAllSchool(Model model,
                                HttpServletRequest request,
                                @RequestParam(name = "areaId", defaultValue = "-1") String[] areaId,
                                @RequestParam(name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "size", defaultValue = "10") Integer size,
                                @RequestParam(name = "type", required = false) String type,
                                @RequestParam(name = "is985", required = false) Integer is985,
                                @RequestParam(name = "is211", required = false) Integer is211,
                                @RequestParam(name = "isdoublefirstclass", required = false) Integer isdoublefirstclass) {

//        if (areaId != null) {
//            String[] split = areaId.split("_");
//            List<School> allSchool = schoolMapper.findAllSchool(split, type, is985, is211,isdoublefirstclass);
////            List<School> allSchool = schoolMapper.findAllSchool(areaId, type, is985, is211,isdoublefirstclass);
//            model.addAttribute("allschool", allSchool);
//            return "zhaodaxue";
//        }
//        else {
//            String[] split=null;
//            List<School> allSchool = schoolMapper.findAllSchool(split, type, is985, is211,isdoublefirstclass);
////            List<School> allSchool = schoolMapper.findAllSchool(areaId, type, is985, is211,isdoublefirstclass);
//            model.addAttribute("allschool", allSchool);
//            return "zhaodaxue";
//        }

//        List<School> allSchool = schoolMapper.findAllSchool(areaId, type, is985, is211, isdoublefirstclass);


//        ObjectMapper objectMapper = new ObjectMapper();
//        String strObject = "";
//        //list转json
//        try {
//            strObject = objectMapper.writeValueAsString(allSchool);
//            System.out.println(strObject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        model.addAttribute("allschool",allSchool);


        PaginationDTO allSchool = schoolService.list(page, size, areaId, type, is985, is211, isdoublefirstclass);


        model.addAttribute("allschool", allSchool);
        model.addAttribute("areaId", areaId);
        model.addAttribute("type", type);
        model.addAttribute("is985", is985);
        model.addAttribute("is211", is211);
        model.addAttribute("isdoublefirstclass", isdoublefirstclass);
        return "zhaodaxue";
//        return allSchool.getSchools();
    }

    /*
     * 输入学校名称查询相应的学校
     * */
    @RequestMapping("/schoolsearch")
    @ResponseBody
    public PaginationDTO getSchoolByName(HttpServletRequest request, Model model) {
        String schoolname = request.getParameter("schoolname");
        PaginationDTO schoolByName = schoolService.findSchoolByName(schoolname);
//        School schoolByName = schoolMapper.findSchoolByName(schoolname);
        model.addAttribute("allschool", schoolByName);
//        return "zhaodaxue";
        return schoolByName;
    }

    /*
     * 根据学校名称查找相应学校的专业
     * 对应网站上（根据大学选专业）
     * */
    @RequestMapping("/school/subject")
    public String getSchoolBySname(HttpServletRequest request, Model model) {
        String schoolName = request.getParameter("schoolName");
        List<Profession> schoolProByName = schoolMapper.getSchoolByName(schoolName);
        model.addAttribute("schoolPros", schoolProByName);
        return "school2profession";
    }

    /*
     * 根据专业名称查找开设该专业的学校
     * 对应（根据专业选科目）
     * */
    @RequestMapping("/school/major")
    public String getSchoolByPro(Model model, HttpServletRequest request) {
        String proname = request.getParameter("proname");
        List<Gaokao> schoolByPro = schoolMapper.getSchoolByPro(proname);
        model.addAttribute("schoolList", schoolByPro);
        return "profession2school";
    }

    /*
     * 首页“找大学/查专业”
     * */
    @RequestMapping("/indexSearch")

    public String getSchoolOrProfession(HttpServletRequest request, Model model) {
        String indexSearhInput = request.getParameter("indexSearhInput");
        model.addAttribute("indexSearhInput", indexSearhInput);
        return "indexsearch";

    }

    /*
     * 首页“找大学/查专业”---找大学
     * */
    @RequestMapping("/indexSearch/school")
    @ResponseBody
    public List<School> getSchool(HttpServletRequest request, Model model) {
        String indexSearchInput = request.getParameter("indexSearchInput");
        List<School> schools = schoolMapper.findSchool(indexSearchInput);
        model.addAttribute("schools", schools);
//        return "indexsearch";
        return schools;
    }


}
