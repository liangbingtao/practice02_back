package practice02_back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import practice02_back.model.Schoolscore;

import java.util.List;

@Mapper
public interface ScoreLineMapper {

    List<Schoolscore> getScoresBySchoolName(String schoolName);

    int getRankByScore(@Param("score") int score, @Param("sort") int sort);
}
