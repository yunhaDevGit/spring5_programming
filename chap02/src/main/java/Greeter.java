public class Greeter {

  private String format;

  public String greet(String guest){
    return String.format(format, guest); // String 문자열 포맷을 이용해 새로운 문자열 생성
  }

  public void setFormat(String format){ // greet() 메서드에서 사용할 문자열 포멧 설정
    this.format = format;
  }

}
