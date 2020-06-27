package practice02_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import practice02_back.dto.PaginationDTO;
import practice02_back.mapper.AreaMapper;
import practice02_back.model.School;
import practice02_back.service.SchoolService;

import java.util.List;

@Controller
public class AreaController {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private SchoolService schoolService;

    /*
    /根据地区、省份查找学校
    1.查找所有地区（华北地区，华中地区，西北地区...）
     */
    @RequestMapping("/area")
    @ResponseBody
    public List<School> area(Model model) {
        List<School> regionList = areaMapper.regionList();
//        List<School> areaList = areaMapper.list();
//        model.addAttribute("areas", areaList);
        model.addAttribute("regions", regionList);
//        return "regions";
        return regionList;
    }


    /*
    /根据地区、省份查找学校
    2.根据地区reid查找该地区所有省份
     */
//    @RequestMapping("/area/{reid}")
//    @ResponseBody
//    public PaginationDTO schoolList(@PathVariable(name="reid") Integer reid, Model model,
//                                   @RequestParam(name = "page", defaultValue = "1") Integer page,
//                                   @RequestParam(name = "size", defaultValue = "10") Integer size){
//        PaginationDTO allSchool = schoolService.listSchoolByreid(reid,page,size);
//
//        List<School> areaByReid = areaMapper.getSchoolByReid(reid);
//        model.addAttribute("areas",areaByReid);
////        return "area";
//        return allSchool;
//    }

    @RequestMapping("/area2")
    @ResponseBody
    public PaginationDTO schoolList(@RequestParam(name="reid") Integer reid, Model model,
                                    @RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size){
        PaginationDTO allSchool = schoolService.listSchoolByreid(reid,page,size);
        return allSchool;
    }
}
