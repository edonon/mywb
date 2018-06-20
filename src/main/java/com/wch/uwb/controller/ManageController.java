package com.wch.uwb.controller;

import com.wch.uwb.entity.ReportEntity;
import com.wch.uwb.entity.UserEntity;
import com.wch.uwb.entity.WeiboEntity;
import com.wch.uwb.entity.WeiboFNT;
import com.wch.uwb.mapper.ReportMapper;
import com.wch.uwb.mapper.UserMapper;
import com.wch.uwb.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

@Controller
@EnableAutoConfiguration
public class ManageController {
    //编辑用户信息-获取
    @RequestMapping(value="/userManage", method= RequestMethod.GET)
    public String userManagePOST(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        List<UserEntity> userEntityList = userMapper.getAll();
        UserEntity ue = new UserEntity();
        int state = 0;
        if(userMapper.getOne(id).getAuthor() == 1){
            state =1;
        }
        model.addAttribute("state", state);
        model.addAttribute("ue", ue);
        model.addAttribute("userEntityList", userEntityList);
        model.addAttribute("cnt", cnt);
        return "userManage";
    }
    //编辑用户信息-获取
    @RequestMapping(value="/userManage", method= RequestMethod.POST)
    public String userManage(UserEntity ue,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        int author = ue.getAuthor();
        List<UserEntity> userEntityList = userMapper.getAll();
        if (author != 0) {
            userEntityList = userMapper.getOneByAuthor(author);
        }
        ue = new UserEntity();
        int state = 0;
        if(userMapper.getOne(id).getAuthor() == 1){
            state =1;
        }
        model.addAttribute("state", state);
        model.addAttribute("ue", ue);
        model.addAttribute("userEntityList", userEntityList);
        model.addAttribute("cnt", cnt);
        return "userManage";
    }
    //
    @RequestMapping(value="/editUser", method= RequestMethod.POST)
    public String editUser(UserEntity ue,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        UserEntity userEntity = userMapper.getOne(ue.getId());
        userEntity.setPasswd(ue.getPasswd());
        userEntity.setUserName(ue.getUserName());
        if(ue.getAuthor() != 0)userEntity.setAuthor(ue.getAuthor());
        userEntity.setSex(ue.getSex());
        userMapper.update(userEntity);
        System.out.println(ue.toString());
        return "redirect:/userManage";
    }
    //free
    @RequestMapping(value="/free", method= RequestMethod.GET)
    public String free(int uid,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        UserEntity userEntity = userMapper.getOne(id);
        Date now=new java.sql.Date(System.currentTimeMillis());
        userEntity.setFree(getPreDoneScore(now));
        userEntity.toString();
        userMapper.update(userEntity);

        return "redirect:/userManage";
    }
    //管理主界面
    @RequestMapping(value="/mainManage", method= RequestMethod.GET)
    public String mainManage(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        model.addAttribute("cnt", cnt);
        return "mainManage";
    }
    //管理主界面
    @RequestMapping(value="/reportManage", method= RequestMethod.GET)
    public String reportManage(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        List<ReportEntity> list = reportMapper.getALL();
        List<WeiboFNT> wblist = new ArrayList<WeiboFNT>();
        for(int i = 0; i < list.size();i++){
            WeiboEntity weiboEntity = weiboMapper.getOne(list.get(i).getwId());
            UserEntity userEntity = userMapper.getOne(weiboEntity.getuId());
            wblist.add(new WeiboFNT(userEntity, weiboEntity));
        }
        model.addAttribute("wblist", wblist);
        model.addAttribute("cnt", cnt);
        return "reportManage";
    }
    @RequestMapping(value="/reportManageGET", method= RequestMethod.GET)
    public String reportManageGET(@RequestParam("oper") int oper,@RequestParam("id") int id, Model model, HttpServletRequest request) {
        int manageId = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        if(oper == 1){
            reportMapper.deleteBYWId(id);
        }else{
            WeiboEntity weiboEntity = weiboMapper.getOne(id);
            UserEntity userEntity = userMapper.getOne(weiboEntity.getuId());
            reportMapper.deleteBYWId(id);
            Date now=new java.sql.Date(System.currentTimeMillis());
            userEntity.setFree(getPreDoneScore(now));
            userMapper.update(userEntity);
        }

        List<ReportEntity> list = reportMapper.getALL();
        List<WeiboFNT> wblist = new ArrayList<WeiboFNT>();
        for(int i = 0; i < list.size();i++){
            WeiboEntity weiboEntity = weiboMapper.getOne(list.get(i).getwId());
            UserEntity userEntity = userMapper.getOne(weiboEntity.getuId());
            wblist.add(new WeiboFNT(userEntity, weiboEntity));
        }
        model.addAttribute("wblist", wblist);
        model.addAttribute("cnt", cnt);
        return "reportManage";
    }
    //微博管理
    @RequestMapping(value="/weiboManage", method= RequestMethod.GET)
    public String weiboManage(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        List<WeiboEntity> list = weiboMapper.getAll();

        model.addAttribute("list", list);
        model.addAttribute("cnt", cnt);
        return "weiboManage";
    }
    @RequestMapping(value="/weiboManage", method= RequestMethod.POST)
    public String weiboManagePOST(int uid,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("manageId");
        int cnt = reportMapper.getCNT();
        List<WeiboEntity> list = weiboMapper.getAll();
        if(uid != 0){
            list = weiboMapper.getOneByUID(uid);
        }

        model.addAttribute("list", list);
        model.addAttribute("cnt", cnt);
        return "weiboManage";
    }
    //删除微博
    @RequestMapping(value="/delWeiboMan", method= RequestMethod.GET)
    public String delWeibo(int id,Model model, HttpServletRequest request) {
        int uid = (int)request.getSession().getAttribute("manageId");
        weiboMapper.delete(id);
        return "redirect:/weiboManage";
    }
    @RequestMapping(value="/loginManage", method=RequestMethod.GET)
    private String loginManage(Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "loginManage";
    }
    @RequestMapping(value = "/loginManage", method = RequestMethod.POST)
    @ResponseBody
    private Map manageLogin(@RequestBody UserEntity userEntity, Model model, HttpServletRequest request) {
        UserEntity uE = userMapper.getOneByLogin(userEntity.getLogin());
        System.out.println(userEntity.getLogin()+","+userEntity.getPasswd());

        Map<String, Object> result = new LinkedHashMap<>();
        if(uE != null && uE.getPasswd().equals(userEntity.getPasswd()) && uE.getAuthor() < 3){
            request.getSession().setAttribute("manageId",uE.getId());
            result.put("message", "loginSuccess");
        }else{
            result.put("message", "密码错误或无权限");
        }
        return result;
    }
    //Date是java.sql.Date类型
    protected Date getPreDoneScore(Date holdDate) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(holdDate);
        calendar.add(calendar.DATE, -6);
// calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        calendar.add(calendar.DATE, 7);
        utilDate = (java.util.Date)calendar.getTime();
//java.util.Date日期转换成转成java.sql.Date格式
        Date newDate =new Date(utilDate.getTime());
        return newDate;
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private ReportMapper reportMapper;
}
