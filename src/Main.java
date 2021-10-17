import observer.Observer;
import observer.Subject;
import observer.TestObserver;
import observer.TestSubject;
import tree.QueryTree;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Subject subject = new TestSubject();

//        ExecutorService es = Executors.newFixedThreadPool(4);
//        es.submit();

        Observer observerA = new TestObserver("A：");
        Observer observerB = new TestObserver("B：");
        subject.attach(observerA);
        subject.attach(observerB);
        subject.notify("通知One");
        subject.detach(observerA);
        subject.notify("通知Two");
//
//        TreeNode[] node = TreeNode.init(10);
//
//        QueryTree queryTree = new QueryTree();
//        queryTree.preOrderRe(node[0]);
//
//        ArrayList list = new ArrayList<String>();
//        list.remove("");
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//        char[] array = new char[]{'0', 'q', 'l'};
//        stringBuilder.append(array);
//        "".toCharArray();
//
//        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
//        threadLocal.set("");
//        threadLocal.get();
//        threadLocal.remove();
    }
}
