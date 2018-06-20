package com.wch.uwb.controller;

import com.wch.uwb.entity.*;
import com.wch.uwb.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class SquareController {

    @RequestMapping(value = "/main",method=RequestMethod.GET)
    private String main(int page,Model model,HttpServletRequest request){
        int id = (int)request.getSession().getAttribute("id");
        UserEntity userEntity = userMapper.getOne(id);
        InformEntity informEntity = informMapper.getByUid(id);

        if(informEntity == null) {informEntity = new InformEntity();}
        informEntity.setChatCnt(chatlistMapper.getCNT(id));
        model.addAttribute("informEntity", informEntity);
        model.addAttribute("userEntity", userEntity);

        int len = WEIBO_PAGE;
        if(weiboMapper.getWeiboListCNT() < WEIBO_PAGE*page){
            len = weiboMapper.getWeiboListCNT()%page;
        }
        System.out.println("页面参数"+ WEIBO_PAGE*(page - 1)+ len+weiboMapper.getMyWeiboListCNT(id));
        List<WeiboEntity>  WBList = weiboMapper.getListInInterval(WEIBO_PAGE*(page - 1), len);
        int pageCnt = (int)weiboMapper.getWeiboListCNT()/WEIBO_PAGE;
        if(pageCnt*WEIBO_PAGE != weiboMapper.getWeiboListCNT()){
            pageCnt++;
        }
        List<WeiboFNT> userEntityList = new ArrayList<WeiboFNT>();
        for(int i = 0; i < WBList.size(); i++){
            WeiboEntity we = WBList.get(i);
            userEntityList.add(new WeiboFNT(userMapper.getOne(we.getuId()), we));
            System.out.println(we.toString());
        }

        model.addAttribute("userEntityList", userEntityList);
        model.addAttribute("pageCnt", pageCnt);
        model.addAttribute("page", page);
        return "main";
    }
    //
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    @ResponseBody
    private Map good(@RequestBody int i, Model model, HttpServletRequest request) {
        System.out.println("weiboID"+i);
        Map<String, Object> result = new LinkedHashMap<>();
        WeiboEntity weiboEntity = weiboMapper.getOne(i);
        weiboEntity.setGood(weiboEntity.getGood()+1);
        weiboMapper.update(weiboEntity);
        result.put("message", "ok");
        return result;
    }
    //关注
    @RequestMapping(value = "/ob", method = RequestMethod.POST)
    @ResponseBody
    private Map ob(@RequestBody int i, Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        Map<String, Object> result = new LinkedHashMap<>();
        Date now=new java.sql.Date(System.currentTimeMillis());
        FanEntity fanEntity = new FanEntity(id, i,now);
        fanMapper.insert(fanEntity);
        result.put("message", "ok");
        return result;
    }
    //q取消关注
    @RequestMapping(value = "/disob", method = RequestMethod.POST)
    @ResponseBody
    private Map disob(@RequestBody int i, Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        Map<String, Object> result = new LinkedHashMap<>();
        System.out.println("deletefan"+id+":"+i);
        fanMapper.delete(id, i);
        result.put("message", "ok");
        return result;
    }
    //编辑微博
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    private String edit(int id, String content, Model model, HttpServletRequest request) {
        System.out.println("id+con"+id+"  "+content);
        int uid = (int)request.getSession().getAttribute("id");
        WeiboEntity wb = weiboMapper.getOne(id);
        wb.setContent(content);
        weiboMapper.update(wb);
        return "redirect:/main?page=1";
    }
    //删除微博
    @RequestMapping(value = "/delWeibo", method = RequestMethod.POST)
    @ResponseBody
    private Map delWeibo(@RequestBody int i, Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        Map<String, Object> result = new LinkedHashMap<>();
        weiboMapper.delete(i);
        result.put("message", "ok");
        return result;
    }
    //举报
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @ResponseBody
    private Map report(@RequestBody int i, Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        Map<String, Object> result = new LinkedHashMap<>();
        Date now=new java.sql.Date(System.currentTimeMillis());
        ReportEntity reportEntity = new ReportEntity(i, now);
        reportMapper.insert(reportEntity);
        return result;
    }

    private static int WEIBO_PAGE = 2;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private InformMapper informMapper;
    @Autowired
    private FanMapper fanMapper;
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private ChatlistMapper chatlistMapper;
}
