package DI_example;

import config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {

    public static ApplicationContext ctx = null;

    private static void processNewCommand(String[] arg){
        if(arg.length!=5){
            printHelp();
            return;
        }

        // 스프링 컨테이너로부터 이름이 "memberRegisterService"인 bean 객체를 구한다.
        MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
        RegisterRequest request = new RegisterRequest();
        request.setEmail(arg[1]);
        request.setName(arg[2]);
        request.setPassword(arg[3]);
        request.setConfirmPassword(arg[4]);

        if(!request.isPasswordEqualToConfirmPassword()){
            System.out.println("암호와 확인이 일치하지 않습니다 \n");
            return;
        }
        try{
            regSvc.regist(request);
            System.out.println("등록했습니다 \n");
        }catch (DuplicateMemberException e){
            System.out.println("이미 존재하는 이메일입니다 \n");
        }
    }

    private static void processChangeCommand(String[] args){
        if(args.length!=4){
            printHelp();
            return;
        }

        // 스프링 컨테이너로부터 이름이 "changePasswordService"인 bean 객체를 구한다.
        ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
        try{
            changePwdSvc.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다 \n");
        } catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다. \n");
        } catch (WrongIdPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다. \n");
        }
    }

    private static void processListCommand() {
        MemberListPrinter memberListPrinter = ctx.getBean("memberListPrinter", MemberListPrinter.class);
        memberListPrinter.printAll();
    }

    private static void processInfoCommand(String[] args){
        if(args.length!=2){
            printHelp();
            return;
        }
        MemberInfoPrinter memberInfoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        memberInfoPrinter.printMemberInfo(args[1]);
    }

    private static void processVersionCommand(){
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    public static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재 비번 변경비번");
        System.out.println("list");
        System.out.println("info 이메일");
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        // 스프링 컨테이너 생성
        // 스프링 컨테이너는 Assembler와 동일하게 객체를 생성하고, 의존 객체를 주입해준다.
        // Assembler는 직접 객체를 생성하는 대신 AnnotationConfigApplicationContext는
        // 설정 파일(AppCtx)로부터 생성할 객체와 의존 주입 대상을 정한다.
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("명령어를 입력하세요");
            String command = reader.readLine();

            // 대소문자 상관 없이 비교
            // exit 입력되면 프로그램 종료
            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다");
                break;
            }
            if(command.startsWith("new")){
                processNewCommand(command.split(" "));
                continue;
            } else if(command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if(command.equals("list")){
                processListCommand();
                continue;
            } else if(command.startsWith("info")){
                processInfoCommand(command.split(" "));
                continue;
            } else if(command.equals("version")){
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }

}
