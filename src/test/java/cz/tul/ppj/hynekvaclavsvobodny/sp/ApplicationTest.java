package cz.tul.ppj.hynekvaclavsvobodny.sp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void testContextLoads() {
        assertNotNull(this.ctx);
    }

}
