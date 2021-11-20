package me.progresssoft.interview;

import me.progresssoft.interview.controller.DataWarehouseController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@SpringBootTest
public class ApplicationTests {

    private @Autowired DataWarehouseController controller;
    
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
