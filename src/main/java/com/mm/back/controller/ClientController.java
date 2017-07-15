package com.mm.back.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mm.back.common.WebResponse;
import com.mm.back.constants.CommandEnum;
import com.mm.back.netty.ServerHandler;
import com.mm.back.service.HandlerMessageService;
import com.mm.back.vo.ClientInfoVo;
import com.mm.back.vo.CommandVo;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/15
 * Time:18:34
 * Desc:客户端交互
 */

@Controller
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private HandlerMessageService messageService;
    /**
     * 在线客户端交互页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "client/index";
    }




    @RequestMapping("/clients")
    @ResponseBody
    public WebResponse clients() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        Map<String, String> addressToDeviceNumMap = ServerHandler.addressToDeviceNumMap;
        List<ClientInfoVo> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : addressToDeviceNumMap.entrySet()) {
            ClientInfoVo clientInfoVo = new ClientInfoVo();
            clientInfoVo.setAddress(entry.getKey());
            clientInfoVo.setClientInfo(entry.getKey()+"_"+entry.getValue());
            list.add(clientInfoVo);
        }
        webResponse.setData(list);
        return webResponse;
    }

    @RequestMapping("/commonds")
    @ResponseBody
    public WebResponse commonds() {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        List<CommandVo> list = new ArrayList<>();
        list.add(new CommandVo(CommandEnum.QUERY_PARAM.getCode(),CommandEnum.QUERY_PARAM.getCommond()));
        list.add(new CommandVo(CommandEnum.SETPARAM.getCode(),CommandEnum.SETPARAM.getCommond()));
        list.add(new CommandVo(CommandEnum.STOP.getCode(),CommandEnum.STOP.getCommond()));
        webResponse.setData(list);
        return webResponse;
    }


    @RequestMapping("/send")
    @ResponseBody
    public WebResponse send(String address, Integer command, String message) {
        WebResponse webResponse = WebResponse.getSuccessWebResponse();
        messageService.sendCommond(address,command,message);
        return webResponse;
    }


}
