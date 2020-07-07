package practice02_back.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice02_back.mapper.NewsMapper;
import practice02_back.model.News;
import practice02_back.model.User;
import practice02_back.service.NewsService;
import practice02_back.util.ExcelUtils;
import practice02_back.util.PdfUtil;
import practice02_back.util.WordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NewsController {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    //增
    @RequestMapping("/insertNews")
    public void insertNews(
            @RequestParam("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestBody User user) {
        News newsById = newsMapper.findNewsById(id);
        News news=new News();
        news.setId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setAuthor(user.getUserName());
        if (newsById==null){
            Date now = new Date();
            System.out.println("mow"+now);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String createTime = dateFormat.format(now);//格式化然后放入字符串中
            news.setReleaseTime(createTime);
            newsMapper.insertNews(news);
        }else{
            newsMapper.updateNews(news);
        }
    }

    //删
    @RequestMapping("/deleteNews/{id}")
    public void deleteNews(@PathVariable("id") Integer id){
        newsMapper.deleteNewsById(id);

    }

    //改
    @RequestMapping("/updateNews/{id}")
    public void updateNews(@PathVariable("id") Integer id){
        News newsById = newsMapper.findNewsById(id);
    }

    /* 查
    * 查询所有新闻
    * */
    @RequestMapping("/selectNews")
    public List<News> selectNews(){
        List<News> allNews = newsMapper.findAllNews();
        return allNews;
    }

    //根据id查找新闻
    @RequestMapping("/findNewsById/{id}")
    public News findNewsById(@PathVariable("id") Integer id){
        News newsById = newsMapper.findNewsById(id);
        return newsById;

    }


    /*
    * excel文件导入数据库
    * */
    @PostMapping("/impNews")
    public String impNews( MultipartFile file){
        List<News> news = ExcelUtils.importData(file, 1, News.class);
        newsService.insertAll(news);
        return "success";
    }


    /*
    * 数据库表导出到excel
    * */
    @GetMapping("/expNews")
    public List<News> expNews(HttpServletResponse response){
        List<News> news = newsService.select();
        if(news != null && news.size() > 0){
            ExcelUtils.exportExcel( news,null, "新闻数据", News.class, "新闻数据表.xls", response);
        }
        return news;
    }



    /*
    * 数据库表导出到word
    * */
    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("title","我是1号标题");
        params.put("content","我是1号内容");
        params.put("author","1号");
        params.put("releaseTime","2020-07-06");
        //这里是我说的一行代码
        WordUtils.exportWord("/word/newsTemplate.docx","E:/word","aaa.docx",params,request,response);
    }

    /*
    * 导出到PDF
    * */
    @RequestMapping(value = "/UserExportPDF")
    public @ResponseBody String UserExportPDF(HttpServletResponse response) throws IOException, DocumentException {
        OutputStream out1 = response.getOutputStream();
        List<News> newsList = newsService.select();
//        ExportPDF exportPDF=new ExportPDF();
        PdfUtil.exportPDF(newsList,"新闻明细表",out1);
        return "导出PDF成功";
    }

}
