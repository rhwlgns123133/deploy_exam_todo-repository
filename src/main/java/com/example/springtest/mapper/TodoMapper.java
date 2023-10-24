package com.example.springtest.mapper;

import com.example.springtest.domain.TodoVO;
import com.example.springtest.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long todoVO);

    void delet(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pagRequsetDTO);

    int getCount(PageRequestDTO pagRequsetDTO);

}
