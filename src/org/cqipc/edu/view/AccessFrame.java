
package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.bean.Detail;
import org.cqipc.edu.bean.UserData;
import org.cqipc.edu.dao.AccountDao;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.DetailDao;
import org.cqipc.edu.dao.Impl.AccountDaoImpl;
import org.cqipc.edu.dao.Impl.CustomerDaoImpl;
import org.cqipc.edu.dao.Impl.DetailDaoImpl;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AccessFrame extends JInternalFrame {
	private JTextField textField;
	private JComboBox customerComboBox;
	private	JComboBox accountComboBox ;
	private JComboBox caozuoComboBox;
	private AccountDao ad=new AccountDaoImpl();
	private CustomerDao cm=new CustomerDaoImpl();
	private DetailDao dd=new DetailDaoImpl();
	private List<Account> accountList;
	private double balance;
	private JLabel balanceeLable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccessFrame frame = new AccessFrame();
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
	public AccessFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("存取款业务办理");
		setBounds(100, 100, 360, 480);
		
		JLabel lblNewLabel = new JLabel("用户");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("账户");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2 = new JLabel("操作");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label = new JLabel("金额");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		balanceeLable = new JLabel("");
		
		 customerComboBox = new JComboBox();
		 customerComboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String customerName=((UserData) customerComboBox.getSelectedItem()).getName();
		 		if (!customerName.equals("==请选择==")) {
		 			int c_id=cm.selectCustomerByname(customerName).get(0).getC_id();
		 			accountList=ad.selectAccountcid(c_id);
		 			String[] accountIds=Tools.getaccountIds(accountList);
		 			accountComboBox.setModel(new DefaultComboBoxModel(accountIds));
				}else {
					accountComboBox.setModel(new DefaultComboBoxModel(new String[] {"==请选择=="}));
				}
		 	}
		 });
		
		 
 		 accountComboBox = new JComboBox();
 		 accountComboBox.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent e) {
 		 		String accountId=(String) accountComboBox.getSelectedItem();
 		 		if (!accountId.equals("==请选择==")) {
 		 			balance=ad.selectAccountid(Integer.parseInt(accountId));
 	 		 		balanceeLable.setText("余额为："+balance);
				}else {
					return;
				}
 		 	}
 		 });
 		 accountComboBox.addItem("==请选择==");
		
		 caozuoComboBox = new JComboBox(new String[] {"==请选择==","存款","取款"});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton submitButton = new JButton("提交");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitButton();
			}
		});
		submitButton.setFont(new Font("宋体", Font.PLAIN, 18));
		
		
		balanceeLable.setHorizontalAlignment(SwingConstants.CENTER);
		balanceeLable.setFont(new Font("宋体", Font.BOLD, 18));
		balanceeLable.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(78)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(caozuoComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(accountComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(customerComboBox, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(submitButton)
											.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE))
										.addComponent(textField)))))
						.addComponent(balanceeLable, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(customerComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(accountComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(caozuoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(balanceeLable, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(submitButton)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		initcomboBox1() ;
		
	}


	protected void submitButton() {
		String type=customerComboBox.getSelectedItem().toString();
		String accountId=(String) accountComboBox.getSelectedItem();
		if (!type.equals("==请选择==")&&!accountId.equals("==请选择==")) {
			String typeClass=(String) caozuoComboBox.getSelectedItem();
			if (!typeClass.equals("==请选择==")) {
				boolean flag=Tools.isNumber(textField.getText());
				if (flag) {
					String a_balance=textField.getText().replace(" ", "");
					if (typeClass.equals("存款")) {
						balance+=Double.parseDouble(a_balance);
						if (ad.modifyBalance(balance,Integer.parseInt(accountId))>0) {
							JOptionPane.showMessageDialog(null, "存款成功");
							balanceeLable.setText("余额为："+balance);
						}else {
							JOptionPane.showMessageDialog(null, "存款失败,网络异常请稍后在尝试");
						}
					}else {
						balance-=Double.parseDouble(a_balance);
						if (balance>=0) {
							if (ad.modifyBalance(balance,Integer.parseInt(accountId))>0) {
								JOptionPane.showMessageDialog(null, "取款成功");
								balanceeLable.setText("余额为："+balance);
							}else {
								JOptionPane.showMessageDialog(null, "取款失败,网络异常请稍后在尝试");
							}
						}else {
							JOptionPane.showMessageDialog(null, "取款失败，余额不足");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "请输入正确的金额数");
				}
			}else {
				JOptionPane.showMessageDialog(null, "请先选择你的操作类型");
			}
		}else {
			JOptionPane.showMessageDialog(null, "先选着账户类型");
		}
		
		}
		
	

	private void initcomboBox1() {
		List<Customer> list=cm.selectCustomerAll();
		UserData da=new UserData(-1,"==请选择==");
		customerComboBox.addItem(da);
		for(Customer c:list) {
			UserData u=new UserData(c.getC_id(), c.getC_name());
			customerComboBox.addItem(u);
		}
	}
}
