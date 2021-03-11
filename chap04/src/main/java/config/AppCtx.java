package config;

import DI_example.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
//        return new MemberRegisterService(memberDao());
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        ChangePasswordService changePasswordService = new ChangePasswordService();
//        changePasswordService.setMemberDao(memberDao());
        // 의존을 주입하지 않아도 스프링이 @Autowired가 붙은 필드에
        // 해당 타입의 빈 객체를 찾아서 주입한다.
        return changePasswordService;
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1(){
        return new MemberPrinter();
    }

    @Bean
    public MemberPrinter memberPrinter2(){
        return new MemberPrinter();
    }

    @Bean
    public MemberSummaryPrinter memberSummaryPrinter(){
        return new MemberSummaryPrinter();
    }

    @Bean
    public MemberListPrinter memberListPrinter(){
//        return new MemberListPrinter(memberDao(), memberPrinter());
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){
//        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
//        memberInfoPrinter.setMemberDao(memberDao());
//        memberInfoPrinter.setMemberPrinter(memberPrinter());
//        return memberInfoPrinter;
        return new MemberInfoPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
