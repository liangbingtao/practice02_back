package practice02_back.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author chenqi
 * @ClassName: ExcelUtils
 * @Description: excle工具类
 * @date 2018年11月17日
 */
@Slf4j
public class ExcelUtils {
    /**
     * @param file       文件
     * @param headerRows 忽略头行数
     * @param pojoClass  转换的实体
     * @return List<User>  返回的集合
     * @Title: importData
     * @Description: 导入excle 数据
     */
    public static <T> List<T> importData(MultipartFile file, Integer headerRows,
                                         Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param list      导出的数据
     * @param title     文件标题
     * @param sheetName sheet名称
     * @param pojoClass 集合的类
     * @param fileName  文件名
     * @param response
     * @Title: exportExcel
     * @Description: 导出excel
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,
                                   String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), pojoClass, list);
        if (workbook != null) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
//                                       boolean isCreateHeader, HttpServletResponse response) {
//            ExportParams exportParams = new ExportParams(title, sheetName);
//            exportParams.setCreateHeadRows(isCreateHeader);
//            defaultExport(list, pojoClass, fileName, response, exportParams);
//        }

//        public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
//                                       HttpServletResponse response) {
//            defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
//        }


    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null)
            ;
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            // throw new NormalException(e.getMessage());
            log.error("down load excel: {}", e);
        }
    }


}
