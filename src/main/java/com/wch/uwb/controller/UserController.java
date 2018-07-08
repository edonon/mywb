package com.wch.uwb.controller;

import com.wch.uwb.entity.*;
import com.wch.uwb.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@EnableAutoConfiguration
public class UserController {

    private void setInform(Model model, int id){
        UserEntity userEntity = userMapper.getOne(id);
        InformEntity informEntity = informMapper.getByUid(id);
        if(informEntity == null) {informEntity = new InformEntity();}
        informEntity.setChatCnt(chatlistMapper.getCNT(id));
        model.addAttribute("informEntity", informEntity);
        model.addAttribute("userEntity", userEntity);
    }

    @RequestMapping("/personalSpace")
    public String getUsers(int page,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        int len = WEIBO_PAGE;
        if(weiboMapper.getMyWeiboListCNT(id) < WEIBO_PAGE*page){
            len = weiboMapper.getMyWeiboListCNT(id)%page;
            if(len == 0 ){
                len = weiboMapper.getMyWeiboListCNT(id);
            }
        }
        System.out.println("页面参数"+id+ WEIBO_PAGE*(page - 1)+ len+weiboMapper.getMyWeiboListCNT(id));
        List<WeiboEntity>  WBList = weiboMapper.getMyListInInterval(id, WEIBO_PAGE*(page - 1), len);
        model.addAttribute("WBList", WBList);
        System.out.println("WBList:"+WBList.size());
        int pageCnt = (int)weiboMapper.getMyWeiboListCNT(id)/WEIBO_PAGE;
        if(pageCnt*WEIBO_PAGE != weiboMapper.getMyWeiboListCNT(id)){
            pageCnt++;
        }
        System.out.println("pageCnt:"+pageCnt);
        model.addAttribute("pageCnt", pageCnt);
        model.addAttribute("page", page);
        return "personalSpace";
    }

    @PostMapping("/newWeibo") // //new annotation since 4.3
    public String newWeibo(@RequestParam("content") String content,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,HttpServletRequest request) {

        Date now=new java.sql.Date(System.currentTimeMillis());
        int id = (int)request.getSession().getAttribute("id");
        UserEntity userEntity  = userMapper.getOne(id);
        //发言检测
//        if(userEntity.getFree() != null && userEntity.getFree().before(now)){
//            return "freetime";
//        }
        WeiboEntity weiboEntity = new WeiboEntity(id, content,now, now, 0, "");
       if (!file.isEmpty()) {
           try {
               // Get the file and save it somewhere
               byte[] bytes = file.getBytes();
               Path path = Paths.get(IMG_FOLDER + file.getOriginalFilename());
               Files.write(path, bytes);

               redirectAttributes.addFlashAttribute("message",
                       "You successfully uploaded '" + file.getOriginalFilename() + "'");
               weiboEntity.setPhoto(IMG_FOLDER_TOSAVE + file.getOriginalFilename());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        weiboMapper.insert(weiboEntity);
       return "redirect:uploadStatus";
    }
    @GetMapping("/uploadStatus")
    public String uploadStatus(Model model, HttpServletRequest request) {
        return "redirect:/main?page=1";
    }

    //私聊列表
    @RequestMapping(value="/chatList", method= RequestMethod.GET)
    public String chatList(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");

        List<Chatlist> list = chatlistMapper.getOneByUid(id);
        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        for(int i = 0; i < list.size(); i++){
            UserEntity userEntity = userMapper.getOne(list.get(i).getSeuid());
            userEntities.add(userEntity);
        }
        model.addAttribute("userEntities", userEntities);
        setInform(model, id);
        return "chatList";
    }
    //私聊
    @RequestMapping(value="/chat", method= RequestMethod.GET)
    public String chat(int id,Model model, HttpServletRequest request) {
        int uid = (int)request.getSession().getAttribute("id");
        List<Chat> chatL = chatMapper.getOneByDoubleID(min(id, uid),max(id, uid));
        UserEntity friend = userMapper.getOne(id);
        UserEntity my = userMapper.getOne(id);
        for(int i=0; i<chatL.size();i++){
            System.out.println(chatL.get(i).toString());
        }
        model.addAttribute("chatL", chatL);
        model.addAttribute("friend", friend);
        model.addAttribute("my", my);
        //model.addAttribute("userEntities", userEntities);
        //setInform(model, id);
        return "chat";
    }
    //私聊
    @RequestMapping(value="/chat", method= RequestMethod.POST)
    public String chat(int id,String discuss,Model model, HttpServletRequest request) {
        int uid = (int)request.getSession().getAttribute("id");
        Chat chat = new Chat();
        chat.setwIdFri(min(uid, id));
        chat.setwIdSec(max(uid, id));
        if(uid == min(uid, id)){
            chat.setDirection(1);
        }else{
            chat.setDirection(0);
        }
        chat.setDiscuss(discuss);
        Date now=new java.sql.Date(System.currentTimeMillis());
        chat.setTime(now);
        chatMapper.insert(chat);
        Chatlist chatlist = new Chatlist();
        chatlist.setUid(id);
        chatlist.setSeuid(uid);
        chatlistMapper.insert(chatlist);
        List<Chat> chatL = chatMapper.getOneByDoubleID(min(id, uid),max(id, uid));
        UserEntity friend = userMapper.getOne(id);
        UserEntity my = userMapper.getOne(id);
        for(int i=0; i<chatL.size();i++){
            System.out.println(chatL.get(i).toString());
        }
        model.addAttribute("chatL", chatL);
        model.addAttribute("friend", friend);
        model.addAttribute("my", my);
        //model.addAttribute("userEntities", userEntities);
        //setInform(model, id);
        String res = "redirect:/chat?id="+id;
        return res;
    }
    //编辑用户信息-获取
    @RequestMapping(value="/editInfo", method= RequestMethod.GET)
    public String editInfo(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        return "editInfo";
    }
    //编辑用户信息-编辑
    @RequestMapping(value="/editInfo", method= RequestMethod.POST)
    public String editInfoPOST(UserEntity userEntity,@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes,HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        UserEntity ue = userMapper.getOne(id);

        if (!file.isEmpty()) {
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(PHOTO_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
                userEntity.setPhoto(PHOTO_FOLDER_TOSAVE + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(userEntity.getPhoto() != null) ue.setPhoto(userEntity.getPhoto());
        ue.setUserName(userEntity.getUserName());
        ue.setResume(userEntity.getResume());
        System.out.println(ue.toString());
        userMapper.update(ue);

        return "redirect:uploadStatus";
    }
    //粉丝
    @RequestMapping(value="/fans", method= RequestMethod.GET)
    public String fans(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        List<FanEntity> followList = fanMapper.getByfanId(id);
        List<UserEntity> userEntities = new ArrayList<UserEntity>();

        for(int i = 0; followList!= null && i < followList.size(); i++){

            userEntities.add( userMapper.getOne( followList.get(i).getuId() ) );
            //能放入对象
            System.out.println(userEntities.get(i).toString());
        }

        model.addAttribute("userEntities", userEntities);
        return "fans";
    }
    @RequestMapping(value="/follow", method= RequestMethod.GET)
    public String follow(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        List<FanEntity> fanEntityList =  fanMapper.getByUid(id);
        List<UserEntity> userEntities = new ArrayList<UserEntity>();

        for(int i = 0; fanEntityList!= null && i < fanEntityList.size(); i++){

            userEntities.add( userMapper.getOne( fanEntityList.get(i).getFanId() ) );
            //能放入对象
            System.out.println(userEntities.get(i).toString());
        }
        model.addAttribute("userEntities", userEntities);
        return "follow";
    }
    //个人信息
    @RequestMapping(value="/info", method= RequestMethod.GET)
    public String info(Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        return "info";
    }
    //关注


    //information
    @RequestMapping(value="/information", method= RequestMethod.GET)
    public String information(int uid,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        int state = -1;
        if(id == uid){state = 0;};
        List<FanEntity> fanEntityList = fanMapper.getByUid(id);
        for(int i = 0; i < fanEntityList.size();i++){
            if(fanEntityList.get(i).getFanId() == uid){
                state = 1;
            }
        }
        UserEntity ue = userMapper.getOne(uid);
        System.out.println("state="+state);
        model.addAttribute("state", state);
        model.addAttribute("ue", ue);
        return "information";
    }
    //weibo
    @RequestMapping(value="/weibo", method= RequestMethod.GET)
    public String weibo(int wid,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        setInform(model, id);
        UserEntity userEntity =userMapper.getOne(weiboMapper.getOne(wid).getuId());
        WeiboFNT weiboFNT = new WeiboFNT(userEntity, weiboMapper.getOne(wid));
        List<CommentEntity> commentEntityList = commentMapper.getOneByWID(wid);
        List<CommentFNT> commentFNTList =  new ArrayList<CommentFNT>();
        for(int i = 0; commentEntityList != null && i < commentEntityList.size(); i++){
            CommentFNT commentFNT = new CommentFNT(commentEntityList.get(i),userEntity);
            commentFNTList.add(commentFNT);
        }

        model.addAttribute("weiboFNT", weiboFNT);
        model.addAttribute("commentFNTList", commentFNTList);
        return "weibo";
    }
    //weibo
    @RequestMapping(value="/addComment", method= RequestMethod.POST)
    public String addComment(int wid,String comment,Model model, HttpServletRequest request) {
        int id = (int)request.getSession().getAttribute("id");
        Date now=new java.sql.Date(System.currentTimeMillis());
        CommentEntity commentEntity = new CommentEntity(wid, id, comment, now);
        System.out.println(commentEntity.toString());
        commentMapper.insert(commentEntity);

        return "redirect:/main?page=1";
    }
    //测试--用户登录
    @RequestMapping("/userLoginTest")
    public String userLoginTest(HttpServletRequest request) {
        request.getSession().setAttribute("id",1);
//        FanEntity fanEntity = new FanEntity();
////        fanEntity.setuId(1);
////        fanEntity.setFanId(2);
////        Date now=new java.sql.Date(System.currentTimeMillis());
////        fanEntity.setTime(now);
////        fanMapper.insert(fanEntity);

        return "userLoginTest";
    }
    private int min(int i, int j){if(i<j)return i; return j;}
    private int max(int i, int j){if(i>j)return i; return j;}
    //private static String IMG_FOLDER = "E:\\java_code\\uwb\\src\\main\\resources\\static\\WBresources\\img\\";
    //private static String PHOTO_FOLDER = "E:\\java_code\\uwb\\src\\main\\resources\\static\\WBresources\\photo\\";
    private static String IMG_FOLDER = "E:\\java_code\\uwb\\target\\classes\\static\\WBresources\\img\\";
    private static String PHOTO_FOLDER = "E:\\java_code\\uwb\\target\\classes\\static\\WBresources\\photo\\";
    private static String IMG_FOLDER_TOSAVE = "./WBresources/img/";
    private static String PHOTO_FOLDER_TOSAVE = "./WBresources/photo/";
    private static int USER_PAGE = 2;
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
    private CommentMapper commentMapper;
    @Autowired
    private ChatlistMapper chatlistMapper;
    @Autowired
    private ChatMapper chatMapper;
}
