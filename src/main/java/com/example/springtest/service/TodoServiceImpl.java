package com.example.springtest.service;

import com.example.springtest.domain.TodoVO;
import com.example.springtest.dto.PageRequestDTO;
import com.example.springtest.dto.PageResponseDTO;
import com.example.springtest.dto.TodoDTO;
import com.example.springtest.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor//final 이용시 자동으로 연결
public class TodoServiceImpl implements TodoService{
    
    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.insert(todoVO);
    }

//    @Override
//    public List<TodoDTO> getAll(){
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
//        return dtoList;
//    }

    //@Override
    //public List<TodoDTO> getAll(){
        //List<TodoVO> voLsit = todoMapper.selectAll();
        //List<TodoDTO> dtoList = new ArrayList<>();
        //for(TodoVO todoVO : voLsit){
            //TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
            //dtoList.add(todoDTO);
        //}
        //return dtoList;
    //}

    @Override
    public TodoDTO getOne(Long tno){
        TodoVO todoVO = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno){
        todoMapper.delet(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO){
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.update(todoVO);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        List<TodoDTO> dtoList = new ArrayList<>();
        for (TodoVO todoVO : voList) {
            dtoList.add(modelMapper.map(todoVO, TodoDTO.class));
        }

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pagRequsetDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;

    }
}
