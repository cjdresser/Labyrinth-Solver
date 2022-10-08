package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import trading_analysis.TradingBroker;
import trading_analysis.TraderList;


/**
 * The Class EditBrokerFrame is a pop-up frame which facilitates the editing of a pre-existing trading broker.
 */
public class EditBrokerFrame {
	
	/** The trader list. */
	private TraderList traderList;
	
	/** The broker list table model. */
	private DefaultTableModel brokerListTableModel;
	
	/** The old broker name. */
	private String editBrokerName;
	
	/** The old trading strategy. */
	private String editStrategy;
	
	/** The new broker array. */
	private String[] newBrokerArr;
	
	/** The selected row. */
	private int selectedRow;
	 
	
	/**
	 * Instantiates a new edits the broker frame.
	 *
	 * @param traderList the trader list
	 * @param brokerListTableModel the broker list table model
	 * @param editBrokerName the old broker name
	 * @param editStrategy the old trading strategy
	 * @param selectedRow the selected row
	 */
	public EditBrokerFrame(TraderList traderList, DefaultTableModel brokerListTableModel, 
						   String editBrokerName, String editStrategy, int selectedRow) {
		this.traderList = traderList;
		this.brokerListTableModel = brokerListTableModel;
		this.editBrokerName = editBrokerName;
		this.editStrategy = editStrategy;
		this.selectedRow = selectedRow;
		initialize();
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		final JFrame editBrokerFrame = new JFrame();
		
		JPanel contentPane;
		final JTextField nameTextBox;
		
		editBrokerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editBrokerFrame.setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editBrokerFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("Broker Name: ");
		nameLabel.setBounds(55, 10, 89, 32);
		contentPane.add(nameLabel);
		
		nameTextBox = new JTextField();
		nameTextBox.setBounds(154, 17, 96, 19);
		nameTextBox.setText(editBrokerName);
		contentPane.add(nameTextBox);
		nameTextBox.setColumns(10);
		
		JLabel StrategyLabel = new JLabel("Broker Strategy: ");
		StrategyLabel.setBounds(43, 64, 101, 13);
		contentPane.add(StrategyLabel);
		
		String[] strategyList = {"Strategy-A", "Strategy-B", "Strategy-C", "Strategy-D", "Strategy-E"};

		
		final JComboBox<String> strategyDropdown = new JComboBox<String>(strategyList);
		strategyDropdown.setBounds(154, 60, 96, 21);
		strategyDropdown.setSelectedItem(editStrategy);
		contentPane.add(strategyDropdown);
		
		JLabel coinListLabel = new JLabel("Enter Coins: ");
		coinListLabel.setBounds(66, 118, 71, 13);
		contentPane.add(coinListLabel);
		

		
		
		
		JScrollPane coinListScrollPane = new JScrollPane();
		coinListScrollPane.setBounds(154, 118, 96, 70);
		contentPane.add(coinListScrollPane);
		//"bitcoin", "ethereum", "cardano", "tether", "heco-peg-bnb", "heco-peg-xrp", "terra-luna", "solana", "avalanche-2", "dogecoin"
		String[] coins = {"BTC", "ETH", "ADA", "USDT", "BNB", "XRP", "LUNA", "SOL", "AVAX", "DOGE"};
		
		
		final JList<String> list = new JList<String>(coins);
	
		coinListScrollPane.setViewportView(list);
		
		JButton submitButton = new JButton("Edit Broker");
		
		submitButton.addActionListener(new ActionListener() {
			
			/**
			 * Edits a user defined broker when the "Edit Broker" is button pressed.
			 * 
			 * @param e the button press
			 */
			public void actionPerformed(ActionEvent e) {
				TradingBroker newBroker;
				String name = nameTextBox.getText();
				String strategy = (String) strategyDropdown.getSelectedItem();
				List<String> coinList = list.getSelectedValuesList();
				if((name.equals(editBrokerName)) || !traderList.contains(name)) {
					newBroker = new TradingBroker(name, strategy, coinList);
					traderList.editBroker(editBrokerName, newBroker);
					String[] newBrokerArrTemp = {name, coinList.toString(), strategy};
					newBrokerArr = newBrokerArrTemp;
					brokerListTableModel.setValueAt(name, selectedRow, 0);
					brokerListTableModel.setValueAt(coinList.toString(), selectedRow, 1);
					brokerListTableModel.setValueAt(strategy, selectedRow, 2);
					editBrokerFrame.dispose();
				}
				else {
					int input = JOptionPane.showConfirmDialog(null, "The name \"" + name + "\" already exists. Would you like to enter another name?", 
							                                  "Broker Name Already Exists", JOptionPane.YES_NO_OPTION);
					if((input == 1)) {
						editBrokerFrame.dispose();
					}
				}
				
				
			}
		});
		
		submitButton.setBounds(96, 218, 122, 21);
		contentPane.add(submitButton);
		
		editBrokerFrame.setVisible(true);
		}
	
	/**
	 * Gets the new broker arrary.
	 *
	 * @return the new broker array
	 */
	public String[] getNewBrokerArr() {
		return newBrokerArr;
	}
	}