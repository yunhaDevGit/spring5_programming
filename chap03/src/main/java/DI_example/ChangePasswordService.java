package DI_example;

public class ChangePasswordService {

    private MemberDao memberDao;

    public void changePassword(String email,String oldPwd, String newPwd){
        Member member = memberDao.selectByEmail(email);
        if(member==null)
            throw new MemberNotFoundException();
        member.changePassword(oldPwd,newPwd);
        memberDao.update(member);
    }

    // setter�� ���� ���� ��ü ����
    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }
}