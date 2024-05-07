package org.Big_Event.exception;

import org.Big_Event.pojo.Result;
import org.apache.catalina.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;


@RestController
@ControllerAdvice
public class GlovalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        e.printStackTrace();
        //System.out.println("操作失败");
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"操作失败");
    }
}
