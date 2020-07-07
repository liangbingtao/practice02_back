package practice02_back.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


@Data
public class News {

    @Excel(name = "id")
    private Integer id;

    @Excel(name = "title")
    private String title;

    @Excel(name = "content")
    private String content;

    @Excel(name = "author")
    private String author;

    @Excel(name = "releaseTime",width = 20)
    private String releaseTime;



}
