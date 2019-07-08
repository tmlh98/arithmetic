package xyz.tmlh.airthmetic.tree.huffman;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

    // 生成赫夫曼树对应的赫夫曼编码
    // 思路:
    // 1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    // 生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111,
    // 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    // 2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        List<TreeNode> nodes = getNodes(content.getBytes());
        Map<Byte, String> codes = getCodes(createHuffmanTree(nodes));
        System.out.println(codes);
    }

    private static Map<Byte, String> getCodes(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes((TreeNode)root.getLeft(), "0", stringBuilder);
        // 处理root的右子树
        getCodes((TreeNode)root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * 
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(TreeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { // 如果node == null不处理
            // 判断当前node 是叶子结点还是非叶子结点
            if (node.getData() == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes((TreeNode)node.getLeft(), "0", stringBuilder2);
                // 向右递归
                getCodes((TreeNode)node.getRight(), "1", stringBuilder2);
            } else { // 说明是一个叶子结点
                // 就表示找到某个叶子结点的最后
                huffmanCodes.put(node.getData(), stringBuilder2.toString());
            }
        }
    }

    /**
     * 
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式 [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static List<TreeNode> getNodes(byte[] bytes) {
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();

        // 遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据,第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new TreeNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 创建一颗哈夫曼树
    private static TreeNode createHuffmanTree(List<TreeNode> nodes) {
        LinkedList<TreeNode> nodeList = (LinkedList<TreeNode>)nodes;
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);

            TreeNode firstNode = nodeList.poll();
            TreeNode secondNode = nodeList.poll();
            TreeNode rootNode = new TreeNode(firstNode.getNo() + secondNode.getNo());
            rootNode.setLeft(firstNode);
            rootNode.setRight(secondNode);
            nodeList.add(rootNode);
        }

        return nodeList.peek();
    }

}

class TreeNode extends Node {

    /**
     * 字符
     */
    private Byte data;

    /**
     * @param no 表示字符出现的次数
     */
    public TreeNode(int no) {
        super(no);
    }

    public TreeNode(Byte data, int no) {
        super(no);
        this.data = data;
    }

    public Byte getData() {
        return data;
    }

    public String toString() {
        return "data:" + data + ",weight:" + getNo();
    }

}