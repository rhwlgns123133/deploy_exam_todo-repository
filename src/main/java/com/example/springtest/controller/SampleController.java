package com.example.springtest.controller;

import com.example.springtest.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collections;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(){
        log.info("hello()...");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1()...");
        log.info("name:" + name);
        log.info("age:" + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "aaa")String name,
                    @RequestParam(name = "age", defaultValue="16")int age){
        log.info("ex2()...");
        log.info("name:" + name);
        log.info("age:" + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3()...");
        log.info("dueDate: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("--------");
        model.addAttribute("message", "Hello Word");
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(){

    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info("p1......" + p1);
        log.info("p2......" + p2);
    }

    @GetMapping("/test01")
    public void test01(){
    }

    @PostMapping("/test02")
    public void test01To(TodoDTO todoDTO, Model model){
        log.info(todoDTO);
    }

    //@PostMapping("/test02")
    //public void test01To(@ModelAttribute("dto") TodoDTO todoDTO, Model model){
        //log.info(todoDTO);
    //}위에 코드랑 같지만 @ModelAttribute("dto")를 추가 하면 값을 불러올때 name(이름을) 을 dto 로 설정 된다는 뜻이다 "dto.title" 같이 사용
}
