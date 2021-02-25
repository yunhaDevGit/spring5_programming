import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.awt.AppContext;

public class Main {

  public static void main(String[] args) {

    // AnnotationConfigApplicationContext 클래스는 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리한다.
    // AppContext에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화한다.
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

    // getBean() 메서드는 AnnotationConfigApplicationContext가 자바 설정을 읽어와 생성한 빈 객체를 검색할 때 사용된다.
    Greeter g1 = ctx.getBean("greeter", Greeter.class);
    Greeter g2 = ctx.getBean("greeter", Greeter.class);
    System.out.println("(g1 == g2) = " + (g1 == g2));
    ctx.close();

  }

}
