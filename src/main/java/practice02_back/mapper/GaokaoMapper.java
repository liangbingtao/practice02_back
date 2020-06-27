package practice02_back.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice02_back.model.Gaokao;

import java.util.List;

@Mapper
public interface GaokaoMapper {

    List<Gaokao> getProAndCalendarData(String schoolName);

    List<Gaokao> selectSch(String province, String type);

    List<Gaokao> getSchoolByProname(String proname);
}
