package com.test2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class Jframe1 extends JFrame {
	
	@Autowired
	RestTemplate restTemplate;

	private JPanel contentPane;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframe1 frame = new Jframe1();
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
	public Jframe1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(197, 42, 146, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(113, 45, 69, 20);
		contentPane.add(lblName);
		
		JButton btnSayhello = new JButton("SayHello");
		btnSayhello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("SayHello button clicked");
				
				String ROOT_URI = "http://localhost:8081/migrations/status";
				restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.getForEntity(ROOT_URI, String.class);
				
				System.out.println("response = "+response.getBody());
				
			}
		});
		btnSayhello.setBounds(197, 96, 115, 29);
		contentPane.add(btnSayhello);
	}
	
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}
	
}
