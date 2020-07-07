package practice02_back.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice02_back.model.News;

import java.util.List;

@Mapper
public interface NewsMapper {

    void insertNews(News news);

    List<News> findAllNews();

    void deleteNewsById(Integer id);

    News findNewsById(Integer id);

    void updateNews(News news);

    void insertAll(News news);

}
