package com.lij.controllers;

import com.lij.dao.PetMapper;
import com.lij.entity.ApiResponse;
import com.lij.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.lang.Double.isNaN;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetMapper petMapper;

    @RequestMapping()
    public String index(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Pet> entities = petMapper.selectAll();
        model.addAttribute("pet", entities);
        return "index";
    }

    @PostMapping("/add")
    public ApiResponse PetAdd(Pet pet) {

        int c = petMapper.insert(pet);

        if (c > 0) {
            return new ApiResponse();
        }
        return new ApiResponse(405, "err", "Validation exception");
    }

//
//    @RequestMapping("/InsertAndUpdate")
//    public int Petupdate(Pet pet){
//        if (petMapper.selectByPrimaryName(pet.getpName()) == null) {
//            return petMapper.insert(pet);
//        } else {
//            return petMapper.updateByPrimaryKey(pet);
//        }
//    }

    @PutMapping
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

    @GetMapping("/Petcid/{cid}")
    public ApiResponse PetselectByPrimarycId(@PathVariable("cid") int cid, Model model) {
        Pet pet = petMapper.selectByPrimarycId(cid);
        model.addAttribute("pet", pet);
        if (pet != null) {
            return new ApiResponse(200, "err", "successful operation");
        } else {
            return new ApiResponse(400, "err", "Invalid status value");
        }
    }

    @GetMapping("/Petpid/{pid}")
    public ApiResponse selectByPrimaryKey(@PathVariable("pid") int pid, Model model) {
        Pet pet = petMapper.selectByPrimaryKey(pid);
        model.addAttribute("pet", pet);
        if (pid == 0) {
            if (pet != null) {
                return new ApiResponse(200, "err", "successful operation");
            } else {
                return new ApiResponse(400, "err", "Invalid status value");
            }
        } else {
            return new ApiResponse(404, "err", "Pet not found");
        }
    }

    @PostMapping("/update")
    public ApiResponse PetUpdate(Pet pet) {
        int c = petMapper.updateByPrimaryKey(pet);
        if (c > 0) {
            return new ApiResponse();
        }
        return new ApiResponse(405, "err", "Validation exception");
    }

    @DeleteMapping("/del/{pid}")
    public ApiResponse PetDel(@PathVariable("pid") int pid) {
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

    @PostMapping("/phpto")
    public ApiResponse PetphotoUrls(String photo) {
       int c = petMapper.UpdatePhoto(photo);
        if(c > 0){
            return new ApiResponse();
        }
        return new ApiResponse(200, "err", "successful operation");
    }
}
