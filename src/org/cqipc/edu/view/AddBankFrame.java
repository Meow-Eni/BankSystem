package org.cqipc.edu.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.dao.BankDao;
import org.cqipc.edu.dao.Impl.BankDaoImpl;
import org.cqipc.edu.view.Tools;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBankFrame extends JInternalFrame {
	private JTextField bankName;
	private BankDao bd=new BankDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBankFrame frame = new AddBankFrame();
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
	public AddBankFrame() {
		setTitle("�������");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 480, 360);
		
		JLabel label = new JLabel("�������ƣ�");
		label.setFont(new Font("����", Font.PLAIN, 18));
		
		JButton button = new JButton("��  ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addFrame(arg0);
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 18));
		
		bankName = new JTextField();
		bankName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bankName, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(89)))
					.addGap(74))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bankName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(81)
					.addComponent(button)
					.addGap(60))
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void addFrame(ActionEvent arg0) {
		String b_name=bankName.getText().toString();
		b_name=Tools.isEmpty(b_name);
		if(b_name!=""){
			Bank b=new Bank(b_name);
			if(!Tools.isNumber(b_name)){
				if(bd.selectBankById(b_name)!=null){
					if(bd.addBank(b)>0){
						JOptionPane.showMessageDialog(this,"��ӳɹ���");
					}else{
						JOptionPane.showMessageDialog(this,"δ֪����");
					}
				}else{
					JOptionPane.showMessageDialog(this,"�����ظ���");
				}	
			}else{
				JOptionPane.showMessageDialog(this, "���������֣�");
			}
			
				
		}else{
			JOptionPane.showMessageDialog(this, "���ֲ���Ϊ�գ�");
		}
		
		
	}
}
