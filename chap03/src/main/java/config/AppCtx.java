package config;

import DI_example.ChangePasswordService;
import DI_example.MemberDao;
import DI_example.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : 스프링 설정 클래스를 의미.
// 이 어노테이션이 있어야 스프링 설정 클래스로 사용 가능
@Configuration
public class AppCtx {

    @Bean // 해당 메서드가 생성한 객체를 스프링 빈이라고 설정
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        ChangePasswordService changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }
}
