package com.mm.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mm.common.exception.ServiceException;

/**
 * Created by suchenguang on 17/6/26.
 */
public class DownloadUtil {

    private DownloadUtil() {
    }

    public static final void downloadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Workbook workbook) {

        response.setContentType("application/vnd.ms-excel");
        OutputStream bos = null;
        try {
            request.setCharacterEncoding("UTF-8");
            bos = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
            workbook.write(bos);
        } catch (Exception e) {
            throw new ServiceException("数据下载异常！", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new ServiceException("数据流关闭异常", e);
                }
            }
        }
    }
}
