package org.cqipc.edu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.UIManager;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private int id=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setBackground(UIManager.getColor("Button.disabledForeground"));
		setTitle("\u64CD\u4F5C\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF\u64CD\u4F5C");
		menu.setForeground(Color.DARK_GRAY);
		menu.setFont(new Font("Ó×Ô²", Font.PLAIN, 24));
		menu.setIcon(new ImageIcon(mainFrame.class.getResource("/org/cqipc/edu/image/\u91D1\u878D-91.png")));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA");
		menuItem.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				breakFrame(arg0);	
			}
		});
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("\u94F6\u884C\u7BA1\u7406");
		menu_1.setForeground(Color.DARK_GRAY);
		menu_1.setIcon(new ImageIcon(mainFrame.class.getResource("/org/cqipc/edu/image/\u91D1\u878D \u94F6\u884C.png")));
		menu_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 24));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u6DFB\u52A0\u94F6\u884C");
		menuItem_1.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBankFrame(e);
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u94F6\u884C\u4FE1\u606F\u7BA1\u7406");
		menuItem_2.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bankMessageFrame(e);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("\u7528\u6237\u7BA1\u7406");
		menu_2.setForeground(Color.DARK_GRAY);
		menu_2.setIcon(new ImageIcon(mainFrame.class.getResource("/org/cqipc/edu/image/\u91D1\u878D-57.png")));
		menu_2.setFont(new Font("Ó×Ô²", Font.PLAIN, 24));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
		menuItem_3.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomerFrame(arg0);
			}
		});
		menu_2.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		menuItem_4.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerMessageFrame(e);
			}
		});
		menu_2.add(menuItem_4);
		
		JMenu menu_3 = new JMenu("\u8D26\u6237\u7BA1\u7406");
		menu_3.setForeground(Color.DARK_GRAY);
		menu_3.setIcon(new ImageIcon(mainFrame.class.getResource("/org/cqipc/edu/image/\u91D1\u878D\u8D27\u5E01.png")));
		menu_3.setFont(new Font("Ó×Ô²", Font.PLAIN, 24));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_5 = new JMenuItem("\u6DFB\u52A0\u8D26\u6237");
		menuItem_5.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAccountFrame(e);
			}
		});
		menu_3.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("\u8D26\u6237\u4FE1\u606F\u7BA1\u7406");
		menuItem_6.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountMessageFrame(e);
			}
		});
		menu_3.add(menuItem_6);
		
		JMenu menu_4 = new JMenu("\u4E1A\u52A1\u529E\u7406");
		menu_4.setForeground(Color.DARK_GRAY);
		menu_4.setIcon(new ImageIcon(mainFrame.class.getResource("/org/cqipc/edu/image/\u91D1\u878D\u6587\u5E93.png")));
		menu_4.setFont(new Font("Ó×Ô²", Font.PLAIN, 24));
		menuBar.add(menu_4);
		
		JMenuItem menuItem_7 = new JMenuItem("\u5B58\u53D6\u6B3E\u4E1A\u52A1\u529E\u7406");
		menuItem_7.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGetFrame(e);
			}
		});
		menu_4.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("\u6C47\u6B3E\u4E1A\u52A1\u529E\u7406");
		menuItem_8.setFont(new Font("ºÚÌå", Font.PLAIN, 18));
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huiKuanFrame(e);
			}
		});
		menu_4.add(menuItem_8);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(UIManager.getColor("Button.disabledForeground"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
		);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 872, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 526, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);
		contentPane.setLayout(gl_contentPane);
	}

	protected void breakFrame(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(this, "È·¶¨ÒªÍË³öÂð£¿")==
						JOptionPane.OK_OPTION)
					System.exit(0);
	}

	protected void saveGetFrame(ActionEvent e) {
		AccessFrame t=new AccessFrame();
		t.setVisible(true);
		desktopPane.add(t);
	}

	protected void huiKuanFrame(ActionEvent e) {
		RemitFrame t=new RemitFrame();
		t.setVisible(true);
		desktopPane.add(t);
	}

	protected void accountMessageFrame(ActionEvent e) {
		AccountMessageFrame t=new AccountMessageFrame();
		t.setVisible(true);
		desktopPane.add(t);
		
	}

	protected void customerMessageFrame(ActionEvent e) {
		CustomerMessageFrame t=new CustomerMessageFrame();
		t.setVisible(true);
		desktopPane.add(t);
		
	}

	protected void bankMessageFrame(ActionEvent e) {
		BankMessageFrame t=new BankMessageFrame();
		t.setVisible(true);
		desktopPane.add(t);
	}

	protected void addCustomerFrame(ActionEvent arg0) {
		AddCustomerFrame t=new AddCustomerFrame();
		t.setVisible(true);
		desktopPane.add(t);
		
	}

	protected void addAccountFrame(ActionEvent arg0) {
		AddAccountFrame t=new AddAccountFrame();
		t.setVisible(true);
		desktopPane.add(t);
		
	}

	protected void addBankFrame(ActionEvent e) {
		AddBankFrame t=new AddBankFrame();
		t.setVisible(true);
		desktopPane.add(t);
	}
}
