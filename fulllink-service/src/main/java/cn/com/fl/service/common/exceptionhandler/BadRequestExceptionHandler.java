package cn.com.fl.service.common.exceptionhandler;

import cn.com.doone.tocloud.entity.Result;
import cn.com.doone.tocloud.tools.MyLogUtil;
import cn.com.doone.tocloud.tools.MyLogger;
import cn.com.fl.service.PlatApplication;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @auther: Lxy
 * @description: 异常处理类
 * @create: 2021-09-03 14:02
 */
@RestControllerAdvice
public class BadRequestExceptionHandler {
    MyLogger logger = MyLogUtil.getLogger(PlatApplication.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationBodyException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        String message = "";
          if (result.hasErrors()) {
              List<ObjectError> errors = result.getAllErrors();
              for(ObjectError objectError : errors){
                  FieldError fieldError = (FieldError) objectError;
                  logger.log(MyLogUtil.LOG_ERROR,"Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                          "},errorMessage{"+fieldError.getDefaultMessage()+"}");
              message =fieldError.getDefaultMessage();
              }

          }
        return new Result().error(message);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result validationBindExceptionBodyException( BindException e){
        String message = "";
        for (ObjectError objectError : e.getBindingResult().getAllErrors()){
            logger.log(MyLogUtil.LOG_ERROR,"参数缺失："+objectError.getDefaultMessage());
            message+=objectError.getDefaultMessage()+",";
        }
        logger.log(MyLogUtil.LOG_ERROR,"参数缺失："+e.getMessage());
        return new Result().error(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handler(ConstraintViolationException e) throws IOException {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return new Result().error("参数缺失");
    }
}
