package com.example.springtest.service;

import com.example.springtest.dto.PageRequestDTO;
import com.example.springtest.dto.PageResponseDTO;
import com.example.springtest.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);

    //List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageResponseDTO);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
