package com.example.springtest.controller;

import com.example.springtest.dto.PageRequestDTO;
import com.example.springtest.dto.TodoDTO;
import com.example.springtest.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

 //   @RequestMapping("/list")
//    public void list(Model model){
//        log.info("todo list");
//        model.addAttribute("dtoList", todoService.getAll());
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequsetDTO, BindingResult bindingResult, Model model){
        log.info(pageRequsetDTO);

        if(bindingResult.hasErrors()){
            pageRequsetDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequsetDTO));
    }


    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    //@GetMapping("/register")
    // void register(){
        //log.info("todo register");
    //}

    //@PostMapping("/register")
    //public void registerPOST(){
        //log.info("POST todo register");
    //}

    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("POST todo register.....");

        if(bindingResult.hasErrors()){
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/register")
    public void regsterGET(){
        log.info("GET todo regster");
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("-------------------remove-------------");
        log.info("tno: " + tno);
        log.info("tno: " + pageRequestDTO);

        todoService.remove(tno);

        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){//유효성 검사 결과 에러가 있으면 수정 페이지로 되돌아감
            log.info(("has error"));
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());//tno 가 쿼리스트행으로 전달
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);

        //redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        //redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        redirectAttributes.addAttribute("tno", todoDTO.getTno());
        return "redirect:/todo/read";
    }
}
