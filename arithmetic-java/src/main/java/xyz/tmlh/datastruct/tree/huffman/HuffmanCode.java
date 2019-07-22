package xyz.tmlh.datastruct.tree.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
        byte[] bytes = content.getBytes();
        byte[] huffmanZip = huffmanZip(bytes);
        
        byte[] decode = decode(huffmanCodes, huffmanZip);
        for (byte b : decode) {
            System.out.print((char)b);
        }
        
        System.out.println("---------------------------------------------------------------------");
        
        //测试解压文件
        String zipFile = "D:\\note\\note.txt";
        String dstFile = "D:\\note\\note.xml";
        zipFile(zipFile, "D:\\note");
//        unZipFile(zipFile, dstFile);
        System.out.println("解压成功!");
    }
    
    
    //编写一个方法，完成对压缩文件的解压
    /**
     * 
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    
    
    /**
     * 将一个文件进行压缩
     * 
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
    
    //完成数据的解压
    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"
    /**
     * 完成对压缩数据的解码
     * 
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder sBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            sBuilder.append(byteToBitString(!flag, b));
        }
        
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<Byte>();
        String temp = "";
        String bytes = sBuilder.toString();
        for (int i = 0; i < bytes.length(); i++) {
            temp += bytes.charAt(i);
            if(map.containsKey(temp)) {
                list.add(map.get(temp));
                temp = "";
            }
        }
        
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }
    
    
    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int`
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }
    
    /**
     * 将前面的方法封装起来
     * 
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<CodeNode> nodes = getNodes(bytes);
        //获取哈夫曼编码表
        Map<Byte, String> haffmanCodeTable = getCodes(createHuffmanTree(nodes));
        byte[] zip = zip(bytes , haffmanCodeTable);
        return zip;
    }
    
    /**
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * 
     * @param bytes 这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[] 
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : bytes) {
            sBuilder.append(huffmanCodes.get(b));
        }
        int len = (sBuilder.length() + 7) / 8;
        
        //创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        
        for (int i = 0,index = 0; i < sBuilder.length(); i += 8, index ++) {
            String str = "";
            if(i + 8 > sBuilder.length()) {
                str = sBuilder.substring(i);
            }else {
                str = sBuilder.substring(i , i + 8);
            }
            huffmanCodeBytes[index] = (byte)Integer.parseInt(str, 2);
        }
        return huffmanCodeBytes;
    }
    
    
    /**
     * 重载getCodes 方法
      *
      * @param @param root
      * @param @return    参数
      * @return Map<Byte,String>    返回类型
      * @throws
     */
    private static Map<Byte, String> getCodes(CodeNode root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes((CodeNode)root.getLeft(), "0", stringBuilder);
        // 处理root的右子树
        getCodes((CodeNode)root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * 
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(CodeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { // 如果node == null不处理
            // 判断当前node 是叶子结点还是非叶子结点
            if (node.getData() == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes((CodeNode)node.getLeft(), "0", stringBuilder2);
                // 向右递归
                getCodes((CodeNode)node.getRight(), "1", stringBuilder2);
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
    private static List<CodeNode> getNodes(byte[] bytes) {
        LinkedList<CodeNode> nodes = new LinkedList<CodeNode>();

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
            nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 创建一颗哈夫曼树
    private static CodeNode createHuffmanTree(List<CodeNode> nodes) {
        LinkedList<CodeNode> nodeList = (LinkedList<CodeNode>)nodes;
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);

            CodeNode firstNode = nodeList.poll();
            CodeNode secondNode = nodeList.poll();
            CodeNode rootNode = new CodeNode(firstNode.getNo() + secondNode.getNo());
            rootNode.setLeft(firstNode);
            rootNode.setRight(secondNode);
            nodeList.add(rootNode);
        }

        return nodeList.peek();
    }

}

class CodeNode extends Node {

    /**
     * 字符
     */
    private Byte data;

    /**
     * @param no 表示字符出现的次数
     */
    public CodeNode(int no) {
        super(no);
    }

    public CodeNode(Byte data, int no) {
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