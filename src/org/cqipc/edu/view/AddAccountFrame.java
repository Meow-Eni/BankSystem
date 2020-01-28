package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddAccountFrame extends JInternalFrame {
	private JTable table;
	private JTextField textField;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private AccountDao ad=new AccountDaoImpl();
	private CustomerDao cm=new CustomerDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAccountFrame frame = new AddAccountFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAccountFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("添加账户");
		setBounds(100, 100, 360, 480);
		
		JLabel label = new JLabel("账户类型");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("账户余额");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("关联账户");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		 
		 comboBox = new JComboBox(new String[] {"==请选择==","储蓄账户","信用账户","定期账户","外汇账户"});
		 
		 comboBox_1 = new JComboBox();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAccount(e);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(152)
							.addComponent(button)))
					.addContainerGap(124, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, Alignment.TRAILING)
						.addComponent(comboBox_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addComponent(button)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		initcomboBox1();

	}

	protected void AddAccount(ActionEvent e) {
		String type=(String)comboBox.getSelectedItem();
		if (!type.equals("==请选择==")) {
			String a_balance=textField.getText();
			a_balance=Tools.isEmpty(a_balance);
			UserData d1=(UserData)comboBox_1.getSelectedItem();
			Customer id=cm.selectCustomerByname(d1.getName()).get(0);
			if(a_balance!="") {
				double balance=Double.parseDouble(a_balance);
				Account ac=new Account(type,balance,id);
				if(ad.addAccount(ac)>0) {
					JOptionPane.showMessageDialog(this, "添加成功！");
				}else {
					JOptionPane.showMessageDialog(this, "未知错误，请稍后再试！");
				}
			}else {
				JOptionPane.showMessageDialog(this, "不能有空值！");
			}
		}else {
			JOptionPane.showMessageDialog(null, "先选着账户类型");
		}
		
		}
		
	private void initcomboBox1() {
		List<Customer> list=cm.selectCustomerAll();
		UserData da=new UserData(-1,"==请选择==");
		comboBox_1.addItem(da);
		for(Customer c:list) {
			UserData u=new UserData(c.getC_id(), c.getC_name());
			comboBox_1.addItem(u);
		}
	}
}
