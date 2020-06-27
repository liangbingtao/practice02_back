package practice02_back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import practice02_back.model.Profession;

import java.util.List;

@Mapper
public interface ProfessionMapper {

    List<Profession> getProfessionByScid(@Param("scid") int scid);

    List<Profession> selectDistinctMajor();

    Profession selectByPid(Integer code);

    List<Profession> getProfessionByProname(String profession);

    List<Profession> getDistinctMajor();

    Profession getProfessionDescByProname(String proname);

    Integer countByMajor(String major);

    List<Profession> findProfessionByMajor(String major, Integer offset, Integer size);
}
