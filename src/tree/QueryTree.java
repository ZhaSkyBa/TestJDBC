package tree;

import java.util.Stack;

/**
 * Author 余涛
 * Description 遍历二叉树操作
 * Time 2020/4/18 23:55
 */
public class QueryTree {

/*************************************前序查询************************************************/
    //递归实现
    public void preOrderRe(TreeNode biTree){
        if(biTree != null){
            System.out.print(biTree.value + "-");
            preOrderRe(biTree.left);
            preOrderRe(biTree.right);
        }
    }

    //非递归实现
    public void preOrder(TreeNode biTree){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(biTree != null || !stack.isEmpty()){
            //处理完左节点，全部压到堆栈
            while(biTree != null){
                System.out.print(biTree.value + "-");
                stack.push(biTree);
                biTree = biTree.left;
            }
            //从最底层节点回溯
            if(!stack.isEmpty()){
                biTree = stack.pop();
                biTree = biTree.right;
            }
        }
    }


/*************************************中序查询************************************************/
    //中序遍历递归实现
    public static void midOrderRe(TreeNode biTree){
        if(biTree == null){
            return;
        }else{
            midOrderRe(biTree.left);
            System.out.print(biTree.value + "-");
            midOrderRe(biTree.right);
        }
    }

    //中序遍历费递归实现
    public static void midOrder(TreeNode biTree){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(biTree != null || !stack.isEmpty()){
            while(biTree != null){
                stack.push(biTree);
                biTree = biTree.left;
            }
            if(!stack.isEmpty()){
                biTree = stack.pop();
                System.out.print(biTree.value + "-");
                biTree = biTree.right;
            }
        }
    }



/*************************************后序查询************************************************/
    //后序遍历递归实现
    public static void postOrderRe(TreeNode biTree){
        if(biTree == null) {
            return;
        }else {
            postOrderRe(biTree.left);
            postOrderRe(biTree.right);
            System.out.print(biTree.value + "-");
        }
    }

    //后序遍历非递归实现
    public static void postOrder(TreeNode biTree){
        int left = 1;//在辅助栈里表示左节点
        int right = 2;//在辅助栈里表示右节点
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //辅助栈，用来判断子节点返回父节点时处于左节点还是右节点。
        Stack<Integer> stack2 = new Stack<Integer>();

        while(biTree != null || !stack.empty()){
            //将节点压入栈1，并在栈2将节点标记为左节点
            while(biTree != null){
                stack.push(biTree);
                stack2.push(left);
                biTree = biTree.left;
            }
            //如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
            while(!stack.empty() && stack2.peek() == right){
                stack2.pop();
                System.out.println(stack.pop().value + "-");
            }
            //如果是从左子节点返回父节点，则将标记改为右子节点
            if(!stack.empty() && stack2.peek() == left){
                stack2.pop();
                stack2.push(right);
                biTree = stack.peek().right;
            }
        }
    }

}
