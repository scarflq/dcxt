package dcxt.controller;

import dcxt.bean.Msg;
import dcxt.service.Order_Service;
import dcxt.service.OtherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("")
public class OtherController {
    @Resource
    OtherService otherService;


}
