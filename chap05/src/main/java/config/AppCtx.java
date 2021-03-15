package config;

import DI_example.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Configuration : 스프링 설정 클래스를 의미.
// 이 어노테이션이 있어야 스프링 설정 클래스로 사용 가능
@Configuration
@ComponentScan(basePackages = {"DI_example"})
public class AppCtx {

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1(){
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberSummaryPrinter(){
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
