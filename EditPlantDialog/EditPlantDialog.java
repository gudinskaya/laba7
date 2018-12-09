
package org.anngudin.laba10.EditPlantDialog;
 
import org.anngudin.laba10.Plant.Plant;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class EditPlantDialog extends JDialog implements ActionListener
{
   
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";
 
    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;
 

    private final JTextPane txtPlantName = new JTextPane();
    
    private final JTextPane txtFamily = new JTextPane();
   
    private final JTextPane txtAge = new JTextPane();
   
    private final JTextPane txtKind = new JTextPane();
 
    private Long plantId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;
 
    public EditPlantDialog() {
        this(null);
    }
 
    public EditPlantDialog(Plant plant) {
      
        setLayout(null);
 
        buildFields();
    
        initFields(plant);
       
        buildButtons();
 
        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        
        setBounds(300, 300, 450, 200);
       
        setVisible(true);
    }
 
   
    private void buildFields() {
        
        JLabel lblPlantName = new JLabel("Название:");
        
        lblPlantName.setHorizontalAlignment(SwingConstants.RIGHT);
       
        lblPlantName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
       
        add(lblPlantName);
        // Выставляем координаты поля
        txtPlantName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        txtPlantName.setBorder(BorderFactory.createEtchedBorder());
       
        add(txtPlantName);
 
        
        JLabel lblFamily = new JLabel("Семейство:");
        lblFamily.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFamily.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblFamily);
        txtFamily.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtFamily.setBorder(BorderFactory.createEtchedBorder());
        add(txtFamily);
 
       
        JLabel lblAge = new JLabel("Возраст:");
        lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAge.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblAge);
        
        txtAge.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtAge.setBorder(BorderFactory.createEtchedBorder());
        add(txtAge);
 
        
        JLabel lblKind = new JLabel("Вид:");
        lblKind.setHorizontalAlignment(SwingConstants.RIGHT);
        lblKind.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblKind);
        txtKind.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtKind.setBorder(BorderFactory.createEtchedBorder());
        add(txtKind);
    }
 
    private void initFields(Plant plant) {
        if (plant != null) {
            plantId = plant.getPlantId();
            txtPlantName.setText(plant.getPlantName());
            txtFamily.setText(plant.getFamily());
            txtKind.setText(plant.getKind());
            txtAge.setText(plant.getAge());
        }
    }
 
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);
 
        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }
 
    @Override

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        
        save = SAVE.equals(action);

        setVisible(false);
    }
 
    public boolean isSave() {
        return save;
    }
 
    public Plant getPlant() {
        Plant plant = new Plant(plantId, txtPlantName.getText(),
                txtFamily.getText(), txtAge.getText(), txtKind.getText());
        return plant;
    }
}
// try {
// int i = Integer.parseInt(plant.age);
// if (i < 0) {
// JOptionPane.showMessageDialog(this, "Число должно быть положительным");
// }
// } catch (NumberFormatException e) {
// JOptionPane.showMessageDialog(this, "Введите число");
// }
