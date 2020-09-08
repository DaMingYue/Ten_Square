package base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import until.IdWorker;

/**
 * @Author: LvDuanMing
 * @Date: 2020-05-30 15:04
 * @Description: 十次方启动类
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
