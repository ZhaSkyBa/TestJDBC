package observer;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2020/4/16 23:50
 */
public interface Subject {
    /**
     * 添加观察者
     */
    void attach(Observer observer);
    /**
     * 删除观察者
     */
    void detach(Observer observer);
    /**
     * 通知更新
     */
    void notify(String msg);
}
