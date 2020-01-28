package org.cqipc.edu.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.cqipc.edu.bean.Account;
import org.cqipc.edu.bean.UserData;
import org.cqipc.edu.dao.AccountDao;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.DetailDao;
import org.cqipc.edu.dao.Impl.AccountDaoImpl;
import org.cqipc.edu.dao.Impl.CustomerDaoImpl;
import org.cqipc.edu.dao.Impl.DetailDaoImpl;

import java.awt.Color;

public class RemitFrame extends JInternalFrame {
	private JTextField moneyText;
	private AccountDao ad=new AccountDaoImpl();
	private CustomerDao cm=new CustomerDaoImpl();
	private DetailDao dd=new DetailDaoImpl();
	private List<Account> accountList;
	private double repositBalance;
	private double getMoneyBalance;
	private JLabel repositBalanceeLable;
	private JLabel getMoneyBalanceeLable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemitFrame frame = new RemitFrame();
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
	public RemitFrame() {
		setTitle("���ҵ��");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 720, 480);
		
		JLabel label = new JLabel("����û�");
		label.setFont(new Font("����", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("����˻�");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		
		JComboBox repositIdComboBox = new JComboBox();
		repositIdComboBox.addItem("==��ѡ��==");
		repositIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountId=(String) repositIdComboBox.getSelectedItem();
 		 		if (!accountId.equals("==��ѡ��==")) {
 		 			repositBalance=ad.selectAccountid(Integer.parseInt(accountId));
 	 		 		repositBalanceeLable.setText("���Ϊ��"+repositBalance);
				}else {
					return;
				}
			}
		});
		
		String[] repositNames=Tools.getuserNames(cm.selectCustomerAll());
		JComboBox repositNameComboBox = new JComboBox(repositNames);
		repositNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName=(String) repositNameComboBox.getSelectedItem();
		 		if (!customerName.equals("==��ѡ��==")) {
		 			int c_id=cm.selectCustomerByname(customerName).get(0).getC_id();
		 			accountList=ad.selectAccountcid(c_id);
		 			String[] accountIds=Tools.getaccountIds(accountList);
		 			repositIdComboBox.setModel(new DefaultComboBoxModel(accountIds));
				}else {
					repositIdComboBox.setModel(new DefaultComboBoxModel(new String[] {"==��ѡ��=="}));
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("�տ��û�");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 18));
		
		
		JComboBox getMoneyIdComboBox = new JComboBox();
		getMoneyIdComboBox.addItem("==��ѡ��==");
		getMoneyIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountId=(String) getMoneyIdComboBox.getSelectedItem();
 		 		if (!accountId.equals("==��ѡ��==")) {
 		 		getMoneyBalance=ad.selectAccountid(Integer.parseInt(accountId));
 		 		getMoneyBalanceeLable.setText("���Ϊ��"+getMoneyBalance);
				}else {
					return;
				}
			}
		});
		
		
		String[] getMoneyNames=Tools.getuserNames(cm.selectCustomerAll());
		JComboBox getMoneyNameComboBox = new JComboBox(getMoneyNames);
		getMoneyNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName=(String) getMoneyNameComboBox.getSelectedItem();
		 		if (!customerName.equals("==��ѡ��==")) {
		 			int c_id=cm.selectCustomerByname(customerName).get(0).getC_id();
		 			accountList=ad.selectAccountcid(c_id);
		 			String[] accountIds=Tools.getaccountIds(accountList);
		 			getMoneyIdComboBox.setModel(new DefaultComboBoxModel(accountIds));
				}else {
					getMoneyIdComboBox.setModel(new DefaultComboBoxModel(new String[] {"==��ѡ��=="}));
				}
			}
		});
		
		JLabel label_2 = new JLabel("�տ��˻�");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		
		
		
		JButton repositButton = new JButton("���");
		repositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=(String) repositNameComboBox.getSelectedItem();
			if (!name.equals("==��ѡ��==")) {
				String aId=(String) repositIdComboBox.getSelectedItem();
				if (!aId.equals("-��ѡ��-")) {
					String acceptName=(String) repositNameComboBox.getSelectedItem();
					if (!acceptName.equals("-��ѡ��-")) {
						String acceptaId=(String) repositIdComboBox.getSelectedItem();
						if (!acceptaId.equals("-��ѡ��-")) {
							boolean flag=Tools.isNumber(moneyText.getText());
							 if (flag) {
								String money=moneyText.getText().replace(" ", "");
								double result=getMoneyBalance-Double.parseDouble(money);
								if (result>=0) {
									double number=Double.parseDouble(money);
									if (ad.remit(repositBalance, getMoneyBalance,number,Integer.parseInt(aId),Integer.parseInt(acceptaId))) {
										JOptionPane.showMessageDialog(null, "���ɹ�");
										moneyText.setText("�˻����Ϊ��"+(getMoneyBalance-number));
										repositBalanceeLable.setText("�˻����Ϊ��"+(repositBalance+number));
										moneyText.setText("");
										accountList=ad.AccountAll();
										String[] accountIds=Tools.getaccountIds(accountList);
							 			getMoneyIdComboBox.setModel(new DefaultComboBoxModel(accountIds));
									}else {
										JOptionPane.showMessageDialog(null, "���ʧ��");
									}
								}else {
									JOptionPane.showMessageDialog(null, "���ʧ�ܣ�����û�����");
								}
							}else {
								JOptionPane.showMessageDialog(null, "������������������������ֻ���Ϊ��");
							}
						}else {
							JOptionPane.showMessageDialog(null, "��ѡ����տ��û���һ�����п�");
						}
					}else {
						JOptionPane.showMessageDialog(null, "��ѡ��һ���տ��û�");
					}
				}else {
					JOptionPane.showMessageDialog(null, "��ѡ��û���û���һ�����п�");
				}
			}else {
				JOptionPane.showMessageDialog(null, "����ѡ��һ������û�");
			}
				
			}
		});
		repositButton.setFont(new Font("����", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("�����");
		label_3.setFont(new Font("����", Font.PLAIN, 18));
		
		moneyText = new JTextField();
		moneyText.setColumns(10);
		
		repositBalanceeLable= new JLabel("");
		repositBalanceeLable.setFont(new Font("����", Font.BOLD, 18));
		repositBalanceeLable.setForeground(Color.RED);
		
		getMoneyBalanceeLable= new JLabel("");
		getMoneyBalanceeLable.setForeground(Color.RED);
		getMoneyBalanceeLable.setFont(new Font("����", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(repositBalanceeLable, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addComponent(repositNameComboBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(getMoneyBalanceeLable, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addComponent(getMoneyNameComboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_3)
							.addGap(18)
							.addComponent(moneyText, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(getMoneyIdComboBox, 0, 156, Short.MAX_VALUE)
						.addComponent(repositIdComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(84))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(265)
					.addComponent(repositButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(347))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(repositNameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(repositIdComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(repositBalanceeLable, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(getMoneyNameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_2)
							.addComponent(getMoneyIdComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(getMoneyBalanceeLable, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(moneyText, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(repositButton)
					.addGap(44))
		);
		getContentPane().setLayout(groupLayout);

	}
}
