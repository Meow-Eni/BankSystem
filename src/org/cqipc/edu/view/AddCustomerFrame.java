package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.bean.UserData;
import org.cqipc.edu.dao.BankDao;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.Impl.BankDaoImpl;
import org.cqipc.edu.dao.Impl.CustomerDaoImpl;
import org.cqipc.edu.view.Tools;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCustomerFrame extends JInternalFrame {
	private JTextField userTextField;
	private JTextField telTextField;
	private JTextField addrTextField;
	private JComboBox bankComboBox;
	private BankDao cm=new BankDaoImpl();
	private CustomerDao cu=new CustomerDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomerFrame frame = new AddCustomerFrame();
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
	public AddCustomerFrame() {
		setTitle("添加用户");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 480, 360);
		
		JLabel label = new JLabel("姓  名：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("电  话：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("地  址：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("银  行：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		bankComboBox = new JComboBox();
		
		userTextField = new JTextField();
		userTextField.setColumns(10);
		
		telTextField = new JTextField();
		telTextField.setColumns(10);
		
		addrTextField = new JTextField();
		addrTextField.setColumns(10);
		
		JButton sumbitbutton = new JButton("提  交");
		sumbitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addFrame(arg0);
			}
		});
		sumbitbutton.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addrTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(telTextField))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(sumbitbutton)
								.addComponent(bankComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(telTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_2)
						.addComponent(addrTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3, Alignment.TRAILING)
						.addComponent(bankComboBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addComponent(sumbitbutton)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		initBankComboBox();
	}
	protected void addFrame(ActionEvent arg0) {
			String c_name=userTextField.getText().toString();
			c_name=Tools.isEmpty(c_name);
			String c_tel=telTextField.getText().toString();
			c_tel=Tools.isEmpty(c_tel);
			String c_addr=addrTextField.getText().toString();
			String c_createDate=Tools.getDates();
			c_addr=Tools.isEmpty(c_addr);
			UserData d=(UserData)bankComboBox.getSelectedItem();
			Bank id=(Bank)cu.selectCustomerBank(d.getName());
			if(c_name!="" && c_tel!="" && c_addr!="") {
				Customer cn=new Customer(c_name, c_tel, c_addr, c_createDate, id);
				if(cu.addCustomer(cn)>0) {
					JOptionPane.showMessageDialog(this, "添加成功！");
				}else {
					JOptionPane.showMessageDialog(this, "未知错误，请稍后再试！");
				}
			}else {
				JOptionPane.showMessageDialog(this, "不能有空值！");
			}
			
		
	}

	private void initBankComboBox() {
		List<Bank> list=cm.selectBankAll();
		UserData da=new UserData(-1,"==请选择==");
		bankComboBox.addItem(da);
		for(Bank c:list) {
			UserData u=new UserData(c.getB_id(), c.getB_name());
			bankComboBox.addItem(u);
		}
	}
}
