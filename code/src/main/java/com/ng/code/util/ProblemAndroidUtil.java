package com.ng.code.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.code.util.model.CodeDataModel;
import com.ng.code.util.model.CodeState;
import com.ng.code.util.tree.CodeDirNode;
import com.ng.code.util.tree.CodeNode;
import com.ng.code.util.tree.HeadLayoutNode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/02/27
 * @description :
 */
public class ProblemAndroidUtil {

    public static CodeNode getRandomProblemJavaContentNew(Context context) {
        List<CodeNode> realNodeList = new ArrayList<>();
        List<BaseNode> mCodeData = CodeDataModel.getInstance().getCodeData(context);
        for (BaseNode tempNode : mCodeData) {
            if (tempNode instanceof CodeDirNode) {
                CodeDirNode codeDirNode = (CodeDirNode) tempNode;
                if (codeDirNode.getChildNode() != null) {
                    for (BaseNode tempCodeNode : codeDirNode.getChildNode()) {
                        if (tempCodeNode instanceof CodeNode) {
                            realNodeList.add((CodeNode) tempCodeNode);
                        }
                    }
                }
            }
        }
        int randNum = new Random().nextInt(realNodeList.size() - 1);
        return realNodeList.get(randNum);
    }

//    /**
//     * 从assets中随机抽取一道题
//     */
//    public static CodeNode getRandomProblemJavaContent(Context context) {
//        List<String> codeList = getAssetsJavaCodeList(context);
//        int randNum = new Random().nextInt(codeList.size() - 1);
//        String content = readAssets(context, codeList.get(randNum));
//        return new CodeNode(codeList.get(randNum), content);
//    }

    public static String getNowProgressAndroid(Context context) {
        List<String> codeList = getAssetsJavaCodeList(context);
        int easyCount = 0;
        int hardCount = 0;
        int readCount = 0;
        try {
            for (String codeStr : codeList) {
                //@Solution(easy = 1, hard = 0)
                String content = readAssets(context, codeStr);
                int easyIndex = content.indexOf("easy = ");
                if (Integer.parseInt(content.substring(easyIndex + 7, easyIndex + 8)) > 0) {
                    easyCount++;
                }
                int hardIndex = content.indexOf("hard = ");
                if (Integer.parseInt(content.substring(hardIndex + 7, hardIndex + 8)) > 0) {
                    hardCount++;
                }
            }
            for (CodeState temp : CodeDataModel.getInstance().mCodeStateList) {
                if (temp.state == 2) {
                    readCount++;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "all: " + codeList.size() + " easy: " + easyCount + " hard: " + hardCount + " read:" + readCount;
    }


    public static List<String> getAssetsJavaCodeList(Context context) {
        List<String> result = new ArrayList<>();
        try {
            String[] files = context.getAssets().list("");
            for (String temp : files) {
                String[] subFiles = context.getAssets().list(temp);
                for (String subTemp : subFiles) {
                    String realFilePath = temp + "/" + subTemp;
                    if (realFilePath.endsWith(".java")) {
                        result.add(realFilePath);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @NonNull
    public static List<BaseNode> getJavaCodeList(Context context) {
        //entity.setExpanded(i == 0);
        if (CodeDataModel.getInstance().isLocalCodeStateListIsNull(context)) {
            CodeDataModel.getInstance().mCodeStateList.clear();
        }
        List<BaseNode> dirList = new ArrayList<>();
        dirList.add(new HeadLayoutNode(""));
        try {
            String[] files = context.getAssets().list("");
            for (String temp : files) {
                List<BaseNode> codeList = new ArrayList<>();

                boolean needAdd = false;
                for (String subTemp : context.getAssets().list(temp)) {
                    String realFilePath = temp + "/" + subTemp;
                    if (realFilePath.endsWith(".java")) {
                        int id = IdGenerator.generateID();
                        String name = subTemp.replace(".java", "");
                        if (CodeDataModel.getInstance().isLocalCodeStateListIsNull(context)) {
                            CodeDataModel.getInstance().mCodeStateList.add(new CodeState(name, -1));
                        }
                        CodeNode node = new CodeNode(name, id, realFilePath, "");
                        codeList.add(node);
                        needAdd = true;
                    }
                }

                if (needAdd) {
                    CodeDirNode dirNode = new CodeDirNode(codeList, temp);
                    dirList.add(dirNode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CodeDataModel.getInstance().isLocalCodeStateListIsNull(context)) {
            CodeDataModel.getInstance().saveLocalCodeStateList(context);
        }
        return dirList;
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }
}
