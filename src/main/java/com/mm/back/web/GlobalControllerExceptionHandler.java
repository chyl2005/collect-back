package com.mm.back.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.mm.back.common.ThreadLocalContext;
import com.mm.back.common.ThreadLocalIndex;
import com.mm.back.common.WebResponse;
import com.mm.back.utils.JsonUtils;

/**
 * Author:chyl2005
 * Date:17/3/26
 * Time:10:13
 * Desc:
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse handleException(HttpServletRequest req, Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            writeWarnLog(req, ex);
            return WebResponse.getParamErrorWebResponse(ex.getMessage());
        } else if (ex instanceof MissingServletRequestParameterException) {
            /**
             * 参数缺失错误
             */
            writeWarnLog(req, ex);
            return WebResponse.getParamErrorWebResponse(ex.getMessage());
        } else if (ex instanceof HttpMessageNotReadableException) {
            /**
             * requestBody中参数不符合规范
             */
            writeWarnLog(req, ex);
            return WebResponse.getParamErrorWebResponse(ex.getMessage());
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            /**
             * requestParam中参数不符合规范
             */
            writeWarnLog(req, ex);
            return WebResponse.getParamErrorWebResponse(ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            /**
             * 参数使用了valid注解
             */
            writeWarnLog(req, ex);
            return WebResponse.getParamErrorWebResponse(ex.getMessage());

        }
        writeErrorLog(req, ex);
        return WebResponse.getErrorWebResponse();
    }

    private void writeErrorLog(HttpServletRequest req, Exception ex) {
        Map<String,Object> extraParams = ThreadLocalContext.get(ThreadLocalIndex.EXTRA_PARAMS);
        String params = ThreadLocalContext.get(ThreadLocalIndex.PARAMS);
        LOGGER.error(req.getRequestURI() + ", parameter " + req.getQueryString() +
               "[REQUEST:" + params + "EXTRAPARAMS:[" + JsonUtils.object2Json(extraParams) + "]]",
                ex);
    }

    private void writeWarnLog(HttpServletRequest req, Exception ex) {
        Map<String,Object> extraParams = ThreadLocalContext.get(ThreadLocalIndex.EXTRA_PARAMS);
        String params = ThreadLocalContext.get(ThreadLocalIndex.PARAMS);
        LOGGER.warn(req.getRequestURI() + ", parameter " + req.getQueryString()+ " , METHOD:["+req.getMethod()+"] "+
                "[REQUEST:" + params + "EXTRAPARAMS:[" + JsonUtils.object2Json(extraParams) + "]]",
                 ex);
    }
}
