package mapper;

import com.example.springtest.domain.TodoVO;
import com.example.springtest.dto.PageRequestDTO;
import com.example.springtest.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime(){
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .writer("user00")
                .dueDate(LocalDate.parse("2022-10-22"))
                .build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectOne(){
        TodoVO todoVO = todoMapper.selectOne(7L);
        log.info(todoVO);
    }

    @Test
    public void testDelet(){
        log.info(todoMapper.selectAll());
        todoMapper.delet(7L);
        log.info(todoMapper.selectAll());
    }

    @Test
    public void testUpdate(){
        log.info(todoMapper.selectOne(10L));

        TodoVO todoVO = TodoVO.builder().tno(10L)
                .title("업데이트")
                .dueDate(LocalDate.parse("2024-01-12"))
                .finished(false)
                .build();
        todoMapper.update(todoVO);
        log.info(todoMapper.selectOne(10L));
    }

    @Test
    public void testSelectList(){
        PageRequestDTO pagRequsetDTO = PageRequestDTO.builder()
                .page(11)
                .build();
      todoMapper.selectList(pagRequsetDTO);
      log.info(todoMapper.selectList(pagRequsetDTO));
    }

    @Test
    public void testGetCount(){
        log.info(todoMapper.getCount(PageRequestDTO.builder().build()));
    }

    @Test
    public void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .keyword("하나적습니다")
                .finished(true)
                .from(LocalDate.parse("2023-12-29"))
                .to(LocalDate.parse("2024-11-11"))
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));
    }
}
