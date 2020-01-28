package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.bean.Customer;
import org.cqipc.edu.bean.UserData;
import org.cqipc.edu.dao.BankDao;
import org.cqipc.edu.dao.CustomerDao;
import org.cqipc.edu.dao.Impl.BankDaoImpl;
import org.cqipc.edu.dao.Impl.CustomerDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Checkbox;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerMessageFrame extends JInternalFrame {
	private JTable table;
	private JTextField userTextField;
	private JTextField telTextField;
	private JTextField addrTextField;
	private JComboBox bankComboBox;
	private JComboBox customerComboBox;
	private String oldName;
	private UserData u;
	private UserData b;
	private List<Customer> list;
	private CustomerDao cm=new CustomerDaoImpl();
	private BankDao cb=new BankDaoImpl();
	private int id=0;
	private JComboBox bankComboBox_1;

	/**
	 * Create the frame.
	 */
	public CustomerMessageFrame() {
		setTitle("用户信息管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 860, 520);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7528\u6237\u4FE1\u606F\u7F16\u8F91", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("银  行：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		bankComboBox = new JComboBox();
		bankComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				getUserData(arg0);
			}
		});

		
		JLabel label_3 = new JLabel("用  户：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		customerComboBox = new JComboBox();
		UserData dd=new UserData(-1,"==请选择==");
		customerComboBox.addItem(dd);
		JButton button_1 = new JButton("搜  索");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_name=((UserData) bankComboBox.getSelectedItem()).getName();
				int b_id=cb.selectBankById(b_name).get(0).getB_id();
				list=cm.selectCustomerBid(b_id);
				initTable(list);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bankComboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(customerComboBox, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(button_1)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bankComboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(customerComboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel label = new JLabel("姓名：");
		
		JLabel label_1 = new JLabel("电话：");
		
		JLabel lblNewLabel = new JLabel("地址：");
		
		JLabel label_2 = new JLabel("所属银行：");
		
		userTextField = new JTextField();
		userTextField.setColumns(10);
		
		telTextField = new JTextField();
		telTextField.setColumns(10);
		
		addrTextField = new JTextField();
		addrTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("修  改");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=userTextField.getText();
				String tel=telTextField.getText();
				String addr=addrTextField.getText();
				String b_name=b.getName();
				List<Bank> lsbanks=cb.selectBankById(b_name);
				int b_id=lsbanks.get(0).getB_id();
				int c_id=cm.selectCustomerByname(oldName).get(0).getC_id();
				
				if (cm.modifyCustomer(name, tel, addr, b_id, c_id)>0) {
					JOptionPane.showMessageDialog(null, "修改成功");
					initTable();
				}else {
					JOptionPane.showMessageDialog(null, "修改失败");
				}
			}
		});
		
		JButton button = new JButton("重  置");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userTextField.setText("");
				telTextField.setText("");
				addrTextField.setText("");
				bankComboBox_1.setSelectedIndex(0);
			}
		});
		bankComboBox_1 = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(83)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(addrTextField)
								.addComponent(userTextField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(177)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(37)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bankComboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(telTextField, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
							.addGap(95))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(133)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(telTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(label_2)
						.addComponent(addrTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bankComboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTable(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u7535\u8BDD", "\u5730\u5740", "\u6CE8\u518C\u65F6\u95F4", "\u6240\u5C5E\u94F6\u884C"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		initCustomerComboBox();
		initBankComboBox();
		initTable();
		initbankComboBox_1();
	}




	protected void getUserData(ItemEvent arg0) {
		if(arg0.getStateChange()==ItemEvent.SELECTED) {
			UserData b=(UserData)bankComboBox.getSelectedItem();
			if(b.getId()!=-1) {
				List<Customer> list=cm.selectCustomerBid(b.getId());
				customerComboBox.removeAllItems();
				UserData us=new UserData(-1,"==请选择==");
				customerComboBox.addItem(us);
				for(Customer c:list) {
					u=new UserData(c.getC_id(),c.getC_name());
					customerComboBox.addItem(u);
				}
			}
		}
		
	}

	private void initbankComboBox_1() {
		List<Bank> list=cb.selectBankAll();
		UserData da=new UserData(-1,"==请选择==");
		bankComboBox_1.addItem(da);
		for(Bank c:list) {
			UserData u=new UserData(c.getB_id(), c.getB_name());
			bankComboBox_1.addItem(u);
		}
		
	}

	protected void clickTable(MouseEvent e) {
		if(e.getClickCount()==1){
			int row=((JTable)e.getSource()).rowAtPoint(e.getPoint());
			id=Integer.parseInt(table.getValueAt(row, 0).toString());
			oldName=table.getValueAt(row, 1).toString();
			userTextField.setText(table.getValueAt(row, 1).toString());
			telTextField.setText(table.getValueAt(row, 2).toString());
			addrTextField.setText(table.getValueAt(row, 3).toString());
			
			String s=(table.getValueAt(row, 5).toString());		
			for(int i=0;i<bankComboBox_1.getItemCount();i++) {
				b=(UserData)bankComboBox_1.getItemAt(i);
				if(b.getName().equals(s)) {
					bankComboBox_1.setSelectedItem(b);
					break;
				}
			}
		}
	}

	private void initTable() {
		list=cm.selectCustomerAll();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Customer c:list) {
			Vector v=new Vector();
			v.add(c.getC_id());
			v.add(c.getC_name());
			v.add(c.getC_tel());
			v.add(c.getC_addr());
			v.add(c.getC_createdate());
			v.add(c.getB_id().getB_name());
			dtm.addRow(v);
		}
	}
	private void initTable(List<Customer> list) {
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Customer c:list) {
			Vector v=new Vector();
			v.add(c.getC_id());
			v.add(c.getC_name());
			v.add(c.getC_tel());
			v.add(c.getC_addr());
			v.add(c.getC_createdate());
			v.add(c.getB_id().getB_name());
			dtm.addRow(v);
		}
	}

	private void initBankComboBox() {
		List<Bank> list=cb.selectBankAll();
		UserData da=new UserData(-1,"==请选择==");
		bankComboBox.addItem(da);
		for(Bank c:list) {
			UserData u=new UserData(c.getB_id(), c.getB_name());
			bankComboBox.addItem(u);
		}
	}
	private void initCustomerComboBox() {
		List<Customer> list=cm.selectCustomerAll();
		for(Customer c:list) {
			UserData u=new UserData(c.getC_id(),c.getC_name());
			customerComboBox.addItem(u);
		}
	}
}
