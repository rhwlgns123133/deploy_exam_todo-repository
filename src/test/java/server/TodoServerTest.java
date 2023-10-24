package server;


import com.example.springtest.domain.TodoVO;
import com.example.springtest.dto.PageRequestDTO;
import com.example.springtest.dto.PageResponseDTO;
import com.example.springtest.dto.TodoDTO;
import com.example.springtest.mapper.TodoMapper;
import com.example.springtest.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServerTest {

    @Autowired(required = false)
    private TodoService todoService;

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("스프링 테스트")
                .writer("user00")
                .dueDate(LocalDate.parse("2022-10-22"))
                .build();
        todoService.register(todoDTO);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> todoVOs = todoMapper.selectAll();
        for(TodoVO todoVO : todoVOs){
            log.info(todoVO);
        }
    }

//    @Test
//    public void testSelectAll2(){
//        List<TodoDTO> todoDTOs = todoService.getAll();
//        for(TodoDTO todoDTO : todoDTOs){
//            log.info(todoDTO);
//        }
//    }

    @Test
    public void testSelectOne(){
        TodoDTO todoDTO = todoService.getOne(7L);
        log.info(todoDTO);
    }

    @Test
    public void PageRespons(){
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(PageRequestDTO.builder().build());
        log.info(pageResponseDTO);
    }
}
