package practice02_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import practice02_back.mapper.SchoolMapper;
import practice02_back.model.Profession;
import practice02_back.model.School;

import java.util.List;


@Controller
public class SchoolDescController {
    @Autowired
    private SchoolMapper schoolMapper;


    /*
    * 根据学校id查询学校具体信息，如学校简介，学校层次等
    * */
    @RequestMapping("/school_desc_l/{scid}")
    @ResponseBody
    public School profession(@PathVariable("scid") int scid, Model model) {
        School school = schoolMapper.findByScid(scid);
        List<Profession> proByScid = schoolMapper.getProByScid(scid);
        model.addAttribute("school",school);
        model.addAttribute("professions",proByScid);
        school.setProfessions(proByScid);
        return school;
    }
}
