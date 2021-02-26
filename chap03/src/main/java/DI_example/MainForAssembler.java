package DI_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    // DI_example.Assembler ��ü ����
    // DI_example.Assembler �����ڿ� �ʿ��� ��ü ����&������ ���� -> DI_example.Assembler ��ü ���� ������ �ʿ��� ��ü�� ��� �����ȴ�
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
            System.out.println("��ȣ�� Ȯ���� ��ġ���� �ʽ��ϴ� \n");
            return;
        }
        try{
            registerService.regist(request);
            System.out.println("����߽��ϴ� \n");
        }catch (DuplicateMemberException e){
            System.out.println("�̹� �����ϴ� �̸����Դϴ� \n");
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
            System.out.println("��ȣ�� �����߽��ϴ� \n");
        } catch (MemberNotFoundException e){
            System.out.println("�������� �ʴ� �̸����Դϴ�. \n");
        } catch (WrongIdPasswordException e){
            System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�. \n");
        }
    }

    public static void printHelp(){
        System.out.println();
        System.out.println("�߸��� ����Դϴ�. �Ʒ� ��ɾ� ������ Ȯ���ϼ���");
        System.out.println("��ɾ� ����");
        System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
        System.out.println("change �̸��� ���� ��� ������");
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // �ֿܼ��� �Է��ϱ� ����
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("��ɾ �Է��ϼ���");
            String command = reader.readLine();

            // ��ҹ��� ��� ���� ��
            // exit �ԷµǸ� ���α׷� ����
            if(command.equalsIgnoreCase("exit")){
                System.out.println("�����մϴ�");
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