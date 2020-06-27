package practice02_back.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice02_back.dto.PaginationDTO;
import practice02_back.mapper.ProfessionMapper;
import practice02_back.model.Profession;

import java.util.List;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionMapper professionMapper;

    public PaginationDTO listProfessionByMajor(String major, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = professionMapper.countByMajor(major);  //计算出该学科门类下共有多少种专业
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
        List<Profession> professionByMajor = professionMapper.findProfessionByMajor(major, offset, size);
        paginationDTO.setProfessions(professionByMajor);

        return  paginationDTO;
    }

    public PaginationDTO getProfessionByProname(String profession) {
        PaginationDTO paginationDTO=new PaginationDTO();
        List<Profession> professionByProname = professionMapper.getProfessionByProname(profession);
        paginationDTO.setProfessions(professionByProname);
        return paginationDTO;
    }
}
