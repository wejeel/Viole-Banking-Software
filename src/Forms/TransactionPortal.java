/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author WEJE
 */
public class TransactionPortal extends javax.swing.JFrame {

    /**
     * Creates new form TransactionPortal
     */
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;

String accbal;
String accholder;
String accno;
String updateinfo ="Withdrawal: ";
String rembal;

//deposit
String accbal2;
String accholder2;
String accno2;
String slip_no2;

String accbal5000;
//deposit
    public TransactionPortal() {
        initComponents();
          setLocationRelativeTo(null);
    }
public void GetBalance()
{
try
{
  
         accno = accnumber.getText();
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");                 
         Statement st = (Statement) con.createStatement();
         ResultSet res = st.executeQuery("SELECT * FROM customer_info WHERE Account_Number ='"+accno+"'"); 
        if(accnumber.getText().equals(""))
        
        {
         JOptionPane.showMessageDialog(null, "Please input a valid account number");
        }
        else
        {
         if(res.next() )
         {

         accbal = res.getString("Deposit");
         accholder = res.getString("Customer_Name");

         //setting text
         cbal.setText(accbal);
         jButton1.setEnabled(true);
         } 
         else
         {
         JOptionPane.showMessageDialog(null, "Account Number not found");
         }
        
        }
        
}
    
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
}

}

public void ProcessWithdrawal()
{
    String slip_no = slipno.getText();
     try
        {
         try
{    
    
    int ch1 = Integer.parseInt(cbal.getText());
    int ch2 = Integer.parseInt(amount.getText());
    int remsum = ch1-ch2;
    rembal = ""+remsum;
    if(ch2 > ch1)
    {
    JOptionPane.showMessageDialog(null,"Insufficient Funds");
    }
    else
    {
conn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");      
String sql ="update customer_info set Deposit ='"+rembal+"' where Account_Number ='"+accno+"'";
pst= conn.prepareStatement(sql);
pst.execute();
updateinfo ="Withdrawal: ";

///update record

           try
{
        conn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");
        Statement st = (Statement) conn.createStatement();
        String insert = "INSERT INTO transaction_report (Slip_Number,Account_Holder,Account_Number,Previous_Balance,Current_Balance,Amount,Transaction_Type) VALUES ('" +slipno.getText()+ "','" +accholder+ "', '" +accno+ "', '" +cbal.getText()+ "', '" +rembal+ "', '" +amount.getText()+ "', '" +"Debit"+ "')";
        st.executeUpdate(insert);
        JOptionPane.showMessageDialog(null,updateinfo+"Transaction Successful");
        
        slipno.setText("");
        accnumber.setText("");
        cbal.setText("");
        amount.setText("");
      

}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
} 
 
///end of update
    }
    
    




}

catch(Exception e)
{

    JOptionPane.showMessageDialog(null, e);
}
           
            
        }
        catch(Exception e)
        {
        
        } 


}

public void ProcessDeposit()
{
try
{
         slip_no2 = slipnumber2.getText();
         accno2 = accnumber2.getText();
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");                 
         Statement st = (Statement) con.createStatement();
         ResultSet res = st.executeQuery("SELECT * FROM customer_info WHERE Account_Number ='"+accno2+"'"); 
        if(accnumber2.getText().equals("") || amount2.getText().equals("") )
        
        {
         JOptionPane.showMessageDialog(null, "Please input a valid account number");
        }
        else
        {
         if(res.next() )
         {
         accholder2 = res.getString("Customer_Name");

//insert into transaction report
                    try
{       
        conn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");
        Statement st2 = (Statement) conn.createStatement();
        String insert = "INSERT INTO transaction_report (Slip_Number,Account_Holder,Account_Number,Amount,Transaction_Type) VALUES ('" +slipnumber2.getText()+ "','" +accholder2+ "', '" +accno2+ "', '" +amount2.getText()+ "', '" +"Credit"+ "')";
        st2.executeUpdate(insert);
        
        
 try
{
    try
{
  
         
         Connection connn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");                 
         Statement st3 = (Statement) connn.createStatement();
         ResultSet res3 = st3.executeQuery("SELECT * FROM customer_info WHERE Account_Number ='"+accno2+"'"); 
        if(accnumber2.getText().equals(""))
        
        {
         JOptionPane.showMessageDialog(null, "Please input a valid account number");
        }
        else
        {
         if(res3.next() )
         {

         accbal5000 = res.getString("Deposit");

         //setting text
         cbal.setText(accbal);
         jButton1.setEnabled(true);
         } 
         else
         {
         JOptionPane.showMessageDialog(null, "Account Number not found");
         }
        
        }
        
}
    
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
}
    
   int a = Integer.parseInt(accbal5000);
   int b = Integer.parseInt(amount2.getText());
   int newbal = a+b;
   String newbals = ""+newbal;
   
conn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");      
String sql ="update customer_info set Deposit ='"+newbals+"' where Account_Number ='"+accno2+"'";
pst= conn.prepareStatement(sql);
pst.execute();
JOptionPane.showMessageDialog(null,"Deposit: Transaction Successful");
}

catch(Exception e)
{

    JOptionPane.showMessageDialog(null, e);
}
 
 
        slipnumber2.setText("");
        accnumber2.setText("");
        amount2.setText("");
      

}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
} 
         } 
         
        
        }
        
}
    
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
}

}

public void ProcessLoanPayment()
{
try
{
        conn = DriverManager.getConnection("jdbc:mysql://localhost/bsdb", "root","root");
        Statement st = (Statement) conn.createStatement();
        String insert = "INSERT INTO loanpayment VALUES ('" +pslipno.getText()+ "', '" +pno.getText()+ "', '" +pcname.getText()+ "', '" +pdoa.getText()+ "', '" +paa.getText()+ "', '" +ppol.getText()+ "', '" +prip.getText()+ "', '" +pi.getText()+ "')";
        st.executeUpdate(insert);
        JOptionPane.showMessageDialog(null,"Successfully submitted loan payment!");
        
        pslipno.setText("");
        pno.setText("");
        pcname.setText("");
        pdoa.setText("");
        paa.setText("");
        ppol.setText("");
        prip.setText("");
        pi.setText("");
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
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

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        slipno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        accnumber = new javax.swing.JTextField();
        cbal = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        slipnumber2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        accnumber2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        amount2 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        pslipno = new javax.swing.JTextField();
        pno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pcname = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pdoa = new javax.swing.JTextField();
        paa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ppol = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        prip = new javax.swing.JTextField();
        pi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaction Portal");

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/arrow527.png"))); // NOI18N
        jLabel25.setText("Back");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(253, 253, 253)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel2.setText("Slip Number");

        slipno.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel3.setText("Account Number:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel4.setText("Current Balance:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel5.setText("Amount:");

        accnumber.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        cbal.setEditable(false);
        cbal.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        amount.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jButton1.setText("Submit");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jButton4.setText("Load");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbal, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(amount)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slipno, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(accnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(310, 310, 310))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(slipno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(accnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Withdrawal", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel8.setText("Slip Number");

        slipnumber2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel9.setText("Account Number:");

        accnumber2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel10.setText("Deposit Amount:");

        amount2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(slipnumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(accnumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(amount2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jButton2)))
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(slipnumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(accnumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(amount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Deposit", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel14.setText("Slip Number");

        pslipno.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        pno.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel15.setText("Loan Number:");

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel16.setText("Customer Name:");

        pcname.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel17.setText("Date of Application:");

        pdoa.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        paa.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel18.setText("Actual Amount:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel19.setText("Period of Loan:");

        ppol.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel20.setText("Rate in Percentage:");

        prip.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        pi.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel21.setText("Interest:");

        jButton3.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pdoa, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(paa, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ppol, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pcname, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(prip, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pslipno, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pno, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(304, Short.MAX_VALUE))))
            .addComponent(jSeparator3)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(pslipno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(pno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(pcname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(pdoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(paa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(ppol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(prip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loan Payment", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        GetBalance();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ProcessWithdrawal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ProcessDeposit();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ProcessLoanPayment();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        AdminPortal ap = new AdminPortal();
        ap.setVisible(true);
    }//GEN-LAST:event_jLabel25MouseClicked

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
            java.util.logging.Logger.getLogger(TransactionPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionPortal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accnumber;
    private javax.swing.JTextField accnumber2;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField amount2;
    private javax.swing.JTextField cbal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField paa;
    private javax.swing.JTextField pcname;
    private javax.swing.JTextField pdoa;
    private javax.swing.JTextField pi;
    private javax.swing.JTextField pno;
    private javax.swing.JTextField ppol;
    private javax.swing.JTextField prip;
    private javax.swing.JTextField pslipno;
    private javax.swing.JTextField slipno;
    private javax.swing.JTextField slipnumber2;
    // End of variables declaration//GEN-END:variables
}
