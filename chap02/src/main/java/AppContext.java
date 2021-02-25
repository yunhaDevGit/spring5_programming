import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 해당 클래스를 spring의 설정 클래스로 지정
@Configuration
public class AppContext {

  // @Bean 어노테이션을 메서드에 붙이면 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록한다.
  @Bean
  public Greeter greeter() {
    Greeter g = new Greeter();
    g.setFormat("%s, 안녕하세요");
    return g;
  }
}
