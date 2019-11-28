package homework.controller;

import homework.dto.UrlDto;
import homework.dto.ValueDto;
import homework.service.HttpHandlerService;
import homework.service.ParsingService;
import homework.service.impl.HttpHandlerIServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Repository
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HttpHandlerService httpHandlerService;
    @Autowired
    private ParsingService parsingService;

    @GetMapping("index")
    public String start(){
        return "index";
    }

//    @RequestMapping(value="/parse", method = RequestMethod.GET)
//    public String reStart(){
//        return "index";
//    }

    @RequestMapping(value="/parse", method = RequestMethod.GET)
    public ModelAndView parsing(HttpServletRequest req){
        ModelAndView mav = new ModelAndView();
        UrlDto urlDto = new UrlDto();

        try{
            urlDto.setUrl(req.getParameter("url"));
            urlDto.setParsingType(req.getParameter("parsingType"));
            urlDto.setNum(Integer.parseInt(req.getParameter("num")));
            String handleUrl = httpHandlerService.httpHandle(urlDto.getUrl());
            String type = urlDto.getParsingType();
            int num = urlDto.getNum();
            ValueDto valueDto = parsingService.parsing(handleUrl,type,num);
            mav.addObject("quotient",valueDto.getQuotient());
            mav.addObject("remainder",valueDto.getRemainder());
            mav.setViewName("index");

        }catch (NullPointerException e) {
            e.printStackTrace();
            mav.setViewName("redirect:index");
        }
        return mav;
    }
}
