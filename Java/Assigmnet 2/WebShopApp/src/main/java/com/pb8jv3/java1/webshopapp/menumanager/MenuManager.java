package com.pb8jv3.java1.webshopapp.menumanager;

import com.pb8jv3.java1.webshopapp.datamanager.DataManager;
import com.pb8jv3.java1.webshopapp.datamanager.data.DisplaySize;
import com.pb8jv3.java1.webshopapp.datamanager.data.DisplayType;
import com.pb8jv3.java1.webshopapp.datamanager.data.Monitor;
import com.pb8jv3.java1.webshopapp.filemanager.WriteToFile;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class MenuManager extends javax.swing.JFrame {
    private DataManager dataManager;
    
    private final DefaultTableModel productTableModel, productInCartTableModel, productInWishlistTableModel;
    
    /**
     * Creates new form MainPanel
     * @param dataManager
     */
    public MenuManager(DataManager dataManager) {
	this.dataManager = dataManager;
	
	initComponents();
	
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent event) {
		exitProcedure();
	    }
	});
	
	grandTotalTextField.setText("0");
	
	productTableModel = (DefaultTableModel)productTable.getModel();
	productInCartTableModel = (DefaultTableModel)productInCartTable.getModel();
	productInWishlistTableModel = (DefaultTableModel)productInWishlistTable.getModel();
	
	loadData();
	
	this.setVisible(true);
    }
    
    private void exitProcedure(){
	
	Map<Integer, Monitor> products = new LinkedHashMap<>();
	Map<Integer, Monitor> productsInCart = new LinkedHashMap<>();
	Map<Integer, Monitor> productsInWishlist = new LinkedHashMap<>();
	
	
	for(int i = 0; i < productTable.getRowCount(); i++){
	    String[] size = productTable.getValueAt(i, 4).toString().split("x");
	    products.put(i, new Monitor(productTable.getValueAt(i, 0).toString(),
					productTable.getValueAt(i, 1).toString(),
					productTable.getValueAt(i, 3).toString(),
					new DisplaySize(Integer.parseInt(size[0]), Integer.parseInt(size[1])),
					Integer.parseInt(productTable.getValueAt(i, 6).toString()),
					DisplayType.valueOf(productTable.getValueAt(i, 2).toString()),
					Integer.parseInt(productTable.getValueAt(i, 5).toString()),
					Integer.parseInt(productTable.getValueAt(i, 7).toString())));
	}
	
	for(int i = 0; i < productInCartTable.getRowCount(); i++){
	    for (int ix = 0; ix < productTable.getRowCount(); ix++) {
                if (productTable.getValueAt(ix, 0).toString().concat(productTable.getValueAt(ix, 1).toString()).equalsIgnoreCase(productInCartTable.getValueAt(i, 0).toString().concat(productInCartTable.getValueAt(i, 1).toString()))) {
                    String[] size = productTable.getValueAt(ix, 4).toString().split("x");
		    productsInCart.put(ix, new Monitor(productTable.getValueAt(ix, 0).toString(),
					productTable.getValueAt(ix, 1).toString(),
					productTable.getValueAt(ix, 3).toString(),
					new DisplaySize(Integer.parseInt(size[0]), Integer.parseInt(size[1])),
					Integer.parseInt(productTable.getValueAt(ix, 6).toString()),
					DisplayType.valueOf(productTable.getValueAt(ix, 2).toString()),
					Integer.parseInt(productTable.getValueAt(ix, 5).toString()),
					Integer.parseInt(productInCartTable.getValueAt(i, 3).toString())));
		    break;
                }
	    } 
	}
	for(int i = 0; i < productInWishlistTable.getRowCount(); i++){
	    for (int ix = 0; ix < productTable.getRowCount(); ix++) {
                if (productTable.getValueAt(ix, 0).toString().concat(productTable.getValueAt(ix, 1).toString()).equalsIgnoreCase(productInWishlistTable.getValueAt(i, 0).toString().concat(productInWishlistTable.getValueAt(i, 1).toString()))) {
                    String[] size = productTable.getValueAt(ix, 4).toString().split("x");
		    productsInWishlist.put(ix, new Monitor(productTable.getValueAt(ix, 0).toString(),
					productTable.getValueAt(ix, 1).toString(),
					productTable.getValueAt(ix, 3).toString(),
					new DisplaySize(Integer.parseInt(size[0]), Integer.parseInt(size[1])),
					Integer.parseInt(productTable.getValueAt(ix, 6).toString()),
					DisplayType.valueOf(productTable.getValueAt(ix, 2).toString()),
					Integer.parseInt(productTable.getValueAt(ix, 5).toString()),
					Integer.parseInt(productTable.getValueAt(ix, 7).toString())));
		    break;
                }
	    }
	}
	
	dataManager.productManager.products = products;
	dataManager.cartManager.products = productsInCart;
	dataManager.wishlistManager.products = productsInWishlist;
	
	WriteToFile.write(dataManager);
	
	this.dispose();
	System.exit(0);
    }
    
    public int grandTotalUpdater(){
	int grandTotal = 0;
        for (int i = 0; i < productInCartTable.getRowCount(); i++) {
            grandTotal += (Integer.parseInt(productInCartTable.getValueAt(i, 2).toString())*Integer.parseInt(productInCartTable.getValueAt(i, 3).toString()));
        }         
        grandTotalTextField.setText(String.valueOf(grandTotal) + " $");
        return grandTotal;
    }
    
    private void loadData(){
	dataManager.getProductManager().getProducts().forEach((ID, product) ->
	    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()}));
	dataManager.getCartManager().getProducts().forEach((ID, product) ->
	    productInCartTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getPrice(),
							product.getInStock()}));
	dataManager.getWishlistManager().getProducts().forEach((ID, product) ->
	    productInWishlistTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getPrice()}));
    }
    
    private void searchByTypeUpdater(){
        String type = (String)searchByTypeComboBox.getSelectedItem();
	
	if(searchByTypeTextField.getText().isEmpty()){
	    dataManager.getProductManager().getProducts().forEach((ID, product) ->
	    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()}));
	}else{
	    switch(type){
		case "Manufacturer":
		    productTableModel.setRowCount(0);                    
		    dataManager.getProductManager().getProducts().forEach((id , product) -> {
			if(product.getManufacturer().toLowerCase().contains(searchByTypeTextField.getText().toLowerCase())){
			    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()});
                        }
                    });
		    break;
		case "Model Number":
		    productTableModel.setRowCount(0);                    
		    dataManager.getProductManager().getProducts().forEach((id, product) -> {
			if(product.getModelNumber().toLowerCase().contains(searchByTypeTextField.getText().toLowerCase())){
			    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()});
				}
			    });
		    break;
		case "Type":
		    productTableModel.setRowCount(0);                    
		    dataManager.getProductManager().getProducts().forEach((id, product) -> {
			if(product.getType().toString().toLowerCase().contains(searchByTypeTextField.getText().toLowerCase())){
			    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()});
				}
			    });
		    break;
		case "Price Greater":
		    productTableModel.setRowCount(0);                    
		    dataManager.getProductManager().getProducts().forEach((id, product) -> {
			if(product.getPrice() > Integer.parseInt(searchByTypeTextField.getText())){
			    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()});
				}
			    });
		    break;
		case "Price Lower":
		    productTableModel.setRowCount(0);                    
		    dataManager.getProductManager().getProducts().forEach((id, product) -> {
			if(product.getPrice() < Integer.parseInt(searchByTypeTextField.getText())){
			    productTableModel.addRow(new Object[]{product.getManufacturer(),
							product.getModelNumber(),
							product.getType(),
							product.getResolution(),
							product.getSize().getWidth() + "x" + product.getSize().getHeight(),
							product.getRefreshRate(),
							product.getPrice(),
							product.getInStock()});
				}
			    });
		    break;
	    }
	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        shopPanel = new javax.swing.JPanel();
        productScrollPane = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        shopLabel = new javax.swing.JLabel();
        searchByTypeTextField = new javax.swing.JTextField();
        searchByTypeComboBox = new javax.swing.JComboBox<>();
        searchLabel = new javax.swing.JLabel();
        addToCartButton = new javax.swing.JButton();
        addToWishlistButton = new javax.swing.JButton();
        cartPanel = new javax.swing.JPanel();
        productInCartScrollPane = new javax.swing.JScrollPane();
        productInCartTable = new javax.swing.JTable();
        cartLabel = new javax.swing.JLabel();
        removeFromCartButton = new javax.swing.JButton();
        payButton = new javax.swing.JButton();
        grandTotalTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        productMinusButton = new javax.swing.JButton();
        productPlusButton = new javax.swing.JButton();
        wishlistPanel = new javax.swing.JPanel();
        productInWishlistScrollPane = new javax.swing.JScrollPane();
        productInWishlistTable = new javax.swing.JTable();
        wishlistLabel = new javax.swing.JLabel();
        removeFromWishlistButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 800));

        productScrollPane.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        productTable.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Manufacturer", "Model Number", "Type", "Resolution", "Size", "Refresh Rate", "Price", "In Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productScrollPane.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(2).setHeaderValue("Type");
            productTable.getColumnModel().getColumn(3).setResizable(false);
            productTable.getColumnModel().getColumn(3).setHeaderValue("Resolution");
            productTable.getColumnModel().getColumn(4).setResizable(false);
            productTable.getColumnModel().getColumn(4).setHeaderValue("Size");
            productTable.getColumnModel().getColumn(5).setResizable(false);
            productTable.getColumnModel().getColumn(5).setHeaderValue("Refresh Rate");
            productTable.getColumnModel().getColumn(6).setResizable(false);
            productTable.getColumnModel().getColumn(7).setResizable(false);
        }

        shopLabel.setFont(new java.awt.Font("Calibri", 0, 50)); // NOI18N
        shopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shopLabel.setText("Very Awsome Online Monitor Shop");

        searchByTypeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchByTypeTextFieldKeyReleased(evt);
            }
        });

        searchByTypeComboBox.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        searchByTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manufacturer", "Model Number", "Type", "Price Greater", "Price Lower" }));
        searchByTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchComboBoxItemStateChanged(evt);
            }
        });
        searchByTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByTypeComboBoxActionPerformed(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchLabel.setText("Search By:");

        addToCartButton.setText("Add to Cart");
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        addToWishlistButton.setText("Add to Wishlist");
        addToWishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToWishlistButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shopPanelLayout = new javax.swing.GroupLayout(shopPanel);
        shopPanel.setLayout(shopPanelLayout);
        shopPanelLayout.setHorizontalGroup(
            shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addComponent(productScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shopPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(searchByTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchByTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addToWishlistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addToCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        shopPanelLayout.setVerticalGroup(
            shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToCartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addToWishlistButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(searchByTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchByTypeTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("WebShop", shopPanel);

        productInCartScrollPane.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        productInCartTable.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        productInCartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Manufacturer", "Model Number", "Price", "In Cart"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productInCartTable.getModel().addTableModelListener( new TableModelListener() { public void tableChanged(TableModelEvent evt){grandTotalUpdater(); } });
        productInCartScrollPane.setViewportView(productInCartTable);
        if (productInCartTable.getColumnModel().getColumnCount() > 0) {
            productInCartTable.getColumnModel().getColumn(0).setResizable(false);
            productInCartTable.getColumnModel().getColumn(1).setResizable(false);
            productInCartTable.getColumnModel().getColumn(2).setResizable(false);
            productInCartTable.getColumnModel().getColumn(3).setResizable(false);
        }

        cartLabel.setFont(new java.awt.Font("Calibri", 0, 50)); // NOI18N
        cartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartLabel.setText("Contents of my Cart");

        removeFromCartButton.setText("Remove from Cart");
        removeFromCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromCartButtonActionPerformed(evt);
            }
        });

        payButton.setText("Pay");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
            }
        });

        grandTotalTextField.setEditable(false);
        grandTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grandTotalTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Grand Total");

        productMinusButton.setText("-1");
        productMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productMinusButtonActionPerformed(evt);
            }
        });

        productPlusButton.setText("+1");
        productPlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPlusButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addComponent(productInCartScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPanelLayout.createSequentialGroup()
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productPlusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productMinusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeFromCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(payButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productInCartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cartPanelLayout.createSequentialGroup()
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeFromCartButton)
                            .addComponent(productPlusButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(payButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(grandTotalTextField)
                                .addComponent(jLabel1))))
                    .addComponent(productMinusButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cart", cartPanel);

        productInWishlistScrollPane.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        productInWishlistTable.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        productInWishlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Manufacturer", "Model Number", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productInWishlistScrollPane.setViewportView(productInWishlistTable);
        if (productInWishlistTable.getColumnModel().getColumnCount() > 0) {
            productInWishlistTable.getColumnModel().getColumn(0).setResizable(false);
            productInWishlistTable.getColumnModel().getColumn(1).setResizable(false);
            productInWishlistTable.getColumnModel().getColumn(2).setResizable(false);
        }

        wishlistLabel.setFont(new java.awt.Font("Calibri", 0, 50)); // NOI18N
        wishlistLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wishlistLabel.setText("Contents of my Wishlist");

        removeFromWishlistButton.setText("Remove from Wishlist");
        removeFromWishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromWishlistButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout wishlistPanelLayout = new javax.swing.GroupLayout(wishlistPanel);
        wishlistPanel.setLayout(wishlistPanelLayout);
        wishlistPanelLayout.setHorizontalGroup(
            wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wishlistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addComponent(productInWishlistScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wishlistPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeFromWishlistButton)))
                .addContainerGap())
        );
        wishlistPanelLayout.setVerticalGroup(
            wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wishlistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productInWishlistScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromWishlistButton)
                .addGap(41, 41, 41))
        );

        jTabbedPane1.addTab("Wishlist", wishlistPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("WebShopTab");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchByTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchByTypeComboBoxActionPerformed

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        int inStock = (Integer)productTable.getValueAt(productTable.getSelectedRow(), 7);
	boolean contains = false;
	int selectedRow = productTable.getSelectedRow();
        if(inStock >= 1){
            for (int i = 0; i < productInCartTable.getRowCount(); i++) {
                if (productTable.getValueAt(selectedRow, 0).toString().concat(productTable.getValueAt(selectedRow, 1).toString()).equalsIgnoreCase(productInCartTable.getValueAt(i, 0).toString().concat(productInCartTable.getValueAt(i, 1).toString()))) {
                    contains = true;
                }
            }
            
            if (contains) {
                JOptionPane.showMessageDialog(this, "This product is already in your cart");
            }else{
                productInCartTableModel.addRow(new Object [] {productTable.getValueAt(selectedRow, 0), productTable.getValueAt(selectedRow, 1), productTable.getValueAt(selectedRow, 6), 1});
            }
        }else{
            JOptionPane.showMessageDialog(this, "This product is not in stock");
        }
    }//GEN-LAST:event_addToCartButtonActionPerformed

    private void removeFromCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromCartButtonActionPerformed
        if(productInCartTable.getSelectedRow() != -1)
        {
            productInCartTableModel.removeRow(productInCartTable.getSelectedRow());
        }
    }//GEN-LAST:event_removeFromCartButtonActionPerformed

    private void grandTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grandTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grandTotalTextFieldActionPerformed

    private void productPlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPlusButtonActionPerformed
        int inStock;
        int inCart;
        int selectedRow = productInCartTable.getSelectedRow();
	boolean isInStock = false;
	
        if(selectedRow != -1){
	    inCart = Integer.parseInt(productInCartTableModel.getValueAt(selectedRow, 3).toString());
	    for(int i = 0; i < productTable.getRowCount(); i++){
		if(productTable.getValueAt(i, 0).toString().concat(productTable.getValueAt(i, 1).toString()).equals(productInCartTable.getValueAt(selectedRow, 0).toString().concat(productInCartTable.getValueAt(selectedRow, 1).toString()))){
		    inStock = (Integer)productTable.getValueAt(i, 7);
		    if(inStock > inCart && inCart > 0){
			productInCartTableModel.setValueAt(++inCart, selectedRow, 3);
		    }
		    isInStock = true;
		    break;
		}
	    }  
        }
	if(!isInStock){
	    JOptionPane.showMessageDialog(this, "No more product on stock");
	}
    }//GEN-LAST:event_productPlusButtonActionPerformed

    private void removeFromWishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromWishlistButtonActionPerformed
        if(productInWishlistTable.getSelectedRow() != -1)
        {
            productInWishlistTableModel.removeRow(productInWishlistTable.getSelectedRow());
        }
    }//GEN-LAST:event_removeFromWishlistButtonActionPerformed

    private void addToWishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToWishlistButtonActionPerformed
	boolean contains = false;
	int selectedRow = productTable.getSelectedRow();
        for (int i = 0; i < productInWishlistTable.getRowCount(); i++) {
            if (productTable.getValueAt(selectedRow, 0).toString().concat(productTable.getValueAt(selectedRow, 1).toString()).equalsIgnoreCase(productInWishlistTable.getValueAt(i, 0).toString().concat(productInWishlistTable.getValueAt(i, 1).toString()))) {
                contains = true;
            }
        }    
        if (contains) {
            JOptionPane.showMessageDialog(this, "This product is already on your wishlist");
        }else{
            productInWishlistTableModel.addRow(new Object [] {productTable.getValueAt(selectedRow, 0), productTable.getValueAt(selectedRow, 1), productTable.getValueAt(selectedRow, 6)});
        }

    }//GEN-LAST:event_addToWishlistButtonActionPerformed

    private void productMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productMinusButtonActionPerformed
        int inCart = 0;
	int selectedRow = productInCartTable.getSelectedRow();
        if(selectedRow != -1){
            inCart = Integer.parseInt(productInCartTableModel.getValueAt(selectedRow, 3).toString());
        }  
        if(inCart > 0 && inCart != 1){
            inCart--;
            productInCartTableModel.setValueAt(inCart, selectedRow, 3);
        }else if(inCart == 1){
            if(productInCartTable.getSelectedRow() != -1){
                productInCartTableModel.removeRow(selectedRow);
	    }   
        }
    }//GEN-LAST:event_productMinusButtonActionPerformed

    private void searchByTypeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchByTypeTextFieldKeyReleased
        searchByTypeUpdater();
    }//GEN-LAST:event_searchByTypeTextFieldKeyReleased

    private void searchComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchComboBoxItemStateChanged
        searchByTypeUpdater();
    }//GEN-LAST:event_searchComboBoxItemStateChanged

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
        if(productInCartTable.getRowCount() != 0){
	    for(int ix = 0; ix < productInCartTable.getRowCount(); ix++){
		for(int i = 0; i < productTable.getRowCount(); i++){
		    if(productTable.getValueAt(i, 0).toString().concat(productTable.getValueAt(i, 1).toString()).equals(productInCartTable.getValueAt(ix, 0).toString().concat(productInCartTable.getValueAt(ix, 1).toString()))){
			productTableModel.setValueAt(Integer.parseInt(productTable.getValueAt(i, 7).toString()) - Integer.parseInt(productInCartTable.getValueAt(ix, 3).toString()), i, 7);
			Map<Integer, Monitor> products = new LinkedHashMap<>();
			for(int iy = 0; iy < productTable.getRowCount(); iy++){
			    String[] size = productTable.getValueAt(iy, 4).toString().split("x");
			    products.put(iy, new Monitor(productTable.getValueAt(iy, 0).toString(),
					productTable.getValueAt(iy, 1).toString(),
					productTable.getValueAt(iy, 3).toString(),
					new DisplaySize(Integer.parseInt(size[0]), Integer.parseInt(size[1])),
					Integer.parseInt(productTable.getValueAt(iy, 6).toString()),
					DisplayType.valueOf(productTable.getValueAt(iy, 2).toString()),
					Integer.parseInt(productTable.getValueAt(iy, 5).toString()),
					Integer.parseInt(productTable.getValueAt(iy, 7).toString())));
			}
			dataManager.productManager.products = products;
			break;
		    }
		} 
	    }
	    productInCartTableModel.setRowCount(0);
	    JOptionPane.showMessageDialog(this, "Beep boop beep boop, i'm a little bank transaction, your card have been charged.");
        }else{
            JOptionPane.showMessageDialog(this, "Your cart is empty");
        }
    }//GEN-LAST:event_payButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JButton addToWishlistButton;
    private javax.swing.JLabel cartLabel;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JTextField grandTotalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton payButton;
    private javax.swing.JScrollPane productInCartScrollPane;
    private javax.swing.JTable productInCartTable;
    private javax.swing.JScrollPane productInWishlistScrollPane;
    private javax.swing.JTable productInWishlistTable;
    private javax.swing.JButton productMinusButton;
    private javax.swing.JButton productPlusButton;
    private javax.swing.JScrollPane productScrollPane;
    private javax.swing.JTable productTable;
    private javax.swing.JButton removeFromCartButton;
    private javax.swing.JButton removeFromWishlistButton;
    private javax.swing.JComboBox<String> searchByTypeComboBox;
    private javax.swing.JTextField searchByTypeTextField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JLabel shopLabel;
    private javax.swing.JPanel shopPanel;
    private javax.swing.JLabel wishlistLabel;
    private javax.swing.JPanel wishlistPanel;
    // End of variables declaration//GEN-END:variables
}
