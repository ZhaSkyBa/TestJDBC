package tree;

/**
 * Description 功能说明
 * Time 2020/4/18 23:53
 */
public class TreeNode{ //节点结构
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value){
        this.value = value;
    }

    /**
     * 初始化二叉树结构
     */
    public static TreeNode[] init(int length){
        TreeNode[] node = new TreeNode[length];//以数组形式生成一棵完全二叉树
        for(int i = 0; i < length; i++){
            node[i] = new TreeNode(i);
        }
        for(int i = 0; i < length; i++){
            if(i*2+1 < length)
                node[i].left = node[i*2+1];
            if(i*2+2 < length)
                node[i].right = node[i*2+2];
        }
        return node;
    }
}
