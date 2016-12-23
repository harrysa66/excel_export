package com.excel.table;

import com.excel.vo.UserMoneyExport;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class UserMoneyTable extends JTable{
    //JTable表分页信息相关变量
    public int currentPage=1;
    public int pageCount=50;
    public int totalPage=0;
    public int totalRowCount=0;
    public int column=0;
    public int restCount;
    public Object[][] resultData;
    //JTable表信息相关变量
    public List<UserMoneyExport> userMoneys=UserMoneyExport.userMoneys;
    public String[] columnNames={"序号","金额","数量","操作",""};
    public DefaultTableModel model=null;
    public UserMoneyTable(){initTable();}

    public boolean isCellEditable(int row, int column) {				// 表格不可编辑
        return false;
    }
    // 隐藏列
    public void HiddenCell(JTable table, int column) {
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(
                column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column)
                .setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column)
                .setMinWidth(0);
    }
    /**
     * 获取下一页
     */
    public int getNextPage(){
        if(this.currentPage!=this.totalPage){
            return ++currentPage;
        }
        return -1;
    }
    /**
     * 获取上一页
     */
    public int getPreviousPage(){
        if(this.currentPage!=1){
            return --currentPage;
        }
        return -1;
    }
    /**
     * 获取最后一页
     */
    public int getLastPage(){
        return this.totalPage;
    }
    /**
     * 获取第一页
     */
    public int getFirstPage(){
        return 1;
    }
    /**
     * 获取总页数
     */
    public int getTotolPage(){
        return this.totalPage;
    }
    /**
     * 获取当前页
     */
    public int getCurrentPage(){
        if(this.currentPage < 1){
            return 1;
        }
        return this.currentPage;
    }
    /**
     * 获得原始数据集
     * @param userMoneys
     * @return
     */
    public Object[][] getData(List<UserMoneyExport> userMoneys){
        if(userMoneys.size()>0){
            Object[][] data=new Object[userMoneys.size()][4];
            for(int i=0;i<userMoneys.size();i++){
                UserMoneyExport um=userMoneys.get(i);
                Object[] a={i+1,um.getMoney(),um.getCount(),"查看人员信息",um.getUserExportList()};//把List集合的数据赋给Object数组
                data[i]=a;//把数组的值赋给二维数组的一行
            }
            return data;
        }
        return null;
    }
    /**
     * 初始化结果集
     * @param data
     */
    public void initResultData(Object[][] data){
        if(data!=null){
            currentPage = 1;
            resultData=data;//总的结果集
            column=data[0].length;//表的列数
            totalRowCount=data.length;//表的长度
            totalPage=totalRowCount%pageCount==0?totalRowCount/pageCount:totalRowCount/pageCount+1;//结果集的总页数
            restCount=totalRowCount%pageCount==0?pageCount:totalRowCount%pageCount;//最后一页的数据数
        }
    }
    /**
     * 获取分页数据
     * @return
     */
    public Object[][] getPageData(){
        Object[][] currentPageData=new Object[pageCount][column];//构造每页数据集
        if(this.getCurrentPage()<this.totalPage){//如果当前页数小于总页数，那么每页数目应该是规定的数pageCount
            for(int i=pageCount*(this.getCurrentPage()-1);i<pageCount*(this.getCurrentPage()-1)+pageCount;i++){
                for(int j=0;j<column;j++){
                    //把结果集中对应每页的每一行数据全部赋值给当前页的每一行的每一列
                    currentPageData[i%pageCount][j]=resultData[i][j];
                }
            }
        }else{
            //在动态改变数据结果集的时候，如果当前页没有数据了，则回到前一页（一般针对最后一页而言）
            if(pageCount*(this.getCurrentPage()-1)>=totalRowCount)this.currentPage--;
            for(int i=pageCount*(this.getCurrentPage()-1);i<pageCount*(this.getCurrentPage()-1)+restCount;i++){
                for(int j=0;j<column;j++){
                    currentPageData[i%pageCount][j]=resultData[i][j];
                }
            }
        }
        return currentPageData;
    }
    /**
     * 初始化表格数据
     */
    public void initTable(){
        Object[][] data=getData(userMoneys);
        if(data!=null){
            initResultData(data);
            model=new DefaultTableModel(getPageData(),columnNames);
        }else{
            //如果结果集中没有数据，那么就用空来代替数据集中的每一行
            //Object[][] nothing={{},{},{},{},{},{},{},{}};
            model=new DefaultTableModel(getPageData(),columnNames);
            totalRowCount=0;
        }
        this.setModel(model);
        this.setRowHeight(20);
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        HiddenCell(this,4);
    }
}
