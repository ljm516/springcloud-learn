package top.ljming.common.conviction;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvictionClientConfiguration {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
