package practice02_back.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import practice02_back.model.Gaokao;
import practice02_back.model.Profession;
import practice02_back.model.School;

import java.util.List;

@Mapper
public interface SchoolMapper {

    List<School> selectNameByAreaid(@Param("areaid") int areaid);

    List<Gaokao> getSchoolByPro(String proname);

    List<Profession> getSchoolByName(String schoolName);

//        List<School> findAllSchool(@Param("areaId") String[] areaId, @Param("type") String type, @Param("is985") Integer is985, @Param("is211") Integer is211, @Param("isdoublefirstclass") Integer isdoublefirstclass);
    List<School> findAllSchool(@Param("offset") Integer offset, @Param("size") Integer size, @Param("areaId") String[] areaId, @Param("type") String type, @Param("is985") Integer is985, @Param("is211") Integer is211, @Param("isdoublefirstclass") Integer isdoublefirstclass);

    List<School> findSchool(@Param("schoolname") String schoolname);

    Integer count(@Param("areaId") String[] areaId, @Param("type") String type, @Param("is985") Integer is985, @Param("is211") Integer is211, @Param("isdoublefirstclass") Integer isdoublefirstclass);

    School findByScid(int scid);

    List<Profession> getProByScid(int scid);

    List<Gaokao> getSchoolByPid(Integer pid);

    School findSchoolByName(String schoolname);

    Integer countByReid(Integer reid);

    List<School> findAllSchoolByreid(Integer offset, Integer size, Integer reid);

}
