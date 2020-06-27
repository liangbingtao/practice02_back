package practice02_back.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice02_back.model.School;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<School> regionList();


}
