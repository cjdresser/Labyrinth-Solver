package gui;

import api.CG_API_DataFetcher;

import trading_analysis.TraderList;
import trading_analysis.TradeResult;
import trading_analysis.TradingBroker;
import trading_analysis.TradingStrategy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.json.simple.parser.ParseException;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.awt.event.ActionEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;

public class MainGUI {

	private JFrame frame;
	private JTable brokerListTable;
	private JTable tradeHistoryTable;
	TraderList traderList = new TraderList();
	CG_API_DataFetcher apiClient = new CG_API_DataFetcher();
	TradingStrategy tradeStrat = new TradingStrategy();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] columnNames = {"Trading Client", "Coin List", "Strategy Name"};
		
		final DefaultTableModel brokerListTableModel = new DefaultTableModel(0,0);
		brokerListTableModel.setColumnIdentifiers(columnNames);
		
		String[] resultList_Col = {"Trader", "Strategy", "CryptoCoin", "Action", "Quantity", "Price", "Date"};
		
		final DefaultTableModel tradeHistoryTableModel = new DefaultTableModel(0,0);
		tradeHistoryTableModel.setColumnIdentifiers(resultList_Col);
		
		frame = new JFrame();
		frame.setTitle("CryptoCoin Trader v1.0");
		frame.setBounds(100, 100, 1230, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel brokerListPanel = new JPanel();
		brokerListPanel.setBounds(682, 35, 524, 445);
		frame.getContentPane().add(brokerListPanel);
		brokerListPanel.setLayout(null);
		
		JScrollPane brokerListScrollPane = new JScrollPane();
		brokerListScrollPane.setBounds(0, 0, 524, 445);
		brokerListPanel.add(brokerListScrollPane);
		
		brokerListTable = new JTable();
		
		brokerListTable.setModel(brokerListTableModel);
		
		brokerListScrollPane.setViewportView(brokerListTable);
		brokerListTable.setFillsViewportHeight(true);
		
		JPanel tradeHistoryPanel = new JPanel();
		tradeHistoryPanel.setBounds(10, 35, 651, 261);
		frame.getContentPane().add(tradeHistoryPanel);
		tradeHistoryPanel.setLayout(null);
		
		JScrollPane tradeHistoryScrollPane = new JScrollPane();
		tradeHistoryScrollPane.setBounds(0, 0, 651, 261);
		tradeHistoryPanel.add(tradeHistoryScrollPane);
		
		tradeHistoryTable = new JTable();
		tradeHistoryTable.setModel(tradeHistoryTableModel);
		tradeHistoryTable.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tradeHistoryTable.setFillsViewportHeight(true);
		tradeHistoryScrollPane.setViewportView(tradeHistoryTable);
		
		JPanel histogramPanel = new JPanel();
		histogramPanel.setBounds(10, 306, 651, 239);
		frame.getContentPane().add(histogramPanel);
		histogramPanel.setLayout(new BorderLayout(0, 0));
		
		
		DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		final XYSeries seriesA = new XYSeries("Strategy-A", true, false);
		final XYSeries seriesB = new XYSeries("Strategy-B", true, false);
		final XYSeries seriesC = new XYSeries("Strategy-C", true, false);
		final XYSeries seriesD = new XYSeries("Strategy-D", true, false);
		final XYSeries seriesE = new XYSeries("Strategy-E", true, false);
		
		seriesA.add(10, 0);
	    seriesB.add(20, 0);
	    seriesC.add(30, 0);
	    seriesD.add(40, 0);
	    seriesE.add(50, 0);
	    
	    dataset.addSeries(seriesA);
	    dataset.addSeries(seriesB);
	    dataset.addSeries(seriesC);
	    dataset.addSeries(seriesD);
	    dataset.addSeries(seriesE);
		
		JFreeChart histogram = ChartFactory.createHistogram("Strategy Usage", "Strategy", "Uses", dataset);
		XYPlot plot = histogram.getXYPlot();
		
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.RED);
		renderer.setSeriesPaint(2, Color.GREEN);
		renderer.setSeriesPaint(3, Color.CYAN);
		renderer.setSeriesPaint(4, Color.MAGENTA);
		
		
		Axis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarksVisible(false);
		
		LegendItem legendA = new LegendItem("Strategy-A");
		legendA.setFillPaint(Color.BLUE);
		
		LegendItem legendB = new LegendItem("Strategy-B");
		legendB.setFillPaint(Color.RED);
		
		LegendItem legendC = new LegendItem("Strategy-C");
		legendC.setFillPaint(Color.GREEN);
		
		LegendItem legendD = new LegendItem("Strategy-D");
		legendD.setFillPaint(Color.CYAN);
		
		LegendItem legendE = new LegendItem("Strategy-E");
		legendE.setFillPaint(Color.MAGENTA);
		
		LegendItemCollection legend = new LegendItemCollection();
		
		legend.add(legendA);
		legend.add(legendB);
		legend.add(legendC);
		legend.add(legendD);
		legend.add(legendE);

		
		plot.setFixedLegendItems(legend);
		//histogram.
		ChartPanel chart = new ChartPanel(histogram);
		
		histogramPanel.add(chart, BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		
		
		
		JPanel rowButtonPanel = new JPanel();
		rowButtonPanel.setBounds(682, 490, 524, 55);
		frame.getContentPane().add(rowButtonPanel);
		rowButtonPanel.setLayout(null);
		
		JButton addRowButton = new JButton("Add Row");
		
		addRowButton.addActionListener(new ActionListener() {
			
			/**
			 * Adds a user defined broker when the "Add Broker" is button pressed.
			 * 
			 * @param e the button press
			 */
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AddBrokerFrame abf = new AddBrokerFrame(traderList, brokerListTableModel);
			}
		});

		addRowButton.setBounds(48, 10, 119, 35);
		rowButtonPanel.add(addRowButton);
		
		JButton removeRowButton = new JButton("Remove Row");
		removeRowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				while(brokerListTable.getSelectedRow() != -1) {
					int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected row?", "Delete Row", JOptionPane.YES_NO_OPTION);
					if(delete == 0) {
						String deleteBrokerName = (String)brokerListTable.getValueAt(brokerListTable.getSelectedRow(), 0); 
						brokerListTableModel.removeRow(brokerListTable.getSelectedRow());
						traderList.removeBroker(deleteBrokerName);
					}
					else {
						brokerListTable.getSelectionModel().clearSelection();
					}
				}
			}
		});
		removeRowButton.setBounds(199, 10, 119, 35);
		rowButtonPanel.add(removeRowButton);
		
		JButton editRowButton = new JButton("Edit Row");
		
		editRowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//basically just open the add row pop up, change data, update table and brokerList
				if(brokerListTable.getSelectedRow() != -1) {
					String editBrokerName = (String) brokerListTable.getValueAt(brokerListTable.getSelectedRow(), 0);
					String editStrategy = (String)brokerListTable.getValueAt(brokerListTable.getSelectedRow(), 2);
					@SuppressWarnings("unused")
					EditBrokerFrame ebf = new EditBrokerFrame(traderList, brokerListTableModel, editBrokerName, editStrategy, brokerListTable.getSelectedRow());
				}
				brokerListTable.getSelectionModel().clearSelection();
				
			}
		});
		editRowButton.setBounds(351, 10, 119, 35);
		rowButtonPanel.add(editRowButton);
		
		JPanel tradeButtonPanel = new JPanel();
		tradeButtonPanel.setBounds(10, 555, 1196, 52);
		frame.getContentPane().add(tradeButtonPanel);
		tradeButtonPanel.setLayout(null);
		
		JButton makeTradeButton = new JButton("Perform Trades");
		makeTradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, Double> priceMap = apiClient.makeRequestMap(traderList.compileCoinList());//make map with every coin name and price by getting prices through api
					TradeResult result = null;
					for(TradingBroker trader : traderList.getList()) {//for each trader in the traderList
						for(String coin : trader.getCoinList()) {//get the trader's coin list
							try {
								trader.coinMap.put(coin, priceMap.get(coin));//and get the price of each coin in which the trader has expressed interest and put it in the trader's
																			// map of coin names and prices
								
							}
							catch(Exception error) {
								error.printStackTrace();
							}
						}
						try {
							result = tradeStrat.makeTrade(trader.getTradingStrategy(), trader.getName(), trader.coinMap);//then attempts the strategy of that broker
						}catch(Exception e1) {//if the trader does not have a strategy-required coin in their list, display and error message and add a NULL trade result to the table
							JOptionPane.showMessageDialog(null, "Error: " + trader.getName() + " does not include coin(s) neccessary for their trading strategy.", "Error", JOptionPane.ERROR_MESSAGE);
							String[] arr = {trader.getName(), trader.getTradingStrategy(), "NULL", "FAIL", "NULL", "NULL", LocalDate.now().toString()};
							tradeHistoryTableModel.addRow(arr);
						}
						if(result != null) {//if the trade went through, add it to the table
							tradeHistoryTableModel.addRow(result.getResult());
							result = null;//reset so it doesn't automatically go with whatever the previous result was when a trade fails
						}
					}
					
					//update the histogram 
					seriesA.update(10, tradeStrat.getACount());
					seriesB.update(20, tradeStrat.getBCount());
					seriesC.update(30, tradeStrat.getCCount());
					seriesD.update(40, tradeStrat.getDCount());
					seriesE.update(50,  tradeStrat.getECount());
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		makeTradeButton.setBounds(542, 0, 133, 32);
		tradeButtonPanel.add(makeTradeButton);
		
		JLabel traderActionsLabel = new JLabel("Trader Actions");
		traderActionsLabel.setBounds(289, 10, 141, 26);
		frame.getContentPane().add(traderActionsLabel);
		
		JLabel tradingClientListLabel = new JLabel("Trading Clients");
		tradingClientListLabel.setBounds(909, 14, 120, 19);
		frame.getContentPane().add(tradingClientListLabel);
		
		
	}
}