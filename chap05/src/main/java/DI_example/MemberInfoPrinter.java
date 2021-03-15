package DI_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
    private MemberDao memberDao;
    private MemberPrinter memberPrinter;

    public void printMemberInfo(String email){
        Member member = memberDao.selectByEmail(email);
        if(member==null){
            System.out.printf("데이터 없음\n");
            return;
        }
        memberPrinter.print(member);
        System.out.println();
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    // 빈 객체의 메서드에 @Autowired를 붙이면 스프링은 해당 메서드를 호출하고,
    // 메서드 파라미터 타입에 해당하는 빈 객체를 찾아 인자로 주입한다.
    // 만약 파라미터 타입에 해당하는 빈 객체가 여러개일 경우, 익셉션 발생! @Qualifier로 지정해줘야 한다.
    @Autowired
    @Qualifier("printer")
    public void setMemberPrinter(MemberPrinter memberPrinter){
        this.memberPrinter = memberPrinter;
    }

}
