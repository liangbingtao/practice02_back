package practice02_back.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import practice02_back.model.News;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PdfUtil {

        public static void exportPDF(List<News> newsList, String title, OutputStream out1) throws IOException, DocumentException {
            // 第一步，实例化一个document对象
            Document document = new Document();
            // 第二步，设置要到出的路径
            FileOutputStream out = new  FileOutputStream("E:\\NEW\\"+title+".pdf");
            //如果是浏览器通过request请求需要在浏览器中输出则使用下面方式
            //OutputStream out = response.getOutputStream();
            // 第三步,设置字符 要导入itext 2.1.7包和itext-asian 5.2.0包.不然会报错
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Font fontZH = new Font(bfChinese, 12.0F, 0);
            // 第四步，将pdf文件输出到磁盘
            PdfWriter writer = PdfWriter.getInstance(document, out);//(OutputStream out1 = response.getOutputStream();）返回页面阅览out1，直接导出就用out
            // 第五步，打开生成的pdf文件
            document.open();
            // 第六步,设置内容
            Font titieFont = new Font(bfChinese, 15, Font.NORMAL);
            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setFont(titieFont);
            Chunk chunk = new Chunk("新闻明细表");
            paragraph.add(chunk);
            //网页标签名
            document.addTitle("Set Attribute Example");
            document.add(paragraph);
            document.add(new Paragraph("\n"));

            // 创建table,注意这里的2是两列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
            PdfPTable table = new PdfPTable(5);
            //设置表格宽度比例为100%
            table.setWidthPercentage(100.0F);
            //每页都输出表头
            table.setHeaderRows(1);
            table.getDefaultCell().setHorizontalAlignment(1);
            table.addCell(new Paragraph("id", fontZH));
            table.addCell(new Paragraph("标题", fontZH));
            table.addCell(new Paragraph("内容", fontZH));
            table.addCell(new Paragraph("作者", fontZH));
            table.addCell(new Paragraph("发布时间", fontZH));
//            table.addCell(new Paragraph("地址", fontZH));
            if(newsList.size()==0){
                table.addCell(new Paragraph("无", fontZH));
                table.addCell(new Paragraph("无", fontZH));
                table.addCell(new Paragraph("无", fontZH));
                table.addCell(new Paragraph("无", fontZH));
                table.addCell(new Paragraph("无", fontZH));
            }else{
                for(int i=0;i<newsList.size();i++){
                    table.addCell(new Paragraph(String.valueOf(newsList.get(i).getId()), fontZH));
                    table.addCell(new Paragraph(newsList.get(i).getTitle(), fontZH));
                    table.addCell(new Paragraph(newsList.get(i).getContent(), fontZH));
                    table.addCell(new Paragraph(String.valueOf(newsList.get(i).getAuthor()), fontZH));
                    table.addCell(new Paragraph(newsList.get(i).getReleaseTime(), fontZH));
//                    table.addCell(new Paragraph(newsList.get(i).getAddress(), fontZH));
                }
            }
            document.add(table);
            document.add(new Paragraph("\n"));

            Chunk chunkEnd = new Chunk("java导出PDF");
            Paragraph paragraphEND = new Paragraph();
            paragraphEND.setAlignment(Element.ALIGN_CENTER);
            paragraphEND.setFont(titieFont);
            paragraphEND.add(chunkEnd);
            document.add(paragraphEND);

            // 第七步，关闭document
            document.close();
            System.out.println("导出pdf成功————"+title);
        }


}
