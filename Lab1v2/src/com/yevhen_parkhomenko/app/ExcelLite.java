package com.yevhen_parkhomenko.app;

import static com.yevhen_parkhomenko.app.Evaluate.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashSet;
import java.util.Vector;


class RowHeaderRenderer extends JLabel implements ListCellRenderer {

    RowHeaderRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

public class ExcelLite extends JFrame
{
    FileInputStream fin = null;
    FileOutputStream out = null;

    static int rowCnt = 20, colCnt = 20;

    private DefaultTableModel tableModel = new DefaultTableModel(rowCnt, colCnt){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private JTable table1 = new JTable(tableModel);

    private JScrollPane scroll;
    private JTextField formula;

    private JLabel label;

    private JButton calculateBtn;
    private JButton closeBtn;
    private JButton scene1Btn;
    private JButton scene2Btn;
    private JButton helpBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton removeCollumnBtn = new JButton("Remove collum");
    private JButton addCollumnBtn = new JButton("Add collum");
    private JButton addRowBtn = new JButton("Add row");
    private JButton remRowBtn = new JButton("Remove row");
    private Object[][] formulas = new String[][]{
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
    };


    public ExcelLite() {
        super("Table");

        table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //table1.setRowSelectionAllowed (false);
       //table1.setColumnSelectionAllowed (false);
        //table1.setRowSelectionAllowed(false);
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
        table1.setFillsViewportHeight(true);
        table1.setSelectionBackground (Color.white);
        table1.setSelectionForeground (Color.blue);



        int[] selectedCell = new int[]{0, 0};
        ListModel rowHeaderNameStorage = new AbstractListModel() {
            String headers[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
                    , "17", "18", "19", "20"};

            public int getSize() {
                return headers.length;
            }

            public Object getElementAt(int ind) {
                return headers[ind];
            }
        };

        label = new JLabel();
        label.setText("Pick a cell to edit");

        addRowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rowCnt ==20) JOptionPane.showMessageDialog(null, "You have already reached the limit\n");
                else {
                    tableModel.addRow(new Vector<>());
                    rowCnt++;
                }
            }
        });
        
        remRowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rowCnt == 1) JOptionPane.showMessageDialog(null, "You can't have less rows\n");
                else tableModel.removeRow(--rowCnt);
            }
        });
        
        addCollumnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colCnt ==20) JOptionPane.showMessageDialog(null, "You have already reached the limit\n");
                else {
                    String header = String.valueOf((char) (65 + colCnt));
                    tableModel.addColumn(header);
                    colCnt++;
                }
            }
        });

        removeCollumnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colCnt == 1) JOptionPane.showMessageDialog(null, "You can't have less columns\n");
                else {
                    TableColumn col = table1.getColumnModel().getColumn(colCnt - 1);
                    table1.removeColumn(col);
                    tableModel.setColumnCount(--colCnt);
                }
            }
        });

        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                tableModel.setValueAt(String.valueOf(j%10+1)+".0",i,j);
            }
        }
        closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formula = new JTextField();
        MouseListener tableMouseListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table1.columnAtPoint(e.getPoint());
                int row = table1.rowAtPoint(e.getPoint());
                selectedCell[0] = row;
                selectedCell[1] = col;
                formula.setText((String) formulas[selectedCell[0]][selectedCell[1]]);
                label.setText("Selected cell is " + (char)(Integer.valueOf('A') + col) + String.valueOf(row + 1));
            }
        };
        calculateBtn = new JButton("Calculate");
        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp = formula.getText();
                    formulas[selectedCell[0]][selectedCell[1]] = temp;
                    for (int i = 0; i < rowCnt; i++) {
                        for (int j = 0; j < colCnt; j++) {
                            try {
                                temp = String.valueOf(formulas[i][j]);
                                String cell = String.valueOf('A' + j) + String.valueOf(i + 1);
                                HashSet<String> set = new HashSet<>();
                                set.add(cell);
                                temp = atPointParser(temp,set, formulas);
                                tableModel.setValueAt(evaluate(temp),i,j);
                                set.remove(cell);
                            } catch (Exception ex) {
                                tableModel.setValueAt("ERROR2",i,j);
                            }
                        }
                    }
                } catch (Exception ex) {
                    tableModel.setValueAt("ERROR",selectedCell[0],selectedCell[1]);
                    JOptionPane.showMessageDialog(null, "ERROR");
                }
            }
        });
        scene1Btn = new JButton("Table");
        scene1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene2Btn = new JButton("Menu");
                scene2Btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Box contents = new Box(BoxLayout.Y_AXIS);
                        contents.add(helpBtn);
                        contents.add(loadBtn);
                        contents.add(saveBtn);
                        contents.add(scene1Btn);
                        contents.add(closeBtn);
                        setContentPane(contents);
                        setSize(200, 200);
                        setVisible(true);
                    }
                });
                Box contents1 = new Box(BoxLayout.Y_AXIS);
                contents1.add(helpBtn);
                contents1.add(scene2Btn);
                contents1.add(closeBtn);
                contents1.add(scroll);
                contents1.add(label);
                contents1.add(formula);
                JPanel buttons = new JPanel();
                buttons.add(calculateBtn);
                buttons.add(addCollumnBtn);
                buttons.add(addRowBtn);
                buttons.add(removeCollumnBtn);
                buttons.add(remRowBtn);
                setContentPane(contents1);
                getContentPane().add(buttons, "South");
                setSize(750, 350);
                setVisible(true);
            }
        });
        loadBtn = new JButton("Load");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter a file name" + "\n" + "The correct format is *.txt");
                    File file = new File(fileName);
                    if(file.exists()){
                        fin = new FileInputStream(file);
                        BufferedReader bi = new BufferedReader(new InputStreamReader(fin));
                        int rows = Integer.parseInt(bi.readLine());
                        int columns = Integer.parseInt(bi.readLine());
                        JOptionPane.showMessageDialog(null,"The load was successful");
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                formulas[i][j] = bi.readLine();
                            }
                        }
                        for (int i = 0; i < rows ; i++) {
                            for (int j = 0; j < columns; j++) {


                                try {
                                    String temp = String.valueOf(formulas[i][j]);
                                    String cell = String.valueOf('A' + j) + String.valueOf(i + 1);
                                    HashSet<String> set = new HashSet<>();
                                    set.add(cell);
                                    temp = atPointParser(temp,set, formulas);
                                    tableModel.setValueAt(evaluate(temp),i,j);
                                    set.remove(cell);
                                } catch (Exception ex) {
                                    tableModel.setValueAt("ERROR",i,j);
                                }
                            }
                        }
                    }
                    else throw new Exception();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"ERROR: WRONG FILE NAME");
                }
                try {
                    fin.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog("Enter a file name" + "\n" + "The correct format is *.txt");
                    String temp = fileName.substring(fileName.length() - 4);
                    if (temp.equals(".txt")){
                        out = new FileOutputStream(fileName);
                        BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(out));
                        bo.write(String.valueOf(rowCnt));
                        bo.newLine();
                        bo.write(String.valueOf(colCnt));
                        bo.newLine();
                        for (int i = 0; i < rowCnt; i++) {
                            for (int j = 0; j < colCnt; j++) {
                                bo.write(String.valueOf(formulas[i][j]));
                                bo.newLine();
                            }
                        }
                        bo.close();
                    }
                    else throw new Exception();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"ERROR: WRONG FILE NAME");
                }
            }
        });
        helpBtn = new JButton("Help");
        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "Greetings from Yevhen Parkhomenko, K-25.\n";
                temp += "Operations available:\n";
                temp += "+ or - are add or subtract correspondingly.\n";
                temp += "/ or * are divide or multiply correspondingly.\n";
                temp += "^ is exponent.\n";
                temp += "mmax(x,y,...) or mmin(x,y,...) == maximum or minimum between x and y correspondingly.\n";
                temp += "+expr or - expr are unary positive or unary negative correspondingly.\n";
                temp += "(expr) == parenthesized expression.\n";
                temp += "Pointer to another cell should look like this:  @E4\n";
                temp += "Recursion is not allowed.\n";
                JOptionPane.showMessageDialog(null, temp);
            }
        });
        table1 = new JTable(tableModel);
        table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table1.addMouseListener(tableMouseListener);


        //table1.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box contents = new Box(BoxLayout.Y_AXIS);
        JList rowHeader = new JList(rowHeaderNameStorage);
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(table1.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(table1));
        scroll=new JScrollPane(table1);
        scroll.setRowHeaderView(rowHeader);
        contents.add(helpBtn);
        contents.add(loadBtn);
        contents.add(saveBtn);
        contents.add(scene1Btn);
        contents.add(closeBtn);

        setContentPane(contents);
        setSize(200, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ExcelLite();
    }
}


