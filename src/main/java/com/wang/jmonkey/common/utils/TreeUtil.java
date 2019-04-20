package com.wang.jmonkey.common.utils;

import com.wang.jmonkey.common.model.BaseTreeNode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 树形结构构造器
 * @Auther: HeJiawang
 * @Date: 2018/12/1
 */
public class TreeUtil {

    /**
     * 构建树形结构数据，用于树形表格的构建
     * 该方法不试用于循环中，调用一次后传入的treeNodes会被改变，如果是循环，子节点会越来越多
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends BaseTreeNode> List<T> bulid(List<T> treeNodes, Object root) {
        return bulid(treeNodes, root, false);
    }

    /**
     * 构建树形结构数据
     * @param treeNodeList
     * @param root
     * @param isClone true 不影响原来的treeNodeList， 但是要重写clone方法，参考SysResourceService guideInfo 方法
     * @param <T>
     * @return
     */
    public static <T extends BaseTreeNode> List<T> bulid(List<T> treeNodeList, Object root, boolean isClone) {
        List<T> treeNodes ;
        if(isClone){
            treeNodes = new ArrayList<>();
            treeNodeList.forEach( t -> treeNodes.add((T) t.clone()) );
        } else {
            treeNodes = treeNodeList;
        }

        // 构建树的算法
        List<T> trees = new ArrayList<>();
        treeNodes.forEach( treeNode -> {
            // 没有归属具体系统的菜单，在所有系统中都显示
            if ( null == treeNode.getParentId() || StringUtils.equals("", treeNode.getParentId()) ) trees.add(treeNode);

            // 查出归属该系统的菜单
            if( null != root ){
                if ( root == treeNode.getParentId() || StringUtils.equals(root.toString(), treeNode.getParentId()) ) trees.add(treeNode);
            }

            // 构建子节点
            treeNodes.forEach(it -> { if ( StringUtils.equals(it.getParentId(), treeNode.getId()) ) treeNode.addChildren(it); });
        });

        return trees;
    }

}
