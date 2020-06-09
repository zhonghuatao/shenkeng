package com.tao.shenkeng.advice;

import com.alibaba.fastjson.JSON;
import com.tao.shenkeng.dto.ResultDTO;
import com.tao.shenkeng.exception.CustomizeErrorCode;
import com.tao.shenkeng.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

        @ExceptionHandler(Exception.class)
        ModelAndView handle(Throwable e, Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
            String contentType = request.getContentType();
            if ("application/json".equals(contentType)){
                ResultDTO resultDTO;
                if (e instanceof CustomizeException){
                  resultDTO = ResultDTO.errorOf((CustomizeException) e);
                }else{
                  resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
                }

                try {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    response.setStatus(200);
                    PrintWriter writer = response.getWriter();
                    writer.write(JSON.toJSONString(resultDTO));
                    writer.close();
                } catch (IOException ex) {

                }
                return null;
                }else{
                    if (e instanceof CustomizeException){
                        model.addAttribute("message",e.getMessage());
                    }else{
                        model.addAttribute(CustomizeErrorCode.SYS_ERROR);
                    }
                    return new ModelAndView("error");
                }
        }
}
