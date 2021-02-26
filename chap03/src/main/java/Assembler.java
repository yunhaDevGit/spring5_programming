public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService memberRegisterService;
    private ChangePasswordService changePasswordService;

    // 객체에 대한 의존성 주입
    // MemberRegisterService는 생성자를 통해 MemeberDao 객체를 주입 받는다
    // ChangePasswordService는 setter를 통해 의존성을 주입 받는다.
    public Assembler(){
        memberDao = new MemberDao();
        memberRegisterService = new MemberRegisterService(memberDao);
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao(){
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService(){
        return memberRegisterService;
    }

    public ChangePasswordService getChangePasswordService(){
        return changePasswordService;
    }
}

// DI에서 객체 생성에 사용할 클래스를 변경하기 위해 객체를 사용하는 코드를 바꾸지 않고
// 객체를 주입하는 코드 한 곳만 변경하면 된다고 했는데,
// 실제 객체를 생성하는 코드는 어디에 있을까?
// main 메서드에서 객체를 생성해도 되지만,
// 의존 객체를 주입해주는 클래스를 따로 작성하는 것이 조금 더 좋다.
// Assembler 클래스가 여기서 의존 객체를 주입(서로 다른 두 객체 조립)하는 클래스이다