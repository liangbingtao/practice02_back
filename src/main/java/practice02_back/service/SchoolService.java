package practice02_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice02_back.dto.PaginationDTO;
import practice02_back.mapper.SchoolMapper;
import practice02_back.model.School;

import java.util.List;

@Service
public class SchoolService {


    @Autowired
    private SchoolMapper schoolMapper;

    public PaginationDTO list(Integer page, Integer size, String[] areaId, String type, Integer is985, Integer is211, Integer isdoublefirstclass) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = schoolMapper.count(areaId,type,is985,is211,isdoublefirstclass);    //计算出学校总数
        /* 计算总页数 */
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        /* 容错处理*/
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page == 0) {
            page = 1;
        }
        paginationDTO.setPagination(totalPage, page);//setPagination方法来计算页面的展示逻辑
        Integer offset = size * (page - 1); //偏移量和页码的关系
//        PaginationDTO schools = schoolMapper.findAllSchool(offset, size, areaId, type, is985, is211, isdoublefirstclass);//获得问题列表
        List<School> schools = schoolMapper.findAllSchool(offset, size, areaId, type, is985, is211, isdoublefirstclass);//获得问题列表
        paginationDTO.setSchools(schools);

        return  paginationDTO;
    }

    public PaginationDTO findSchoolByName(String schoolname) {
        PaginationDTO paginationDTO=new PaginationDTO();
        List<School> school = schoolMapper.findSchool(schoolname);
        paginationDTO.setSchools(school);
        return paginationDTO;
    }

    public PaginationDTO listSchoolByreid(Integer reid,Integer page,Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = schoolMapper.countByReid(reid);    //计算出学校总数
        /* 计算总页数 */
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        /* 容错处理*/
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page == 0) {
            page = 1;
        }
        paginationDTO.setPagination(totalPage, page);//setPagination方法来计算页面的展示逻辑
        Integer offset = size * (page - 1); //偏移量和页码的关系
        List<School> schools = schoolMapper.findAllSchoolByreid(offset, size, reid);//获得问题列表
        paginationDTO.setSchools(schools);

        return  paginationDTO;
    }
}
