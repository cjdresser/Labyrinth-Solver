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
 * The Class AddBrokerFrame is a pop-up frame which is created in order to facilitate broker creation.
 */
public class AddBrokerFrame {
	
	/** The trader list. */
	private TraderList traderList;
	
	/** The broker list table model. */
	private DefaultTableModel brokerListTableModel;
	
	/**
	 * Instantiates a new adds the broker frame.
	 *
	 * @param traderList the trader list
	 * @param brokerListTableModel the broker list table model
	 */
	public AddBrokerFrame(TraderList traderList, DefaultTableModel brokerListTableModel) {
		this.traderList = traderList;
		this.brokerListTableModel = brokerListTableModel;
		initialize();
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		final JFrame addBrokerFrame = new JFrame();
		
		JPanel contentPane;
		final JTextField nameTextBox;
		addBrokerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addBrokerFrame.setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		addBrokerFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("Broker Name: ");
		nameLabel.setBounds(55, 10, 89, 32);
		contentPane.add(nameLabel);
		
		nameTextBox = new JTextField();
		nameTextBox.setBounds(154, 17, 96, 19);
		contentPane.add(nameTextBox);
		nameTextBox.setColumns(10);
		
		JLabel StrategyLabel = new JLabel("Broker Strategy: ");
		StrategyLabel.setBounds(43, 64, 101, 13);
		contentPane.add(StrategyLabel);
		
		String[] strategyList = {"Strategy-A", "Strategy-B", "Strategy-C", "Strategy-D", "Strategy-E"};

		
		final JComboBox<String> strategyDropdown = new JComboBox<String>(strategyList);
		strategyDropdown.setBounds(154, 60, 96, 21);
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
		
		JButton submitButton = new JButton("Add Broker");
		
		submitButton.addActionListener(new ActionListener() {
			
			/**
			 * Adds a user defined broker when the "Add Broker" is button pressed.
			 * 
			 * @param e the button press
			 */
			public void actionPerformed(ActionEvent e) {
				TradingBroker newBroker;
				String name = nameTextBox.getText();
				String strategy = (String) strategyDropdown.getSelectedItem();
				List<String> coinList = list.getSelectedValuesList();
				if(!traderList.contains(name)) {
					newBroker = new TradingBroker(name, strategy, coinList);
					traderList.addBroker(newBroker);
					String[] newBrokerArr = {name, coinList.toString(), strategy};
					brokerListTableModel.addRow(newBrokerArr);

					addBrokerFrame.dispose();
				}
				else {
					int input = JOptionPane.showConfirmDialog(null, "The name \"" + name + "\" already exists. Would you like to enter another name?", 
							                                  "Broker Name Already Exists", JOptionPane.YES_NO_OPTION);
					if((input == 1)) {
						addBrokerFrame.dispose();
					}
				}
				
				
			}
		});
		
		submitButton.setBounds(96, 218, 122, 21);
		contentPane.add(submitButton);
		
		addBrokerFrame.setVisible(true);
		}
	}


