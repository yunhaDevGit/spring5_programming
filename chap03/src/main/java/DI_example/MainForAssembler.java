package DI_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    // DI_example.Assembler 객체 생성
    // DI_example.Assembler 생성자에 필요한 객체 생성&의존성 주입 -> DI_example.Assembler 객체 생성 시점에 필요한 객체가 모두 생성된다
    private static Assembler assembler = new Assembler();

    private static void processNewCommand(String[] arg){
        if(arg.length!=5){
            printHelp();
            return;
        }
        MemberRegisterService registerService = assembler.getMemberRegisterService();
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
            registerService.regist(request);
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
        ChangePasswordService changePasswordService = assembler.getChangePasswordService();
        try{
            changePasswordService.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다 \n");
        } catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다. \n");
        } catch (WrongIdPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다. \n");
        }
    }

    public static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재 비번 변경비번");
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // 콘솔에서 입력하기 위함
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
            }
            printHelp();
        }
    }
}