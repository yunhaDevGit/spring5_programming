public class ExeTimeCalculator implements Calculator {
    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate){
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
                delegate.getClass().getSimpleName(), num, (end-start));
        return result;
    }
}

// 생성자를 통해 다른 Calculator 객체를 전달받아 delegate 필드에 할당하고
// factorial() 메서드에서 deledate.factorial() 메서드를 실행한다.