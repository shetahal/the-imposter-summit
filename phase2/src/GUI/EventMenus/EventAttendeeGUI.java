package GUI.EventMenus;

import Controller.EventManagementSystem;
import Controller.LoginSystem;
import GUI.Main.PanelStack;

import javax.swing.*;
import java.awt.*;

public class EventAttendeeGUI {
    private PanelStack panelStack;
    private JList eventsJList = new JList();
    private JList yourEventsJList = new JList();

    private JLabel eventsJLabel = new JLabel("Events");
    private JLabel yourEventsJLabel = new JLabel("Your Events");

    private JButton signUpButton = new JButton("Sign Up");
    private JButton sort1Button = new JButton("Sort");
    private JButton sort2Button = new JButton("Sort");
    private JButton cancelButton = new JButton("Cancel");
    private JButton backButton = new JButton("Back");

    private JPanel eventsButtonPanel = new JPanel();
    private JPanel yourEventsButtonPanel = new JPanel();
    private JPanel eventsPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel eventsJlistPanel = new JPanel();
    private JPanel yourEventsJlistPanel = new JPanel();
    private JPanel yourEventsPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    private EventPanelBuilder panelBuilder = new EventPanelBuilder();

    private DefaultListModel<String> eventsListModel = new DefaultListModel<>();
    private DefaultListModel<String> yourEventsListModel = new DefaultListModel<>();

    private EventManagementSystem eventSystem;

    private JScrollPane eventsListScroller = new JScrollPane();
    private JScrollPane yourEventsListScroller = new JScrollPane();

    public EventAttendeeGUI(EventManagementSystem eventSystem, PanelStack panelStack) {
        this.eventSystem = eventSystem;
        this.panelStack = panelStack;
        backButtonListen();
    }

    public JPanel startEventPage() {
        // NorthPanel:
        panelBuilder.buildAttendeeNorthPanel(northPanel, eventsPanel, yourEventsPanel);
        //EventsPanel:
        panelBuilder.buildAttendeeEventsPanel(eventsPanel, eventsJLabel);
        // EventsJListPanel:
        eventsJList = new JList(eventsListModel);
        panelBuilder.buildAttendeeEventsJListPanel(eventsJlistPanel, eventsJList, eventsListScroller);
        eventsPanel.add(eventsJlistPanel, BorderLayout.CENTER);
        // EventsButtonPanel
        panelBuilder.buildAttendeeEventsButtonPanel(eventsButtonPanel, signUpButton, sort1Button);
        eventsPanel.add(eventsButtonPanel, BorderLayout.SOUTH);

        // YourEventsPanel:
        panelBuilder.buildAttendeeEventsPanel(yourEventsPanel, yourEventsJLabel);
        // Your Events JListPanel:
        yourEventsJList = new JList(yourEventsListModel);
        panelBuilder.buildAttendeeEventsJListPanel(yourEventsJlistPanel, yourEventsJList, yourEventsListScroller);
        yourEventsPanel.add(yourEventsJlistPanel, BorderLayout.CENTER);
        //YourEventsButtonPanel:
        panelBuilder.buildAttendeeEventsButtonPanel(yourEventsButtonPanel, cancelButton, sort2Button);
        yourEventsPanel.add(yourEventsButtonPanel, BorderLayout.SOUTH);

        //MainPanel
        panelBuilder.buildAttendeeMainPanel(mainPanel, northPanel, southPanel, backButton);
        return mainPanel;
    }

    private void backButtonListen(){
        backButton.addActionListener(e -> {
            panelStack.pop();
            JPanel panel = (JPanel) panelStack.pop();
            panelStack.loadPanel(panel);
        });
    }


}