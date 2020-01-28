package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.bean.UserData;
import org.cqipc.edu.dao.AccountDao;
import org.cqipc.edu.dao.BankDao;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.Impl.AccountDaoImpl;
import org.cqipc.edu.dao.Impl.BankDaoImpl;
import org.cqipc.edu.dao.Impl.CustomerDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountMessageFrame extends JInternalFrame {
	private JTable table;
	private JTextField textField;
	private List<Bank> bankdata;
	private List<Account> list;
	private List<Customer> list1;
	private  int accountSelectedRow=-1;
	private UserData b;
	private UserData u;
	private JComboBox bankComboBox;
	private JComboBox  accountComboBox;
	private JComboBox accountTypeComboBox;
	private int id=0;
	private AccountDao ad=new AccountDaoImpl();
	private CustomerDao cm=new CustomerDaoImpl();
	private BankDao cb=new BankDaoImpl();



	/**
	 * Create the frame.
	 */
	public AccountMessageFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("账户信息管理");
		setBounds(100, 100, 860, 520);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8D26\u6237\u4FE1\u606F\u7F16\u8F91", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("银  行:");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		
		bankdata=cb.selectBankAll();
		 bankComboBox = new JComboBox(Tools.getbankNames(bankdata));
		 bankComboBox.addItemListener(new ItemListener() {
		
			public void itemStateChanged(ItemEvent e) {
				getUserData(e);
				
			}
		});
		
		JLabel label_1 = new JLabel("用  户：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		 accountComboBox = new JComboBox();
		 accountComboBox.addItem("==请选择==");
		
		JButton btnNewButton = new JButton("搜  索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			searchMessage();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(66)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bankComboBox, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(accountComboBox, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(70)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bankComboBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(accountComboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(label))
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		
		JLabel label_2 = new JLabel("账户类型：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		 accountTypeComboBox = new JComboBox(new String[] {"==请选择==","储蓄账户","信用账户","定期账户","外汇账户"});
		
		JLabel label_3 = new JLabel("余额：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("修  改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type=(String) accountTypeComboBox.getSelectedItem();
				if (!type.equals("==请选择==")) {
					boolean flag=Tools.isBalance(textField.getText());
					if (flag) {
						Account account=list.get(accountSelectedRow);
						account.setA_type(type);
						account.setA_balance(Double.parseDouble(textField.getText().replace(" "," ")));
						if (ad.modifyAccount(account)>0) {
							JOptionPane.showMessageDialog(null,"修改成功");
							initTable(list);
						}else {
							JOptionPane.showMessageDialog(null,"修改失败");
						}
					}else {
						JOptionPane.showMessageDialog(null,"请输入正确的金额数");
					}
				}else {
					JOptionPane.showMessageDialog(null,"请先选择该用户的一种账户类型");
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton button = new JButton("\u91CD   \u7F6E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountTypeComboBox.setSelectedIndex(0);
				textField.setText("");
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(40)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(accountTypeComboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(171))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(198)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addGap(150)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addGap(116))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(accountTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(button))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8D26\u6237\u7F16\u53F7", "\u8D26\u6237\u7C7B\u578B", "\u4F59\u989D", "\u6240\u5C5E\u7528\u6237"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
	    initTable() ;
	    ListSelectionModel rowSelectionModel = table.getSelectionModel();
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//只处理鼠标释放
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				accountSelectedRow = lsm.getMinSelectionIndex();
				if (accountSelectedRow < 0) {
					return;
				}
				Account account=list.get(accountSelectedRow);
				accountTypeComboBox.setSelectedItem(account.getA_type());
				textField.setText(""+account.getA_balance());
				
			}
		});

	}


	protected void searchMessage() {
		String userName=(String) accountComboBox.getSelectedItem();
		if (!userName.equals("==请选择==")) {
			int c_id=cm.selectCustomerByname(userName).get(0).getC_id();
			list=ad.selectAccountcid(c_id);
			initTable(list);
		}else {
			JOptionPane.showMessageDialog(null, "请选择该银行下的一名用户");
		}
	}
		
		
	


	protected void getUserData(ItemEvent e) {
		String bankName=(String) bankComboBox.getSelectedItem();
		if (!bankName.equals("==请选择==")) {
			int b_id=cm.selectCustomerBank(bankName).getB_id();
			list1=cm.selectCustomerBid(b_id);
			accountComboBox.setModel(new DefaultComboBoxModel(Tools.getuserNames(list1)));
		}else {
			accountComboBox.setModel(new DefaultComboBoxModel(new String[] {"==请选择=="}));
		}
		
	}

	private void initBankComboBox() {
        List<Account>list=ad.AccountAll();
		UserData da=new UserData(-1,"==请选择==");
		bankComboBox.addItem(da);
		for(Account a:list) {
			UserData u=new UserData(a.getA_id(), a.getA_type());
			bankComboBox.addItem(u);
		}
	}
	
	protected void clickTable(MouseEvent e) {
		if(e.getClickCount()==1){
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			id=Integer.parseInt(table.getValueAt(row, 0).toString());
			textField.setText(table.getValueAt(row, 1).toString());
			String s=(table.getValueAt(row, 5).toString());		
			for(int i=0;i<accountTypeComboBox.getItemCount();i++) {
				b=(UserData)accountTypeComboBox.getItemAt(i);
				if(b.getName().equals(s)) {
					accountTypeComboBox.setSelectedItem(b);
					break;
				}
			}
		}
	}

	private void initTable(List<Account> list1) {
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Account a:list) {
			Vector v=new Vector();
			v.add(a.getA_id());
			v.add(a.getA_type());
			v.add(a.getA_balance());
			v.add(a.getCid().getC_name());
			dtm.addRow(v);
		}
	}
	private void initTable() {
		list=ad.AccountAll();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Account a:list) {
			Vector v=new Vector();
			v.add(a.getA_id());
			v.add(a.getA_type());
			v.add(a.getA_balance());
			v.add(a.getCid().getC_name());
			dtm.addRow(v);
		}
	}
}
