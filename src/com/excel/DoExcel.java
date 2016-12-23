package com.excel;

import com.excel.util.*;
import com.excel.vo.User;
import com.excel.vo.UserExport;
import com.excel.vo.UserMoneyExport;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;


/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class DoExcel extends JFrame implements ActionListener{
    public static File chooseFile;
    private static JFileChooser fileChooser;
    private JScrollPane panel,userPanel;
    private JButton next,previous,export07,export03,open,userNext,userPrevious;
    private JLabel label1,label2;
    private UserMoneyTable table;
    private UserExportTable userTable;

    public static void main(String[] args){
        new DoExcel();
    }

    public DoExcel (){
        this.setTitle("导入Excel");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(500 , 700);
        sizeWindowOnScreen(this,0.4,0.9);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        table=new UserMoneyTable();
        panel = new JScrollPane(table);
        panel.setBounds(10, 50, (int)(screenSize.width*0.38), (int)(screenSize.height*0.7));
        JLabel excelLabel = new JLabel("文件：");
        open=new JButton("导入");
        this.add(panel);
        excelLabel.setBounds(40, 10, 70, 20);
        panel.add(excelLabel);
        open.setBounds(80, 10, 75, 20);
        panel.add(open);
        previous=new JButton("上一页");
        previous.setBounds((int)(this.getWidth()*0.15), (int)(this.getHeight()*0.91), 75,20);
        next=new JButton("下一页");
        next.setBounds((int)(this.getWidth()*0.35), (int)(this.getHeight()*0.91), 75, 20);
        export07=new JButton("导出07");
        export07.setBounds((int)(this.getWidth()*0.55), (int)(this.getHeight()*0.91), 75, 20);
        export03=new JButton("导出03");
        export03.setBounds((int)(this.getWidth()*0.75), (int)(this.getHeight()*0.91), 75, 20);
        previous.addActionListener(this);
        next.addActionListener(this);
        export07.addActionListener(this);
        export03.addActionListener(this);
        label1=new JLabel("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页|总数量为0个");
        label1.setBounds((int)(this.getWidth()*0.35), (int)(this.getHeight()*0.87), 250, 20);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
                    if (e.getClickCount() == 2) {//双击
                        Point p = e.getPoint();
                        int row = table.rowAtPoint(p);
                        int column = table.columnAtPoint(p);
                        if(column == 3){
                            List<UserExport> userExportList = (List<UserExport>)table.getModel().getValueAt(row, column+1);
                            if(userExportList != null && userExportList.size() > 0){//TODO 添加人员信息
                                System.out.println(userExportList);
                                JFrame frame = new JFrame("人员列表");
                                frame.setLayout(null);
                                sizeWindowOnScreen(frame,0.65,0.9);
                                frame.setLocationRelativeTo(null);
                                frame.setResizable(false);
                                userTable=new UserExportTable();
                                userPanel = new JScrollPane(userTable);
                                userPanel.setBounds(0, 0, (int)(frame.getWidth()*0.99), (int)(frame.getHeight()*0.85));
                                frame.add(userPanel);
                                userPrevious=new JButton("上一页");
                                userPrevious.setBounds((int)(frame.getWidth()*0.35), (int)(frame.getHeight()*0.92), 75,20);
                                userNext=new JButton("下一页");
                                userNext.setBounds((int)(frame.getWidth()*0.55), (int)(frame.getHeight()*0.92), 75, 20);
                                userPrevious.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int i=userTable.getPreviousPage();
                                        if(i==-1)return;
                                        DefaultTableModel model=new DefaultTableModel(userTable.getPageData(),userTable.columnNames);
                                        userTable.setModel(model);
                                        FitTableColumns(userTable);
                                        label2.setText("总共"+userTable.totalRowCount+"记录|当前第"+userTable.currentPage+"页");
                                    }
                                });
                                userNext.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int i=userTable.getNextPage();
                                        if(i==-1)return;
                                        DefaultTableModel model=new DefaultTableModel(userTable.getPageData(),userTable.columnNames);
                                        userTable.setModel(model);
                                        FitTableColumns(userTable);
                                        label2.setText("总共"+userTable.totalRowCount+"记录|当前第"+userTable.currentPage+"页");
                                    }
                                });
                                label2=new JLabel("总共"+userTable.totalRowCount+"记录|当前第"+userTable.currentPage+"页");
                                label2.setBounds((int)(frame.getWidth()*0.4), (int)(frame.getHeight()*0.87), 150, 20);
                                frame.getContentPane().setLayout(null);
                                frame.getContentPane().add(userPanel);
                                frame.getContentPane().add(userPrevious);
                                frame.getContentPane().add(userNext);
                                frame.getContentPane().add(label2);
                                UserExport.userExports.clear();
                                UserExport.userExports.addAll(userExportList);
                                userTable.initTable();
                                label2.setText("总共"+userTable.totalRowCount+"记录|当前第"+userTable.currentPage+"页");
                                frame.setVisible(true);
                            }
                        }
                    }
                }
            }
        });
        this.getContentPane().setLayout(null);
        this.getContentPane().add(panel);
        this.getContentPane().add(excelLabel);
        this.getContentPane().add(open);
        this.getContentPane().add(previous);
        this.getContentPane().add(next);
        this.getContentPane().add(export07);
        this.getContentPane().add(export03);
        this.getContentPane().add(label1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button=(JButton) event.getSource();
        if(button.equals(previous)){
            int i=table.getPreviousPage();
            if(i==-1)return;
        }
        if(button.equals(next)){
            int i=table.getNextPage();
            if(i==-1)return;
        }
        if(button.equals(export07)){
            exportExcel(true);
        }
        if(button.equals(export03)){
            exportExcel(false);
        }
        if(button.equals(open)){
            fileChooser = new JFileChooser();
            //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls","xlsx");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);
            //弹出一个文件选择提示框
            if (returnValue == fileChooser.APPROVE_OPTION) {
                //当用户选择文件后获取文件路径
                chooseFile = fileChooser.getSelectedFile();
                CacheManager.clearOnly("moneyGroup");
                CacheManager.clearOnly("totalCount");
                UserMoneyExport.userMoneys.clear();
                try{
                    String[] fieldNames = new String[]{"username", "cardNumber", "date", "money", "status"};
                    boolean isE2007 = false;
                    String filename = chooseFile.getName();
                    if(filename.endsWith("xlsx")){
                        isE2007 = true;
                    }
                    ExcelHelper eh;
                    if(isE2007){
                        eh = XssfExcelHelper.getInstance(chooseFile);
                    }else{
                        eh = HssfExcelHelper.getInstance(chooseFile);
                    }

                    List<User> list = eh.readExcel(User.class, fieldNames, false);
                    List<UserExport> exportList = new ArrayList<UserExport>();
                    UserExport userExport = null;
                    Map<String,UserMoneyExport> group = new HashMap<String,UserMoneyExport>();
                    UserMoneyExport userMoneyExport = null;
                    String money = null;
                    Integer countMoney = 0;
                    int totalCount = 0;
                    for (User user : list) {
                        userExport = new UserExport();
                        userExport.setUserNo(StringUtil.removeAllBlank(user.getUsername().split("/")[0]));
                        userExport.setCardNo(StringUtil.removeAllBlank(StringUtil.removeAllBlank(user.getCardNumber().replace("_",""))));
                        userExport.setName(StringUtil.removeAllBlank(user.getUsername().split("/")[1]));
                        userExport.setCardType("借记卡");
                        userExport.setAccountBank("中国农业银行");
                        userExport.setAccountProvince("天津市");
                        userExport.setAccountCity("");
                        userExport.setAccountNetwork("");
                        userExport.setMobilePhone("");
                        money = user.getMoney();
                        userMoneyExport = group.get(money);
                        if(userMoneyExport == null){
                            countMoney = 0;
                            exportList = new ArrayList<UserExport>();
                        }else{
                            countMoney = userMoneyExport.getCount();
                            exportList = userMoneyExport.getUserExportList();
                        }
                        exportList.add(userExport);
                        userMoneyExport = new UserMoneyExport(money,++countMoney,exportList);
                        group.put(money,userMoneyExport);
                        totalCount = totalCount + 1;
                    }
                    List<UserMoneyExport> userMoneyExportList = new ArrayList<UserMoneyExport>();
                    for (String key : group.keySet()) {
                        userMoneyExportList.add(group.get(key));
                    }
                    Collections.sort(userMoneyExportList);
                    CacheManager.putCache("moneyGroup",new Cache("moneyList",userMoneyExportList));
                    CacheManager.putCache("totalCount",new Cache("totalCount",totalCount));
                    System.out.println(CacheManager.getCacheInfo("moneyGroup"));
                    UserMoneyExport.userMoneys.addAll((List<UserMoneyExport>) CacheManager.getCacheInfo("moneyGroup").getValue());
                    table.initTable();
                    label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页|总数量为"+CacheManager.getCacheInfo("totalCount").getValue()+"个");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        DefaultTableModel model=new DefaultTableModel(table.getPageData(),table.columnNames);
        table.setModel(model);
        //设置隐藏列
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(
                4);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(4)
                .setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(4)
                .setMinWidth(0);
        label1.setText("总共"+table.totalRowCount+"记录|当前第"+table.currentPage+"页|总数量为"+CacheManager.getCacheInfo("totalCount").getValue()+"个");

    }

    private void exportExcel(boolean is07){
        int[] rows = table.getSelectedRows();
        String[] fieldNames = new String[]{"userNo","cardNo","name","cardType","accountBank","accountProvince","accountCity","accountNetwork","mobilePhone"};
        String[] titles = new String[]{"序号","账号","账户名称","账户类型","开户行行别","账户所在省","账户所在市","账户所属网点","手机"};
        int totalMoneyCount = 0;
        for (int row : rows) {
            totalMoneyCount = totalMoneyCount + Integer.parseInt(StringUtil.toString(table.getValueAt(row,2)));
        }
        if(totalMoneyCount < 60 && totalMoneyCount > 0){
            List<UserExport> userExports = new ArrayList<UserExport>();
            StringBuffer count = new StringBuffer();
            StringBuffer money = new StringBuffer();
            for (int row : rows) {
                count.append(StringUtil.toString(table.getValueAt(row,0))).append("&");
                money.append(StringUtil.toString(table.getValueAt(row,1))).append("&");
                List<UserExport> tempUserExports = (List<UserExport>) table.getValueAt(row,4);
                userExports.addAll(tempUserExports);
            }
            try {
                exportExcelList(userExports,count.toString().substring(0,count.toString().length()-1),money.toString().substring(0,money.toString().length()-1),fieldNames,titles,is07);
                JOptionPane.showMessageDialog(null, "导出成功！", "", 1);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "导出失败！", "", 0);
            }
        }else if(totalMoneyCount >= 60){
            for (int row : rows) {
                String count = StringUtil.toString(table.getValueAt(row,0));
                String money = StringUtil.toString(table.getValueAt(row,1));
                List<UserExport> userExports = (List<UserExport>) table.getValueAt(row,3);
                try {
                    exportExcelList(userExports,count,money,fieldNames,titles,is07);
                    JOptionPane.showMessageDialog(null, "导出成功！", "", 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "导出失败！", "", 0);
                }
            }
        }else{
            return;
        }
    }

    private void exportExcelList(List<UserExport> userExports,String count,String money,String[] fieldNames,String[] titles,boolean is07) throws Exception {
        System.out.println(userExports);
        int totalCount=userExports.size()%60==0?userExports.size()/60:userExports.size()/60+1;
        for(int i =0 ; i < totalCount ; i++){
            ExcelHelper eh;
            if(is07){
                String exportPath = chooseFile.getParent()+"/export-"+DateUtil.format(new Date(),"yyyyMMdd")+"-"+count+"-"+money+"("+(i+1)+").xlsx";
                File writeFile = new File(exportPath);
                eh = XssfExcelHelper.getInstance(writeFile);
            }else{
                String exportPath = chooseFile.getParent()+"/export-"+DateUtil.format(new Date(),"yyyyMMdd")+"-"+count+"-"+money+"("+(i+1)+").xls";
                File writeFile = new File(exportPath);
                eh = HssfExcelHelper.getInstance(writeFile);
            }
            int endIndex;
            if(i==totalCount-1){
                endIndex = userExports.size();
            }else{
                endIndex = 60*(i+1);
            }
            eh.writeExcel(UserExport.class,userExports.subList(60*i,endIndex),fieldNames,titles);
        }
    }

    /**
     *
     * @param jFrame
     * @param widthRate 宽度比例
     * @param heightRate 高度比例
     */
    private void sizeWindowOnScreen(JFrame jFrame, double widthRate, double heightRate)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        jFrame.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height *heightRate)));
    }

    private void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while(columns.hasMoreElements()){
            TableColumn column = (TableColumn)columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int)myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(int row = 0; row<rowCount; row++){
                int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width+myTable.getIntercellSpacing().width+20);
        }
    }

}
