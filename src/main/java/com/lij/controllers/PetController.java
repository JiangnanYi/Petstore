package com.lij.controllers;

import com.lij.dao.CategoryMapper;
import com.lij.dao.PetMapper;
import com.lij.dao.TagMapper;
import com.lij.entity.ApiResponse;
import com.lij.entity.Category;
import com.lij.entity.Pet;
import com.lij.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Double.isNaN;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @RequestMapping()
    public String index(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Pet> pet = petMapper.selectAll();
        List<Category> category = categoryMapper.selectAll();
        List<Tag> tag = tagMapper.selectAll();
        model.addAttribute("pets", pet);
        model.addAttribute("categorys", category);
        model.addAttribute("tags", tag);
        return "pet";
    }
//按状态查询
    @RequestMapping(value = "/petsta{status}",method = RequestMethod.GET)
    public String PetSelectByPrimaryStatus(String status, Model model) {
        List<Pet> pets = petMapper.selectByPrimaryStatus(status);
        List<Category> category = categoryMapper.selectAll();
        List<Tag> tag = tagMapper.selectAll();
        model.addAttribute("pets", pets);
        model.addAttribute("categorys", category);
        model.addAttribute("tags", tag);
        return "pet";
    }
//按编号查询
    @RequestMapping(value = "/ptd{pid}",method = RequestMethod.GET)
    public String selectByPrimaryKey(int pid, Model model) {
        List<Pet> pet = petMapper.selectByPrimaryKey(pid);
        List<Category> category = categoryMapper.selectAll();
        List<Tag> tag = tagMapper.selectAll();
        model.addAttribute("pets", pet);
        model.addAttribute("categorys", category);
        model.addAttribute("tags", tag);
        return "pet";
    }

//添加
    public ApiResponse PetAdd(Pet pet) {

        int c = petMapper.insert(pet);

        if (c > 0) {
            return new ApiResponse();
        }else {
            return new ApiResponse(405, "err", "Validation exception");
        }
    }
    @PostMapping("/add")
    public String PetAdd2(Pet pet){
        PetAdd(pet);
        return "redirect:/pet";
    }
//
//    @RequestMapping("/petupd")
//    public int Petupdate(Pet pet){
//        if (petMapper.selectByPrimaryName(pet.getpName()) == null) {
//            return petMapper.insert(pet);
//        } else {
//            return petMapper.updateByPrimaryKey(pet);
//        }
//    }

    @PutMapping("/petupd")
    @ResponseBody
    public ApiResponse update(Pet pet) {
        if (pet.getpId() == null) {
            if (petMapper.selectByPrimaryKey(pet.getpId()) != null) {
                if (petMapper.updateByPrimaryKey(pet) != 0) {
                    return new ApiResponse();
                } else {
                    return new ApiResponse(405, "err", "Validation exception");
                }
            } else {
                return new ApiResponse(404, "err", "Pet not found");
            }
        } else {
            return new ApiResponse(400, "err", "Invalid ID supplied");
        }
    }


   //修改
    public ApiResponse PetUpdate(Pet pet) {
        int c = petMapper.updateByPrimaryKey(pet);
        if (c > 0) {
            return new ApiResponse();
        }
        return new ApiResponse(405, "err", "Validation exception");
    }
    @PostMapping("/update")
    public String PetUpdate2(Pet pet) {
        PetUpdate(pet);
        return "redirect:/pet";
    }

//删除
    public ApiResponse PetDel(int pid) {
        int c = petMapper.deleteByPrimaryKey(pid);
        if (isNaN(pid)) {
            if (c > 0) {
                return new ApiResponse();
            } else {
                return new ApiResponse(404, "err", "Pet not found");
            }
        } else {
            return new ApiResponse(400, "err", "Invalid status value");
        }
    }

    @RequestMapping(value = "/del/{pid}",method = RequestMethod.GET)
    public String  PetDel2(@PathVariable("pid") int pid) {
        PetDel(pid);
        return "redirect:/pet";
    }

//    @PostMapping("/phpto")
//    public ApiResponse PetphotoUrls(Pet pet) {
//       int c = petMapper.UpdatePhoto(pet);
//        if(c > 0){
//            return new ApiResponse();
//        }
//        return new ApiResponse(200, "err", "successful operation");
//}

    @PostMapping("/photo")
    public String imageshangchuan(MultipartFile multipartFile, HttpServletRequest req, Model model,Pet pet) {
        String fileName = null;
        petMapper.UpdatePhoto(pet);
        if (multipartFile.isEmpty()) {
            model.addAttribute("err", "图片不能为空");
            return "pet";
        }

        if (multipartFile.getSize() > 5 * 1024 * 1024) {
            model.addAttribute("err", "图片大小超出范围");
            return "pet";
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("image/")) {
            model.addAttribute("err", "只允许上传图片");
            return "pet";
        }
        String barget = req.getServletContext().getRealPath("/img");
        String relativePath;
        try {
            relativePath = makeImagePath(multipartFile.getOriginalFilename());
            File target = new File(barget + relativePath);
            target.getParentFile().mkdir();
            multipartFile.transferTo(target);
        } catch (IOException e) {
            model.addAttribute("err","文件上传失败请重试");
            return "pet";
        }

        System.out.println("正常业务。。。。");
        return "redirect:/pet";
    }

    public String makeImagePath(String fileName) {
        Date now = new Date();
        String[] filename = spliFileString(fileName);
        return String.format("%s%supload_%s_%s.%s",
                File.separator,
                new SimpleDateFormat("yyyyMMdd").format(now),
                File.separator,
                filename[0],
                new SimpleDateFormat("hhmmss").format(now),
                filename[1]);
    }

    public String[] spliFileString(String filename) {
        int dotPos = filename.lastIndexOf(".");
        String est = filename.substring(dotPos + 1);
        String name = filename.substring(0, dotPos);
        return new String[]{name, est};
    }
}
