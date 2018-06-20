package com.wch.uwb.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/23weq")
    @ResponseBody
    private String hello() {
        // 直接返回字符串
        return "PHP is the best language";
    }
    @RequestMapping("/template")
    private String index() {
        // 使用模板 返回模板的文件名 不需要.html
        return "index";
    }

    @RequestMapping("/blog")
    private String blog(Model model) {
        // 去数据库查博客去

        // 查到博客了，输出
        model.addAttribute("title", "标题啦北理珠好大啊");
        model.addAttribute("content", "内容啊啊啊啊啊");
        return "blog";
    }
    //    @RequestMapping(value="/test", method= RequestMethod.GET)
//    public String test() {
//
//        return "test";
//    }
//
//    @PostMapping("/upload") // //new annotation since 4.3
//    public String singleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//       if (file.isEmpty()) {
////            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
////            return "redirect:uploadStatus";
//       }
//
//        try {
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:uploadStatus";
//    }
//    @GetMapping("/uploadStatus")
//    public String uploadStatus() {
//        return "index";
//    }


}
