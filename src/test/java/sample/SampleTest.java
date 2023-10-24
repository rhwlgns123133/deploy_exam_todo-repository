package sample;

import com.example.springtest.sample.SampleServeice;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTest {
    @Autowired// sampleServeice 가 new 없이 객체 가 생성 되있음 Autowired 주입 방식
    private SampleServeice sampleServeice;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testServeice1(){
        log.info(sampleServeice);
        Assertions.assertNotNull(sampleServeice);
    }

    @Test
    public void testConnection() throws Exception{
        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);//null 인지 여부 확인(객체가 반환 되는지 여부 확인)

        connection.close();
    }
}