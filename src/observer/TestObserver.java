package observer;

/**
 * Description 功能说明
 * Time 2020/4/16 23:52
 */
public class TestObserver implements Observer{
    private String info;

    public TestObserver(String info){
        this.info = info;
    }

    @Override
    public void update(String msg) {
        System.out.println(info + "----" + msg);
    }
}
