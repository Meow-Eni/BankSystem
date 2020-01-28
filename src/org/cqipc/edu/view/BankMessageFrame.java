package org.cqipc.edu.view;

import java.awt.EventQueue;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cqipc.edu.bean.Bank;
import org.cqipc.edu.dao.BankDao;
import org.cqipc.edu.dao.Impl.BankDaoImpl;
import org.cqipc.edu.view.Tools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BankMessageFrame extends JInternalFrame {
	private JTable table;
	private JTextField bankName;
	private int id=0;
	private BankDao pd=new BankDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankMessageFrame frame = new BankMessageFrame();
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
	public BankMessageFrame() {
		setTitle("银行信息编辑");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 720, 480);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u94F6\u884C\u4FE1\u606F\u7F16\u8F91", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("银行名称：");
		
		bankName = new JTextField();
		bankName.setColumns(10);
		
		JButton btnNewButton = new JButton("提  交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifyFrame(arg0);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(139)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bankName, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(242)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(66)))
					.addGap(200))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bankName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickTable(arg0);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u94F6\u884C\u7F16\u53F7", "\u94F6\u884C\u540D\u79F0"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		initTable();
	}

	protected void modifyFrame(ActionEvent arg0) {
		String b_name=bankName.getText().toString();
		b_name=Tools.isEmpty(b_name);
		if(b_name!="") {
			Bank b=new Bank(id, b_name);
			if(pd.modifyBank(b)>0) {
				JOptionPane.showMessageDialog(this, "修改成功！");
				initTable();
			}else {
				JOptionPane.showMessageDialog(this, "系统未知错误！");
			}
		}else {
			JOptionPane.showMessageDialog(this, "不能有空值！");
		}
		
	}

	protected void clickTable(MouseEvent arg0) {
		if(arg0.getClickCount()==1){
			int row=((JTable)arg0.getSource()).rowAtPoint(arg0.getPoint());
			id=Integer.parseInt(table.getValueAt(row, 0).toString());
			bankName.setText(table.getValueAt(row, 1).toString());
		
		}
		
	}

	private void initTable() {
		List<Bank> list=pd.selectBankAll();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for(Bank b:list) {
			Vector v=new Vector();
			v.add(b.getB_id());
			v.add(b.getB_name());
			dtm.addRow(v);
		}
		
	}
}
