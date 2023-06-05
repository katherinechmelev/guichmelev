import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MyWeatherGUI extends JFrame implements ActionListener, ItemListener {
    private JTextField zipcodeEntry;
    private JButton submitButton;
    private JButton clearButton;
    private JCheckBox showCelsiusCheckBox;
    private JLabel tempLabel;
    private JLabel conditionLabel;
    private JPanel mainPanel;
    private JLabel locationLabel;
    private JLabel conditionIcon;
    private Weather weather;

    public MyWeatherGUI() {
        createUIComponents();
    }

    private void createUIComponents() {
        setContentPane(mainPanel);
        setTitle("Weather App");
        setSize(550, 200);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        showCelsiusCheckBox.addItemListener(this);
        setVisible(true);
    }

    private void loadWeather(String zip) {
        weather = WeatherNetworking.getWeatherForZip(zip);
        locationLabel.setText("Location: " + weather.getLocation());
        if (showCelsiusCheckBox.isSelected()) {
            tempLabel.setText("Current temp: " + weather.getTempC() + "°C");
        } else {
            tempLabel.setText("Current temp: " + weather.getTempF() + "°F");
        }
        conditionLabel.setText("Current condition: " + weather.getCondition());
        try {
            URL imageURL = new URL(weather.getIconURL());
            BufferedImage image = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(image);
            conditionIcon.setIcon(icon);
        } catch (IOException e) { }
    }

    private void clear() {
        zipcodeEntry.setText("");
        locationLabel.setText("Location: ");
        tempLabel.setText("Current temp: ");
        conditionLabel.setText("Current condition: ");
        conditionIcon.setIcon(new ImageIcon("src/placeholder.jpg"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Submit")) {
                String zipCode = zipcodeEntry.getText();
                loadWeather(zipCode);
            } else {
                clear();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox box = (JCheckBox) (e.getSource());
        if (box.isSelected() && weather != null) {
            tempLabel.setText("Current temp: " + weather.getTempC() + "°C");
        } else if (!box.isSelected() && weather != null) {
            tempLabel.setText("Current temp: " + weather.getTempF() + "°F");
        }
    }
}
