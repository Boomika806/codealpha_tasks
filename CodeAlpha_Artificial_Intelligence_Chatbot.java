import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CodeAlpha_Artificial_Intelligence_Chatbot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Map<String, String> knowledgeBase;

    public CodeAlpha_Artificial_Intelligence_Chatbot() {
        
        trainBot();

        
        setTitle("AI Interactive Chatbot");
        setSize(450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(70, 130, 180));
        sendButton.setForeground(Color.WHITE);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

    
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

    
        chatArea.append("Bot: Hello! I am your AI assistant. How can I help you today?\n");

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void trainBot() {
        knowledgeBase = new HashMap<>();
        
        knowledgeBase.put("hello", "Hi there! How can I assist you?");
        knowledgeBase.put("who are you", "I am a Java-based AI chatbot programmed with rule-based logic.");
        knowledgeBase.put("java", "Java is a high-level, class-based, object-oriented programming language.");
        knowledgeBase.put("help", "I can answer questions about Java, tell you who I am, or just chat!");
        knowledgeBase.put("bye", "Goodbye! Have a productive day!");
    }

    private String getAIResponse(String userInput) {
        String input = userInput.toLowerCase();
        
        
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        
        
        return "I'm sorry, I don't have information on that yet. Can you try asking differently?";
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            chatArea.append("You: " + text + "\n");
            
            
            String response = getAIResponse(text);
            
            chatArea.append("Bot: " + response + "\n");
            chatArea.append("--------------------------------------------------\n");
            
            inputField.setText("");
            chatArea.setCaretPosition(chatArea.getDocument().getLength()); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CodeAlpha_Artificial_Intelligence_Chatbot());
    }
}



