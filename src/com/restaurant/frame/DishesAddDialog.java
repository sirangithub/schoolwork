package com.restaurant.frame;

import com.restaurant.dao.Impl.CategoryDaoImpl;
import com.restaurant.dao.Impl.DishDaoImpl;
import com.restaurant.entity.Category;
import com.restaurant.entity.Dish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class DishesAddDialog extends JDialog {
    Dish dish = null;
    DishDaoImpl ddi = null;
    CategoryDaoImpl cdi = null;
    List<Category> listCategory = null;
    public DishesAddDialog() {
        super();
        setModal(true);
        setTitle("菜品管理");
        setBounds(100, 100, 500, 475);
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        dish = new Dish();
        ddi = new DishDaoImpl();
        cdi = new CategoryDaoImpl();
        listCategory = cdi.getList();
        JLabel nameLabel = new JLabel("菜品名：");
        JTextField nameTxt = new JTextField(20);
        JLabel categoryIDLabel = new JLabel("菜品类别:");
        JComboBox categoryIDCombo = new JComboBox();
        for(Category cate:listCategory){
            categoryIDCombo.addItem(cate.getName());
        }
        JLabel picLabel = new JLabel("图片：");
        JTextField picTxt = new JTextField(10);
        JLabel codeLabel = new JLabel("代码：");
        JTextField codeTxt = new JTextField(50);
        JLabel unitLabel = new JLabel("单位：");
        JTextField unitTxt = new JTextField(20);
        JLabel priceLabel = new JLabel("单价：");
        JTextField priceTxt = new JTextField(20);
        JLabel statusLabel = new JLabel("状态：");
        JTextField statusTxt = new JTextField(20);
        inputPanel.add(nameLabel);
        inputPanel.add(nameTxt);
        inputPanel.add(categoryIDLabel);
        inputPanel.add(categoryIDCombo);
        inputPanel.add(picLabel);
        inputPanel.add(picTxt);
        inputPanel.add(codeLabel);
        inputPanel.add(codeTxt);
        inputPanel.add(unitLabel);
        inputPanel.add(unitTxt);
        inputPanel.add(priceLabel);
        inputPanel.add(priceTxt);
        inputPanel.add(statusLabel);
        inputPanel.add(statusTxt);
        nameLabel.setBounds(120, 20, 160, 30);
        nameTxt.setBounds(180, 20, 160, 30);
        categoryIDLabel.setBounds(120, 60, 160, 30);
        categoryIDCombo.setBounds(180, 60, 160, 30);
        picLabel.setBounds(120, 100, 160, 30);
        picTxt.setBounds(180, 100, 160, 30);
        codeLabel.setBounds(120, 140, 160, 30);
        codeTxt.setBounds(180, 140, 160, 30);
        unitLabel.setBounds(120, 180, 160, 30);
        unitTxt.setBounds(180, 180, 160, 30);
        priceLabel.setBounds(120, 220, 160, 30);
        priceTxt.setBounds(180, 220, 160, 30);
        statusLabel.setBounds(120, 260, 160, 30);
        statusTxt.setBounds(180, 260, 160, 30);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okBtn = new JButton("确定");
        JButton cancleBtn = new JButton("取消");
        buttonPanel.add(okBtn);
        buttonPanel.add(cancleBtn);
        okBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Dish dish=new Dish();
                DishDaoImpl ddi=new DishDaoImpl();
                Category category=new Category();
                CategoryDaoImpl cdi=new CategoryDaoImpl();
                String str=(String) categoryIDCombo.getSelectedItem();
                category=cdi.getCategoryByName(str);

                dish.setCategory(category);
                dish.setName(nameTxt.getText());
                dish.setPic(picTxt.getText());
                dish.setCode(codeTxt.getText());
                dish.setUnit(unitTxt.getText());
                dish.setPrice(Double.parseDouble(priceTxt.getText()));
                dish.setStatus(statusTxt.getText());
                ddi.save(dish);
                setVisible(false);
                JOptionPane.showMessageDialog(null,"添加成功");
            }

        });
        cancleBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });

    }
}
