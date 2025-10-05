/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pullu
 */
public final class Main2 extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    DefaultTableModel model;
    Statement st;

    /**
     * Creates new form Main2
     */
    public Main2() {
        initComponents();
        Loadlibraryno();
        loadbook();
        generateShowbook();
        loadbookdetails();
        bookNameComBox();
        loadbookid();
        loadtable();
        LocalDate d1=LocalDate.now();
        LocalDate d2=d1.plusDays(15);
        issuedate1.setText(d1.toString());
        issuedate1.setEditable(false);
        returndate1.setText(d2.toString());
        returndate1.setEditable(false);
        
    }
     public Main2(String msg) {
         unamemsg.setText(msg);
     }
    
    public void loadtable()
    {
        try {
            model=(DefaultTableModel)showtable.getModel();
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st=con.createStatement();
            model.setRowCount(0);
            ResultSet rs=st.executeQuery("select * from issuebook");
             while(rs.next())
           {
               model.insertRow(model.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
           }  
        } catch (SQLException ex) {
            Logger.getLogger(ShowDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadbookid()
    {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st=con.createStatement();
            ResultSet rs=st.executeQuery("Select bookid1 from issuebook");
            while(rs.next())
            {
               bookid2.addItem(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void bookNameComBox()
    {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct(bookname) from book");
            
            while(rs.next())
            {
                bookname1.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void Loadlibraryno()
{
       
    libno.setEditable(false);
    libno.setText("VASAVI"+branchtype.getSelectedItem().toString()+"");
    
} 
    public void loadbook() {
       try {
            model=(javax.swing.table.DefaultTableModel)booktable.getModel();
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
           st=con.createStatement();
           model.setRowCount(0);
           ResultSet rs=st.executeQuery("select * from book");
           
           while(rs.next())
           {
               model.insertRow(model.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3)});
           }  
       } catch (SQLException ex) {
           Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
     public void generateShowbook() {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("select * from bookdetails");
            
            if(rs1.next()==true)
            {
               while (rs.next()) {
                for (int i = 1; i <= rs.getInt(3); i++) {
                    String s = rs.getString(1).substring(0, 3) + rs.getString(2).substring(0, 3) + String.format("%03d", i).toUpperCase();
                    pst=con.prepareStatement("insert into bookdetails values(?,?,?)");
                    pst.setString(2,rs.getString(1));
                    pst.setString(3,rs.getString(2));
                    pst.setString(1,s);
                    pst.executeUpdate();
                }
            } 
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(BookDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadbookdetails()
    {
        try {
           model=(DefaultTableModel)bookdetailstable.getModel();
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
           st=con.createStatement();
           model.setRowCount(0);
           ResultSet rs=st.executeQuery("select * from bookdetails");
           
           while(rs.next())
           {
               model.insertRow(model.getRowCount(),new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)});
           }  
       } catch (SQLException ex) {
           Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        unamemsg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        addmembt = new javax.swing.JButton();
        addbookbt = new javax.swing.JButton();
        bookdetailsbt = new javax.swing.JButton();
        issuebookbt = new javax.swing.JButton();
        returnbookbt = new javax.swing.JButton();
        showdetailsbt = new javax.swing.JButton();
        TabbedPane = new javax.swing.JTabbedPane();
        addmempanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        libno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        memtype = new javax.swing.JComboBox<>();
        branchtype = new javax.swing.JComboBox<>();
        name = new javax.swing.JTextField();
        sno = new javax.swing.JTextField();
        empno = new javax.swing.JTextField();
        addbt = new javax.swing.JButton();
        addbookpanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bookname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        author = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        bookcount = new javax.swing.JTextField();
        addbk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        booktable = new javax.swing.JTable();
        bookdetailspanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookdetailstable = new javax.swing.JTable();
        issuebookpanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        bookname1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        author1 = new javax.swing.JComboBox<>();
        bookid1 = new javax.swing.JComboBox<>();
        memtype1 = new javax.swing.JComboBox<>();
        name1 = new javax.swing.JTextField();
        num1 = new javax.swing.JTextField();
        issuedate1 = new javax.swing.JTextField();
        returndate1 = new javax.swing.JTextField();
        issuebk = new javax.swing.JButton();
        retrunbookpanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        bookid2 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        bookname2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        author2 = new javax.swing.JTextField();
        memtype2 = new javax.swing.JTextField();
        name2 = new javax.swing.JTextField();
        num2 = new javax.swing.JTextField();
        issuedate2 = new javax.swing.JTextField();
        returndate2 = new javax.swing.JTextField();
        returnbk = new javax.swing.JButton();
        showdetailspanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        showtable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/R.png"))); // NOI18N
        jLabel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setText("USER: ");

        unamemsg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        unamemsg.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unamemsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unamemsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        addmembt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addmembt.setText("Add Mem");
        addmembt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addmembtMouseClicked(evt);
            }
        });
        addmembt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmembtActionPerformed(evt);
            }
        });

        addbookbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addbookbt.setText("Add Book");
        addbookbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addbookbtMouseClicked(evt);
            }
        });
        addbookbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbookbtActionPerformed(evt);
            }
        });

        bookdetailsbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bookdetailsbt.setText("Book Details");
        bookdetailsbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookdetailsbtMouseClicked(evt);
            }
        });
        bookdetailsbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookdetailsbtActionPerformed(evt);
            }
        });

        issuebookbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        issuebookbt.setText("Issue Book");
        issuebookbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                issuebookbtMouseClicked(evt);
            }
        });
        issuebookbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebookbtActionPerformed(evt);
            }
        });

        returnbookbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        returnbookbt.setText("Return Book");
        returnbookbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnbookbtMouseClicked(evt);
            }
        });
        returnbookbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnbookbtActionPerformed(evt);
            }
        });

        showdetailsbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        showdetailsbt.setText("Show Details");
        showdetailsbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showdetailsbtMouseClicked(evt);
            }
        });
        showdetailsbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showdetailsbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(issuebookbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addbookbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addmembt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookdetailsbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnbookbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showdetailsbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(addmembt)
                .addGap(30, 30, 30)
                .addComponent(addbookbt)
                .addGap(30, 30, 30)
                .addComponent(bookdetailsbt)
                .addGap(31, 31, 31)
                .addComponent(issuebookbt)
                .addGap(29, 29, 29)
                .addComponent(returnbookbt)
                .addGap(26, 26, 26)
                .addComponent(showdetailsbt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Library No: ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Member Type:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Branch:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Std ID:  ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Emp ID:  ");

        memtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Value", "Student", "Faculty" }));
        memtype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memtypeItemStateChanged(evt);
            }
        });

        branchtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Value", "CSE", "CAI", "AIML", "ECE", "ECT", "EEE", "CIVIL", "MECH", " " }));
        branchtype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                branchtypeItemStateChanged(evt);
            }
        });

        addbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addbt.setText("Add Member");
        addbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addmempanelLayout = new javax.swing.GroupLayout(addmempanel);
        addmempanel.setLayout(addmempanelLayout);
        addmempanelLayout.setHorizontalGroup(
            addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addmempanelLayout.createSequentialGroup()
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addmempanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(39, 39, 39)
                        .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(branchtype, javax.swing.GroupLayout.Alignment.LEADING, 0, 188, Short.MAX_VALUE)
                            .addComponent(libno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memtype, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(empno)))
                    .addGroup(addmempanelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(addbt)))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        addmempanelLayout.setVerticalGroup(
            addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addmempanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(libno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(memtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(branchtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addmempanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(empno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(addbt)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Add Mem", addmempanel);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Book name: ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Author:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("No. of Books: ");

        addbk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addbk.setText("Add Book");
        addbk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbkActionPerformed(evt);
            }
        });

        booktable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Name", "Author", "Author"
            }
        ));
        jScrollPane1.setViewportView(booktable);

        javax.swing.GroupLayout addbookpanelLayout = new javax.swing.GroupLayout(addbookpanel);
        addbookpanel.setLayout(addbookpanelLayout);
        addbookpanelLayout.setHorizontalGroup(
            addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addbookpanelLayout.createSequentialGroup()
                .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addbookpanelLayout.createSequentialGroup()
                        .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addbookpanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(addbookpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bookcount, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addbookpanelLayout.createSequentialGroup()
                                        .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(33, 33, 33)
                                        .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bookname, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(addbookpanelLayout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(addbk)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
                .addContainerGap())
        );
        addbookpanelLayout.setVerticalGroup(
            addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addbookpanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(bookname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(bookcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addbk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(394, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Add Book", addbookpanel);

        bookdetailstable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bookdetailstable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        bookdetailstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Name", "Author"
            }
        ));
        bookdetailstable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(bookdetailstable);

        javax.swing.GroupLayout bookdetailspanelLayout = new javax.swing.GroupLayout(bookdetailspanel);
        bookdetailspanel.setLayout(bookdetailspanelLayout);
        bookdetailspanelLayout.setHorizontalGroup(
            bookdetailspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookdetailspanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        bookdetailspanelLayout.setVerticalGroup(
            bookdetailspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookdetailspanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Book Details", bookdetailspanel);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Book Name: ");

        bookname1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bookname1ItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Author:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Book ID: ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Member Type:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Name: ");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Number: ");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Issue Date: ");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Return Date: ");

        memtype1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Value", "Student", "Faculty" }));

        issuebk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        issuebk.setText("Issue Book");
        issuebk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout issuebookpanelLayout = new javax.swing.GroupLayout(issuebookpanel);
        issuebookpanel.setLayout(issuebookpanelLayout);
        issuebookpanelLayout.setHorizontalGroup(
            issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(issuebookpanelLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(36, 36, 36)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returndate1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(issuedate1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(num1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(memtype1, 0, 187, Short.MAX_VALUE)
                        .addComponent(bookid1, 0, 187, Short.MAX_VALUE)
                        .addComponent(author1, 0, 187, Short.MAX_VALUE)
                        .addComponent(bookname1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(name1)))
                .addContainerGap(270, Short.MAX_VALUE))
            .addGroup(issuebookpanelLayout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(issuebk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        issuebookpanelLayout.setVerticalGroup(
            issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(issuebookpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bookname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(author1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(bookid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(memtype1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(num1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(issuedate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(issuebookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(returndate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(issuebk)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Issue Book", issuebookpanel);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Book ID: ");

        bookid2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bookid2ItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Book Name: ");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Author: ");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Member Type:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Name: ");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Number: ");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Issuedate: ");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Return Date:");

        returnbk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        returnbk.setText("Return Book");
        returnbk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnbkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout retrunbookpanelLayout = new javax.swing.GroupLayout(retrunbookpanel);
        retrunbookpanel.setLayout(retrunbookpanelLayout);
        retrunbookpanelLayout.setHorizontalGroup(
            retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(retrunbookpanelLayout.createSequentialGroup()
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(retrunbookpanelLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(retrunbookpanelLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(35, 35, 35)
                                .addComponent(returndate2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                            .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(retrunbookpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(49, 49, 49)
                                    .addComponent(issuedate2))
                                .addGroup(retrunbookpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addGap(59, 59, 59)
                                    .addComponent(num2))
                                .addGroup(retrunbookpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addGap(73, 73, 73)
                                    .addComponent(name2))
                                .addGroup(retrunbookpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(28, 28, 28)
                                    .addComponent(memtype2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, retrunbookpanelLayout.createSequentialGroup()
                                    .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel20))
                                    .addGap(37, 37, 37)
                                    .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bookid2, 0, 218, Short.MAX_VALUE)
                                        .addComponent(bookname2)
                                        .addComponent(author2))))))
                    .addGroup(retrunbookpanelLayout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(returnbk)))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        retrunbookpanelLayout.setVerticalGroup(
            retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(retrunbookpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(bookid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(bookname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(author2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(memtype2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(name2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(num2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(issuedate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(retrunbookpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(returndate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(returnbk)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Return Book", retrunbookpanel);

        showtable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        showtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Name", "Author", "Book ID", "Member Type", "Name", "Number", "Issue Date", "Return Date"
            }
        ));
        showtable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(showtable);

        javax.swing.GroupLayout showdetailspanelLayout = new javax.swing.GroupLayout(showdetailspanel);
        showdetailspanel.setLayout(showdetailspanelLayout);
        showdetailspanelLayout.setHorizontalGroup(
            showdetailspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showdetailspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addContainerGap())
        );
        showdetailspanelLayout.setVerticalGroup(
            showdetailspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showdetailspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Show Details", showdetailspanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPane)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addmembtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmembtActionPerformed
        // TODO add your handling code here:
        TabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_addmembtActionPerformed

    private void addbookbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbookbtActionPerformed
        // TODO add your handling code here:
         TabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_addbookbtActionPerformed

    private void bookdetailsbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookdetailsbtActionPerformed
        // TODO add your handling code here:
         TabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_bookdetailsbtActionPerformed

    private void issuebookbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebookbtActionPerformed
        // TODO add your handling code here:
         TabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_issuebookbtActionPerformed

    private void returnbookbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnbookbtActionPerformed
        // TODO add your handling code here:
         TabbedPane.setSelectedIndex(4);
    }//GEN-LAST:event_returnbookbtActionPerformed

    private void showdetailsbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showdetailsbtActionPerformed
        // TODO add your handling code here:
         TabbedPane.setSelectedIndex(5);
         
    }//GEN-LAST:event_showdetailsbtActionPerformed

    private void memtypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memtypeItemStateChanged
        // TODO add your handling code here:
        String mem=memtype.getSelectedItem().toString();
        if(mem.equals("Student"))
        {
            sno.setEnabled(true);
            empno.setEnabled(false);
            empno.setText("");
        }
        else if(mem.equals("Faculty"))
        {
            sno.setEnabled(false);
            empno.setEnabled(true);
            sno.setText("");
        }
        else
        {
            sno.setEnabled(true);
            empno.setEnabled(true);
        }
    }//GEN-LAST:event_memtypeItemStateChanged

    private void addbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtActionPerformed
        // TODO add your handling code here:
        if (name.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Please Fill Name");
        return;
    }

    if (sno.getText().equals("") && empno.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Please fill either SNO or EMPNO");
        return;
    }

    try {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        pst = con.prepareStatement("INSERT INTO AddMem VALUES (?, ?, ?, ?, ?, ?)");
        pst.setString(1, libno.getText());
        pst.setString(2, String.valueOf(memtype.getSelectedItem()));
        pst.setString(3, String.valueOf(branchtype.getSelectedItem()));
        pst.setString(4, name.getText());
        pst.setString(5, sno.getText());
        pst.setString(6, empno.getText());

        int rc = pst.executeUpdate();
        if (rc == 1) {
            JOptionPane.showMessageDialog(this, "Member Added Successfully");
            
          
            libno.setText("");
            memtype.setSelectedIndex(0);
            branchtype.setSelectedIndex(0);
            name.setText("");
            sno.setText("");
            empno.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Adding Failed");
        }

       
       
        
    } catch (SQLException ex) {
        Logger.getLogger(AddMem.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
    }
    }//GEN-LAST:event_addbtActionPerformed

    private void branchtypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branchtypeItemStateChanged
        // TODO add your handling code here:
         Loadlibraryno();
    }//GEN-LAST:event_branchtypeItemStateChanged

    private void addbkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbkActionPerformed
        // TODO add your handling code here:
        try {
           // TODO add your handling code here:
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
           st=con.createStatement();
           model.setRowCount(0);
           ResultSet rs=st.executeQuery("select * from book");
           loadbook();
           
           int c=0;
           while(rs.next())
           {
               if(bookname.getText().equals(rs.getString(1)) && author.getText().equals(rs.getString(2)))
               {
                   c=1;
                   break;
               }
           }
           if(c==0)
           {
              pst=con.prepareStatement("insert into book values(?,?,?)");
              
              pst.setString(1,bookname.getText());
              pst.setString(2,author.getText());
              pst.setInt(3,Integer.valueOf(bookcount.getText()));
              pst.executeUpdate();
              model=(javax.swing.table.DefaultTableModel)booktable.getModel();
              model.insertRow(model.getRowCount(),new Object[]{bookname.getText(),author.getText(),Integer.valueOf(bookcount.getText())});
              
           }
           else
           {
              pst=con.prepareStatement("update book set bookcount=bookcount+? where bookname=? and author=?"); 
              pst.setInt(1,Integer.valueOf(bookcount.getText()));
              pst.setString(2,bookname.getText());
              pst.setString(3,author.getText());
              pst.executeUpdate();
            
              
              model=(javax.swing.table.DefaultTableModel)booktable.getModel();
              Statement st1=con.createStatement();
              ResultSet rs1=st1.executeQuery("select * from book");
              model.setRowCount(0);
              
              
              while(rs1.next())
              {
                  model.insertRow(model.getRowCount(),new Object[]{rs1.getString(1),rs1.getString(2),rs1.getInt(3)});
                  
                  
              }
              
           }
           JOptionPane.showMessageDialog(this,"Book Inserted Successfully");
           loadbook();
       } catch (SQLException ex) {
           Logger.getLogger(AddBooks.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_addbkActionPerformed

    private void bookname1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bookname1ItemStateChanged
        // TODO add your handling code here:
        try {
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select author from book where bookname='"+bookname1.getSelectedItem().toString()+"'");
            author1.removeAllItems();
            while(rs.next())
            {
                author1.addItem(rs.getString(1));
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         try {
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select bookid from bookdetails where bookname='"+bookname1.getSelectedItem().toString()+"'");
            bookid1.removeAllItems();
            while(rs1.next())
            {
                bookid1.addItem(rs1.getString(1));
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bookname1ItemStateChanged

    private void issuebkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebkActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            pst=con.prepareStatement("insert into issuebook values(?,?,?,?,?,?,?,?)");
            pst.setString(1,bookname1.getSelectedItem().toString());
            pst.setString(2,author1.getSelectedItem().toString());
            pst.setString(3,bookid1.getSelectedItem().toString());
            pst.setString(4,memtype1.getSelectedItem().toString());
            pst.setString(5,name1.getText());
            pst.setString(6,num1.getText());
            pst.setString(7,issuedate1.getText());
            pst.setString(8,returndate1.getText());
            
            pst1=con.prepareStatement("update book set bookcount=bookcount-1 where bookname=? and author=?");
            pst1.setString(1,bookname1.getSelectedItem().toString());
            pst1.setString(2,author1.getSelectedItem().toString());
            pst1.executeUpdate();
            int rc=pst.executeUpdate();
            if(rc==1)
            {
                JOptionPane.showMessageDialog(this,"Book Issued Succussefully");
                bookname1.setSelectedItem("");
                author1.setSelectedItem("");
                bookid1.setSelectedItem("");
                memtype1.setSelectedItem("");
                name1.setText("");
                num1.setText("");
                issuedate1.setText("");
                returndate1.setText("");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Book Issue Failed");
            }
            
             
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_issuebkActionPerformed

    private void bookid2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bookid2ItemStateChanged
        // TODO add your handling code here:
        try {
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            st=con.createStatement();
            ResultSet rs=st.executeQuery("Select * from issuebook where bookid1='"+bookid2.getSelectedItem()+"'");
            while(rs.next())
            {
                bookname2.setText(rs.getString(1));
                bookname2.setEditable(false);
                author2.setText(rs.getString(2));
                author2.setEditable(false);
                memtype2.setText(rs.getString(4));
                memtype2.setEditable(false);
                name2.setText(rs.getString(5));
                name2.setEditable(false);
                num2.setText(rs.getString(6));
                num2.setEditable(false);
                issuedate2.setText(rs.getString(7));
                issuedate2.setEditable(false);
                LocalDate d1=LocalDate.now();
                returndate2.setText(d1.toString());
                returndate2.setEditable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bookid2ItemStateChanged

    private void returnbkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnbkActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            pst=con.prepareStatement("delete from issuebook where bookid1='"+bookid2.getSelectedItem()+"'");
            
            pst1=con.prepareStatement("update book set bookcount=bookcount+1 where bookname=? and author=?");
            pst1.setString(1,bookname2.getText());
            pst1.setString(2,author2.getText());
            pst1.executeUpdate();
            int rc=pst.executeUpdate();
            if(rc==1)
            {
                JOptionPane.showMessageDialog(this,"Book Returned!");
                bookname2.setText("");
                author2.setText("");
                memtype2.setText("");
                name2.setText("");
                num2.setText("");
                issuedate2.setText("");
                returndate2.setText("");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Book Return Failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_returnbkActionPerformed

    private void addmembtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addmembtMouseClicked
        // TODO add your handling code here:
        addmembt.setBackground(Color.red);
        addbookbt.setBackground(Color.white);
        bookdetailsbt.setBackground(Color.white);
        issuebookbt.setBackground(Color.white);
        returnbookbt.setBackground(Color.white);
        showdetailsbt.setBackground(Color.white);
        
    }//GEN-LAST:event_addmembtMouseClicked

    private void addbookbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbookbtMouseClicked
        // TODO add your handling code here:
         addmembt.setBackground(Color.white);
        addbookbt.setBackground(Color.red);
        bookdetailsbt.setBackground(Color.white);
        issuebookbt.setBackground(Color.white);
        returnbookbt.setBackground(Color.white);
        showdetailsbt.setBackground(Color.white);
    }//GEN-LAST:event_addbookbtMouseClicked

    private void bookdetailsbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookdetailsbtMouseClicked
        // TODO add your handling code here:
         addmembt.setBackground(Color.white);
        addbookbt.setBackground(Color.white);
        bookdetailsbt.setBackground(Color.red);
        issuebookbt.setBackground(Color.white);
        returnbookbt.setBackground(Color.white);
        showdetailsbt.setBackground(Color.white);
    }//GEN-LAST:event_bookdetailsbtMouseClicked

    private void issuebookbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_issuebookbtMouseClicked
        // TODO add your handling code here:
         addmembt.setBackground(Color.white);
        addbookbt.setBackground(Color.white);
        bookdetailsbt.setBackground(Color.white);
        issuebookbt.setBackground(Color.red);
        returnbookbt.setBackground(Color.white);
        showdetailsbt.setBackground(Color.white);
    }//GEN-LAST:event_issuebookbtMouseClicked

    private void returnbookbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnbookbtMouseClicked
        // TODO add your handling code here:
         addmembt.setBackground(Color.white);
        addbookbt.setBackground(Color.white);
        bookdetailsbt.setBackground(Color.white);
        issuebookbt.setBackground(Color.white);
        returnbookbt.setBackground(Color.red);
        showdetailsbt.setBackground(Color.white);
    }//GEN-LAST:event_returnbookbtMouseClicked

    private void showdetailsbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showdetailsbtMouseClicked
        // TODO add your handling code here:
         addmembt.setBackground(Color.white);
        addbookbt.setBackground(Color.white);
        bookdetailsbt.setBackground(Color.white);
        issuebookbt.setBackground(Color.white);
        returnbookbt.setBackground(Color.white);
        showdetailsbt.setBackground(Color.red);
    }//GEN-LAST:event_showdetailsbtMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Loginpage l=new Loginpage();
        l.setVisible(true);
        Main2 m=new Main2();
        m.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JButton addbk;
    private javax.swing.JButton addbookbt;
    private javax.swing.JPanel addbookpanel;
    private javax.swing.JButton addbt;
    private javax.swing.JButton addmembt;
    private javax.swing.JPanel addmempanel;
    private javax.swing.JTextField author;
    private javax.swing.JComboBox<String> author1;
    private javax.swing.JTextField author2;
    private javax.swing.JTextField bookcount;
    private javax.swing.JButton bookdetailsbt;
    private javax.swing.JPanel bookdetailspanel;
    private javax.swing.JTable bookdetailstable;
    private javax.swing.JComboBox<String> bookid1;
    private javax.swing.JComboBox<String> bookid2;
    private javax.swing.JTextField bookname;
    private javax.swing.JComboBox<String> bookname1;
    private javax.swing.JTextField bookname2;
    private javax.swing.JTable booktable;
    private javax.swing.JComboBox<String> branchtype;
    private javax.swing.JTextField empno;
    private javax.swing.JButton issuebk;
    private javax.swing.JButton issuebookbt;
    private javax.swing.JPanel issuebookpanel;
    private javax.swing.JTextField issuedate1;
    private javax.swing.JTextField issuedate2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField libno;
    private javax.swing.JComboBox<String> memtype;
    private javax.swing.JComboBox<String> memtype1;
    private javax.swing.JTextField memtype2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name1;
    private javax.swing.JTextField name2;
    private javax.swing.JTextField num1;
    private javax.swing.JTextField num2;
    private javax.swing.JPanel retrunbookpanel;
    private javax.swing.JButton returnbk;
    private javax.swing.JButton returnbookbt;
    private javax.swing.JTextField returndate1;
    private javax.swing.JTextField returndate2;
    private javax.swing.JButton showdetailsbt;
    private javax.swing.JPanel showdetailspanel;
    private javax.swing.JTable showtable;
    private javax.swing.JTextField sno;
    private javax.swing.JLabel unamemsg;
    // End of variables declaration//GEN-END:variables
}
