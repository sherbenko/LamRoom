package com.sherbenko.exceptionHandlers;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
//        logger.info("SQLException Occured:: URL="+request.getRequestURL());

        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNHException(HttpServletRequest request, Exception ex) {


        return "error";
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ModelAndView handleRoomNotFoundException(HttpServletRequest request, Exception ex) {
//        logger.error("Requested URL=" + request.getRequestURL());
//        logger.error("Exception Raised=" + ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public String handleIOException() {
//        logger.error("IOException handler executed");

        return "error";
    }


    @ExceptionHandler({Exception.class})
    public String handleAll(Exception ex, HttpServletRequest request, Model model) {

        model.addAttribute("exception", ex);
        model.addAttribute("url", request.getRequestURL());
        return "error";
    }
}
