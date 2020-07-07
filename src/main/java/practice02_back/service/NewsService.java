package practice02_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice02_back.mapper.NewsMapper;
import practice02_back.model.News;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;


    public void insertAll(List<News> allnews) {
        for (News news : allnews) {
            newsMapper.insertAll(news);
        }

    }

    public List<News> select() {
        List<News> allnews = newsMapper.findAllNews();
        return allnews;
    }
}
