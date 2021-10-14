package observer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Description 功能说明
 * Time 2020/4/16 23:55
 */
public class TestSubject implements Subject{
    private List<Observer> mList = new ArrayList();

    @Override
    public void attach(Observer observer) {
        mList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        mList.remove(observer);
    }

    @Override
    public void notify(String msg) {
        for (Observer observer : mList) {
            observer.update(msg);
        }
    }
}
