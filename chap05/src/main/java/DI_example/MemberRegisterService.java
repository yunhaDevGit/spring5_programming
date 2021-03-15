package DI_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    // 인자 없는 생성자
    public MemberRegisterService(){

    }

    // 생성자를 통해 의존 객체를 주입받는다.
    public MemberRegisterService(MemberDao memberDao){
        // 주입 받은 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req){
        // 주입 받은 객체의 메서드를 사용
        Member member = memberDao.selectByEmail(req.getEmail());
        if(member!=null)
            throw new DuplicateMemberException("dup email" + req.getEmail());
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }

}