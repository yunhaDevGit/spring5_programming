public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService memberRegisterService;
    private ChangePasswordService changePasswordService;

    // ��ü�� ���� ������ ����
    // MemberRegisterService�� �����ڸ� ���� MemeberDao ��ü�� ���� �޴´�
    // ChangePasswordService�� setter�� ���� �������� ���� �޴´�.
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

// DI���� ��ü ������ ����� Ŭ������ �����ϱ� ���� ��ü�� ����ϴ� �ڵ带 �ٲ��� �ʰ�
// ��ü�� �����ϴ� �ڵ� �� ���� �����ϸ� �ȴٰ� �ߴµ�,
// ���� ��ü�� �����ϴ� �ڵ�� ��� ������?
// main �޼��忡�� ��ü�� �����ص� ������,
// ���� ��ü�� �������ִ� Ŭ������ ���� �ۼ��ϴ� ���� ���� �� ����.
// Assembler Ŭ������ ���⼭ ���� ��ü�� ����(���� �ٸ� �� ��ü ����)�ϴ� Ŭ�����̴�